package org.reciept.util;

import java.sql.*;
import java.sql.DriverManager;

import oracle.jdbc.OracleDriver;

public class DBConnector {
	  private static Connection con = null;
	  
	    static
	    {
	        String url = "jdbc:oracle:thin:@localhost:1521:xe";
	        String user = "SYSTEM";
	        String pass = "P@$$w0rd";
	        try {
	        	DriverManager.registerDriver(new OracleDriver());
	            con = DriverManager.getConnection(url, user, pass);
				
	        }
	        catch ( SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    public static Connection getConnection()
	    {
	        return con;
	    }
}
