package controllers

import akka.util.Timeout
import play.api.mvc.Result
import play.api.mvc.Content
import play.api.test._

import org.scalatest.FunSuite
import org.scalatest.Matchers
import org.scalatest.MustMatchers
import org.scalatest.ShouldMatchers

class ScalaControllerMatchersUnitTest extends FunSuite with Matchers {

  implicit val timeout: Timeout = Timeout(100L)

  test("Controller should render 'Hi' on index") {
	val tuple = TestHelper.invokeIndexOnController
    val (result, s) = tuple
	
    // Spot the differences !
    s should equal ("Hi")
    s.should(equal("Hi"))

    s should be ("Hi")
	s.should(be("Hi"))
	
    s should === ("Hi")
    
    import play.mvc.Http // !!!
    Helpers.status(result) shouldEqual  (Http.Status.OK)
    Helpers.status(result).shouldEqual(Http.Status.OK)
  }
}

class ScalaControllerShouldMatchersUnitTest extends FunSuite with ShouldMatchers {

  test("Controller should render 'Hi' on index") {
	val (result, s) = TestHelper.invokeIndexOnController
	
    // Spot the differences !
    s should equal ("Hi")
    s.should(equal("Hi"))

    s should === ("Hi")

    import play.mvc.Http // !!!
    implicit val timeout: Timeout = Timeout(100L)
    Helpers.status(result) shouldBe (Http.Status.OK)
    Helpers.status(result).shouldBe(Http.Status.OK)
  }
}

class ScalaControllerMustMatchersUnitTest extends FunSuite with MustMatchers {

  test("Controller should render 'Hi' on index") {
    val (result, s) = TestHelper.invokeIndexOnController

    // Spot the differences !
    s must equal ("Hi")
    s.must(equal("Hi"))

    s must === ("Hi")
    
    import play.mvc.Http // !!!

    Helpers.status(result)(TestHelper.timeout) must equal (Http.Status.OK)
    Helpers.status(result)(TestHelper.timeout).must(equal(Http.Status.OK))
  }
}

import org.scalatest.junit.AssertionsForJUnit

class ScalaControllerJUnitTest extends AssertionsForJUnit {

  import org.junit.After
  import org.junit.Before
  import org.junit.Test
  import org.junit.Assert._

  implicit var timeout: Timeout = Timeout(0L)
  
  val fakeRequest = FakeRequest()
  
  @Before
  def setUp = {
    import scala.concurrent.duration._
    import scala.language.postfixOps
	timeout = Timeout(1 seconds)
  }
  
  @After
  def tearDown = timeout = Timeout(0L)
  
  @Test
  def controllerShouldRenderHiOnIndex = {
    val result = controllers.ScalaController.index()(fakeRequest)
    val s = Helpers.contentAsString(result)
    
    assertEquals("Hi", s) // a good old jUnit assert
  }
  
}


private object TestHelper {

  import scala.concurrent.duration._
  import scala.language.postfixOps
  implicit val timeout = Timeout(1 seconds)
  
  def invokeIndexOnController = {
    val result = controllers.ScalaController.index()(FakeRequest())
    val s = Helpers.contentAsString(result) // Note: a curried implicit timeout argument is required here
    (result -> s) // the very last expression is the result returned by function
  }
} 
