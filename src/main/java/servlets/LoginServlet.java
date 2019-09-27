package servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoFactory;
import dao.ProductDAO;
import dao.UserDAO;
import mySql.MySQLDAOFactory;
import ua.itea.DBPrepStatement;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static boolean formBlocked = false;
	static long failTime = 0;
	static int errorCounter = 0;
	long timeBlockLeft = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		if (formBlocked) {
			checkUblock();
			if (timeBlockLeft > 0) {
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/BlockedFormView.jsp");
				request.setAttribute("time", timeBlockLeft);
				rd.forward(request, response);
			}
		}
		if (session.getAttribute("login") != null) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/HelloUserView.jsp");
			request.setAttribute("loginName", session.getAttribute("login"));
			request.setAttribute("login", true);
			rd.forward(request, response);

		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/LoginView.jsp");
			request.setAttribute("attempt", errorCounter);
			request.setAttribute("page", "login");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("login");
		String pass = request.getParameter("password");
		if (!checkCredials(login, pass)) {
			HttpSession session = request.getSession();
			session.setAttribute("login", login);
		}
		doGet(request, response);
	}

	private boolean checkCredials(String login, String password) {
		boolean loginFailded = true;
		DaoFactory df = new MySQLDAOFactory();
		 UserDAO uersDAO = df.getUserDAO();
			if (uersDAO.checkLoginPasswords(login, password)) {
				errorCounter = 0;
				loginFailded = false;
			} else {
				errorCounter++;
				loginFailded = true;
				checkBlock();
			}
		return loginFailded;
	}

	private void checkBlock() {
		if (errorCounter > 2) {
			failTime = new Date().getTime();
			formBlocked = true;
		}
	}

	private void checkUblock() {
		if (failTime != 0) {
			timeBlockLeft = ((failTime + 60000) - new Date().getTime()) / 1000;
			if (timeBlockLeft <= 0) {
				failTime = 0;
				formBlocked = false;
				errorCounter = 0;
			}
		}
	}

}
