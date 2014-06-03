package controllers

import org.scalatest.{FunSuite, Matchers}
import play.api.test.{Helpers, FakeRequest}
import play.mvc.Http
import play.api.test.Helpers._


/**
 * Created by user02 on 5/19/14.
 */
class UserRegistrationScalaUnitTest  extends FunSuite with Matchers{


  test ("User has been presented with the Registration screen") {

    val result = controllers.UserRegistration.index.apply(FakeRequest())

    status(result).should(equal(Http.Status.OK))

    contentAsString(result).should(include("Account Registration"))

  }

  test (" User submits registration form without entering data") {

    val result = controllers.UserRegistration.submitUserRegistration()(FakeRequest())

    Helpers.status(result).should(equal(Http.Status.BAD_REQUEST))
  }

}
