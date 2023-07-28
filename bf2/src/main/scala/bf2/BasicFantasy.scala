package bf2

import scala.scalajs.js
import scala.scalajs.js.annotation.*
import com.raquo.laminar.api.L.{*, given}

import org.scalajs.dom

@main
def BasicFantasy(): Unit =
  renderOnDomContentLoaded(
    dom.document.getElementById("app"),
    Main.appElement()
  )
end BasicFantasy

object Main:
  def appElement(): Element =
    div(className := "grid-x grid-padding-x callout",
      p("Hi Ryan")
    )
  end appElement
end Main
