package controllers

import models.{Demography, Region}
import play.api.data.Mapping
import play.modules.reactivemongo.Formatters.bsonFormatter
import reactivemongo.api.bson.BSONObjectID

object RegionForm {

  import play.api.data.Form
  import play.api.data.Forms._

  val demographyMapping: Mapping[List[Demography]] = list(
    mapping(
      "race" -> nonEmptyText,
      "lowRange" -> number,
      "highRange" -> number
    )(Demography.apply)(Demography.unapply)
  )

  val regionForm = Form(
    mapping(
      "_id" -> optional(of[BSONObjectID]),
      "campaign" -> nonEmptyText,
      "name" -> nonEmptyText,
      "ruler" -> nonEmptyText,
      "capital" -> nonEmptyText,
      "population" -> nonEmptyText,
      "demiHumans" -> nonEmptyText,
      "humanoids" -> nonEmptyText,
      "resources" -> nonEmptyText,
      "description" -> nonEmptyText,
      "demography" -> demographyMapping
    )(Region.apply)(Region.unapply)
  )

}