package basic.fantasy.rules

import basic.fantasy.Roller
import basic.fantasy.backgrounds.Races._
import basic.fantasy.characterclass.CharacterClasses.{CharacterClass, MagicUser}

object BasicFantasy {

  def calcBaseAttackModifier(characterClass: CharacterClass, level: Int): String = {
    characterClass match {
      case f if f.isFighter =>
        if (level == 1)
          "+1"
        else if (level <= 3)
          "+2"
        else if (level == 4)
          "+3"
        else if (level <= 6)
          "+4"
        else if (level == 7)
          "+5"
        else if (level <= 10)
          "+6"
        else if (level <= 12)
          "+7"
        else if (level <= 15)
          "+8"
        else if (level <= 17)
          "+9"
        else
          "+10"
      case s if s.isThief || s.isCleric =>
        if (level <= 2)
          "+1"
        else if (level == 4)
          "+2"
        else if (level <= 6)
          "+3"
        else if (level <= 8)
          "+4"
        else if (level <= 11)
          "+5"
        else if (level <= 14)
          "+6"
        else if (level <= 17)
          "+7"
        else
          "+8"
      case MagicUser =>
        if (level <= 3)
          "+1"
        else if (level <= 5)
          "+2"
        else if (level <= 8)
          "+3"
        else if (level <= 12)
          "+4"
        else if (level <= 15)
          "+5"
        else if (level <= 18)
          "+6"
        else
          "+7"
    }
  }

  def calcMeleeAttackModifier(strength: Int, baseAttackBonus: Int): String = {
    val num = baseAttackBonus + attributeModifiers(strength)
    modifierBonusIntToString(num)
  }

  def calcRangeAttackModifier(dexterity: Int, baseAttackBonus: Int, race: Race): String = {
    val num = if (race == Halfling)
      baseAttackBonus + attributeModifiers(dexterity) + 1
    else
      baseAttackBonus + attributeModifiers(dexterity)
    modifierBonusIntToString(num)
  }

  def calcACModifier(dexterity: Int, wisdom: Int, characterClass: CharacterClass, level: Int): String = {
    val dexMod = attributeModifiers(dexterity)
    modifierBonusIntToString(dexMod)
  }

  def calcHitPoints(characterClass: CharacterClass, race: Race, level: Int, constitution: Int): Int = {
    val mod = attributeModifiers(constitution)
    val cutOffLevel = 9

    val truncated_level = Math.min(level, cutOffLevel)
    val hitDice: Int = characterClass match {
      case f if f.isFighter => 8
      case c if c.isCleric || (c.isFighter && c.isMagicUser) => 6
      case s if s.isThief || s.isMagicUser => 4
    }

    val modHitDice: Int = race match {
      case Elf => if (hitDice > 6) 6 else hitDice
      case Halfling => if (hitDice > 6) 6 else hitDice
      case _ => hitDice
    }

    val hpForFirst9 = Roller.rollHP(truncated_level, modHitDice, mod)

    val hpFor9AndUp = if (level > cutOffLevel) level - cutOffLevel else 0

    hpForFirst9 + hpFor9AndUp
  }

  def attributeModifiers(attribute: Int): Int = attribute match {
    case 3 => -3
    case i if i >= 4 && i <= 5 => -2
    case i if i >= 6 && i <= 8 => -1
    case i if i >= 9 && i <= 12 => 0
    case i if i >= 13 && i <= 15 => 1
    case i if i >= 16 && i <= 17 => 2
    case _ => 3
  }

  def getLanguageBonus(int: Int): Int = Math.max(0, attributeModifiers(int))

  def modifierBonusIntToString(mod: Int): String = {
    if (mod > 0)
      s"+$mod"
    else
      mod.toString
  }

  def checkCharacterClass(race: Race, characterClass: CharacterClass): Boolean = {
    val isFighterClericThief: Boolean =
      (characterClass.isFighter || characterClass.isThief || characterClass.isCleric) && !characterClass.isMultiClass

    race match {
      case Human => !characterClass.isMultiClass
      case Elf => true
      case Dwarf => isFighterClericThief
      case Halfling => isFighterClericThief
      case HalfElf => true
      case HalfOrc => isFighterClericThief
    }
  }

  def getAbilities(race: Race, characterClass: CharacterClass): String = {
    val abilities = characterClass.getAbilities() ++ race.weapons ++ race.getAbilities

    abilities.mkString("&#13;&#10;&#13;&#10;")
  }
}
