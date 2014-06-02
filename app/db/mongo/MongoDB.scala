package db.mongo

import play.api.Play
import java.util.NoSuchElementException
import com.mongodb.casbah.MongoClient

object MongoDB {
  
  val KEY_DB = "mongo.db"
  
  private lazy val dbname: String = Play.current.configuration.getString(KEY_DB).getOrElse(throw new NoSuchElementException(KEY_DB))
  private lazy val client = MongoClient()
  private lazy val db = client(dbname) 

  def apply() = db
  def collection(col: String) = db(col)
}