package servlets;

import java.io.IOException;
import java.util.ArrayList;
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
 * Servlet implementation class CartServlet
 */
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/ProductsView.jsp");
		request.setAttribute("productList", (List<Product>) session.getAttribute("cart"));
		if (session.getAttribute("login") != null) {
			request.setAttribute("login", true);
			request.setAttribute("userName", session.getAttribute("userName"));
		}
		if (session.getAttribute("cart") != null) {
			if (session.getAttribute("cart_number") != null) {
				request.setAttribute("items", session.getAttribute("cart_number"));
			}
		} else {
			request.setAttribute("items", 0);
		}
		request.setAttribute("page", "cart");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("productToBuy") != null) {
			long productId = Integer.parseInt(request.getParameter("productToBuy"));
			DaoFactory df = new MySQLDAOFactory();
			ProductDAO pd = df.getProductDAO();
			Product product = pd.getProductById(productId);
			HttpSession session = request.getSession();
			List<Product> products;
			if (session.getAttribute("cart") != null) {
				products = (List<Product>) session.getAttribute("cart");
			} else {
				products = new ArrayList<Product>();
			}
			products.add(product);
			session.setAttribute("cart", products);
			session.setAttribute("cart_number", products.size());
			System.out.println(products);
			response.sendRedirect("./product");
		}
		if (request.getParameter("productToRemove") != null) {
			long productId = Integer.parseInt(request.getParameter("productToRemove"));
			DaoFactory df = new MySQLDAOFactory();
			ProductDAO pd = df.getProductDAO();
			Product product = pd.getProductById(productId);
			HttpSession session = request.getSession();
			List<Product> products;
				products = (List<Product>) session.getAttribute("cart");
			products.remove(product);
			session.setAttribute("cart", products);
			session.setAttribute("cart_number", products.size());
			System.out.println("remove product");
			doGet(request, response);
		}
	}

}
