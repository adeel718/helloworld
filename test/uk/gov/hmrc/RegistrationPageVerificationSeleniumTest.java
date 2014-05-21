package uk.gov.hmrc;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import views.BaseSeleniumTest;

public class RegistrationPageVerificationSeleniumTest extends BaseSeleniumTest {

	@Test
	public void myTest(){
		driver.navigate().to(getBaseURL());
		
		//Main Verification Tests
		
		String verifyTextLinkExp = "GOV .UK";
		String verifyTextLinkAct = driver.findElement(By.id("logo")).getText();
		assertEquals(verifyTextLinkExp, verifyTextLinkAct);
		
		String verifyAppTitleExp = "Hello World";
		String verifyAppTitleAct = driver.findElement(By.xpath("//a[@id='proposition-name']")).getText();
		assertEquals(verifyAppTitleExp, verifyAppTitleAct);
		
		String verifyMenuLinkExp = "Login";
		String verifyMenuLinkAct = driver.findElement(By.xpath(".//*[@id='proposition-links']/li/a")).getText();
		assertEquals(verifyMenuLinkExp, verifyMenuLinkAct);
		
		String verifyAppBanExp = "GOV.UK uses cookies to make the site simpler. Find out more about cookies";
		String verifyAppBanAct = driver.findElement(By.xpath(".//*[@id='global-cookie-message']/p")).getText();
		assertEquals(verifyAppBanExp, verifyAppBanAct);
		
		assertTrue(isElementPresent(By.cssSelector("img[alt=\'OGL\']")));
		
		String verifyFootTextLinkExp = "All content is available under the Open Government Licence v2.0, except where otherwise stated";
		String verifyFootTextLinkAct = driver.findElement(By.xpath(".//*[@id='footer']/div/div/div[1]/div/p")).getText();
		assertEquals(verifyFootTextLinkExp, verifyFootTextLinkAct);

		String verifyFootCopyRightLogoExp = "© Crown Copyright";
		String verifyFootCopyRightLogoAct = driver.findElement(By.xpath(".//*[@id='footer']/div/div/div[2]/a")).getText();
		assertEquals(verifyFootCopyRightLogoExp, verifyFootCopyRightLogoAct);
		
	}
		
