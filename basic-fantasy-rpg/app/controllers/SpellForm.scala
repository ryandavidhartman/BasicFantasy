package controllers

import models.Spell


object SpellForm {
  import play.api.data.Forms._
  import play.api.data.Form

  val spellForm = Form(
    mapping(
      "_id" -> optional(text),
      "name" -> nonEmptyText,
      "range" -> nonEmptyText,
      "cleric" -> optional(number(min = 1, max = 6)),
      "magicUser" -> optional(number(min = 1, max = 6)),
      "duration" -> nonEmptyText,
      "description" -> nonEmptyText
    )(Spell.apply)(Spell.unapply)
  )
}
