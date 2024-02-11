package basic.fantasy.backgrounds

import basic.fantasy.Roller
import basic.fantasy.Roller.getRandomData
import basic.fantasy.backgrounds.Races._
import play.api.libs.json.{Json, Writes}

object BackgroundGenerator {

  case class Background(
   birthOrder: String,
   parentsOccupation: String,
   childHoodEvents: Set[String],
   adolescentEvents: Set[String]
 )

  def getBackground(): Background = {
    val numChildhoodEvents = Roller.randomInt(4)
    val numAdolescentEvents = Roller.randomInt(4)

    val childHoodEvents: Set[String] = (0 to numChildhoodEvents).map(_ => getChildhoodEvents()).toSet
    val adolescentEvents: Set[String] = (0 to numAdolescentEvents).map(_ => getYoungAdultEvents()).toSet

    Background(
      birthOrder = getBirthOrder(),
      parentsOccupation = getOccupation(),
      childHoodEvents = childHoodEvents,
      adolescentEvents = adolescentEvents
    )
  }

  def getLanguages(nativeLanguage: String, race: Races.Race, bonus: Int): Set[String] = {

    val bonusLanguageData: Seq[String] = Seq("Dwarvish", "Elvish", "Giant", "Gnomish", "Goblin", "Hinish", "Orcish", "Abyssal",
      "Celestial", "Deep Speech", "Draconic", "Infernal", "Primordial", "Sylvan")
    val bonusLanguages = (0 until bonus).map(_ => getRandomData(bonusLanguageData)).toSet

    race match {
      case Human => Set("Thyatian", nativeLanguage) ++ bonusLanguages
      case Elf => Set("Thyatian", "Elvish", nativeLanguage) ++ bonusLanguages
      case Dwarf => Set("Thyatian", "Dwarvish", nativeLanguage) ++ bonusLanguages
      case Halfling => Set("Thyatian", "Hinish", nativeLanguage) ++ bonusLanguages
      case HalfElf => Set("Thyatian", "Elvish", nativeLanguage) ++ bonusLanguages
      case HalfOrc => Set("Thyatian", "Goblin", nativeLanguage) ++ bonusLanguages
    }
  }



  //Chart 1. Birth Order
  def getBirthOrder(): String = {
    val birthOrders: Map[Int, String] = Map(
      1 -> "First born",
      2 -> "First born",
      3 -> "First born",
      4 -> "Second child",
      5 -> "Second child",
      6 -> "Second child",
      7 -> "Second child",
      8 -> "Third child",
      9 -> "Third child",
      10 -> "Third child",
      11 -> "Fourth child",
      12 -> "Fourth child",
      13 -> "Fifth child",
      14 -> "Sixth child",
      15 -> "Seventh child",
      16 -> "Eighth or more"
    )

    getRandomData(birthOrders)
  }

  //Chart 2: Parent Occupation
  def getOccupation(): String = {

    val occupations: Map[Int, String] = Map(
      1 -> "Beggar",
      2 -> "Drifter",
      3 -> "Criminal",
      4 -> "Peasant",
      5 -> "Farm worker",
      6 -> "Farmer",
      7 -> "Fisherman",
      8 -> "Miner",
      9 -> "Forester",
      10 -> "Sailor",
      11 -> "Soldier",
      12 -> "Mercenary",
      13 -> getCrafts(),
      14 -> getCrafts(),
      15 -> "Sage",
      16 -> "Scholar",
      17 -> "Alchemist",
      18 -> "Scribe",
      19 -> "Slaver",
      20 -> "Adventurer",
      21 -> "Actor",
      22 -> "Bard",
      23 -> "Courtesan",
      24 -> getGovernmentJob(),
      25 -> getMerchantJob(),
      26 -> getMerchantJob(),
      27 -> getClergyJob(),
      28 -> "Genteel",
      29 -> getNobility(),
      30 -> getCrafts(),
      31 -> getCrafts(),
      32 -> getCrafts(),
      33 -> getCrafts(),
      34 -> "Physician",
      35 -> "Interpreter",
      36 -> "Assassin",
      37 -> "Writer",
      38 -> "Astronomer",
      39 -> "Lothario",
      40 -> "Orator",
      41 -> "Dream Interpreter",
      42 -> "Naturalist",
      43 -> "Vinter",
      44 -> "Gambler",
      45 -> "Astrologer",
      46 -> "Herbalist",
      47 -> "Smith",
      48 -> "Woodsman",
      49 -> "Tinker",
      50 -> "Carpenter",
      51 -> "Mason",
    )
    getRandomData(occupations)
  }

