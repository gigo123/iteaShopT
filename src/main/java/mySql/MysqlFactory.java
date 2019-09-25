package mySql;

import ua.itea.DAOFactory;
import ua.itea.UserDAO;

public class MysqlFactory extends DAOFactory{

	@Override
	public UserDAO getUserDAO() {
		
				return new MySQlUserDAO();
	}

}
