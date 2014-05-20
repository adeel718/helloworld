package controllers

import play.api.mvc.{Action, Result, Controller}
import play.api.data.Form

import play.api.data.Forms._
import models.{User, Registration}
import views.html.{register, login}
import play.data.Form._

/**
 * Created by user02 on 5/20/14.
 */
object UserRegistration extends Controller {

  val form =Form( mapping( "firstName" -> nonEmptyText, "surname" -> nonEmptyText, "email" -> nonEmptyText ,
    "confirmEmail" -> nonEmptyText, "password" -> nonEmptyText ,"confirmPassword" -> nonEmptyText)
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
