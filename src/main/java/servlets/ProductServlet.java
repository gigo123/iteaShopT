package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoFactory;
import dao.ProductDAO;
import models.Product;
import mySql.MySQLDAOFactory;
import mySql.MySQLProductDAO;

/**
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int category;
		List<Product> products;
		HttpSession session = request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/ProductsView.jsp");
		DaoFactory df = new MySQLDAOFactory();
		ProductDAO pd = df.getProductDAO();
		if (request.getParameter("category") != null) {
			category = Integer.parseInt(request.getParameter("category"));
			products = pd.getProductByCategory(category);
		} else {
			products = pd.getProductList();
		}
		if (session.getAttribute("login") != null) {	
			request.setAttribute("login", true);
			request.setAttribute("userName", session.getAttribute("userName"));
		}
		if(session.getAttribute("cart")!=null) {	
			if(session.getAttribute("cart_number")!=null) {
				request.setAttribute("items", session.getAttribute("cart_number"));
			}
		}
		else {
			request.setAttribute("items", 0);
		}
		request.setAttribute("productList", products);
		request.setAttribute("page", "product");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
