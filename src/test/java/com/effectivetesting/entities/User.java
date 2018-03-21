package com.effectivetesting.entities;

public class User {
	private String id;
	private String email;
	private String password_hash;
	private String name;
	private boolean admin;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getpassword_hash() {
		return password_hash;
	}
	
	public void setpassword_hash(String password) {
		this.password_hash = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAdmin(boolean admin){
		this.admin = admin;
	}
	
	public boolean getAdmin(){
		return this.admin;
	}

}
