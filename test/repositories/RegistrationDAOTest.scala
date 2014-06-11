package repositories


import org.scalatest.{Matchers, FunSuite}
import play.api.test.{Helpers, FakeRequest}
import models._
import util.{TestConfigurations, RegistrationTestDBUtil}
import com.mongodb.casbah.commons.MongoDBObject
import org.scalatest.OptionValues._
import play.api.test.FakeApplication
import play.api.test.Helpers.running
import com.mongodb.casbah.MongoClient

/**
 * Created by user02 on 5/30/14.
 */

class RegistrationDAOTest extends FunSuite with Matchers{


  test ("Create User Registration")
  {
    running(FakeApplication()){
     val userRegistration = models.RegisteredUser(surname = "Sachin",
      firstName ="Tendulkar",
      email="100@sachin.com",
      password="password")

    // remove a test user from test db before insert is called
    val mongoClient = MongoClient()
    val db = mongoClient.getDB(TestConfigurations.testDBName)
    db.getCollection("users").remove(MongoDBObject("_id" -> userRegistration.email))

      RegisteredUserDAO.insert(userRegistration)

	   val actualDataOpt = RegistrationTestDBUtil.findOne(MongoDBObject("_id" -> userRegistration.email, "password" -> userRegistration.password))
      actualDataOpt.value shouldEqual userRegistration
    }
  }

}
