package repositories

import models.Region
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.Cursor
import reactivemongo.api.bson.{BSONDocument, BSONObjectID, BSONValue}
import reactivemongo.api.bson.collection.BSONCollection
import reactivemongo.api.commands.WriteResult

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class RegionRepository@Inject() (reactiveMongoApi: ReactiveMongoApi,
                                 implicit val ec: ExecutionContext) {

  def collection: Future[BSONCollection] = reactiveMongoApi.database.map(db => db.collection("Regions"))


  def list(): Future[Seq[(String, String)]] = {

    val query = BSONDocument.empty
    val projection = Some(BSONDocument(
      "_id" -> 1,
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
          val id = doc.getAsOpt[BSONObjectID]("_id") match {
            case Some(bsonId) => bsonId.stringify
            case None => "unknown"
          }
          val name = doc.getAsOpt[String]("name").getOrElse("unknown")
          Seq((id, name))
        })
    }

  }

  def findOne(id: String): Future[Option[Region]] = RepositoryUtilities.findOne[Region](id, collection)

  def findOne(fieldName: String, fieldValue: BSONValue): Future[Option[Region]] = RepositoryUtilities.findOne[Region](fieldName, fieldValue, collection)

  def create(region: Region): Future[WriteResult] = RepositoryUtilities.create[Region](region, collection)

  //def update(region: Region): Future[WriteResult] = RepositoryUtilities.update[Region](region, collection)

  def delete(id: BSONObjectID): Future[WriteResult] = RepositoryUtilities.delete(id, collection)

}
