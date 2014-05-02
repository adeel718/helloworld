package controllers;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import play.mvc.Http;
import play.mvc.Result;
import play.test.FakeRequest;
import play.test.Helpers;

public class ApplicationControllerUnitTest {
	
	@Test
	public void controllerShouldRenderLoginView() {
		Result result =	Application.login();
//		System.out.println(result.getWrappedResult().toString());
		String s = Helpers.contentAsString(result);
		assertTrue((s).contains("Please sign in")); // should render login page
		assertOk(result);
	}
	
	@Test
	public void controllerShouldRenderWelcomeViewOnSubmit() {
		// create a fake request
	    FakeRequest fakeRequest = new FakeRequest("POST", "/login");
	    // create an Hashmap for data
	    Map<String, String> data = new HashMap<String, String>();
	    data.put("userName", "Adeel");
		Result result = Helpers.callAction(controllers.routes.ref.Application.loginSubmit(), fakeRequest.withFormUrlEncodedBody(data));
		
		String s = Helpers.contentAsString(result);
		assertTrue((s).contains("Hello Adeel")); // should render welcome page
		assertOk(result);
	}
	
	@Test
	public void controllerShouldValidateOnSubmit() {
		final String username = "";
		assertNotValid(username);
	}

	@Test
	public void controllerShouldValidateBlankSpacesOnSubmit() {
		final String username = "  ";
		assertNotValid(username);
	}

	private void assertNotValid(final String username) {
		// create a fake request
	    FakeRequest fakeRequest = new FakeRequest("POST", "/login");
	    // create an Hashmap for data
	    Map<String, String> data = new HashMap<String, String>();
	    data.put("userName", username);
	    final Result result = Helpers.callAction(controllers.routes.ref.Application.loginSubmit(), fakeRequest.withFormUrlEncodedBody(data));
		
		final String s = Helpers.contentAsString(result);
		assertTrue((s).contains("Please sign in")); // should render login page
		assertTrue(s.contains("value=\"" + username + "\"")); // should prepopulate the username input field
		assertContainsTwice(s, "Please enter the user name field and it should not be blank"); // should show error in summary and the input field
		assertThat(Helpers.status(result)).isEqualTo(Http.Status.BAD_REQUEST); // should indicate an erroneous submission
	}
	
	private void assertContainsTwice(String html, String errorMessage) {
		assertNotNull(errorMessage);
		final int firstIndexOf = html.indexOf(errorMessage);
		assertThat(firstIndexOf).isPositive();
		final int secondIndexOf = html.substring(firstIndexOf + errorMessage.length()).indexOf(errorMessage);
		assertThat(secondIndexOf).isPositive();
	}
	
	private void assertOk(Result result) {
		assertNotNull(result);
		assertThat(Helpers.status(result)).isEqualTo(Http.Status.OK);
	}
	
	////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void controllerShouldRedirectIndexToLogin() {
		final Result result = Application.index();
		
		// index should redirect to /login
		assertThat(Helpers.status(result)).isEqualTo(Http.Status.SEE_OTHER);
		assertThat(Helpers.redirectLocation(result)).isEqualTo("/login");
	}
	
	
}
