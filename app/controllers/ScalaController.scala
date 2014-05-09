package controllers

import play.api.mvc.Controller
import play.api.mvc.Action

object ScalaController extends Controller {
  
  def index() = Action {
    Ok("Hi")
  }
  

}