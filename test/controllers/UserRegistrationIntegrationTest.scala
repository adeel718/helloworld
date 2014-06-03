package controllers

import org.scalatestplus.play.PlaySpec
import play.api.test.{Helpers, FakeRequest, FakeApplication, WithApplication}
import util.TestConfigurations
import db.mongo.MongoDB
import models.RegisteredUser
import com.mongodb.casbah.commons.MongoDBObject
import play.mvc.Http
import play.api.test.Helpers.defaultAwaitTimeout

/**
 * Created by user02 on 6/3/14.
 */
class UserRegistrationIntegrationTest extends PlaySpec {

  val username = "some.user@somewhere.some"
  val password = "somepassword"

  val user = RegisteredUser(email = username,
    password = password,
    firstName = "David",
    surname = "Backhome")

  "Registration controller" must {

    "save new user and redirect to login page" in
    new WithApplication(FakeApplication(additionalConfiguration = TestConfigurations.mongo)) {
      // 1. setup mongo test instance done through application
      MongoDB().name mustBe TestConfigurations.mongo(MongoDB.KEY_DB)

      val users = MongoDB.collection("users")
      val selector = MongoDBObject("_id" -> user.email)
      users.remove(selector)
      users.count(selector) must be (0)

      // submit all fields filled with proper data
      val fakeRequest = FakeRequest(Helpers.POST, "/register").withFormUrlEncodedBody(
        "firstName" -> user.firstName,
        "surname" -> user.surname,
        "email" -> user.email,
        "confirmEmail" -> user.email,
        "password" -> user.password,
        "confirmPassword"-> user.password,
        "tconditions" -> "true"
      )
      val result = controllers.UserRegistration.submitUserRegistration()(fakeRequest)

      // assert redirect
      Helpers.status(result) must be (Http.Status.SEE_OTHER)

      Helpers.redirectLocation(result).value must be ("/login")

      // assert saved user
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

  }

}
