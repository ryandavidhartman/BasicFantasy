package basic.fantasy.backgrounds

object Spells {

  sealed abstract class Spell(
    val name: String,
    val range: String,
    val duration: String,
    val reverseSpell: Option[String] = None
  )

  case object AnimateDead extends Spell("Animate Dead", "touch", "special")
  case object AnimateObjects extends Spell("Animate Objects", "100' + 10'/level", "1 round/level")
  case object AntiMagicShell extends Spell("Anti-Magic Shell", "10'", "1 turn/level")
  case object BladeBarrier extends Spell("Blade Barrier", "90'", "1 round/level")
  case object Bless extends Spell("Bless", "50' radius", "1 minute/level", Some("Bane"))
  case object Bane extends Spell("Bane", "50' radius", "1 minute/level", Some("Bless"))
  case object CharmAnimal extends Spell("Charm Animal", "60'", "level + 1d4 rounds")
  case object CharmMonster extends Spell("Charm Monster", "30'", "special")
  case object CharmPerson extends Spell("Charm Person", "30'", "special")
  case object Clairvoyance extends Spell("Clairvoyance", "60'", "12 turns")
  case object CloudKill extends Spell("Cloud Kill", "100' + 10'/level", "6 rounds/level")
  case object Commune extends Spell("Commune", "range", "1 round/level")
  case object Confusion extends Spell("Confusion", "360'", "2 rounds + 1 round/level")
  case object ConjureElemental extends Spell("Conjure Elemental", "240'", "special")
  case object ContinualLight extends Spell("Continual Light", "360'", "1 year/level", Some("Continual Darkness"))
  case object ContinualDarkness extends Spell("Continual Light", "360'", "1 year/level", Some("Continual Light"))
  case object CreateFood extends Spell("Create Food", "10'", "permanent")
  case object CreateWater extends Spell("Create Water", "10'", "permanent")
  case object CureBlindness extends Spell("Cure Blindness", "touch", "instantaneous")
  case object CureDisease extends Spell("Cure Disease", "touch", "instantaneous", Some("Cause Disease"))
  case object CauseDisease extends Spell("Cause Disease", "touch", "instantaneous", Some("Cure Disease"))
  case object CureLightWounds extends Spell("Cure Light Wounds", "touch", "instantaneous", Some("Cause Light Wounds"))
  case object CauseLightWounds extends Spell("Cause Light Wounds", "touch", "instantaneous", Some("Cure Light Wounds"))
  case object CureSeriousWounds extends Spell("Cure Serious Wounds", "touch", "instantaneous", Some("Cause Serious Wounds"))
  case object CauseSeriousWounds extends Spell("Cause Serious Wounds", "touch", "instantaneous", Some("Cure Serious Wounds"))
  case object DarkVision extends Spell("Dark Vision", "touch", "1 hour/level")
  case object DeathSpell extends Spell("Death Spell", "240'", "instantaneous")
  case object DetectEvil extends Spell("Detect Evil", "60'", "1 round/level", Some("Detect Good"))
  case object DetectGood extends Spell("Detect Good", "60'", "1 round/level", Some("Detect Evil"))
  case object DetectInvisible extends Spell("Detect Invisible", "60'", "1 turn/level")
  case object DetectMagic extends Spell("Detect Magic", "60'", "2 turns")
  case object DimensionDoor extends Spell("Dimension Door", "10'", "instantaneous")
  case object Disintegrate extends Spell("Disintegrate", "60'", "instantaneous")
  case object DispelEvil extends Spell("Dispel Evil", "touch", "1 round/level")
  case object DispelMagic extends Spell("Dispel Magic", "120'", "instantaneous")
  case object ESP extends Spell("ESP", "60'", "1 turn/level")
  case object Feeblemind extends Spell("Feeblemind", "180'", "permanent")
  case object FindTraps extends Spell("Find Traps", "30'", "3 turns")
  case object FindThePath extends Spell("Find The Path", "touch", "1 turn/level")
  case object Fireball extends Spell("Fireball", "100' + 10'/level", "instantaneous")
  case object FleshToStone extends Spell("Flesh To Stone", "30'/level", "permanent", Some("Stone To Flesh"))
  case object StoneToFlesh extends Spell("Flesh To Stone", "30'/level", "permanent", Some("Flesh To Stone"))
  case object FloatingDisc extends Spell("Floating Disc", "0", "5 turns +1 turn/level")
  case object Fly extends Spell("Fly", "touch", "1 turn/level")
  case object Geas extends Spell("Geas", "5 foot/level", "special", Some("Remove Geas"))
  case object RemoveGeas extends Spell("Remove Geas", "5 foot/level", "special", Some("Geas"))
  case object GrowthOfAnimals extends Spell("Growth Of Animals", "60' + 10'/level", "1 turn/level")
  case object GrowthOfPlants extends Spell("Growth Of Plants", "120'", "permanent", Some("Shrink Plants"))
  case object ShrinkPlants extends Spell("Growth Of Plants", "120'", "permanent", Some("Growth Of Plants"))
  case object HallucinatoryTerrain extends Spell("Hallucinatory Terrain", "400' + 40'/level", "12 turn/level")
  case object Haste extends Spell("Haste", "30' + 20'/level", "1 round/level", Some("Slow"))
  case object Slow extends Spell("Slow", "30' + 20'/level", "1 round/level", Some("Haste"))
  case object Heal extends Spell("Heal", "touch", "permanent", Some("Harm"))
  case object Harm extends Spell("Harm", "touch", "permanent", Some("Heal"))
  case object HoldMonster extends Spell("Hold Monster", "180'", "2d8 turn")
  case object HoldPerson extends Spell("Hold Person", "180'", "2d8 turn")
  case object HoldPortal extends Spell("Hold Portal", "100' + 10'/level", "1 round/level")
  case object IceStorm extends Spell("Ice Storm", "300' + 30'/level", "1 round")
  case object InsectPlague extends Spell("Insect Plague","300' + 30'/level", "1 round")
  case object Invisibility extends Spell("Invisibility", "touch", "special")
  case object InvisibilityTenFootRadius extends Spell("Invisibility 10' Radius", "touch", "1 turn/level")
  case object InvisibleStalker extends Spell("Invisible Stalker", "0", "special")
  case object Knock extends Spell("Knock", "30'", "special")
  case object Levitate extends Spell("Levitate", "touch", "1 turn/level")
  case object Light extends Spell("Light", "120'", "6 turns + 1 turn/level", Some("Darkness"))
  case object Darkness extends Spell("Light", "120'", "6 turns + 1 turn/level", Some("Light"))
  case object LightningBolt extends Spell("Lightning Bolt", "50' + 10'/level", "instantaneous")
  case object LocateObject extends Spell("Locate Object", "360'", "1 round/level")
  case object LowerWater extends Spell("Lower Water", "360'", "1 turn/level")
  case object MagicJar extends Spell("Magic Jar", "60'", "special")
  case object MagicMissile extends Spell("Magic Missile", "100' + 10'/level", "instantaneous")
  case object MagicMouth extends Spell("Magic Mouth", "30'", "special")
  case object MassMorph extends Spell("Mass Morph", "100' + 10'/level", "1 hour/level")
  case object MirrorImage extends Spell("Mirror Image", "self", "1 turn/level")
  case object NeutralizePoison extends Spell("Neutralize Poison", "touch", "instantaneous", Some("Poison"))
  case object Poison extends Spell("Poison", "touch", "instantaneous", Some("Neutralize Poison"))
  case object PassWall extends Spell("Pass Wall", "30'", "3 turns")
  case object PhantasmalForce extends Spell("Phantasmal Force", "180'", "concentration")
  case object PolymorphOther extends Spell("Polymorph Other", "30'", "permanent")
  case object PolymorphSelf extends Spell("Polymorph Self", "self", "1 hour/level")
  case object ProjectedImage extends Spell("Projected Image", "240'", "6 turns")
  case object ProtectionFromEvil extends Spell("Protection From Evil", "touch", "1 turn/level", Some("Protection From Good"))
  case object ProtectionFromGood extends Spell("Protection From Good", "touch", "1 turn/level", Some("Protection From Evil"))
  case object ProtectionFromEvilTenFootRadius extends Spell("Protection From Evil 10' Radius", "touch", "1 turn/level", Some("Protection From Good Ten Foot Radius"))
  case object ProtectionFromGoodTenFootRadius extends Spell("Protection From Good 10' Radius", "touch", "1 turn/level", Some("Protection From Evil Ten Foot Radius"))
  case object ProtectionFromNormalMissiles extends Spell("Protection From Normal Missiles", "self", "1 turn/level")
  case object PurifyFoodAndWater extends Spell("Purify Food And Water", "10'", "instantaneous")
  case object Quest extends Spell("Quest", "5'/level", "special", Some("RemoveQuest"))
  case object RemoveQuest extends Spell("Remove Quest", "5'/level", "special", Some("Quest"))
  case object RaiseDead extends Spell("Raise Dead", "touch", "instantaneous", Some("Slay Living"))
  case object SlayLiving extends Spell("Slay Living", "touch", "instantaneous", Some("Raise Dead"))
  case object ReadLanguages extends Spell("Read Languages", "0", "special")
  case object ReadMagic extends Spell("Read Magic", "0", "permanent")
  case object Regenerate extends Spell("Regenerate", "touch", "permanent")
  case object Reincarnate extends Spell("Reincarnate", "touch", "instantaneous")
  case object RemoveCurse extends Spell("Remove Curse", "30'", "instantaneous", Some("BestowCurse"))
  case object BestowCurse extends Spell("Bestow Curse", "30'", "instantaneous", Some("Remove Curse"))
  case object RemoveFear extends Spell("Remove Fear", "touch", "instantaneous", Some("Cause Fear"))
  case object CauseFear extends Spell("Cause Fear", "120'", "2 turns", Some("Remove Fear"))
  case object ResistCold extends Spell("Resist Cold", "touch", "1 round/level")
  case object ResistFire extends Spell("Resist Fire", "touch", "1 round/level")
  case object Restoration extends Spell("Restoration", "touch", "permanent")
  case object Shield extends Spell("Shield", "self", "5 rounds + 1/level")
  case object SilenceFifteenFootRadius extends Spell("Silence 15' Radius", "360'", "2 round/level")
  case object Sleep extends Spell("Sleep", "90'", "5 round/level")
  case object SpeakWithAnimals extends Spell("Speak With Animals", "special", "1 turn/ 4 level")
  case object SpeakWithMonsters extends Spell("Speak With Monsters", "special", "1 turn/ 5 level")
  case object SpeakWithPlants extends Spell("Speak With Plants", "20'", "1 turn")
  case object SpeakWithDead extends Spell("Speak With Dead", "10'", "3 round/level")
  case object SpiritualHammer extends Spell("Spiritual Hammer", "30'", "1 round/level")
  case object SticksToSnakes extends Spell("Sticks To Snakes", "120'", "6 turns")
  case object Striking extends Spell("Striking", "touch", "1 round/level")
  case object Telekinesis extends Spell("Telekinesis", "self", "3 turns")
  case object Teleport extends Spell("Teleport", "self", "instantaneous")
  case object TrueSeeing extends Spell("True Seeing", "touch", "1 round/level")
  case object Ventriloquism extends Spell("Ventriloquism", "60'", "1 turn/level")
  case object WallOfFire extends Spell("Wall Of Fire", "180'", "1 round/level (or special)")
  case object WallOfIron extends Spell("Wall Of Iron", "90'", "permanent")
  case object WallOfStone extends Spell("Wall Of Stone", "15'", "permanent")
  case object WaterBreathing extends Spell("Water Breathing", "touch", "2 hours/levels")
  case object Web extends Spell("Web", "10'/level", "2 turn/levels")
  case object WizardEye extends Spell("Wizard Eye", "240'", "6 turns")
  case object WizardLock extends Spell("Wizard Lock", "20'", "permanent")
  case object WordOfRecall extends Spell("Word Of Recall", "self (special)", "instantaneous")


