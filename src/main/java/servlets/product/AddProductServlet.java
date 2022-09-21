package servlets.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductDao;
import db.DBConnection;
import entity.Product;

/**
 * Triggers the "submit" button from ADMIN side in Add Product form
 */
@WebServlet("/addProductServlet")
public class AddProductServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String name = req.getParameter("name");
			String category = req.getParameter("category");
			Integer price = Integer.valueOf(req.getParameter("price"));
			String active = req.getParameter("active");
			
			ProductDao dao = new ProductDao(DBConnection.getConn());
			Product product = new Product(name, category, price, active);
			
			HttpSession session = req.getSession();
			if(dao.addProduct(product)) {
				session.setAttribute("addProdSuccessMsg", "Product Added Successfully !!");
				resp.sendRedirect("admin/admin_addProduct.jsp");
			} else {
				session.setAttribute("addProdErrorMsg", "Something went wrong on server ...");
				resp.sendRedirect("admin/admin_addProduct.jsp");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}