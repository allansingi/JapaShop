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
 * Triggers the "Update" button from ADMIN side in product list table
 */
@WebServlet("/editProductServlet")
public class EditProductServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			Integer id = Integer.parseInt(req.getParameter("id"));
			String name = req.getParameter("name");
			String category = req.getParameter("category");
			Integer price = Integer.parseInt(req.getParameter("price"));
			String active = req.getParameter("active");
			
			Product product = new Product(id, name, category, price, active);
			ProductDao dao = new ProductDao(DBConnection.getConn());
			
			HttpSession session = req.getSession();
			if(dao.editProduct(product)) {
				Product productObj = dao.getProductById(id);
				session.setAttribute("editProdSuccessMsg", "Product Updated Successfully !!");
				session.setAttribute("productObj", productObj);
				resp.sendRedirect("admin/admin_viewProducts.jsp");
			} else {
				session.setAttribute("editProdErrorMsg", "Something went wrong on server ...");
				resp.sendRedirect("admin/admin_viewProducts.jsp");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}