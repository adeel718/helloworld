package controllers

import play.api.mvc.{Action, Controller}
import play.api.data.Form
import play.api.data.Forms._
import models.{RegisteredUser,  Registration, RegisteredUserDAO}
import views.html.register
import play.api.i18n.Messages

/**
 * Created by user02 on 5/20/14.
 */
object UserRegistration extends Controller {

  def form =Form( mapping( "firstName" -> text.verifying(Messages("error.registration.field.required", "First Name"), {!_.isEmpty}),
    "surname" -> text.verifying(Messages("error.registration.field.required", "Surname"), {!_.isEmpty}),
    "email" -> text.verifying(Messages("error.email.required"), {!_.isEmpty}) ,
    "confirmEmail" -> text.verifying(Messages("error.confirmEmail.required"), {!_.isEmpty}) ,
     "password" -> text.verifying(Messages("error.registration.field.required", "Password"), {!_.isEmpty}) ,
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
        //top be coded for later sprint i.er. to submit registration data to database
        val Registration(firstName, surname, email, _, password, _, _) = userData

        RegisteredUserDAO.insert(RegisteredUser(email, firstName, surname, password))
        Redirect(routes.Application.login()).flashing(
            "message" -> "Registration was successful, use the form below to login"
        )
      })
  }
}
