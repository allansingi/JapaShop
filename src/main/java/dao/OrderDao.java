package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
	
	// connection variable created to get connection from DBConnection class
	private Connection conn;
	
	/**
	 * Constructor designed to always receive the getConnection method when instantiated
	 * @param connection variable and getConnection method
	 * @return connection to BD
	 */
	public OrderDao(Connection conn) {
		this.conn = conn;
	}
	
	
	/**
	 * Getter methods to offer order data in order to populate tables and specific fields
	 * @return lists, list of list and integers
	 * 
	 * 
	 * Populates the user cart table list
	 * @param email that identifies the user
	 * @return list of list
	 */
	public List<List<String>> getUserOrdersList(String email) {
		List<List<String>> list = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM cart INNER JOIN products ON cart.product_id=products.id AND cart.email='"+email+"' AND cart.order_date IS NOT NULL ORDER BY order_date DESC";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ArrayList<String> line = new ArrayList<String>();
				line.add(rs.getString(17));
				line.add(rs.getString(18));
				line.add(rs.getString(19));
				line.add(rs.getString(3));
				line.add(rs.getString(5));
				line.add(rs.getString(11));
				line.add(rs.getString(12));
				line.add(rs.getString(13));
				line.add(rs.getString(15));
				list.add(line);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}
	
	/**
	 * Populates sum total field in cart form
	 * @param email that identifies the user
	 * @return total sum of all products in cart
	 */
	public Integer getOrderedCartTotalAmount(String email) {
		int total = 0;
		
		try {
			String sql = "SELECT sum(total) FROM cart WHERE email='"+email+"' AND status='invoice'";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				total = rs.getInt(1);
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return total;
	}
	
	/**
	 * Populates the invoice screen this user details for printing
	 * @param email that identifies the user
	 * @return list
	 */
	public List<String> getUserDetailsWithInvoiceStatus(String email) {
		List<String> list = new ArrayList<>();
						
		try {
			String sql = "SELECT * FROM users INNER JOIN cart ON users.email=cart.email AND cart.email='"+email+"' AND cart.status='invoice' ORDER BY order_date DESC";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getString(1));
				list.add(rs.getString(2));
				list.add(rs.getString(3));
				list.add(rs.getString(21));
				list.add(rs.getString(23));
				list.add(rs.getString(22));
				list.add(rs.getString(24));
				list.add(rs.getString(17));
				list.add(rs.getString(16));
				list.add(rs.getString(9));
				list.add(rs.getString(10));
				break;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}
	
	/**
	 * Populates the user invoice table with products ordered
	 * @param email that identifies the user
	 * @return list of list
	 */
	public List<List<String>> getOrderListWithInvoiceStatus(String email) {
		List<List<String>> list = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM cart INNER JOIN products ON cart.product_id=products.id AND cart.email='"+email+"' AND cart.status='invoice' ORDER BY order_date DESC";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ArrayList<String> line = new ArrayList<String>();
				line.add(rs.getString(17));
				line.add(rs.getString(18));
				line.add(rs.getString(19));
				line.add(rs.getString(3));
				line.add(rs.getString(5));
				list.add(line);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}
	
	/**
	 * Getter methods to offer order ADMIN data in order to populate tables and specific fields
	 *
	 *
	 * Populates the ADMIN order list table with orders with processing status in order to the ADMIN be able to update the order status to canceled or delivered
	 * @return list of list
	 */
	public List<List<String>> getAdminOrderListWithProcessingStatus() {
		List<List<String>> list = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM cart INNER JOIN products ON cart.product_id=products.id AND cart.order_date IS NOT NULL and cart.status='processing' ORDER BY order_date DESC";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ArrayList<String> line = new ArrayList<String>();
				line.add(rs.getString(10));
				line.add(rs.getString(17));
				line.add(rs.getString(3));
				line.add(rs.getString(5));
				line.add(rs.getString(6));
				line.add(rs.getString(7));
				line.add(rs.getString(8));
				line.add(rs.getString(9));
				line.add(rs.getString(11));
				line.add(rs.getString(12));
				line.add(rs.getString(13));
				line.add(rs.getString(14));
				line.add(rs.getString(15));
				line.add(rs.getString(2));
				line.add(rs.getString(1));
				list.add(line);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}
	
	/**
	 * Populates the ADMIN canceled orders list 
	 * @return list of list
	 */
	public List<List<String>> getAdminOrderListWithCanceledStatus() {
		List<List<String>> list = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM cart INNER JOIN products ON cart.product_id=products.id AND cart.order_date IS NOT NULL and cart.status='canceled' ORDER BY order_date DESC";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ArrayList<String> line = new ArrayList<String>();
				line.add(rs.getString(10));
				line.add(rs.getString(17));
				line.add(rs.getString(3));
				line.add(rs.getString(5));
				line.add(rs.getString(6));
				line.add(rs.getString(7));
				line.add(rs.getString(8));
				line.add(rs.getString(9));
				line.add(rs.getString(11));
				line.add(rs.getString(12));
				line.add(rs.getString(13));
				line.add(rs.getString(14));
				line.add(rs.getString(15));
				line.add(rs.getString(2));
				line.add(rs.getString(1));
				list.add(line);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}
	
	/**
	 * Populates the ADMIN delivered orders list 
	 * @return list of list
	 */
	public List<List<String>> getAdminOrderListWithDeliveredStatus() {
		List<List<String>> list = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM cart INNER JOIN products ON cart.product_id=products.id AND cart.order_date IS NOT NULL and cart.status='delivered' ORDER BY order_date DESC";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ArrayList<String> line = new ArrayList<String>();
				line.add(rs.getString(10));
				line.add(rs.getString(17));
				line.add(rs.getString(3));
				line.add(rs.getString(5));
				line.add(rs.getString(6));
				line.add(rs.getString(7));
				line.add(rs.getString(8));
				line.add(rs.getString(9));
				line.add(rs.getString(11));
				line.add(rs.getString(12));
				line.add(rs.getString(13));
				line.add(rs.getString(14));
				line.add(rs.getString(15));
				line.add(rs.getString(2));
				line.add(rs.getString(1));
				list.add(line);
			}
		} catch (Exception e ) {
			throw new RuntimeException(e);
		}
		return list;
	}
	
	/**
	 * Counts and populates the ADMIN dash board index dynamically with new orders total as soon as they get
	 * @return integer total new orders(counts the status cart column with processing status)
	 */
	public Integer getAdminNewOrdersTotal() {
		Integer i = 0;
		try {
			String sql = "SELECT count(*) FROM cart WHERE status='processing'";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				i = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
	/**
	 * status column changer methods in order to update the order inside the business logic
	 * @return booleans for servlets conditional statements
	 */
	public boolean changeInvoiceStatusToProcessing(String email) {
		boolean flag = false;
		String status = "processing";
		
		try {
			String sql = "UPDATE cart SET status='"+status+"' WHERE email='"+email+"' AND status='invoice'";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.executeUpdate();
			flag = true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return flag;
	}
	
	public boolean changeInvoiceStatusToCanceled(String product_id, String email) {
		boolean flag = false;
		String status = "canceled";
		
		try {
			String sql = "UPDATE cart SET status='"+status+"' WHERE product_id='"+product_id+"' AND email='"+email+"' AND status='processing'";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.executeUpdate();
			flag = true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return flag;
	}
	
	public boolean changeInvoiceStatusToDelivered(String product_id, String email) {
		boolean flag = false;
		String status = "delivered";
		
		try {
			String sql = "UPDATE cart SET status='"+status+"' WHERE product_id='"+product_id+"' AND email='"+email+"' AND address IS NOT NULL";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.executeUpdate();
			flag = true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return flag;
	}
	
	/**
	 * cart table update method with last user details for delivery in order to start the business logic for ADMIN side
	 * @param receive String address, String city, String state, String country, String mobileNumber, String paymentMethod, String transaction_id, String status, String email parameters from the servlet fetch of the submit form and updates the cart table
	 * @return boolean for conditional statement in the SubmitOrderServlet
	 */
	public boolean submitOrder(String address, String city, String state, String country, String mobileNumber, String paymentMethod, String transaction_id, String status, String email) {
		boolean flag = false;
		
		try {
			String sql = "UPDATE cart SET address='"+address+"',city='"+city+"',state='"+state+"',country='"+country+"',mobileNumber='"+mobileNumber+"',order_date=SYSDATE,delivery_date=SYSDATE+7,payment_method='"+paymentMethod+"',transaction_id='"+transaction_id+"',status='"+status+"' WHERE email='"+email+"' AND address IS NULL";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			flag = true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return flag;
	}

}