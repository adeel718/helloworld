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

  test ("User has been presented with the Registration screen")
  {
    var fakeRequest = FakeRequest()
    val result = controllers.UserRegistration.index.apply(fakeRequest)

    status(result).should(equal(Http.Status.OK))

    contentAsString(result).should(include("Account Registration"))

  }

  test (" User submits registration form without entering data") {
    var fakeRequest = FakeRequest()

    val result = controllers.UserRegistration.submitUserRegistration()(fakeRequest)

    Helpers.status(result).should(equal(Http.Status.BAD_REQUEST))
  }
/*
  test (" User submits registration form by providing valid data") {

//    val requestValues = Map("firstName" -> "Stephen", "surname" -> "Butler" , "email" -> "sbutler@gmail.com" , "confirmEmail" -> "sbutler@gmail.com" , "password" -> "password1" , "confirmPassword"-> "password1")

//    val reqValues = Tuple6 ("firstName" -> "Stephen", "surname" -> "Butler" , "email" -> "sbutler@gmail.com" , "confirmEmail" -> "sbutler@gmail.com" , "password" -> "password1" , "confirmPassword"-> "password1")
//    val fakeRequest = FakeRequest(POST, "/").withFormUrlEncodedBody(reqValues)

//    println(rq)
    val fakeRequest = FakeRequest(POST, "/").withFormUrlEncodedBody("firstName" -> "Stephen", "surname" -> "Butler" , "email" -> "sbutler@gmail.com" , "confirmEmail" -> "sbutler@gmail.com" , "password" -> "password1" , "confirmPassword"-> "password1")
    val result = controllers.UserRegistration.submitUserRegistration()(fakeRequest)

    status(result).should(equal(Http.Status.SEE_OTHER))

    redirectLocation(result).should(equal(Some("/login")))
  }
*/
}
