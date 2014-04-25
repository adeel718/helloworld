package views;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;

public class HelloWorldViewTest extends BaseSeleniumTest{
	
  @Test
  public void testHelloWorld() throws Exception {
      // open | / | 
      driver.get(getBaseURL() + "/");
      System.out.println("Get url" + driver.getCurrentUrl());
      // assertText | //html/body/h1 | 
      assertEquals("", driver.findElement(By.xpath("//html/body/h1")).getText());
      // type | //input[@name="username"] | 
      driver.findElement(By.xpath("//input[@name=\"username\"]")).clear();
      driver.findElement(By.xpath("//input[@name=\"username\"]")).sendKeys("");
      // click | //html/body/button[2] | 
      driver.findElement(By.xpath("//html/body/button[2]")).click();
      // verifyAllButtons | //html/body/button | 
      // ERROR: Caught exception [ERROR: Unsupported command [getAllButtons |  | ]]
  }
}

