package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entity.User;

public class UserDao {
	
	// connection variable created to get connection from DBConnection class
	private Connection conn;

	/**
	 * Constructor designed to always receive the getConnection method when instantiated
	 * @param argument - connection variable and getConnection method
	 * @return connection to BD
	 */
	public UserDao(Connection conn) {
		this.conn = conn;
	}
	
	/**
	 * Auxiliary method for editUserDetails logic in order to return the edited object to populate the fields dynamically
	 * @param String email to identify the user
	 * @return desired user
	 */
	public User getUserByEmail(String email) {
		User user = null;
		
		try {
			String sql = "SELECT * FROM users WHERE email='"+email+"'";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				user = new User();
				user.setName(rs.getString(1));
				user.setEmail(rs.getString(2));
				user.setMobileNumber(rs.getString(3));
				user.setSecurityQuestion(rs.getString(4));
				user.setAnswer(rs.getString(5));
				user.setPassword(rs.getString(6));
				user.setAddress(rs.getString(7));
				user.setCity(rs.getString(8));
				user.setState(rs.getString(9));
				user.setCountry(rs.getString(10));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return user;
	}
	
	/**
	 * Create user logic - the user types the requested data and the servlet sends it to DB 
	 * @param  User object
	 * @return boolean defining if the method worked or not  
	 */
	public boolean signup(User user) {
		boolean flag = false;
		
		try {
			String sql = "INSERT INTO users(name, email, mobileNumber, securityQuestion, answer, password, address, city, state, country) VALUES('"+user.getName()+"','"+user.getEmail()+"','"+user.getMobileNumber()+"','"+user.getSecurityQuestion()+"','"+user.getAnswer()+"','"+user.getPassword()+"','"+user.getAddress()+"','"+user.getCity()+"','"+user.getState()+"','"+user.getCountry()+"')";
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
	 * User login logic - the user types the requested data and the servlet checks it in the DB 
	 * @param  String email and String password
	 * @return user object with all data from the DB  
	 */
	public User login(String email, String password) {
		User user = null;
		
		try {
			String sql = "SELECT * FROM users WHERE email='"+email+"' AND password='"+password+"'";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				user = new User();
				user.setName(rs.getString(1));
				user.setEmail(rs.getString(2));
				user.setMobileNumber(rs.getString(3));
				user.setSecurityQuestion(rs.getString(4));
				user.setAnswer(rs.getString(5));
				user.setPassword(rs.getString(6));
				user.setAddress(rs.getString(7));
				user.setCity(rs.getString(8));
				user.setState(rs.getString(9));
				user.setCountry(rs.getString(10));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return user;
	}
	
	/**
	 * Forgot password logic - the method fetch all user data necessary to identify the user
	 * @param  String email, String mobileNumber, String securityQuestion, String answer, String newPassword
	 * @return boolean defining if the method worked or not
	 */
	public boolean passwordRecall(String email, String mobileNumber, String securityQuestion, String answer, String newPassword) {
		boolean flag = false;
		
		try {
			String sql = "SELECT * FROM users WHERE email='"+email+"' AND mobileNumber='"+mobileNumber+"' AND securityQuestion='"+securityQuestion+"' AND answer='"+answer+"'";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				flag = true;
				String sql2 = "UPDATE users SET password='"+newPassword+"' WHERE email='"+email+"'";
				PreparedStatement ps2 = conn.prepareStatement(sql2);
				ps2.executeUpdate();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return flag;
	}
	
	/**
	 * Checks if the old password matches
	 * @param  String email, String oldPassword
	 * @return boolean defining if the method worked or not
	 */
	public boolean checkOldPassword(String email, String oldPassword) {
		boolean flag = false;
		
		try {
			String sql = "SELECT * FROM users WHERE email='"+email+"' AND password='"+oldPassword+"'";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return flag;
	}
	
	/**
	 * @param  String email, String oldPassword
	 * @return boolean defining if the method worked or not
	 */
	public boolean changePassword(String email, String newPassword) {
		boolean flag = false;
		
		try {
			String sql = "UPDATE users SET password='"+newPassword+"' WHERE email='"+email+"'";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			int i = ps.executeUpdate();
			if (i == 1) {
				flag = true;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return flag;
	}
	
	/**
	 * Checks if the old mobile number matches
	 * @param  String email, String oldMobileNumber
	 * @return boolean defining if the method worked or not
	 */
	public boolean checkOldMobileNumber(String email, String oldMobileNumber) {
		boolean flag = false;
		
		try {
			String sql = "SELECT * FROM users WHERE email='"+email+"' AND mobileNumber='"+oldMobileNumber+"'";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return flag;
	}
	
	/**
	 * @param  String email, String newMobileNumber
	 * @return boolean defining if the method worked or not
	 */
	public boolean changeMobileNumber(String email, String newMobileNumber) {
		boolean flag = false;
		
		try {
			String sql = "UPDATE users SET mobileNumber='"+newMobileNumber+"' WHERE email='"+email+"'";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			int i = ps.executeUpdate();
			if (i == 1) {
				flag = true;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return flag;
	}
	
	/**
	 * Checks if the old security question matches
	 * @param  String email, String oldSecurityQuestion, String oldAnswer
	 * @return boolean defining if the method worked or not
	 */
	public boolean checkOldSecurityQuestionAndAnswer(String email, String oldSecurityQuestion, String oldAnswer) {
		boolean flag = false;
		
		try {
			String sql = "SELECT * FROM users WHERE email='"+email+"' AND securityQuestion='"+oldSecurityQuestion+"' AND answer='"+oldAnswer+"'";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return flag;
	}
	
	/**
	 * @param  String email, String securityQuestion, String newAnswer
	 * @return boolean defining if the method worked or not
	 */
	public boolean changeSecurityQuestionAndAnswer(String email, String securityQuestion, String newAnswer) {
		boolean flag = false;
		
		try {
			String sql = "UPDATE users SET securityQuestion='"+securityQuestion+"',answer='"+newAnswer+"' WHERE email='"+email+"'";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			int i = ps.executeUpdate();
			if (i == 1) {
				flag = true;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return flag;
	}
	
	/**
	 * @param  String email, String address, String city, String state, String country
	 * @return boolean defining if the method worked or not
	 */
	public boolean changeAddAddress(String email, String address, String city, String state, String country) {
		boolean flag = false;
		
		try {
			String sql = "UPDATE users SET address='"+address+"',city='"+city+"',state='"+state+"',country='"+country+"' WHERE email='"+email+"'";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			int i = ps.executeUpdate();
			if (i == 1) {
				flag = true;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return flag;
	}
	
	/**
	 * @param  String address, String city, String state, String country, String mobileNumber, String email
	 * @return boolean defining if the method worked or not
	 */
	public boolean updateUserDetails(String address, String city, String state, String country, String mobileNumber, String email) {
		boolean flag = false;
		
		try {
			String sql = "UPDATE users SET address='"+address+"',city='"+city+"',state='"+state+"',country='"+country+"',mobileNumber='"+mobileNumber+"' WHERE email='"+email+"'";
			PreparedStatement ps = conn.prepareStatement(sql);

			int i = ps.executeUpdate();
			if (i == 1) {
				flag = true;
			}
		} catch (Exception e ) {
			throw new RuntimeException(e);
		}
		return flag;
	}
	
	/**
	 * Counts and populates the ADMIN dash board index dynamically with total users in table as soon as they get
	 * @return integer total of users in table
	 */
	public Integer countUsers() {
		Integer i = 0;
		try {
			String sql = "SELECT count(*) FROM users";
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