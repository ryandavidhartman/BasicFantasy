package basic.fantasy.backgrounds

import basic.fantasy.Roller
import basic.fantasy.backgrounds.Races._

object NameGenerator {

  def getName(race: Race, gender: String): String = race match {
    case Human => getHumanName(gender)
    case Elf => getElvenName(gender)
    case Dwarf => getDwarvenName(gender)
    case Halfling => getHalflingName(gender)
    case HalfElf => if (Roller.coinFLip()) getElvenName(gender) else getHumanName(gender)
    case HalfOrc => if (Roller.coinFLip()) getOrcishName(gender) else getHumanName(gender)
  }

  private def getHumanName(gender: String): String = {
    if (gender == "Male")
      s"$getHumanMaleFirstName $getHumanSurname"
    else
      s"$getHumanFemaleFirstName $getHumanSurname"
  }

  private def getElvenName(gender: String): String = {
    if (gender == "Male")
      s"$getElvenMaleFirstName $getElvenSurname"
    else
      s"$getElvenFemaleFirstName $getElvenSurname"
  }

  private def getDwarvenName(gender: String): String = {
    if (gender == "Male")
      s"$getDwarvenMaleFirstName $getDwarvenSurname"
    else
      s"$getDwarvenFemaleFirstName $getDwarvenSurname"
  }

  private def getHalflingName(gender: String): String = {
    if (gender == "Male")
      s"$getHalflingMaleFirstName $getHalflingSurname"
    else
      s"$getHalflingFemaleFirstName $getHalflingSurname"
  }

  private def getOrcishName(gender: String): String = {
    if (gender == "Male")
      getOrcishMaleName
    else
      getOrcishFemaleName
  }

  private def getOrcishMaleName: String = {
    val names: Seq[String] = Seq("Komamash", "Aguburk", "Garadash", "Sigur", "Gask", "Nidash", "Gnaezur", "Aggorash",
      "Duriz", "Morubash", "Brod", "Kelurk", "Tharotir", "Zusorimm", "Mugunar", "Haridim", "Korumash", "Lumubar",
      "Gromarsh")
    val count = names.length
    names(Roller.randomInt(count))
  }

  private def getOrcishFemaleName: String = {
    val names: Seq[String] = Seq("Karani", "Shayiri", "Rame", "Growar", "Fulozara", "Noshi", "Kutegum", "Rohkini",
      "Gynanir", "Gijesha", "Rashiz", "Sumimi", "Rohkuzura", "Sumarook", "Zonemira", "Kurogur", "Olanir", "Kurimi",
      "Kurid", "Sanani")
    val count = names.length
    names(Roller.randomInt(count))
  }

  private def getHumanSurname: String = {

    val surNames: Seq[String] = Seq("Hill", "Morton", "Hutchinson", "Hutchinson", "Brooks", "Montgomery", "Jensen",
      "Rogers", "Mccarthy", "O'Quinn", "Gardner", "Booth", "Pham", "Austin", "Jackson", "Day", "Ayala", "Page",
      "Vazquez", "Zang", "Rice", "Wolf", "Abbott", "Mcguire", "Aguilar", "Mckinney", "Henry", "Baker", "Ross", "Lowe",
      "Cole", "Arnold", "Pratt", "Rowe", "Cummings", "Frank", "Clark", "Clayton", "Wilson", "Hammond", "Byrne",
      "Sweeney", "Porter", "Aguirre", "Robles", "Beck", "Harvey", "Alvarez", "Medina", "Benson", "Franklin",
      "Bass", "Lloyd", "Allen", "Hughes", "Lyons", "Howell", "Flores", "Harris", "Brady", "Dominguez", "Young",
      "Tate", "Rivera", "Luna", "Rios", "Bird", "Becker", "Grant", "Jennings", "Bell", "Miller", "Stevens", "Mccoy",
      "English", "Williamson", "Lynch", "Gough", "Waters", "Newman", "Ingram", "Vaughn", "Williams", "Estrada",
      "Edwards", "Scott", "Griffiths", "Mack", "Solis", "Price", "Lambert", "Roberts", "Munoz", "Graham", "Garza",
      "Poole", "Dunn", "Wallace", "Peterson", "Pham", "Hawkins", "Finch", "Strickland", "Barrett", "Mohamed",
      "Cunningham", "Bailey", "Myers", "Spencer", "Romero", "Armstrong", "Austin", "O'Connor", "Peters", "Thomas",
      "Foster", "Carey", "Cruz", "Hamilton", "Love", "Keller", "Taylor", "Gibbs", "Cook", "Mckenna", "Williamson",
      "Garcia", "Flores", "Stanley", "Juarez", "Burns", "Mejia", "Stevenson", "Fletcher", "Palmer", "Alexander",
      "Jimenez", "Jones", "Bush", "Carlson", "Dean", "Atherton", "Mann", "Vasquez", "Wise", "Sherman", "Gibson",
      "Garrett", "Brown", "Waters", "Parsons", "Mcdonald", "Watson", "Osborne", "Thornton", "Harmon", "Park", "Wilkins",
      "Alvarado", "Hunt", "Carrillo", "Gardner", "Crossley", "Burgess", "Wood", "Field", "Abbott", "Khan", "Elliott",
      "Terry", "Leonard", "Allen", "Barnett", "Herbert", "Boyd", "Naylor", "Sims", "Joyce", "Sharp", "Patterson",
      "Nichols", "Thompson", "Moran", "Figueroa", "Graham", "Fuller", "Reed", "Horton", "Kaye", "Reynolds", "Rossi",
      "Nicholson", "Brady", "Black", "Gallegos")

    val count = surNames.length
    surNames(Roller.randomInt(count))
  }

