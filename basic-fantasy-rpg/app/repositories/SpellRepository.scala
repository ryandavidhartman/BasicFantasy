package repositories

import models.Spell
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.Cursor
import reactivemongo.api.bson.collection.BSONCollection
import reactivemongo.api.bson.{BSONDocument, BSONObjectID, BSONValue}
import reactivemongo.api.commands.WriteResult

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class SpellRepository @Inject() (reactiveMongoApi: ReactiveMongoApi,
                                 implicit val ec: ExecutionContext)  {
  def collection: Future[BSONCollection] = reactiveMongoApi.database.map(db => db.collection(name = "Spells"))
  def get(name: Option[String] = None,
          range: Option[String] = None,
          cleric: Option[Int] = None,
          magicUser: Option[Int] = None,
          duration: Option[String] = None,
          description: Option[String] = None,
          limit: Int = 100): Future[Seq[Spell]] = {

    val query = BSONDocument(
      "name" -> name,
      "range" -> range,
      "cleric" -> cleric,
      "magicUser" -> magicUser,
      "duration" -> duration,
      "description" -> description
    )

    val projection = Some(BSONDocument.empty)

    collection.flatMap { col =>
      col.find(query, projection)
        .cursor[Spell]()
        .collect[Seq](limit, Cursor.FailOnError[Seq[Spell]]())
    }
  }
  def findOne(id: String): Future[Option[Spell]] = RepositoryUtilities.findOne[Spell](id, collection)
  def findOne(fieldName: String, fieldValue: BSONValue): Future[Option[Spell]] = RepositoryUtilities.findOne[Spell](fieldName, fieldValue, collection)
  def create(spell: Spell): Future[WriteResult] = RepositoryUtilities.create[Spell](spell, collection)
  def update(spell: Spell): Future[WriteResult] = RepositoryUtilities.update[Spell](spell, collection)
  def delete(id: BSONObjectID):Future[WriteResult] = RepositoryUtilities.delete(id, collection)
}
