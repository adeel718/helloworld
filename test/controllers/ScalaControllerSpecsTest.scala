package controllers

import play.api.test._
import play.api.mvc.Result
import play.api.mvc.Content
import akka.util.Timeout
import org.specs2.mutable.Specification
import org.junit.runner.RunWith

@RunWith(classOf[org.specs2.runner.JUnitRunner])
class ScalaControllerSpecsTest extends Specification {

  "Scala controller" should {
    
    "render 'Hi' on index" in {
	    implicit val timeout = Timeout(100L)
	    val result = controllers.ScalaController.index()(FakeRequest())
	    val s = Helpers.contentAsString(result)
	    s must be equalTo("Hi")
    }
    
  }
  
}