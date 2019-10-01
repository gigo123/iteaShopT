package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	private HttpSession session;

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
		session = request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/CartView.jsp");
		if (session.getAttribute("login") != null) {
			request.setAttribute("login", true);
			request.setAttribute("userName", session.getAttribute("userName"));
		}
		if (session.getAttribute("cart") != null) {
			request.setAttribute("productList", session.getAttribute("cart"));
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
		session = request.getSession();
		if (request.getParameter("productToBuy") != null) {
			cartMapProcessed("buy", Integer.parseInt(request.getParameter("productToBuy")));
			response.sendRedirect("./product");
		}
		if (request.getParameter("productToRemove") != null) {
			cartMapProcessed("remove", Integer.parseInt(request.getParameter("productToRemove")));
			doGet(request, response);
		}
	}

	@SuppressWarnings("unchecked")
	private void cartMapProcessed(String type, long productId) {
		Map<Product, Integer> cartMap;
		if (session.getAttribute("cart") != null) {

			cartMap = (Map<Product, Integer>) session.getAttribute("cart");
		} else{
			cartMap = new HashMap<Product, Integer>();
		}
		boolean inCart = false;
		for (Product key : cartMap.keySet()) {
			if (key.getId() == productId) {
				if (type.equals("remove")) {
						cartMap.remove(key);	
				} 
				if(type.equals("buy")){
					cartMap.put(key, (int) cartMap.get(key) + 1);
				}
				inCart = true;
				break;
			}
		}
		if (!inCart) {
			DaoFactory df = new MySQLDAOFactory();
			ProductDAO pd = df.getProductDAO();
			cartMap.put(pd.getProductById(productId), 1);
		}
		session.setAttribute("cart", cartMap);
		session.setAttribute("cart_number",productsCount(cartMap));
		return;
	}

	private int productsCount(Map<Product, Integer> cartMap) {
		ArrayList<Integer> numbers = new ArrayList<Integer>(cartMap.values());
		int sum = 0;
		for (int n : numbers) {
			sum = sum + n;
		}
		return sum;
	}

}
