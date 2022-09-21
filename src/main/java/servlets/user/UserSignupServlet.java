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
 * Triggers the "Signup" button from USER side in user signup page
 */
@WebServlet("/userSignupServlet")
public class UserSignupServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String mobileNumber = req.getParameter("mobileNumber");
			String securityQuestion = req.getParameter("securityQuestion");
			String answer = req.getParameter("answer");
			String password = req.getParameter("password");
			String address = "";
			String city = "";
			String state = "";
			String country = "";
			
			User user = new User(name, email, mobileNumber, securityQuestion, answer, password, address, city, state, country);
			UserDao dao = new UserDao(DBConnection.getConn());
			boolean flag = dao.signup(user);
			
			HttpSession session = req.getSession();
			if(flag) {
				session.setAttribute("userRegSuccessMsg", "User Registered Successfully!");
				resp.sendRedirect("login_index.jsp");
			} else {
				session.setAttribute("userRegErrorMsg", "Something went wrong on server...");
				resp.sendRedirect("user_signup.jsp");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}