  private def getHumanMaleFirstName: String = {

    val firstName: Seq[String] = Seq("Arkandor", "Aeryk", "Aubir", "Birker", "Coro", "Charn", "Dunni", "Dhenor",
      "Enlo", "Ferdirk", "Golian", "Ghörn", "Hold", "Ivor", "Jarum", "Kolm", "Kherano", "Laef", "Mannur", "Nixor",
      "O'mar", "Paulu", "Phork", "Quoro", "Redgar", "Särk", "Shönn", "Skold", "Thonas", "Thudgar", "Ulfvaer", "Villo",
      "Wilham", "Xärn", "Ymor", "Ziermo", "Årukr", "Äleks", "Öykir", "Alsar", "Aenir", "Asur", "Boris", "Birt", "Collun",
      "Dunhir", "Sighard", "Dharko", "Alric", "Fontur", "Fufnir", "Elkar", "Leiks", "Mervan", "Irminric", "Coenred",
      "Paetr", "Phlick", "Quaggi", "Rory", "Ronder", "Ruddar", "Caelin", "Saren", "Sturv", "Hislir", "Issolur",
      "Ulrekk", "Vigor", "Thorold", "Wulfer", "Xeron", "Yarlo", "Zorn", "Ilfarulv", "Jonnus", "Merlot", "Mimur",
      "Neolo", "Borker", "Oerkur", "Parus", "Pertus", "Rialter", "Raulfur", "Thul", "Tirul", "Timbul", "Sturv", "Särkor",
      "Sturmnir", "Solan", "Svert", "Ernoldus", "Albinus", "Ailmar", "Gredlan", "Gaufridus", "Turbert")

    val count = firstName.length
    firstName(Roller.randomInt(count))
  }

  private def getHumanFemaleFirstName: String = {

    val firstName: Seq[String] = Seq("Alonina", "Aena", "Aula", "Belea", "Celinda", "Chistri", "Dorah", "Dhunha",
      "Essai", "Flora", "Gunn", "Gharee", "Hejlaga", "Ingera", "Jess", "Kolmee", "Khira", "Lomma", "Moniské",
      "Nonna", "O'sarena", "Paladina", "Phera", "Quenn", "Reena", "Sunni", "Shaelah", "Skalda", "Tulla", "Thera",
      "Urvella", "Verona", "Wienna", "Xorone", "Ylfmar", "Zara", "Åsha", "Älta", "Öja", "Annalyn", "Bridonna",
      "Celestine", "Chasnera", "Desini", "Efari", "Fritha", "Gwenna", "Halette", "Ilenne", "Justaline", "Kassathra",
      "Khirta", "Lonna", "Maritt", "Nixi", "Orana", "Polona", "Phora", "Qannae", "Rohana", "Reya", "Ritta", "Selena",
      "Stina", "Stella", "Tressa", "Uneste", "Ulrekkana", "Vehlma", "Verkaa", "Wolkaee", "Xera", "Yenna", "Zelma",
      "Amara", "Ada", "Ani", "Aelli", "Alandra", "Belga", "Alba", "Branda", "Beasir", "Chelena", "Delia", "Dorah",
      "Debera", "Ellena", "Eiri", "Halaë", "Ilda", "Mirla", "Tembla", "Sinea", "Senessa", "Salana", "Sonna", "Stella",
      "Alta",
    )

    val count = firstName.length
    firstName(Roller.randomInt(count))
  }

