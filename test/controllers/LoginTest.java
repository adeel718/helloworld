package controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import views.BaseSeleniumTest;

public class LoginTest extends BaseSeleniumTest {

	@Test
	public void loginScreen() {
		driver.get(getBaseURL());
		assertEquals(getBaseURL() + "/login", driver.getCurrentUrl());
	}
}
