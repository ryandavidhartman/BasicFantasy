package models

import reactivemongo.api.bson.BSONObjectID

import scala.util.{Failure, Success}

case class SpellDto(
 _id: BSONObjectID,
 name: String,
 range: String,
 cleric: Option[Int],
 magicUser: Option[Int],
 duration: String,
 description: String
)

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