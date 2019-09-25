package XML;

import ua.itea.DAOFactory;
import ua.itea.UserDAO;

public class XMLDAOFactory  extends DAOFactory{

	@Override
	public UserDAO getUserDAO() {
		// 
		return new XmlUserDAO();
	}

}
