package basic.fantasy.equipment

import basic.fantasy.Roller
import basic.fantasy.backgrounds.Races.{Dwarf, Elf, HalfElf, HalfOrc, Halfling, Human, Race}
import basic.fantasy.characterclass.CharacterClasses._

object MeleeWeapons {

  sealed trait WeaponSize
  case object Small extends WeaponSize
  case object Medium extends WeaponSize
  case object Large extends WeaponSize

  sealed abstract class MeleeWeapon(
   val baseCost: Int,
   val size: WeaponSize,
   val weight: Int,
   val damage: String,
   val magicBonus: Int = 0
 ) {
    def name: String

    def cost: Int = if(magicBonus > 1)
      baseCost + (1000 * Math.pow(4,magicBonus-1)).toInt
    else
      baseCost

    override def toString: String = {
      val magicString = if(magicBonus > 0) s" (+$magicBonus)" else ""
      val magicDamage = if(magicBonus > 0) s" +$magicBonus" else ""
      s"$name$magicString, dmg: $damage$magicDamage, weight: $weight"
    }
  }

  case class Unarmed(characterClass: CharacterClass, level: Int)
    extends MeleeWeapon(0, Small, 0,"") {
    val name = "Unarmed"
    override def toString: String = {
      val damage = "punch 1d3, kick 1d4 (-2 attack penalty)"
//        if(characterClass.isMonk)
//          if(level < 4) "1d4"
//          else if(level < 8) "1d6"
//          else if(level < 12) "1d8"
//          else if(level < 16) "1d10"
//          else if(level < 20) "2d6"
//          else "2d8"
//        else "punch 1d3, kick 1d4 (-2 attack penalty)"

      s"Unarmed Attack: $damage"

    }
  }

  //Axes
  case class HandAxe(magic: Int) extends MeleeWeapon(4, Small, 5, "1d6", magic) {
    val name: String = "Hand Axe"
  }

  case class BattleAxe(magic: Int) extends MeleeWeapon(7, Medium, 7, "1d8", magic) {
    val name: String = "Battle Axe"
  }

  case class GreatAxe(magic: Int) extends MeleeWeapon(14, Large, 15, "1d10", magic) {
    val name: String = "Great Axe"
  }

  case class MilitaryPick(magic: Int) extends MeleeWeapon(6, Small, 4, "1d6", magic) {
    val name: String = "Military Pick"
  }

  case class FootmansPick(magic: Int) extends MeleeWeapon(8, Medium, 6, "1d8", magic) {
    val name: String = "Footman's Pick"
  }

  // Daggers
  case class Dagger(magic: Int) extends MeleeWeapon(2, Small, 1, "1d4", magic) {
    val name: String = "Dagger"
  }

  case class DefendingDagger(magic: Int) extends MeleeWeapon(7, Small, 2, "1d4", magic) {
    val name: String = "Defending Dagger"

    override def toString: String = {
      val magicString = if(magicBonus > 0) s" (+$magicBonus)" else ""
      val magicDamage = if(magicBonus > 0) s" +$magicBonus" else ""
      s"Melee: $name$magicString, dmg: $damage$magicDamage OR +1 to melee AC, weight: $weight"
    }
  }

  case class SilverDagger(magic: Int) extends MeleeWeapon(25, Small, 1, "1d4", magic) {
    val name: String = "Silver Dagger"
  }

  // Swords
  case class ShortSword(magic: Int) extends MeleeWeapon(6, Small, 3, "1d6", magic) {
    val name: String = "Short Sword"
  }

  case class Cutlass(magic: Int) extends MeleeWeapon(6, Small, 3, "1d6", magic) {
    val name: String = "Cutlass"
  }

  case class LongSword(magic: Int) extends MeleeWeapon(10, Medium, 4, "1d8", magic) {
    val name: String = "Long Sword"
  }

  case class Scimitar(magic: Int) extends MeleeWeapon(10, Medium, 4, "1d8", magic) {
    val name: String = "Scimitar"
  }

