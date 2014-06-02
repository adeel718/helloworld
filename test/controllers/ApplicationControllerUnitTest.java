package controllers;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import play.mvc.Http;
import play.mvc.Result;
import play.test.FakeRequest;
import play.test.Helpers;
import util.JsoupUtils;
import util.TestUtils;

public class ApplicationControllerUnitTest {
	
	@Test
	public void controllerShouldRenderLoginView() {
		Result result =	Application.login();
//		System.out.println(result.getWrappedResult().toString());
		String s = Helpers.contentAsString(result);
		assertTrue((s).contains("Please sign in")); // should render login page
		TestUtils.assertStatusOK(result);
	}
	
	@Test
	public void controllerShouldValidateOnSubmit() {
		final String username = "";
		assertNotValidUsername(username);
	}

	@Test
	public void controllerShouldValidateBlankSpacesOnSubmit() {
		final String username = "  ";
		assertNotValidUsername(username);
	}

	// TODO: Move to integration test
	private void assertNotValidUsername(final String username) {
		// create a fake request
	    FakeRequest fakeRequest = new FakeRequest("POST", "/login");
	    // create an Hashmap for data
	    Map<String, String> data = new HashMap<String, String>();
	    data.put("userName", username);
	    final Result result = Helpers.callAction(controllers.routes.ref.Application.loginSubmit(), fakeRequest.withFormUrlEncodedBody(data));

	    TestUtils.assertStatusBadRequest(result); // should indicate an erroneous submission
		
		final String html = Helpers.contentAsString(result);
		assertTrue((html).contains("Please sign in")); // should render login page

		final Document doc = Jsoup.parse(html);
		JsoupUtils.assertErrorPresent("userName", "Please enter the user name field and it should not be blank", doc);
	}
	
	////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void controllerShouldRedirectIndexToLogin() {
		final Result result = Application.index();
		
		// index should redirect to /login
		TestUtils.assertRedirect(result, "/login");
	}
	
	
}
