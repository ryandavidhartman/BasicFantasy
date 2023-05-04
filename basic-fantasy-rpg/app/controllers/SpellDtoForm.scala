package controllers

import models.SpellDto
import play.api.data.Form
import play.api.data.Forms.{mapping, nonEmptyText, number, optional}
import reactivemongo.api.bson.BSONObjectID
import play.api.libs.json.{Format, JsError, JsString, JsSuccess, Reads, Writes}
import play.modules.reactivemongo.Formatters.bsonFormatter
import reactivemongo.api.bson.BSONObjectID

import scala.util.{Failure, Success}

object SpellDtoForm {

  import play.api.data.Forms._
  import play.api.data.Form

  val spellDtoForm = Form(
    mapping(
      "_id" -> of[BSONObjectID],
      "name" -> nonEmptyText,
      "range" -> nonEmptyText,
      "cleric" -> optional(number(min = 1, max = 6)),
      "magicUser" -> optional(number(min = 1, max = 6)),
      "duration" -> nonEmptyText,
      "description" -> nonEmptyText
    )(SpellDto.apply)(SpellDto.unapply)
  )

}
