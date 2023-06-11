package repositories

import models.Model
import play.api.libs.json.OFormat
import reactivemongo.api.bson.collection.BSONCollection
import reactivemongo.api.bson.{BSONDocument, BSONDocumentReader, BSONObjectID, BSONValue}
import reactivemongo.api.commands.WriteResult
import reactivemongo.play.json.compat.json2bson.toDocumentWriter

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

object RepositoryUtilities {

  def findOne[T](id: String,  collection: Future[BSONCollection])(implicit ec: ExecutionContext, fmt: OFormat[T], reader: BSONDocumentReader[T]): Future[Option[T]] = {
    BSONObjectID.parse(id) match {
      case Success(id) => collection.flatMap(_.find(BSONDocument("_id" -> id), Option.empty[T]).one[T])
      case Failure(e) => throw e
    }
  }

  def findOne[T](fieldName: String, fieldValue: BSONValue, collection: Future[BSONCollection])(implicit ec: ExecutionContext, fmt: OFormat[T], reader: BSONDocumentReader[T]): Future[Option[T]] = {
    collection.flatMap(_.find(BSONDocument(fieldName -> fieldValue), Option.empty[T]).one[T])
  }
  def create[T](record: T, collection: Future[BSONCollection])(implicit ec: ExecutionContext, fmt: OFormat[T]): Future[WriteResult] = {
    collection.flatMap(_.insert(ordered = false).one(record))
  }
  def update[T <: Model](record: T, collection: Future[BSONCollection])(implicit ec: ExecutionContext, fmt: OFormat[T]): Future[WriteResult] = {
    collection.flatMap(
      _.update(ordered = false).one(BSONDocument("_id" -> record._id), record)
    )
  }
  def delete(id: BSONObjectID,collection: Future[BSONCollection])(implicit ec: ExecutionContext): Future[WriteResult] = {
    collection.flatMap(
      _.delete().one(BSONDocument("_id" -> id), Some(1))
    )
  }

}
