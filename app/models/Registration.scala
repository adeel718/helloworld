package models

import play.data.validation.Constraints.Required
import com.novus.salat.annotations.Key

/**
 * Created by user02 on 5/20/14.
 */

case class Registration (firstName : String,
                         surname : String ,
                         email : String ,
                         confirmEmail : String,
                         password : String,
                         confirmPassword : String,
                         tconditions:Boolean)
object Registration {


}


case class RegisteredUser (@Key("_id") email : String,
						 firstName : String,
                         surname : String,
                         password : String)

import com.novus.salat.dao.SalatDAO
import db.mongo.MongoDB
import com.novus.salat._
import com.novus.salat.annotations._
import com.novus.salat.global._


object RegisteredUserDAO extends SalatDAO[RegisteredUser, String](collection = MongoDB.collection("users")) {

  
}