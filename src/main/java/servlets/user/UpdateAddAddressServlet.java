package servlets.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import db.DBConnection;
import entity.User;

/**
 * Triggers the "Update" button from USER side in user details section
 */
@WebServlet("/updateAddAddressServlet")
public class UpdateAddAddressServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String email = req.getParameter("email");
			String address = req.getParameter("address");
			String city = req.getParameter("city");
			String state = req.getParameter("state");
			String country = req.getParameter("country");
			
			UserDao dao = new UserDao(DBConnection.getConn());
			User updatedUser = null;
			
			HttpSession session = req.getSession();
			if(dao.changeAddAddress(email, address, city, state, country)) {
				updatedUser = dao.getUserByEmail(email);
				session.setAttribute("addressUpdateSuccessMsg", "Address Added/Updated Successfully !!");
				session.setAttribute("userObj", updatedUser);
				resp.sendRedirect("user_userDetails.jsp");
			} else {
				session.setAttribute("addressUpdateErrorMsg", "Something Went Wrong on Server ...");
				resp.sendRedirect("user_updateAddAddress.jsp");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}