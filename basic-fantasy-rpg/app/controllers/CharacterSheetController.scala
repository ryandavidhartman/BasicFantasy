package controllers

import basic.fantasy.backgrounds.{CharacterAlignments, PersonalityGenerator}
import basic.fantasy.backgrounds.CharacterAlignments.stringToCharacterAlignment
import basic.fantasy.characterclass.CharacterClasses.stringToCharacterClass
import basic.fantasy.characterclass.{CharacterClasses, SpellsPerLevel}
import controllers.CharacterSheetForm.characterSheetForm
import models.{CharacterSheet, Spell}
import play.api.data.Form
import play.api.mvc.{Action, AnyContent, MessagesAbstractController, MessagesControllerComponents, MessagesRequest}
import repositories.{CharacterSheetRepository, SpellRepository}

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class CharacterSheetController @Inject()(
                                          implicit executionContext: ExecutionContext,
                                          val spellRepository: SpellRepository,
                                          val spellsPerLevel: SpellsPerLevel,
                                          val personalityGenerator: PersonalityGenerator,
                                          val characterSheetRepository: CharacterSheetRepository,
                                          controllerComponents: MessagesControllerComponents)
  extends MessagesAbstractController(controllerComponents) {

  private val createCharacterSheetCall = routes.CharacterSheetController.createCharacterSheet()
  private val getRandomNameCall = routes.CharacterSheetController.getRandomName()

  def createCharacterSheetPage(): Action[AnyContent] = Action { implicit request: MessagesRequest[AnyContent] =>
    Ok(views.html.characterSheetCreate(characterSheetForm, createCharacterSheetCall, getRandomNameCall ))
  }

  def createCharacterSheet(): Action[AnyContent] = Action { implicit request: MessagesRequest[AnyContent] =>

    val errorFunction = { formWithErrors: Form[CharacterSheet] =>
      // This is the bad case, where the form had validation errors.
      // Let's show the user the form again, with the errors highlighted.
      // Note how we pass the form with errors to the template.
      println(formWithErrors.errors.mkString(";"))
      BadRequest(views.html.characterSheetCreate(formWithErrors, createCharacterSheetCall, getRandomNameCall ))
    }

    val successFunction = { characterSheet: CharacterSheet =>
      // This is the good case, where the form was successfully parsed as a Data object.
      characterSheetRepository.create(characterSheet)
      Redirect(routes.CharacterSheetController.getCharacter(None, None, None, None))
        .flashing("info" -> s"${characterSheet.name} added!")
    }

    val formValidationResult = characterSheetForm.bindFromRequest()
    formValidationResult.fold(errorFunction, successFunction)
  }



  // Button Handlers
  def getRandomName(): Action[AnyContent] = Action { implicit request: MessagesRequest[AnyContent] =>
    val formValidationResult = characterSheetForm.bindFromRequest()
    formValidationResult.value match {
      case Some(form) => Ok(views.html.characterSheetCreate(characterSheetForm.fill(form.copy(name  = "bob")), createCharacterSheetCall, getRandomNameCall ))
      case None => Ok(views.html.characterSheetCreate(characterSheetForm, createCharacterSheetCall, getRandomNameCall ))
    }
  }


  /*
  Some garbage stuff
   */
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
