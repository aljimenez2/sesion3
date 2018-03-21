package com.effectivetesting.user;

import static io.restassured.RestAssured.*;

import org.junit.After;
import org.junit.Test;

import com.effectivetesting.entities.User;
import com.effectivetesting.helper.UserHelper;

public class TestCreateUser {
	private static final String DEFAULT_BASE_URL = "http://localhost:5000/api";
	private static final String ID = "23";
	
	@Test
	public void postUser() {
		//User user = createTestUser();
		
		UserHelper userHelper = new UserHelper();
		
        given()
        	.contentType("application/json")
        	.body(userHelper)
        	
        .when()
    		.post(DEFAULT_BASE_URL + "/user")
        	
    	.then()
    		.statusCode(201);
	}

    @After
    public void tearDown() {
        delete(DEFAULT_BASE_URL + "/user/" + ID);
    }
}
