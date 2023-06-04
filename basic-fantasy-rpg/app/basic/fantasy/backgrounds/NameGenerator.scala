package basic.fantasy.backgrounds

import basic.fantasy.Roller
import basic.fantasy.backgrounds.Races._
import repositories.NameRepository

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class NameGenerator  @Inject()(
  implicit executionContext: ExecutionContext,
  val nameRepository: NameRepository
) {

  def getName(race: Race, gender: String): Future[String] = race match {
    case Human => getHumanName(gender)
    case Elf => getElvenName(gender)
    case Dwarf => getDwarvenName(gender)
    case Halfling => getHalflingName(gender)
    case HalfElf => if (Roller.coinFLip()) getElvenName(gender) else getHumanName(gender)
    case HalfOrc => if (Roller.coinFLip()) getOrcishName(gender) else getHumanName(gender)
  }

  def getHumanName(gender: String): Future[String] = {
    for {
      surName <- genericNameGetter(HumanSurnames)
      firstName <- if(gender.toLowerCase == "male") genericNameGetter(HumanMaleFirstNames) else genericNameGetter(HumanFemaleFirstNames)
    } yield {
      s"$firstName $surName"
    }
  }

  def getElvenName(gender: String): Future[String] = {
    for {
      surName <- genericNameGetter(ElvenSurnames)
      firstName <- if (gender.toLowerCase == "male") genericNameGetter(ElvenMaleFirstNames) else genericNameGetter(ElvenFemaleFirstNames)
    } yield {
      s"$firstName $surName"
    }
  }

  def getDwarvenName(gender: String): Future[String] = {
    for {
      surName <- genericNameGetter(DwarvenSurnames)
      firstName <- if (gender.toLowerCase == "male") genericNameGetter(DwarvenMaleFirstNames) else genericNameGetter(DwarvenFemaleFirstNames)
    } yield {
      s"$firstName $surName"
    }
  }

  def getHalflingName(gender: String): Future[String] = {
    for {
      surName <- genericNameGetter(HalflingSurnames)
      firstName <- if (gender.toLowerCase == "male") genericNameGetter(HalflingMaleFirstNames) else genericNameGetter(HalflingFemaleFirstNames)
    } yield {
      s"$firstName $surName"
    }
  }

  def getOrcishName(gender: String): Future[String] = {
    if (gender.toLowerCase == "male")
      genericNameGetter(OrcishMaleNames)
    else
      genericNameGetter(OrcishFemaleNames)
  }

  def genericNameGetter(names: Future[Seq[String]]): Future[String] = {
    names.map(n => {
      val count = n.length
      n(Roller.randomInt(count))
    })
  }

  private lazy val OrcishMaleNames: Future[Seq[String]] = {
    nameRepository.get(firstName = Some(true), race = Some("orc"), gender = Some("male")).map(_.map(_.name))
  }

  private lazy val OrcishFemaleNames: Future[Seq[String]] = {
    nameRepository.get(firstName = Some(true), race = Some("orc"), gender = Some("female")).map(_.map(_.name))
  }

  private lazy val HumanSurnames: Future[Seq[String]] = {
    nameRepository.get(lastName = Some(true), race = Some("human")).map(_.map(_.name))
  }

  private lazy val HumanMaleFirstNames: Future[Seq[String]] = {
    nameRepository.get(firstName = Some(true), race = Some("human"), gender = Some("male")).map(_.map(_.name))
  }

  private lazy val HumanFemaleFirstNames: Future[Seq[String]] = {
    nameRepository.get(firstName = Some(true), race = Some("human"), gender = Some("female")).map(_.map(_.name))
  }

  private lazy val ElvenSurnames: Future[Seq[String]] = {
    nameRepository.get(lastName = Some(true), race = Some("elf")).map(_.map(_.name))
  }

  private lazy val ElvenMaleFirstNames: Future[Seq[String]] = {
    nameRepository.get(firstName = Some(true), race = Some("elf"), gender = Some("male")).map(_.map(_.name))
  }

  private lazy val ElvenFemaleFirstNames: Future[Seq[String]] = {
    nameRepository.get(firstName = Some(true), race = Some("elf"), gender = Some("female")).map(_.map(_.name))
  }

  private lazy val DwarvenMaleFirstNames: Future[Seq[String]] = {
    nameRepository.get(firstName = Some(true), race = Some("dwarf"), gender = Some("male")).map(_.map(_.name))
  }

  private lazy val DwarvenFemaleFirstNames: Future[Seq[String]] = {
    nameRepository.get(firstName = Some(true), race = Some("dwarf"), gender = Some("female")).map(_.map(_.name))
  }

  private lazy val DwarvenSurnames: Future[Seq[String]] = {
    nameRepository.get(lastName = Some(true), race = Some("dwarf")).map(_.map(_.name))
  }

  private lazy val HalflingMaleFirstNames: Future[Seq[String]] = {
    nameRepository.get(firstName = Some(true), race = Some("halfling"), gender = Some("male")).map(_.map(_.name))
  }

  private lazy val HalflingFemaleFirstNames: Future[Seq[String]] = {
    nameRepository.get(firstName = Some(true), race = Some("halfling"), gender = Some("female")).map(_.map(_.name))
  }

  private lazy val HalflingSurnames: Future[Seq[String]] = {
    nameRepository.get(lastName = Some(true), race = Some("halfling")).map(_.map(_.name))
  }
}
