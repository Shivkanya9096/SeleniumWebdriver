import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sun.jna.platform.win32.Netapi32Util.User;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login {
	
	 private static WebDriver driver;
	 
	 @BeforeTest
	 public void Setup() {
//		 String ProjectPath= System.getProperty("user.dir");
//		 System.setProperty("webdriver.chrome.driver", ProjectPath+
//                 "\\drivers\\chromedriver.exe");
		 
		 WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
		 driver.manage().window().maximize();
		 driver.get("http://www.gmail.com/");
	 }
		 
	@Test	 
	 public void EnetrCred() {
		 WebElement email= driver.findElement(By.xpath("//input[@type='email']"));
		 new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(email));
		 email.sendKeys("jadhav.shivkanya09@gmail.com");
		 
		 WebElement nextButton= driver.findElement(By.xpath("//div[@class='VfPpkd-RLmnJb']"));
		 if(nextButton.isDisplayed()) {
			 nextButton.click();
		 }else {
			 Assert.assertFalse(false,"Next button is not displayed");
			 return;
		 }
		 
		 WebElement passwrd= driver.findElement(By.xpath("//input[@type='password']"));
		 new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(passwrd));
		 passwrd.sendKeys("Shiv@12346");
		 
		 WebElement validationMsg= driver.findElement(By.xpath("//span[contains(text(),'Wrong password. Try again or click "
		 		+ "Forgot password to reset it.')]"));
		 
		 boolean isPass = validationMsg.getText().contains("Wrong password.");
		 Assert.assertEquals(true, isPass);
	 }
	 
	@AfterTest
	 public void closebrowser() {
		 driver.quit();
	 }

}
