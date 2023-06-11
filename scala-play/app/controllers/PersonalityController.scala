package controllers

import models.Personality
import models.Personality.BulkPersonalities
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{Action, MessagesAbstractController, MessagesControllerComponents}
import repositories.PersonalityRepository

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}


@Singleton
class PersonalityController @Inject()(
                                       implicit executionContext: ExecutionContext,
                                       val personalityRepository: PersonalityRepository,
                                       controllerComponents: MessagesControllerComponents)
  extends MessagesAbstractController(controllerComponents) {

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