package models

import play.api.test.WithApplication
import db.mongo.MongoDB
import play.api.test.FakeApplication
import util.TestConfigurations
import com.mongodb.casbah.commons.MongoDBObject
import org.scalatestplus.play.PlaySpec
import com.mongodb.casbah.MongoCollection
import com.mongodb.casbah.MongoCursor
import scala.List

class RegisteredUserDAOIntegrationTest extends PlaySpec {
  
    val username = "some.user@somewhere.some"
    val password = "somepassword"

    val user = RegisteredUser(email = username,
      password = password,
      firstName = "David",
      surname = "Backhome")

  "DAO" must {
    "save new users" in
      new WithApplication(FakeApplication(additionalConfiguration = TestConfigurations.mongo)) {
        // 1. setup mongo test instance done through application
        MongoDB().name mustBe TestConfigurations.mongo(MongoDB.KEY_DB)


        val users: MongoCollection = MongoDB.collection("users")
        val selector = MongoDBObject("_id" -> username)
        users.remove(selector)
        users.count(selector) must be (0)
        

        RegisteredUserDAO.insert(user)
        
        users.count(selector) must be (1)
        
        val dbUsers = users.find(selector).toList
	    dbUsers must have size 1
	    val dbUser = dbUsers.head.toMap
	    dbUser must have size 4
	    dbUser.get("_id") must be (user.email)
	    dbUser.get("password") must be (user.password)
	    dbUser.get("firstName") must be (user.firstName)
	    dbUser.get("surname") must be (user.surname)
    }
    
    "find existing user by id" in
      new WithApplication(FakeApplication(additionalConfiguration = TestConfigurations.mongo)) {
        // 1. setup mongo test instance done through application
        MongoDB().name mustBe TestConfigurations.mongo(MongoDB.KEY_DB)

        val users: MongoCollection = MongoDB.collection("users")
        val selector = MongoDBObject("_id" -> username)
        users.remove(selector)
        users.count(selector) must be (0)
        

        	users.insert(MongoDBObject("_id" -> user.email, "password" -> user.password, "firstName" -> user.firstName, "surname" -> user.surname))
        users.count(selector) must be (1)

        val dbUser = RegisteredUserDAO.findOneById(user.email)
        dbUser.value must === (user)
      }
    
    "find existing user by id and password" in
      new WithApplication(FakeApplication(additionalConfiguration = TestConfigurations.mongo)) {
        // 1. setup mongo test instance done through application
        MongoDB().name mustBe TestConfigurations.mongo(MongoDB.KEY_DB)

        val users: MongoCollection = MongoDB.collection("users")
        val selector = MongoDBObject("_id" -> username)
        users.remove(selector)
        users.count(selector) must be (0)
        

        	users.insert(MongoDBObject("_id" -> user.email, "password" -> user.password, "firstName" -> user.firstName, "surname" -> user.surname))
        users.count(selector) must be (1)

        val dbUsers = RegisteredUserDAO.find(MongoDBObject("_id" -> user.email, "password" -> user.password)).toList
	    dbUsers must have size 1
	    dbUsers.head must === (user) 
      }
  }

}