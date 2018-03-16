package com.effectivetesting.entry;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.effectivetesting.pageobject.EntryPageObject;
import com.effectivetesting.pageobject.HomePageObject;
import com.effectivetesting.pageobject.LoginPageObject;

public class TestCreateEntry {
	private WebDriver driver;
	private LoginPageObject loginPage;
	
	@Test
	public void postIsSuccessfull() {
		loginPage = new LoginPageObject(driver);
		HomePageObject homePage = loginPage.login("admin1@gmail.com", "admin1");
		
		EntryPageObject entryPage = homePage.goToCreateEntry();
		entryPage.createEntry("My newest post", "This is a post.");
		
		String currentMessage = entryPage.getResultMessage();
		System.out.println(currentMessage);
		
		assertTrue(currentMessage.contains("Entry 'My newest post' created successfully."));
	}
	
	@Test
	public void textRequired(){
		loginPage = new LoginPageObject(driver);
		HomePageObject homePage = loginPage.login("admin1@gmail.com", "admin1");
		
		EntryPageObject entryPage = homePage.goToCreateEntry();
		entryPage.createEntry("", "This is a post.");
		
		String currentMessage = entryPage.getTextErrorMessage();
		System.out.println(currentMessage);
		
		assertTrue(currentMessage.contains("This field is required."));
	}
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("localhost:5000");
	}

	@After
	public void teardDown() {
		driver.get("http://localhost:5000/admin/entry/");
		if(driver.findElements(By.xpath("/html/body/div/table/tbody/tr/td[2]/form")).size() != 0){
			driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/form")).click();
			
		    WebDriverWait wait = new WebDriverWait(driver, 10);
		    Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			   
		    alert.accept();
		    
		    WebDriverWait waitForMessage = new WebDriverWait(driver, 10);
		    waitForMessage.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div[2]")));
		}
	    driver.quit();
	}
}
