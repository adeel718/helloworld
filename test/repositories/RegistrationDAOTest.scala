package repositories


import org.scalatest.{Matchers, FunSuite}
import play.api.test.{Helpers, FakeRequest}
import models._
import util.RegistrationTestDBUtil
import com.mongodb.casbah.commons.MongoDBObject
import org.scalatest.OptionValues._
import play.api.test.FakeApplication
import play.api.test.Helpers.running

/**
 * Created by user02 on 5/30/14.
 */

class RegistrationDAOTest extends FunSuite with Matchers{


  test ("Create User Registration")
  {
    running(FakeApplication){
     val userRegistration = models.RegisteredUser(surname = "Sachin",
      firstName ="Tendulkar",
      email="100@sachin.com",
      password="password")
      RegisteredUserDAO.insert(userRegistration)

	   val actualData = RegistrationTestDBUtil.findOne(MongoDBObject("_id" -> userRegistration.email, "password" -> userRegistration.password)).value
	    actualData.email shouldEqual userRegistration.email
	    actualData.surname shouldEqual userRegistration.surname
	    actualData.firstName shouldEqual userRegistration.firstName
	    actualData.password shouldEqual userRegistration.password
    }
  }

}
