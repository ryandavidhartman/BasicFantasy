package controllers


import play.api.libs.json.{JsValue, Json, __}

import javax.inject._
import play.api.data._
import play.api.i18n._
import play.api.mvc._
import repositories.SpellRepository
import models.Spell
import reactivemongo.api.bson.{BSONObjectID, BSONString}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

@Singleton
class SpellController @Inject()(
                                 implicit executionContext: ExecutionContext,
                                 val spellRepository: SpellRepository,
                                 controllerComponents: MessagesControllerComponents)
  extends MessagesAbstractController(controllerComponents) {

  // Spell UI Methods
  def listSpells: Action[AnyContent] = Action.async { implicit request: MessagesRequest[AnyContent] =>
    val spellsF: Future[Seq[Spell]] = spellRepository.findAll(100)
    spellsF.map(spells => Ok(views.html.listSpells(spells)))
  }
  def createPage() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.createSpell())
  }

  def saveSpellButtonClicked(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    println("ryan ryan ryan")
    val name: String = request.body.asFormUrlEncoded.get("spellNameTextbox").head
    val range: String = request.body.asFormUrlEncoded.get("spellRangeTextbox").head
    val cleric: Option[Int] = Spell.getLevel(request.body.asFormUrlEncoded.get("clericSpellLevel").head)
    val magicUser: Option[Int] = Spell.getLevel(request.body.asFormUrlEncoded.get("magicUserSpellLevel").head)
    val duration: String = request.body.asFormUrlEncoded.get("spellDurationTextbox").head
    val description: String = request.body.asFormUrlEncoded.get("spellDescription").mkString("\n")

    val newSpell = Spell(None, name, range, cleric, magicUser, duration, description)

    spellRepository.create(newSpell)


    Ok // Return a simple response
  }



  // CRUD API for Spells
  def findAll(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    spellRepository.findAll().map {
      spells => Ok(Json.toJson(spells))
    }
  }

  def findByName(spellName: String): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    spellRepository.findOne(fieldName = "name", fieldValue = BSONString(spellName)).map {
      spell => Ok(Json.toJson(spell))
    }
  }

  def create(): Action[JsValue] = Action.async(controllerComponents.parsers.json) { implicit request => {

    request.body.validate[Spell].fold(
      _ => Future.successful(BadRequest("Cannot parse request body")),
      spell =>
        spellRepository.create(spell).map {
          _ => Created(Json.toJson(spell))
        }
    )
  }
  }

  def update(id: String): Action[JsValue] = Action.async(controllerComponents.parsers.json) { implicit request => {
    request.body.validate[Spell].fold(
      _ => Future.successful(BadRequest("Cannot parse request body")),
      spell => {
        val objectIdTryResult = BSONObjectID.parse(id)
        objectIdTryResult match {
          case Success(objectId) => spellRepository.update(objectId, spell).map {
            result => Ok(Json.toJson(result.n))
          }
          case Failure(_) => Future.successful(BadRequest("Cannot parse the spell id"))
        }
      }
    )
  }
  }

  def delete(id: String): Action[AnyContent] = Action.async { implicit request => {
    val objectIdTryResult = BSONObjectID.parse(id)
    objectIdTryResult match {
      case Success(objectId) => spellRepository.delete(objectId).map {
        _ => NoContent
      }
      case Failure(_) => Future.successful(BadRequest("Cannot parse the spell id"))
    }
  }
  }

}
