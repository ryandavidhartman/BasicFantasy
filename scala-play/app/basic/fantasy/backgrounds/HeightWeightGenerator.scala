package basic.fantasy.backgrounds

import basic.fantasy.Roller
import basic.fantasy.backgrounds.Races._

object HeightWeightGenerator {

  def getHeight(race: Race, gender: String, strength: Int): (String, String) = {

    val (baseHeight, baseWeight) = race match {
      case Human =>
        val hMod = (Roller.randomInt(10) + 1) + (Roller.randomInt(10) + 1)
        val wMod = ((Roller.randomInt(4) + 1) + (Roller.randomInt(4) + 1)) * hMod
        (56 + hMod, 110 + wMod)
      case Elf =>
        val hMod = (Roller.randomInt(10) + 1) + (Roller.randomInt(10) + 1)
        val wMod = (Roller.randomInt(4) + 1) * hMod
        (54 + hMod, 90 + wMod)
      case HalfElf =>
        val hMod = (Roller.randomInt(10) + 1) + (Roller.randomInt(10) + 1)
        val wMod = (Roller.randomInt(4) + 1) * hMod
        (55 + hMod, 100 + wMod)
      case HalfOrc =>
        val hMod = (Roller.randomInt(10) + 1) + (Roller.randomInt(10) + 1)
        val wMod = ((Roller.randomInt(4) + 1) + (Roller.randomInt(4) + 1)) * hMod
        (57 + hMod, 120 + wMod)
      case Dwarf =>
        val hMod = (Roller.randomInt(4) + 1) + (Roller.randomInt(4) + 1)
        val wMod = (Roller.randomInt(6) + 1) * hMod + (Roller.randomInt(6) + 1) * hMod
        (46 + hMod, 115 + wMod)
      case Halfling =>
        val hMod = (Roller.randomInt(4) + 1) + (Roller.randomInt(4) + 1)
        val wMod = (Roller.randomInt(4) + 1) * hMod + (Roller.randomInt(4) + 1) * hMod
        (31 + hMod, 35 + wMod)
    }

    val strMod: Double =
      if (strength <= 6)
        0.90
      else if (strength <= 9)
        0.95
      else if (strength < 12)
        1.0
      else if (strength < 17)
        1.05
      else
        1.10

    val (height, weight) =
      if (gender == "Female")
        (baseHeight - 5, (baseWeight.toDouble * strMod * 0.85).toInt)
      else
        (baseHeight, (baseWeight.toDouble * strMod).toInt)


    (inchesToString(height), weight.toString)
  }


  def inchesToString(inches: Int): String = {
    val feet: Int = inches / 12
    val remainder = inches - (12 * feet)
    s"""$feet\' $remainder""""
  }

}
