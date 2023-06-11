package basic.fantasy.equipment

import basic.fantasy.Roller
import basic.fantasy.backgrounds.Races.{Elf, Race}
import basic.fantasy.characterclass.CharacterClasses.CharacterClass


object MagicalItems {

  sealed trait MagicItem {
    def usable(characterClass: CharacterClass, race: Race): Boolean = true
  }

  sealed trait Potion extends MagicItem {
    override def toString: String = s"Potion of ${this.getClass.getSimpleName.replace("$", "")}"
  }

  sealed trait Miscellaneous extends MagicItem

  sealed trait Ring extends MagicItem {
    override def toString: String = s"Ring of ${this.getClass.getSimpleName.replace("$", "")}"
  }

  sealed trait WandStaffOrRod extends MagicItem {
    override def usable(characterClass: CharacterClass, race: Race): Boolean = characterClass.isMagicUser
  }

  // Miscellaneous
  case object AmuletOfProofAgainstDetectionAndLocation extends Miscellaneous {
     override def toString: String = "Amulet of Proof against Detection and Location"
  }
  case object BagOfDevouring extends Miscellaneous {
    override def toString: String = "Bag of Devouring"
  }
  case object BagOfHolding extends Miscellaneous {
    override def toString: String = "Bag of Holding"
  }
  case object BootsOfLevitation extends Miscellaneous {
    override def toString: String = "Boots of Levitation"
  }
  case object BootsOfSpeed extends Miscellaneous {
    override def toString: String = "Boots of Speed"
  }
  case object BootsOfTravelingAndLeaping extends Miscellaneous {
    override def toString: String = "Boots of Traveling and Leaping"
  }
  case object BowlCommandingWaterElementals extends Miscellaneous {
    override def toString: String = "Bowl Commanding Water Elementals"
  }
  case object BrazierCommandingFireElementals extends Miscellaneous {
    override def toString: String = "Brazier Commanding Fire Elementals"
  }
  case object BroomOfFlying extends Miscellaneous {
    override def toString: String = "Commanding Air Elementals"
  }
  case object CenserCommandingAirElementals extends Miscellaneous {
    override def toString: String = "Commanding Air Elementals"
  }
  case object CloakOfDisplacement extends Miscellaneous {
    override def toString: String = "Cloak of Displacement"
  }
  case object CrystalBall extends Miscellaneous {
    override def toString: String = "Crystal Ball"
  }
  case object CrystalBallWithClairaudience extends Miscellaneous {
    override def toString: String = "Crystal Ball with Clairaudience"
  }
  case object DrumsOfPanic extends Miscellaneous {
    override def toString: String = "Drums of Panic"
  }
  case object EfreetiBottle extends Miscellaneous {
    override def toString: String = "Efreeti Bottle"
  }
  case object ElvenBoots extends Miscellaneous {
    override def toString: String = "Elven Boots"
    override def usable(characterClass: CharacterClass, race: Race): Boolean = race == Elf
  }
  case object ElvenCloak extends Miscellaneous {
    override def toString: String = "Elven Cloak"
    override def usable(characterClass: CharacterClass, race: Race): Boolean = race == Elf
  }
  case object FlyingCarpet extends Miscellaneous {
    override def toString: String = "Flying Carpet"
  }
  case object GauntletsOfOgrePower extends Miscellaneous {
    override def toString: String = "Gauntlets of Ogre Power"
    override def usable(characterClass: CharacterClass, race: Race): Boolean = characterClass.isFighter
  }
  case object GirdleOfGiantStrength extends Miscellaneous {
    override def toString: String = "Girdle of Giant Strength"
    override def usable(characterClass: CharacterClass, race: Race): Boolean = characterClass.isFighter
  }
  case object HelmOfReadingLanguagesAndMagic extends Miscellaneous {
    override def toString: String = "Helm of Reading Languages and Magic"
  }
  case object HelmOfTelepathy extends Miscellaneous {
    override def toString: String = "Helm of Telepathy"
  }
  case object HelmOfTeleportation extends Miscellaneous {
    override def toString: String = "Helm of Teleportation"
  }
  case object HornOfBlasting extends Miscellaneous {
    override def toString: String = "Horn of Blasting"
  }
  case object HornOfDoom extends Miscellaneous {
    override def toString: String = "Horn of Doom"
  }
  case object MedallionOfESP extends Miscellaneous {
    override def toString: String = "Medallion of ESP"
  }
  case object MirrorOfLifeTrapping extends Miscellaneous {
    override def toString: String = "Mirror of Life Trapping"
  }
  case object RopeOfClimbing extends Miscellaneous {
    override def toString: String = "Rope of Climbing"
  }
  case object ScarabOfProtection extends Miscellaneous {
    override def toString: String = "Scarab of Protection"
  }
  case object StoneCommandingEarthElementals extends Miscellaneous {
    override def toString: String = "Stone Commanding Earth Elementals"
  }