  //Chart 2A: Craft
  def getCrafts(): String = {
    val crafts: Map[Int, String] = Map(
      1 -> "Tailor",
      2 -> "Fletcher",
      3 -> "Bowyer",
      4 -> "Glass Blower",
      5 -> "Carpenter",
      6 -> "Animal trainer",
      7 -> "Beast master",
      7 -> "Cartographer",
      8 -> "Smith",
      9 -> "Cobbler",
      10 -> "Weaver",
      11 -> "Armorer",
      12 -> "Brewer",
      13 -> "Mason",
      14 -> "Potter",
      15 -> "Miller",
      16 -> "Dyer",
      17 -> "Shipwright",
      18 -> "Jeweler",
      19 -> "Artist",
      20 -> "Sculptor",
      21 -> "Musician",
      22 -> "Baker",
      23 -> "Weaponsmith",
    )
    getRandomData(crafts)
  }

  //Chart 2B: Government Officials
  def getGovernmentJob(): String = {
    val governmentJobs = Map(
      1 -> "Tax collector",
      2 -> "Tex collector",
      3 -> "Sheriff",
      4 -> "Forest warden",
      5 -> "Magistrate",
      6 -> "Town mayor",
      7 -> "City mayor",
      8 -> "Ducal advisor",
      9 -> "Royal advisor",
      10 -> "Spytracker",
      11 -> "Magistrate",
      12 -> "Diplomat",
    )
    getRandomData(governmentJobs)
  }

  //Chart 2C: Merchant
  def getMerchantJob(): String = {
    val merchantJobs = Map(
      1 -> "Shopkeeper, foodstuffs",
      2 -> "Shopkeeper, dry goods",
      3 -> "Shopkeeper, exotic goods",
      4 -> "Innkeeper",
      5 -> "Local trader",
      6 -> "Caravan trader",
      7 -> "Warehouse owner"
    )
    getRandomData(merchantJobs)
  }

  //Chart 2D: Clergy
  def getClergyJob(): String = {
    val clergyJobs = Map(
      1 -> "Parish Priest (mainstream religion)",
      2 -> "Parish Priest (mainstream religion)",
      3 -> "Upper clergy (mainstream religion)",
      4 -> "Priest (heretic religion)",
      5 -> "Acolyte (heretic religion)",
      6 -> "Acolyte (mainstream  religion)",
      7 -> "Druid",
      8 -> "Priest of an Elder god",
      9 -> "Shaman"
    )
    getRandomData(clergyJobs)
  }

  //Chart 2E: Nobility
  def getNobility(): String = {
    val nobility = Map(
      1 -> "Landless Knight",
      2 -> "Landless Knight",
      3 -> "Landless Knight",
      4 -> "Knight Banneret",
      5 -> "Knight Banneret",
      6 -> "Knight",
      7 -> "Knight",
      8 -> "Knight",
      9 -> "Knight",
      10 -> "Knight",
      11 -> "Earl",
      12 -> "Earl",
      13 -> "Baron",
      14 -> "Baron",
      15 -> "Baron",
      16 -> "Count",
      17 -> "Marquis",
      18 -> "Duke",
      19 -> "Arch Duke",
      20 -> "Prince",
      21 -> "King"
    )
    getRandomData(nobility)
  }

