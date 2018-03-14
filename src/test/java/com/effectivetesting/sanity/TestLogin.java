package com.effectivetesting.sanity;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestLogin {
	private WebDriver driver;
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("localhost:5000");
	}
	
	@Test
	public void successfulLoginMessageShouldAppear(){
		driver.findElement(By.id("login")).click();
		driver.findElement(By.id("email")).sendKeys("admin1@gmail.com");
		driver.findElement(By.id("password")).sendKeys("admin1");
		driver.findElement(By.id("btn-submit")).click();
		
		String expectedMessage = "Successfully logged in as admin1@gmail.com.";
		String currentMessage = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div/span")).getText();
		assertEquals(expectedMessage, currentMessage);
	}
	
	@Test
	public void createPostShouldBeAvailable(){
		driver.findElement(By.id("login")).click();
		driver.findElement(By.id("email")).sendKeys("admin1@gmail.com");
		driver.findElement(By.id("password")).sendKeys("admin1");
		driver.findElement(By.id("btn-submit")).click();
		driver.findElement(By.id("create_post")).click();
		String currentUrl = driver.getCurrentUrl();
		assertTrue(currentUrl.contains("/entries/create/"));
	}
	
	@Test
	public void wrongEmailLoginMessageShouldAppear(){
		driver.findElement(By.id("login")).click();
		driver.findElement(By.id("email")).sendKeys("admin14@gmail.com");
		driver.findElement(By.id("password")).sendKeys("admin1");
		driver.findElement(By.id("btn-submit")).click();
		
		String expectedMessage = "Invalid email or password.";
		String currentMessage = driver.findElement(By.xpath("//*[@id=\"content_title\"]/form/div[1]/div/span[2]")).getText();
		assertEquals(expectedMessage, currentMessage);
	}
	
	@Test
	public void emptyEmailLoginMessageShouldAppear(){
		driver.findElement(By.id("login")).click();
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("password")).sendKeys("admin1");
		driver.findElement(By.id("btn-submit")).click();
		
		String expectedMessage = "This field is required.";
		String currentMessage = driver.findElement(By.xpath("//*[@id=\"content_title\"]/form/div[1]/div/span[2]")).getText();
		assertEquals(expectedMessage, currentMessage);
	}
	
	@Test
	public void emptyPassLoginMessageShouldAppear(){
		driver.findElement(By.id("login")).click();
		driver.findElement(By.id("email")).sendKeys("admin14@gmail.com");
		driver.findElement(By.id("password")).sendKeys("");
		driver.findElement(By.id("btn-submit")).click();
		
		String expectedMessage = "This field is required.";
		String currentMessage = driver.findElement(By.xpath("//*[@id=\"content_title\"]/form/div[2]/div/span[2]")).getText();
		assertEquals(expectedMessage, currentMessage);
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}

}
