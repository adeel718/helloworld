package controllers;

import static org.junit.Assert.*;
import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Http.Context;
import play.mvc.Http.Flash;
import play.test.FakeRequest;
import play.test.Helpers;

public class ApplicationControllerTest {
	
	@Before
	public void setUp() {
		final Context ctx = mock(Context.class);
		final Flash mockFlash = new Flash(new HashMap<String, String>());
		when(ctx.flash()).thenReturn(mockFlash);
		Context.current.set(ctx);
	}
	
	@Test
	public void controllerShouldRenderLoginView() {
		Result result =	Application.login();
//		System.out.println(result.getWrappedResult().toString());
		String s = Helpers.contentAsString(result);
		assertTrue((s).contains("Please sign in"));
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
		assertTrue((s).contains("Hello Adeel"));
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
		assertTrue((s).contains("Please sign in"));
		assertTrue(s.contains("value=\"" + username + "\""));
		assertTrue((s).contains("Username should not be empty."));
		assertThat(Helpers.status(result)).isEqualTo(Http.Status.BAD_REQUEST);
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
