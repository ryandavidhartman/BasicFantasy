package controllers

import basic.fantasy.backgrounds.{AttributeGenerator, BackgroundGenerator, Races}
import basic.fantasy.characterclass.CharacterClasses
import basic.fantasy.rules.BasicFantasy
import play.api.libs.json.Json

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext
import play.api.mvc.{Action, AnyContent, MessagesAbstractController, MessagesControllerComponents, MessagesRequest}

@Singleton
class BackgroundController @Inject()(
                                      implicit executionContext: ExecutionContext,
                                      controllerComponents: MessagesControllerComponents
                                    ) extends MessagesAbstractController(controllerComponents) {
  def getRandom(race: String, intelligence: String): Action[AnyContent] = Action { implicit request: MessagesRequest[AnyContent] =>
    val languageBonus = BasicFantasy.getLanguageBonus(intelligence.toInt)
    val background = BackgroundGenerator.getBackground(Races.stringToRace(race), languageBonus)
    Ok(Json.toJson(background))
  }

  def getAttributes(characterClass: Option[String], heroic: Boolean): Action[AnyContent] = Action { implicit request: MessagesRequest[AnyContent] =>
    val chrClass = characterClass.map(CharacterClasses.stringToCharacterClass)
    val attributes = AttributeGenerator.getRandomAttributes(chrClass, heroic)
    Ok(Json.toJson(attributes))
  }
}
