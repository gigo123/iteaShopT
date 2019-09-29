package servlets;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoFactory;
import dao.UserDAO;
import models.User;
import mySql.MySQLDAOFactory;
import ua.itea.DBPrepStatement;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean error = false;
	private boolean createUser = false;
	private StringBuilder errorText;
	private User user;
	private UserDAO uersDAO;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (createUser) {
			createUser = false;
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/HelloUserView.jsp");
			request.setAttribute("login", session.getAttribute("login"));
			rd.forward(request, response);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/RegisterView.jsp");
		if (error) {
			error = false;
			request.setAttribute("login", false);
			request.setAttribute("errorText", errorText.toString());
			if (session.getAttribute("login") != null) {
				request.setAttribute("login", true);

			}
		} else if (session.getAttribute("login") != null) {
			DaoFactory df = new MySQLDAOFactory();
			 uersDAO = df.getUserDAO();
				user = uersDAO.getUserByLogin(session.getAttribute("login").toString());
		
			request.setAttribute("login", true);
		} else {
			user = new User();
			request.setAttribute("login", false);
		}
		request.setAttribute("page", "register");
		request.setAttribute("user", user);
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		error = false;
		createUser = false;
		errorText = new StringBuilder("<ul>");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String name = request.getParameter("name");
		String region = request.getParameter("region");
		String gender = request.getParameter("gender");
		String comment = request.getParameter("comment");
		String acceptOffer = request.getParameter("acceptOffer");
		user = new User(login, password, name, region, convertGenderToBool(gender), comment);
		DaoFactory df = new MySQLDAOFactory();
		 uersDAO = df.getUserDAO();
			checkErrors(session, password2, acceptOffer);
			if (!error) {
				if (session.getAttribute("login") != null) {
					if(!uersDAO.updateUser(user, session.getAttribute("login").toString())) {
						error = true;
						errorText.append("<li>DataBase error</li>");
					};
				} else {
					if(!uersDAO.insertUser(user)) {
						error = true;
						errorText.append("<li>DataBase error</li>");
					}
				}
				createUser = true;
				session.setAttribute("login", login);
			}
		doGet(request, response);
	}

	private boolean convertGenderToBool(String gender) {
		if (gender.equals("Male")) {
			return true;
		} else {
			return false;
		}

	}

	private void checkErrors(HttpSession session, String password2, String acceptOffer) {
		if (session.getAttribute("login") == null) {
			checkLogin(user.getLogin());
		}
		checkPassword(user.getPassword(), password2);
		if (user.getName().length() < 1) {
			error = true;
			errorText.append("<li> name field is empty</li>");
		}
		if (user.getComment().length() < 1) {
			error = true;
			errorText.append("<li> you not fill commetary</li>");
		}
		if (acceptOffer == null) {
			error = true;
			errorText.append("<li> you not accept offer </li>");
		}
	}

	private void checkLogin(String login) {
		if (login.length() < 1) {
			error = true;
			errorText.append("<li>Login field is empty</li>");
			return;
		}
		if (uersDAO.selectEmail(login)) {
			error = true;
			errorText.append("<li>this email is alredy in use</li>");
		}
		if (!Pattern.matches("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$", login)) {
			error = true;
			errorText.append("<li>you login is not valid email</li>");
		}
	}

	private void checkPassword(String password, String password2) {
		if (password.length() < 1 || password2.length() < 1) {
			error = true;
			errorText.append("<li>Password field is empty </li>");
			return;
		}

		if (!password.equals(password2)) {
			error = true;
			errorText.append("<li>Passwords must match </li>");
			return;
		}
		if (!Pattern.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9]{8,}$", password)) {
			error = true;
			errorText.append("<li>Password must meet security settings </li>");
		}
	}
}