  private def getElvenMaleFirstName: String = {
    val firstName = Seq("Abadda", "Abarat", "Adamar", "Adorellan", "Adresin", "Aduce", "Aelrindel", "Aerendyl",
      "Aermhar", "Aesar", "Aeson", "Afamrail", "Agis", "Aglanthol", "Ainésilver", "Aithlin", "Ajaar", "Akhelbhen",
      "Akkar", "Alabyran", "Albondiel", "Alinar", "Allain", "Alok", "Alosrin", "Althidon", "Amrynn", "Anarallath",
      "Andaerean", "Andrathath", "Anfalen", "Anlyth", "Aolis", "Aquilan", "Araevin", "Arandron", "Aravilar",
      "Arbane", "Ardreth", "Ardryll", "Argus", "Arkhun", "Arkiem", "Arl", "Arlen", "Arun", "Ascal", "Athtar", "Aubric",
      "Aubron", "Aulathar", "Aulauthar", "Aumanas", "Aumrauth", "Avourel", "Baerdelcoam", "Baerithryn", "Belanor",
      "Beldroth", "Bellas", "Belstram", "Beluar", "Bhyrindaar", "Biafyndar", "Bialaer", "Braern", "Brindarry",
      "Buttorwyr", "Cameron", "Chaalmyth", "Chathanglas", "Cheyrth", "Chozzaster", "Chylnoth", "Cluhurach",
      "Cluym", "Cohnal", "Conall", "Connak", "Cornaith", "Corym", "Cymbiir", "Cystenn", "Dakath", "Dalyor", "Dannyd",
      "Daratrine", "Darcassan", "Darfin", "Darthoridan", "Deldrach", "Delmuth", "Delsaran", "Dhoelath", "Divisav",
      "Drannor", "Droth", "Druindar", "Durlan", "Durothil", "Dyffros", "Earynspieir", "Edansyr", "Edicûve", "Edwyrd",
      "Edyrm", "Ehlark", "Ehrendil", "Eilauver", "Elaethan", "Elaith", "Elandorr", "Elanjar", "Elashor", "Elbauthin",
      "Elbereth", "Eldaernth", "Eldar", "Eldrin", "Elénaril", "Elenshaer", "Elephon", "Elhieardacil", "Elidyr",
      "Elion", "Elkhazel", "Ellisar", "Elorfindar", "Elorshin", "Elpaerae", "Eltaor", "Eltargrim", "Emmyth",
      "Entrydal", "Erendriel", "Erglareo", "Eriladar", "Erlan", "Erlathan", "Eroan", "Erolith", "Eschallus", "Estelar",
      "Ethlando", "Ettrian", "Euchoé", "Evindal", "Eyrynnhv", "Faahresc", "Faelar", "Faelyn", "Faeranduil", "Falael",
      "Faoraar", "Felaern", "Fenian", "Fflar", "Fhaornik", "Fhociin", "Filarion", "Filvendor", "Filverel", "Finufaranell",
      "Flardryn", "Flinar", "Foxfire", "Fylson", "Gaeleath", "Gaelin", "Galaeron", "Galan", "Galather", "Ganamede",
      "Gantar", "Garrik", "Garynnon", "Giilvas", "Giullio", "Glarald", "Glorandal", "Goronyyv", "Grathgor", "Haalaari",
      "Hachaam", "Haemir", "Haladavar", "Halafarin", "Halamar", "Haldreithen", "Halflar", "Halgondas", "Halpaeril",
      "Halueth", "Halueve", "Haryk", "Hastos", "Hatharal", "Hiflanyl", "Hoccar", "Horith", "Hubyr", "Iefyr", "Ievos",
      "Ihimbraskar", "Ilbryn", "Ilimitar", "Iliphar", "Ilitharath", "Illianaro", "Illithor", "Illitran", "Ilphas",
      "Ilrune", "Ilthuryn", "Ilyndrathyl", "Inchel", "Inialos", "Injros", "Intevar", "Iolas", "Iolrath", "Itham",
      "Ithraides", "Ivlisar", "Ivósaar", "Ivran", "Iymbryl", "Iyrandrar", "Iyriklaunavan", "Jandar", "Jannalor",
      "Jaonos", "Jassin", "Jhaan", "Jhaartael", "Jhaeros", "Jharak", "Jharym", "Jhuvik", "Jonas", "Jonik", "Jorildyn",
      "Josidiah", "Juppar", "Kahvoerm", "Kalaerede", "Katar", "Katyr", "Keletheryl", "Kelvhan", "Kendel", "Kerym",
      "Keryth", "Kesefehon", "Khaalindaan", "Kharis", "Khatar", "Khidell", "Khiiral", "Khilseith", "Khuumal", "Khyrmn",
      "Khyssoun", "Kindroth", "Kivessin", "Kiyuigh", "Klaern", "Kolvar", "Korrigash", "Kroloth", "Kuornos", "Kuskyn",
      "Kuskyn", "Kymil", "Kyrtaar", "Laeroth", "Lafarallin", "Lamruil", "Laosx", "Larongar", "Larrel", "Lashul",
      "Lathai", "Lathlaeril", "Leayonadas", "Leojym", "Lhombaerth", "Lhoris", "Lianthorn", "Llarm", "Llewellenar",
      "Llombaerth", "Lorsan", "Luirlan", "Luthais", "Luvon", "Lyari", "Lyklor", "Lysanthir", "Maasli", "Maendellyn",
      "Maeraddyth", "Maeral", "Maiele", "Malgath", "Mardeiym", "Marikoth", "Marlevaur", "Melandrach", "Melisander",
      "Merellien", "Merith", "Methild", "Mhaenal", "Mi’tilarro", "Mihangyl", "Miilaethorn", "Miirphys", "Mirthal",
      "Mirthal", "Mlartlar", "Mlossae", "Molonym", "Molostroi", "Montagor", "Morgan", "Morthil", "Mothrys", "Mourn",
      "Myrddin", "Myriil", "Myrin", "Myronthilar", "Mythanthar", "Naertho", "Naeryndam", "Naeryndam", "Napraeleon",
      "Narbeth", "Nardual", "Nelaeryn", "Nelaeryn", "Neldor", "Neldor", "Nesterin", "Nevarth", "Nhamashal", "Nieven",
      "Nindrol", "Ninthalor", "Nlossae", "Nopos", "Norlorn", "Nremyn", "Nuvian", "Nyaalsir", "Nylian", "Nym", "Nyvorlas",
      "Oacenth", "Oenel", "Ohmbryn", "Olaurae", "Olinsivver", "Olithir", "Onas", "Oncith", "Ondabrar", "Ondroth",
      "Onvyr", "Orist", "Orlpar", "Orndacil", "Ornthalas", "Ornthalas", "Ortauré", "Orym", "Oslarelar", "Otaehryn",
      "Otaerhyn", "Othorion", "Paeral", "Paeris", "Passilorris", "Paulorin", "Phaendar", "Pharom", "Phraan", "Pirphal",
      "Pleufan", "Purtham", "Pyrder", "Pyrravym", "Pywaln", "Pyxaanthal,", "Qildor", "Quastarte", "Quynn", "Raeranthur",
      "Raibyr", "Ralikanthae", "Ralnor", "Rathal", "Raunaeril", "Rauthomyr", "Rauvelore", "Reluraun", "Reluvethel",
      "Rennyn", "Reptar", "Respen", "Rhaac'var", "Rhalyf", "Rhangyl", "Rhenalyrr", "Rhespen", "Rhistel", "Rhothomir",
      "Rhys", "Rilitar", "Riluaneth", "Rolim", "Rotheloe", "Rothilion", "Ruardh", "Ruehar", "Ruith", "Rumathil",
      "Ruvaen", "Ruven", "Ruvyn", "Rychell", "Ryfon", "Ryul", "Ryvvik,", "Sadalymn", "Saelethil", "Saevel", "Saevel",
      "Sakrattars", "Sakrattars", "Samblar", "Sandevv", "Seanchai", "Seith", "Seiveril", "Selanlar", "Selgauth", "Shael",
      "Shammath", "Sharian", "Sharian", "Sharlario", "Shaundyl", "Shevarash", "Shonassir", "Shyrrik", "Siirist",
      "Silvyr", "Simimar", "Sinaht", "Sinaht", "Skalanis", "Skalanis", "Sontar", "Srindin", "Strohm", "Sundamar",
      "Sylvar", "Symkalr", "Sythaeryn", "Sythaeryn", "T’karon", "Taanyth", "Taegen", "Taeglyn", "Taenaran", "Taeral",
      "Taerntym", "Taleisin", "Tammson", "Tamnaeuth", "Tamnaeuth", "Tamsin", "Tanithil", "Tannivh", "Tannivh", "Tannyll",
      "Tanseril", "Tanyl", "Tanyl", "Taranath", "Tarathiel", "Tarathiel", "Taredd", "Tarosspur", "Tarron", "Tasar",
      "Tassarion", "Tathaln", "Tehlmar", "Teirist", "Tenyajn", "Tethir", "Thalanil", "Thallan", "Thatoryl", "Thiilthan",
      "Thurdan", "Thurruvyn", "Tiarshus", "Tlannatar", "Tolthe", "Tolthe", "Tordynnar", "Toross", "Traeliorn",
      "Traeliorn", "Travaran", "Triandal", "Triktappic", "Tsaer", "Tsiilmas", "Tyvollus", "Ualair", "Uevareth",
      "Uldreiyn", "Uldreiyn", "Urddusk", "Usunaar", "Uthorim",
    )

    val count = firstName.length
    firstName(Roller.randomInt(count))
  }

