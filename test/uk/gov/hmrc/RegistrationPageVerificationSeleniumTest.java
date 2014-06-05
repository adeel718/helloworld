package uk.gov.hmrc;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.util.Date;
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
	DateFormat formatter; 
	long d = new Date().getTime();
	String testEmail = "andy@andy.com"+ d;
	
    @Before
    public void myBefore(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

	@Test
	public void checkHeaderAndFooterContent(){
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
    public void registrationCheckLabelsAndFieldsPresent(){

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
        assertTrue("Could not find Password Field", verifyRegPasswordPwordAct.contains(verifyRegPasswordPwordExp));

        String verifyRegPasswordReExp = "Re-enter Password:";
        String verifyRegPasswordReAct = driver.findElement(By.id("confirmPasswordP")).getText();
        assertEquals(verifyRegPasswordReExp, verifyRegPasswordReAct);

        String verifyRegTandCTitleExp = "Terms & Conditions";
        String verifyRegTandCTitleAct = driver.findElement(By.id("termsTitle")).getText();
        assertEquals(verifyRegTandCTitleExp, verifyRegTandCTitleAct);

        String verifyRegTandCDescExp = "Before registering, please read our terms & conditions below";
        String verifyRegTandCDescAct = driver.findElement(By.id("TermsSubHeading")).getText();
        assertEquals(verifyRegTandCDescExp, verifyRegTandCDescAct);

        String verifyRegBoxTextExp = "I agree with the Terms and Conditions";
        String verifyRegBoxTextAct = driver.findElement(By.id("TermsP")).getText();
        assertEquals(verifyRegBoxTextExp, verifyRegBoxTextAct);

        assertTrue(isElementPresent(By.name("tconditions")));
    }
		
    @Test
    public void registrationCheckLinks(){
        driver.navigate().to(getBaseURL() + "/register");

        //Links Navigation Tests
        driver.findElement(By.cssSelector("img[alt=\'OGL\']")).click();
        assertEquals("Open Government Licence", driver.getTitle());
        driver.navigate().back();
        assertTrue("Did not go to register page 1", driver.getCurrentUrl().endsWith(getBaseURL() + "/register"));

        driver.findElement(By.xpath("./*//*[@id='logo']/span")).click();
        assertEquals("Welcome to GOV.UK", driver.getTitle());
        driver.navigate().back();
        assertTrue("Did not go to register page 2", driver.getCurrentUrl().endsWith(getBaseURL() + "/register"));

//			    driver.findElement(By.linkText("Login")).click();	     
//			    assertEquals("Please sign in", driver.getTitle());

        driver.findElement(By.linkText("Open Government Licence v2.0")).click();
        assertEquals("Open Government Licence", driver.getTitle());
        driver.navigate().back();
        assertEquals("Account Registration", driver.getTitle());

        driver.findElement(By.linkText("© Crown Copyright")).click();
        assertEquals("Crown copyright | The National Archives", driver.getTitle());
        driver.navigate().back();
        assertEquals("Account Registration", driver.getTitle());

        driver.findElement(By.linkText("Find out more about cookies")).click();
        assertEquals("Cookies - GOV.UK", driver.getTitle());
        driver.navigate().back();
        assertEquals("Account Registration", driver.getTitle());

        driver.findElement(By.id("proposition-name")).click();
        assertTrue("Did not go to login page", driver.getCurrentUrl().endsWith(getBaseURL() + "/login"));

    }

    @Test
    public void registrationCheckValidationMessages() {
        driver.navigate().to((getBaseURL() + "/register"));

        // Submit blank
        driver.findElement(By.id("submitBtn")).click();

        String validationMsgFirstNameExp = "firstName should not be empty Please enter.";
        String validationMsgFirstNameAct = driver.findElement(By.id("errorfirstName")).getText();
        assertEquals(validationMsgFirstNameExp, validationMsgFirstNameAct);

        String validationMsgSurnameExp = "surname should not be empty Please enter.";
        String validationMsgSurnameAct = driver.findElement(By.id("errorsurname")).getText();
        assertEquals(validationMsgSurnameExp, validationMsgSurnameAct);

        String validationMsgEmailExp = "email should not be empty Please enter valid email.";
        String validationMsgEmailAct = driver.findElement(By.id("erroremail")).getText();
        assertEquals(validationMsgEmailExp, validationMsgEmailAct);

        String validationMsgConfirmEmailExp = "Please confirm email.";
        String validationMsgConfirmEmailAct = driver.findElement(By.id("errorconfirmEmail")).getText();
        assertEquals(validationMsgConfirmEmailExp, validationMsgConfirmEmailAct);

        String validationMsgPasswordExp = "password should not be empty Please enter.";
        String validationMsgPasswordAct = driver.findElement(By.id("errorpassword")).getText();
        assertEquals(validationMsgPasswordExp, validationMsgPasswordAct);

        String validationMsgConfirmPasswordExp = "Please confirm entered password.";
        String validationMsgConfirmPasswordAct = driver.findElement(By.id("errorconfirmPassword")).getText();
        assertEquals(validationMsgConfirmPasswordExp, validationMsgConfirmPasswordAct);

        String validationMsgTermsExp = "Please accept terms & conditions";
        String validationMsgTermsAct = driver.findElement(By.id("errortconditions")).getText();
        assertEquals(validationMsgTermsExp, validationMsgTermsAct);
    }

    @Test
    public void loginSuccessUserAndPasswordFound() {
        driver.navigate().to((getBaseURL() + "/login"));

        String urlExpectedLogin = "/login";
        String urlActual = driver.getCurrentUrl();

        assertTrue("Did not go to login page", urlActual.contains(urlExpectedLogin));

        WebElement usernameField = driver.findElement(By.id("userName"));
        usernameField.sendKeys("andy@andy.com");
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("12345678");

        driver.findElement(By.name("submitBtn")).click();

        urlActual = driver.getCurrentUrl();
        String urlExpectedWelcome = "welcome";

        assertTrue("Did not go to welcome page", urlActual.contains(urlExpectedWelcome));
        //assertTrue(urlActual.contains(urlExpectedWelcome));
    }

    @Test
    public void loginFailUserAndPasswordNotFound() {
        driver.navigate().to((getBaseURL() + "/login"));

        WebElement usernameField = driver.findElement(By.id("userName"));
        usernameField.sendKeys("nouserfound@example.com");
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("incorrectpassword");

        driver.findElement(By.name("submitBtn")).click();

        String urlExpectedLogin = getBaseURL() + "/login";
        String urlActual = driver.getCurrentUrl();
        assertEquals(urlExpectedLogin, urlActual);
        //assertTrue(urlActual.contains(urlExpectedLogin));

        String validationAct = driver.findElement(By.id("validation-summary")).getText();
        String userNotFoundExp = "The entered username and/or password is not correct";
        assertTrue("Validation not found for No username in DB", validationAct.contains(userNotFoundExp));

    }

    @Test
    public void loginFailUserFoundPasswordNotFound() {
        driver.navigate().to((getBaseURL() + "/login"));

        String urlExpectedLogin = getBaseURL() + "/login";
        String urlActual = driver.getCurrentUrl();

        assertEquals(urlExpectedLogin, urlActual);

        WebElement usernameField = driver.findElement(By.id("userName"));
        usernameField.sendKeys("andy@andy.com");
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("incorrectpassword");

        driver.findElement(By.name("submitBtn")).click();

        urlActual = driver.getCurrentUrl();
        urlExpectedLogin = getBaseURL() + "/login";
        assertEquals(urlExpectedLogin, urlActual);
        //assertTrue(urlActual.contains(urlExpectedWelcome));

        String validationAct = driver.findElement(By.id("validation-summary")).getText();
        String passNotFoundExp = "The entered username and/or password is not correct";
        assertTrue("Validation not found: password incorrect", validationAct.contains(passNotFoundExp));
    }

    @Test
    public void registerSuccess() {
    	
        driver.navigate().to((getBaseURL() + "/register"));

        String urlExpectedRegister = getBaseURL() + "/register";
        String urlActualRegister = driver.getCurrentUrl();

        assertEquals(urlExpectedRegister, urlActualRegister);

        driver.findElement(By.name("firstName")).sendKeys("Andy");
        driver.findElement(By.name("surname")).sendKeys("Burdis");
        driver.findElement(By.name("email")).sendKeys(testEmail);
        driver.findElement(By.name("confirmEmail")).sendKeys(testEmail);
        driver.findElement(By.name("password")).sendKeys("12345678");
        driver.findElement(By.name("confirmPassword")).sendKeys("12345678");

        driver.findElement(By.id("tconditions")).click();

        driver.findElement(By.name("submitBtn")).click();

        String urlExpectedLogin = getBaseURL() + "/login";
        String urlActualLogin = driver.getCurrentUrl();

        assertEquals(urlExpectedLogin, urlActualLogin);
        
        String urlExpMessage = "Registration was successful, use the form below to login";
        String urlActMessage = driver.findElement(By.id("message")).getText();
        assertTrue("Register Success message not shown on login", urlActMessage.contains(urlExpMessage));
        
        //assertTrue(urlActual.contains(urlExpectedWelcome));
    }

    @Test
    public void registerIncorrectConfirmationPassword() {
        driver.navigate().to((getBaseURL() + "/register"));

        driver.findElement(By.name("firstName")).sendKeys("Andy");
        driver.findElement(By.name("surname")).sendKeys("Burdis");
        driver.findElement(By.name("email")).sendKeys(testEmail);
        driver.findElement(By.name("confirmEmail")).sendKeys(testEmail);
        driver.findElement(By.name("password")).sendKeys("12345678");
        driver.findElement(By.name("confirmPassword")).sendKeys("passwordnotmatch");

        driver.findElement(By.id("tconditions")).click();

        driver.findElement(By.name("submitBtn")).click();

        String urlExpectedLogin = getBaseURL() + "/login";
        String urlActual = driver.getCurrentUrl();

        assertEquals(urlExpectedLogin, urlActual);
        //assertTrue(urlActual.contains(urlExpectedLogin));

        String errorConfirmPasswordExp = "The confirmation password you have entered does not match. Please try again";
        String errorConfirmPasswordAct = driver.findElement(By.id("validation-summary")).getText();

        assertTrue("Validation not found: Incorrect confirm password", errorConfirmPasswordAct.contains(errorConfirmPasswordExp));
    }

    @Test
    public void registerIncorrectConfirmationEmail() {
        driver.navigate().to((getBaseURL() + "/register"));

        driver.findElement(By.name("firstName")).sendKeys("Andy");
        driver.findElement(By.name("surname")).sendKeys("Burdis");
        driver.findElement(By.name("email")).sendKeys(testEmail);
        driver.findElement(By.name("confirmEmail")).sendKeys("incorrect@email.com");
        driver.findElement(By.name("password")).sendKeys("12345678");
        driver.findElement(By.name("confirmPassword")).sendKeys("12345678");

        driver.findElement(By.id("tconditions")).click();

        driver.findElement(By.name("submitBtn")).click();

        String urlExpectedLogin = getBaseURL() + "/login";
        String urlActual = driver.getCurrentUrl();

        assertEquals(urlExpectedLogin, urlActual);
        //assertTrue(urlActual.contains(urlExpectedLogin));

        String errorConfirmEmailExp = "The confirmation email you have entered does not match. Please try again";
        String errorConfirmEmailAct = driver.findElement(By.id("validation-summary")).getText();

        assertTrue("Validation not found: Incorrect confirm email", errorConfirmEmailAct.contains(errorConfirmEmailExp));
    }

    @Test
    public void registerTermsNotChecked() {
        driver.navigate().to((getBaseURL() + "/register"));

        driver.findElement(By.name("firstName")).sendKeys("Andy");
        driver.findElement(By.name("surname")).sendKeys("Burdis");
        driver.findElement(By.name("email")).sendKeys("andy2@andy.com");
        driver.findElement(By.name("confirmEmail")).sendKeys("andy2@andy.com");
        driver.findElement(By.name("password")).sendKeys("12345678");
        driver.findElement(By.name("confirmPassword")).sendKeys("12345678");

        // NOTE: We do not checkbox the terms
        //driver.findElement(By.id("tconditions")).click();

        driver.findElement(By.name("submitBtn")).click();

        String urlExpectedLogin = getBaseURL() + "/login";
        String urlActual = driver.getCurrentUrl();

        assertEquals(urlExpectedLogin, urlActual);
        //assertTrue(urlActual.contains(urlExpectedLogin));

        String errorCheckTermsExp = "Please accept terms & conditions";
        String errorCheckTermsAct = driver.findElement(By.id("validation-summary")).getText();

        assertTrue("Validation not found: Terms not checked", errorCheckTermsAct.contains(errorCheckTermsExp));
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
