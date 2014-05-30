package views

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import views.html.login
import models.User
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
 

class LoginViewScalaTest extends FlatSpec with Matchers {
  
  val userName = "userName"
  val password = "password"
  
  def form = play.data.Form.form(classOf[User])
  
  "Login view" should "have the username and password fields" in {
    
	implicit val doc = renderView(login(form))
    
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
    
	implicit val doc = renderView(login(errForm))
    
    assertFieldPresent(userName, "User name here", expectedUsername)
    // NB! password field should not be populated!
    assertFieldPresent(password, "Password here")
    
    errors.foreach{case (field, msg) => assertErrorPresent(field, msg)}

  }
  
  
////////////////////////////////////////////////////////////////////////////////
  
  private def renderView(viewRenderer: => play.mvc.Content): Document = 
    Jsoup.parse(play.test.Helpers.contentAsString(viewRenderer))
  
  private implicit def string2Option(s: String): Option[String] = Option(s) 
    
  private def assertFieldPresent(fieldName: String, 
      expectedPlaceholder: String, 
      expectedValue: Option[String] = None)(implicit doc: Document) = {
    val field = getUniqueElement("#" + fieldName) // id == fieldName
    field.attr("placeholder") shouldBe expectedPlaceholder
    val fieldValue = field.`val`
    expectedValue match {
      case None => fieldValue shouldBe empty
      case Some(asExpected) => fieldValue shouldBe asExpected
    }
  }
  
  private def getUniqueElement(jsoupSelector: String)
  		(implicit doc: Document): org.jsoup.nodes.Element = {
    val elements = doc.select(jsoupSelector)
    elements should have size 1
    elements.first
  }
  
  private def assertErrorPresent(field: String, msg: String)(implicit doc: Document) = {
    val summary = getUniqueElement("#validation-summary").text
    summary should include(msg)
    
    val fieldError = getUniqueElement("#error" + field).text
    fieldError shouldBe msg
  }

}