  private def getElvenFemaleFirstName: String = {
    val firstName: Seq[String] = Seq("Aelieyeeva", "Aelrue", "Aelynthi", "Aerilaya", "Ahrendaaria", "Ahrendue",
      "Ahskahala", "Ajhalanda", "Alaglossa", "Alais", "Alauthshaee", "Alavara", "Alea", "Alerathla", "Allannia",
      "Allisa", "Alloralla", "Allynna", "Almithara", "Aloevan", "Alvaerele", "Aluendalee", "Alyndra", "Amara",
      "Amaranthae", "Amkissra", "Amlaruil", "Amnestria", "Amra", "Anaharae", "Anarzee", "Aneirin", "Anhaern",
      "Anyllan", "Ara", "Araushnee", "Aravae", "Arcaena", "Arielimnda", "Arlayna", "Arnarra", "Artin", "Ashemmi",
      "Aubaudameira", "Auluua", "Aurae", "Ava", "Axilya", "Ayaeqlarune", "Azariah", "Bhuraelea", "Blythswana",
      "Bonnalurie", "Braerindra", "Caerthynna", "Calarel", "Cauladra", "Chaenath", "Chalsarda", "Chandrelle",
      "Chasianna", "Chichlandra", "Chin’nesstre", "Chomylla", "Cilivren", "Ciyradyl", "Claire", "Csharynn",
      "Daenalaia", "Daratrine", "Darshee", "Dasyra", "Dathlue", "Dathlue", "Dathlue", "Delimbiyra", "Delshandra",
      "Deularla", "Duilya", "Eallyrl", "Ecaeris", "Edraele", "Elanalue", "Elanil", "Elasha", "Eldratha", "Elénaril",
      "Eletha", "Ellarian", "Ellifain", "Eloen", "Elora", "Elvandaruil", "Embrae", "Emvorele", "Enajharas",
      "Eshenesra", "Essaerae", "Esta", "Ester", "Esyae", "Faranni", "Faunalyn", "Fhaertala", "Fieryat", "Filaurel",
      "Filauria", "Fildaerae", "Fox-at-Twilight", "Francessca", "Gaelira", "Gaerradh", "Gaylia", "Gemstarzah",
      "Ghilanna", "Glynnii", "Gweyr", "Gwynnestri", "Gylledha", "Haalija", "Hacathra", "Halaema", "Halama", "Halaema",
      "Halanaestra", "Hamalitia", "Haramara", "Helartha", "Hhora", "Holone", "Huquethae", "Hycis", "Iahalae",
      "Ialantha", "Ikeshia", "Ildilyntra", "Ilmadia", "Ilyrana", "Ilythyrra", "Imdalace", "Imizael", "Immianthe",
      "Imra", "Imramarthree", "Imryll", "Innovindil", "Ioelena", "Irhaal", "Isciira", "Ithrythra", "Itiireae", "Itylra",
      "Jastra", "Jeardra", "Jhaer", "Jhalass", "Jhanandra", "Jhaumrithe", "Jhiilsraa", "Ilsevel", "Ilyrana", "Immianthe",
      "Isilfarrel", "Kavrala", "Kaylessa", "Keerla", "Keishara", "Kethryllia", "Keya", "Khiipaera", "Kileontheal",
      "Kythaela", "Laamtora", "Laerdya", "Laerune", "Laurlaethee", "Lazziar", "Leilatha", "Liluth", "Llamryl",
      "Lorelei", "Lydi’aleera", "Lyraesel", "Maaleshiira", "Maelyrra", "Maeralya", "Maith", "Makaela", "Malruthiia",
      "Mariona", "Marissa", "Martainn", "Meira", "Melarue", "Merethyl", "Merialeth", "Meriel", "Merlara", "Mistale",
      "Mladris", "Mnuvae", "Morgwais", "Moryggan", "Muerlara", "Mylaela", "Mylaerla", "Myriani", "Naevys", "Nakiasha",
      "Nambra", "Namyriitha", "Nanalethalee", "Nanthee", "Nanthleene", "Naumys", "Nlaea", "Nuala", "Nueleth", "Nuovis",
      "Nushala", "Nylaathria", "Ochyllyss", "Oluevaera", "Phaerl", "Phantyni", "Phelorna", "Phuingara", "Phyrra",
      "Pollae", "Puorlaas", "Qemba", "Quamara", "Raejiisa", "Raerauntha", "Rathiain", "Renestrae", "Roanmara", "Ruavia",
      "Rubrae", "Ryllae", "Saelihn", "Saélihn", "Saida", "Sakaala", "Sariandi", "Sarya", "Schimae", "Seirye", "Seldanna",
      "Selussa", "Séonais", "Shael", "Shadowmoon", "Shalana", "Shalantha", "Shalendra", "Shalheira", "Shandalar",
      "Shanyrria", "Sharaera", "Sheedra", "Sheera", "Shialaevar", "Shi’larra", "Shialaevar", "Shoulree", "Shyael",
      "Shyael", "Shyllisyrr", "Sinnafain", "Soliania", "Soora", "Sorsasta", "Susklahava", "Sylmae", "Symrustar",
      "Syndra", "Synnorha", "Syrune", "Syviis", "Taenya", "Takari", "Talaedra", "Talanashta", "Talila", "Talindra",
      "Tamara", "Tarasynora", "Teharissa", "Teryani", "Thalaera", "Thalanil", "Thaola", "Thasitalia", "Throleatha",
      "Tiatha", "Tira’allara", "Tiriara", "Tisharu", "Tsarra", "Tyllaetha,", "Ulelesse", "Unae", "Urmicca", "Uschymna",
      "Vaervenshalice", "Valindra", "Vashti", "Velaethaunyl", "Velatha", "Veluthil", "Verrona", "Vestele", "Viansola",
      "Winterflower", "Xiiltharra", "Yaereene", "Yalanilue", "Yathlanae", "Yeshelné", "Yghiilra", "Ygrainne", "Ynshael",
      "Yrlissa", "Yrneha", "Yrthraethra", "Ysmyrlda", "Ytharra", "Yulmanda", "Z’Beryl", "Zhuirentel", "Zoastria",
    )

    val count = firstName.length
    firstName(Roller.randomInt(count))
  }