  //Chart 3: Significant Events in Childhood and Adolescence
  def getChildhoodEvents(): String = {
    val events: Map[Int, String] = Map(
      1 -> "Loved by parents",
      2 -> "Protected by parents",
      3 -> "Unloved by parents",
      4 -> "Spurned by parents",
      5 -> "Jealous sibling",
      6 -> "Sibling rivalry",
      7 -> "Lived a nomadic life",
      8 -> "Moved to the big city",
      9 -> "Moved to the borderlands",
      10 -> "Moved to the wilderness",
      11 -> "Ran away from home",
      12 -> "Learned to use a weapon",
      13 -> s"${getGuardians()}", //Adopted
      14 -> s"Family Member killed by a ${getOther()}",
      15 -> s"Caused the death of: ${getRelative()}",
      16 -> s"Caused the death of: ${getOther()}",
      17 -> s"Illegitimate: ${getGuardians()}",
      18 -> "Apprenticed in parent's occupation",
      19 -> s"Apprenticed in: ${getOccupation()}",
      20 -> "Parent killed by a relative",
      21 -> s"Parent outlawed for: ${getCrime()}",
      22 -> s"Religious experience: ${getReligiousExperience()}",
      23 -> s"Magical occurrence: ${getMagicalOccurrence()}",
      24 -> s"Suspected of a crime: ${getCrime()}",
      25 -> "Had a few close friends",
      26 -> "Did something that brought shame to family",
      26 -> "Did something that brought pride to family",

    )
    getRandomData(events)
  }

  // Chart 3A: Guardians
  def getGuardians(): String = {
    val guardians: Map[Int, String] = Map(
      1 -> "Raised by a wicked step-father",
      2 -> "Raised by a cruel step-father",
      3 -> "Raised by a wicked step-mother",
      4 -> "Raised by a cruel step-mother",
      5 -> "Raised by a hedge Wizard",
      6 -> "Raised in a monastery or convent",
      7 -> s"Raised by: ${getCrafts()}",
      8 -> s"Raised by: ${getRelative()}",
      9 -> "Sold into slavery",
      10 -> "Raised by orcs",
      11 -> "Raised by an Adventurer",
      12 -> "Raised by Dwarven indentured servants",
      13 -> "Raised by mysterious red-robed monks",
      14 -> "Raised by centaurs",
      15 -> "Raised by mercenaries",
      16 -> "Raised by Bandits",
      17 -> "Adopted by Nomads",
      18 -> s"Adopted by: ${getMerchantJob()}",
      19 -> s"Adopted by: ${getClergyJob()}",
      20 -> s"Adopted by: ${getNobility()}",
      21 -> "Lived on the streets/no guardian",
      22 -> "Lived on the streets/no guardian",
      23 -> "Lived on the streets/no guardian",
      24 -> "Raised by Cultists",
      25 -> "Raised by Hobgoblins",
      26 -> "Raised by Deep Ones",
      27 -> "Raised by Elves",
      28 -> "Raised by Pirates",
      29 -> "Raised by barbarians",
    )
    getRandomData(guardians)
  }

  //Chart 3B: Relatives
  def getRelative(): String = {
    val relatives = Map(
      1 -> "Brother",
      2 -> "Sister",
      3 -> "First Cousin",
      4 -> "Uncle",
      5 -> "Aunt",
      6 -> "Grandfather",
      7 -> "Grandmother",
      8 -> "Great Uncle",
      9 -> "Great Aunt",
      10 -> "Distant relation"
    )

    getRandomData(relatives)
  }

  //Chart 3C: Others
  def getOther(): String = {
    val others: Map[Int, String] = Map(
      1 -> "Friend",
      2 -> "Thief",
      3 -> "Wizard",
      4 -> "Mentor",
      5 -> "Raider",
      6 -> "Orcs",
      7 -> "Demon",
      8 -> "Lover",
      9 -> "Highwayman",
      10 -> "Adventurer",
      11 -> "Comrade",
      12 -> "Wild animal",
      13 -> "Nomad",
      14 -> "Religious sect member/leader",
      15 -> "Mysterious stranger",
      16 -> "Invader",
      17 -> "Bandit",
      18 -> "Pirate",
      19 -> s"${getGovernmentJob()}",
      20 -> s"${getCrafts()}",
      21 -> s"${getNobility()}",
    )
    getRandomData(others)
  }