  val GoodClericSpells: Map[Int, Seq[Spell]] = Map(
    1 -> Seq(CureLightWounds, DetectEvil, DetectMagic, Light, Darkness, ProtectionFromEvil, PurifyFoodAndWater,
      RemoveFear, ResistCold),
    2 -> Seq(Bless, CharmAnimal, FindTraps, HoldPerson, ResistFire, SilenceFifteenFootRadius, SpeakWithAnimals,
      SpiritualHammer),
    3 -> Seq(ContinualLight, CureBlindness, CureDisease, GrowthOfAnimals, LocateObject, RemoveCurse, SpeakWithDead, Striking),
    4 -> Seq(CreateWater, CureSeriousWounds, DispelMagic, NeutralizePoison, ProtectionFromEvilTenFootRadius, SpeakWithPlants,
      SticksToSnakes),
    5 -> Seq(Commune, CreateFood, DispelEvil, InsectPlague, Quest, RemoveQuest, RaiseDead, TrueSeeing, WallOfFire),
    6 -> Seq(AnimateObjects, BladeBarrier, FindThePath, Heal, Regenerate, Restoration, SpeakWithMonsters, WordOfRecall)
  )

  val KnownClericSpells: Map[Int, Seq[Spell]] = Map(
    1 -> Seq(CureLightWounds, CauseLightWounds, DetectEvil, DetectGood, DetectMagic, Light, Darkness, ProtectionFromEvil,
      ProtectionFromGood, PurifyFoodAndWater,RemoveFear, CauseFear, ResistCold),
    2 -> Seq(Bless, Bane, CharmAnimal, FindTraps, HoldPerson, ResistFire, SilenceFifteenFootRadius, SpeakWithAnimals,
      SpiritualHammer),
    3 -> Seq(ContinualLight, ContinualDarkness, CureBlindness, CureDisease, CauseDisease, GrowthOfAnimals, LocateObject,
      RemoveCurse, BestowCurse, SpeakWithDead, Striking),
    4 -> Seq(AnimateDead, CreateWater, CureSeriousWounds, CauseSeriousWounds, DispelMagic, NeutralizePoison, Poison,
      ProtectionFromEvilTenFootRadius, ProtectionFromGoodTenFootRadius, SpeakWithPlants, SticksToSnakes),
    5 -> Seq(Commune, CreateFood, DispelEvil, InsectPlague, Quest, RemoveQuest, RaiseDead, SlayLiving, TrueSeeing, WallOfFire),
    6 -> Seq(AnimateObjects, BladeBarrier, FindThePath, Heal, Harm, Regenerate, Restoration, SpeakWithMonsters, WordOfRecall)
  )

