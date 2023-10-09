package basic.fantasy.characterclass

object CharacterClasses {

  sealed trait CharacterClass {
    val isCleric = false
    val isFighter = false
    val isMagicUser = false
    val isThief = false
    val isMultiClass = false
    val isSpellCaster = false

    def getAbilities(): Seq[String]
  }

  case object Cleric extends CharacterClass {
    override val isCleric = true
    override val isSpellCaster = true

    override def getAbilities(): Seq[String] = Seq(
      "Any armor/shield",
      "Blunt weapons only (club, mace, maul, quarterstaff, sling, warhammer)",
    )
  }

  case object Fighter extends CharacterClass {
    override val isFighter = true

    override def getAbilities(): Seq[String] = Seq(
      "Any armor/shield",
      "Any weapon type"
    )
  }

  case object FighterMagicUser extends CharacterClass {
    override val isFighter = true
    override val isMagicUser = true
    override val isMultiClass = true
    override val isSpellCaster = true

    override def getAbilities(): Seq[String] = Seq(
      "Any armor/shield, (may cast with armor)",
      "All weapon types"
    )
  }

  case object MagicUser extends CharacterClass {
    override val isMagicUser = true
    override val isSpellCaster = true

    override def getAbilities(): Seq[String] = Seq(
      "No armor or shield",
      "Allowed weapons: cudgel, dagger, walking staff",
    )
  }

  case object MagicUserThief extends CharacterClass {
    override val isThief = true
    override val isMagicUser = true
    override val isMultiClass = true
    override val isSpellCaster = true

    override def getAbilities(): Seq[String] = Seq(
      "Leather armor, no shield (may cast with armor)",
      "All weapon types"
    )
  }
  case object Thief extends CharacterClass {
    override val isThief = true

    override def getAbilities(): Seq[String] = Seq(
      "Leather armor, no shield",
      "All weapon types"
    )
  }

  case class InvalidCharacterClassException(msg: String) extends Exception(msg)

  def stringToCharacterClass(characterClass: String): CharacterClass = characterClass.toLowerCase match {
    case "cleric" => Cleric
    case "fighter" => Fighter
    case "fighter/magic-user" => FighterMagicUser
    case "magic-user" => MagicUser
    case "magic-user/thief" => MagicUserThief
    case "thief" => Thief
    case _ => throw InvalidCharacterClassException(s"Invalid character class: $characterClass")
  }

  val All_CLASSES: Set[CharacterClass] = Set(Cleric,
    Fighter,
    FighterMagicUser,
    MagicUser,
    MagicUserThief,
    Thief)
}
