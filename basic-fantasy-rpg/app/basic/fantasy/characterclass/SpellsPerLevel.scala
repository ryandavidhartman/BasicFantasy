package basic.fantasy.characterclass

import basic.fantasy.Roller
import basic.fantasy.backgrounds.CharacterAlignments.{Chaotic, CharacterAlignment, Lawful, Neutral}
import basic.fantasy.characterclass.CharacterClasses.CharacterClass
import models.Spell
import repositories.SpellRepository

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class SpellsPerLevel @Inject()(
  implicit executionContext: ExecutionContext,
  val spellRepository: SpellRepository) {

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

  def getRandomSpells(numSpellsForLevelN: Int, levelNSpells: Seq[Spell]): Seq[Spell] = {
    if (numSpellsForLevelN > 0) {
      try {
        (0 until numSpellsForLevelN).map { _ =>
          val random = Roller.randomInt(levelNSpells.length)
          levelNSpells(random)
        }
      } catch {
        case e: Throwable => println(e.getMessage); Seq.empty
      }
    } else
      Seq.empty
  }

  def getSpellsFromDb(characterClass: CharacterClass, alignment: Option[String]): Future[Map[Int, Seq[Spell]]] = {
    if(characterClass.isCleric)
      spellRepository.get(Some("cleric"), alignment).map(_.groupBy(_.cleric.get))
    else if (characterClass.isMagicUser)
      spellRepository.get(Some("magicUser"), alignment).map(_.groupBy(_.magicUser.get))
    else
      Future.successful(Map.empty)
  }

  lazy val EvilClericSpells: Future[Map[Int, Seq[Spell]]] = getSpellsFromDb(CharacterClasses.Cleric, Some("dark"))
  lazy val EvilMagicUserSpells: Future[Map[Int, Seq[Spell]]] = getSpellsFromDb(CharacterClasses.MagicUser, Some("dark"))
  lazy val GoodClericSpells: Future[Map[Int, Seq[Spell]]] = getSpellsFromDb(CharacterClasses.Cleric, Some("light"))
  lazy val GoodMagicUserSpells: Future[Map[Int, Seq[Spell]]] = getSpellsFromDb(CharacterClasses.MagicUser, Some("light"))
  lazy val KnownClericSpells: Future[Map[Int, Seq[Spell]]] = getSpellsFromDb(CharacterClasses.Cleric, None)
  lazy val KnownMagicUserSpells: Future[Map[Int, Seq[Spell]]] = getSpellsFromDb(CharacterClasses.MagicUser, None)

  def getSpells(characterClass: CharacterClass, characterLevel: Int, characterAlignment: CharacterAlignment): Future[Map[Int, Seq[Spell]]] = {

    val (spellsForAlignmentF: Future[Map[Int, Seq[Spell]]], spellsPerCharacterLevel: Map[Int, Seq[Int]]) =
      if (characterClass.isMagicUser) {
        val spells = characterAlignment match {
          case Lawful => GoodMagicUserSpells
          case Neutral => KnownMagicUserSpells
          case Chaotic => EvilMagicUserSpells
        }
        (spells, magicUserSpellsPerLevel)
      } else if (characterClass.isCleric) {
        val spells = characterAlignment match {
          case Lawful => GoodClericSpells
          case Neutral => KnownClericSpells
          case Chaotic => EvilClericSpells
        }
        (spells, clericSpellsPerLevel)
      } else {
        (Future.successful(Map.empty), Seq.empty)
    }

    spellsForAlignmentF.map(spellsForAlignment => {
      val spellsPerSpellLevel: Seq[Int] = spellsPerCharacterLevel(characterLevel)
      spellsForAlignment.map {
        case (spellLevel: Int, possibleSpellsPerLevel: Seq[Spell]) => (spellLevel, getRandomSpells(spellsPerSpellLevel(spellLevel), possibleSpellsPerLevel))
      }
    })
  }


}
