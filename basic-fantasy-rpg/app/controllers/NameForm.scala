package controllers

import models.Name
import play.modules.reactivemongo.Formatters.bsonFormatter
import reactivemongo.api.bson.BSONObjectID
object NameForm {

  import play.api.data.Form
  import play.api.data.Forms._

  val nameForm = Form(
    mapping(
      "_id" -> optional(of[BSONObjectID]),
      "name" -> nonEmptyText,
      "firstName" -> optional(boolean),
      "lastName" -> optional(boolean),
      "gender" -> optional(text),
      "race" -> nonEmptyText
    )(Name.apply)(Name.unapply)
  )

}