  private def getElvenSurname: String = {
    val surName = Seq("Aelasar", "Aelorothi", "Aendryr", "Aerasumé", "Aeravansel", "Agayous", "Agrivar", "Ahmaquissar",
      "Alaenree", "Alantar", "Alavara", "Alastrarra", "Alenuath", "Alerothi", "Alluth", "Aloevan", "Aluianti",
      "Aluviirsaan", "Amalith", "Amarallis", "Amaratharr", "Amarthen", "Ammath", "Amrallatha", "Anuaer", "Argentaamn",
      "Arren", "Ash", "Ashgrove", "Audark", "Auglamyr", "Auglathla", "Aunglor", "Autumnfire", "Bellas", "Berethryl",
      "Berilan", "Bharaclaiev", "Bhephel", "Blackhelm", "Braegen", "Briarbosk", "Brightcloak", "Brightsong",
      "Brightwing", "Caersaelk", "Calaudra", "Calauth", "Camusiil", "Cathdeiryn", "Ceretlan", "Chaadren",
      "Chamaranthe", "Clatharla", "Cormyth", "Coudoarluth", "Craulnober", "Crystalembers", "Dahast", "Dawnhorn",
      "Dhorinshyl", "Dlardrageth", "Doedance", "Donnathlascen", "Dracoseir", "Dree", "Duirsar", "Durothil",
      "Duskmere", "Duthjuth", "Ealoeth", "Echorn", "Elaéyadar", "Elassidil", "Elian", "Ellarian", "Elond", "Eluarshee",
      "Ereuvyn", "Erkowe", "Erladden", "Eroth", "Erlshade", "Estelda", "Evanara", "Eveningfall", "Everlove", "Evioro",
      "Eyriendor", "Faerondaerl", "Faerondarl", "Falanae", "Felinaun", "Fellmirr", "Fenmarel", "Fflannidan", "Floshin",
      "Fynnasla", "Gildenguard", "Goadulphyn", "Goldenleaf", "Gourael", "Greencloak", "Gwaelon", "Haell", "Haerlgent",
      "Haladar", "Hawksong", "Haevaul", "Halavanthlarr", "Hlarr", "Hyshaanth", "Iazymnal", "Ibryiil", "Ilbaereth",
      "Ilbenalu", "Ildacer", "Ildroun", "Iliathor", "Iliathor", "Iliathorr", "Ilnatar", "Immeril", "Ipyllasc",
      "Irian", "Irithyl", "Irithyl", "Ithruen", "Iydril", "Jaglene", "Kadelaryn", "Kelerandri", "Kelpor’ral", "Keove",
      "Kevanarial", "Korianthil", "Kraok", "Laelithar", "Laralytha", "Larenthanil", "Larethian", "Laughingwater",
      "Leafbower", "Leafsigil", "Le’Quella", "Lharithlyn", "Lhoril", "Llundlar", "Loceath", "Lightshiver", "Maendellyn",
      "Maerdrym", "Melruth", "Meirityn", "M'Haaren", "Miritar", "Mistrivvin", "Mistwinter", "Mithalvarin", "Moonbow",
      "Moondown", "Moonflower", "Moonglade", "Moonglamaer", "Moonsnow", "Moonweather", "Morningdove", "Mornmist",
      "Mrhulaedir", "Nacnar", "Naelgrath", "Narlbeth", "Narlbeth", "Neirdre", "Nelnueve", "Never", "Nhachashaal",
      "Nhaéslal", "Nharimlur", "Nightstar", "Nightwing", "Nihmedu", "Ni’Tessine", "Nierde", "Nightmeadow", "Nimesin",
      "Nlossae", "Nlossae", "Nolbrae", "Nyamtharsar", "Nyntynel", "Oakstaff", "Oakwood", "Olortynnal", "Olyrnn",
      "Omberdawn", "Ongluth", "Orama", "Orbryn", "Orbryn", "Ortauré", "Oumryn", "Phenthae", "Pholont", "Presrae",
      "Q'Naepp,", "Rachiilstar", "Raedrimn", "Raryndur", "Reithel", "Revven", "Rhaevaern", "Rhothomir", "Rhuidhen",
      "Rhyllgallohyr", "Rivleam", "Rivvikyn", "Runemaster", "Sarsantyr", "Selakiir", "Selmer", "Selorn", "Shadowmantle",
      "Shadowwater", "Shaeremae", "Shaethe", "Shalandalan", "Sharrith", "Shaurlanglar", "Shraiee", "Shyr", "Sicafei",
      "Siltral", "Silverbow", "Silverhand", "Silveroak", "Silverspear", "Sinaran", "Slenderbow", "Spellstalker", "Soryn",
      "Srinshee", "Starnar", "Starbrow", "Starglance", "Starglow", "Starym", "Stillhawk", "Stilmyst", "Straeth",
      "Strongbow", "Suldusk", "Sultaasar", "Summerstars", "Sunweaver", "Swordstar", "Symbaern", "Talandren",
      "Talesspur", "Tamlyranth", "Tanagathor", "Tarnruth", "Tarsap", "Tarsis", "Tassarion", "Taurntyrith", "Tellynnan",
      "Teshurr", "Thea", "Tlanbourn", "Tohrthaal", "Toralynnsyr", "Tornglara", "Tornglara", "Torthtan", "Toryvhallen",
      "Trueshot", "Tsornyl", "Tyrneladhelu", "Uirthur", "Ulondarr", "Ulongyr", "Vandiir", "Veverell", "Vispasial",
      "Vyshaan", "Waelvor", "Whitethistle", "Windstar", "Windwalker", "Xantrani", "Yeschant", "Yhendorn", "Yraueme",
      "Yridnae", "Yundraer",
    )

    val count = surName.length
    surName(Roller.randomInt(count))
  }

