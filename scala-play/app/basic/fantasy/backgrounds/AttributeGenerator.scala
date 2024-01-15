package basic.fantasy.backgrounds

import basic.fantasy.Roller
import basic.fantasy.characterclass.CharacterClasses.{CharacterClass, Cleric, Fighter, FighterMagicUser, MagicUser, MagicUserThief, Thief}
import play.api.libs.json.{Format, Json}

object AttributeGenerator {

  case class Attributes(strength: Int,
                        intelligence: Int,
                        wisdom: Int,
                        dexterity: Int,
                        constitution: Int,
                        charisma: Int
                       )

  implicit val attributesFormat: Format[Attributes] = Json.format[Attributes]

  def getRandomAttributes(characterClass: Option[CharacterClass] = None,
                          heroic: Boolean = false): Attributes = {

    val results: Attributes = if (characterClass.isDefined) {
      val r = Roller.getSixScores(heroic).sortWith(_ > _)

      characterClass.get match {
        case Cleric =>           Attributes(strength = r(1), intelligence = r(3), wisdom = r(0), dexterity = r(5), constitution = r(2), charisma = r(4))
        case Fighter =>          Attributes(strength = r(0), intelligence = r(5), wisdom = r(4), dexterity = r(2), constitution = r(1), charisma = r(3))
        case FighterMagicUser => Attributes(strength = r(0), intelligence = r(1), wisdom = r(4), dexterity = r(2), constitution = r(3), charisma = r(5))
        case MagicUser =>        Attributes(strength = r(5), intelligence = r(0), wisdom = r(3), dexterity = r(1), constitution = r(2), charisma = r(4))
        case MagicUserThief =>   Attributes(strength = r(3), intelligence = r(1), wisdom = r(5), dexterity = r(0), constitution = r(2), charisma = r(4))
        case Thief =>            Attributes(strength = r(1), intelligence = r(3), wisdom = r(5), dexterity = r(0), constitution = r(2), charisma = r(4))
      }

    } else {
      val rolls = Roller.getSixScores(heroic)
      Attributes(rolls(0), rolls(1), rolls(2), rolls(3), rolls(4), rolls(5))
    }

    results
  }

}
