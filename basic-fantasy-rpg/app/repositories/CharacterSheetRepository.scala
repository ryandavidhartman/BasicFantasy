package repositories

import models.CharacterSheet
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.bson.collection.BSONCollection
import reactivemongo.api.bson.{BSONObjectID, BSONValue}
import reactivemongo.api.commands.WriteResult


import javax.inject._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class CharacterSheetRepository @Inject() (reactiveMongoApi: ReactiveMongoApi,
                                          implicit val ec: ExecutionContext) {

  def collection: Future[BSONCollection] = reactiveMongoApi.database.map(db => db.collection(name = "CharacterSheets"))
  def findOne(id: String): Future[Option[CharacterSheet]] = RepositoryUtilities.findOne[CharacterSheet](id, collection)
  def findOne(fieldCharacterSheet: String, fieldValue: BSONValue): Future[Option[CharacterSheet]] = RepositoryUtilities.findOne[CharacterSheet](fieldCharacterSheet, fieldValue, collection)
  def create(CharacterSheet: CharacterSheet): Future[WriteResult] = RepositoryUtilities.create[CharacterSheet](CharacterSheet, collection)
  def update(CharacterSheet: CharacterSheet): Future[WriteResult] = RepositoryUtilities.update[CharacterSheet](CharacterSheet, collection)
  def delete(id: BSONObjectID): Future[WriteResult] = RepositoryUtilities.delete(id, collection)

}
