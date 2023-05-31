package models

import basic.fantasy.backgrounds.CharacterAlignments.CharacterAlignment
import basic.fantasy.backgrounds.Races.Race
import basic.fantasy.characterclass.CharacterClasses.CharacterClass

case class CharacterAttributes(
                                race: Option[Race],
                                characterClass: Option[CharacterClass],
                                level: Option[Int],
                                gender: Option[String],
                                height: Option[String],
                                weight: Option[Int],
                                alignment: Option[CharacterAlignment],
                                personality: Option[String],
                                strength: Option[Int],
                                dexterity: Option[Int],
                                constitution: Option[Int],
                                intelligence: Option[Int],
                                wisdom: Option[Int],
                                charisma: Option[Int]
                              )
