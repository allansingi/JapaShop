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
 * Triggers the "Update" button from USER side in user details section
 */
@WebServlet("/userChangePasswordServlet")
public class UserChangePasswordServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String email = req.getParameter("email");
			String oldPassword = req.getParameter("oldPassword");
			String newPassword = req.getParameter("newPassword");
			UserDao dao = new UserDao(DBConnection.getConn());
			
			HttpSession session = req.getSession();
			if(dao.checkOldPassword(email, oldPassword)) {
				if(dao.changePassword(email, newPassword)) {
					session.setAttribute("changePassSuccessMsg", "Password Changed Successfully !!");
					resp.sendRedirect("user_changePassword.jsp");
				} else {
					session.setAttribute("changePassErrorMsg", "Something Went Wrong on Server ...");
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