package models

import play.api.libs.json.{Format, JsObject, JsResult, JsValue, Json, OFormat, Reads, Writes}
import reactivemongo.api.bson.{BSON, BSONArray, BSONDocument, BSONDocumentReader, BSONDocumentWriter, BSONElement, BSONInteger, BSONObjectID, BSONReader, BSONString}

import scala.util.Try

case class Demography(
                     race: String,
                     lowRange: Int,
                     highRange: Int
                     )

case class Region(
                 _id: Option[BSONObjectID],
                 campaign: String,
                 name: String,
                 ruler: String,
                 capital: String,
                 population: String,
                 `demi-humans`: String,
                 humanoids: String,
                 resources: String,
                 description: String,
                 demography: Seq[Demography]
                 ) extends Model

object Region {

  import reactivemongo.play.json.compat._
  import bson2json._
  import json2bson._
  import lax._

  implicit object bsonDemographyWriter extends BSONDocumentWriter[Demography] {
    override def writeTry(demography: Demography): Try[BSONDocument] = Try {
      BSONDocument(
        "race" -> demography.race,
        "lowRange" -> demography.lowRange,
        "highRange" -> demography.highRange
      )
    }
  }
  implicit object bsonRegionWriter extends BSONDocumentWriter[Region] {
    override def writeTry(region: Region): Try[BSONDocument] = Try {

      val demographyDoc = region.demography.map { d =>
        BSONDocument(
          "race" -> d.race,
          "lowRange" -> d.lowRange,
          "highRange" -> d.highRange
        )
      }

      BSONDocument(
        "_id" -> region._id,
        "campaign" -> region.campaign,
        "name" -> region.name,
        "ruler" -> region.ruler,
        "capital" -> region.capital,
        "population" -> region.population,
        "demi-humans" -> region.`demi-humans`,
        "humanoids" -> region.humanoids,
        "resources" -> region.resources,
        "description" -> region.description,
        "demography" -> demographyDoc
      )
    }
  }
  implicit object SeqDemographyBSONWriter extends BSONDocumentWriter[Seq[Demography]] {
    def writeTry(seq: Seq[Demography]): Try[BSONDocument] = Try {
      val demographyDocs = seq.map { demography =>
        BSONDocument(
          "race" -> BSONString(demography.race),
          "lowRange" -> BSONInteger(demography.lowRange),
          "highRange" -> BSONInteger(demography.highRange)
        )
      }

      BSONDocument("demography" -> BSONArray(demographyDocs))
    }
  }
  implicit object bsonDemographyReader extends BSONDocumentReader[Demography] {
    override def readDocument(doc: BSONDocument): Try[Demography] = Try {
      val race = doc.getAsOpt[String]("race").getOrElse("unknown")
      val lowRange = doc.getAsOpt[Int]("lowRange").getOrElse(-1)
      val highRange = doc.getAsOpt[Int]("highRange").getOrElse(-1)

      Demography(race, lowRange, highRange)
    }
  }
  implicit object SeqDemographyBSONReader extends BSONDocumentReader[Seq[Demography]] {
    def readDocument(doc: BSONDocument): Try[Seq[Demography]] = Try {
      doc.getAsTry[BSONArray]("demography").map { bsonArray =>
        bsonArray.values.collect {
          case doc: BSONDocument =>
            val race = doc.getAsTry[BSONString]("race").get.value
            val lowRange = doc.getAsTry[BSONInteger]("lowRange").get.value
            val highRange = doc.getAsTry[BSONInteger]("highRange").get.value

            Demography(race, lowRange, highRange)
        }.toSeq
      }.getOrElse(Seq.empty)
    }
  }
  implicit object bsonRegionReader extends BSONDocumentReader[Region] {
    override def readDocument(doc: BSONDocument): Try[Region] = Try {
      Region(
        _id = doc.getAsOpt[BSONObjectID]("_id"),
        campaign = doc.getAsOpt[String]("campaign").getOrElse("unknown"),
        name = doc.getAsOpt[String]("name").getOrElse("unknown"),
        ruler = doc.getAsOpt[String]("ruler").getOrElse("unknown"),
        capital = doc.getAsOpt[String]("capital").getOrElse("unknown"),
        population = doc.getAsOpt[String]("population").getOrElse("unknown"),
        `demi-humans` = doc.getAsOpt[String]("demi-humans").getOrElse("unknown"),
        humanoids = doc.getAsOpt[String]("humanoids").getOrElse("unknown"),
        resources = doc.getAsOpt[String]("resources").getOrElse("unknown"),
        description = doc.getAsOpt[String]("description").getOrElse("unknown"),
        demography = doc.getAsOpt[Seq[Demography]]("demography").getOrElse(Seq.empty[Demography])
      )
    }
  }

  implicit val jsonDemographyFormat: OFormat[Demography] = Json.format[Demography]
  implicit val jsonSeqDemographyFormat: OFormat[Seq[Demography]] = new OFormat[Seq[Demography]] {
    override def reads(json: JsValue): JsResult[Seq[Demography]] = {
      (json \ "demography").validate[Seq[Demography]]
    }

    override def writes(o: Seq[Demography]): JsObject = {
      Json.obj("demography" -> Json.toJson(o.map(Json.toJson(_)(jsonDemographyFormat))))
    }
  }


  implicit val jsonRegionFormat: OFormat[Region] = Json.format[Region]
}
