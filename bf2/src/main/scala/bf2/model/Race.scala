package bf2.model

import bf2.model.Races.SavingThrowBonuses
sealed trait Race {
  val weapons: Seq[String] = Seq.empty[String]
  val getAbilities: Seq[String]
  val savingsThrowBonuses: SavingThrowBonuses = SavingThrowBonuses(0, 0, 0, 0, 0)
  def carryingCapacity(strength: Int): (Int, Int) = {
    if (strength == 3)
      (25, 60)
    else if (strength <= 5)
      (35, 90)
    else if (strength <= 8)
      (50, 120)
    else if (strength <= 12)
      (60, 150)
    else if (strength <= 15)
      (65, 165)
    else if (strength <= 17)
      (70, 180)
    else
      (80, 195)
  }
}
case object Human extends Race {
  override val getAbilities: Seq[String] = Seq("+10% Bonus on Earned XP")
}
case object Elf extends Race {
  override val getAbilities: Seq[String] = Seq(
    "Darkvision with a 60' range",
    "Find secret door 1-2 on d6 when searching",
    "Find secret door 1 on d6 with a cursory look",
    "Immune to paralyzing attacks of Ghouls",
    "Only surprised on 1 on d6"
  )
  override val savingsThrowBonuses: SavingThrowBonuses = SavingThrowBonuses(0, 2, 1, 0, 2)
}
case object Dwarf extends Race {
  override val weapons: Seq[String] = Seq(
    "No 2-handed swords, pole-arms, or longbows"
  )

  override val getAbilities: Seq[String] = Seq(
    "Darkvision 60'",
    "Detect new construction, shifting walls slanting passages, traps w/ 1-2 on d6 when searching"
  )

  override val savingsThrowBonuses: SavingThrowBonuses = SavingThrowBonuses(4, 4, 4, 3, 4)
}
case object Halfling extends Race {
  override val weapons: Seq[String] = Seq(
    "Must use medium weapons in two hands",
    "Cannot use large weapons")

  override def carryingCapacity(strength: Int): (Int, Int) = {
    if (strength == 3)
      (20, 40)
    else if (strength <= 5)
      (30, 60)
    else if (strength <= 8)
      (40, 80)
    else if (strength <= 12)
      (50, 100)
    else if (strength <= 15)
      (55, 110)
    else if (strength <= 17)
      (60, 120)
    else
      (65, 130)
  }

  override val getAbilities: Seq[String] = Seq(
    "+1 attack bonus on ranged weapons (bonus added already)",
    "+2 bonus to AC when attacked in melee by creatures larger than man-sized",
    "+1 to initiative die rolls",
    "Hide (10% chance to be detected outdoors, 30% chance to be detected indoors)"
  )

  override val savingsThrowBonuses: SavingThrowBonuses = SavingThrowBonuses(4, 4, 4, 3, 4)
}
case object HalfElf extends Race {
  override val getAbilities: Seq[String] = Seq(
    "Darkvision with a 30' range",
    "Able to find secret doors on a 1-2 on 1d6, when searching",
    "+5% on all earned experience, except for multiclass."
  )

  override val savingsThrowBonuses: SavingThrowBonuses = SavingThrowBonuses(0, 1, 0, 0, 1)
}
case object HalfOrc extends Race {
  override val getAbilities: Seq[String] = Seq(
    "+5% on all earned experience.",
    "Darkvision with a 60' range",
    "When dealing with humanoids of Human-size or smaller, a Half-Orc gains an additional +1 on any reaction"
  )

  override val savingsThrowBonuses: SavingThrowBonuses = SavingThrowBonuses(1, 0, 0, 0, 0)
}
object Races {
  case class SavingThrowBonuses(
                                  deathRayOrPoison: Int,
                                  magicWands: Int,
                                  paralysisOrPetrify: Int,
                                  breathWeapons: Int,
                                  spells: Int
                                )

  def stringToRace(race: String): Race = race match {
    case "Human" => Human
    case "Elf" => Elf
    case "Dwarf" => Dwarf
    case "Halfling" => Halfling
    case "HalfElf" => HalfElf
    case "HalfOrc" => HalfOrc
    case _ => throw InvalidRaceException(s"Invalid character race: $race")
  }

  private case class InvalidRaceException(msg: String) extends Exception(msg)

}

