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
@WebServlet("/updateMobileNumberServlet")
public class UpdateMobileNumberServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String email = req.getParameter("email");
			String oldPassword = req.getParameter("oldMobileNumber");
			String newPassword = req.getParameter("newMobileNumber");
			
			UserDao dao = new UserDao(DBConnection.getConn());
			User updatedUser = null;
			
			HttpSession session = req.getSession();
			if(dao.checkOldMobileNumber(email, oldPassword)) {
				if(dao.changeMobileNumber(email, newPassword)) {
					updatedUser = dao.getUserByEmail(email);
					session.setAttribute("updateMobNumSuccessMsg", "Mobile Number Updated Successfully !!");
					session.setAttribute("userObj", updatedUser);
					resp.sendRedirect("user_userDetails.jsp");
				} else {
					session.setAttribute("updateMobNumErrorMsg", "Something Went Wrong on Server ...");
					resp.sendRedirect("user_updateMobileNumber.jsp");
				}
			} else {
				session.setAttribute("incorrectOldMobNumMsg", "Old Mobile Number is Incorrect...");
				resp.sendRedirect("user_updateMobileNumber.jsp");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}