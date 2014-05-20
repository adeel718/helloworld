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
        val form =Form( mapping( "firstName" -> nonEmptyText, "surname" -> nonEmptyText, "email" -> nonEmptyText ,
          "confirmEmail" -> nonEmptyText, "password" -> nonEmptyText ,"confirmPassword" -> nonEmptyText)(Registration.apply)(Registration.unapply))

        //Context.current.set(TestSetup.testHttpContext)
        val html: Html = views.html.register(form)
        val doc: Document = Jsoup.parse(contentAsString(html))

        doc.select("#title").text.should(equal ("Account Registration"))

        doc.select("#personalDetailsTitle").text.should(equal ("Your Personal Details"))

        doc.select("#firstNameLabel").text should(equal ("First Name:"))
        doc.select("#firstName").`val`() should(equal (""))

        doc.select("#lastNameLabel").text should(equal ("Surname:"))
        doc.select("#lastName").`val`() should(equal (""))

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

        doc.select("#agreeCbx").attr("checked") should(equal ("no"))
        doc.select("#agreeCbxLabel").text() should(equal ("I agree with Terms & Conditions"))


        doc.select("#submitBtn").attr("value") should(equal ("Proceed>>"))
      }
    })
  }

  test("Check if validation is in place and error messages shown") {
    //val data = Map("")

    /*
    running(fakeApplication, new Runnable {
      def run {

        val fakeRequest = fakeRequest(POST, "/register")

        val html: Html = views.html.register.render()
        val doc: Document = Jsoup.parse(contentAsString(html))

        doc.select("#firstNameLabel").text should(equal ("First Name"))
      }
    })*/
  }

}
