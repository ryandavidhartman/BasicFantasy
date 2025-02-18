package basic.fantasy.characterclass

object CharacterClasses {

  sealed trait CharacterClass {
    val isCleric = false
    val isFighter = false
    val isMagicUser = false
    //val isMonk = false
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
      "No armor/shield when casting, any otherwise",
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
      "No armor/shield when casting. Leather, no shield otherwise",
      "All weapon types"
    )
  }

//  case object Monk extends CharacterClass {
//    override val isMonk = true
//
//    override def getAbilities(): Seq[String] = Seq(
//      "No armor or shield",
//      "All weapon types",
//    )
//  }

  case object Thief extends CharacterClass {
    override val isThief = true

    override def getAbilities(): Seq[String] = Seq(
      "Leather armor, no shield",
      "All weapon types"
    )
  }

  case class InvalidCharacterClassException(msg: String) extends Exception(msg)

  def stringToCharacterClass(characterClass: String): CharacterClass = characterClass match {
    case "Cleric" => Cleric
    case "Fighter" => Fighter
    case "Fighter/Magic-User" => FighterMagicUser
    case "Magic-User" => MagicUser
    case "Magic-User/Thief" => MagicUserThief
    // case "Monk" => Monk
    case "Thief" => Thief
    case _ => throw InvalidCharacterClassException(s"Invalid character class: $characterClass")
  }

  val All_CLASSES: Set[CharacterClass] = Set(Cleric,
    Fighter,
    FighterMagicUser,
    MagicUser,
    MagicUserThief,
    // Monk,
    Thief)
}