  case class TwoHandedSword(magic: Int) extends MeleeWeapon(18, Large, 10, "1d10", magic) {
    val name: String = "Two-Handed Sword"
  }

  // Hammer and Maces
  case class WarHammer(magic: Int) extends MeleeWeapon(4, Small, 3, "1d6", magic) {
    val name: String = "War Hammer"
  }

  case class LightMace(magic: Int) extends MeleeWeapon(5, Small, 5, "1d6", magic) {
    val name: String = "Light Mace"
  }

  case class Mace(magic: Int) extends MeleeWeapon(6, Medium, 10, "1d8", magic) {
    val name: String = "Mace"
  }

  case class Morningstar(magic: Int) extends MeleeWeapon(7, Medium, 7, "1d8", magic) {
    val name: String = "Morningstar"
  }

  case class Maul(magic: Int) extends MeleeWeapon(10, Large, 16, "1d10", magic) {
    val name: String = "Maul"
  }

  case class GreatMace(magic: Int) extends MeleeWeapon(10, Large, 16, "1d10", magic) {
    val name: String = "Great Mace"
  }


  // Spears and Pole Arms

  case class Spear(magic: Int) extends MeleeWeapon(5, Medium, 5, "1d6 or 1d8 two handed") {
    val name: String = "Spear"
  }

  case class Trident(magic: Int) extends MeleeWeapon(6, Medium, 5, "1d6 or 1d8 two handed") {
    val name: String = "Trident"
  }

  case class QuarterStaff(magic: Int) extends MeleeWeapon(2, Large, 10, "1d8") {
    val name: String = "Quarter Staff"
  }

  case class PoleArm(magic: Int) extends MeleeWeapon(9, Large, 15, "1d10") {
    val name: String = "Pole Arm"
  }

  // Chain and Flail
  case class Chain(magic: Int) extends MeleeWeapon(9, Medium, 3, "1d4") {
    val name: String = "Chain"
  }

  case class Flail(magic: Int) extends MeleeWeapon(8, Medium, 6, "1d8") {
    val name: String = "Flail"
  }

  case class GreatFlail(magic: Int) extends MeleeWeapon(12, Large, 15, "1d10") {
    val name: String = "Great Flail"
  }

  case class Whip(magic: Int) extends MeleeWeapon(3, Medium, 2, "1d3") {
    val name: String = "Whip"
  }

  // Other Weapons
  case class Sap(magic: Int) extends MeleeWeapon(1, Small, 1, "1d4") {
    val name = "Sap"
  }

  case class Blackjack(magic: Int) extends MeleeWeapon(1, Small, 1, "1d4") {
    val name = "Blackjack"
  }

  case class Hook(magic: Int) extends MeleeWeapon(1, Small, 1, "1d4") {
    val name = "Hook"
  }

  case class Sickle(magic: Int) extends MeleeWeapon(2, Small, 2, "1d6") {
    val name = "Sickle"
  }

  case class Club(magic: Int) extends MeleeWeapon(1, Medium, 2, "1d4") {
    val name = "Club"
  }

  case class Cudgel(magic: Int) extends MeleeWeapon(1, Medium, 2, "1d4") {
    val name = "Club"
  }

  case class WalkingStaff(magic: Int) extends MeleeWeapon(1, Medium, 1, "1d4") {
    val name = "Walking Staff"
  }

  case class Scythe(magic: Int) extends MeleeWeapon(7, Large, 10, "1d8") {
    val name = "Scythe"
  }

  case class GreatClub(magic: Int) extends MeleeWeapon(3, Large, 8, "1d8") {
    val name = "Great Club"
  }


