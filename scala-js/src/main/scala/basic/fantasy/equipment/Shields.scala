package basic.fantasy.equipment

import basic.fantasy.Roller
import basic.fantasy.characterclass.CharacterClasses.CharacterClass

object Shields {
  sealed abstract class Shield(
     val baseCost: Int,
     val weight: Int,
     val baseACModMelee: Int,
     val baseACModMissile: Int,
     val magicBonus: Int = 0
   ) {
    def name: String
    def acModMelee: Int = baseACModMelee + magicBonus
    def acModMissile: Int = baseACModMissile + magicBonus
    def cost: Int = if(magicBonus > 1)
      baseCost + (1000 * Math.pow(4,magicBonus-1)).toInt
    else
      baseCost

    override def toString: String = {
      val magicString = if(magicBonus > 0) s" (+$magicBonus)" else ""
      s"$name$magicString, ac mod melee: +$acModMelee, ac mod missile: +$acModMissile, weight: $weight"
    }
  }

  case object NoShield extends Shield(0, 0, 0, 0) {
    override val name = "None"
    override def toString(): String = "None"
  }

  case class Buckler(magic: Int) extends Shield(5, 2, 1, 0, magic) {
    override val name = "Buckler"
  }

  case class MediumShield(magic: Int) extends Shield(7, 5, 1, 1, magic) {
    override val name = "Medium Shield"
  }

  case class TowerShield(magic: Int) extends Shield(15, 12, 1, 3, magic) {
    override val name = "Tower Shield"
  }

  def getShield(characterClass: CharacterClass, level: Int): Shield = {
    val magic: Int = Roller.randomMagicWeaponBonus(level)
    val roll = Roller.randomInt(100)

    characterClass match {
      case m if m.isMagicUser || m.isMonk => NoShield
      case t if t.isThief => NoShield
      case _ =>
        if(roll < 40)
          NoShield
        else if (roll < 55)
          Buckler(magic)
        else if (roll < 85)
          MediumShield(magic)
        else
          TowerShield(magic)
    }
  }
}
