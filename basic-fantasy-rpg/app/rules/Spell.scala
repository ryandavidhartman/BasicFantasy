package rules

import reactivemongo.api.bson._

case class Spell(
                  _id:Option[BSONObjectID],
                  name:String,
                  range: String,
                  cleric: Option[Int],
                  magicUser: Option[Int],
                  duration: String,
                  description:String
                )

object Spell {
  implicit val bsonWriter: BSONDocumentWriter[Spell] = Macros.writer[Spell]
  implicit val bsonReader: BSONDocumentReader[Spell] = Macros.reader[Spell]
}

