package basic.fantasy.equipment

import basic.fantasy.Roller
import basic.fantasy.characterclass.CharacterClasses._

object Armors {

  sealed trait ArmorType
  case object NoneOrMagicLeather extends ArmorType
  case object LeatherOrMagicMetal extends ArmorType
  case object Metal extends ArmorType

  sealed abstract class Armor(
    val baseCost: Int,
    val weight: Int,
    val baseAC: Int,
    val magicBonus: Int = 0
  ) {
    def name: String
    def ac: Int = baseAC + magicBonus
    def cost: Int = if(magicBonus > 1)
      baseCost + (1500 * Math.pow(4,magicBonus-1)).toInt
    else
      baseCost
    def armorType: ArmorType = if(magicBonus > 0) LeatherOrMagicMetal else Metal

    override def toString: String = {
      val magicString = if(magicBonus > 0) s" (+$magicBonus)" else ""
      s"$name$magicString, ac: $ac, weight: $weight"
    }
  }

  case object NoArmor extends Armor(0, 0, 11) {
    override val name = "None"
    override def toString: String = "None"
    override val armorType: ArmorType = NoneOrMagicLeather
  }

  case object Padded extends Armor(15, 10, 12) {
    override val name = "Padded"
    override val armorType: ArmorType  = NoneOrMagicLeather
  }

  case object Hide extends Armor(10, 30, 13) {
    override val name = "Hide"
    override val armorType: ArmorType  = NoneOrMagicLeather
  }

  case class Leather(magic: Int) extends Armor(20, 15, 13, magic) {
    override val name = "Leather"
    override val armorType: ArmorType  = if(magic > 0) NoneOrMagicLeather else LeatherOrMagicMetal
  }

  case class Studded(magic: Int) extends Armor(30, 25, 14, magic) {
    override val name = "Studded Leather"
    override val armorType: ArmorType  = if(magic > 0) NoneOrMagicLeather else LeatherOrMagicMetal
  }

  case class RingMail(magic: Int) extends Armor(25, 30, 14, magic) {
    override val name = "Ring Mail"
  }

  case class Brigandine(magic: Int) extends Armor(80, 30, 15, magic) {
    override val name = "Brigandine"
  }

  case class ChainMail(magic: Int) extends Armor(60, 40, 15, magic) {
    override val name = "Chain Mail"
  }

  case class ScaleMail(magic: Int) extends Armor(80, 55, 16, magic) {
    override val name = "Scale Mail"
  }

  case class SplintMail(magic: Int) extends Armor(100, 45, 16, magic) {
    override val name = "Split Mail"
  }

  case class BandedMail(magic: Int) extends Armor(200, 35, 16, magic) {
    override val name = "Banded Mail"
  }

  case class PlateMail(magic: Int) extends Armor(300, 50, 17, magic) {
    override val name = "Plate Mail"
  }

  case class FieldPlateMail(magic: Int) extends Armor(500, 70, 18, magic) {
    override val name = "Field Plate Mail"
  }

  case class FullPlateMail(magic: Int) extends Armor(1500, 80, 19, magic){
    override val name = "Full Plate Mail"
  }


  def getArmor(characterClass: CharacterClass, level: Int): Armor = {
    val magic: Int = Roller.randomMagicWeaponBonus(level)
    val roll = Roller.randomInt(100)

    characterClass match {
      case m if m.isMagicUser || m.isMonk => NoArmor
      case t if t.isThief =>
        if(roll < 10)
          Padded
        else if (roll < 20)
          Hide
        else if (roll < 80)
          Leather(magic)
        else
          Studded(magic)
      case _ =>
        if(roll < 10)
          Studded(magic)
        else if (roll < 20)
          RingMail(magic)
        else if (roll < 30)
          Brigandine(magic)
        else if (roll < 40)
          ChainMail(magic)
        else if (roll < 50)
          ScaleMail(magic)
        else if (roll < 60)
          SplintMail(magic)
        else if (roll < 70)
          BandedMail(magic)
        else if (roll < 80)
          PlateMail(magic)
        else if (roll < 90)
          FieldPlateMail(magic)
        else
          FullPlateMail(magic)
    }
  }
}
