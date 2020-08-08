package jdev01.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	private static String database = "jdbc:mysql://localhost:3306/jdev01?autoReconnect=true&useTimezone=true&serverTimezone=UTC";
	private static String password = "";
	private static String user = "root";
	private static Connection conn = null;
	
	
	static {
		connect();
	}
	public SingleConnection() {
		// TODO Auto-generated constructor stub
		connect();
	}

	private static void connect() {
		// TODO Auto-generated method stub
		try {
			if(conn == null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String url;
				conn = DriverManager.getConnection(database, user, password);
				conn.setAutoCommit(false);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("Error ao connectar banco de dados");
		}
	}
	
	public static Connection getConn() {
		return conn;
	}
}
