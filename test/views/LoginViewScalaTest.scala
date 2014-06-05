package views

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import views.html.login
import models.User
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import util.JsoupUtils._
import util.Implicits.string2Option
import util.JsoupJavaUtils
import play.api.test.FakeApplication
import helper.TestSetup
 

class LoginViewScalaTest extends FlatSpec with Matchers {
  
  val userName = "userName"
  val password = "password"
  
  def form = play.data.Form.form(classOf[User])
  
  "Login view" should "have the username and password fields" in {
    
	play.mvc.Http.Context.current.set(TestSetup.testHttpContext())

	implicit val doc = JsoupJavaUtils.renderView(login(form))
    
    assertFieldPresent(userName, "User name here")
    assertFieldPresent(password, "Password here")
  }
  
  it should "show validation errors" in {

    val expectedUsername = "some_username"
    val userdata = new User
    userdata.setUserName(expectedUsername)
    userdata.setPassword("some_password")
    
    val errForm = form.fill(userdata)
    val errors = Map(
        (userName -> "The error for username"),
        (password -> "The error for password")
    )
    errors.foreach{case (field, msg) => errForm.reject(field, msg)}
    
	implicit val doc = JsoupJavaUtils.renderView(login(errForm))
    
    assertFieldPresent(userName, "User name here", expectedUsername)
    // NB! password field should not be populated!
    assertFieldPresent(password, "Password here")
    
    errors.foreach{case (field, msg) => assertErrorPresent(field, msg)}

  }
  
}