package models

import play.api.libs.json.{Json, OFormat}
import reactivemongo.api.bson._

import scala.util.Try

case class Spell(
                  id: Option[String],
                  name:String,
                  range: String,
                  cleric: Option[Int],
                  magicUser: Option[Int],
                  duration: String,
                  description:String
                )

object Spell {
  import reactivemongo.play.json.compat._
  import bson2json._
  import lax._
  import json2bson._
  implicit object bsonWriter extends BSONDocumentWriter[Spell] {
    override def writeTry(spell: Spell): Try[BSONDocument] = Try {
      BSONDocument(
        "id"         -> spell.id,
        "name"        -> spell.name,
        "range"       -> spell.range,
        "cleric"      -> spell.cleric,
        "magicUser"   -> spell.magicUser,
        "duration"    -> spell.duration,
        "description" -> spell.description
      )
    }
  }
  implicit object bsonReader extends BSONDocumentReader[Spell] {
    override def readDocument(doc: BSONDocument): Try[Spell] = Try {
      Spell(
        id         =  doc.getAsOpt[String]("_id"),
        name        = doc.getAsOpt[String]("name").getOrElse("unknown"),
        range       = doc.getAsOpt[String]("range").getOrElse("unknown"),
        cleric      = doc.getAsOpt[Int]("cleric"),
        magicUser   = doc.getAsOpt[Int]("magicUser"),
        duration    = doc.getAsOpt[String]("duration").getOrElse("unknown"),
        description = doc.getAsOpt[String]("description").getOrElse("unknown")
      )
    }
  }

  implicit val jsonFormat: OFormat[Spell] = Json.format[Spell]
  def getLevel(levelString: String): Option[Int] = levelString match {
    case "1" => Some(1)
    case "2" => Some(2)
    case "3" => Some(3)
    case "4" => Some(4)
    case "5" => Some(5)
    case "6" => Some(6)
    case "None" => None
  }
}

