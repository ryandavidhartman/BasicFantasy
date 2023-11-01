package controllers


import controllers.SpellForm.spellForm
import models.Spell
import play.api.data._
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import reactivemongo.api.bson.BSONObjectID
import repositories.SpellRepository

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}


@Singleton
class SpellController @Inject()(
                                 implicit executionContext: ExecutionContext,
                                 val spellRepository: SpellRepository,
                                 controllerComponents: MessagesControllerComponents)
  extends MessagesAbstractController(controllerComponents) {

  private val createSpellCall = routes.SpellController.createSpell()
  private val updateSpellCall = routes.SpellController.updateSpell()

  private val updateSpellURL = "http://localhost:9000/spells/update"
  private val viewSpellURL = "http://localhost:9000/spells/view"


  // Spell UI Methods
  def getSpellsPage(name: Option[String],
                    range: Option[String],
                    cleric: Option[Int],
                    magicUser: Option[Int],
                    duration: Option[String],
                    description: Option[String],
                    alignment: Option[String]): Action[AnyContent] = Action.async { implicit request: MessagesRequest[AnyContent] =>
    spellRepository.get(name, range, cleric, magicUser, duration, description, alignment)
      .map(spells =>  Ok(views.html.spellsGet(spells.sortBy(_.name), updateSpellURL)))
  }

  def getListPage(): Action[AnyContent] = Action.async { implicit request: MessagesRequest[AnyContent] =>
    spellRepository.list().map(l => Ok(views.html.spellList(l, viewSpellURL)))
  }


  def createSpellPage(): Action[AnyContent] = Action { implicit request: MessagesRequest[AnyContent] =>
    Ok(views.html.spellCreate(spellForm, createSpellCall))
  }

  def createSpell(): Action[AnyContent] = Action { implicit request: MessagesRequest[AnyContent] =>

    val errorFunction = { formWithErrors: Form[Spell] =>
      // This is the bad case, where the form had validation errors.
      // Let's show the user the form again, with the errors highlighted.
      // Note how we pass the form with errors to the template.
      println(formWithErrors.errors.mkString(";"))
      BadRequest(views.html.spellCreate(formWithErrors, createSpellCall))
    }

    val successFunction = { spell: Spell =>
      // This is the good case, where the form was successfully parsed as a Data object.
      spellRepository.create(spell)
      Redirect(routes.SpellController.getSpellsPage(None, None, None, None, None, None, None))
        .flashing("info" -> s"${spell.name} added!")
    }

    val formValidationResult = spellForm.bindFromRequest()
    formValidationResult.fold(errorFunction, successFunction)
  }

  def updateSpellPage(id: String): Action[AnyContent] = Action.async { implicit request: MessagesRequest[AnyContent] =>
    val spellF: Future[Option[Spell]] = spellRepository.findOne(id)
    spellF.map((s: Option[Spell]) => s match {
      case Some(spell) => Ok(views.html.spellUpdate(spellForm.fill(spell), updateSpellCall))
      case None => Ok(views.html.spellCreate(spellForm, createSpellCall))
    })
  }

  def viewSpellPage(id: String): Action[AnyContent] = Action.async { implicit request: MessagesRequest[AnyContent] =>
    val spellF: Future[Option[Spell]] = spellRepository.findOne(id)
    spellF.map((s: Option[Spell]) => s match {
      case Some(spell) => Ok(views.html.spellsView(spell, updateSpellURL))
      case None => NotFound(Json.toJson(s"bad  id: $id"))
    })
  }

  def updateSpell(): Action[AnyContent] = Action { implicit request: MessagesRequest[AnyContent] =>

    val errorFunction = { formWithErrors: Form[Spell] =>
      // This is the bad case, where the form had validation errors.
      // Let's show the user the form again, with the errors highlighted.
      // Note how we pass the form with errors to the template.
      println(formWithErrors.errors.mkString(";"))
      BadRequest(views.html.spellUpdate(formWithErrors, updateSpellCall))
    }

    val successFunction = { spell: Spell =>
      spellRepository.update(spell)
      Redirect(routes.SpellController.getSpellsPage(None, None, None, None, None, None, None))
        .flashing("info" -> s"${spell.name} updated!")
    }

    val formValidationResult = spellForm.bindFromRequest()
    formValidationResult.fold(errorFunction, successFunction)
  }

  // CRUD API for Spells
  def get(name: Option[String],
          range: Option[String],
          cleric: Option[Int],
          magicUser: Option[Int],
          duration: Option[String],
          description: Option[String],
          alignment: Option[String]): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    spellRepository.get(name, range, cleric, magicUser, duration, description, alignment).map {
      spells => Ok(Json.toJson(spells))
    }
  }

  def list(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] => {
    spellRepository.list().map {
      names => Ok(Json.toJson(names))}
    }
  }
  def create(): Action[JsValue] = Action.async(controllerComponents.parsers.json) { implicit request =>
    request.body.validate[Spell].fold(
      _ => Future.successful(BadRequest("Cannot parse request body")),
      spell =>
        spellRepository.create(spell).map {
          _ => Created(Json.toJson(spell))
        }
    )
  }

  def update(id: String): Action[JsValue] = Action.async(controllerComponents.parsers.json) { implicit request =>
    request.body.validate[Spell].fold(_ => Future.successful(BadRequest("Cannot parse request body")),
      spell => {
        val objectIdTryResult = BSONObjectID.parse(id)
        objectIdTryResult match {
          case Success(objectId) =>
            val spellDto = Spell(Some(objectId), spell.name, spell.range, spell.cleric, spell.magicUser, spell.duration, spell.description, spell.alignment)
            spellRepository.update(spellDto).map(result => Ok(Json.toJson(result.n)))
          case Failure(_) => Future.successful(BadRequest(s"Cannot update the spell id: $id"))
        }
      }
    )
  }

  def delete(id: String): Action[AnyContent] = Action.async { implicit request =>
    val objectIdTryResult = BSONObjectID.parse(id)
    objectIdTryResult match {
      case Success(objectId) => spellRepository.delete(objectId).map(_ => NoContent)
      case Failure(_) => Future.successful(BadRequest(s"Cannot parse the spell id: $id"))
    }
  }

}
