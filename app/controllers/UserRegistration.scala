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

  val form =Form( mapping( "firstName" -> text.verifying(Messages("error.registration.field.required", "firstName"), {!_.isEmpty}),
    "surname" -> text.verifying(Messages("error.registration.field.required", "surname"), {!_.isEmpty}),
    "email" -> text.verifying(Messages("error.email.required"), {!_.isEmpty}) ,
    "confirmEmail" -> text.verifying(Messages("error.confirmEmail.required"), {!_.isEmpty}) ,
     "password" -> text.verifying(Messages("error.registration.field.required", "password"), {!_.isEmpty}) ,
     "confirmPassword" -> text.verifying(Messages("error.confirmPassword.required"), {!_.isEmpty}),
     "tconditions" -> checked(Messages("error.tconditions.required")))
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
