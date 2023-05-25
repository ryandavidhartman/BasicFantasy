package basic.fantasy.characterclass

import basic.fantasy.Roller
import basic.fantasy.backgrounds.Spells._
import basic.fantasy.backgrounds.CharacterAlignments.{Chaotic, CharacterAlignment, Lawful, Neutral}
import basic.fantasy.characterclass.CharacterClasses.CharacterClass

object SpellsPerLevel {

  val clericSpellsPerLevel: Map[Int, Seq[Int]] = Map(
    //        1  2  3  4  5  6
    1 -> Seq(0, 0, 0, 0, 0, 0),
    2 -> Seq(1, 0, 0, 0, 0, 0),
    3 -> Seq(2, 0, 0, 0, 0, 0),
    4 -> Seq(2, 1, 0, 0, 0, 0),
    5 -> Seq(2, 2, 0, 0, 0, 0),
    6 -> Seq(2, 2, 1, 0, 0, 0),
    7 -> Seq(3, 2, 2, 0, 0, 0),
    8 -> Seq(3, 2, 2, 1, 0, 0),
    9 -> Seq(3, 3, 2, 2, 0, 0),
    10 -> Seq(3, 3, 2, 2, 1, 0),
    11 -> Seq(4, 3, 3, 2, 2, 0),
    12 -> Seq(4, 4, 3, 2, 2, 1),
    13 -> Seq(4, 4, 3, 3, 2, 2),
    14 -> Seq(4, 4, 4, 3, 2, 2),
    15 -> Seq(4, 4, 4, 3, 3, 2),
    16 -> Seq(5, 4, 4, 3, 3, 2),
    17 -> Seq(5, 5, 4, 4, 3, 2),
    18 -> Seq(5, 5, 4, 4, 3, 3),
    19 -> Seq(6, 5, 4, 4, 3, 3),
    20 -> Seq(6, 5, 5, 4, 3, 3),
  )



  val magicUserSpellsPerLevel: Map[Int, Seq[Int]] = Map(
    //        1  2  3  4  5  6
    1 -> Seq(1, 0, 0, 0, 0, 0),
    2 -> Seq(2, 0, 0, 0, 0, 0),
    3 -> Seq(2, 1, 0, 0, 0, 0),
    4 -> Seq(2, 2, 0, 0, 0, 0),
    5 -> Seq(2, 2, 1, 0, 0, 0),
    6 -> Seq(3, 2, 2, 0, 0, 0),
    7 -> Seq(3, 2, 2, 1, 0, 0),
    8 -> Seq(3, 3, 2, 2, 0, 0),
    9 -> Seq(3, 3, 2, 2, 1, 0),
    10 -> Seq(4, 3, 3, 2, 2, 0),
    11 -> Seq(4, 4, 3, 2, 2, 1),
    12 -> Seq(4, 4, 3, 3, 2, 2),
    13 -> Seq(4, 4, 4, 3, 2, 2),
    14 -> Seq(4, 4, 4, 3, 2, 2),
    15 -> Seq(5, 4, 4, 3, 3, 2),
    16 -> Seq(5, 5, 4, 5, 3, 2),
    17 -> Seq(5, 5, 4, 4, 3, 3),
    18 -> Seq(6, 5, 4, 4, 3, 3),
    19 -> Seq(6, 5, 5, 4, 3, 3),
    20 -> Seq(6, 5, 5, 4, 4, 3),
  )

  def getRandomSpells(count: Int, spells: Seq[Spell]): String = {
    if (count > 0) {
      try {
        (0 until count).map { _ =>
          val random = Roller.randomInt(spells.length)
          spells(random).name
        }.mkString(",")
      } catch {
        case e: Throwable => println(e.getMessage); e.getMessage
      }
    } else
      "0"
  }


  def getSpells(characterClass: CharacterClass, characterLevel: Int, characterAlignment: CharacterAlignment): Seq[String] = {
    if (characterClass.isMagicUser) {
      val spellsForAlignment = characterAlignment match {
        case Lawful => GoodMagicUserSpells
        case Neutral => KnownMagicUserSpells
        case Chaotic => EvilMagicUserSpells
      }
      (1 to 6).map(i => getRandomSpells(magicUserSpellsPerLevel(characterLevel)(i-1), spellsForAlignment(i)))
    } else if (characterClass.isCleric) {
      val spellsForAlignment = characterAlignment match {
        case Lawful => GoodClericSpells
        case Neutral => KnownClericSpells
        case Chaotic => EvilClericSpells
      }
      (1 to 6).map(i =>  getRandomSpells(clericSpellsPerLevel(characterLevel)(i-1),  spellsForAlignment(i)))
    } else {
      Seq("0", "0", "0", "0", "0", "0")
    }
  }

}
