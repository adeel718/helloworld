package controllers

import play.api.mvc.{Action, Result, Controller}
import play.api.data.Form

import play.api.data.Forms._
import models.{User, Registration}
import views.html.{register, login}
import play.data.Form._
import play.api.i18n.Messages

/**
 * Created by user02 on 5/20/14.
 */
object UserRegistration extends Controller {

  val form =Form( mapping( "firstName" -> text.verifying(Messages("error.firstName.required"), {!_.isEmpty}),
    "surname" -> text.verifying("surname should not be empty Please enter.", {!_.isEmpty}),
    "email" -> text.verifying("email should not be empty Please enter valid email.", {!_.isEmpty}) ,
    "confirmEmail" -> text.verifying("Please confirm email.", {!_.isEmpty}) ,
     "password" -> text.verifying("password should not be empty Please enter.", {!_.isEmpty}) ,
     "confirmPassword" -> text.verifying("Please confirm entered password.", {!_.isEmpty}),
     "tconditions" -> checked("Please accept terms & conditions"))
      (Registration.apply)(Registration.unapply))

  def index() = Action {
    Ok(register(form))
  }

  def submitUserRegistration() = Action { implicit request: play.api.mvc.Request[_] =>

  val registrationForm = form.bindFromRequest()

    registrationForm.fold(
      formWithErrors => {
        BadRequest(views.html.register(formWithErrors))
      },
      userData => {
//        val validregistration = models.Registration
        //top be coded for later sprint i.er. to submit registration data to database
        Redirect(routes.Application.login())
      })
}

}
