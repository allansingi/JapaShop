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

/**
 * Triggers the "Update" button from USER side in user login page
 */
@WebServlet("/userForgotPasswordServlet")
public class UserForgotPasswordServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String email = req.getParameter("email");
			String mobileNumber = req.getParameter("mobileNumber");
			String securityQuestion = req.getParameter("securityQuestion");
			String answer = req.getParameter("answer");
			String newPassword = req.getParameter("newPassword");
			
			UserDao dao = new UserDao(DBConnection.getConn());

			HttpSession session = req.getSession();
			if(dao.passwordRecall(email, mobileNumber, securityQuestion, answer, newPassword)) {
				session.setAttribute("updatePassSuccessMsg", "Password Updated Successfully!");
				resp.sendRedirect("login_index.jsp");
			} else {
				session.setAttribute("incorrectInfDataMsg", "Incorrect informed data, try again");
				resp.sendRedirect("user_forgotPassword.jsp");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}