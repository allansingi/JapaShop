package servlets.product;

import java.io.IOException;

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
 * Triggers the "Add to Cart" button from USER side in product list table
 */
@WebServlet("/addProductToCartFromViewServlet")
public class AddProductToCartFromViewServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String email = req.getSession().getAttribute("email").toString();
			String productId = req.getParameter("productId");
			
			ProductDao dao = new ProductDao(DBConnection.getConn());
			
			HttpSession session = req.getSession();
			Integer addProductToCart = dao.addProductToCart(productId, email);
			if(addProductToCart == 0) {
				session.setAttribute("addedMsg", "Product Added to Cart Successfully !!");
				resp.sendRedirect("product_viewProduct.jsp");
			} else if (addProductToCart == 1) {
				session.setAttribute("existMsg", "Product already exists in cart, added one more!");
				resp.sendRedirect("product_viewProduct.jsp");
			} else {
				session.setAttribute("errorMsg", "Something went wrong on server ...");
				resp.sendRedirect("product_viewProduct.jsp");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}