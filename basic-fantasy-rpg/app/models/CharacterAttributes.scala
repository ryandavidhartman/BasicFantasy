package models

import basic.fantasy.backgrounds.CharacterAlignments.CharacterAlignment
import basic.fantasy.backgrounds.Races.{All_RACES, Race}
import basic.fantasy.characterclass.CharacterClasses.{All_CLASSES, CharacterClass}
import play.api.data.Forms.{mapping, number, optional, text}
import play.api.data.Mapping
import play.api.libs.json.{Json, OFormat}
import reactivemongo.api.bson.{BSONDocument, BSONDocumentReader, BSONDocumentWriter, BSONObjectID}

import scala.util.Try

case class CharacterAttributes(
  race: Option[String],
  characterClass: Option[String],
  level: Option[Int],
  gender: Option[String],
  height: Option[String],
  weight: Option[Int],
  age: Option[Int],
  alignment: Option[String],
  personality: Option[String],
  strength: Option[Int],
  dexterity: Option[Int],
  constitution: Option[Int],
  intelligence: Option[Int],
  wisdom: Option[Int],
  charisma: Option[Int]
)


object CharacterAttributes {
  val characterAttributesMapping: Mapping[CharacterAttributes] = mapping(
    "race" ->  optional(text).verifying("Invalid Race", value => value.forall(All_RACES.contains)),
    "characterClass" -> optional(text).verifying("Invalid Class", value => value.forall(All_CLASSES.toList.contains)),
    "level" -> optional(number).verifying("Invalid Level", value => value.forall(num => num >= 1 && num <= 20)),
    "gender" -> optional(text).verifying("Invalid Gender", value => value.forall(gender => gender == "Male" || gender == "Female")),
    "height" -> optional(text),
    "weight" -> optional(number),
    "age" ->  optional(number),
    "alignment" -> optional(text).verifying("Invalid Alignment", value => value.forall(gender => gender == "Lawful" || gender == "Neutral" || gender == "Chaotic")),
    "personality" -> optional(text),
    "strength" -> optional(number).verifying("Invalid Str", value => value.forall(num => num >= 3 && num <= 18)),
    "dexterity" -> optional(number).verifying("Invalid Dex", value => value.forall(num => num >= 3 && num <= 18)),
    "constitution" -> optional(number).verifying("Invalid Con", value => value.forall(num => num >= 3 && num <= 18)),
    "intelligence" -> optional(number).verifying("Invalid Int", value => value.forall(num => num >= 3 && num <= 18)),
    "wisdom" -> optional(number).verifying("Invalid Wis", value => value.forall(num => num >= 3 && num <= 18)),
    "charisma" -> optional(number).verifying("Invalid Cha", value => value.forall(num => num >= 3 && num <= 18))
  )(CharacterAttributes.apply)(CharacterAttributes.unapply)

  val default = CharacterAttributes(
    race = Some("Human"),
    characterClass = Some("Fighter"),
    level = Some(1),
    gender = Some("Male"),
    height = Some("""5' 10""""),
    weight = Some(170),
    age = Some(25),
    alignment = Some("Lawful"),
    personality = Some("peppy/steppy"),
    strength = Some(12),
    dexterity = Some(12),
    constitution = Some(12),
    intelligence = Some(12),
    wisdom = Some(12),
    charisma = Some(12)
  )

  import reactivemongo.play.json.compat._
  import bson2json._
  import json2bson._
  import lax._
  implicit object bsonWriter extends BSONDocumentWriter[CharacterAttributes] {
    override def writeTry(characterAttributes: CharacterAttributes): Try[BSONDocument] = Try {
      BSONDocument(
        "race" -> characterAttributes.race,
        "characterClass" ->  characterAttributes.characterClass,
        "level" -> characterAttributes.level,
        "gender" -> characterAttributes.gender,
        "height" -> characterAttributes.height,
        "weight" -> characterAttributes.weight,
        "age" -> characterAttributes.age,
        "alignment" ->  characterAttributes.alignment,
        "personality" ->  characterAttributes.personality,
        "strength" ->  characterAttributes.strength,
        "dexterity" ->  characterAttributes.dexterity,
        "constitution" ->  characterAttributes.constitution,
        "intelligence" -> characterAttributes.intelligence,
        "wisdom" -> characterAttributes.wisdom,
        "charisma" ->  characterAttributes.charisma
      )
    }
  }
  implicit object bsonReader extends BSONDocumentReader[CharacterAttributes] {
    override def readDocument(doc: BSONDocument): Try[CharacterAttributes] = Try {
      CharacterAttributes(
        race = doc.getAsOpt[String]("race"),
        characterClass = doc.getAsOpt[String]("characterClass"),
        level = doc.getAsOpt[Int]("level"),
        gender = doc.getAsOpt[String]("gender"),
        height = doc.getAsOpt[String]("height"),
        weight = doc.getAsOpt[Int]("weight"),
        age = doc.getAsOpt[Int]("age"),
        alignment = doc.getAsOpt[String]("alignment"),
        personality = doc.getAsOpt[String]("personality"),
        strength = doc.getAsOpt[Int]("strength"),
        dexterity = doc.getAsOpt[Int]("dexterity"),
        constitution = doc.getAsOpt[Int]("constitution"),
        intelligence = doc.getAsOpt[Int]("intelligence"),
        wisdom = doc.getAsOpt[Int]("wisdom"),
        charisma = doc.getAsOpt[Int]("charisma")
      )
    }
  }

  implicit val jsonFormat: OFormat[CharacterAttributes] = Json.format[CharacterAttributes]
}
