package servlets.product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductDao;
import db.DBConnection;
import entity.Product;

/**
 * Triggers the "Search" button from USER side in product list table
 */
@WebServlet("/productSearchServlet")
public class ProductSearchServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String searchString = req.getParameter("searchString");
			ProductDao dao = new ProductDao(DBConnection.getConn());
			List<Product> searchResultList = new ArrayList<Product>();
			List<Product> emptyList = new ArrayList<Product>();
			searchResultList = dao.getProductsBySearchString(searchString);
			
			HttpSession session = req.getSession();
			if(searchString == "") {
				session.setAttribute("searchResultList", emptyList);
				resp.sendRedirect("product_searchProduct.jsp");
			} else {
				session.setAttribute("searchResultList", searchResultList);
				resp.sendRedirect("product_searchProduct.jsp");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}