package util

import com.mongodb.casbah.MongoClient


import com.novus.salat.dao.SalatDAO
import db.mongo.MongoDB
import com.novus.salat.annotations._

import play.api.Play
import java.util.NoSuchElementException
import com.mongodb.casbah.MongoClient
import context._

/**
 * Created by user02 on 6/3/14.
 */

case class RegisteredUser (@Key("_id") email : String,
                           firstName : String,
                           surname : String,
                           password : String)

object RegistrationTestDBUtil extends SalatDAO[RegisteredUser, String](collection = MongoDB.collection("users")) {

}


object TestDB {

  val KEY_DB = "mongo.db"

  private lazy val dbname: String = "helloworld"
  private lazy val client = MongoClient()
  private lazy val db = client(dbname)

  def apply() = db
  def collection(col: String) = db(col)
}