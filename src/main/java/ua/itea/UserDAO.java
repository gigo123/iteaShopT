package ua.itea;

import models.User;

public interface UserDAO {
	public User getUserByID(long id);
	public  boolean checkLoginPasswords(String login, String password);
}