  // Potions
  case object Clairaudience extends Potion {
    override def usable(characterClass: CharacterClass, race: Race): Boolean = characterClass.isSpellCaster
  }
  case object Clairvoyance extends Potion {
    override def usable(characterClass: CharacterClass, race: Race): Boolean = characterClass.isSpellCaster
  }
  case object ColdResistance extends Potion {
    override def toString: String = s"Potion of Cold Resistance"
  }
  case object ControlAnimal extends Potion {
    override def toString: String = s"Potion of Animal Control"
  }
  case object ControlDragon extends Potion {
    override def toString: String = s"Potion of Dragon Control"
  }
  case object ControlGiant extends Potion {
    override def toString: String = s"Potion of Giant Control"
  }
  case object ControlHuman extends Potion {
    override def toString: String = s"Potion of Human Control"
  }
  case object ControlPlant extends Potion {
    override def toString: String = s"Potion of Plant Control"
  }
  case object ControlUndead extends Potion {
    override def toString: String = s"Potion of Undead Control"
  }
  case object DelusionPotion extends Potion {
    override def toString: String = s"Potion of Delusion"
  }
  case object Diminution extends Potion
  case object ESP extends Potion {
    override def usable(characterClass: CharacterClass, race: Race): Boolean = characterClass.isSpellCaster
  }
  case object FireResistance extends Potion {
    override def toString: String = s"Potion of Fire Resistance"
  }
  case object Flying extends Potion
  case object GaseousForm extends Potion {
    override def toString: String = s"Potion of Gaseous From"
  }
  case object GiantStrength extends Potion {
    override def toString: String = s"Potion of Giant Strength"
  }
  case object Growth extends Potion
  case object Healing extends Potion
  case object Heroism extends Potion
  case object InvisibilityPotion extends Potion {
    override def toString: String = s"Potion of Invisibility"
  }
  case object Invulnerability extends Potion
  case object Levitation extends Potion
  case object Longevity extends Potion
  case object Poison extends Potion
  case object PolymorphSelf {
    override def toString: String = s"Potion of Polymorph Self"
  }
  case object Speed extends Potion
  case object TreasureFinding extends Potion {
    override def toString: String = s"Potion of Treasure Finding"
  }

  // Rings
  case object AnimalControl extends Ring {
    override def toString: String = s"Ring of Animal Control"
  }
  case object Delusion extends Ring
  case object DjinniSummoning extends Ring {
    override def toString: String = s"Ring of Djinni Summoning"
  }
  case object Ear extends Ring
  case object ElementalAdaptation extends Ring {
    override def toString: String = s"Ring of Elemental Adaptation"
  }
  case object FireResistanceRing extends Ring {
    override def toString: String = s"Ring of Fire Resistance"
  }
  case object Holiness extends Ring {
    override def usable(characterClass: CharacterClass, race: Race): Boolean = characterClass.isCleric
  }
  case object HumanControl extends Ring {
    override def toString: String = s"Ring of Human Control"
  }
  case object Invisibility extends Ring
  case object LifeProtection extends Ring {
    override def toString: String = s"Ring of Life Protection"
  }
  case object Memory extends Ring {
    override def usable(characterClass: CharacterClass, race: Race): Boolean = characterClass.isSpellCaster
  }
  case object PlantControl extends Ring {
    override def toString: String = s"Ring of Plant Control"
  }
  case class Protection(level: Int) extends Ring {
    override def toString: String = {
      val magic = Math.max(level/5, 1)
      s"Ring of Protection +$magic"
    }
  }
  case object Quickness extends Ring

