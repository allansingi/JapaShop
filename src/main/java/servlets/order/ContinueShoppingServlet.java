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
 * Triggers the "continue shopping" button from invoice screen in order to change cart status column from "invoice" to "processing"
 */
@WebServlet("/continueShoppingServlet")
public class ContinueShoppingServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String email = req.getSession().getAttribute("email").toString();
			
			OrderDao orderDao = new OrderDao(DBConnection.getConn());
			
			if(orderDao.changeInvoiceStatusToProcessing(email)) {
				resp.sendRedirect("index.jsp");
			} else {
				resp.sendRedirect("index.jsp");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}