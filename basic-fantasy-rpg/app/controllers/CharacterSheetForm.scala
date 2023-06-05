package controllers

import models.CharacterSheet
import play.modules.reactivemongo.Formatters.bsonFormatter
import reactivemongo.api.bson.BSONObjectID

object CharacterSheetForm {

  import play.api.data.Form
  import play.api.data.Forms._

  val characterSheetForm = Form(
    mapping(
      "_id" -> optional(of[BSONObjectID]),
      "name" -> nonEmptyText
    )(CharacterSheet.apply)(CharacterSheet.unapply)
  )

}
