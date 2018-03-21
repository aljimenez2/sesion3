package com.effectivetesting.helper;

import com.effectivetesting.entities.User;

public class UserHelper {

	private static final String ID = "23";
	
	public User createTestUser() {

		User user = new User();

		user.setId(ID);
		user.setEmail("userx@gmail.com");
		user.setpassword_hash("userx");
		user.setName("John Doe");
		user.setAdmin(true);
		return user;

	}
	
	public String getId(){
		return this.ID;
	}
}
