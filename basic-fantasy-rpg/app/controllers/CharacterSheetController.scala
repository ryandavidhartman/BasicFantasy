package controllers

import basic.fantasy.backgrounds.CharacterAlignments
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
                                 controllerComponents: MessagesControllerComponents)
  extends MessagesAbstractController(controllerComponents) {

  def getCharacter(level: Option[Int]): Action[AnyContent] = Action.async { implicit request: MessagesRequest[AnyContent] =>
    val knownSpells: Future[Map[Int, Seq[Spell]]] = spellsPerLevel.getSpells(CharacterClasses.MagicUser, level.getOrElse(1), CharacterAlignments.Lawful)
    knownSpells.map(spells => Ok(views.html.characterSheet(spells(1).sortBy(_.name))))

  }

  }
