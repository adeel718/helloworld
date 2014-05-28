package uk.gov.hmrc;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import views.BaseSeleniumTest;
import play.api.i18n.Messages;

public class RegistrationPageVerificationSeleniumTest extends BaseSeleniumTest {
    @Before
    public void myBefore(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

	/*@Test
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
		String verifyMenuLinkAct = driver.findElement(By.xpath("./*//*[@id='proposition-links']/li/a")).getText();
		assertEquals(verifyMenuLinkExp, verifyMenuLinkAct);
		
		String verifyAppBanExp = "GOV.UK uses cookies to make the site simpler. Find out more about cookies";
		String verifyAppBanAct = driver.findElement(By.xpath("./*//*[@id='global-cookie-message']/p")).getText();
		assertEquals(verifyAppBanExp, verifyAppBanAct);
		
		assertTrue(isElementPresent(By.cssSelector("img[alt=\'OGL\']")));
		
		String verifyFootTextLinkExp = "All content is available under the Open Government Licence v2.0, except where otherwise stated";
		String verifyFootTextLinkAct = driver.findElement(By.xpath("./*//*[@id='footer']/div/div/div[1]/div/p")).getText();
		assertEquals(verifyFootTextLinkExp, verifyFootTextLinkAct);

		String verifyFootCopyRightLogoExp = "© Crown Copyright";
		String verifyFootCopyRightLogoAct = driver.findElement(By.xpath("./*//*[@id='footer']/div/div/div[2]/a")).getText();
		assertEquals(verifyFootCopyRightLogoExp, verifyFootCopyRightLogoAct);
		
	}
		
    @Test
    public void registrationTests(){

        driver.navigate().to(getBaseURL() + "/register");
        //Registration Page Tests

        String verifyRegTitleExp = "Account Registration";
        String verifyRegTitleAct = driver.findElement(By.id("title")).getText();
        assertEquals(verifyRegTitleExp, verifyRegTitleAct);

        String verifyRegDescriptionExp = "This page is the registration page for Hello World";
        String verifyRegDescriptionAct = driver.findElement(By.id("pageDescription")).getText();
        assertEquals(verifyRegDescriptionExp, verifyRegDescriptionAct);

        String verifyRegPersonalDetailsTitleExp = "Your Personal Details";
        String verifyRegPersonalDetailsTitleAct = driver.findElement(By.id("personalDetailsTitle")).getText();
        assertEquals(verifyRegPersonalDetailsTitleExp, verifyRegPersonalDetailsTitleAct);

        String verifyRegPersonalDetailsFNameExp = "First Name:";
        String verifyRegPersonalDetailsFNameAct = driver.findElement(By.id("firstNameP")).getText();
        assertEquals(verifyRegPersonalDetailsFNameExp, verifyRegPersonalDetailsFNameAct);

        String verifyRegPersonalDetailsSNameExp = "Surname:";
        String verifyRegPersonalDetailsSNameAct = driver.findElement(By.id("lastNameP")).getText();
        assertEquals(verifyRegPersonalDetailsSNameExp, verifyRegPersonalDetailsSNameAct);

        String verifyRegContactDetailsTitleExp = "Your Contact Details";
        String verifyRegContactDetailsTitleAct = driver.findElement(By.id("contactDetailsTitle")).getText();
        assertEquals(verifyRegContactDetailsTitleExp, verifyRegContactDetailsTitleAct);

        String verifyRegContactDetailsCorrEmailExp = "Email:";
        String verifyRegContactDetailsCorrEmailAct = driver.findElement(By.id("emailP")).getText();
        assertEquals(verifyRegContactDetailsCorrEmailExp, verifyRegContactDetailsCorrEmailAct);

        String verifyRegContactDetailsConfEmailExp = "Confirm Email:";
        String verifyRegContactDetailsConfEmailAct = driver.findElement(By.id("confirmEmailP")).getText();
        assertEquals(verifyRegContactDetailsConfEmailExp, verifyRegContactDetailsConfEmailAct);

        String verifyRegPasswordTitleExp = "Your Password";
        String verifyRegPasswordTitleAct = driver.findElement(By.id("passwordDetailsTitle")).getText();
        assertEquals(verifyRegPasswordTitleExp, verifyRegPasswordTitleAct);

        String verifyRegPasswordPwordExp = "Password:";
        String verifyRegPasswordPwordAct = driver.findElement(By.id("passwordP")).getText();
        assertEquals(verifyRegPasswordPwordExp, verifyRegPasswordPwordAct);

        String verifyRegPasswordReExp = "Re-enter Password:";
        String verifyRegPasswordReAct = driver.findElement(By.id("confirmPasswordP")).getText();
        assertEquals(verifyRegPasswordReExp, verifyRegPasswordReAct);

        String verifyRegTandCTitleExp = "Terms and Conditions";
        String verifyRegTandCTitleAct = driver.findElement(By.id("termsTitle")).getText();
        assertEquals(verifyRegTandCTitleExp, verifyRegTandCTitleAct);

        String verifyRegTandCDescExp = "Before registering, please read our Terms and Conditions";
        String verifyRegTandCDescAct = driver.findElement(By.id("TermsSubHeading")).getText();
        assertEquals(verifyRegTandCDescExp, verifyRegTandCDescAct);

        String verifyRegBoxTextExp = "I agree with the Terms and Conditions";
        String verifyRegBoxTextAct = driver.findElement(By.id("TermsP")).getText();
        assertEquals(verifyRegBoxTextExp, verifyRegBoxTextAct);

        assertTrue(isElementPresent(By.name("tconditions")));
    }
		
    @Test
    public void linkTests(){
        driver.navigate().to(getBaseURL() + "/register");

        //Links Navigation Tests
        driver.findElement(By.cssSelector("img[alt=\'OGL\']")).click();
        assertEquals("Open Government Licence", driver.getTitle());
        driver.navigate().back();
        assertTrue(driver.getCurrentUrl().endsWith(getBaseURL()));

        driver.findElement(By.xpath("./*//*[@id='logo']/span")).click();
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

    @Test
    public void checkValidationMessages() {
        driver.navigate().to((getBaseURL() + "/register"));

        // Submit blank
        driver.findElement(By.id("submitBtn")).click();

        String validationMsgFirstNameExp = "firstName should not be empty, please enter a value";
        String validationMsgFirstNameAct = driver.findElement(By.id("error1")).getText();
        assertEquals(validationMsgFirstNameExp, validationMsgFirstNameAct);

        String validationMsgSurnameExp = "surName should not be empty, please enter a value";
        String validationMsgSurnameAct = driver.findElement(By.id("error2")).getText();
        assertEquals(validationMsgSurnameExp, validationMsgSurnameAct);

        String validationMsgEmailExp = "Email should not be empty, please enter a value";
        String validationMsgEmailAct = driver.findElement(By.id("error3")).getText();
        assertEquals(validationMsgEmailExp, validationMsgEmailAct);

        String validationMsgConfirmEmailExp = "Please confirm email";
        String validationMsgConfirmEmailAct = driver.findElement(By.id("error4")).getText();
        assertEquals(validationMsgConfirmEmailExp, validationMsgConfirmEmailAct);

        String validationMsgPasswordExp = "password should not be empty, please enter a value";
        String validationMsgPasswordAct = driver.findElement(By.id("error5")).getText();
        assertEquals(validationMsgPasswordExp, validationMsgPasswordAct);

        String validationMsgConfirmPasswordExp = "Please confirm password";
        String validationMsgConfirmPasswordAct = driver.findElement(By.id("error6")).getText();
        assertEquals(validationMsgConfirmPasswordExp, validationMsgConfirmPasswordAct);

        String validationMsgTermsExp = "Please accept terms & conditions";
        String validationMsgTermsAct = driver.findElement(By.id("error7")).getText();
        assertEquals(validationMsgTermsExp, validationMsgTermsAct);
    }*/

    @Test
    public void loginSuccessUserFound() {
        driver.navigate().to((getBaseURL() + "/login"));

        String urlExpectedLogin = "/login";
        String urlActual = driver.getCurrentUrl();

        assertTrue(urlActual.contains(urlExpectedLogin));

        WebElement usernameField = driver.findElement(By.id("userName"));
        usernameField.sendKeys("bob@example.com");

        driver.findElement(By.name("submitBtn")).click();

        urlActual = driver.getCurrentUrl();
        String urlExpectedWelcome = "/welcome";

        assertTrue(urlActual.contains(urlExpectedWelcome));
    }

    @Test
    public void loginFailUserNotFound() {
        driver.navigate().to((getBaseURL() + "/login"));

        String urlExpectedLogin = "/login";
        String urlActual = driver.getCurrentUrl();

        assertTrue(urlActual.contains(urlExpectedLogin));

        WebElement usernameField = driver.findElement(By.id("userName"));
        usernameField.sendKeys("nouserfound@example.com");

        driver.findElement(By.name("submitBtn")).click();

        assertTrue(urlActual.contains(urlExpectedLogin));

        String userNotFoundAct = driver.findElement(By.id("validation-summary")).getText();
        String userNotFoundExp = "The username was not found. Please try again.";
        assertTrue(userNotFoundAct.contains(userNotFoundExp));
    }

    @Test
    public void registerSuccess() {
        driver.navigate().to((getBaseURL() + "/register"));

        driver.findElement(By.name("firstName")).sendKeys("Andy");
        driver.findElement(By.name("surname")).sendKeys("Burdis");
        driver.findElement(By.name("email")).sendKeys("andy@andy.com");
        driver.findElement(By.name("confirmEmail")).sendKeys("andy@andy.com");
        driver.findElement(By.name("password")).sendKeys("12345678");
        driver.findElement(By.name("confirmPassword")).sendKeys("12345678");

        driver.findElement(By.id("tConditions")).click();

        driver.findElement(By.name("submitBtn")).click();

        String urlExpectedWelcome = "/welcome";
        String urlActual = driver.getCurrentUrl();

        assertTrue(urlActual.contains(urlExpectedWelcome));
    }

    @Test
    public void registerIncorrectConfirmationPassword() {
        driver.navigate().to((getBaseURL() + "/register"));

        driver.findElement(By.name("firstName")).sendKeys("Andy");
        driver.findElement(By.name("surname")).sendKeys("Burdis");
        driver.findElement(By.name("email")).sendKeys("andy@andy.com");
        driver.findElement(By.name("confirmEmail")).sendKeys("andy@andy.com");
        driver.findElement(By.name("password")).sendKeys("12345678");
        driver.findElement(By.name("confirmPassword")).sendKeys("passwordnotmatch");

        driver.findElement(By.id("tConditions")).click();

        driver.findElement(By.name("submitBtn")).click();

        String urlExpectedLogin = "/login";
        String urlActual = driver.getCurrentUrl();

        assertTrue(urlActual.contains(urlExpectedLogin));

        String errorConfirmPasswordExp = "The confirmation password you have entered does not match. Please try again";
        String errorConfirmPasswordAct = driver.findElement(By.id("validation-summary")).getText();

        assertTrue(errorConfirmPasswordAct.contains(errorConfirmPasswordExp));
    }

    @Test
    public void registerIncorrectConfirmationEmail() {
        driver.navigate().to((getBaseURL() + "/register"));

        driver.findElement(By.name("firstName")).sendKeys("Andy");
        driver.findElement(By.name("surname")).sendKeys("Burdis");
        driver.findElement(By.name("email")).sendKeys("andy@andy.com");
        driver.findElement(By.name("confirmEmail")).sendKeys("incorrect@email.com");
        driver.findElement(By.name("password")).sendKeys("12345678");
        driver.findElement(By.name("confirmPassword")).sendKeys("12345678");

        driver.findElement(By.id("tConditions")).click();

        driver.findElement(By.name("submitBtn")).click();

        String urlExpectedLogin = "/login";
        String urlActual = driver.getCurrentUrl();

        assertTrue(urlActual.contains(urlExpectedLogin));

        String errorConfirmEmailExp = "The confirmation email you have entered does not match. Please try again";
        String errorConfirmEmailAct = driver.findElement(By.id("validation-summary")).getText();

        assertTrue(errorConfirmEmailAct.contains(errorConfirmEmailExp));
    }
		
    @After
    public void myAfter(){
        //driver.quit();

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