  def getAxe(level: Int, maxSize: WeaponSize): MeleeWeapon = {
    val magic: Int = Roller.randomMagicWeaponBonus(level)
    val roll = Roller.randomInt(100)

    maxSize match {
      case Small =>
        if(roll < 50)
          HandAxe(magic)
        else
          MilitaryPick(magic)
      case Medium =>
        if(roll < 10)
          HandAxe(magic)
        else if(roll < 80)
          BattleAxe(magic)
        else if(roll < 90)
          MilitaryPick(magic)
        else
          FootmansPick(magic)
      case Large =>
        if(roll < 10)
          HandAxe(magic)
        else if(roll < 55)
          BattleAxe(magic)
        else if(roll < 90 )
          GreatAxe(magic)
        else if(roll < 95)
          MilitaryPick(magic)
        else
          FootmansPick(magic)
    }
  }
  def getDagger(level: Int, offHand: Boolean): MeleeWeapon = {
    val magic: Int = Roller.randomMagicWeaponBonus(level)
    val roll = Roller.randomInt(100)

    if (offHand) {
      if (roll < 50)
        Dagger(magic)
      else if (roll < 75)
        DefendingDagger(magic)
      else
        SilverDagger(magic)
    } else {
      if (roll < 75)
        Dagger(magic)
      else
        SilverDagger(magic)
    }
  }
  def getChainOrFlail(level: Int, maxSize: WeaponSize): MeleeWeapon = {
    val magic: Int = Roller.randomMagicWeaponBonus(level)
    val roll = Roller.randomInt(100)

    maxSize match {
      case Small => // There aren't any
        if(roll < 50)
          WarHammer(magic)
        else
          LightMace(magic)
      case Medium =>
        if(roll < 10)
          Chain(magic)
        else if(roll <90)
          Flail(magic)
        else
          Whip(magic)
      case Large =>
        if(roll < 10)
          Chain(magic)
        else if(roll <50)
          Flail(magic)
        else if(roll < 95)
          GreatFlail(magic)
        else
          Whip(magic)
    }
  }
  def getHammerOrMace(level: Int, maxSize: WeaponSize): MeleeWeapon = {
    val magic: Int = Roller.randomMagicWeaponBonus(level)
    val roll = Roller.randomInt(100)

    maxSize match {
      case Small =>
        if(roll < 50)
          WarHammer(magic)
        else
          LightMace(magic)
      case Medium =>
        if(roll < 10)
          HandAxe(magic)
        else if(roll < 80)
          BattleAxe(magic)
        else if(roll < 90)
          MilitaryPick(magic)
        else
          FootmansPick(magic)
      case Large =>
        if(roll < 10)
          HandAxe(magic)
        else if(roll < 55)
          BattleAxe(magic)
        else if(roll < 90 )
          GreatAxe(magic)
        else if(roll < 95)
          MilitaryPick(magic)
        else
          FootmansPick(magic)
    }
  }
  def getMageWeapons(level: Int, maxSize: WeaponSize): MeleeWeapon = {
    val magic: Int = Roller.randomMagicWeaponBonus(level)
    val roll = Roller.randomInt(100)

    maxSize match {
      case Small => Dagger(magic)
      case _ =>
        if (roll < 40)
          Dagger(magic)
        else if (roll < 80)
          WalkingStaff(magic)
        else
          Cudgel(magic)
    }
  }
  def getSpearOrPoleArm(level: Int, maxSize: WeaponSize): MeleeWeapon = {
    val magic: Int = Roller.randomMagicWeaponBonus(level)
    val roll = Roller.randomInt(100)

    maxSize match {
      case Small => // There aren't any
        if(roll < 50)
          ShortSword(magic)
        else
          Dagger(magic)
      case Medium =>
        if(roll < 75)
          Spear(magic)
        else
          Trident(magic)
      case Large =>
        if(roll < 45)
          Spear(magic)
        else if(roll < 50)
          Trident(magic)
        else if(roll < 75 )
          QuarterStaff(magic)
        else
          PoleArm(magic)
    }
  }
  def getSword(level: Int, maxSize: WeaponSize, offHand: Boolean): MeleeWeapon = {

    val magic: Int = Roller.randomMagicWeaponBonus(level)
    val roll = Roller.randomInt(100)

    val modifiedMaxSize = if(offHand) Small else maxSize

    modifiedMaxSize match {
      case Small =>
        if(roll < 70)
          ShortSword(magic)
        else
          Cutlass(magic)
      case Medium =>
        if(roll < 40)
          ShortSword(magic)
        else if(roll < 45)
          Cutlass(magic)
        else if(roll < 95)
          LongSword(magic)
        else
          Scimitar(magic)
      case Large =>
        if(roll < 15)
          ShortSword(magic)
        else if(roll < 20)
          Cutlass(magic)
        else if(roll < 70)
          LongSword(magic)
        else if(roll < 75)
          Scimitar(magic)
        else
          TwoHandedSword(magic)
    }
  }
  def getOtherWeapons(level: Int, maxSize: WeaponSize): MeleeWeapon = {
    val magic: Int = Roller.randomMagicWeaponBonus(level)
    val roll = Roller.randomInt(100)

    maxSize match {
      case Small => // There aren't any
        if(roll < 25)
          Sap(magic)
        else if(roll < 50)
          Blackjack(magic)
        else if(roll < 70)
          Hook(magic)
        else
          Sickle(magic)
      case Medium =>
        if(roll < 33)
          Club(magic)
        else if(roll < 66)
          Cudgel(magic)
        else
          WalkingStaff(magic)
      case Large =>
        if(roll < 20)
          Club(magic)
        else if(roll < 40)
          Cudgel(magic)
        else if(roll < 60)
          Scythe(magic)
        else if(roll < 80)
          GreatClub(magic)
        else
          WalkingStaff(magic)
    }
  }

