package bf2.model
sealed trait CharacterAlignment
case object Lawful extends CharacterAlignment
case object Neutral extends CharacterAlignment
case object Chaotic extends CharacterAlignment
object CharacterAlignments {
  def stringToCharacterAlignment(alignment: String): CharacterAlignment = alignment match {
    case "Lawful" => Lawful
    case "Neutral" => Neutral
    case "Chaotic" => Chaotic
    case _ => throw InvalidAlignmentException(s"Invalid character alignment: $alignment")
  }

  private case class InvalidAlignmentException(msg: String) extends Exception(msg)
}
