package uk.gov.hmrc;

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

public class RegistrationPageVerificationSeleniumTest {
	
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
		
		//Main Verification Tests
		
		String verifyTextLinkExp = "GOV .UK";
		String verifyTextLinkAct = webDriver.findElement(By.id("logo")).getText();
		assertEquals(verifyTextLinkExp, verifyTextLinkAct);
		
		String verifyAppTitleExp = "Hello World";
		String verifyAppTitleAct = webDriver.findElement(By.xpath("//a[@id='proposition-name']")).getText();
		assertEquals(verifyAppTitleExp, verifyAppTitleAct);
		
		String verifyMenuLinkExp = "Login";
		String verifyMenuLinkAct = webDriver.findElement(By.xpath(".//*[@id='proposition-links']/li/a")).getText();
		assertEquals(verifyMenuLinkExp, verifyMenuLinkAct);
		
		String verifyAppBanExp = "GOV.UK uses cookies to make the site simpler. Find out more about cookies";
		String verifyAppBanAct = webDriver.findElement(By.xpath(".//*[@id='global-cookie-message']/p")).getText();
		assertEquals(verifyAppBanExp, verifyAppBanAct);
		
		assertTrue(isElementPresent(By.cssSelector("img[alt=\"OGL\"]")));
		
		String verifyFootTextLinkExp = "All content is available under the Open Government Licence v2.0, except where otherwise stated";
		String verifyFootTextLinkAct = webDriver.findElement(By.xpath(".//*[@id='footer']/div/div/div[1]/div/p")).getText();
		assertEquals(verifyFootTextLinkExp, verifyFootTextLinkAct);

		String verifyFootCopyRightLogoExp = "© Crown Copyright";
		String verifyFootCopyRightLogoAct = webDriver.findElement(By.xpath(".//*[@id='footer']/div/div/div[2]/a")).getText();
		assertEquals(verifyFootCopyRightLogoExp, verifyFootCopyRightLogoAct);
		
	}
		
		@Test
		public void registrationTests(){
			
		
		//Registration Page Tests
		
//		String verifyRegTitleExp = "Account Registration";
//		String verifyRegTitleAct = webDriver.findElement(By.id("")).getText();
//		assertEquals(verifyRegTitleExp, verifyRegTitleAct);
		
		String verifyRegDescriptionExp = "This page is the registration page for Hello World";
		String verifyRegDescriptionAct = webDriver.findElement(By.id("pageDescription")).getText();
		assertEquals(verifyRegDescriptionExp, verifyRegDescriptionAct);
		
		String verifyRegPersonalDetailsTitleExp = "Your Personal Details";
		String verifyRegPersonalDetailsTitleAct = webDriver.findElement(By.id("PersonalDetailsTitle")).getText();
		assertEquals(verifyRegPersonalDetailsTitleExp, verifyRegPersonalDetailsTitleAct);
		
		String verifyRegPersonalDetailsFNameExp = "First Name";
		String verifyRegPersonalDetailsFNameAct = webDriver.findElement(By.id("firstNameP")).getText();
		assertEquals(verifyRegPersonalDetailsFNameExp, verifyRegPersonalDetailsFNameAct);
		
		String verifyRegPersonalDetailsSNameExp = "Surname";
		String verifyRegPersonalDetailsSNameAct = webDriver.findElement(By.id("lastNameP")).getText();
		assertEquals(verifyRegPersonalDetailsSNameExp, verifyRegPersonalDetailsSNameAct);
		
		String verifyRegContactDetailsTitleExp = "Your Contact Details";
		String verifyRegContactDetailsTitleAct = webDriver.findElement(By.id("ContactDetailsTitle")).getText();
		assertEquals(verifyRegContactDetailsTitleExp, verifyRegContactDetailsTitleAct);
		
		String verifyRegContactDetailsCorrEmailExp = "Correspondence Email";
		String verifyRegContactDetailsCorrEmailAct = webDriver.findElement(By.id("emailP")).getText();
		assertEquals(verifyRegContactDetailsCorrEmailExp, verifyRegContactDetailsCorrEmailAct);
		
		String verifyRegContactDetailsConfEmailExp = "Confirm Email";
		String verifyRegContactDetailsConfEmailAct = webDriver.findElement(By.id("confirmEmailP")).getText();
		assertEquals(verifyRegContactDetailsConfEmailExp, verifyRegContactDetailsConfEmailAct);
		
		String verifyRegPasswordTitleExp = "Your Password";
		String verifyRegPasswordTitleAct = webDriver.findElement(By.id("PasswordDetailsTitle")).getText();
		assertEquals(verifyRegPasswordTitleExp, verifyRegPasswordTitleAct);
		
		String verifyRegPasswordPwordExp = "Password";
		String verifyRegPasswordPwordAct = webDriver.findElement(By.id("passwordP")).getText();
		assertEquals(verifyRegPasswordPwordExp, verifyRegPasswordPwordAct);
		
		String verifyRegPasswordReExp = "Re-enter Password";
		String verifyRegPasswordReAct = webDriver.findElement(By.id("confirmPasswordP")).getText();
		assertEquals(verifyRegPasswordReExp, verifyRegPasswordReAct);
		
		String verifyRegTandCTitleExp = "Terms and Conditions";
		String verifyRegTandCTitleAct = webDriver.findElement(By.id("TermsTitle")).getText();
		assertEquals(verifyRegTandCTitleExp, verifyRegTandCTitleAct);
		
		String verifyRegTandCDescExp = "Before registering, please read our Terms and Conditions";
		String verifyRegTandCDescAct = webDriver.findElement(By.id("TermsSubHeading")).getText();
		assertEquals(verifyRegTandCDescExp, verifyRegTandCDescAct);
		
		String verifyRegBoxTextExp = "I agree with the Terms and Conditions";
		String verifyRegBoxTextAct = webDriver.findElement(By.id("TermsP")).getText();
		assertEquals(verifyRegBoxTextExp, verifyRegBoxTextAct);
		
		assertTrue(isElementPresent(By.name("agreeCbx")));
		}
		
		@Test
		public void linkTests(){
			webDriver.navigate().to(vURL);
		
		//Links Navigation Tests
				webDriver.findElement(By.cssSelector("img[alt=\"OGL\"]")).click();
				assertEquals("Open Government Licence", webDriver.getTitle());
				webDriver.navigate().back();
				assertTrue(webDriver.getCurrentUrl().endsWith(vURL));
				
				webDriver.findElement(By.xpath(".//*[@id='logo']/span")).click();
				assertEquals("Welcome to GOV.UK", webDriver.getTitle());
				webDriver.navigate().back();
				assertTrue(webDriver.getCurrentUrl().endsWith(vURL));	
				
//			    webDriver.findElement(By.linkText("Login")).click();	     
//			    assertEquals("Please sign in", webDriver.getTitle());
				
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
