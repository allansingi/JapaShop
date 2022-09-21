package servlets.order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrderDao;
import db.DBConnection;

/**
 * Triggers the "deliver order" button from ADMIN side
 */
@WebServlet("/deliverOrderServlet")
public class DeliverOrderServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String product_id = req.getParameter("id");
			String email = req.getParameter("email");
			
			OrderDao dao = new OrderDao(DBConnection.getConn());

			HttpSession session = req.getSession();
			if(dao.changeInvoiceStatusToDelivered(product_id, email)) {
				session.setAttribute("orderDeliveredMsg", "Order Delivered !!");
				resp.sendRedirect("admin/admin_orders.jsp");
			} else {
				session.setAttribute("deliverErrorMsg", "Something went wrong on deliver server ...");
				resp.sendRedirect("admin/admin_orders.jsp");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}