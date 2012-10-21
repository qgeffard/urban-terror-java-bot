/**
 * This class represent a DAO Interface with the "groups" database table.
 *
 * @author      Daniele Pantaleone
 * @version     1.1
 * @copyright   Daniele Pantaleone, 05 October, 2012
 * @package     net.goreclan.dao
 **/

package net.goreclan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.goreclan.domain.Group;
import net.goreclan.exception.RecordNotFoundException;
import net.goreclan.utility.DataSourceManager;

public class GroupDAO {
    
	private static Connection connection;
	private static PreparedStatement statement;
	private static ResultSet resultset;
	
    private static final String LOAD = "SELECT `name`, `keyword`, `level` FROM `groups` WHERE `id` = ?";
    
    private static final String INSERT = "INSERT INTO `groups` (`id`, `name`, `keyword`, `level`) VALUES (?,?,?,?)";
    
    private static final String UPDATE = "UPDATE `groups` SET `name` = ?, `keyword` = ?, `level` = ? WHERE `id` = ?";
    
    private static final String DELETE = "DELETE FROM `groups` WHERE `id` = ?";
    
    
    /**
     * Load values from the database and fill object attributes.
     * 
     * @author Daniele Pantaleone
     * @param  group The group object where to store data
     * @throws ClassNotFoundException
     * @throws SQLException 
     * @throws RecordNotFoundException 
     **/
    public static void load(Group group) throws ClassNotFoundException, SQLException, RecordNotFoundException {
        
    	if (connection == null || connection.isClosed()) 
    		connection = DataSourceManager.getConnection();
        
    	statement = connection.prepareStatement(LOAD);
    	statement.setInt(1, group.id);
    	resultset = statement.executeQuery();
        
    	if (!resultset.next())
        	throw new RecordNotFoundException("Unable to find a match for group: " + group.id + ".");
    	
    	// Storing the group data
        group.name = resultset.getString("name");
        group.keyword = resultset.getString("keyword");
        group.level = resultset.getInt("level");

        resultset.close();
        statement.close();
        
    }
    
    
    /**
     * Create a new entry in the database for the current object
     * 
     * @author Daniele Pantaleone
     * @param  group The group whose informations needs to be stored
     * @throws ClassNotFoundException
     * @throws SQLException 
     **/
    public static void insert(Group group) throws ClassNotFoundException, SQLException { 
        
    	if (connection == null || connection.isClosed()) 
    		connection = DataSourceManager.getConnection();
    	
    	statement = connection.prepareStatement(INSERT);
    	statement.setInt(1, group.id);
    	statement.setString(2, group.name);
    	statement.setString(3, group.keyword);
    	statement.setInt(4, group.level);
    	statement.executeUpdate();
        statement.close();      
        
    }
    
    
    /**
     * Update domain object in the database
     * 
     * @author Daniele Pantaleone
     * @param  group The group whose informations needs to be updated
     * @throws ClassNotFoundException
     * @throws SQLException 
     **/
    public static void update(Group group) throws ClassNotFoundException, SQLException { 
        
    	if (connection == null || connection.isClosed()) 
    		connection = DataSourceManager.getConnection();
        
    	statement = connection.prepareStatement(UPDATE);
    	statement.setString(1, group.name);
    	statement.setString(2, group.keyword);
    	statement.setInt(3, group.level);
    	statement.setInt(4, group.id);
    	statement.executeUpdate();
        statement.close(); 
            
    }
    
    
    /**
     * Delete domain object from the database
     * 
     * @author Daniele Pantaleone
     * @param  group The group whose informations needs to be deleted
     * @throws ClassNotFoundException
     * @throws SQLException 
     **/
    public static void delete(Group group) throws ClassNotFoundException, SQLException { 
    	
    	if (connection == null || connection.isClosed()) 
    		connection = DataSourceManager.getConnection();

    	statement = connection.prepareStatement(DELETE);
        statement.setInt(1, group.id);
        statement.executeUpdate();
        statement.close(); 
        
    }
    
}