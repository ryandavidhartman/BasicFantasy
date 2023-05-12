package controllers

import models.Spell
import play.api.data.Form
import play.api.data.Forms.{mapping, nonEmptyText, number, optional}
import reactivemongo.api.bson.BSONObjectID
import play.api.libs.json.{Format, JsError, JsString, JsSuccess, Reads, Writes}
import play.modules.reactivemongo.Formatters.bsonFormatter
import reactivemongo.api.bson.BSONObjectID

import scala.util.{Failure, Success}

object SpellForm {

  import play.api.data.Forms._
  import play.api.data.Form

  val spellForm = Form(
    mapping(
      "_id" -> optional(of[BSONObjectID]),
      "name" -> nonEmptyText,
      "range" -> nonEmptyText,
      "cleric" -> optional(number(min = 1, max = 6)),
      "magicUser" -> optional(number(min = 1, max = 6)),
      "duration" -> nonEmptyText,
      "description" -> nonEmptyText
    )(Spell.apply)(Spell.unapply)
  )

}
