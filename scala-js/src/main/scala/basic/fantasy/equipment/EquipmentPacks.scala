package basic.fantasy.equipment

import basic.fantasy.Roller
import basic.fantasy.characterclass.CharacterClasses._

object EquipmentPacks {

  sealed trait EquipmentPack {
    def weight: Int
    def description: String
    override def toString(): String = description
  }

  case object BurglarsPack  extends EquipmentPack {
    val weight: Int = 46
    val description: String =
      """|backpack
         |bag of 1,000 ball bearings
         |10' of string
         |bell
         |5 candles
         |crowbar
         |hammer & 10 pitons
         |hooded lantern
         |2 flasks of oil
         |5 days rations
         |tinderbox
         |50' of hempen rope
         |waterskin
         |""".stripMargin
  }

  case object DungeoneersPack  extends EquipmentPack {
    val weight: Int = 62
    val description: String =
      """|backpack
         |crowbar
         |hammer
         |10 pitons
         |20 torches
         |tinderbox
         |10 days of rations
         |50' of hempen rope
         |waterskin
         |""".stripMargin
  }


  case object ExplorersPack  extends EquipmentPack {
    val weight: Int = 54
    val description: String =
      """|backpack
         |bedroll
         |mess kit
         |tinderbox
         |10 torches
         |10 days of rations
         |50' of hempen rope
         |waterskin
         |""".stripMargin
  }

  case object PriestsPack  extends EquipmentPack {
    val weight: Int = 25
    val description: String =
      """|backpack
         |blanket
         |10 candles
         |tinderbox
         |alms box
         |2 blocks of insense
         |censer
         |vestments
         |2 days of rations
         |waterskin
         |""".stripMargin
  }

  case object ScholarsPack  extends EquipmentPack {
    val weight: Int = 11
    val description: String =
      """|backpack
         |book of lore
         |bottle of ink
         |ink pen
         |10 sheets of parchment
         |little bag of sand
         |small knife
         |""".stripMargin
  }

  case object ScholarBurglarsPack  extends EquipmentPack {
    val weight: Int = 50
    val description: String =
      """|backpack
         |bag of 1,000 ball bearings
         |10' of string,
         |bell
         |5 candles
         |crowbar, "
         |book of lore
         |bottle of ink
         |an ink pen
         |10 sheets of parchment
         |hammer & 10 pitons
         |hooded lantern
         |2 flasks of oil
         |5 days rations
         |tinderbox
         |50' feet of hempen rope
         |waterskin
         |""".stripMargin
  }

  case object ScholarExplorersPack  extends EquipmentPack {
    val weight: Int = 54
    val description: String =
      """|backpack
         |bedroll
         |mess kit
         |tinderbox
         |10 torches
         |10 days of rations
         |book of lore
         |bottle of ink
         |ink pen
         |10 sheets of parchment
         |50' of hempen rope
         |waterskin
      |""".stripMargin
  }

  def getEquipmentPack(characterClass: CharacterClass): EquipmentPack = characterClass match {
    case Cleric => PriestsPack
    case Fighter => if(Roller.coinFLip()) ExplorersPack else DungeoneersPack
    case FighterMagicUser => ScholarExplorersPack
    case MagicUser => ScholarsPack
    case MagicUserThief => ScholarBurglarsPack
    //case Monk => if(Roller.coinFLip()) ExplorersPack else  PriestsPack
    case Thief => BurglarsPack
  }

}