  val EvilClericSpells: Map[Int, Seq[Spell]] = Map(
    1 -> Seq(CauseLightWounds, DetectGood, DetectMagic, Light, Darkness, ProtectionFromGood, CauseFear, ResistCold),
    2 -> Seq(Bane, CharmAnimal, FindTraps, HoldPerson, ResistFire, SilenceFifteenFootRadius, SpeakWithAnimals,
      SpiritualHammer),
    3 -> Seq(ContinualDarkness, CauseDisease, GrowthOfAnimals, LocateObject, BestowCurse, SpeakWithDead, Striking),
    4 -> Seq(AnimateDead, CauseSeriousWounds, DispelMagic, Poison, ProtectionFromGoodTenFootRadius, SpeakWithPlants, SticksToSnakes),
    5 -> Seq(Commune, CreateFood, InsectPlague, Quest, SlayLiving, TrueSeeing, WallOfFire),
    6 -> Seq(AnimateObjects, BladeBarrier, FindThePath, Harm, Regenerate, Restoration, SpeakWithMonsters, WordOfRecall)
  )

  val GoodMagicUserSpells: Map[Int, Seq[Spell]] = Map(
    1 -> Seq(CharmPerson, DetectMagic, HoldPerson, Light, MagicMissile, ProtectionFromEvil, Shield, Sleep),
    2 -> Seq(ContinualLight, DetectEvil, DetectInvisible, ESP, Invisibility, Knock, Levitate, MirrorImage, PhantasmalForce,
      Web),
    3 -> Seq(Clairvoyance, DarkVision, DispelMagic, Fireball, Fly, Haste, Slow, HoldPerson, InvisibilityTenFootRadius, LightningBolt,
      ProtectionFromEvilTenFootRadius, ProtectionFromNormalMissiles),
    4 -> Seq(CharmMonster, Confusion, DimensionDoor, GrowthOfPlants, ShrinkPlants, HallucinatoryTerrain, IceStorm, MassMorph,
      PolymorphOther, PolymorphSelf, RemoveCurse, WallOfFire, WizardEye),
    5 -> Seq(CloudKill, ConjureElemental, Feeblemind, HoldMonster, MagicJar, PassWall, Telekinesis, Teleport,
      WallOfStone),
    6 -> Seq(AntiMagicShell, DeathSpell, Disintegrate, FleshToStone, StoneToFlesh, Geas, InvisibleStalker,
      LowerWater, ProjectedImage, Reincarnate, WallOfIron)
  )

