package repositories


import org.scalatest.{Matchers, FunSuite}
import play.api.test.{Helpers, FakeRequest}
import models._
import util.RegistrationTestDBUtil
import com.mongodb.casbah.commons.MongoDBObject
import org.scalatest.OptionValues._


/**
 * Created by user02 on 5/30/14.
 */

class RegistrationDAOTest extends FunSuite with Matchers{


  test ("Create User Registration")
  {
     val userRegistration = models.RegisteredUser(surname = "Sachin",
      firstName ="Tendulkar",
      email="100@sachin.com",
      password="password")
    RegisteredUserDAO.insert(userRegistration)

   RegistrationTestDBUtil.findOne(MongoDBObject("_id" -> userRegistration.email, "password" -> userRegistration.password)).value.shouldEqual(userRegistration)

  }


  test ("Verify User Registration")
  {
    fail("Not yet Impletemented")

  }

}
