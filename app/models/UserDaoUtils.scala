package models
import com.mongodb.casbah.commons.MongoDBObject

object UserDaoUtils {
	def findUser(email: String, password: String) =
	  RegisteredUserDAO.findOne(MongoDBObject("_id" -> email, "password" -> password))
	
	def isUserExist(email: String, password: String): Boolean = 
	  findUser(email, password).isDefined
}