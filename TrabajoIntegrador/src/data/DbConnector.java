package data;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Date;

public class DbConnector {
	
	public static DbConnector instancia;
	
	private static final int LoginTimeout = 10;  
	public String date = new SimpleDateFormat("dd-MM-yyyy-HH-mm").format(new Date());
	private int conectados=0;
	private Connection conn=null;
	
	
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
			if (conectados<=0) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}

