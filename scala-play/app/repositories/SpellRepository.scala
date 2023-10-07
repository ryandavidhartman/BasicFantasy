package repositories

import models.Spell
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.Cursor
import reactivemongo.api.bson.collection.BSONCollection
import reactivemongo.api.bson.{BSONArray, BSONDocument, BSONObjectID, BSONString, BSONValue}
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
          alignment: Option[String] = None,
          limit: Int = 200): Future[Seq[Spell]] = {


    val alignmentQuery = alignment match {
      case Some(value) => BSONDocument(
        "$or" -> BSONArray(
          BSONDocument("alignment" -> value),
          BSONDocument("alignment" -> "neutral")
        )
      )
      case None => BSONDocument.empty
    }
    val otherQueries = BSONDocument(
      "name" -> name,
      "range" -> range,
      "cleric" -> cleric,
      "magicUser" -> magicUser,
      "duration" -> duration,
      "description" -> description
    )

    val query = BSONDocument(
      "$and" -> BSONArray(alignmentQuery, otherQueries)
    )

    val projection = Some(BSONDocument.empty)

    val sortCriteria = BSONDocument("name" -> 1) // Sort by "name" in ascending order

    collection.flatMap { col =>
      col.find(query, projection)
        .sort(sortCriteria)
        .cursor[Spell]()
        .collect[Seq](limit, Cursor.FailOnError[Seq[Spell]]())
    }
  }

  def list(): Future[Seq[String]] = {

    val query = BSONDocument.empty
    val projection = Some(BSONDocument(
      "name" -> 1
    ))

    val sortCriteria = BSONDocument("name" -> 1) // Sort by "name" in ascending order

    val limit = 1000
    collection.flatMap { col =>
      col.find(query, projection)
        .sort(sortCriteria)
        .cursor[BSONDocument]()
        .collect[List](limit, Cursor.FailOnError[List[BSONDocument]]())
        .map(_.flatMap { doc =>
          doc.getAsOpt[String]("name")
        })
    }

  }
  def findOne(id: String): Future[Option[Spell]] = RepositoryUtilities.findOne[Spell](id, collection)
  def findOne(fieldName: String, fieldValue: BSONValue): Future[Option[Spell]] = RepositoryUtilities.findOne[Spell](fieldName, fieldValue, collection)
  def create(spell: Spell): Future[WriteResult] = RepositoryUtilities.create[Spell](spell, collection)
  def update(spell: Spell): Future[WriteResult] = RepositoryUtilities.update[Spell](spell, collection)
  def delete(id: BSONObjectID):Future[WriteResult] = RepositoryUtilities.delete(id, collection)
}
