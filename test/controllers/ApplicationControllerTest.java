package controllers;

import static org.junit.Assert.*;

import org.junit.Test;

import play.mvc.Result;
import play.test.Helpers;
import views.BaseSeleniumTest;

public class ApplicationControllerTest extends BaseSeleniumTest {

	
	@Test
	public void test() {
	Result result =	Application.index();
	System.out.println(result.getWrappedResult().toString());
	String s = Helpers.contentAsString(result);
	assertSame((s).contains("Your new application is ready."), true);
	}

}
