package repositories

import javax.inject._
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.bson.{BSONDocument, BSONDocumentReader, BSONObjectID, BSONValue}
import reactivemongo.api.bson.collection.BSONCollection
import reactivemongo.api.Cursor
import reactivemongo.api.commands.WriteResult
import models.Spell

import scala.concurrent.{ExecutionContext, Future}
import reactivemongo.api.commands.Command


@Singleton
class SpellRepository @Inject() (reactiveMongoApi: ReactiveMongoApi,
                                 implicit val ec: ExecutionContext)  {

  def collection: Future[BSONCollection] = reactiveMongoApi.database.map(db => db.collection("Spells"))


  import reactivemongo.api.bson.collection.BSONCollection
  import reactivemongo.api.bson.{BSONDocument, BSONDocumentReader}
  import reactivemongo.api.Cursor
  import reactivemongo.api.commands.AggregationFramework

  import scala.concurrent.Future

  def findAll(limit: Int = 100): Future[Seq[Spell]] = {
    val query = BSONDocument.empty
    val projection = Some(BSONDocument.empty)
    collection.flatMap { col =>
      col.find(query, projection)
        .cursor[Spell]()
        .collect[Seq](limit, Cursor.FailOnError[Seq[Spell]]())
    }
  }


  def findOne(id: BSONObjectID): Future[Option[Spell]] = {
    collection.flatMap(_.find(BSONDocument("_id" -> id), Option.empty[Spell]).one[Spell])
  }

  def findOne(fieldName: String, fieldValue: BSONValue): Future[Option[Spell]] = {
    collection.flatMap(_.find(BSONDocument(fieldName -> fieldValue), Option.empty[Spell]).one[Spell])
  }

  def create(spell: Spell): Future[WriteResult] = {
    collection.flatMap(_.insert(ordered = false).one(spell))
  }

  def update(id: BSONObjectID, spell: Spell): Future[WriteResult] = {
    collection.flatMap(
      _.update(ordered = false).one(BSONDocument("_id" -> id), spell)
    )
  }

  def delete(id: BSONObjectID):Future[WriteResult] = {
    collection.flatMap(
      _.delete().one(BSONDocument("_id" -> id), Some(1))
    )
  }



}
