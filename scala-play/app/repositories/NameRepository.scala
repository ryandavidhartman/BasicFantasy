package repositories


import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.bson.collection.BSONCollection

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}
import models.Name
import reactivemongo.api.Cursor
import reactivemongo.api.bson.{BSON, BSONDocument, BSONObjectID, BSONValue}
import reactivemongo.api.commands.WriteResult

class NameRepository @Inject() (reactiveMongoApi: ReactiveMongoApi,
                                 implicit val ec: ExecutionContext) {

  def collection: Future[BSONCollection] = reactiveMongoApi.database.map(db => db.collection("Names"))

  def get(name: Option[String] = None,
          firstName: Option[Boolean] = None,
          lastName: Option[Boolean] = None,
          gender: Option[String] = None,
          race: Option[String] = None,
          limit: Int = 100): Future[Seq[Name]] = {

    val query = BSONDocument(
      "name" -> name,
      "firstName" -> firstName,
      "lastName" -> lastName,
      "gender" -> gender,
      "race" -> race,
    )

    val projection = Some(BSONDocument.empty)

    collection.flatMap { col =>
      col.find(query, projection)
        .cursor[Name]()
        .collect[Seq](limit, Cursor.FailOnError[Seq[Name]]())
    }
  }

  def getRandomFilteredName(name: Option[String] = None,
                            firstName: Option[Boolean] = None,
                            lastName: Option[Boolean] = None,
                            gender: Option[String] = None,
                            race: Option[String] = None): Future[Option[Name]] = {

    val query = BSONDocument(
      "name" -> name,
      "firstName" -> firstName,
      "lastName" -> lastName,
      "gender" -> gender,
      "race" -> race
    )


    collection.flatMap { col =>
      import col.AggregationFramework
      import AggregationFramework._

      val pipeline = List(
        Match(query),
        Sample(1)
      )

      val aggregator = col.aggregatorContext[BSONDocument](pipeline).prepared

      val resultFuture = aggregator.cursor.collect[List]().map { results =>
        results.flatMap { doc =>
          BSON.readDocument[Name](doc) match {
            case scala.util.Success(name) => Some(name)
            case _ => None
          }
        }.headOption
      }

      resultFuture.recover {
        case error: Throwable =>
          println(s"Error getting name: ${error.getMessage}")
          None
      }
    }


  }










  def findOne(id: String): Future[Option[Name]] = RepositoryUtilities.findOne[Name](id, collection)

  def findOne(fieldName: String, fieldValue: BSONValue): Future[Option[Name]] = RepositoryUtilities.findOne[Name](fieldName, fieldValue, collection)

  def create(name: Name): Future[WriteResult] = RepositoryUtilities.create[Name](name, collection)

  def update(name: Name): Future[WriteResult] = RepositoryUtilities.update[Name](name, collection)

  def delete(id: BSONObjectID): Future[WriteResult] = RepositoryUtilities.delete(id, collection)

}
