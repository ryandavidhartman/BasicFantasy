package models

import play.api.libs.json.{Json, OFormat}
import reactivemongo.api.bson.{BSONDocument, BSONDocumentReader, BSONDocumentWriter, BSONObjectID}

import scala.util.Try

case class Region(
                 _id: Option[BSONObjectID],
                 name: String,
                 ruler: String,
                 capital: String,
                 population: String,
                 `demi-humans`: String,
                 humanoids: String,
                 resources: String,
                 description: String,
                 campaign: String
                 ) extends Model

object Region {

  import reactivemongo.play.json.compat._
  import bson2json._
  import json2bson._
  import lax._
  implicit object bsonWriter extends BSONDocumentWriter[Region] {
    override def writeTry(region: Region): Try[BSONDocument] = Try {
      BSONDocument(
        "_id" -> region._id,
        "name" -> region.name,
        "ruler" -> region.ruler,
        "capital" -> region.capital,
        "population" -> region.population,
        "demi-humans" -> region.`demi-humans`,
        "humanoids" -> region.humanoids,
        "resources" -> region.resources,
        "description" -> region.description,
        "campaign" -> region.campaign
      )
    }
  }
  implicit object bsonReader extends BSONDocumentReader[Region] {
    override def readDocument(doc: BSONDocument): Try[Region] = Try {
      Region(
        _id = doc.getAsOpt[BSONObjectID]("_id"),
        name = doc.getAsOpt[String]("name").getOrElse("unknown"),
        ruler = doc.getAsOpt[String]("ruler").getOrElse("unknown"),
        capital = doc.getAsOpt[String]("capital").getOrElse("unknown"),
        population = doc.getAsOpt[String]("population").getOrElse("unknown"),
        `demi-humans` = doc.getAsOpt[String]("demi-humans").getOrElse("unknown"),
        humanoids = doc.getAsOpt[String]("humanoids").getOrElse("unknown"),
        resources = doc.getAsOpt[String]("resources").getOrElse("unknown"),
        description = doc.getAsOpt[String]("description").getOrElse("unknown"),
        campaign = doc.getAsOpt[String]("campaign").getOrElse("unknown"),
      )
    }
  }

  implicit val jsonFormat: OFormat[Region] = Json.format[Region]
}