  val KnownMagicUserSpells: Map[Int, Seq[Spell]] = Map(
    1 -> Seq(CharmPerson, DetectMagic, FloatingDisc, HoldPerson, Light, Darkness, MagicMissile, MagicMouth, ProtectionFromEvil,
      ProtectionFromEvil, ReadLanguages, Shield, Sleep, Ventriloquism),
    2 -> Seq(ContinualLight, ContinualDarkness, DetectEvil, DetectGood, DetectInvisible, ESP, Invisibility, Knock, Levitate,
      LocateObject, MirrorImage, PhantasmalForce, Web, WizardLock),
    3 -> Seq(Clairvoyance, DarkVision, DispelMagic, Fireball, Fly, Haste, Slow, HoldPerson, InvisibilityTenFootRadius, LightningBolt,
      ProtectionFromEvilTenFootRadius, ProtectionFromGoodTenFootRadius, ProtectionFromNormalMissiles, WaterBreathing),
    4 -> Seq(CharmMonster, Confusion, DimensionDoor, GrowthOfPlants, ShrinkPlants, HallucinatoryTerrain, IceStorm, MassMorph,
      PolymorphOther, PolymorphSelf, RemoveCurse, BestowCurse, WallOfFire, WizardEye),
    5 -> Seq(AnimateDead, CloudKill, ConjureElemental, Feeblemind, HoldMonster, MagicJar, PassWall, Telekinesis, Teleport,
      WallOfStone),
    6 -> Seq(AntiMagicShell, DeathSpell, Disintegrate, FleshToStone, StoneToFlesh, Geas, RemoveGeas, InvisibleStalker,
      LowerWater, ProjectedImage, Reincarnate, WallOfIron)
  )

