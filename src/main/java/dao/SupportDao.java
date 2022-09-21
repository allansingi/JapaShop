package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SupportDao {
	
	// connection variable created to get connection from DBConnection class
	private Connection conn;

	/**
	 * Constructor designed to always receive the getConnection method when instantiated
	 * @param argument - connection variable and getConnection method
	 * @return connection to BD
	 */
	public SupportDao(Connection conn) {
		this.conn = conn;
	}
	
	public List<List<String>> getAllMessages() {
		List<List<String>> list = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM support ORDER BY id DESC";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ArrayList<String> line = new ArrayList<String>();
				line.add(rs.getString(1));
				line.add(rs.getString(2));
				line.add(rs.getString(3));
				line.add(rs.getString(4));
				list.add(line);
			}
		} catch (Exception e ) {
			throw new RuntimeException(e);
		}
		return list;
	}
	
	public boolean sendSupportMessage(String email, String subject, String body) {
		boolean flag = false;
		
		try {
			String sql = "INSERT INTO support(email,subject,body) VALUES(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, subject);
			ps.setString(3, body);
			
			int i = ps.executeUpdate();
			if (i == 1) {
				flag = true;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return flag;
	}
	
	public Integer countMessages() {
		Integer i = 0;
		try {
			String sql = "SELECT count(*) FROM support";
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