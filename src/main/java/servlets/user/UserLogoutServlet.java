package servlets.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Triggers the "Logout" button from USER side in user navbar button
 */
@WebServlet("/userLogoutServlet")
public class UserLogoutServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			HttpSession session = req.getSession();
			session.removeAttribute("userObj");
			session.setAttribute("userLogoutSuccessMsg", "User Logged Out Successfully!");
			resp.sendRedirect("login_index.jsp");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}