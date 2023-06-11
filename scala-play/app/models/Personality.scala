package models

import play.api.libs.json.{Json, OFormat}
import reactivemongo.api.bson.{BSONDocument, BSONDocumentReader, BSONDocumentWriter, BSONObjectID}

import scala.util.Try

case class Personality(
  _id: Option[BSONObjectID],
  name: String,
  description: Option[String],
  alignment: Option[String]
) extends Model

object Personality {

  import reactivemongo.play.json.compat._
  import bson2json._
  import json2bson._
  import lax._

  implicit object bsonWriter extends BSONDocumentWriter[Personality] {
    override def writeTry(personality: Personality): Try[BSONDocument] = Try {
      BSONDocument(
        "_id" -> personality._id,
        "name" -> personality.name,
        "description" -> personality.description,
        "alignment" -> personality.alignment,
      )
    }
  }
  implicit object bsonReader extends BSONDocumentReader[Personality] {
    override def readDocument(doc: BSONDocument): Try[Personality] = Try {
      Personality(
        _id = doc.getAsOpt[BSONObjectID]("_id"),
        name = doc.getAsOpt[String]("name").getOrElse("unknown"),
        description = doc.getAsOpt[String]("description"),
        alignment = doc.getAsOpt[String]("alignment"),
      )
    }
  }

  case class BulkPersonalities(
                        names: Seq[String],
                        description: Option[String],
                        alignment: Option[String]
                      )

  implicit val jsonNamesFormat: OFormat[Personality] = Json.format[Personality]
  implicit val jsonBulkNamesFormat: OFormat[BulkPersonalities] = Json.format[BulkPersonalities]
}