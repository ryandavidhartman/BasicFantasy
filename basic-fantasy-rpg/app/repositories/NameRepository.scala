package repositories

import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.bson.collection.BSONCollection

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}
import models.Name
import reactivemongo.api.Cursor
import reactivemongo.api.bson.BSONDocument

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

}
