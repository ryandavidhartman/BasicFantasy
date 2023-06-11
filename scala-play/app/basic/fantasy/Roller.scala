package basic.fantasy


object Roller {

  val r = scala.util.Random
  def getRandomScoreIndex(): Int = {
    r.nextInt(16)
  }
  def getSixScores(): Seq[Int] = {
    (1 to 6).map(_ => getRandomScoreIndex()).sortWith(_ > _)
  }
  def rollDice(numOfDice: Int, typeOfDice: Int, mod: Int): Int = {
    (1 to numOfDice).map(_ => r.nextInt(typeOfDice) + 1 + mod).sum
  }
  def rollHP(numOfDice: Int, typeOfDice: Int, mod: Int): Int = {
    (1 to numOfDice)
      .map(_ => r.nextInt(typeOfDice) + 1 + mod)
      .map(r => Math.max(r, 1))  // You get at least 1 HP per level even if you have a negative con modifier
      .sum
  }

  def randomInt(max: Int): Int = r.nextInt(max)

  def randomDouble(max: Double): Double= r.between(0.0, max)

  def coinFLip(): Boolean = r.nextBoolean()

  def getRandomData[T](data: Map[Int, T]): T = {
    try {
      val random = Roller.randomInt(data.size) + 1
      data(random)
    } catch {
      case e: Throwable => println(e.getMessage); throw e
    }
  }

  def getRandomData(data: Seq[String]): String = {
    try {
      val random = Roller.randomInt(data.size)
      data(random)
    } catch {
      case e: Throwable => println(e.getMessage); e.getMessage
    }
  }

  def randomMagicWeaponBonus(level: Int): Int = {
    val magicRoller = Roller.randomDouble(2.8*level)
    Math.max(0, Math.log(magicRoller).toInt)
  }

  def randomMagicAmmoBonus(level: Int): Int = {
    val magicRoller = Roller.randomDouble(1.4*level)
    Math.max(0, Math.log(magicRoller).toInt)
  }

}