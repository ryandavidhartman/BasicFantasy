package models

import play.api.libs.json.{Json, OFormat}
import reactivemongo.api.bson.{BSONDocument, BSONDocumentReader, BSONDocumentWriter, BSONObjectID}

import scala.util.{Failure, Success, Try}

case class SpellDto(
 _id: BSONObjectID,
 name: String,
 range: String,
 cleric: Option[Int],
 magicUser: Option[Int],
 duration: String,
 description: String
)

object SpellDto {

  import reactivemongo.play.json.compat._
  import bson2json._
  import lax._
  import json2bson._
  implicit object bsonWriter extends BSONDocumentWriter[SpellDto] {
    override def writeTry(spell: SpellDto): Try[BSONDocument] = Try {
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

  implicit object bsonReader extends BSONDocumentReader[SpellDto] {
    override def readDocument(doc: BSONDocument): Try[SpellDto] = Try {
      SpellDto(
        _id = doc.getAsOpt[BSONObjectID]("_id").get,
        name = doc.getAsOpt[String]("name").getOrElse("unknown"),
        range = doc.getAsOpt[String]("range").getOrElse("unknown"),
        cleric = doc.getAsOpt[Int]("cleric"),
        magicUser = doc.getAsOpt[Int]("magicUser"),
        duration = doc.getAsOpt[String]("duration").getOrElse("unknown"),
        description = doc.getAsOpt[String]("description").getOrElse("unknown")
      )
    }
  }

  implicit val jsonFormat: OFormat[SpellDto] = Json.format[SpellDto]
}

object SpellConverters {
  def apply(spell: Spell): SpellDto = {
    val bsonObjectID: BSONObjectID = BSONObjectID.parse(spell.id.get) match {
        case Success(bId) => bId
        case Failure(ex) => throw new Exception("problem with Spell id", ex)
      }

    SpellDto(bsonObjectID, spell.name, spell.range, spell.cleric, spell.magicUser, spell.duration, spell.description)
  }

  def apply(spell: SpellDto): Spell =
    Spell(Some(spell._id.toString), spell.name, spell.range, spell.cleric, spell.magicUser, spell.duration, spell.description)
}