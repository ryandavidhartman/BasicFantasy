package controllers

import models.Region
import play.modules.reactivemongo.Formatters.bsonFormatter
import reactivemongo.api.bson.BSONObjectID

object RegionForm {

  import play.api.data.Form
  import play.api.data.Forms._

  val regionForm = Form(
    mapping(
      "_id" -> optional(of[BSONObjectID]),
      "campaign" -> nonEmptyText,
      "name" -> nonEmptyText,
      "ruler" -> nonEmptyText,
      "capital" -> nonEmptyText,
      "population" -> nonEmptyText,
      "demiHuman" -> nonEmptyText,
      "humanoids" -> nonEmptyText,
      "resources" -> nonEmptyText,
      "description" -> nonEmptyText,
      "demography" -> ???
    )(Region.apply)(Region.unapply)
  )

}