package tests.selen;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class CheckSettingsHomeTab {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver", "C:/driver/chromedriver.exe");
    driver = new ChromeDriver();
    baseUrl = "http://77.40.214.21/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testCheckSettingsHomeTab() throws Exception {
    driver.get(baseUrl + "/site/login");
    driver.findElement(By.id("loginform-email")).clear();
    driver.findElement(By.id("loginform-email")).sendKeys("test.andersen.test@gmail.com");
    driver.findElement(By.id("loginform-password")).clear();
    driver.findElement(By.id("loginform-password")).sendKeys("Aa123456");
    driver.findElement(By.id("sign_button")).click();
    driver.findElement(By.xpath("(//a[contains(@href, '#')])[3]")).click();
    try {
      assertTrue(isElementPresent(By.cssSelector("div.tab-content")));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertTrue(isElementPresent(By.cssSelector("h4.control-sidebar-subheading")));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertTrue(isElementPresent(By.xpath("//div[@id='control-sidebar-home-tab']/ul/li/a[2]/div/h4")));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertTrue(isElementPresent(By.xpath("//div[@id='control-sidebar-home-tab']/ul/li/a[3]/div/h4")));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertTrue(isElementPresent(By.xpath("//div[@id='control-sidebar-home-tab']/ul/li/a[4]/div/h4")));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.cssSelector("i.fa.fa-gears")).click();
    driver.findElement(By.linkText("rightbar1@test.test")).click();
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
