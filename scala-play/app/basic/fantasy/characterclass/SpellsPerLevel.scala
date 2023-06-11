package basic.fantasy.characterclass

import basic.fantasy.Roller
import basic.fantasy.backgrounds.CharacterAlignments.{Chaotic, CharacterAlignment, Lawful, Neutral}
import basic.fantasy.characterclass.CharacterClasses.{CharacterClass, Cleric, MagicUser}
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
    if(characterClass.isCleric) {
      val firstF = spellRepository.get(cleric = Some(1), alignment = alignment)
      val secondF = spellRepository.get(cleric = Some(2), alignment = alignment)
      val thirdF = spellRepository.get(cleric = Some(3), alignment = alignment)
      val fourthF = spellRepository.get(cleric = Some(4), alignment = alignment)
      val fifthF = spellRepository.get(cleric = Some(5), alignment = alignment)
      val sixthF = spellRepository.get(cleric = Some(6), alignment = alignment)

      for {
        first <- firstF
        second <- secondF
        third <- thirdF
        fourth <- fourthF
        fifth <- fifthF
        sixth <- sixthF
      } yield {
        Map(1 -> first, 2 -> second, 3 -> third, 4 -> fourth, 5 -> fifth, 6 -> sixth)
      }


    } else if (characterClass.isMagicUser) {
      val firstF = spellRepository.get(magicUser = Some(1), alignment = alignment)
      val secondF = spellRepository.get(magicUser = Some(2), alignment = alignment)
      val thirdF = spellRepository.get(magicUser = Some(3), alignment = alignment)
      val fourthF = spellRepository.get(magicUser = Some(4), alignment = alignment)
      val fifthF = spellRepository.get(magicUser = Some(5), alignment = alignment)
      val sixthF = spellRepository.get(magicUser = Some(6), alignment = alignment)

      for {
        first <- firstF
        second <- secondF
        third <- thirdF
        fourth <- fourthF
        fifth <- fifthF
        sixth <- sixthF
      } yield {
        Map(1 -> first, 2 -> second, 3 -> third, 4 -> fourth, 5 -> fifth, 6 -> sixth)
      }
    }
    else
      Future.successful(Map.empty)
  }

  private lazy val EvilClericSpells: Future[Map[Int, Seq[Spell]]]     = getSpellsFromDb(Cleric, Some("dark"))
  private lazy val EvilMagicUserSpells: Future[Map[Int, Seq[Spell]]]  = getSpellsFromDb(MagicUser, Some("dark"))
  private lazy val GoodClericSpells: Future[Map[Int, Seq[Spell]]]     = getSpellsFromDb(Cleric, Some("light"))
  private lazy val GoodMagicUserSpells: Future[Map[Int, Seq[Spell]]]  = getSpellsFromDb(MagicUser, Some("light"))
  private lazy val KnownClericSpells: Future[Map[Int, Seq[Spell]]]    = getSpellsFromDb(Cleric, None)
  private lazy val KnownMagicUserSpells: Future[Map[Int, Seq[Spell]]] = getSpellsFromDb(MagicUser, None)

  def getSpells(characterClass: CharacterClass, characterLevel: Int, characterAlignment: CharacterAlignment): Future[Map[Int, Seq[Spell]]] = {
    if(characterClass.isSpellCaster)
      getForCastersSpells(characterClass, characterLevel, characterAlignment)
    else
      Future.successful(Map.empty[Int, Seq[Spell]])
  }

  private def getForCastersSpells(characterClass: CharacterClass, characterLevel: Int, characterAlignment: CharacterAlignment): Future[Map[Int, Seq[Spell]]] = {

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
        case (spellLevel: Int, possibleSpellsPerLevel: Seq[Spell]) => (spellLevel, getRandomSpells(spellsPerSpellLevel(spellLevel - 1), possibleSpellsPerLevel))
      }
    })
  }


}
