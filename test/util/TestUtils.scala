package util

import org.scalatest.Matchers._
import play.utils.UriEncoding

object TestUtils {

  	def assertStatusOK(result: play.mvc.Result) = assertStatus(result, play.mvc.Http.Status.OK)
  	
	def assertStatusBadRequest(result: play.mvc.Result) = assertStatus(result, play.mvc.Http.Status.BAD_REQUEST)
	
	def assertStatusRedirect(result: play.mvc.Result) = assertStatus(result, play.mvc.Http.Status.SEE_OTHER)
	

	def assertStatus(result: play.mvc.Result, httpStatus: Int) = {
		Option(result) shouldBe ('defined)
		play.test.Helpers.status(result) shouldBe httpStatus;
	}

	def assertRedirect(result: play.mvc.Result, expectedLocation: String) = {
	  assertStatus(result, play.mvc.Http.Status.SEE_OTHER)
	  play.test.Helpers.redirectLocation(result) should equal(expectedLocation)
	}
  
}