  //Chart 3D: Crimes and Misdemeanors
  def getCrime(): String = {
    val crimes: Map[Int, String] = Map(
      1 -> "Theft",
      2 -> "Theft",
      3 -> "Assault",
      4 -> "Heresy",
      5 -> "Heresy",
      6 -> "Murder",
      7 -> "Insulting a noble",
      8 -> "Trespassing",
      9 -> "Treason",
      10 -> "Tax evasion",
      11 -> "Political dissidence",
      12 -> "Political dissidence",
      13 -> "Harboring criminals",
      14 -> "Unlawful sorcery",
      15 -> "Banditry",
      16 -> "Piracy",
      17 -> "Crime they didn't commit",
      18 -> "Crime they didn't commit",
    )
    getRandomData(crimes)
  }

  //Chart 4: Significant Events in Young Adulthood
  def getYoungAdultEvents(): String = {
    val events: Map[Int, String] = Map(
      1 -> "Survived plague",
      2 -> "Moved to big city",
      3 -> "Moved to borderlands",
      4 -> "Moved to wilderness",
      5 -> "Sold into slavery (escaped)",
      6 -> "Encountered monster",
      7 -> "Served wealthy patron",
      8 -> "Served noble court",
      9 -> "Traveled abroad",
      10 -> s"Religious experience: ${getReligiousExperience()}",
      11 -> s"Magical occurrence: ${getMagicalOccurrence()}",
      12 -> s"Caused the death of: ${getRelative()}",
      13 -> s"Developed virtue: ${getVirtue()}",
      14 -> s"Developed vice: ${getVice()}",
      15 -> s"Conscripted for military service: ${getMilitaryService()}",
      16 -> s"Volunteered for military service: ${getMilitaryService()}",
      17 -> {
        if (Roller.randomInt(100) > 75) "Romantic affair" else "Had a Child"
      },
      18 -> s"Learned occupation: ${getOccupation()}",
      19 -> s"Suspected of crime: ${getCrime()}",
      20 -> s"Home village/town wiped out by: ${getOther()}",
      21 -> s"Saved life of: ${getRelative()}",
      22 -> s"Saved life of: ${getOther()}",
      23 -> s"Apprenticed to: ${getCrafts()}"
    )
    getRandomData(events)
  }

  //Chart 4A: Military Service
  def getMilitaryService(): String = {
    val militaryService: Map[Int, String] = Map(
      1 -> "Promoted",
      2 -> "Demoted",
      3 -> "Lone survivor of unit",
      4 -> "Captured by enemy and tortured",
      5 -> "Deserted",
      6 -> "Joined mercenaries",
      7 -> "Responsible for the deaths of comrades",
      8 -> "Best friend killed at your side",
      9 -> "Prevented the destruction of innocents",
      10 -> s"Spent most time in “rear echelon” non-combat ${otherService()}",
      11 -> s"Committed an unsanctioned crime ${getCrime()}",
      12 -> "Ran away from battle",
      13 -> "Displayed heroism on the battlefield",
      14 -> "Learned use of exotic weapons",
      15 -> "Learned siege craft",
      16 -> "Led a mutiny",
      17 -> "Survived disease",
      18 -> s"magical occurrence: ${getMagicalOccurrence()}",
      19 -> s"Developed virtue: ${getVirtue()}",
      20 -> s"Developed vice: ${getVice()}",
      21 -> s"Transferred to other service: ${otherService()}",
    )
    getRandomData(militaryService)
  }

  //Chart 4B: Other Service
  def otherService(): String = {
    val otherService: Map[Int, String] = Map(
      1 -> "Palace guard",
      2 -> "City guard/watch",
      3 -> "Temple guard",
      4 -> "Local militia",
      5 -> "Rangers",
      6 -> "Private bodyguard",
      7 -> "Engineer",
      8 -> "Sapper",
      9 -> "Scouts",
      10 -> "Navy",
      11 -> "Shipboard marine",
      12 -> "Messenger",
      13 -> "Caravan guard",
      14 -> "Border guard",
    )
    getRandomData(otherService)
  }

