package tests.selen;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class LogInAsNonAdmin {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  //Creds
  private String mail;
  private String pass;

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
      //System.setProperty("webdriver.gecko.driver", "C:/driver/geckodriver.exe");
      System.setProperty("webdriver.chrome.driver", "C:/driver/chromedriver.exe");
      driver = new ChromeDriver();
      baseUrl = "http://77.40.214.21/";
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testLogInAsNonAdmin() throws Exception {
      driver.get(baseUrl + "/site/login");
      //String loginMail = "test.andersen.test@gmail.com";

      driver.findElement(By.id("loginform-email")).clear();
      driver.findElement(By.id("loginform-email")).sendKeys(mail);
      driver.findElement(By.id("loginform-password")).clear();
      driver.findElement(By.id("loginform-password")).sendKeys(pass);
      driver.findElement(By.cssSelector("ins.iCheck-helper")).click();
      try {
        assertTrue(isElementPresent(By.cssSelector("ins.iCheck-helper")));
      } catch (Error e) {
        verificationErrors.append(e.toString());
      }
      driver.findElement(By.id("sign_button")).click();
      try {
        assertTrue(isElementPresent(By.cssSelector("span.hidden-xs")));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.cssSelector("span.hidden-xs")).click();
    driver.findElement(By.linkText("Sign Out")).click();
    try {
      assertTrue(isElementPresent(By.cssSelector("div.login-box-body")));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
