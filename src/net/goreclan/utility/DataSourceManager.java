/**
 * This class goal is to return a connection with a MySQL DBMS.
 * If the connection is not established yet, it create a new one 
 * before returning the connection handler.
 *
 * @author      Daniele Pantaleone
 * @version     1.2
 * @copyright   Daniele Pantaleone, 05 October, 2012
 * @package     net.goreclan.utility
 **/

package net.goreclan.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceManager {
    
    private static Connection connection;
    private static String username;
    private static String password;
    private static String dcs;
    
    
    /**
     * Return the connection with the MySQL DBMS.
     * 
     * @author Daniele Pantaleone
     * @throws ClassNotFoundException 
     * @throws SQLException 
     * @return Connection
     **/
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        
        if (connection == null) {
        	Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dcs, username, password);
            connection.setAutoCommit(true);
        }
        
        return connection;

    }
    
    
    /**
     * Set the MySQL DBMS access username.
     * 
     * @author Daniele Pantaleone
     * @param  username The username to be set
     **/
    public static void setUsername(String username) {
    	DataSourceManager.username = username;
    }
    
    
    /**
     * Set the MySQL DBMS access password.
     * 
     * @author Daniele Pantaleone
     * @param  password The password to be set
     **/
    public static void setPassword(String password) {
    	DataSourceManager.password = password;
    }
    
    
    /**
     * Set the MySQL DBMS DCS (Database Connection String).
     * 
     * @author Daniele Pantaleone
     * @param  dcs The database connection string
     **/
    public static void setDCS(String dcs) {
    	DataSourceManager.dcs = dcs;
    }
    
    
   

}