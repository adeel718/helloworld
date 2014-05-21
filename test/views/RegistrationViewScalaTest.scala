package views

import org.junit.Test

import org.scalatest.FunSuite
import org.scalatest.Matchers
import play.test.Helpers._
import play.api.templates.Html
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import views.html._
import play.api.data.Form
import play.api.data.Forms._
import models.Registration

/**
 * Created by user02 on 5/20/14.
 */
class RegistrationViewScalaTest extends FunSuite with Matchers {

  test("Check if labels and fields exist on registration screen") {
    running(fakeApplication, new Runnable {
      def run {
        val form =Form( mapping( "firstName" -> text.verifying("firstName should not be empty Please enter.", {!_.isEmpty}),
          "surname" -> text.verifying("firstName should not be empty Please enter.", {!_.isEmpty}),
          "email" -> email ,
          "confirmEmail" -> email,
          "password" -> text.verifying("password should not be empty Please enter.", {!_.isEmpty}) ,
          "confirmPassword" -> text.verifying("Please confirm the password.", {!_.isEmpty}),
          "tconditions" -> checked("Please accept terms & conditions"))
          (Registration.apply)(Registration.unapply))

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
    })
  }

  test("Check if validation is in place and error messages shown") {
    //val data = Map("")


    running(fakeApplication, new Runnable {
      def run {

        val form =Form( mapping( "firstName" -> text.verifying("firstName should not be empty Please enter.", {!_.isEmpty}),
          "surname" -> text.verifying("surname should not be empty Please enter.", {!_.isEmpty}),
          "email" -> text.verifying("email should not be empty Please enter valid email.", {!_.isEmpty}),
          "confirmEmail" -> text.verifying("Please confirm email.", {!_.isEmpty}),
          "password" -> text.verifying("password should not be empty Please enter.", {!_.isEmpty}) ,
          "confirmPassword" -> text.verifying("Please confirm entered password.", {!_.isEmpty}),
          "tconditions" -> checked("Please accept terms & conditions"))
          (Registration.apply)(Registration.unapply)
          ).bind(Map(
              "firstName" -> "",
              "surname" -> "",
              "email" -> "",
              "confirmEmail" -> "",
              "password" -> "",
              "confirmPassword" -> ""
              ))

        //val fakeRequest = fakeRequest(POST, "/register")

        val html: Html = views.html.register(form)
        val doc: Document = Jsoup.parse(contentAsString(html))

        doc.select("#validation-summary").text should(include ("firstName should not be empty Please enter."))
        doc.select("#validation-summary").text should(include ("surname should not be empty Please enter."))
        doc.select("#validation-summary").text should(include ("email should not be empty Please enter valid email."))
        doc.select("#validation-summary").text should(include ("Please confirm email."))
        doc.select("#validation-summary").text should(include ("password should not be empty Please enter."))
        doc.select("#validation-summary").text should(include ("Please confirm entered password."))
        doc.select("#validation-summary").text should(include ("Please accept terms & conditions"))

        doc.select("#error1").text should(include ("firstName should not be empty Please enter."))

        doc.select("#error2").text should(include ("surname should not be empty Please enter."))

        doc.select("#error3").text should(include ("email should not be empty Please enter valid email."))
        doc.select("#error4").text should(include ("Please confirm email."))
        
        doc.select("#error5").text should(include ("password should not be empty Please enter."))
        doc.select("#error6").text should(include ("Please confirm entered password."))

        doc.select("#error7").text should(include ("Please accept terms & conditions"))
      }
    })
  }

}
