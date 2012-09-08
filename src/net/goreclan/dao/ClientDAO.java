/**
 * This class represent a DAO Interface with the "clients" database table.
 *
 * @author      Daniele Pantaleone
 * @version     1.1
 * @copyright   Daniele Pantaleone, 7 September, 2012
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
import net.goreclan.utility.DataSourceManager;

public class ClientDAO {
    
	private static Connection conn;
	private static PreparedStatement stmt;
	private static ResultSet rs;
	
    private static final String LOAD = "SELECT `c`.`name` AS `c_name`, " +
                                              "`c`.`guid` AS `c_guid`, " +
                                              "`c`.`ip` AS `c_ip`, " +
                                              "`c`.`connections` AS `c_connections`, " +
                                              "`c`.`username` AS `c_username`, " +
                                              "`c`.`password` AS `c_password`, " +
                                              "`c`.`time_add` AS `c_time_add`, " +
                                              "`c`.`time_edit` AS `c_time_edit`, " +
                                              "`g`.`id` AS `g_id`, " +
                                              "`g`.`name` AS `g_name`, " +
                                              "`g`.`keyword` AS `g_keyword`, " +
                                              "`g`.`level` AS `g_level` " +
                                              "FROM `clients` AS `c` INNER JOIN `groups` AS `g` ON `c`.`group_id` = `g`.`id` WHERE `c`.`id` = ?";
    
    public static final String INSERT = "INSERT INTO `clients` (`group_id`, `name`, `guid`, `ip`, `time_add`) VALUES (?,?,?,?,?)";
    
    public static final String UPDATE = "UPDATE `clients` SET `group_id` = ?, `name` = ?, `guid` = ?, `ip` = ?, `connections` = ?, `username` = ?, `password` = ?, `time_add` = ?, `time_edit`= ? WHERE `id` = ?";
    
    public static final String DELETE = "DELETE FROM `clients` WHERE `id` = ?";
    
    
    /**
     * Load values from the database and fill object attributes.
     * 
     * @author Daniele Pantaleone
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public static void load(Client client) throws ClassNotFoundException, SQLException {
        
    	if (conn == null || conn.isClosed()) 
    		conn = DataSourceManager.getConnection();
        
    	stmt = conn.prepareStatement(LOAD);
        stmt.setInt(1, client.id);
        rs = stmt.executeQuery();
        
        if (rs.next()) {
        
            // Creating the Group object
            Group group = new Group();
            group.id = rs.getInt("g_id");
            group.name = rs.getString("g_name");
            group.keyword = rs.getString("g_keyword");
            group.level = rs.getInt("g_level");
            
            // Filling object attributes
            client.group = group;
            client.name = rs.getString("c_name");
            client.guid = rs.getString("c_guid");
            client.ip = rs.getString("c_ip");
            client.connections = rs.getInt("c_connections");
            client.username = rs.getString("c_username");
            client.password = rs.getString("c_password");
            client.time_add = new Date(rs.getLong("c_time_add"));
            client.time_edit = new Date(rs.getLong("c_time_edit"));
            
        }
        
        // Closing current ResultSet and Prepared Statement
        if (rs != null) rs.close();
        if (stmt != null) stmt.close();
            
    }
    
    
    /**
     * Create a new entry in the database for the current object.
     * 
     * @author Daniele Pantaleone
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public static void insert(Client client) throws ClassNotFoundException, SQLException { 
        
    	if (conn == null || conn.isClosed()) 
    		conn = DataSourceManager.getConnection();
    	
    	stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
    	stmt.setInt(1, client.group.id);
        stmt.setString(2, client.name);
        stmt.setString(3, client.guid);
        stmt.setString(4, client.ip);
        stmt.setLong(5, client.time_add.getTime());
        stmt.executeUpdate();
    	
        // Getting the auto-incremented client id
    	rs = stmt.getGeneratedKeys();
    	client.id = rs.getInt(1);
        
    	// Closing current ResultSet and Prepared Statement
        if (rs != null) rs.close();
        if (stmt != null) stmt.close();
        
    }
    
    
    /**
     * Update domain object in the database.
     * 
     * @author Daniele Pantaleone
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public static void update(Client client) throws ClassNotFoundException, SQLException { 
        
    	if (conn == null || conn.isClosed()) 
    		conn = DataSourceManager.getConnection();
        
    	stmt = conn.prepareStatement(UPDATE);
        stmt.setInt(1, client.group.id);
        stmt.setString(2, client.name);
        stmt.setString(3, client.guid);
        stmt.setString(4, client.ip);
        stmt.setInt(5, client.connections);
        stmt.setString(6, client.username);
        stmt.setString(7, client.password);
        stmt.setLong(8, client.time_add.getTime());
        stmt.setLong(9, client.time_edit.getTime());
        stmt.setInt(10, client.id);
        stmt.executeUpdate();
        
        // Closing current Prepared Statement
        if (stmt != null) stmt.close();
        
    }
    
    
    /**
     * Delete domain object from the database.
     * 
     * @author Daniele Pantaleone
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public static void delete(Client client) throws ClassNotFoundException, SQLException { 
        
    	if (conn == null || conn.isClosed()) 
    		conn = DataSourceManager.getConnection();
        
        stmt = conn.prepareStatement(DELETE);
        stmt.setInt(1, client.id);
        stmt.executeUpdate();
        
        // Closing current Prepared Statement
        if (stmt != null) stmt.close();
        
    }
    
}