  val EvilMagicUserSpells: Map[Int, Seq[Spell]] = Map(
    1 -> Seq(CharmPerson, DetectMagic, HoldPerson, Darkness, MagicMissile, MagicMouth, ProtectionFromGood,
      Shield, Sleep),
    2 -> Seq(ContinualDarkness, DetectGood, DetectInvisible, Invisibility, Levitate, MirrorImage, PhantasmalForce, Web),
    3 -> Seq(Clairvoyance, DarkVision, DispelMagic, Fireball, Fly, Haste, Slow, HoldPerson, InvisibilityTenFootRadius,
      LightningBolt, ProtectionFromGoodTenFootRadius, ProtectionFromNormalMissiles, WaterBreathing),
    4 -> Seq(CharmMonster, Confusion, DimensionDoor, GrowthOfPlants, ShrinkPlants, HallucinatoryTerrain, IceStorm, MassMorph,
      PolymorphOther, BestowCurse, WallOfFire),
    5 -> Seq(AnimateDead, CloudKill, ConjureElemental, Feeblemind, HoldMonster, MagicJar, PassWall, Telekinesis, Teleport,
      WallOfStone),
    6 -> Seq(AntiMagicShell, DeathSpell, Disintegrate, FleshToStone, Geas, InvisibleStalker,
      LowerWater, ProjectedImage, Reincarnate, WallOfIron)
  )
}
