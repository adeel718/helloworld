package controllers

import play.api.mvc.Controller
import play.api.mvc.Action

object Welcome extends Controller {

  def index(username: String) = Action {
	  Ok(views.html.welcome(username))
  }
}