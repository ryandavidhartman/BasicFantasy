package bf2

import scala.scalajs.js
import scala.scalajs.js.annotation.*

import org.scalajs.dom

@main
def BasicFantasy(): Unit =
  dom.document.querySelector("#app").innerHTML =
    s"""
        <div>
          <h1>Hey Ryan</h1>
        </div>
    """
end BasicFantasy
