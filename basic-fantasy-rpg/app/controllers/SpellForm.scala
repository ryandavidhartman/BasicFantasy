package controllers

import models.Spell
import play.modules.reactivemongo.Formatters.bsonFormatter
import reactivemongo.api.bson.BSONObjectID

object SpellForm {

  import play.api.data.Form
  import play.api.data.Forms._

  val spellForm = Form(
    mapping(
      "_id" -> optional(of[BSONObjectID]),
      "name" -> nonEmptyText,
      "range" -> nonEmptyText,
      "cleric" -> optional(number(min = 1, max = 6)),
      "magicUser" -> optional(number(min = 1, max = 6)),
      "duration" -> nonEmptyText,
      "description" -> nonEmptyText,
      "alignment" -> nonEmptyText
    )(Spell.apply)(Spell.unapply)
  )

}