		@Test
		public void registrationTests(){
			
		driver.navigate().to(getBaseURL() + "/register");
		//Registration Page Tests
		
		String verifyRegTitleExp = "Account Registration";
		String verifyRegTitleAct = driver.findElement(By.id("")).getText();
		assertEquals(verifyRegTitleExp, verifyRegTitleAct);
		
		String verifyRegDescriptionExp = "This page is the registration page for Hello World";
		String verifyRegDescriptionAct = driver.findElement(By.id("pageDescription")).getText();
		assertEquals(verifyRegDescriptionExp, verifyRegDescriptionAct);
		
		String verifyRegPersonalDetailsTitleExp = "Your Personal Details";
		String verifyRegPersonalDetailsTitleAct = driver.findElement(By.id("PersonalDetailsTitle")).getText();
		assertEquals(verifyRegPersonalDetailsTitleExp, verifyRegPersonalDetailsTitleAct);
		
		String verifyRegPersonalDetailsFNameExp = "First Name";
		String verifyRegPersonalDetailsFNameAct = driver.findElement(By.id("firstNameP")).getText();
		assertEquals(verifyRegPersonalDetailsFNameExp, verifyRegPersonalDetailsFNameAct);
		
		String verifyRegPersonalDetailsSNameExp = "Surname";
		String verifyRegPersonalDetailsSNameAct = driver.findElement(By.id("lastNameP")).getText();
		assertEquals(verifyRegPersonalDetailsSNameExp, verifyRegPersonalDetailsSNameAct);
		
		String verifyRegContactDetailsTitleExp = "Your Contact Details";
		String verifyRegContactDetailsTitleAct = driver.findElement(By.id("ContactDetailsTitle")).getText();
		assertEquals(verifyRegContactDetailsTitleExp, verifyRegContactDetailsTitleAct);
		
		String verifyRegContactDetailsCorrEmailExp = "Correspondance Email";
		String verifyRegContactDetailsCorrEmailAct = driver.findElement(By.id("emailP")).getText();
		assertEquals(verifyRegContactDetailsCorrEmailExp, verifyRegContactDetailsCorrEmailAct);
		
		String verifyRegContactDetailsConfEmailExp = "Confirm Email";
		String verifyRegContactDetailsConfEmailAct = driver.findElement(By.id("confirmEmailP")).getText();
		assertEquals(verifyRegContactDetailsConfEmailExp, verifyRegContactDetailsConfEmailAct);
		
		String verifyRegPasswordTitleExp = "Your Password";
		String verifyRegPasswordTitleAct = driver.findElement(By.id("PasswordDetailsTitle")).getText();
		assertEquals(verifyRegPasswordTitleExp, verifyRegPasswordTitleAct);
		
		String verifyRegPasswordPwordExp = "Password";
		String verifyRegPasswordPwordAct = driver.findElement(By.id("passwordP")).getText();
		assertEquals(verifyRegPasswordPwordExp, verifyRegPasswordPwordAct);
		
		String verifyRegPasswordReExp = "Re-enter Password";
		String verifyRegPasswordReAct = driver.findElement(By.id("confirmPasswordP")).getText();
		assertEquals(verifyRegPasswordReExp, verifyRegPasswordReAct);
		
		String verifyRegTandCTitleExp = "Terms and Conditions";
		String verifyRegTandCTitleAct = driver.findElement(By.id("TermsTitle")).getText();
		assertEquals(verifyRegTandCTitleExp, verifyRegTandCTitleAct);
		
		String verifyRegTandCDescExp = "Before registering, please read our Terms and Conditions";
		String verifyRegTandCDescAct = driver.findElement(By.id("TermsSubHeading")).getText();
		assertEquals(verifyRegTandCDescExp, verifyRegTandCDescAct);
		
		String verifyRegBoxTextExp = "I agree with the Terms and Conditions";
		String verifyRegBoxTextAct = driver.findElement(By.id("TermsP")).getText();
		assertEquals(verifyRegBoxTextExp, verifyRegBoxTextAct);
		
		assertTrue(isElementPresent(By.name("agreeCbx")));
		}
		
		@Test
		public void linkTests(){
			driver.navigate().to(getBaseURL() + "/register");
		
		//Links Navigation Tests
				driver.findElement(By.cssSelector("img[alt=\"OGL\"]")).click();
				assertEquals("Open Government Licence", driver.getTitle());
				driver.navigate().back();
				assertTrue(driver.getCurrentUrl().endsWith(getBaseURL()));
				
				driver.findElement(By.xpath(".//*[@id='logo']/span")).click();
				assertEquals("Welcome to GOV.UK", driver.getTitle());
				driver.navigate().back();
				assertTrue(driver.getCurrentUrl().endsWith(getBaseURL()));
				
//			    driver.findElement(By.linkText("Login")).click();	     
//			    assertEquals("Please sign in", driver.getTitle());
				
			    driver.findElement(By.linkText("Open Government Licence v2.0")).click();
		        assertEquals("Open Government Licence", driver.getTitle());
				driver.navigate().back();
			    assertEquals("Please sign in", driver.getTitle());
		      
			    driver.findElement(By.linkText("© Crown Copyright")).click();
			    assertEquals("Crown copyright | The National Archives", driver.getTitle());
				driver.navigate().back();
			    assertEquals("Please sign in", driver.getTitle());

			    driver.findElement(By.linkText("Find out more about cookies")).click();
			    assertEquals("Cookies - GOV.UK", driver.getTitle());
				driver.navigate().back();
			    assertEquals("Please sign in", driver.getTitle());
			      
				driver.findElement(By.id("proposition-name")).click();
				assertTrue(driver.getCurrentUrl().endsWith(getBaseURL()));
		
	}
		
		@After
		public void myAfter(){
			driver.quit();
			
		}

	 private boolean isElementPresent(By by) {
		    try {
		      driver.findElement(by);
		      return true;
		    } catch (NoSuchElementException e) {
		      return false;
		    }
		  }	
}
