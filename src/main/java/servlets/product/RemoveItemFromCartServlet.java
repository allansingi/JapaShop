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
 * Triggers the "Remove" button from USER side in cart list table
 */
@WebServlet("/removeItemFromCartServlet")
public class RemoveItemFromCartServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String email = req.getSession().getAttribute("email").toString();;
			String id = req.getParameter("id");
			
			ProductDao dao = new ProductDao(DBConnection.getConn());
						
			HttpSession session = req.getSession();
			if(dao.removeItemFromCart(email, id)) {
				session.setAttribute("removeSuccessMsg", "Product Removed Successfully !!");
				resp.sendRedirect("product_cart.jsp");
			} else {
				session.setAttribute("removeErrorMsg", "Something went wrong on remove server ...");
				resp.sendRedirect("product_cart.jsp");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}