package models

import reactivemongo.api.bson.BSONObjectID

trait Model {
  val _id: Option[BSONObjectID]
}
