package controllers

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import play.api.test.WithApplication
import play.api.test.FakeApplication
import util.TestConfigurations
import org.jsoup.Jsoup
import util.TestUtils
import util.JsoupUtils
import db.mongo.MongoDB
import com.mongodb.casbah.commons.MongoDBObject
import scala.collection.JavaConversions._
import models.RegisteredUserDAO
import models.RegisteredUser
import java.net.URLEncoder

class ApplicationControllerIntegrationTest extends FlatSpec with Matchers {

  "Application controller" should "reject unknown users" in 
  	new WithApplication(FakeApplication(additionalConfiguration = TestConfigurations.mongo)) {
		// 1. setup mongo test instance done through application
	  	MongoDB().name shouldBe TestConfigurations.mongo(MongoDB.KEY_DB)
		
	  	
	    // 3. call controller with any unexisting username/password
	    val fakeRequest = new play.test.FakeRequest();
	    val result = play.test.Helpers.callAction(
	    		controllers.routes.ref.Application.loginSubmit(), 
	    		fakeRequest.withFormUrlEncodedBody(
	    		    Map(
	    		    ("userName", "someuser@somedomain.some"),
	    		    ("password", "somepassword"))
	    		)
	    )

	    TestUtils.assertStatusBadRequest(result); // should indicate an erroneous submission
	    
		val html = play.test.Helpers.contentAsString(result);
		html should include("Please sign in"); // should render login page
		
		implicit val doc = Jsoup.parse(html);
		JsoupUtils.assertErrorPresent("userName", "The entered username and/or password is not correct");
		JsoupUtils.assertErrorPresent("password", "The entered username and/or password is not correct");
  }
  
  it should "redirect to Welcome View On Submit" in 
  	new WithApplication(FakeApplication(additionalConfiguration = TestConfigurations.mongo)) {
		// 1. setup mongo test instance done through application
	  	MongoDB().name shouldBe TestConfigurations.mongo(MongoDB.KEY_DB)
	  	
	  	val username = "some.user@somewhere.some"
	  	val password = "somepassword"
  	  
  	  val users = MongoDB.collection("users")
  	  users.remove(MongoDBObject("_id" -> username))
  	  users.insert(MongoDBObject("_id" -> username, 
  	      "password" -> password,
  	      "firstName" -> "David",
  	      "surname"	-> "Backhome"))
  	  
	    // 2. call controller with an existing username/password
	    val fakeRequest = new play.test.FakeRequest();
	    val result = play.test.Helpers.callAction(
	    		controllers.routes.ref.Application.loginSubmit(), 
	    		fakeRequest.withFormUrlEncodedBody(
	    		    Map(
	    		    ("userName", username),
	    		    ("password", password))
	    		)
	    )

	    TestUtils.assertRedirect(result, "/welcome?username=" + URLEncoder.encode(username, "UTF-8")) // should redirect
    
  }
 
}