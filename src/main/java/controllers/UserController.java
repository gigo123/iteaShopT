package controllers;

import models.User;

public class UserController {
	public User createUser() {
		return new User();
		
	}
	
	public User createUser(String login, String password) {
		return new User(login, password);
		
	}
	public User createUser(String login, String password,String name, String region, boolean gender, String comment) {
		return new User(login, password, name, region, gender, comment);
		
	}
	public User createUser(int id,String login, String password,String name, String region, boolean gender, String comment) {
		return new User(id,login, password, name, region, gender, comment);
		
	}
}
