package basic.webapp

import org.scalajs.dom
import utest._
import org.scalajs.dom.document
import org.scalajs.dom.ext._

object CharacterAppTest extends TestSuite {
  // Initialize App
  //CharacterApp.setupUI()

  def tests: Tests = Tests {

    /*
    test("HelloWorld") {
      assert(document.querySelectorAll("p").count(_.textContent == "OnceWas RPG Character Generator") == 1)
    }
    */

    /*
    test("ButtonClick") {
      def messageCount =
        document.querySelectorAll("p").count(_.textContent == "You clicked the button!")

      val button = document.querySelector("button").asInstanceOf[dom.html.Button]
      assert(button != null && button.textContent == "Click me!")
      assert(messageCount == 0)

      for (c <- 1 to 5) {
        button.click()
        assert(messageCount == c)
      }
    }

     */
  }
}
