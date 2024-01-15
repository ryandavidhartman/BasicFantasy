package basic.fantasy

import scala.annotation.tailrec


object Roller {

  val r = scala.util.Random


  def getRandomAttribute(heroic: Boolean = false): Int = {

    @tailrec
    def rollD6(heroic: Boolean = false): Int = {
      val roll = rollDice(1, 6, 0)
      if(roll == 1 && heroic)
        rollD6(heroic)
      else
        roll
    }

    if( heroic) {
      (1 to 4).map(_ => rollD6(false)).sortWith(_ < _).tail.sum
    } else {
      (1 to 3).map(_ => rollD6(false)).sum
    }

  }
  def getSixScores(heroic: Boolean = false): Seq[Int] = {
    (1 to 6).map(_ => getRandomAttribute(heroic))
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