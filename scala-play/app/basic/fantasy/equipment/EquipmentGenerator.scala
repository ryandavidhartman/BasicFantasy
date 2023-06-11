package basic.fantasy.equipment

import basic.fantasy.backgrounds.Races.Race
import basic.fantasy.characterclass.CharacterClasses.CharacterClass
import basic.fantasy.equipment.Armors.{Armor, LeatherOrMagicMetal, Metal, NoneOrMagicLeather}
import basic.fantasy.equipment.EquipmentPacks.EquipmentPack
import basic.fantasy.equipment.MagicalItems.MagicItem
import basic.fantasy.equipment.MeleeWeapons.MeleeWeapon
import basic.fantasy.equipment.RangedWeapons.RangedWeapon
import basic.fantasy.equipment.Shields.{NoShield, Shield}

case class Equipment(
  armor: Armor,
  shield: Shield,
  meleeWeapon: MeleeWeapon,
  offhand: MeleeWeapon,
  rangedWeapon: RangedWeapon,
  equipmentPack: EquipmentPack,
  magicalItems: Set[MagicItem]
) {
  def totalWeight(): Int = {
    armor.weight + shield.weight + meleeWeapon.weight + offhand.weight + rangedWeapon.weight + equipmentPack.weight
  }

  def findMovement(strength: Int, race: Race): String = {
    val (lightLoad, heavyLoad) = race.carryingCapacity(strength)
    val weight = totalWeight()

    armor.armorType match {
      case NoneOrMagicLeather =>
        if(weight < lightLoad)
          "40'"
        else if(weight < heavyLoad)
          "30'"
        else
          "10' or less"
      case LeatherOrMagicMetal =>
        if(weight < lightLoad)
          "30'"
        else if(weight < heavyLoad)
          "20'"
        else
          "7' or less"
      case Metal =>
        if(weight < lightLoad)
          "20'"
        else if(weight < heavyLoad)
          "10'"
        else
          "3' or less"
    }


  }
}

object EquipmentGenerator {

  def getEquipment(characterClass: CharacterClass, level: Int, race: Race): Equipment = {
    val armor = Armors.getArmor(characterClass, level)
    val shield = Shields.getShield(characterClass, level)
    val hasShield = (shield != NoShield)
    val meleeWeapon = MeleeWeapons.getMeleeWeapon(characterClass, level, race, hasShield)
    val offhandWeapon = MeleeWeapons.getSecondaryWeapon(characterClass, race, level, meleeWeapon, hasShield)
    val rangedWeapon = RangedWeapons.getRangedWeapon(characterClass, level, race)
    val equipmentPack = EquipmentPacks.getEquipmentPack(characterClass)
    val magicalItems: Set[MagicItem] = MagicalItems.getMagicItems(characterClass, race, level)
    Equipment(armor, shield, meleeWeapon, offhandWeapon, rangedWeapon, equipmentPack, magicalItems)
  }

}
