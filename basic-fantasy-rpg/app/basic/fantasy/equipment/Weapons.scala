package basic.fantasy.equipment

object Weapons {

  sealed trait WeaponSize
  case object Small extends WeaponSize
  case object Medium extends WeaponSize
  case object Large extends WeaponSize

}
