package controllers


import controllers.SpellForm.spellForm
import controllers.SpellDtoForm.spellDtoForm
import play.api.libs.json.{JsValue, Json, __}

import javax.inject._
import play.api.data._
import play.api.i18n._
import play.api.mvc._
import repositories.SpellRepository
import models.{Spell, SpellConverters, SpellDto}
import reactivemongo.api.bson.{BSONObjectID, BSONString}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}


@Singleton
class SpellController @Inject()(
                                 implicit executionContext: ExecutionContext,
                                 val spellRepository: SpellRepository,
                                 controllerComponents: MessagesControllerComponents)
  extends MessagesAbstractController(controllerComponents) {

  private val createSpellCall = routes.SpellController.createSpell()
  private val updateSpellCall = routes.SpellController.updateSpell()

  private val updateSpellURL = "http://localhost:9000/spells/update"


  // Spell UI Methods
  def getSpells: Action[AnyContent] = Action.async { implicit request: MessagesRequest[AnyContent] =>
    val spellsF: Future[Seq[SpellDto]] = spellRepository.findAll(100)
    spellsF.map(spells => Ok(views.html.getSpells(spells, updateSpellURL)))
  }
  def createPage() = Action { implicit request: MessagesRequest[AnyContent] =>
    Ok(views.html.createSpell(spellForm, createSpellCall))
  }

  def createSpell(): Action[AnyContent] = Action { implicit request: MessagesRequest[AnyContent] =>

    val errorFunction = { formWithErrors: Form[Spell] =>
      // This is the bad case, where the form had validation errors.
      // Let's show the user the form again, with the errors highlighted.
      // Note how we pass the form with errors to the template.
      println(formWithErrors.errors.mkString(";"))
      BadRequest(views.html.createSpell(formWithErrors, createSpellCall))
    }

    val successFunction = { spell: Spell =>
      // This is the good case, where the form was successfully parsed as a Data object.
      spellRepository.create(spell)
      Redirect(routes.SpellController.getSpells())
        .flashing("info" -> s"${spell.name} added!")
    }

    val formValidationResult = spellForm.bindFromRequest()
    formValidationResult.fold(errorFunction, successFunction)
  }

  def updatePage(id: String) = Action.async { implicit request: MessagesRequest[AnyContent] =>
    val spellF: Future[Option[SpellDto]] = spellRepository.findOne(id)
    spellF.map(_ match {
      case Some(spell) => Ok(views.html.updateSpell(spellDtoForm.fill(spell), updateSpellCall))
      case None => Ok(views.html.createSpell(spellForm, createSpellCall))
    })
  }

  def updateSpell(): Action[AnyContent] = Action { implicit request: MessagesRequest[AnyContent] =>

    val errorFunction = { formWithErrors: Form[SpellDto] =>
      // This is the bad case, where the form had validation errors.
      // Let's show the user the form again, with the errors highlighted.
      // Note how we pass the form with errors to the template.
      println(formWithErrors.errors.mkString(";"))
      BadRequest(views.html.updateSpell(formWithErrors, createSpellCall))
    }

    val successFunction = { spell: SpellDto =>
      spellRepository.update(spell)
      Redirect(routes.SpellController.getSpells())
        .flashing("info" -> s"${spell.name} updated!")
    }

    val formValidationResult = spellDtoForm.bindFromRequest()
    formValidationResult.fold(errorFunction, successFunction)
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

  def update(id: String): Action[JsValue] = Action.async(controllerComponents.parsers.json) { implicit request =>
    request.body.validate[Spell].fold(_ => Future.successful(BadRequest("Cannot parse request body")),
      spell => {
        val objectIdTryResult = BSONObjectID.parse(id)
        objectIdTryResult match {
          case Success(objectId) =>
            val spellDto = SpellDto(objectId, spell.name, spell.range, spell.cleric, spell.magicUser, spell.duration, spell.description)
            spellRepository.update(spellDto).map(result => Ok(Json.toJson(result.n)))
          case Failure(_) => Future.successful(BadRequest("Cannot parse the spell id"))
        }
      }
    )
  }

  def delete(id: String): Action[AnyContent] = Action.async { implicit request =>
    val objectIdTryResult = BSONObjectID.parse(id)
    objectIdTryResult match {
      case Success(objectId) => spellRepository.delete(objectId).map(_ => NoContent)
      case Failure(_) => Future.successful(BadRequest("Cannot parse the spell id"))
    }
  }

}
