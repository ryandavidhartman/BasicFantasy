package models

import play.api.libs.json.{Json, OFormat}
import reactivemongo.api.bson.{BSONDocument, BSONDocumentReader, BSONDocumentWriter, BSONObjectID}

import scala.util.Try

case class Name(
 _id: Option[BSONObjectID],
 name: String,
 firstName: Option[Boolean],
 lastName: Option[Boolean],
 gender: Option[String],
 race: String
) extends Model

object Name {

  import reactivemongo.play.json.compat._
  import bson2json._
  import json2bson._
  import lax._

  implicit object bsonWriter extends BSONDocumentWriter[Name] {
    override def writeTry(name: Name): Try[BSONDocument] = Try {
      BSONDocument(
        "_id" -> name._id,
        "name" -> name.name,
        "firstName" -> name.firstName,
        "lastName" -> name.lastName,
        "gender" -> name.gender,
        "race" -> name.race
      )
    }
  }
  implicit object bsonReader extends BSONDocumentReader[Name] {
    override def readDocument(doc: BSONDocument): Try[Name] = Try {
      Name(
        _id = doc.getAsOpt[BSONObjectID]("_id"),
        name = doc.getAsOpt[String]("name").getOrElse("unknown"),
        firstName = doc.getAsOpt[Boolean]("firstName"),
        lastName = doc.getAsOpt[Boolean]("lastName"),
        gender = doc.getAsOpt[String]("gender"),
        race = doc.getAsOpt[String]("race").getOrElse("unknown"),
      )
    }
  }

  implicit val jsonFormat: OFormat[Name] = Json.format[Name]
}
