package controllers

import org.scalatest.{FunSuite, Matchers}
import play.api.test.{Helpers, FakeRequest}
import play.mvc.Http
import akka.util.Timeout
import play.api.test.Helpers._


/**
 * Created by user02 on 5/19/14.
 */
class UserRegistrationScalaUnitTest  extends FunSuite with Matchers{


  //implicit val timeout: Timeout = Timeout(100L)

  test ("User has been presented with the Registration screen") {

    val result = controllers.UserRegistration.index.apply(FakeRequest())

    status(result).should(equal(Http.Status.OK))

    contentAsString(result).should(include("Account Registration"))

  }

  test (" User submits registration form without entering data") {

    val result = controllers.UserRegistration.submitUserRegistration()(FakeRequest())

    Helpers.status(result).should(equal(Http.Status.BAD_REQUEST))
  }

  test (" User submits registration form by providing valid data") {

    // submit all fields filled with proper data
    val fakeRequest = FakeRequest(POST, "/register").withFormUrlEncodedBody(
        "firstName" -> "Stephen", 
        "surname" -> "Butler" , 
        "email" -> "sbutler@gmail.com" , 
        "confirmEmail" -> "sbutler@gmail.com" , 
        "password" -> "password1" , 
        "confirmPassword"-> "password1",
        "tconditions" -> "true"
    )
    val result = controllers.UserRegistration.submitUserRegistration()(fakeRequest)

    // upon submission expect redirect to the login page 
    status(result).should(equal(Http.Status.SEE_OTHER))

    import org.scalatest.OptionValues._
    redirectLocation(result).value should(equal("/login"))
  }

}
