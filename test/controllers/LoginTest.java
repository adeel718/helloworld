package controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LoginTest extends BaseSeleniumTest {

	@Test
	public void loginScreen() {
		driver.get(DEFAULT_URL);
		assertEquals(DEFAULT_URL + "/login", driver.getCurrentUrl());
	}
}
