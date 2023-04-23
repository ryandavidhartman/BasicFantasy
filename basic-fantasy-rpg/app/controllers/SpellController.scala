package controllers


import play.api.libs.json.{JsValue, Json, __}

import javax.inject._
import play.api.mvc._
import reactivemongo.api.bson.{BSONObjectID, BSONString}
import repositories.SpellRepository
import rules.Spell

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

@Singleton
class SpellController @Inject()(
                                 implicit executionContext: ExecutionContext,
                                 val spellRepository: SpellRepository,
                                 val controllerComponents: ControllerComponents)
  extends BaseController {

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

  def update(
              id: String): Action[JsValue] = Action.async(controllerComponents.parsers.json) { implicit request => {
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