  //Chart 4C: Virtues
  def getVirtue(): String = {
    val virtues: Map[Int, String] = Map(
      1 -> "Cleanliness",
      2 -> "Benefactor for the poor",
      3 -> "Well-mannered",
      4 -> "Friendly",
      5 -> "Teetotaler",
      6 -> "Pious",
      7 -> "Sincere/earnest",
      8 -> "Quiet/good listener",
      9 -> "Honest",
      10 -> "Defender of the oppressed",
      11 -> "Loving",
      12 -> "Tolerant of all faiths",
      13 -> "Self-confident",
      14 -> "Hard-working",
      15 -> "Humble",
      16 -> "Good negotiator",
      17 -> "diplomatic",
      18 -> "Hard bargainer",
      19 -> "Punctual",
      20 -> "Sensitive/tender",
      21 -> "Gregarious"
    )

    getRandomData(virtues)

  }

  //Chart 4D: Vices
  def getVice(): String = {
    val vices: Map[Int, String] = Map(
      1 -> "Heavy drinker",
      2 -> "Drug problem",
      3 -> "Gambler",
      4 -> "Randiness",
      5 -> "Swears like a sailor",
      6 -> "Duplicitous",
      7 -> "Mistrustful",
      8 -> "Loner",
      9 -> "Pushy",
      10 -> "Loud",
      11 -> "Poor Hygiene",
      12 -> "Loves brawling",
      13 -> "Quick-tempered",
      14 -> "Selfish",
      15 -> "Braggart",
      16 -> "Lazy",
      17 -> "Greedy",
      18 -> "Intolerant",
      19 -> "Lacks self-confidence",
      20 -> "Sacrilegious"
    )
    getRandomData(vices)
  }

  //Chart 4E: Religious Experience
  def getReligiousExperience(): String = {
    val religiousExperiences: Map[Int, String] = Map(
      1 -> "Joined faith",
      2 -> "Lost faith",
      3 -> "Vision of demi-god",
      4 -> "Vision of a saint",
      5 -> "Vision of a deity",
      6 -> "Vision of a demon",
      7 -> "Vision of an elder god",
      8 -> "Became lay clergy",
      9 -> "Pilgrimage to holy place",
      10 -> "Excommunicated",
      11 -> "Persecuted for faith",
      12 -> "Involved in holy war",
      13 -> "Became religious hypocrite",
      14 -> "Made prophetic statement",
      15 -> "Discredited faith",
      16 -> "Sent to religious school",
      17 -> "Started own sect",
      18 -> s"Developed a virtue ${getVirtue()}",
      19 -> s"Developed a virtue ${getVirtue()}",
      20 -> s"Developed a vice ${getVice()}",
      21 -> s"Developed a vice ${getVice()}",
      22 -> "Witnessed a minor miracle"
    )

    getRandomData(religiousExperiences)
  }

  //Chart 4F: Magical Occurrence
  def getMagicalOccurrence(): String = {
    val magicalOccurrences: Map[Int, String] = Map(
      1 -> "Survived magical disaster",
      2 -> "Witnessed a summoning",
      3 -> "Saw magical omens",
      4 -> "Visited by a witch",
      5 -> "Gathered spell components for hedge wizard",
      6 -> "Found magical place",
      7 -> "Found arcane scrolls",
      8 -> "Discovered ancient book",
      9 -> "Learned a cantrip"
    )

    getRandomData(magicalOccurrences)
  }

