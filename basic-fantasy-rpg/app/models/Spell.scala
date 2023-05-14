package models

import play.api.libs.json.{Json, OFormat}
import reactivemongo.api.bson.{BSONDocument, BSONDocumentReader, BSONDocumentWriter, BSONObjectID}

import scala.util.Try

case class Spell(
 _id: Option[BSONObjectID],
 name: String,
 range: String,
 cleric: Option[Int],
 magicUser: Option[Int],
 duration: String,
 description: String
) extends Model

object Spell {

  import reactivemongo.play.json.compat._
  import bson2json._
  import json2bson._
  import lax._
  implicit object bsonWriter extends BSONDocumentWriter[Spell] {
    override def writeTry(spell: Spell): Try[BSONDocument] = Try {
      BSONDocument(
        "_id" -> spell._id,
        "name" -> spell.name,
        "range" -> spell.range,
        "cleric" -> spell.cleric,
        "magicUser" -> spell.magicUser,
        "duration" -> spell.duration,
        "description" -> spell.description
      )
    }
  }

  implicit object bsonReader extends BSONDocumentReader[Spell] {
    override def readDocument(doc: BSONDocument): Try[Spell] = Try {
      Spell(
        _id = doc.getAsOpt[BSONObjectID]("_id"),
        name = doc.getAsOpt[String]("name").getOrElse("unknown"),
        range = doc.getAsOpt[String]("range").getOrElse("unknown"),
        cleric = doc.getAsOpt[Int]("cleric"),
        magicUser = doc.getAsOpt[Int]("magicUser"),
        duration = doc.getAsOpt[String]("duration").getOrElse("unknown"),
        description = doc.getAsOpt[String]("description").getOrElse("unknown")
      )
    }
  }

  implicit val jsonFormat: OFormat[Spell] = Json.format[Spell]
}
