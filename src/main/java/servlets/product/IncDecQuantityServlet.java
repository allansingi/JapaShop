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

/**
 * Triggers the - and + buttons from USER side in product cart table
 */
@WebServlet("/incDecQuantityServlet")
public class IncDecQuantityServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			String email = req.getSession().getAttribute("email").toString();
			String id = req.getParameter("id");
			String qty = req.getParameter("qty");
			
			ProductDao dao = new ProductDao(DBConnection.getConn());
			Integer daoResponse = dao.incDecCartItem(email, id, qty);

			HttpSession session = req.getSession();
			if(daoResponse == -1) {
				session.setAttribute("cantMsg", "Can't decrease (last product). Click in remove");
				resp.sendRedirect("product_cart.jsp");
			} else if (daoResponse == 0) {
				session.setAttribute("decMsg", "Product decreased by one successfully !");
				resp.sendRedirect("product_cart.jsp");
			} else if (daoResponse == 1) {
				session.setAttribute("incMsg", "Product increased by one successfully !");
				resp.sendRedirect("product_cart.jsp");
			} else {
				session.setAttribute("errorIncDecMsg", "Something went wrong on inc/dec server ...");
				resp.sendRedirect("product_cart.jsp");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}