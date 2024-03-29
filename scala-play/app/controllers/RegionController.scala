package controllers

import controllers.RegionForm.regionForm
import models.Region
import play.api.data.Form
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{Action, AnyContent, MessagesAbstractController, MessagesControllerComponents, MessagesRequest, Request}
import reactivemongo.api.bson.BSONObjectID
import repositories.RegionRepository

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

@Singleton
class RegionController @Inject()(
                                  implicit executionContext: ExecutionContext,
                                  val regionRepository: RegionRepository,
                                  controllerComponents: MessagesControllerComponents)
  extends MessagesAbstractController(controllerComponents) {

  import Region._

  // Region UI

  private val updateRegionCall = routes.RegionController.updateRegion()
  private val createRegionCall = routes.RegionController.createRegion()

  private val updateRegionURL = "http://localhost:9000/regions/update"

  def getRegionsPage(campaign: Option[String],
                    name: Option[String]): Action[AnyContent] = Action.async { implicit request: MessagesRequest[AnyContent] =>
    regionRepository.get(campaign, name)
      .map(regions => Ok(views.html.regionsGet(regions.sortBy(_.name), updateRegionURL)))
  }

  def getListPage(campaign: Option[String]): Action[AnyContent] = Action.async { implicit request: MessagesRequest[AnyContent] =>
    regionRepository.get(campaign, None)
      .map(regions => Ok(views.html.regionsList(regions.sortBy(_.name), updateRegionURL)))
  }
  def createRegionPage(): Action[AnyContent] = Action { implicit request: MessagesRequest[AnyContent] =>
    Ok(views.html.regionCreate(regionForm, createRegionCall))
  }
  def createRegion(): Action[AnyContent] = Action { implicit request: MessagesRequest[AnyContent] =>

    val errorFunction = { formWithErrors: Form[Region] =>
      // This is the bad case, where the form had validation errors.
      // Let's show the user the form again, with the errors highlighted.
      // Note how we pass the form with errors to the template.
      println(formWithErrors.errors.mkString(";"))
      BadRequest(views.html.regionCreate(formWithErrors, createRegionCall))
    }

    val successFunction = { region: Region =>
      // This is the good case, where the form was successfully parsed as a Data object.
      regionRepository.create(region)
      Redirect(routes.RegionController.getRegionsPage(None, None))
        .flashing("info" -> s"${region.name} added!")
    }

    val formValidationResult = regionForm.bindFromRequest()
    formValidationResult.fold(errorFunction, successFunction)
  }
  def updateRegionPage(id: String): Action[AnyContent] = Action.async { implicit request: MessagesRequest[AnyContent] =>
    val regionF: Future[Option[Region]] = regionRepository.findOne(id)
    regionF.map((r: Option[Region]) => r match {
      case Some(region) => Ok(views.html.regionUpdate(regionForm.fill(region), updateRegionCall))
      case None => Ok(views.html.regionCreate(regionForm, createRegionCall))
    })
  }

  def viewRegionPage(id: String): Action[AnyContent] = ???
  def updateRegion(): Action[AnyContent] = Action { implicit request: MessagesRequest[AnyContent] =>

    val errorFunction = { formWithErrors: Form[Region] =>
      // This is the bad case, where the form had validation errors.
      // Let's show the user the form again, with the errors highlighted.
      // Note how we pass the form with errors to the template.
      println(formWithErrors.errors.mkString(";"))
      BadRequest(views.html.regionUpdate(formWithErrors, updateRegionCall))
    }

    val successFunction = { region: Region =>
      regionRepository.update(region)
      Redirect(routes.RegionController.getRegionsPage(None, None))
        .flashing("info" -> s"${region.name} updated!")
    }

    val formValidationResult = regionForm.bindFromRequest()
    formValidationResult.fold(errorFunction, successFunction)
  }




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
              description = region.description
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
