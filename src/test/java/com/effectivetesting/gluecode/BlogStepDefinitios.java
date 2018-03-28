package com.effectivetesting.gluecode;

import static com.github.restdriver.serverdriver.RestServerDriver.body;
import static com.github.restdriver.serverdriver.RestServerDriver.delete;
import static com.github.restdriver.serverdriver.RestServerDriver.post;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.effectivetesting.entities.User;
import com.effectivetesting.pageobject.LoginPageObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;

public class BlogStepDefinitios {
	private static final String ID = "23";
	private static final String DEFAULT_BASE_URL = "http://localhost:5000";
	private WebDriver driver;
	private LoginPageObject loginPage;
	private String $currentUrl;
	private String email;
	private String password;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(DEFAULT_BASE_URL);
	}
	
	@Dado("^el usuario \"([^\"]*)\" con email \"([^\"]*)\" con password \"([^\"]*)\"$")
	public void el_usuario_con_email_y_password(String userName, String email, String password) throws Throwable {
		this.email = email;	
        this.password = password;
        
		User user = createTestObject(userName, email, password);
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonInString = mapper.writeValueAsString(user);
		
		post(DEFAULT_BASE_URL + "/api/user", body(jsonInString, "application/json"));
	}

	@Cuando("^se loguea en el blog y entre a la seccion entradas$")
	public void se_loguea_en_el_blog_y_entre_a_la_seccion_entradas() throws Throwable {
		loginPage = new LoginPageObject(driver);
		loginPage
			.login(this.email, this.password);
	    driver.findElement(By.xpath("//*[@id=\"blog\"]/a")).click();
	    $currentUrl = driver.getCurrentUrl();
	}

	@Entonces("^se encuentra en la vista de \"([^\"]*)\"$")
	public void se_encuentra_en_la_vista_de(String expectedMessage) throws Throwable {
		Assert.assertEquals(expectedMessage, $currentUrl);
	}
	

	@After
	public void tearDown() {
		driver.quit();
		delete(DEFAULT_BASE_URL + "/api/user/" + ID);
	}
	
	private User createTestObject(String userName, String email, String password) {
		User user = new User();

		user.setId(ID);
		user.setEmail(email);
		user.setpassword_hash(password);
		user.setName(userName);
		
		return user;
	}
}
