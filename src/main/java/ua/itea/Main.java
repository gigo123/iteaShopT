package ua.itea;

import models.User;

public class Main {

	public static void main(String[] args) {
		DAOFactory factory = DAOFactory.getFactory(1);
		UserDAO userDAO = factory.getUserDAO();
		User user =  userDAO.getUserByID(1);
		

	}

}
