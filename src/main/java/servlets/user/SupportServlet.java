package servlets.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SupportDao;
import db.DBConnection;

/**
 * Triggers the "Submit" button from USER side in Support Page
 */
@WebServlet("/supportServlet")
public class SupportServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String email = req.getParameter("email");
			String subject = req.getParameter("subject");
			String body = req.getParameter("body");
			
			SupportDao dao = new SupportDao(DBConnection.getConn());
			
			HttpSession session = req.getSession();
			if(dao.sendSupportMessage(email, subject, body)) {
				session.setAttribute("supportSuccessMsg", "Thanks! We'll contact you shortly !!");
				resp.sendRedirect("support_index.jsp");
			} else {
				session.setAttribute("supportErrorMsg", "Something Went Wrong on Server ...");
				resp.sendRedirect("support_index.jsp");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}