  private def getDwarvenMaleFirstName: String = {

    val firstName: Seq[String] = Seq("Addanc", "Ari", "Bhalkyl", "Aigror", "Dramoli", "Dorni", "Galvon", "Donar", "Brakan",
      "Duvur", "Bramdahr", "Colan", "Malgon", "Balthrasir", "Ballin", "Thovan", "Draupin", "Argos", "Thondin", "Brynjolvur",
      "Drogo", "Bothor", "Dragroli", "Firguc", "Gidel", "Bhalmun", "Avion", "Kibil", "Danil", "Calgak", "Givur",
      "Algir", "Brokk", "Bagnor", "Ruvon", "Hannar", "Chirysaor", "Artion", "Baglian", "Daremrun", "Ebdrus", "Buboth",
      "Anin", "Brand", "Bovar", "Goliath", "Bharrom", "Dwomnaed", "Dogril", "Onfroi", "Oin", "Davan", "Bargun",
      "Khubrorlug", "Thortor", "Doulgarlig", "Dondnar", "Huffie", "Fimbul", "Digan", "Bilbur", "Glorgil", "Glorgil",
      "Dravan", "Edmundur", "Malvon", "Gim", "Bamiri", "Anil", "Bramdur", "Thovir", "Azaes", "Sarraem", "Hungraec",
      "Hurfumlin", "Azotir", "Brudrith", "Rabreak", "Skosdroud", "Loddac", "Jakdrig", "Flintheart", "Dellinger",
      "Dithin")

    val count = firstName.length
    firstName(Roller.randomInt(count))
  }

