//package basic.fantasy.characterclass
//
//import basic.fantasy.Roller
//
//object KiPowers {
//
//  sealed abstract trait KiPower {
//    def description: String
//    def name: String
//    def canSelect(level: Int, kiPowers: Set[KiPower]): Boolean =  !kiPowers.contains(this)
//    def canUseWhenEncumbered = false
//  }
//
//  case object AbundantStep extends KiPower {
//    override def description: String =
//      """The Monk can magically step between spaces. The Monk
//        |gets the benefit of a dimension door spell once per day.
//        |He or she gains a second use at 15th level. Must be at
//        |least 7th level""".stripMargin
//
//    override def name: String = "Abundant Step"
//
//    override def canSelect(level: Int, kiPowers: Set[KiPower]): Boolean = (level >= 7) && super.canSelect(level, kiPowers)
//  }
//  case object BurstingFist extends KiPower {
//    override def description: String =
//      """Once per day a Monk may slam their palms together
//        |creating a wave of force projecting forward for 50 feet.
//        |Anyone caught in the burst takes 1d6 per monk level, but
//        |may save vs. Dragon Breath for half damage. Must be at
//        |least 15th level and already have diamond fist.
//        |""".stripMargin
//
//    override def name: String = "Bursting Fist"
//
//    override def canSelect(level: Int, kiPowers: Set[KiPower]): Boolean =
//      (level >= 15) &&
//      kiPowers.contains(DiamondFist) &&
//      super.canSelect(level, kiPowers)
//  }
//  case object DeflectArrows extends KiPower {
//    override def description: String =
//      """Once per round, the Monk may deflect incoming ranged
//        |attacks that would have otherwise hit with a flick of their
//        |wrist. This does not reduce the number of attacks the
//        |Monk may make in the same round.
//        |""".stripMargin
//
//    override def name: String = "Deflect Arrows"
//  }
//  case object DiamondBody extends KiPower {
//    override def description: String =
//      """The Monk may flush any and all poison from their system
//        |once a day. The Monk gains an additional use at 15th
//        |level and a third use at 19th level. Must be at least 11th
//        |level.""".stripMargin
//
//    override def name: String = "Diamond Body"
//
//    override def canSelect(level: Int, kiPowers: Set[KiPower]): Boolean = (level >= 11) && super.canSelect(level, kiPowers)
//
//    override def canUseWhenEncumbered: Boolean = true
//  }
//  case object DiamondFist extends KiPower {
//    override def description: String =
//      """The Monk’s unarmed attacks ignore any hardness of the
//        |target. Must be at least 11th level and have stunning fist.""".stripMargin
//
//    override def name: String = "Diamond Fist"
//
//    override def canSelect(level: Int, kiPowers: Set[KiPower]): Boolean =
//      (level >= 11) &&
//      kiPowers.contains(StunningFist) &&
//      super.canSelect(level, kiPowers)
//  }
//  case object Evasion extends KiPower {
//    override def description: String =
//      """When a Monk makes a saving throw to avoid physical
//        |damage where he or she would normally take half
//        |damage, no damage is take instead.""".stripMargin
//
//    override def name: String = "Evasion"
//  }
//  case object FeatherBalance extends KiPower {
//    override def description: String =
//      """For one turn, the Monk cannot lose their balance. Any
//        |time they would be forced to make an ability check
//        |against their dexterity, they are considered to have rolled
//        |a 20. A Monk may use this once per day. An additional
//        |use is gained at 7th level and every four levels after that,
//        |to a maximum of five uses at 19th level.
//        |""".stripMargin
//
//    override def name: String = "Feather Balance"
//  }
//  case object FeatherStep extends KiPower {
//    override def description: String =
//      """While using feather balance, the Monk is able to move
//        |lightly across any surface even if it would not normally
//        |support his or her weight, ignoring any sort of difficult
//        |terrain. This does not allow the Monk to walk on water.
//        |Must be at least 11th level and already have feather
//        |balance.
//        |""".stripMargin
//
//    override def name: String = "Feather Step"
//
//    override def canSelect(level: Int, kiPowers: Set[KiPower]): Boolean =
//      (level >= 11) &&
//        kiPowers.contains(FeatherBalance) &&
//        super.canSelect(level, kiPowers)
//  }
//  case object FlawlessMind extends KiPower {
//    override def description: String =
//      """The Monk gains a +4 bonus on all saving throws against
//        |magical effects targeting the mind (e.g. charms or
//        |illusions). Must be at least 15th level.
//        |""".stripMargin
//
//    override def name: String = "Flawless Mind"
//
//    override def canSelect(level: Int, kiPowers: Set[KiPower]): Boolean =
//      (level >= 15) &&
//        super.canSelect(level, kiPowers)
//  }
//  case object ImprovedEvasion extends KiPower {
//    override def description: String =
//      """When a Monk makes a saving throw to avoid physical
//        |damage, not only is no damage taken if the saving throw
//        |succeeds, but only half damage is taken if the saving
//        |throw is failed. Must be at least 7th level and already have
//        |evasion""".stripMargin
//
//    override def name: String = "Improved Evasion"
//
//    override def canSelect(level: Int, kiPowers: Set[KiPower]): Boolean =
//      (level >= 7) &&
//        kiPowers.contains(Evasion) &&
//        super.canSelect(level, kiPowers)
//
//    override def canUseWhenEncumbered: Boolean = true
//  }
//  case object KiStrike extends KiPower {
//    override def description: String =
//      """The Monk’s unarmed attacks are treated as if they were
//        |magical weapons. They do not have an attack modifier,
//        |but the Monk can strike creatures that can normally only
//        |be hit by magical weapons. Must be at least 7th level.
//        |""".stripMargin
//
//    override def name: String = "Ki Strike"
//
//    override def canSelect(level: Int, kiPowers: Set[KiPower]): Boolean =
//      (level >= 7) &&
//        super.canSelect(level, kiPowers)
//  }
//  case object PunishingStrike extends KiPower {
//    override def description: String =
//      """Once per day a Monk may deliver a particularly punishing
//        |blow. If the blow hits, it does another 1d6 of damage. An
//        |additional use is gained at 15th level and a third use at 19th
//        |level. Must be at least 11th level and have stunning fist.
//        |""".stripMargin
//
//    override def name: String = "Punishing Strike"
//
//    override def canSelect(level: Int, kiPowers: Set[KiPower]): Boolean =
//      (level >= 11) &&
//        kiPowers.contains(StunningFist) &&
//        super.canSelect(level, kiPowers)
//  }
//  case object PurityOfBody extends KiPower {
//    override def description: String =
//      """The Monk may remove any diseases from his or her
//        |body, whether natural or magical, once a day. A second
//        |use is gained at 11th level and a third use at 15th level.
//        |Must be at least 7th level.""".stripMargin
//
//    override def name: String = "Purity of Body"
//
//    override def canSelect(level: Int, kiPowers: Set[KiPower]): Boolean =
//      (level >= 7) &&
//        super.canSelect(level, kiPowers)
//  }
//  case object QuiveringPalm extends KiPower {
//    override def description: String =
//      """Once a week the Monk may designate they are using the
//        |quivering palm. If the attack hits, the Monk is able impart
//        |vibrations in the target. For the next week, at any time the
//        |Monk may simply will it and the target will instantly die.
//        |The target does get a save vs. Death Ray to avoid death.
//        |If the target makes the save or the week passes, the
//        |vibrations cease and the target is no longer in danger,
//        |though the target could be affected later by a separate
//        |attack. This ability only affects living creatures. Must be at
//        |least 15th level.
//        |""".stripMargin
//
//    override def name: String = "Quivering Palm"
//
//    override def canSelect(level: Int, kiPowers: Set[KiPower]): Boolean =
//      (level >= 15) &&
//        super.canSelect(level, kiPowers)
//  }
//  case object SilverFist extends KiPower {
//    override def description: String =
//      """The Monk’s unarmed attacks may count as any kind of
//        |special material (e.g. silver).""".stripMargin
//
//    override def name: String = "Silver Fist"
//  }
//  case object SlowFall extends KiPower {
//    override def description: String =
//    """As long as a Monk is within 10’ of a vertical surface while
//      |falling, they are treated as if under the effects of a feather
//      |fall spell.
//      |""".stripMargin
//
//    override def name: String = "Slow Fall"
//  }
//  case object StunningFist extends KiPower {
//    override def description: String =
//      """Once per day, the Monk may declare an unarmed attack
//        |to be a stunning fist. If the attack succeeds, in addition to
//        |doing damage, the target must save vs. Petrify or be
//        |stunned. If the target is stunned, then for one round he or
//        |she can make no action, must drop whatever he or she is
//        |holding, take a -2 AC bonus, and lose their dexterity
//        |bonus to AC. This only affects living creatures. The Monk
//        |gains an additional use at 7th level and every four levels
//        |after that, to a maximum of five uses at 19th level.
//        |""".stripMargin
//    override def name: String = "StunningFist"
//  }
//  case object TimelessBody extends KiPower {
//    override def description: String =
//      """The Monk gains harmony between their mind and body
//        |and stops aging. At some point the mind will still give out,
//        |but the body no longer shows the effects of further aging.
//        |Must be at least 15th level""".stripMargin
//
//    override def name: String = "Timeless Body"
//
//    override def canSelect(level: Int, kiPowers: Set[KiPower]): Boolean =
//      (level >= 15) &&
//        super.canSelect(level, kiPowers)
//
//    override def canUseWhenEncumbered: Boolean = true
//  }
//  case object TongueOfTheStars extends KiPower {
//    override def description: String =
//      """The Monk may speak with any living creature for one
//        |turn. This may be used twice a day. At 19th level a third
//        |use is gained. Must be at least 11th level.""".stripMargin
//
//    override def name: String = "Tongue of the Stars"
//
//    override def canSelect(level: Int, kiPowers: Set[KiPower]): Boolean =
//      (level >= 11) &&
//        super.canSelect(level, kiPowers)
//
//    override def canUseWhenEncumbered: Boolean = true
//  }
//  case object WholenessOfBody extends KiPower {
//    override def description: String =
//      """The Monk may heal twice his or her level in hit points per
//        |day. This can be done once, or spread out over multiple
//        |applications. Must be at least 7th level.
//        |""".stripMargin
//
//    override def name: String = "Wholeness of Body"
//
//    override def canSelect(level: Int, kiPowers: Set[KiPower]): Boolean =
//      (level >= 7) &&
//        super.canSelect(level, kiPowers)
//
//    override def canUseWhenEncumbered: Boolean = true
//  }
//
//  val numPowersPerLevel: Map[Int, Int] = Map(
//    1 -> 0,
//    2 -> 0,
//    3 -> 2,
//    4 -> 2,
//    5 -> 3,
//    6 -> 3,
//    7 -> 4,
//    8 -> 4,
//    9 -> 5,
//    10 -> 5,
//    11 -> 6,
//    12 -> 6,
//    13 -> 7,
//    14 -> 7,
//    15 -> 8,
//    16 -> 8,
//    17 -> 9,
//    18 -> 9,
//    19 -> 10,
//    20 -> 10,
//  )
//
//  val kiPowers: Map[Int, KiPower] = Map(
//    1 -> AbundantStep,
//    2 -> BurstingFist,
//    3 -> DeflectArrows,
//    4 -> DiamondBody,
//    5 -> DiamondFist,
//    6 -> Evasion,
//    7 -> FeatherBalance,
//    8 -> FeatherStep,
//    9 -> FlawlessMind,
//    10 -> ImprovedEvasion,
//    11 -> KiStrike,
//    12 -> PunishingStrike,
//    13 -> PurityOfBody,
//    14 -> QuiveringPalm,
//    15 -> SilverFist,
//    16 -> SlowFall,
//    17 -> StunningFist,
//    18 -> TimelessBody,
//    19 -> TongueOfTheStars,
//    20 -> WholenessOfBody
//  )
//
//  private def getKiPowersHelper(num: Int, level: Int, acc: Set[KiPower]): Set[KiPower] = {
//    if (num <= 0) {
//      acc
//    }
//    else {
//      val newPower: KiPower = Roller.getRandomData(kiPowers)
//      if (newPower.canSelect(level, acc))
//        getKiPowersHelper(num - 1, level, acc + newPower)
//      else
//        getKiPowersHelper(num, level, acc)
//    }
//  }
//
//  def getKiPowers(monkLevel: Int): Set[KiPower] = {
//    val numPowers = numPowersPerLevel(monkLevel)
//    getKiPowersHelper(numPowers, monkLevel, Set.empty)
//  }
//
//}
