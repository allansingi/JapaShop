package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Product;

public class ProductDao {
	
	// connection variable created to get connection from DBConnection class
	private Connection conn;

	/**
	 * Constructor designed to always receive the getConnection method when instantiated
	 * @param argument - connection variable and getConnection method
	 * @return connection to BD
	 */
	public ProductDao(Connection conn) {
		this.conn = conn;
	}
	
	
	/**
	 * Getter methods to offer to the user the data in order to populate tables and specific fields
	 * @return list of Products(entity) and the Product itself
	 * 
	 * 
	 * Populates the user product section table list
	 * @return list of products where status column has "active" status
	 */
	public List<Product> getActiveProducts() {
		List<Product> list = new ArrayList<Product>();
		
		try {
			String sql = "SELECT * FROM products WHERE active='Yes' ORDER BY id DESC";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setName(rs.getString(2));
				product.setCategory(rs.getString(3));
				product.setPrice(rs.getInt(4));
				product.setActive(rs.getString(5));
				list.add(product);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}
	
	/**
	 * Populates the active product table by search string
	 * @param search string typed by the user in search box
	 * @return product list by typed string
	 */
	public List<Product> getProductsBySearchString(String searchString) {
		List<Product> list = new ArrayList<Product>();
		
		try {
			String sql = "SELECT * FROM products WHERE name like '%"+searchString+"%' OR category like '%"+searchString+"%' AND active='Yes'";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setName(rs.getString(2));
				product.setCategory(rs.getString(3));
				product.setPrice(rs.getInt(4));
				product.setActive(rs.getString(5));
				list.add(product);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}
	
	/**
	 * Auxiliary method for editProduct logic in order to return the edited object to populate the table dynamically
	 * @param integer product id
	 * @return desired product
	 */
	public Product getProductById(Integer id) {
		Product product = null;
		
		try {
			String sql = "SELECT * FROM products WHERE id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				product = new Product();
				product.setId(rs.getInt(1));
				product.setName(rs.getString(2));
				product.setCategory(rs.getString(3));
				product.setPrice(rs.getInt(4));
				product.setActive(rs.getString(5));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return product;
	}
	
	/**
	 * Methods that create the cart interaction logic
	 * 
	 * Updates the cart table with products and its quantity
	 * @param  String productId, String email to specify each one
	 * @return integer which defines if the product is the first one to go to the cart or not (the servlet prompts the user differently)
	 */
	public Integer addProductToCart(String productId, String email) {
		int quantity = 1;
		int product_price = 0;
		int product_total = 0;
		int cart_total = 0;
		int i = 0;
		
		try {
			String sql = "SELECT * FROM products WHERE id='"+productId+"'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				product_price = rs.getInt(4);
				product_total = product_price;
			}
			String sql1 = "SELECT * FROM cart WHERE product_id='"+productId+"' AND email='"+email+"' AND address IS NULL";
			PreparedStatement ps1 = conn.prepareStatement(sql1);
			ResultSet rs1 = ps1.executeQuery();
			while(rs1.next()) {
				cart_total = rs1.getInt(5);
				cart_total = cart_total + product_total;
				quantity = rs1.getInt(3);
				quantity = quantity + 1;
				i = 1;
			}
			if(i == 1) {
				String sql2 = "UPDATE cart SET total='"+cart_total+"',quantity='"+quantity+"' WHERE product_id='"+productId+"' AND email='"+email+"' AND address IS NULL";
				PreparedStatement ps2 = conn.prepareStatement(sql2);
				ps2.executeUpdate();
			}
			if(i == 0) {
				String sql3 = "INSERT INTO cart(email,product_id,quantity,price,total) values('"+email+"','"+productId+"','"+quantity+"','"+product_price+"','"+product_total+"')";
				PreparedStatement ps3 = conn.prepareStatement(sql3);
				ps3.executeUpdate();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return i;
	}
	
	/**
	 * Updates the cart table with quantity by clicking on - or + icons
	 * @param  String email (identifies the user), String id (identifies the product) and String qty (identifies if - or + icon)
	 * @return integer which defines if the product is the last one of the cart or not (the servlet prompts the user differently)
	 */
	public Integer incDecCartItem(String email, String id, String qty) {
		int quantity = 0;
		int price = 0;
		int total = 0;
		
		try {
			String sql = "SELECT * FROM cart WHERE email='"+email+"' AND product_id='"+id+"' AND address IS NULL";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				quantity = rs.getInt(3);
				price = rs.getInt(4);
				total = rs.getInt(5);
			}
			if(quantity == 1 && qty.equals("dec")) {
				return -1;
			} else if (quantity > 1 && qty.equals("dec")) {
				total = total - price;
				quantity = quantity - 1;
				String sql1 = "UPDATE cart SET total='"+total+"',quantity='"+quantity+"' WHERE email='"+email+"' AND product_id='"+id+"' AND address IS NULL";
				PreparedStatement ps1 = conn.prepareStatement(sql1);
				ps1.executeUpdate();
				return 0;
			} else {
				total = total + price;
				quantity = quantity + 1;
				String sql2 = "UPDATE cart SET total='"+total+"',quantity='"+quantity+"' WHERE email='"+email+"' AND product_id='"+id+"' AND address IS NULL";
				PreparedStatement ps2 = conn.prepareStatement(sql2);
				ps2.executeUpdate();
				return 1;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Removes the product from the cart
	 * @param  String email (identifies the user) and String product_id (identifies the product)
	 * @return boolean defining if the method worked or not
	 */
	public boolean removeItemFromCart(String email, String product_id) {
		boolean flag = false;
		
		try {
			String sql = "DELETE FROM cart WHERE email='"+email+"' AND product_id='"+product_id+"' AND address IS NULL";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeQuery();
			flag = true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return flag;
	}
	
	/**
	 * Populates the cart list table with added products to the user
	 * @param email that identifies the user
	 * @return list of list with added products in cart
	 */
	public List<List<String>> getCartList(String email) {
		List<List<String>> cartList = new ArrayList<List<String>>();
		
		try {
			String sql = "SELECT * FROM products INNER JOIN cart ON products.id=cart.product_id AND cart.email='"+email+"' AND cart.address IS NULL";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ArrayList<String> line = new ArrayList<String>();
				line.add(rs.getString(2));
				line.add(rs.getString(3));
				line.add(rs.getString(4));
				line.add(rs.getString(1));
				line.add(rs.getString(8));
				line.add(rs.getString(10));
				cartList.add(line);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return cartList;
	}
	
	/**
	 * @param email that identifies the user
	 * @return sum of total amount of added products in the cart
	 */
	public Integer getCartTotalAmount(String email) {
		int total = 0;
		
		try {
			String sql = "SELECT SUM(total) FROM cart WHERE email='"+email+"' AND address IS NULL";
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
	 * Creates a new product to products table
	 * @param  Product from entity
	 * @return boolean defining if the method worked or not 
	 */
	public boolean addProduct(Product product) {
		boolean flag = false;
		
		try {
			String sql = "INSERT INTO products (NAME,CATEGORY,PRICE,ACTIVE) VALUES('"+product.getName()+"','"+product.getCategory()+"','"+product.getPrice()+"','"+product.getActive()+"')";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			int i = ps.executeUpdate();
			if(i == 1) {
				flag = true;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return flag;
	}
	
	/**
	 * Edits a existing product from products table
	 * @param  Product from entity
	 * @return boolean defining if the method worked or not 
	 */
	public boolean editProduct(Product product) {
		boolean flag = false;
		
		try {
			String sql = "UPDATE products SET name='"+product.getName()+"',category='"+product.getCategory()+"',price='"+product.getPrice()+"',active='"+product.getActive()+"' WHERE id='"+product.getId()+"'";
			PreparedStatement ps = conn.prepareStatement(sql);

			Integer i = ps.executeUpdate();
			if (i == 1) {
				flag = true;
				ps.executeUpdate("DELETE FROM cart WHERE product_id='"+product.getId()+"' AND address IS NULL");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return flag;
	}
	
	/**
	 * Edits a existing product from products table
	 * @return list of all products including active(products.active='Yes') and inactive(products.active='No') values
	 */
	public List<Product> getAllProducts() {
		List<Product> list = new ArrayList<Product>();
		
		try {
			String sql = "SELECT * FROM products ORDER BY id DESC";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setName(rs.getString(2));
				product.setCategory(rs.getString(3));
				product.setPrice(rs.getInt(4));
				product.setActive(rs.getString(5));
				list.add(product);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}
	
	/**
	 * Counts and populates the ADMIN dash board index dynamically with total products in table as soon as they get
	 * @return integer total of products in table
	 */
	public Integer countProducts() {
		Integer i = 0;
		try {
			String sql = "SELECT count(*) FROM products";
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

}