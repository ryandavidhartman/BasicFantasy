package controllers

import models.CharacterAttributes.characterAttributesMapping
import models.CharacterSheet
import play.api.data.Mapping
import play.modules.reactivemongo.Formatters.bsonFormatter
import reactivemongo.api.bson.BSONObjectID

object CharacterSheetForm {

  import play.api.data.Form
  import play.api.data.Forms._

  val characterSheetMapping: Mapping[CharacterSheet] = mapping(
      "_id" -> optional(of[BSONObjectID]),
      "name" -> nonEmptyText,
      "characterAttributes" -> optional(characterAttributesMapping)
    )(CharacterSheet.apply)(CharacterSheet.unapply)


  val characterSheetForm: Form[CharacterSheet] = Form(characterSheetMapping)


}