  // Wands, Staves and Rods
  case object RodOfCancellation extends WandStaffOrRod {
    override def toString: String = s"Rod Of Cancellation"
  }
  case object SnakeStaff extends WandStaffOrRod {
    override def toString: String = s"Snake Staff"
  }
  case object StaffOfCommanding extends WandStaffOrRod {
    override def toString: String = s"Staff Of Commanding"
  }
  case object StaffOfHealing extends WandStaffOrRod {
    override def toString: String = s"Staff Of Healing"
    override def usable(characterClass: CharacterClass, race: Race): Boolean = characterClass.isCleric
  }
  case object StaffOfPower extends WandStaffOrRod {
    override def toString: String = s"Staff Of Power"
  }
  case object StaffOfStriking extends WandStaffOrRod {
    override def toString: String = s"Staff Of Striking"
  }
  case object StaffOfWizardry extends WandStaffOrRod {
    override def toString: String = s"Staff Of Wizardry"

    override def usable(characterClass: CharacterClass, race: Race): Boolean = characterClass.isMagicUser
  }
  case object WandOfCold extends WandStaffOrRod {
    override def toString: String = s"Wand Of Cold"
  }
  case object WandOfEnemyDetection extends WandStaffOrRod {
    override def toString: String = s"Wand Of Enemy Detection"
  }
  case object WandOfFear extends WandStaffOrRod {
    override def toString: String = s"Wand Of Fear"
  }
  case object WandOfFireballs extends WandStaffOrRod {
    override def toString: String = s"Wand Of Fireballs"
  }
  case object WandOfIllusion extends WandStaffOrRod {
    override def toString: String = s"Wand Of Illusion"
  }
  case object WandOfLightningBolts extends WandStaffOrRod {
    override def toString: String = s"Wand Of Lightning Bolts"
  }
  case object WandOfMagicDetection extends WandStaffOrRod {
    override def toString: String = s"Wand Of Magic Detection"
  }
  case object WandOfParalyzation extends WandStaffOrRod {
    override def toString: String = s"Wand Of Magic Paralyzation"
  }
  case object WandOfPolymorph extends WandStaffOrRod {
    override def toString: String = s"Wand Of Polymorph"
  }
  case object WandOfSecretDoorDetection extends WandStaffOrRod {
    override def toString: String = s"Wand Of Secret Door Detection"
  }
  case object WandOfTrapDetection extends WandStaffOrRod {
    override def toString: String = s"Wand Of Trap Detection"
  }

  def getPotion(characterClass: CharacterClass, race: Race, level: Int): Potion = {
    val roll = Roller.randomInt(29)
    val item: Potion =
      if(roll < 1) Clairaudience
      else if (roll < 2) Clairvoyance
      else if (roll < 3) ColdResistance
      else if (roll < 4) ControlAnimal
      else if (roll < 5) ControlDragon
      else if (roll < 6) ControlGiant
      else if (roll < 7) ControlHuman
      else if (roll < 8) ControlPlant
      else if (roll < 9) ControlUndead
      else if (roll < 10) DelusionPotion
      else if (roll < 11) Diminution
      else if (roll < 12) ESP
      else if (roll < 13) FireResistance
      else if (roll < 14) Flying
      else if (roll < 15) GaseousForm
      else if (roll < 16) GiantStrength
      else if (roll < 17) Growth
      else if (roll < 20) Healing
      else if (roll < 21) Heroism
      else if (roll < 22) InvisibilityPotion
      else if (roll < 23) Invulnerability
      else if (roll < 24) Levitation
      else if (roll < 25) Longevity
      else if (roll < 26) Poison
      else if (roll < 28) Speed
      else TreasureFinding

    if(item.usable(characterClass, race)) item else getPotion(characterClass, race, level)

  }

