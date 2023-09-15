package controllers

import models.Personality
import models.Personality.BulkPersonalities
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{Action, AnyContent, MessagesAbstractController, MessagesControllerComponents, Request}
import reactivemongo.api.Cursor
import reactivemongo.api.bson.{BSONArray, BSONDocument}
import repositories.PersonalityRepository

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}


@Singleton
class PersonalityController @Inject()(
                                       implicit executionContext: ExecutionContext,
                                       val personalityRepository: PersonalityRepository,
                                       controllerComponents: MessagesControllerComponents)
  extends MessagesAbstractController(controllerComponents) {


  def get( name: Option[String],
           description: Option[String],
           alignment: Option[String], limit: Option[Int]): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    personalityRepository.get(name, description, alignment, limit.getOrElse(4)).map {
      personalities => Ok(Json.toJson(personalities))
    }
  }


  def create(): Action[JsValue] = Action.async(controllerComponents.parsers.json) { implicit request =>
    request.body.validate[Personality].fold(
      _ => Future.successful(BadRequest("Cannot parse request body")),
      personality =>
        personalityRepository.create(personality).map {
          _ => Created(Json.toJson(personality))
        }
    )
  }

  def createBulk(): Action[JsValue] = Action.async(controllerComponents.parsers.json) { implicit request =>
    request.body.validate[BulkPersonalities].fold(
      _ => Future.successful(BadRequest("Cannot parse request body")),
      bulkPersonalities => bulkPersonalities.names.map { n =>
        val personality = Personality(
          _id = None,
          name = n,
          description = bulkPersonalities.description,
          alignment = bulkPersonalities.alignment
        )
        personalityRepository.create(personality).map {
          _ => Created(Json.toJson(personality))
        }
      }
    )
    Future.successful(Ok)
  }
}