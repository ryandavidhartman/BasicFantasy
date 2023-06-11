package basic.fantasy

import utest.{TestSuite, Tests, test}

object RollerTests extends TestSuite {

  import Roller._

  override def tests: Tests = Tests {
    test("Dice Roller Tests") {

      val rolls = (1 to 100).map(_ => rollDice(1, 4, 0))

      assert(rolls.max == 4)
    }
  }
}