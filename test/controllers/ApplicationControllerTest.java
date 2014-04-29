package controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import play.mvc.Result;
import play.mvc.Http.Context;
import play.mvc.Http.Flash;
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
	}
	
	@Test
	public void controllerShouldRenderWelcomeViewOnSubmit() {
		Result result =	Application.loginSubmit("Adeel");
		String s = Helpers.contentAsString(result);
		assertTrue((s).contains("Hello Adeel"));
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
		final Result result = Application.loginSubmit(username);
		final String s = Helpers.contentAsString(result);
		assertTrue(s.contains("value=\"" + username + "\""));
		assertTrue((s).contains("Username should not be empty."));
	}
	
}