  def getMiscellaneous(characterClass: CharacterClass, race: Race, level: Int): Miscellaneous = {
    val roll = Roller.randomInt(48)
    val item: Miscellaneous =
      if(roll < 1) AmuletOfProofAgainstDetectionAndLocation
      else if (roll < 2) BagOfDevouring
      else if (roll < 12) BagOfHolding
      else if (roll < 13) BootsOfLevitation
      else if (roll < 15) BootsOfSpeed
      else if (roll < 16) BootsOfTravelingAndLeaping
      else if (roll < 17) BowlCommandingWaterElementals
      else if (roll < 18) BrazierCommandingFireElementals
      else if (roll < 19) BroomOfFlying
      else if (roll < 20) CenserCommandingAirElementals
      else if (roll < 21) CloakOfDisplacement
      else if (roll < 22) CrystalBall
      else if (roll < 23) CrystalBallWithClairaudience
      else if (roll < 24) DrumsOfPanic
      else if (roll < 25) EfreetiBottle
      else if (roll < 26) BootsOfLevitation
      else if (roll < 28) ElvenBoots
      else if (roll < 30) ElvenCloak
      else if (roll < 31) FlyingCarpet
      else if (roll < 32) GauntletsOfOgrePower
      else if (roll < 33) GirdleOfGiantStrength
      else if (roll < 34) HelmOfReadingLanguagesAndMagic
      else if (roll < 35) HelmOfTelepathy
      else if (roll < 36) HelmOfTeleportation
      else if (roll < 37) HornOfBlasting
      else if (roll < 38) HornOfDoom
      else if (roll < 39) MedallionOfESP
      else if (roll < 40) MirrorOfLifeTrapping
      else if (roll < 41) GauntletsOfOgrePower
      else if (roll < 42) GirdleOfGiantStrength
      else if (roll < 43) HelmOfReadingLanguagesAndMagic
      else if (roll < 44) HelmOfTelepathy
      else if (roll < 45) HelmOfTeleportation
      else if (roll < 46) RopeOfClimbing
      else if (roll < 47) ScarabOfProtection
      else StoneCommandingEarthElementals

    if(item.usable(characterClass, race)) item else getMiscellaneous(characterClass, race, level)

  }

  def getRing(characterClass: CharacterClass, race: Race, level: Int): Ring = {
    val roll = Roller.randomInt(20)
    val item: Ring =
      if(roll < 1) AnimalControl
      else if (roll < 2) Delusion
      else if (roll < 3) DjinniSummoning
      else if (roll < 4) Ear
      else if (roll < 5) ElementalAdaptation
      else if (roll < 6) FireResistanceRing
      else if (roll < 7) Holiness
      else if (roll < 8) HumanControl
      else if (roll < 10) Invisibility
      else if (roll < 11) LifeProtection
      else if (roll < 12) Memory
      else if (roll < 13) PlantControl
      else if (roll < 16) Quickness
      else Protection(level)

    if(item.usable(characterClass, race)) item else getRing(characterClass, race, level)
  }

  def getWandStaffOrRod(characterClass: CharacterClass, race: Race, level: Int):  WandStaffOrRod = {
    val roll = Roller.randomInt(20)
    val item: WandStaffOrRod =
      if(roll < 1) RodOfCancellation
      else if (roll < 2) SnakeStaff
      else if (roll < 3) StaffOfCommanding
      else if (roll < 4) StaffOfHealing
      else if (roll < 5) StaffOfPower
      else if (roll < 6) SnakeStaff
      else if (roll < 7) StaffOfCommanding
      else if (roll < 8) StaffOfHealing
      else if (roll < 9) StaffOfPower
      else if (roll < 10) StaffOfStriking
      else if (roll < 11) StaffOfWizardry
      else if (roll < 12) WandOfCold
      else if (roll < 13) WandOfEnemyDetection
      else if (roll < 14) WandOfFear
      else if (roll < 15) WandOfFireballs
      else if (roll < 16) WandOfIllusion
      else if (roll < 17) WandOfLightningBolts
      else if (roll < 18) WandOfMagicDetection
      else if (roll < 19) WandOfParalyzation
      else WandOfTrapDetection

    if(item.usable(characterClass, race)) item else getWandStaffOrRod(characterClass, race, level)
  }

  def getMagicItem(characterClass: CharacterClass, race: Race, level: Int): MagicItem = {
    val roll = Roller.randomInt(100)
    characterClass match {
      case c if c.isSpellCaster =>
        if(roll < 25) getPotion(characterClass, race, level)
        else if(roll < 50) getMiscellaneous(characterClass, race, level)
        else if(roll < 75) getRing(characterClass, race, level)
        else getWandStaffOrRod(characterClass, race, level)
      case _ =>
        if(roll < 33) getPotion(characterClass, race, level)
        else if(roll < 66) getMiscellaneous(characterClass, race, level)
        else getRing(characterClass, race, level)
    }
  }

  def getMagicItems(characterClass: CharacterClass, race: Race, level: Int): Set[MagicItem] = {
    val numItems: Int = Math.min(Roller.randomInt(level), 6)

    (1 to numItems).map(_ => getMagicItem(characterClass, race, level)).toSet
  }

}