  def getMeleeWeapon(characterClass: CharacterClass, level: Int, race: Race, hasShield: Boolean): MeleeWeapon = {

    val maxSize = race match {
      case Human => if (hasShield) Medium else Large
      case Elf => if (hasShield) Medium else Large
      case Dwarf => if (hasShield) Small else Medium
      case Halfling => if (hasShield) Small else Medium
      case HalfElf => if (hasShield) Medium else Large
      case HalfOrc => if (hasShield) Medium else Large
    }

    val roll = Roller.randomInt(100)

    characterClass match {
      case Cleric => getHammerOrMace(level, maxSize)
      case Fighter =>
        if(roll < 10)
          getAxe(level, maxSize)
        else if(roll < 20)
          getChainOrFlail(level,maxSize)
        else if(roll < 30)
          getHammerOrMace(level, maxSize)
        else if(roll < 40)
          getSpearOrPoleArm(level, maxSize)
        else if(roll < 90)
          getSword(level, maxSize, offHand = false)
        else
          getOtherWeapons(level, maxSize)
      case FighterMagicUser =>
        if(roll < 90)
          getSword(level, maxSize, offHand = false)
        else
          getDagger(level, offHand = false)
      case MagicUser => getMageWeapons(level, maxSize)
      case MagicUserThief =>
        if(roll < 50)
          getSword(level, maxSize, offHand = false)
        else
          getDagger(level, offHand = false)
      //case Monk =>
      //  Unarmed(characterClass, level)
      case Thief => getAxe(level, maxSize)
        if(roll < 70)
          getSword(level, maxSize, offHand = false)
        else
          getDagger(level, offHand = false)
    }
  }

  def getSecondaryWeapon(
    characterClass: CharacterClass,
    race: Race,
    level: Int,
    mainWeapon: MeleeWeapon,
    hasShield: Boolean): MeleeWeapon = {

    val maxSize = race match {
      case Dwarf => Medium
      case Halfling => Medium
      case _ => Large
    }

    val roll = Roller.randomInt(100)

    if(characterClass.isFighter && mainWeapon.size != maxSize &&  hasShield == false)
      if(roll < 40)
        getDagger(level, offHand = true)
      else if(roll < 60)
        getSword(level, Small, offHand = true)
//    if(characterClass.isMonk)
//      if(roll < 20)
//        getChainOrFlail(level,maxSize)
//      else if(roll < 40)
//        getHammerOrMace(level, maxSize)
//      else if(roll < 60)
//        getSpearOrPoleArm(level, maxSize)
//      else if(roll < 80)
//        getSword(level, maxSize, offHand = false)
      else
        getOtherWeapons(level, maxSize)
    else
      Unarmed(characterClass, level)

  }

}
