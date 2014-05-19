package uk.gov.hmrc

import org.openqa.selenium.WebDriver
import org.scalatest.BeforeAndAfterAll
import org.scalatest.FunSuite
import org.scalatest.Matchers
import org.scalatest.concurrent.Eventually.eventually
import org.scalatest.concurrent.Eventually.patienceConfig
import org.scalatest.selenium.Firefox
import org.scalatest.selenium.WebBrowser
import org.scalatest.time.Seconds
import org.scalatest.time.Span
import play.api.test.FakeApplication
import play.api.test.TestServer
import org.scalatest.BeforeAndAfter

abstract class BaseSeleniumScalaTest extends FunSuite with BeforeAndAfterAll with Matchers with WebBrowser {
  
	implicit val webDriver: WebDriver // to be overriden by implementors

	val port = 9000
	private val testServer: TestServer = TestServer(port, FakeApplication())
	
	override def beforeAll = {
		testServer.start();
		info("TestServer started " + this.getClass().getName());
	}
	
	override def afterAll() {
		// stop Play test server
		testServer.stop();
		info("TestServer stopped " + this.getClass().getName());
	}
}

class MySeleniumScalaTest extends BaseSeleniumScalaTest with BeforeAndAfter with Firefox {
  
  	val vURL = "http://localhost:" + port + "/login";
  	
  	  before {
		implicitlyWait(Span(10, Seconds)) // webdriver
		info("in before")
	  }
	
	  after {
		// close browser and quit web driver
		quit
		info("in after")
	  }
	
	test("testing...") {
		go to (vURL)
		
		val verifyTextLinkExp = "GOV .UK"
		val verifyTextLinkAct = find("logo").map(_.text).get
		info("Expected: "+verifyTextLinkExp+ "-" +", Actual: "+ verifyTextLinkAct)
		verifyTextLinkAct should be (verifyTextLinkExp)
		
		val verifyAppTitleExp = "Hello World";
		val verifyAppTitleAct = xpath("//a[@id='proposition-name']").element.text
		info("Expected: "+verifyAppTitleExp+ "-" +" Actual: "+ verifyAppTitleAct );
		verifyAppTitleAct should be (verifyAppTitleExp)
		
		//Links Navigation
		click on cssSelector("img[alt=\"OGL\"]");
		eventually { pageTitle should be ("Open Government Licence") }
		
		goBack
		
		currentUrl should endWith (vURL);
	}

/*	
    test("Should fail...") {
    		go to (vURL)
			val verifyTextLinkAct = id("logoz").element
    }
*/
	
}





