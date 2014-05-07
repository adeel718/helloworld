
package ukgovhmrc;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import views.BaseSeleniumTest;

public class MainPageVerfication extends BaseSeleniumTest {
	WebDriver webDriver;
	String vURL;

	@Before
	public void myBefore(){
		webDriver = new FirefoxDriver();
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		vURL = "http://localhost:9000/login";
	}
	
	@Test
	public void myTest(){
		webDriver.navigate().to(vURL);
		
		String verifyTextLinkExp = "GOV .UK";
		String verifyTextLinkAct = webDriver.findElement(By.id("logo")).getText();
		assertEquals(verifyTextLinkExp, verifyTextLinkAct);
		System.out.println("Expected: "+verifyTextLinkExp+ "-" +", Actual: "+ verifyTextLinkAct );
		
		String verifyAppTitleExp = "Hello World";
		String verifyAppTitleAct = webDriver.findElement(By.xpath("//a[@id='proposition-name']")).getText();
		assertEquals(verifyAppTitleExp, verifyAppTitleAct);
		System.out.println("Expected: "+verifyAppTitleExp+ "-" +" Actual: "+ verifyAppTitleAct );
		
		String verifyMenuLinkExp = "Login";
		String verifyMenuLinkAct = webDriver.findElement(By.xpath(".//*[@id='proposition-links']/li/a")).getText();
		assertEquals(verifyAppTitleExp, verifyAppTitleAct);
		System.out.println("Expected: "+verifyMenuLinkExp+ "-" +" Actual: "+ verifyMenuLinkAct );
		
		String verifyAppBanExp = "GOV.UK uses cookies to make the site simpler. Find out more about cookies";
		String verifyAppBanAct = webDriver.findElement(By.xpath(".//*[@id='global-cookie-message']/p")).getText();
		assertEquals(verifyAppTitleExp, verifyAppTitleAct);
		System.out.println("Expected: "+verifyAppBanExp+ "-" +" Actual: "+ verifyAppBanAct );
		
		assertTrue(isElementPresent(By.cssSelector("img[alt=\"OGL\"]")));
		 
		String verifyFootTextLinkExp = "All content is available under the Open Government Licence v2.0, except where otherwise stated";
		String verifyFootTextLinkAct = webDriver.findElement(By.xpath(".//*[@id='footer']/div/div/div[1]/div/p")).getText();
		assertEquals(verifyFootTextLinkExp, verifyFootTextLinkAct);

		
		String verifyFootCopyRightLogoExp = "© Crown Copyright";
		String verifyFootCopyRightLogoAct = webDriver.findElement(By.xpath(".//*[@id='footer']/div/div/div[2]/a")).getText();
		assertEquals(verifyFootCopyRightLogoExp, verifyFootCopyRightLogoAct);
		
		
	     String titleAct = webDriver.findElement(By.xpath(".//*[@id='content']/div/div/div/h1")).getText();
	     String titleExp = "Please sign in ALPHA";
	     assertEquals(titleExp,titleAct);
	     
	     String verifyUserNameLabelExp = "Username";
	     String verifyUserNameLabelAct = webDriver.findElement(By.xpath(".//*[@id='userName_field']/dt/label")).getText();
	     assertEquals(verifyUserNameLabelExp, verifyUserNameLabelAct);
	     
	     
	     assertTrue(isElementPresent(By.xpath(".//*[@id='userName']")));
	     assertTrue(isElementPresent(By.name("resetBtn")));
	     assertTrue(isElementPresent(By.name("submitBtn")));
	      assertTrue(isElementPresent(By.className("info")));
	      

		//Links Navigation
		webDriver.findElement(By.cssSelector("img[alt=\"OGL\"]")).click();
		assertEquals("Open Government Licence", webDriver.getTitle());
		webDriver.navigate().back();
		assertTrue(webDriver.getCurrentUrl().endsWith(vURL));
		
		webDriver.findElement(By.xpath(".//*[@id='logo']/span")).click();
		assertEquals("Welcome to GOV.UK", webDriver.getTitle());
		webDriver.navigate().back();
		assertTrue(webDriver.getCurrentUrl().endsWith(vURL));		
		
	    webDriver.findElement(By.linkText("Login")).click();	     
	    assertEquals("Please sign in", webDriver.getTitle());
	    webDriver.findElement(By.linkText("Open Government Licence v2.0")).click();
	    assertEquals("Open Government Licence", webDriver.getTitle());
		webDriver.navigate().back();
	    assertEquals("Please sign in", webDriver.getTitle());
	      
	    webDriver.findElement(By.linkText("© Crown Copyright")).click();
	    assertEquals("Crown copyright | The National Archives", webDriver.getTitle());
		webDriver.navigate().back();
	    assertEquals("Please sign in", webDriver.getTitle());

	    webDriver.findElement(By.linkText("Find out more about cookies")).click();
	    assertEquals("Cookies - GOV.UK", webDriver.getTitle());
		webDriver.navigate().back();
	    assertEquals("Please sign in", webDriver.getTitle());
	      
		webDriver.findElement(By.id("proposition-name")).click();
		assertTrue(webDriver.getCurrentUrl().endsWith(vURL));		      
	}
	
	@After
	public void myAfter(){
		webDriver.quit();
		
	}
	
	 private boolean isElementPresent(By by) {
		    try {
		      webDriver.findElement(by);
		      return true;
		    } catch (NoSuchElementException e) {
		      return false;
		    }
		  }	
}
