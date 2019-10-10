package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
				request.setAttribute("totalSumm", totalCartSum((Map<Product, Integer>) session.getAttribute("cart")));
			}
		} else {
			request.setAttribute("items", 0);
			request.setAttribute("totalSumm", 0);
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
		System.out.println(request.getParameter("productToBuy"));
		System.out.println(request.getParameter("numberOfGoods"));
		if (request.getParameter("productToBuy") != null && request.getParameter("numberOfGoods") != null) {
			cartMapProcessed("buy", Integer.parseInt(request.getParameter("productToBuy")),
					Integer.parseInt(request.getParameter("numberOfGoods")));
			response.getWriter().write(session.getAttribute("cart_number").toString());
		}
		if(request.getParameter("productToChange") != null && request.getParameter("numberOfGoods") != null) {
			cartMapProcessed("buy", Integer.parseInt(request.getParameter("productToChange")),
					Integer.parseInt(request.getParameter("numberOfGoods")));
			String cartResponseJson = "{\"numberOfGoods\":\""+session.getAttribute("cart_number").toString() +
					"\",\"totalCartSum\":\"" +totalCartSum()+"\"}" ;
		System.out.println(cartResponseJson);
	        PrintWriter out = response.getWriter();
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        out.write(cartResponseJson);
	        out.flush(); 
		}
		if (request.getParameter("productToRemove") != null) {
			cartMapProcessed("remove", Integer.parseInt(request.getParameter("productToRemove")), 0);
			doGet(request, response);
		}
	}

	@SuppressWarnings("unchecked")
	private void cartMapProcessed(String type, long productId, int numberOfGoods) {
		Map<Product, Integer> cartMap;
		if (session.getAttribute("cart") != null) {

			cartMap = (Map<Product, Integer>) session.getAttribute("cart");
		} else {
			cartMap = new HashMap<Product, Integer>();
		}
		DaoFactory df = new MySQLDAOFactory();
		ProductDAO pd = df.getProductDAO();
		Product product = pd.getProductById(productId);
		if (type.equals("buy")) {
			if (cartMap.containsKey(product)) {
				numberOfGoods = numberOfGoods + cartMap.get(product);
			}
			cartMap.put(product, numberOfGoods);
		}
		if (type.equals("remove")) {
			if (cartMap.containsKey(product)) {
				cartMap.remove(product);
			}
		}
		session.setAttribute("cart", cartMap);
		session.setAttribute("cart_number", productsCount(cartMap));
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

	private int totalCartSum(Map<Product, Integer> cartMap) {
		int sum = 0;
		Iterator<Map.Entry<Product, Integer>> itr = cartMap.entrySet().iterator();

		while (itr.hasNext()) {
			Map.Entry<Product, Integer> entry = itr.next();
			sum = sum + entry.getKey().getPrice() * entry.getValue();
		}
		return sum;
	}
	private int totalCartSum() {
		Map<Product, Integer> cartMap;
		cartMap = (Map<Product, Integer>) session.getAttribute("cart");
		int sum = 0;
		Iterator<Map.Entry<Product, Integer>> itr = cartMap.entrySet().iterator();

		while (itr.hasNext()) {
			Map.Entry<Product, Integer> entry = itr.next();
			sum = sum + entry.getKey().getPrice() * entry.getValue();
		}
		return sum;
	}
}
