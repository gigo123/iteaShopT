package ua.itea;

import XML.XMLDAOFactory;
import mySql.MysqlFactory;

public abstract class DAOFactory {

	public abstract UserDAO getUserDAO();
	public static DAOFactory getFactory(int type) {
		switch (type) {
		case 1:
			return new MysqlFactory();
		case 2:
			return new XMLDAOFactory();
		default:
		return null;

		}
	}
}
