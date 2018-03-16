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

import com.effectivetesting.pageobject.AdminHomePageObject;
import com.effectivetesting.pageobject.LoginPageObject;

public class TestCreateEntry {
	private WebDriver driver;
	private LoginPageObject loginPage;
	private AdminHomePageObject adminPage;
	
	@Test
	public void postIsSuccessfull() {
		loginPage = new LoginPageObject(driver);

		String currentMessage = loginPage
									.login("admin1@gmail.com", "admin1")
									.goToCreateEntry()
									.createEntry("My newest post", "this is my body")
									.getResultMessage();
								
		
		assertTrue(currentMessage.contains("Entry 'My newest post' created successfully."));
	}
	
	@Test
	public void textRequired(){
		loginPage = new LoginPageObject(driver);
		
		String currentMessage = loginPage
									.login("admin1@gmail.com", "admin1")
									.goToCreateEntry()
									.createEntry("", "this is my body")
									.getTextErrorMessage();
		
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
		driver.get("localhost:5000/admin/");
		adminPage = new AdminHomePageObject(driver);
		
		adminPage
			.goToEntrySection()
			.getButtonDelete();
		
	    driver.quit();
	}
}
