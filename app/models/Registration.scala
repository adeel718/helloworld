package models

import play.data.validation.Constraints.Required

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
