package views;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.fakeGlobal;
import static play.test.Helpers.inMemoryDatabase;

import java.util.HashMap;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

import play.test.FakeApplication;
import play.test.TestServer;
import util.TestConfigurations;

public class BaseSeleniumTest {

	private static TestServer testServer;
	
	public static FakeApplication app;
	private String baseURL = "http://localhost:9000";
	public WebDriver driver;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		final Map<String, String> testConfig = new HashMap<>();
		testConfig.putAll(inMemoryDatabase());
		testConfig.putAll(TestConfigurations.mongoForJava());
		app = fakeApplication(testConfig, fakeGlobal());
		
		// Start test HTTP server
		testServer = new TestServer(9000, app);
		testServer.start();
		System.out.println("TestServer started");

		// Setup the chrome web driver
		/*File driverFile = new File("test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", driverFile.getAbsolutePath());*/
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		testServer.stop();
	}
	
	/*@Before
	public void setUp() throws Exception {			
		driver = new FirefoxDriver();
	    System.out.println("Started firefox driver");
	}*/

//	@After
//	public void tearDown() throws Exception {	
//		driver.quit();
//	}

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

}