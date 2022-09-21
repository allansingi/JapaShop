package servlets.order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrderDao;
import dao.UserDao;
import db.DBConnection;

/**
 * Triggers the "proceed to checkout" button to invoice screen 
 */
@WebServlet("/submitOrderServlet")
public class SubmitOrderServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String email = req.getParameter("email");
			String address = req.getParameter("address");
			String city = req.getParameter("city");
			String state = req.getParameter("state");
			String country = req.getParameter("country");
			String payment_method = req.getParameter("payment_method");
			String transaction_id = req.getParameter("transaction_id");
			String mobileNumber = req.getParameter("mobileNumber");
			String status = "invoice";
			
			UserDao userDao = new UserDao(DBConnection.getConn());
			OrderDao orderDao = new OrderDao(DBConnection.getConn());
			
			HttpSession session = req.getSession();
			if(!userDao.updateUserDetails(address, city, state, country, mobileNumber, email)) {
				session.setAttribute("userErrorMsg", "Something went wrong on User server ...");
			} else if (!orderDao.submitOrder(address, city, state, country, mobileNumber, payment_method, transaction_id, status, email))
				session.setAttribute("orderErrorMsg", "Something went wrong on Order server ...");
			else {
				session.setAttribute("orderSuccessMsg", "Order Submited Successfully !!");
				resp.sendRedirect("invoice_index.jsp");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}