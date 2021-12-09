package data;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Date;

//public class DbConnector {
//	
//	public static DbConnector instancia;
//	
//	private String driver="com.mysql.cj.jdbc.Driver";
//	private String host="localhost";
//	private String port="3306";
//	private String user="tpintegrador";
//	private String password="aprobamos";
//	private String db="trabajointegrador";
//	private int conectados=0;
//	private Connection conn=null;
//	
//	private DbConnector() {
//		try {
//			Class.forName(driver);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		
//	}
//	
//	public static DbConnector getInstancia() {
//		if (instancia == null) {
//			instancia = new DbConnector();
//		}
//		return instancia;
//	}
//	
//	public Connection getConn() throws SQLException{
//		try {
//			if(conn==null || conn.isClosed()) {
//				conn=DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db, user, password);
//				conectados=0;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		conectados++;
//		return conn;
//	}
//	
//	public void releaseConn() throws SQLException{
//		conectados--;
//		try {
//			if (conectados<=0) {
//				conn.close();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//}

public class DbConnector {
	
	public static DbConnector instancia;
	
	private static final int LoginTimeout = 10;  
	public String date = new SimpleDateFormat("dd-MM-yyyy-HH-mm").format(new Date());
	private int conectados=0;
	private Connection conn;
	
	
	public static DbConnector getInstancia() {
		if (instancia == null) {
			instancia = new DbConnector();
		}
		return instancia;
	}
	
	public Connection getConn() throws SQLException {
		 Properties prop = new Properties();  
	        String host;  
	        String username;  
	        String password;  
	        String driver;  
	        try {  
	            prop.load(new java.io.FileInputStream(System.getProperty("user.home") + "/mydb.cfg"));  

	            host = prop.getProperty("host").toString();  
	            username = prop.getProperty("username").toString();  
	            password = prop.getProperty("password").toString();  
	            driver = prop.getProperty("driver").toString();  
	        } catch (IOException e) {  
	            System.out.println("Unable to find mydb.cfg in " + System.getProperty("user.home") + "\n Please make sure that configuration file created in this folder.");  
	            
	            host = "Unknown HOST";  
	            username = "Unknown USER";  
	            password = "Unknown PASSWORD";  
	            driver = "Unknown DRIVER";  
	        }  

	        try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}  
	        DriverManager.setLoginTimeout(LoginTimeout);  
	        Connection conn = DriverManager.getConnection(host, username, password);  
	        
	        return conn;
	}
	
	public void releaseConn() {
		conectados--;
		try {
			conn = this.getConn();
			if (conectados<=0) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}

