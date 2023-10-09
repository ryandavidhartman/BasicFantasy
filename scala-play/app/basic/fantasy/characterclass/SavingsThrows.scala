package basic.fantasy.characterclass

import basic.fantasy.backgrounds.Races._
import basic.fantasy.characterclass.CharacterClasses._

case class SavingsThrows(deathRayOrPoison: Int,
                        magicWands: Int,
                        paralysisOrPetrify: Int,
                        breathWeapons: Int,
                        spells: Int)

object SavingsThrows {

  def multiClassSaves(s1: SavingsThrows, s2: SavingsThrows): SavingsThrows = {
    SavingsThrows(
      Math.min(s1.deathRayOrPoison, s2.deathRayOrPoison),
      Math.min(s1.magicWands, s2.magicWands),
      Math.min(s1.paralysisOrPetrify, s2.paralysisOrPetrify),
      Math.min(s1.breathWeapons, s2.breathWeapons),
      Math.min(s1.spells, s2.spells)
    )
  }

  def addRacialBonuses(s: SavingsThrows, b: SavingsThrowBonuses): SavingsThrows = {
    SavingsThrows(
      s.deathRayOrPoison - b.deathRayOrPoison,
      s.magicWands - b.magicWands,
      s.paralysisOrPetrify - b.paralysisOrPetrify,
      s.breathWeapons - b.breathWeapons,
      s.spells - b.spells
    )
  }

  val cleric: Map[Int, SavingsThrows] = Map(
    1  -> SavingsThrows(11, 12, 14, 16, 15),
    2  -> SavingsThrows(10, 11, 13, 15, 14),
    3  -> SavingsThrows( 9, 10, 13, 15, 14),
    4  -> SavingsThrows( 9, 10, 12, 14, 13),
    5  -> SavingsThrows( 8,  9, 12, 14, 13),
    6  -> SavingsThrows( 8,  9, 11, 13, 12),
    7  -> SavingsThrows( 7,  8, 11, 13, 12),
    8  -> SavingsThrows( 7,  8, 10, 12, 11),
    9  -> SavingsThrows( 6,  7, 10, 12, 11),
    10 -> SavingsThrows( 6,  7,  9, 11, 10),
    11 -> SavingsThrows( 5,  6,  9, 11, 10),
  )

  val magicUser: Map[Int, SavingsThrows] = Map(
    1  -> SavingsThrows(13, 14, 13, 16, 15),
    2  -> SavingsThrows(13, 14, 13, 15, 14),
    3  -> SavingsThrows(12, 13, 12, 15, 13),
    4  -> SavingsThrows(12, 12, 11, 14, 13),
    5  -> SavingsThrows(11, 11, 10, 14, 12),
    6  -> SavingsThrows(11, 10,  9, 13, 11),
    7  -> SavingsThrows(10, 10,  9, 13, 11),
    8  -> SavingsThrows(10,  9,  8, 12, 10),
    9  -> SavingsThrows( 9,  8,  7, 12,  9),
    10 -> SavingsThrows( 9,  7,  6, 11,  9),
    11 -> SavingsThrows( 8,  6,  5, 11,  8),
  )

  val fighter: Map[Int, SavingsThrows] = Map(
    1  -> SavingsThrows(12, 13, 14, 15, 17),
    2  -> SavingsThrows(11, 12, 14, 15, 16),
    3  -> SavingsThrows(11, 11, 13, 14, 15),
    4  -> SavingsThrows(10, 11, 12, 14, 15),
    5  -> SavingsThrows( 9, 10, 12, 13, 14),
    6  -> SavingsThrows( 9,  9, 11, 12, 13),
    7  -> SavingsThrows( 8,  9, 10, 12, 13),
    8  -> SavingsThrows( 7,  8, 10, 11, 12),
    9  -> SavingsThrows( 7,  7,  9, 10, 11),
    10 -> SavingsThrows( 6,  7,  8, 10, 11),
    11 -> SavingsThrows( 5,  6,  8,  9, 10),
  )

  val thief: Map[Int, SavingsThrows] = Map(
    1  -> SavingsThrows(13, 14, 13, 16, 15),
    2  -> SavingsThrows(12, 14, 12, 15, 14),
    3  -> SavingsThrows(11, 13, 12, 14, 13),
    4  -> SavingsThrows(11, 13, 11, 13, 13),
    5  -> SavingsThrows(10, 12, 11, 12, 12),
    6  -> SavingsThrows( 9, 12, 10, 11, 11),
    7  -> SavingsThrows( 9, 10, 10, 10, 11),
    8  -> SavingsThrows( 8, 10,  9,  9, 10),
    9  -> SavingsThrows( 7,  9,  9,  8,  9),
    10 -> SavingsThrows( 7,  9,  8,  7,  9),
    11 -> SavingsThrows( 6,  8,  8,  6,  8),
  )

  def getSavingsThrows(characterClass: CharacterClass, race: Race, level: Int): SavingsThrows = {
    val modLevel = level/2 +1

    characterClass match {
      case Fighter => addRacialBonuses(fighter(modLevel), race.savingsThrowBonuses)
      case MagicUser => addRacialBonuses(magicUser(modLevel), race.savingsThrowBonuses)
      case Thief => addRacialBonuses(thief(modLevel), race.savingsThrowBonuses)
      case Cleric => addRacialBonuses(cleric(modLevel), race.savingsThrowBonuses)
      case FighterMagicUser =>
        val f = fighter(modLevel)
        val m = magicUser(modLevel)
        addRacialBonuses(multiClassSaves(f,m), race.savingsThrowBonuses)
      case MagicUserThief =>
        val t = thief(modLevel)
        val m = magicUser(modLevel)
        addRacialBonuses(multiClassSaves(t,m), race.savingsThrowBonuses)
    }
  }
}