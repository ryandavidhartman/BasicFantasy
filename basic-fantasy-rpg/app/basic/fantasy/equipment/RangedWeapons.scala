package basic.fantasy.equipment

import basic.fantasy.Roller
import basic.fantasy.backgrounds.Races.{Dwarf, Elf, HalfElf, HalfOrc, Halfling, Human, Race}
import basic.fantasy.characterclass.CharacterClasses.{CharacterClass, Cleric, Fighter, FighterMagicUser, MagicUser, MagicUserThief, Monk, Thief}
import basic.fantasy.equipment.Weapons.{Large, Medium, Small, WeaponSize}

object RangedWeapons {

  sealed trait Ammo {
    def name: String
    def magicBonus: Int
    def count: Int

    def ammoString: String = {
      if(magicBonus > 0)
        s", ammo: $name (+$magicBonus) (x$count)"
      else
        s", ammo: $name (x$count)"
    }
  }

  case class Arrow(count:Int, magicBonus:Int = 0) extends Ammo{
    val name = "Arrow"
  }

  case class SilverArrow(count:Int, magicBonus:Int = 0) extends Ammo{
    val name = "Silver Arrow"
  }

  case class Quarrel(count:Int, magicBonus:Int = 0) extends Ammo{
    val name = "Quarrel"
  }

  case class SilverQuarrel(count:Int, magicBonus:Int = 0) extends Ammo{
    val name = "Silver Quarrel"
  }

  case class Bullet(count:Int, magicBonus:Int = 0) extends Ammo{
    val name = "Bullet"
  }

  case class SilverBullet(count:Int, magicBonus:Int = 0) extends Ammo{
    val name = "Silver Bullet"
  }

  case class Stone(count:Int, magicBonus:Int = 0) extends Ammo{
    val name = "Stone"
  }

  case class DartAmmo(count:Int, magicBonus:Int = 0) extends Ammo{
    val name = "Dart"
  }

  sealed trait RangedWeapon{
    def name: String
    def size: WeaponSize
    def baseCost: Int
    def weight: Int
    def damage: String
    def ammo: Option[Ammo] = None
    def magicBonus: Int = 0

    def cost: Int = if(magicBonus > 1)
      baseCost + (1000 * Math.pow(4,magicBonus-1)).toInt
    else
      baseCost

    override def toString: String = {
      val magicString = if(magicBonus > 0) s" (+$magicBonus)" else ""
      val magicDamage = if(magicBonus > 0) s" +$magicBonus" else ""
      val ammoString = ammo.map(_.ammoString).getOrElse("")
      s"$name$magicString, dmg: $damage$magicDamage$ammoString, weight: $weight"
    }
  }

  case class ShortBow(override val magicBonus: Int = 0, override val ammo: Option[Ammo] = None) extends RangedWeapon{
    val name: String = "Short Bow"
    val size: WeaponSize = Medium
    val baseCost: Int = 25
    val weight: Int = 2
    val damage: String = "1d6"
  }

  case class LongBow(override val magicBonus: Int = 0, override val ammo: Option[Ammo] = None) extends RangedWeapon{
    val name: String = "Long Bow"
    val size: WeaponSize = Large
    val baseCost: Int = 60
    val weight: Int = 3
    val damage: String = "1d8"
  }

  case class LightCrossbow(override val magicBonus: Int = 0, override val ammo: Option[Ammo] = None) extends RangedWeapon{
    val name: String = "Light Crossbow"
    val size: WeaponSize = Medium
    val baseCost: Int = 30
    val weight: Int = 7
    val damage: String = "1d6"
  }

  case class HeavyCrossbow(override val magicBonus: Int = 0, override val ammo: Option[Ammo] = None) extends RangedWeapon{
    val name: String = "Heavy Crossbow"
    val size: WeaponSize = Large
    val baseCost: Int = 50
    val weight: Int = 14
    val damage: String = "1d8"
  }

  case class HandCrossbow(override val magicBonus: Int = 0, override val ammo: Option[Ammo] = None) extends RangedWeapon{
    val name: String = "Hand Crossbow"
    val size: WeaponSize = Small
    val baseCost: Int = 150
    val weight: Int = 3
    val damage: String = "1d3"
  }

  case class Sling(override val magicBonus: Int = 0, override val ammo: Option[Ammo] = None) extends RangedWeapon{
    val name: String = "Sling"
    val size: WeaponSize = Small
    val baseCost: Int = 1
    val weight: Int = 0
    val damage: String = "1d4"
  }

  case class Blowgun(override val magicBonus: Int = 0, override val ammo: Option[Ammo] = None) extends RangedWeapon{
    val name: String = "Blowgun"
    val size: WeaponSize = Medium
    val baseCost: Int = 2
    val weight: Int = 1
    val damage: String = "1d3"
  }

  case class Bola(override val magicBonus: Int = 0) extends RangedWeapon{
    val name: String = "Bola"
    val size: WeaponSize = Small
    val baseCost: Int = 2
    val weight: Int = 2
    val damage: String = "1d3"
  }

  case class Dart(count: Int, override val magicBonus: Int = 0) extends RangedWeapon{
    val name: String = "Dart"
    val size: WeaponSize = Small
    val baseCost: Int = 1
    val weight: Int = 0
    val damage: String = "1d3"

    override def toString: String = {
      val magicString = if(magicBonus > 0) s" (+$magicBonus)" else ""
      val magicDamage = if(magicBonus > 0) s" +$magicBonus" else ""
      s"$name$magicString, dmg: $damage$magicDamage, count: $count, weight: $weight"
    }
  }

