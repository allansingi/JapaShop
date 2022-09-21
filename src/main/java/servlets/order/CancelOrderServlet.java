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
 * Triggers the "cancel order" button from ADMIN side
 */
@WebServlet("/cancelOrderServlet")
public class CancelOrderServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String product_id = req.getParameter("id");
			String email = req.getParameter("email");
			
			OrderDao dao = new OrderDao(DBConnection.getConn());

			HttpSession session = req.getSession();
			if(dao.changeInvoiceStatusToCanceled(product_id, email)) {
				session.setAttribute("orderCanceledMsg", "Order Canceled !!");
				resp.sendRedirect("admin/admin_orders.jsp");
			} else {
				session.setAttribute("cancelOrderErrorMsg", "Something went wrong on cancel order server ...");
				resp.sendRedirect("admin/admin_orders.jsp");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}