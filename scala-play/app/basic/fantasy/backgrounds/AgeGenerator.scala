package basic.fantasy.backgrounds

import basic.fantasy.Roller
import basic.fantasy.backgrounds.Races._

object AgeGenerator {
  def getAge(race: Race, level: Int): Int = race match {
    case Human =>     15 + Roller.randomInt(20)  + level
    case Elf =>      100 + Roller.randomInt(200) + level * 10
    case Dwarf =>     40 + Roller.randomInt(80)  + level * 4
    case Halfling => (17 + Roller.randomInt(22)  + level * 1.1).toInt
    case HalfElf =>   50 + Roller.randomInt(100) + level * 5
    case HalfOrc =>   10 + Roller.randomInt(15)  + level
  }

}
