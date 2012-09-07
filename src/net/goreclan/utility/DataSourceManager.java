/**
 * This class provides a Factory implementation of a JDBC Connector against an SQLite 3 database
 *
 * @author      Daniele Pantaleone
 * @version     1.2
 * @copyright   Daniele Pantaleone, 26 June, 2012
 * @package     net.goreclan.utility
 **/

package net.goreclan.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import net.goreclan.logger.Log;

public class DataSourceManager {
    
	private static String sqliteDB;
    private static Connection connection;
    
    
    /**
     * @return Connection
     * @author Daniele Pantaleone
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        
        if (connection == null) {
        	Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(String.format("jdbc:sqlite:%s", sqliteDB));
            connection.setAutoCommit(true);
        }
        
        return connection;

    }
    
    
    /**
     * Set the SQLite database absoulte file path
     * 
     * @author Daniele Pantaleone
     **/
    public static void setSQLiteDB(String sqliteDB) {
        Log.debug(String.format("Using SQLite database: %s.", sqliteDB));
        DataSourceManager.sqliteDB = sqliteDB;
    }

}