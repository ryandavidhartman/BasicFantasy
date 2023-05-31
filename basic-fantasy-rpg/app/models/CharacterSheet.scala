package models

import basic.fantasy.backgrounds.BackgroundGenerator.Background
import basic.fantasy.characterclass.SavingsThrows
import basic.fantasy.equipment.Equipment
import reactivemongo.api.bson.BSONObjectID

case class CharacterSheet(
                           _id: Option[BSONObjectID],
                           name: Option[String],
                           characterAttributes: Option[CharacterAttributes],
                           combat: Option[Combat],
                           savingsThrows: Option[SavingsThrows],
                           abilitiesRestrictions: Option[Seq[String]],
                           turnUndead: Option[Map[Int, String]],
                           spells: Option[Map[Int, Spell]],
                           background: Option[Background],
                           equipment: Option[Equipment]
                         ) extends Model {

}
