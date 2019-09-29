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
		if (session.getAttribute("login") != null) {
			request.setAttribute("login", true);
			request.setAttribute("userName", session.getAttribute("userName"));
		}
		if (session.getAttribute("cart") != null) {
			Map<Long, Integer> cartMap = (Map<Long, Integer>) session.getAttribute("cart");
			// List<Integer> list = new ArrayList<Integer>(cartMap.values());
			List<Product> products = new ArrayList<Product>();
			List<Integer> quantity = new ArrayList<Integer>();
			DaoFactory df = new MySQLDAOFactory();
			ProductDAO pd = df.getProductDAO();
			for (long key : cartMap.keySet()) {
				products.add(pd.getProductById(key));
				quantity.add(cartMap.get(key));
			}
			request.setAttribute("productList", products);
			request.setAttribute("quantityList", quantity);
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
			// List<Product> products;
			Map<Long, Integer> cartMap;
			if (session.getAttribute("cart") != null) {

				cartMap = (Map<Long, Integer>) session.getAttribute("cart");
			} else {
				cartMap = new HashMap<Long, Integer>();
			}
			boolean inCart = false;
			for (long key : cartMap.keySet()) {
				if (key == productId) {
					cartMap.put(key, (int) cartMap.get(key) + 1);
					inCart = true;
				}
			}
			if (!inCart) {
				cartMap.put(productId, 1);
			}
		

			/*
			 * Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
			 * while (entries.hasNext()) { Map.Entry<String, String> entry = entries.next();
			 * System.out.println("ID = " + entry.getKey() + " День недели = " +
			 * entry.getValue()); }
			 * 
			 * System.out.println();
			 * 
			 * // keySet - возвращает множество ключей
			 * 
			 * for (String key : map.keySet()) { System.out.println("ID = " + key +
			 * ", День недели = " + map.get(key)); }
			 */
			session.setAttribute("cart", cartMap);
			session.setAttribute("cart_number",productsCount(cartMap));
			response.sendRedirect("./product");
		}
		if (request.getParameter("productToRemove") != null) {
			long productId = Integer.parseInt(request.getParameter("productToRemove"));
			DaoFactory df = new MySQLDAOFactory();
			ProductDAO pd = df.getProductDAO();
			Product product = pd.getProductById(productId);
			HttpSession session = request.getSession();
			// List<Product> products;
			// products = (List<Product>) session.getAttribute("cart");

			// products.remove(product);
			Map<Long, Integer> cartMap;
			if (session.getAttribute("cart") != null) {

				cartMap = (Map<Long, Integer>) session.getAttribute("cart");

				for (long key : cartMap.keySet()) {
					if (key == productId) {
						cartMap.remove(key);
					}
				}
				session.setAttribute("cart", cartMap);
				session.setAttribute("cart_number", productsCount(cartMap));
				System.out.println("remove product");
				doGet(request, response);
			}
		}
	}
	
	private int  productsCount(	Map<Long, Integer> cartMap) {
		ArrayList<Integer> numbers =  new ArrayList<Integer>(cartMap.values());
		int sum =0 ;
		for (int n : numbers) {
			 sum = sum +n;
	       }
		return sum;
	}

}
