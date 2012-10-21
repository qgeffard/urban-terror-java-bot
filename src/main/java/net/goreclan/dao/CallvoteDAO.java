/**
 * This class represent a DAO Interface with the "callvotes" database table.
 *
 * @author      Daniele Pantaleone
 * @version     1.0
 * @copyright   Daniele Pantaleone, 08 October, 2012
 * @package     net.goreclan.dao
 **/

package net.goreclan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import net.goreclan.domain.Callvote;
import net.goreclan.exception.RecordNotFoundException;
import net.goreclan.utility.DataSourceManager;

public class CallvoteDAO {
    
	private static Connection connection;
	private static PreparedStatement statement;
	private static ResultSet resultset;
	
    private static final String LOAD = "SELECT `client_id`, `type`, `data`, `yes`, `no`, `time_add`, `time_edit` FROM `callvotes` WHERE `id` = ?";
    
    private static final String INSERT = "INSERT INTO `callvotes` (`client_id`, `type`, `data`, `yes`, `no`, `time_add`, `time_edit`) VALUES (?,?,?,?,?,?,?)";
    
    private static final String UPDATE = "UPDATE `callvotes` SET `client_id` = ?, `type` = ?, `data` = ?, `yes` = ?, `no` = ?, `time_edit` = ? WHERE `id` = ?";
    
    private static final String DELETE = "DELETE FROM `callvotes` WHERE `id` = ?";
    
    
    /**
     * Load values from the database and fill object attributes.
     * 
     * @author Daniele Pantaleone
     * @param  callvote The callvote object where to store data
     * @throws ClassNotFoundException 
     * @throws SQLException 
     * @throws RecordNotFoundException 
     **/
    public static void load(Callvote callvote) throws ClassNotFoundException, SQLException, RecordNotFoundException {
        
    	if (connection == null || connection.isClosed()) 
    		connection = DataSourceManager.getConnection();
        
    	statement = connection.prepareStatement(LOAD);
    	statement.setInt(1, callvote.id);
        resultset = statement.executeQuery();
        
        if (!resultset.next())
        	throw new RecordNotFoundException("Unable to find a match for callvote: " + callvote.id + ".");
        
        // Storing the callvote data.
        callvote.client_id = resultset.getInt("client_id");
        callvote.type = resultset.getString("type");
        callvote.data = resultset.getString("data");
        callvote.yes = resultset.getInt("yes");
        callvote.no = resultset.getInt("no");
        callvote.time_add = new Date(resultset.getLong("time_add"));
        callvote.time_edit = new Date(resultset.getLong("time_edit"));
            
        resultset.close();
        statement.close();
            
    }
    
    
    /**
     * Create a new entry in the database for the current object.
     * 
     * @author Daniele Pantaleone
     * @param  callvote The callvote whose informations needs to be stored
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public static void insert(Callvote callvote) throws ClassNotFoundException, SQLException { 
        
    	if (connection == null || connection.isClosed()) 
    		connection = DataSourceManager.getConnection();
    	
    	statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
    	statement.setInt(1, callvote.client_id);
    	statement.setString(2, callvote.type);
    	statement.setString(3, callvote.data);
    	statement.setInt(4, callvote.yes);
    	statement.setInt(5, callvote.no);
    	statement.setLong(6, callvote.time_add.getTime());
    	statement.setLong(7, callvote.time_edit.getTime());
    	
    	// Executing the statement.
    	statement.executeUpdate();
    	 
    	// Storing the new generated callvote id.
    	resultset = statement.getGeneratedKeys();
    	callvote.id = resultset.getInt(1);
        
        resultset.close();
        statement.close();
        
    }
    
    
    /**
     * Update domain object in the database.
     * 
     * @author Daniele Pantaleone
     * @param  callvote The callvote whose informations needs to be updated
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public static void update(Callvote callvote) throws ClassNotFoundException, SQLException { 
        
    	if (connection == null || connection.isClosed()) 
    		connection = DataSourceManager.getConnection();
        
    	statement = connection.prepareStatement(UPDATE);
    	statement.setInt(1, callvote.client_id);
    	statement.setString(2, callvote.type);
    	statement.setString(3, callvote.data);
    	statement.setInt(4, callvote.yes);
    	statement.setInt(5, callvote.no);
        statement.setLong(6, callvote.time_edit.getTime());
        statement.setInt(7, callvote.id);
        
        // Executing the statement.
        statement.executeUpdate();
        statement.close();
        
    }
    
    
    /**
     * Delete domain object from the database.
     * 
     * @author Daniele Pantaleone
     * @param  callvote The callvote whose informations needs to be deleted
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public static void delete(Callvote callvote) throws ClassNotFoundException, SQLException { 
        
    	if (connection == null || connection.isClosed()) 
    		connection = DataSourceManager.getConnection();
        
    	statement = connection.prepareStatement(DELETE);
    	statement.setInt(1, callvote.id);
    	
    	// Executing the statement.
    	statement.executeUpdate();
        statement.close();
        
    }
    
}