package models

import reactivemongo.api.bson._
import play.api.libs.json._

import scala.util.{Success, Failure, Try}

case class Demography(
                       race: String,
                       lowRange: Int,
                       highRange: Int
                     )

object Demography {
  // BSON Serialization
  implicit object DemographyBSONWriter extends BSONDocumentWriter[Demography] {
    def writeTry(demography: Demography): Try[BSONDocument] = Try {
      BSONDocument(
        "race" -> BSONString(demography.race),
        "lowRange" -> BSONInteger(demography.lowRange),
        "highRange" -> BSONInteger(demography.highRange)
      )
    }
  }

  implicit object DemographyBSONReader extends BSONDocumentReader[Demography] {
    def readDocument(doc: BSONDocument): Try[Demography] = Try {
      val race = doc.getAsOpt[BSONString]("race").get.value
      val lowRange = doc.getAsOpt[BSONInteger]("lowRange").get.value
      val highRange = doc.getAsOpt[BSONInteger]("highRange").get.value

      Demography(race, lowRange, highRange)
    }
  }

  // JSON Serialization/Deserialization
  implicit val demographyFormat: OFormat[Demography] = Json.format[Demography]
}

case class Region(
                   _id: Option[BSONObjectID],
                   campaign: String,
                   name: String,
                   ruler: String,
                   capital: String,
                   population: String,
                   demiHumans: String,
                   humanoids: String,
                   resources: String,
                   description: String
                 )  extends Model

object Region {


  implicit val objectIdFormat: Format[BSONObjectID] = new Format[BSONObjectID] {
    override def writes(objectId: BSONObjectID): JsValue = Json.obj("$oid" -> JsString(objectId.stringify))

    override def reads(json: JsValue): JsResult[BSONObjectID] = json match {
      case JsObject(obj) if obj.contains("$oid") =>
        obj("$oid").validate[String].flatMap { str =>
          BSONObjectID.parse(str) match {
            case Success(bsonId) => JsSuccess(bsonId)
            case Failure(e) => JsError(e.getMessage)
          }
        }
      case _ => JsError("Invalid BSONObjectID format")
    }
  }
  // BSON Serialization
  implicit object RegionBSONWriter extends BSONDocumentWriter[Region] {
    def writeTry(region: Region): Try[BSONDocument] = Try {
      BSONDocument(
        "_id" -> region._id.getOrElse(BSONUndefined),
        "campaign" -> BSONString(region.campaign),
        "name" -> BSONString(region.name),
        "ruler" -> BSONString(region.ruler),
        "capital" -> BSONString(region.capital),
        "population" -> BSONString(region.population),
        "demiHumans" -> BSONString(region.demiHumans),
        "humanoids" -> BSONString(region.humanoids),
        "resources" -> BSONString(region.resources),
        "description" -> BSONString(region.description)
      )
    }
  }

  // BSON Deserialization
  implicit object RegionBSONReader extends BSONDocumentReader[Region] {
    def readDocument(doc: BSONDocument): Try[Region] = Try {
      val id = doc.getAsOpt[BSONObjectID]("_id")
      val campaign = doc.getAsOpt[BSONString]("campaign").get.value
      val name = doc.getAsOpt[BSONString]("name").get.value
      val ruler = doc.getAsOpt[BSONString]("ruler").get.value
      val capital = doc.getAsOpt[BSONString]("capital").get.value
      val population = doc.getAsOpt[BSONString]("population").get.value
      val demiHumans = doc.getAsOpt[BSONString]("demiHumans").get.value
      val humanoids = doc.getAsOpt[BSONString]("humanoids").get.value
      val resources = doc.getAsOpt[BSONString]("resources").get.value
      val description = doc.getAsOpt[BSONString]("description").get.value

      Region(id, campaign, name, ruler, capital, population, demiHumans, humanoids, resources, description)
    }
  }

  // JSON Serialization/Deserialization
  implicit val regionFormat: OFormat[Region] = Json.format[Region]
}