  private def getDwarvenFemaleFirstName: String = {
    val firstNames: Seq[String] = Seq("Dothura", "Dila", "Crystal", "Dunora", "Divira", "Gagon", "Dhara", "Dolana",
      "Agran", "Aden", "Bhelkam", "Esmeralda", "Calona", "Demeter", "Dradir", "Bes", "Erla", "Admina",
      "Giva", "Sienna", "Jade", "Chalia", "Dilgli", "Branmand", "Randonne", "Dovana", "Ebgranne", "Dora", "Lazula",
      "Cosette", "Dugrona", "Calgrima", "Chalona", "Ela", "Amethyst", "Gavil", "Eiki", "Fiden", "Lana", "Chalia",
      "Elindur", "Nvari", "Eartha", "Avil", "Reginn", "Rudli", "Montana", "Eilivur", "Sapphire", "Barol", "Calund",
      "Emorette", "Malvan", "Ula", "Dorola", "Dovla", "Calgrima", "Afra", "Bjarki", "Narva", "Bavolle", "Rarona",
      "Bavola", "Thrakhubyrn", "Hofaebelyn", "Strordrouthra", "Bugretalyn"
    )
    val count = firstNames.length
    firstNames(Roller.randomInt(count))
  }

  private def getDwarvenSurname: String = {
    val surnames: Seq[String] = Seq("Keghood", "Anvilmaster", "Anvilbender", "Runebreaker", "Flintcoat", "Deepgrip",
      "Minebrow", "Flaskview", "Ironforge", "Oreheart", "Stonejaw", "orcbane", "Cavebreaker", "Warrencloak",
      "Nightgranite", "Dorgin", "Morgus", "Austri", "Kalady", "Maur", "Dagni", "Geirfinnur", "MaurMaur", "Dagni",
      "Geirfinnur", "Dagni", "Geirfinnur", "Rikkin", "Lovisa", "Gidal", "Sudri", "Yatmaen", "Obara", "Hjolman",
      "Runira", "Malgon", "Sveinur", "Hazeal", "Harbet", "Steinur", "Hokul", "Dorvari", "Myagrun", "Sigvaldur",
      "Panoptes", "Werazzet", "Skirfar", "Myanra", "Thogrin", "Rervia", "Kecha", "Onin", "Baerdal", "Bifurr", "Melnur",
      "Sliryn", "Glondil", "Githlia", "Lonvari", "Taleema", "Kelardy", "Salmumin", "Robekur", "Thaggoum", "Dirila",
      "Goddumir", "Terta", "Myalinden", "Grendel", "Shatterbranch", "Sorkvi", "Hlevari", "Mavia", "Sjurthi", "Hillmane",
      "Hanarav", "Lirra", "Mireveh", "Hepti", "Henna", "Ruvona", "Galren", "Drargna", "Ragnur", "Regin", "Airgan", "Loa",
      "Onfroi", "Rugin", "Mirev", "Horkahm", "Lis", "Gindira", "Fundinn", "Balskjald", "Moira", "Linka", "Ragna",
      "Dulir", "Gadona", "Lonlin", "Mili", "Apophis", "Korbeth", "Recha", "Brana", "Dhotdruk", "Stoneaxe", "Copperbranch",
      "Flatriver", "Wyvernheath"

    )

    val count = surnames.length
    surnames(Roller.randomInt(count))
  }

