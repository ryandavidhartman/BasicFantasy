package controllers

import basic.fantasy.backgrounds.{CharacterAlignments, PersonalityGenerator}
import basic.fantasy.backgrounds.CharacterAlignments.stringToCharacterAlignment
import basic.fantasy.characterclass.CharacterClasses.stringToCharacterClass
import basic.fantasy.characterclass.{CharacterClasses, SpellsPerLevel}
import models.Spell
import play.api.mvc.{Action, AnyContent, MessagesAbstractController, MessagesControllerComponents, MessagesRequest}
import repositories.SpellRepository

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class CharacterSheetController @Inject()(
                                          implicit executionContext: ExecutionContext,
                                          val spellRepository: SpellRepository,
                                          val spellsPerLevel: SpellsPerLevel,
                                          val personalityGenerator: PersonalityGenerator,
                                          controllerComponents: MessagesControllerComponents)
  extends MessagesAbstractController(controllerComponents) {

  private val baseSpellURL = "http://localhost:9000/spells"
  def getCharacter(characterClass: Option[String], race: Option[String], alignment: Option[String], level: Option[Int]): Action[AnyContent] = Action.async { implicit request: MessagesRequest[AnyContent] =>
    val lvl = level.getOrElse(1)
    val cc = stringToCharacterClass(characterClass.getOrElse("fighter"))
    val align = stringToCharacterAlignment(alignment.getOrElse("lawful"))

    for {
      knownSpells <- spellsPerLevel.getSpells(cc, lvl, align)
      personality <- personalityGenerator.getPersonality(align)
    } yield {
      Ok(views.html.characterSheet(cc, knownSpells, baseSpellURL, personality))
    }

  }

}