  //Chart 5: Nationality
  def getNationality(race: Race): Nationality = {
    val random = Roller.randomInt(100)

    race match {
      case Human =>
        if (random < 15)
          Karameikos
        else if (random < 20)
          Ylaruam
        else if (random < 30)
          Glantri
        else if (random < 35)
          Ierendi
        else if (random < 36)
          Alfheim
        else if (random < 37)
          Rockhome
        else if (random < 43)
          NorthernReaches
        else if (random < 44)
          FiveShires
        else if (random < 49)
          Minrothad
        else if (random < 50)
          Thar
        else if (random < 65)
          Darokin
        else if (random < 70)
          Ethengar
        else if (random < 71)
          ShadowLands
        else if (random < 76)
          Altruaghin
        else if (random < 85)
          Alphatia
        else if (random < 95)
          Thyatis
        else
          Wildlands
      case Elf =>
        if (random < 2)
          Karameikos
        else if (random < 4)
          Ylaruam
        else if (random < 14)
          Glantri
        else if (random < 15)
          Ierendi
        else if (random < 65)
          Alfheim
        else if (random < 66)
          Rockhome
        else if (random < 67)
          NorthernReaches
        else if (random < 68)
          FiveShires
        else if (random < 69)
          Minrothad
        else if (random < 70)
          Thar
        else if (random < 75)
          Darokin
        else if (random < 71)
          Ethengar
        else if (random < 80)
          ShadowLands
        else if (random < 81)
          Altruaghin
        else if (random < 84)
          Alphatia
        else if (random < 90)
          Thyatis
        else
          Wildlands
      case Dwarf =>
        if (random < 2)
          Karameikos
        else if (random < 4)
          Ylaruam
        else if (random < 5)
          Glantri
        else if (random < 15)
          Ierendi
        else if (random < 16)
          Alfheim
        else if (random < 66)
          Rockhome
        else if (random < 67)
          NorthernReaches
        else if (random < 68)
          FiveShires
        else if (random < 69)
          Minrothad
        else if (random < 70)
          Thar
        else if (random < 75)
          Darokin
        else if (random < 71)
          Ethengar
        else if (random < 80)
          ShadowLands
        else if (random < 81)
          Altruaghin
        else if (random < 84)
          Alphatia
        else if (random < 90)
          Thyatis
        else
          Wildlands
      case Halfling => Darokin
        if (random < 2)
          Karameikos
        else if (random < 4)
          Ylaruam
        else if (random < 5)
          Glantri
        else if (random < 15)
          Ierendi
        else if (random < 16)
          Alfheim
        else if (random < 17)
          Rockhome
        else if (random < 18)
          NorthernReaches
        else if (random < 68)
          FiveShires
        else if (random < 69)
          Minrothad
        else if (random < 70)
          Thar
        else if (random < 75)
          Darokin
        else if (random < 71)
          Ethengar
        else if (random < 80)
          ShadowLands
        else if (random < 81)
          Altruaghin
        else if (random < 84)
          Alphatia
        else if (random < 90)
          Thyatis
        else
          Wildlands
      case HalfElf =>
        if (random < 10)
          Karameikos
        else if (random < 15)
          Ylaruam
        else if (random < 30)
          Glantri
        else if (random < 35)
          Ierendi
        else if (random < 55)
          Alfheim
        else if (random < 56)
          Rockhome
        else if (random < 60)
          NorthernReaches
        else if (random < 61)
          FiveShires
        else if (random < 65)
          Minrothad
        else if (random < 66)
          Thar
        else if (random < 75)
          Darokin
        else if (random < 80)
          Ethengar
        else if (random < 90)
          ShadowLands
        else if (random < 91)
          Altruaghin
        else if (random < 93)
          Alphatia
        else if (random < 98)
          Thyatis
        else
          Wildlands

      case HalfOrc =>
        if (random < 5)
          Karameikos
        else if (random < 10)
          Ylaruam
        else if (random < 15)
          Glantri
        else if (random < 20)
          Ierendi
        else if (random < 21)
          Alfheim
        else if (random < 22)
          Rockhome
        else if (random < 27)
          NorthernReaches
        else if (random < 28)
          FiveShires
        else if (random < 33)
          Minrothad
        else if (random < 60)
          Thar
        else if (random < 65)
          Darokin
        else if (random < 70)
          Ethengar
        else if (random < 71)
          ShadowLands
        else if (random < 76)
          Altruaghin
        else if (random < 81)
          Alphatia
        else if (random < 86)
          Thyatis
        else
          Wildlands
    }
  }

  sealed trait Nationality {
    def niceString(): String = {
      this.toString.replace("()", "")
    }
    val nativeLanguage: String
  }

  case object Karameikos extends Nationality {
    override val nativeLanguage: String = "Thyatian"
  }

  case object Ylaruam extends Nationality {
    override val nativeLanguage: String = "Ylaruamian"
  }

  case object Glantri extends Nationality {
    override val nativeLanguage: String = "Thyatian"
  }

  case object Ierendi extends Nationality {
    override val nativeLanguage: String = "Ierendian"
  }

  case object Alfheim extends Nationality {
    override val nativeLanguage: String = "Elvish"
  }

  case object Rockhome extends Nationality {
    override val nativeLanguage: String = "Dwarvish"
  }

