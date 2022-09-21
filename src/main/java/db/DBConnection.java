package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	// static variable in order to offer connection to dao classes
	private static Connection conn;
	
	/**
	 * Static get connection method
	 * @return connection configuration to Oracle DB 11g installed in my machine but works for more recent versions
	 */
	public static Connection getConn() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "japa", "japa");
		} catch (Exception e) {
			System.out.println("Connection error: Please, verify your configuration or installed programs");
			throw new RuntimeException(e);
		}
		return conn;
	}

}