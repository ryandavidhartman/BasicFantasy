package controllers

import models.Name
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{Action, AnyContent, MessagesAbstractController, MessagesControllerComponents, MessagesRequest}
import repositories.NameRepository

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class NameController @Inject()(
                                 implicit executionContext: ExecutionContext,
                                 val nameRepository: NameRepository,
                                 controllerComponents: MessagesControllerComponents)
  extends MessagesAbstractController(controllerComponents) {

  // Names UI Methods
  def getNamesPage(name: Option[String],
                   firstName: Option[Boolean],
                   lastName: Option[Boolean],
                   gender: Option[String],
                   race: Option[String]): Action[AnyContent] = Action.async { implicit request: MessagesRequest[AnyContent] =>
    nameRepository.get(name, firstName, lastName, gender, race)
      .map(names => Ok(views.html.namesGet(names.sortBy(_.name))))
  }

  // Names CRUD Methods
  def get(name: Option[String],
          firstName: Option[Boolean],
          lastName: Option[Boolean],
          gender: Option[String],
          race: Option[String]): Action[AnyContent] = Action.async { implicit request: MessagesRequest[AnyContent] =>
    nameRepository.get(name, firstName, lastName, gender, race).map(names => Ok(Json.toJson(names)))
  }

  def create(): Action[JsValue] = Action.async(controllerComponents.parsers.json) { implicit request =>
    request.body.validate[Name].fold(
      _ => Future.successful(BadRequest("Cannot parse request body")),
      name =>
        nameRepository.create(name).map {
          _ => Created(Json.toJson(name))
        }
    )
  }
}