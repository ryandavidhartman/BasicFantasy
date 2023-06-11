package basic.webapp

import basic.fantasy.backgrounds.BackgroundGenerator.Background
import basic.fantasy.backgrounds.CharacterAlignments.CharacterAlignment
import basic.fantasy.backgrounds.Races.Race
import basic.fantasy.backgrounds.Spells.Spell
import basic.fantasy.characterclass.CharacterClasses.CharacterClass
import basic.fantasy.characterclass.KiPowers.KiPower
import basic.fantasy.characterclass.SavingsThrows
import basic.fantasy.equipment.Equipment

object CharacterState {

}
case class CharacterState(
  name: String,
  race: Race,
  characterClass: CharacterClass,
  level: Int,
  gender: String,
  height: String,
  weight: Int,
  age: Int,
  alignment: CharacterAlignment,
  personality: String,
  strength: Int,
  dexterity: Int,
  constitution: Int,
  intelligence: Int,
  wisdom: Int,
  charisma: Int,
  attackBonus: String,
  meleeBonus: String,
  rangedBonus: String,
  acBonus: String,
  acBase: String,
  hitPoints: Int,
  savingsThrows: SavingsThrows,
  abilities: String,
  background: Background,
  equipment: Equipment,
  spells: Seq[String],
  turnUndead: Seq[String],
  thiefSkills: Seq[String],
  monkSkills: Seq[String],
  monkPowers: Set[KiPower]
)
