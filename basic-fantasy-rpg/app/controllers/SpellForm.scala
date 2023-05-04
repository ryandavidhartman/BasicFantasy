package controllers

import models.Spell
import play.api.libs.json.{Format, JsError, JsString, JsSuccess, Reads, Writes}
import play.modules.reactivemongo.Formatters.bsonFormatter
import reactivemongo.api.bson.BSONObjectID

import scala.util.{Failure, Success}

object SpellForm {
  import play.api.data.Forms._
  import play.api.data.Form

  val spellForm = Form(
    mapping(
      "id" -> optional(text),
      "name" -> nonEmptyText,
      "range" -> nonEmptyText,
      "cleric" -> optional(number(min = 1, max = 6)),
      "magicUser" -> optional(number(min = 1, max = 6)),
      "duration" -> nonEmptyText,
      "description" -> nonEmptyText
    )(Spell.apply)(Spell.unapply)
  )


}
