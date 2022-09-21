package servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Triggers the "Logout" button from ADMIN side in admin navbar button
 */
@WebServlet("/adminLogoutServlet")
public class AdminLogoutServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			HttpSession session = req.getSession();
			session.removeAttribute("adminObj");
			session.setAttribute("admLogoutSuccessMsg", "Admin Logged Out Successfully!");
			resp.sendRedirect("login_index.jsp");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}