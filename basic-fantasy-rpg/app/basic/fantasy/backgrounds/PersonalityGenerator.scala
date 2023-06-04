package basic.fantasy.backgrounds

import basic.fantasy.Roller
import basic.fantasy.backgrounds.CharacterAlignments.{Chaotic, CharacterAlignment, Lawful, Neutral}
import repositories.PersonalityRepository

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}


@Singleton
class PersonalityGenerator @Inject() (
 implicit executionContext: ExecutionContext,
 val personalityRepository: PersonalityRepository) {

  private lazy val goodPersonalities = personalityRepository.get(alignment = Some("lawful"))
  private lazy val neutralPersonalities = personalityRepository.get(alignment = Some("neutral"))
  private lazy val evilPersonalities =  personalityRepository.get(alignment = Some("chaotic"))
  private def getPersonalities(alignment: CharacterAlignment): Future[String] = {
    val personalitiesF = alignment match {
      case Lawful => goodPersonalities
      case Neutral => neutralPersonalities
      case Chaotic => evilPersonalities
    }
    personalitiesF.map(personalities => {
      val length = personalities.length
      val random = Roller.randomInt(length)
      personalities(random).name
    })
  }
  def getPersonality(alignment: CharacterAlignment): Future[String] = {
    for {
      personality1 <- getPersonalities(alignment)
      personality2 <- getPersonalities(alignment).flatMap { p2 =>
        if (p2 != personality1) {
          Future.successful(p2)
        } else {
          getPersonalities(alignment)
        }
      }
    } yield s"$personality1/$personality2"
  }
}
