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
@WebServlet("/updateSecurityQuestionAndAnswerServlet")
public class UpdateSecurityQuestionAndAnswerServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String email = req.getParameter("email");
			String oldSecurityQuestion = req.getParameter("oldSecurityQuestion");
			String oldAnswer = req.getParameter("oldAnswer");
			String newSecurityQuestion = req.getParameter("newSecurityQuestion");
			String newAnswer = req.getParameter("newAnswer");
			
			UserDao dao = new UserDao(DBConnection.getConn());
			User updatedUser = null;
			
			HttpSession session = req.getSession();
			if(dao.checkOldSecurityQuestionAndAnswer(email, oldSecurityQuestion, oldAnswer)) {
				if(dao.changeSecurityQuestionAndAnswer(email, newSecurityQuestion, newAnswer)) {
					updatedUser = dao.getUserByEmail(email);
					session.setAttribute("secQuesSuccessMsg", "Security Question and Answer Updated Successfully !!");
					session.setAttribute("userObj", updatedUser);
					resp.sendRedirect("user_userDetails.jsp");
				} else {
					session.setAttribute("secQuesErrorMsg", "Something Went Wrong on Server ...");
					resp.sendRedirect("user_updateSecurityQuestion.jsp");
				}
			} else {
				session.setAttribute("incorrectOldSecQuesMsg", "Old Security Question and/or Answer is Incorrect...");
				resp.sendRedirect("user_updateSecurityQuestion.jsp");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}