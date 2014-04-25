package controllers;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.fakeGlobal;
import static play.test.Helpers.inMemoryDatabase;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import play.test.FakeApplication;
import play.test.TestServer;

public class BaseSeleniumTest {

	private static TestServer testServer;
	
	public static FakeApplication app;
	protected static final String DEFAULT_URL = "http://localhost:9000";
	protected static WebDriver driver;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		app = fakeApplication(inMemoryDatabase(), fakeGlobal());
		
		// Start test HTTP server
		testServer = new TestServer(9000, app);
		testServer.start();

		// Setup the web driver
		File driverFile = new File("c:/Dev/selenium/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", driverFile.getAbsolutePath());
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		testServer.stop();
	}
	
	@Before
	public void setUp() throws Exception {	
		String ymlFile = "initial-data.yml";
		System.out.println("BaseSeleniumTest: adding test data from file " + ymlFile + "...");
		System.out.println("BaseSeleniumTest: test data import complete");
		
		driver = new ChromeDriver();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

}