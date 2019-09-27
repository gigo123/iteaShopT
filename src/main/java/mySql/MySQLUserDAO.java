package mySql;

import ua.itea.DBPrepStatement;
import models.User;
import ua.itea.UserDAO;

public class MySQlUserDAO implements UserDAO {
	DBPrepStatement db;
	@Override
	public User getUserByID(long id) {
		// select from user where id = ?
		return null;
	}
	
	public MySQlUserDAO() {
		 db = new DBPrepStatement();
		db.openDatabase();
	}
	@Override
	public boolean checkLoginPasswords(String login, String password) {
	return db.checkLoginPasswords(login, password);
	}
	public User readUserFromLogin(String login) {
		return db.readUserFromLogin(login);
		
	}
	public void closeBase() {
		db.closeConection();
	}
}