  case class ThrowingKnife(override val magicBonus: Int = 0) extends RangedWeapon{
    val name: String = "Throwing Knife"
    val size: WeaponSize = Small
    val baseCost: Int = 2
    val weight: Int = 0
    val damage: String = "1d3"
    val count: Int = {
      Roller.randomInt(4)
    }

    override def toString: String = {
      val magicString = if(magicBonus > 0) s" (+$magicBonus)" else ""
      val magicDamage = if(magicBonus > 0) s" +$magicBonus" else ""
      s"$name$magicString, dmg: $damage$magicDamage, count: $count, weight: $weight"
    }
  }

  case class Javelin(override val magicBonus: Int = 0) extends RangedWeapon{
    val name: String = "Javelin"
    val size: WeaponSize = Medium
    val baseCost: Int = 2
    val weight: Int = 2
    val damage: String = "1d4"
  }

  def getSling(level: Int): RangedWeapon = {
    val weaponMagicBonus: Int = Roller.randomMagicWeaponBonus(level)
    val ammoMagicBonus: Int = Roller.randomMagicAmmoBonus(level)
    val ammoCount = Roller.randomInt(20)+1
    val ammo = Bullet(ammoCount, ammoMagicBonus)
    Sling(weaponMagicBonus, Some(ammo))
  }

  def getBow(level: Int, maxSize: WeaponSize): RangedWeapon = {
    val weaponMagicBonus: Int = Roller.randomMagicWeaponBonus(level)
    val ammoMagicBonus: Int = Roller.randomMagicAmmoBonus(level)
    val ammoCount = Roller.randomInt(30)+1
    val ammo = Arrow(ammoCount, ammoMagicBonus)

    val roll = Roller.randomInt(100)

    if(maxSize == Large && roll > 50)
      LongBow(weaponMagicBonus, Some(ammo))
    else
      ShortBow(weaponMagicBonus, Some(ammo))
  }

  def getCrossBow(level: Int, maxSize: WeaponSize): RangedWeapon = {
    val weaponMagicBonus: Int = Roller.randomMagicWeaponBonus(level)
    val ammoMagicBonus: Int = Roller.randomMagicAmmoBonus(level)
    val ammoCount = Roller.randomInt(30)+1
    val ammo = Some(Quarrel(ammoCount, ammoMagicBonus))

    val roll = Roller.randomInt(100)

    if(maxSize == Large) {
      if(roll < 10)
        HandCrossbow(weaponMagicBonus, ammo)
      else if(roll < 55)
        LightCrossbow(weaponMagicBonus, ammo)
      else
        HeavyCrossbow(weaponMagicBonus, ammo)
    } else if(maxSize == Medium) {
      if(roll < 20)
        HandCrossbow(weaponMagicBonus, ammo)
      else
        LightCrossbow(weaponMagicBonus, ammo)
    }  else
      HandCrossbow(weaponMagicBonus, ammo)
  }

  def getMagicUserRangedWeapon(level: Int): RangedWeapon = {
    val weaponMagicBonus: Int = Roller.randomMagicWeaponBonus(level)
    val ammoMagicBonus: Int = Roller.randomMagicAmmoBonus(level)
    val ammoCount = Roller.randomInt(20)+1
    val ammo = DartAmmo(ammoCount, ammoMagicBonus)

    val roll = Roller.randomInt(100)

    if(roll < 30)
      Dart(ammoCount, ammoMagicBonus)
    else if(roll < 60)
      getSling(level)
    else if(roll < 80)
      Blowgun(weaponMagicBonus, Some(ammo))
    else
      ThrowingKnife(weaponMagicBonus)

  }

  def getRandomRangedWeapon(level: Int, mazSize: WeaponSize): RangedWeapon = {
    val weaponMagicBonus: Int = Roller.randomMagicWeaponBonus(level)
    val ammoMagicBonus: Int = Roller.randomMagicAmmoBonus(level)
    val ammoCount = Roller.randomInt(20)+1
    val roll = Roller.randomInt(100)

    if(mazSize == Small) {
      if(roll < 33)
        Bola(weaponMagicBonus)
      else if(roll < 66)
        Dart(ammoCount, ammoMagicBonus)
      else
        ThrowingKnife(ammoMagicBonus)
    } else {
      if(roll < 20)
        Bola(weaponMagicBonus)
      else if(roll < 40)
        Dart(ammoCount, ammoMagicBonus)
      else if(roll < 60)
        ThrowingKnife(ammoMagicBonus)
      else if(roll < 80)
        Blowgun(weaponMagicBonus, Some(DartAmmo(ammoCount, ammoMagicBonus)))
      else
        Javelin(ammoMagicBonus)
    }
  }

  def getFighterTypeRangedWeapon(level: Int, maxSize: WeaponSize): RangedWeapon = {
    val roll = Roller.randomInt(100)

    if(roll < 5)
      getSling(level)
    else if(roll < 30)
      getRandomRangedWeapon(level, maxSize)
    else if(roll < 65)
      getCrossBow(level, maxSize)
    else
      getBow(level, maxSize)
  }

  def getRangedWeapon(characterClass: CharacterClass, level: Int, race: Race): RangedWeapon = {
    val maxSize = race match {
      case Human => Large
      case Elf => Large
      case Dwarf => Medium
      case Halfling => Medium
      case HalfElf => Large
      case HalfOrc => Large
    }

    characterClass match {
      case Cleric => getSling(level)
      case Fighter => getFighterTypeRangedWeapon(level, maxSize)
      case FighterMagicUser =>getFighterTypeRangedWeapon(level, maxSize)
      case MagicUser => getMagicUserRangedWeapon(level)
      case MagicUserThief => getFighterTypeRangedWeapon(level, maxSize)
      case Monk => getFighterTypeRangedWeapon(level, maxSize)
      case Thief => getFighterTypeRangedWeapon(level, maxSize)
    }

  }


}
