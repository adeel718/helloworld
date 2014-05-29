package views

import org.junit.Test
import org.scalatest.FunSuite
import org.scalatest.Matchers
import play.api.test.Helpers._
import play.api.templates.Html
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import views.html._
import play.api.data.Form
import play.api.data.Forms._
import models.Registration
import play.api.i18n.Messages
import play.api.test.FakeRequest
import play.api.test.FakeApplication

/**
 * Created by user02 on 5/20/14.
 */
class RegistrationViewScalaTest extends FunSuite with Matchers {

  
  def form =Form( mapping( "firstName" -> text.verifying(Messages("error.registration.field.required", "firstName"), {!_.isEmpty}),
    "surname" -> text.verifying(Messages("error.registration.field.required", "surname"), {!_.isEmpty}),
    "email" -> text.verifying(Messages("error.email.required"), {!_.isEmpty}) ,
    "confirmEmail" -> text.verifying(Messages("error.confirmEmail.required"), {!_.isEmpty}) ,
    "password" -> text.verifying(Messages("error.registration.field.required", "password"), {!_.isEmpty}) ,
    "confirmPassword" -> text.verifying(Messages("error.confirmPassword.required"), {!_.isEmpty}),
    "tconditions" -> checked(Messages("error.tconditions.required")))
    (Registration.apply)(Registration.unapply))

  test("Check if labels and fields exist on registration screen") {
    running(FakeApplication()) {
        //Context.current.set(TestSetup.testHttpContext)
        val html: Html = views.html.register(form)
        val doc: Document = Jsoup.parse(contentAsString(html))

        doc.select("#title").text.should(equal ("Account Registration"))

        doc.select("#personalDetailsTitle").text.should(equal ("Your Personal Details"))

        doc.select("#firstNameLabel").text should(equal ("First Name:"))
        doc.select("#firstName").`val`() should(equal (""))

        doc.select("#lastNameLabel").text should(equal ("Surname:"))
        doc.select("#surname").`val`() should(equal (""))

        doc.select("#contactDetailsTitle").text.should(equal ("Your Contact Details"))

        doc.select("#emailLabel").text should(equal ("Email:"))
        doc.select("#email").`val`() should(equal (""))

        doc.select("#confirmEmailLabel").text should(equal ("Confirm Email:"))
        doc.select("#confirmEmail").`val`() should(equal (""))

        doc.select("#passwordDetailsTitle").text.should(equal ("Your Password"))

        doc.select("#passwordLabel").text should(equal ("Password:"))
        doc.select("#password").`val`() should(equal (""))

        doc.select("#confirmPasswordLabel").text should(equal ("Re-enter Password:"))
        doc.select("#confirmPassword").`val`() should(equal (""))

        doc.select("#termsTitle").text should(equal ("Terms & Conditions"))

        doc.select("#tConditions")
        doc.select("#agreeCbxLabel").text() should(include ("I agree with Terms & Conditions"))

        doc.select("#submitBtn").attr("value") should(equal ("Proceed>>"))
    }
  }

  test("Check if validation is in place and error messages shown") {

    running(FakeApplication()) {
    	// here we "submit" the empty fields
        val fakeRequest = FakeRequest(POST, "/register").withFormUrlEncodedBody(
       		"firstName" -> "",
		    "surname" -> "",
		    "email" -> "",
		    "confirmEmail" -> "",
		    "password" -> "",
		    "confirmPassword" -> ""
//		    "tconditions" -> ""
	    )

	    // here we bind form from the empty fields in request and get the form with validation errors
	    // rendering the form with validation errors should display the error messages by the fields
        val html: Html = views.html.register(form.bindFromRequest()(fakeRequest))
        val doc: Document = Jsoup.parse(contentAsString(html))

        // expect the validation errors appear in a summary section 
        doc.select("#validation-summary").text should(include (Messages("error.registration.field.required", "firstName")))
        doc.select("#validation-summary").text should(include (Messages("error.registration.field.required", "surname")))
        doc.select("#validation-summary").text should(include (Messages("error.email.required")))
        doc.select("#validation-summary").text should(include (Messages("error.confirmEmail.required")))
        doc.select("#validation-summary").text should(include (Messages("error.registration.field.required", "password")))
        doc.select("#validation-summary").text should(include (Messages("error.confirmPassword.required")))
        doc.select("#validation-summary").text should(include (Messages("error.tconditions.required")))

        // expect the validation errors appear at the each field 
        doc.select("#errorfirstName").text should(include ("firstName should not be empty Please enter."))

        doc.select("#errorsurname").text should(include ("surname should not be empty Please enter."))

        doc.select("#erroremail").text should(include ("email should not be empty Please enter valid email."))
        doc.select("#errorconfirmEmail").text should(include ("Please confirm email."))
        
        doc.select("#errorpassword").text should(include ("password should not be empty Please enter."))
        doc.select("#errorconfirmPassword").text should(include ("Please confirm entered password."))

        doc.select("#errortConditions").text should(include ("Please accept terms & conditions"))
      }
  }

}
