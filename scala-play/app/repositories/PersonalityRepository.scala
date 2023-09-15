package repositories

import models.Personality
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.Cursor
import reactivemongo.api.bson.collection.BSONCollection
import reactivemongo.api.bson.{BSONArray, BSONDocument, BSONObjectID, BSONValue}
import reactivemongo.api.commands.WriteResult

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}
import scala.util.Random


@Singleton
class PersonalityRepository @Inject() (reactiveMongoApi: ReactiveMongoApi,
                                       implicit val ec: ExecutionContext) {
  def get(name: Option[String] = None,
          description: Option[String] = None,
          alignment: Option[String] = None,
          limit: Int = 4): Future[Seq[Personality]] = {

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
      "description" -> description
    )

    val query = BSONDocument(
      "$and" -> BSONArray(alignmentQuery, otherQueries)
    )


    val projection = Some(BSONDocument.empty)

    val allMatches: Future[Seq[Personality]] = collection.flatMap { col =>
      col.find(query, projection)
        .cursor[Personality]()
        .collect[Seq](Int.MaxValue, Cursor.FailOnError[Seq[Personality]]())
    }

    allMatches.map(personalities => Random.shuffle(personalities).distinct.take(limit))
  }
  private def collection: Future[BSONCollection] = reactiveMongoApi.database.map(db => db.collection(name = "Personalities"))
  def findOne(id: String): Future[Option[Personality]] = RepositoryUtilities.findOne[Personality](id, collection)
  def findOne(fieldName: String, fieldValue: BSONValue): Future[Option[Personality]] = RepositoryUtilities.findOne[Personality](fieldName, fieldValue, collection)
  def create(personality: Personality): Future[WriteResult] = RepositoryUtilities.create[Personality](personality, collection)
  def update(spell: Personality): Future[WriteResult] = RepositoryUtilities.update[Personality](spell, collection)
  def delete(id: BSONObjectID): Future[WriteResult] = RepositoryUtilities.delete(id, collection)
}