  case object NorthernReaches extends Nationality {
    override def toString: String = "Northern Reaches"
    override val nativeLanguage: String = "Nordic"
  }

  case object FiveShires extends Nationality {
    override def toString: String = "The Five Shires"
    override val nativeLanguage: String = "Hinish"
  }

  case object Minrothad extends Nationality {
    override val nativeLanguage: String = "Minrothadi"
  }

  case object Thar extends Nationality {
    override val nativeLanguage: String = "Goblin"
  }

  case object Darokin extends Nationality {
    override val nativeLanguage: String = "Darokian"
  }

  case object Ethengar extends Nationality {
    override val nativeLanguage: String = "Ethengari"
  }

  case object ShadowLands extends Nationality {
    override def toString: String = "Shadow Lands"
    override val nativeLanguage: String = "Elvish"
  }

  case object Altruaghin extends Nationality {
    override val nativeLanguage: String = "Altruaghini"
  }

  case object Alphatia extends Nationality {
    override val nativeLanguage: String = "Alphatian"
  }

  case object Thyatis extends Nationality {
    override val nativeLanguage: String = "Thyatian"
  }

  case object Wildlands extends Nationality {
    override val nativeLanguage: String = "Regional Dialect"
  }

  def nationalityFromString(nation: String): Nationality = nation match {
    case "Alfheim" => Alfheim
    case "Alphatia" => Alphatia
    case "Altruaghin" => Altruaghin
    case "Darokin" => Darokin
    case "Ethengar" => Ethengar
    case "Glantri" => Glantri
    case "Ierendi" => Ierendi
    case "Karameikos" => Karameikos
    case "Minrothad" => Minrothad
    case "Northern Reaches" => NorthernReaches
    case "Rockhome" => Rockhome
    case "Shadow Lands" => ShadowLands
    case "Thar" => Thar
    case "The Five Shires" => FiveShires
    case "Thyatis" => Thyatis
    case "Wildlands" => Wildlands
    case "Ylaruam" => Ylaruam
    case _ => Ylaruam //remove this!!!
  }

  implicit val nationalityWrites: Writes[Nationality] = Writes {
    case Karameikos => Json.obj("type" -> "Karameikos", "nativeLanguage" -> "Thyatian")
    case Ylaruam => Json.obj("type" -> "Ylaruam", "nativeLanguage" -> "Ylaruamian")
    case Glantri => Json.obj("type" -> "Glantri", "nativeLanguage" -> "Thyatian")
    case Ierendi => Json.obj("type" -> "Ierendi", "nativeLanguage" -> "Ierendian")
    case Alfheim => Json.obj("type" -> "Alfheim", "nativeLanguage" -> "Elvish")
    case Rockhome => Json.obj("type" -> "Rockhome", "nativeLanguage" -> "Dwarvish")
    case NorthernReaches => Json.obj("type" -> "Northern Reaches", "nativeLanguage" -> "Nordic")
    case FiveShires => Json.obj("type" -> "The Five Shires", "nativeLanguage" -> "Hinish")
    case Minrothad => Json.obj("type" -> "Minrothad", "nativeLanguage" -> "Minrothadi")
    case Thar => Json.obj("type" -> "Thar", "nativeLanguage" -> "Goblin")
    case Darokin => Json.obj("type" -> "Darokin", "nativeLanguage" -> "Darokian")
    case Ethengar => Json.obj("type" -> "Ethengar", "nativeLanguage" -> "Ethengari")
    case ShadowLands => Json.obj("type" -> "Shadow Lands", "nativeLanguage" -> "Elvish")
    case Altruaghin => Json.obj("type" -> "Altruaghin", "nativeLanguage" -> "Altruaghini")
    case Alphatia => Json.obj("type" -> "Alphatia", "nativeLanguage" -> "Alphatian")
    case Thyatis => Json.obj("type" -> "Thyatis", "nativeLanguage" -> "Thyatian")
    case Wildlands => Json.obj("type" -> "Wildlands", "nativeLanguage" -> "Regional Dialect")
  }
  implicit val backgroundWrites: Writes[Background] = Json.writes[Background]

}