  private def getHalflingMaleFirstName: String = {
    val firstNames: Seq[String] = Seq("Wiltran", "Janfire", "Pimzor", "Xanvias", "Yenlan", "Ridon", "Danry", "Norpher",
      "Yarsire", "Osret", "Welkath", "Elikis", "Adran", "Amalis", "Bert", "Burrick", "Car", "Caridon", "Cyroen",
      "Davis", "Davon", "Eld", "Far", "Fel", "Huudus", "Imnard", "Jor", "Julk", "Kevon", "Lenson", "Lius", "Magnes",
      "Marijn", "Micus", "Norimon", "Priand", " Ricus", "Turic", "Urijs", "Waldel", "Samwise", "Ansegar", "Celedor",
      "Chlodomer", "Lull", "Berchar", "Wala", "Theodoric", "Prospero", "Bauto", "Waltgaud", "Togo", "Evroul", "Brutus",
      "Sunno", "Otto", "Syagrius", "Audovald", "Bertin", "Reolus", "Rigunth", "Polo", "Dagaric", "Gozolon", "Polo",
      "Reginard", "Arnor", "Valdemar", "Drogo", "Goisfrid", "Siegfried", "Saradoc", "Hartgard", "Chlodwig", "Jago",
      "Fastolph", "Bingo", "Reginar", "Reginar", "Godobald",
    )
    val count = firstNames.length
    firstNames(Roller.randomInt(count))
  }

  private def getHalflingFemaleFirstName: String = {
    val firstNames: Seq[String] = Seq("Prufice", "Yomita", "Fenprys", "Fromita", "Uviora", "Verhaly", "Quda",
      "Therprys", "Yoris", "Paesica", "Unaris", "Malwyn", "Zenlienne", "Saeni", "Uviola", "Lidtina", "Wimia",
      "Lidwyn", "Odivyre", "Adea", "Amonitza", "Arola", "Azasina", "Baita", "Bruderea", "Bruena", "Eloavela", "Elonia",
      "Elso", "Fioria", "Flonda", "Gista", "Gleta", "Ilitza", "Irmuania", "Jona", "Kitza", "Lavieza", "Marda", "Marora",
      "Mazisa", "Merina", "Musa", "Nata", "Petza", "Saniami", "Tiza", "Venuelia", "Zamenza")
    val count = firstNames.length
    firstNames(Roller.randomInt(count))
  }

  private def getHalflingSurname: String = {
    val surnames: Seq[String] = Seq("Truebeam", "Stoutheart", "Shadowbough", "Humblehare", "RidonHillhand", "Sunbarrel",
      "Freemantle", "Quickcrest", "Wisesun", "Deephare", "Stonewater", "Havenfellow", "Bramblemoon", "Keenwind",
      "Goldseeker", "Greatmeadow", "Fasteye", "Lunardance", "Hogflower", "Shadowshaker", "Slystoat", "Took", "Townsend",
      "Underbough", "Vlibrusbird", "Goodbody", "Quickhand", "Riverflower", "Truthstride", "Vlibrusbird", "Whitsoar",
      "Hornblower", "Bophin", "Longriver", "Hedgehopper", "Hopesinger", "Noakesburrow", "Clayhanger", "Whitbottom",
      "Elvellon", "Underhill", "Gardner", "Elvellon", "Hayward", "Leafwalker", "Bunce", "Riverhopper", "Lothran",
      "Burrows", "Sandheaver", "Goldworthy", "Bramblethorn", "Bracegirdle", "Bolger", "Fleetfoot", "Goodwort",
      "Oakbottom", "Smallburrow", "Goodchild", "Brownlock", "Undertree"
    )
    val count = surnames.length
    surnames(Roller.randomInt(count))
  }


}
