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
import util.PasswordValidator;

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
			String newPassword1 = req.getParameter("newPassword1");
			String newPassword2 = req.getParameter("newPassword2");
			
			UserDao dao = new UserDao(DBConnection.getConn());

			HttpSession session = req.getSession();
			if(PasswordValidator.isValid(newPassword1)) {
				if(newPassword1.equals(newPassword2)) {
					if(dao.passwordRecall(email, mobileNumber, securityQuestion, answer, newPassword1)) {
						session.setAttribute("updatePassSuccessMsg", "Password Updated Successfully!");
						resp.sendRedirect("login_index.jsp");
					} else {
						session.setAttribute("incorrectInfDataMsg", "Incorrect informed data, try again");
						resp.sendRedirect("user_forgotPassword.jsp");
					}
				} else {
					session.setAttribute("passwordsDontMatch", "Password fields don't match, try again");
					resp.sendRedirect("user_forgotPassword.jsp");
				}
			} else {
				session.setAttribute("passwordNotValidMsg", "The password doesn't reach minimum requirements, try again");
				resp.sendRedirect("user_forgotPassword.jsp");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}