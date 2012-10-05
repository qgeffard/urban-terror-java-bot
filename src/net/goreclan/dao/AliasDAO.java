/**
 * This class represents a DAO interface with the "aliases" database table.
 * 
 * @author 		Mathias Van Malderen, Daniele Pantaleone
 * @version 	1.2
 * @copyright 	Mathias Van Malderen, 05 October, 2012
 * @package 	net.goreclan.dao
 **/

package net.goreclan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import net.goreclan.domain.Alias;
import net.goreclan.exception.RecordNotFoundException;
import net.goreclan.utility.DataSourceManager;

public class AliasDAO {
	
	private static Connection connection;
	private static PreparedStatement statement;
	private static ResultSet resultset;
	
    private static final String LOAD = "SELECT `client_id`, `name`, `num_used`, `time_add`, `time_edit` FROM `aliases` WHERE `id` = ?";
    
    private static final String INSERT = "INSERT INTO `aliases` (`client_id`, `name`, `num_used`, `time_add`) VALUES (?,?,?,?)";
    
    private static final String UPDATE = "UPDATE `aliases` SET `client_id` = ?, `name` = ?, `num_used` = ?, `time_edit` = ? WHERE `id` = ?";
    
    private static final String DELETE = "DELETE FROM `aliases` WHERE `id` = ?";
    
    
    /**
     * Load values from the database and fill object attributes.
     * 
     * @author Mathias Van Malderen, Daniele Pantaleone
     * @param  alias The alias object where to store data
     * @throws ClassNotFoundException 
     * @throws SQLException 
     * @throws RecordNotFoundException 
     **/
    public static void load(Alias alias) throws ClassNotFoundException, SQLException, RecordNotFoundException {
        
    	if (connection == null || connection.isClosed()) 
    		connection = DataSourceManager.getConnection();
        
    	statement = connection.prepareStatement(LOAD);
    	statement.setInt(1, alias.id);
    	resultset = statement.executeQuery();
    	
    	if (!resultset.next())
        	throw new RecordNotFoundException("Unable to find a match for alias: " + alias.id + ".");
    	
    	// Storing the alias data
    	alias.client_id = resultset.getInt("client_id");
    	alias.name = resultset.getString("name");
    	alias.num_used = resultset.getInt("num_used");
    	alias.time_add = new Date(resultset.getLong("time_add"));
    	alias.time_edit = new Date(resultset.getLong("time_edit"));

    	resultset.close();
        statement.close();
            
    }
    
    
    /**
     * Create a new entry in the database for the current object.
     * 
     * @author Mathias Van Malderen, Daniele Pantaleone
     * @param  alias The alias whose informations needs to be stored
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public static void insert(Alias alias) throws ClassNotFoundException, SQLException {
        
    	if (connection == null || connection.isClosed()) 
    		connection = DataSourceManager.getConnection();
    	
    	statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
    	statement.setInt(1, alias.client_id);
    	statement.setString(2, alias.name);
    	statement.setInt(3, alias.num_used);
    	statement.setLong(4, alias.time_add.getTime());
    	statement.executeUpdate();
    	
    	// Storing the new generated alias id
    	resultset = statement.getGeneratedKeys();
    	alias.id = resultset.getInt(1);
        
    	resultset.close();
        statement.close();
            
    }
    
    
    /**
     * Update domain object in the database.
     * 
     * @author Mathias Van Malderen, Daniele Pantaleone
     * @param  alias The alias whose informations needs to be updated
     * @throws ClassNotFoundException
     * @throws SQLException 
     **/
    public static void update(Alias alias) throws ClassNotFoundException, SQLException {
        
    	if (connection == null || connection.isClosed()) 
    		connection = DataSourceManager.getConnection();
    
    	statement = connection.prepareStatement(UPDATE);
    	statement.setInt(1, alias.client_id);
    	statement.setString(2, alias.name);
    	statement.setInt(3, alias.num_used);
    	statement.setLong(4, alias.time_edit.getTime());
    	statement.setInt(5, alias.id);
    	statement.executeUpdate();
    	statement.close();
        
    }
    
    
    /**
     * Delete domain object from the database.
     * 
     * @author Mathias Van Malderen, Daniele Pantaleone
     * @param  alias The alias whose informations needs to be deleted
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public static void delete(Alias alias) throws ClassNotFoundException, SQLException {
        
    	if (connection == null || connection.isClosed()) 	
    		connection = DataSourceManager.getConnection();
        
    	statement = connection.prepareStatement(DELETE);
    	statement.setInt(1, alias.id);
    	statement.executeUpdate();
    	statement.close();
        
    }
    
}