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
 * Triggers the "Login" button from USER side in user login page
 */
@WebServlet("/userLoginServlet")
public class UserLoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			UserDao dao = new UserDao(DBConnection.getConn());
			User user = dao.login(email, password);
			
			HttpSession session = req.getSession();
			if("admin@gmail.com".equals(email) && "admin".equals(password)) {
				session.setAttribute("adminObj", new User());
				resp.sendRedirect("admin/admin_index.jsp");
			} else if(user != null) {
				session.setAttribute("userObj", user);
				session.setAttribute("email", email);
				resp.sendRedirect("product_index.jsp");
			} else {
				session.setAttribute("loginErrorMsg", "Invalid Email and/or Password");
				resp.sendRedirect("login_index.jsp");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}