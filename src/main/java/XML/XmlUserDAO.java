package XML;


import models.User;
import ua.itea.UserDAO;

public class XmlUserDAO  implements UserDAO{

	@Override
	public User getUserByID(long id) {
		// get from xml
		return null;
	}

	@Override
	public boolean checkLoginPasswords(String login, String password) {
		// check login
		return false;
	}

}
