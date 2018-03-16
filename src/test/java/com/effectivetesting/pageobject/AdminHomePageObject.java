package com.effectivetesting.pageobject;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminHomePageObject {
	private WebDriver driver;
	
	public AdminHomePageObject(WebDriver driver){
		this.driver = driver;
	}
	
	public AdminEntryPageObject goToEntrySection(){
		driver.findElement(By.xpath("/html/body/div/div/div/ul[1]/li[2]/a")).click();
		return new AdminEntryPageObject(driver);
	}

}
