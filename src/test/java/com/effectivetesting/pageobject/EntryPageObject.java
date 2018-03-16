package com.effectivetesting.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EntryPageObject {
	private WebDriver driver;
	
	public EntryPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public EntryPageObject createEntry(String title, String text) {
		driver.findElement(By.id("title")).sendKeys(title);
		driver.findElement(By.id("body")).sendKeys(text);
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/form/div[5]/div/button")).click();	
		return this;
	}
	
	public String getResultMessage() {
		return driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div/span")).getText();
	}
	
	public String getTextErrorMessage() {
		return driver.findElement(By.xpath("//*[@id=\"create_post\"]/div[1]/div/span[2]")).getText();
	}
}
