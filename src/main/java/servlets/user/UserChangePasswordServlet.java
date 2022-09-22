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
 * Triggers the "Update" button from USER side in user details section
 */
@WebServlet("/userChangePasswordServlet")
public class UserChangePasswordServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String email = req.getParameter("email");
			String oldPassword = req.getParameter("oldPassword");
			String newPassword1 = req.getParameter("newPassword1");
			String newPassword2 = req.getParameter("newPassword2");
			UserDao dao = new UserDao(DBConnection.getConn());
			
			HttpSession session = req.getSession();
			if(dao.checkOldPassword(email, oldPassword)) {
				if(PasswordValidator.isValid(newPassword1)) {
					if(newPassword1.equals(newPassword2)) {
						if(dao.changePassword(email, newPassword1)) {
							session.setAttribute("changePassSuccessMsg", "Password Changed Successfully !!");
							resp.sendRedirect("user_changePassword.jsp");
						} else {
							session.setAttribute("changePassErrorMsg", "Something Went Wrong on Server ...");
							resp.sendRedirect("user_changePassword.jsp");
						}
					} else {
						session.setAttribute("passwordsDontMatch", "Password fields don't match, try again");
						resp.sendRedirect("user_changePassword.jsp");
					}
				} else {
					session.setAttribute("passwordNotValidMsg", "The password doesn't reach minimum requirements, try again");
					resp.sendRedirect("user_changePassword.jsp");
				}
			} else {
				session.setAttribute("incorrectOldPassMsg", "Old Password is Incorrect...");
				resp.sendRedirect("user_changePassword.jsp");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}