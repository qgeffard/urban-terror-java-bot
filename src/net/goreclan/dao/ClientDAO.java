/**
 * This class represent a DAO Interface with the "clients" database table.
 *
 * @author      Daniele Pantaleone
 * @version     1.2
 * @copyright   Daniele Pantaleone, 05 October, 2012
 * @package     net.goreclan.dao
 **/

package net.goreclan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import net.goreclan.domain.Client;
import net.goreclan.domain.Group;
import net.goreclan.exception.RecordNotFoundException;
import net.goreclan.utility.DataSourceManager;

public class ClientDAO {
    
	private static Connection connection;
	private static PreparedStatement statement;
	private static ResultSet resultset;
	
    private static final String LOAD = "SELECT `cl`.`name` AS `cl_name`, " +
    										  "`cl`.`connections` AS `cl_connections`, " +
                                              "`cl`.`ip` AS `cl_ip`, " +
                                              "`cl`.`guid` AS `cl_guid`, " +
                                              "`cl`.`auth` AS `cl_auth`, " +
                                              "`cl`.`time_add` AS `cl_time_add`, " +
                                              "`cl`.`time_edit` AS `cl_time_edit`, " +
                                              "`gr`.`id` AS `gr_id`, " +
                                              "`gr`.`name` AS `gr_name`, " +
                                              "`gr`.`keyword` AS `gr_keyword`, " +
                                              "`gr`.`level` AS `gr_level` " +
                                              "FROM `clients` AS `cl` INNER JOIN `groups` AS `gr` " +
                                              "ON `cl`.`group_id` = `gr`.`id` WHERE `cl`.`id` = ?";
    
    private static final String INSERT = "INSERT INTO `clients` (`group_id`, `name`, `ip`, `guid`, `auth`, `time_add`) VALUES (?,?,?,?,?,?)";
    
    private static final String UPDATE = "UPDATE `clients` SET `group_id` = ?, `name` = ?, `connections` = ?, `ip` = ?, `guid` = ?, `auth` = ?, `time_add` = ?, `time_edit`= ? WHERE `id` = ?";
    
    private static final String DELETE = "DELETE FROM `clients` WHERE `id` = ?";
    
    
    /**
     * Load values from the database and fill object attributes.
     * 
     * @author Daniele Pantaleone
     * @param  client The client object where to store data
     * @throws ClassNotFoundException 
     * @throws SQLException 
     * @throws RecordNotFoundException 
     **/
    public static void load(Client client) throws ClassNotFoundException, SQLException, RecordNotFoundException {
        
    	if (connection == null || connection.isClosed()) 
    		connection = DataSourceManager.getConnection();
        
    	statement = connection.prepareStatement(LOAD);
    	statement.setInt(1, client.id);
        resultset = statement.executeQuery();
        
        if (!resultset.next())
        	throw new RecordNotFoundException("Unable to find a match for client: @" + client.id + ".");
        
        // Creating the Group object
        Group group = new Group();
        group.id = resultset.getInt("gr_id");
        group.name = resultset.getString("gr_name");
        group.keyword = resultset.getString("gr_keyword");
        group.level = resultset.getInt("gr_level");
        
        // Storing the client data
        client.group = group;
        client.name = resultset.getString("cl_name");
        client.connections = resultset.getInt("cl_connections");
        client.ip = resultset.getString("cl_ip");
        client.guid = resultset.getString("cl_guid");
        client.auth = resultset.getString("cl_auth");
        client.time_add = new Date(resultset.getLong("cl_time_add"));
        client.time_edit = new Date(resultset.getLong("cl_time_edit"));
            
        resultset.close();
        statement.close();
            
    }
    
    
    /**
     * Create a new entry in the database for the current object.
     * 
     * @author Daniele Pantaleone
     * @param  client The client whose informations needs to be stored
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public static void insert(Client client) throws ClassNotFoundException, SQLException { 
        
    	if (connection == null || connection.isClosed()) 
    		connection = DataSourceManager.getConnection();
    	
    	statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
    	statement.setInt(1, client.group.id);
    	statement.setString(2, client.name);
    	statement.setString(3, client.ip);
    	statement.setString(4, client.guid);
    	statement.setString(5, client.auth);
    	statement.setLong(6, client.time_add.getTime());
    	statement.executeUpdate();
    	
    	// Storing the new generated client id
    	resultset = statement.getGeneratedKeys();
    	client.id = resultset.getInt(1);
        
        resultset.close();
        statement.close();
        
    }
    
    
    /**
     * Update domain object in the database.
     * 
     * @author Daniele Pantaleone
     * @param  client The client whose informations needs to be updated
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public static void update(Client client) throws ClassNotFoundException, SQLException { 
        
    	if (connection == null || connection.isClosed()) 
    		connection = DataSourceManager.getConnection();
        
    	statement = connection.prepareStatement(UPDATE);
    	statement.setInt(1, client.group.id);
    	statement.setString(2, client.name);
    	statement.setInt(3, client.connections);
    	statement.setString(4, client.ip);
    	statement.setString(5, client.guid);
    	statement.setString(6, client.auth);
    	statement.setLong(7, client.time_add.getTime());
        statement.setLong(8, client.time_edit.getTime());
        statement.setInt(9, client.id);
        statement.executeUpdate();
        statement.close();
        
    }
    
    
    /**
     * Delete domain object from the database.
     * 
     * @author Daniele Pantaleone
     * @param  client The client whose informations needs to be deleted
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public static void delete(Client client) throws ClassNotFoundException, SQLException { 
        
    	if (connection == null || connection.isClosed()) 
    		connection = DataSourceManager.getConnection();
        
    	statement = connection.prepareStatement(DELETE);
    	statement.setInt(1, client.id);
    	statement.executeUpdate();
        statement.close();
        
    }
    
}