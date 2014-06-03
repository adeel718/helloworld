package controllers

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import play.api.test.Helpers._
import play.mvc.Http
import play.api.test.FakeRequest

class WelcomeUnitTest extends FlatSpec with Matchers {
  
  "Welcome Controller" should "render welcome message" in {
    val username = "somename"
    val result = Welcome.index(username)(FakeRequest())
    
    status(result) shouldBe Http.Status.OK
    contentAsString(result) should include("Hello " + username)
  }
}