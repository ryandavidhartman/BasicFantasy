package controllers

import models.Region
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{Action, AnyContent, MessagesAbstractController, MessagesControllerComponents, Request}
import reactivemongo.api.bson.BSONObjectID
import repositories.RegionRepository

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Success, Failure}

@Singleton
class RegionController @Inject()(
                                  implicit executionContext: ExecutionContext,
                                  val regionRepository: RegionRepository,
                                  controllerComponents: MessagesControllerComponents)
  extends MessagesAbstractController(controllerComponents) {

  import Region._

  // Region CRUD API
  def get(campaign: Option[String], name: Option[String]): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    regionRepository.get(campaign, name).map {
      regions => Ok(Json.toJson(regions))
    }
  }
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

  def update(id: String): Action[JsValue] = Action.async(controllerComponents.parsers.json) { implicit request =>
    request.body.validate[Region].fold(_ => Future.successful(BadRequest("Cannot parse request body")),
      region => {
        val objectIdTryResult = BSONObjectID.parse(id)
        objectIdTryResult match {
          case Success(objectId) =>
            val regionDto = Region(
              _id = Some(objectId),
              campaign =  region.campaign,
              name = region.name,
              ruler = region.ruler,
              capital = region.capital,
              population = region.population,
              demiHumans = region.demiHumans,
              humanoids = region.humanoids,
              resources = region.resources,
              description = region.description,
              demography = region.demography
            )
            regionRepository.update(regionDto).map(result => Ok(Json.toJson(result.n)))
          case Failure(_) => Future.successful(BadRequest(s"Cannot update the region id: $id"))
        }
      }
    )
  }

  def delete(id: String): Action[AnyContent] = Action.async { implicit request =>
    val objectIdTryResult = BSONObjectID.parse(id)
    objectIdTryResult match {
      case Success(objectId) => regionRepository.delete(objectId).map(_ => NoContent)
      case Failure(_) => Future.successful(BadRequest(s"Cannot parse the region id: $id"))
    }
  }

}
