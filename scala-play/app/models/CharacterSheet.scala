package models

import basic.fantasy.backgrounds.BackgroundGenerator.Background
import basic.fantasy.characterclass.SavingsThrows
import basic.fantasy.equipment.Equipment
import play.api.libs.json.{Json, OFormat}
import reactivemongo.api.bson.{BSONDocument, BSONDocumentReader, BSONDocumentWriter, BSONObjectID}

import scala.util.Try

case class CharacterSheet(
  _id: Option[BSONObjectID],
  name: String,
  attributes: Option[CharacterAttributes]
  //combat: Option[Combat],
  //savingsThrows: Option[SavingsThrows],
  //abilitiesRestrictions: Option[Seq[String]],
  //turnUndead: Option[Map[Int, String]],
  //spells: Option[Map[Int, Spell]],
  //background: Option[Background],
  //equipment: Option[Equipment]
  ) extends Model {

}

object CharacterSheet {

  import reactivemongo.play.json.compat._
  import bson2json._
  import json2bson._
  import lax._

  implicit object bsonWriter extends BSONDocumentWriter[CharacterSheet] {
    override def writeTry(characterSheet: CharacterSheet): Try[BSONDocument] = Try {
      BSONDocument(
        "_id" -> characterSheet._id,
        "name" -> characterSheet.name,
      )
    }
  }
  implicit object bsonReader extends BSONDocumentReader[CharacterSheet] {
    override def readDocument(doc: BSONDocument): Try[CharacterSheet] = Try {
      CharacterSheet(
        _id = doc.getAsOpt[BSONObjectID]("_id"),
        name = doc.getAsOpt[String]("name").getOrElse("unknown"),
        attributes = doc.getAsOpt[CharacterAttributes]("characterAttributes")
      )
    }
  }

  implicit val jsonFormat: OFormat[CharacterSheet] = Json.format[CharacterSheet]
}
