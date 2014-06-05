package util

import com.mongodb.casbah.MongoClient


import com.novus.salat.dao.SalatDAO
import db.mongo.MongoDB
import com.novus.salat.annotations._

import play.api.Play
import java.util.NoSuchElementException
import com.mongodb.casbah.MongoClient
import context._
import models.RegisteredUser

/**
 * Created by user02 on 6/3/14.
 */


object RegistrationTestDBUtil extends SalatDAO[RegisteredUser, String](collection = MongoDB.collection("users")) {

}


