package controllers

import models.Region
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{Action, AnyContent, MessagesAbstractController, MessagesControllerComponents, Request}
import repositories.RegionRepository

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class RegionController @Inject()(
                                  implicit executionContext: ExecutionContext,
                                  val regionRepository: RegionRepository,
                                  controllerComponents: MessagesControllerComponents)
  extends MessagesAbstractController(controllerComponents) {


  def list(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    regionRepository.list().map {
      names => Ok(Json.toJson(names))
    }
  }

  def create(): Action[JsValue] = Action.async(controllerComponents.parsers.json) { implicit request =>
    request.body.validate[Region].fold(
      _ => Future.successful(BadRequest("Cannot parse request body")),
      region =>
        regionRepository.create(region).map {
          _ => Created(Json.toJson(region))
        }
    )
  }



}
