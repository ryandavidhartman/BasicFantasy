package repositories

import models.{Spell, SpellDto}
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.Cursor
import reactivemongo.api.bson.collection.BSONCollection
import reactivemongo.api.bson.{BSONDocument, BSONObjectID, BSONValue}
import reactivemongo.api.commands.WriteResult

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}


@Singleton
class SpellRepository @Inject() (reactiveMongoApi: ReactiveMongoApi,
                                 implicit val ec: ExecutionContext)  {
  def collection: Future[BSONCollection] = reactiveMongoApi.database.map(db => db.collection("Spells"))
  def findAll(limit: Int = 100): Future[Seq[Spell]] = {
    val query = BSONDocument.empty
    val projection = Some(BSONDocument.empty)
    collection.flatMap { col =>
      col.find(query, projection)
        .cursor[Spell]()
        .collect[Seq](limit, Cursor.FailOnError[Seq[Spell]]())
    }
  }

  def findAll_v2(limit: Int = 100): Future[Seq[SpellDto]] = {
    val query = BSONDocument.empty
    val projection = Some(BSONDocument.empty)
    collection.flatMap { col =>
      col.find(query, projection)
        .cursor[SpellDto]()
        .collect[Seq](limit, Cursor.FailOnError[Seq[SpellDto]]())
    }
  }
  def findOne(id: String): Future[Option[Spell]] = {
    BSONObjectID.parse(id) match {
      case Success(id) => {
        val maybeSpellF = collection.flatMap(_.find(BSONDocument("_id" -> id), Option.empty[Spell]).one[Spell])
        maybeSpellF.map(_.map(_.copy(id = Some(id.stringify))))
      }
      case Failure(e) => throw e
    }
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
