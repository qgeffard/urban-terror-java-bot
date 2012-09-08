/**
 * This class represents a DAO interface with the Aliases database table.
 * 
 * @author Mathias Van Malderen, Daniele Pantaleone
 * @version 1.1
 * @copyright Mathias Van Malderen, Daniele Pantaleone, 30 June, 2012
 * @package net.goreclan.dao
 **/

package net.goreclan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import net.goreclan.domain.Alias;
import net.goreclan.utility.DataSourceManager;

public class AliasDAO {
	
	private static Connection conn;
	private static PreparedStatement stmt;
	private static ResultSet rs;
	
    private static final String LOAD = "SELECT `a`.`client_id` AS `a_client_id`," +
                                              "`a`.`name` AS `a_name`," +
                                              "`a`.`num_used` AS `a_num_used`," +
                                              "`a`.`time_add` AS `a_time_add`," +
                                              "`a`.`time_edit` AS `a_time_edit`" +
                                              "FROM `aliases` AS `a` WHERE `a`.`id` = ?";
    
    private static final String INSERT = "INSERT INTO `aliases` (`client_id`, `name`, `num_used`, `time_add`) VALUES (?,?,?,?)";
    
    private static final String UPDATE = "UPDATE `aliases` SET `client_id` = ?, `name` = ?, `num_used` = ?, `time_edit` = ? WHERE `id` = ?";
    
    private static final String DELETE = "DELETE FROM `aliases` WHERE `id` = ?";
    
    
    /**
     * Load values from the database and fill object attributes.
     * 
     * @author Mathias Van Malderen, Daniele Pantaleone
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public static void load(Alias alias) throws ClassNotFoundException, SQLException {
        
    	if (conn == null || conn.isClosed()) 
    		conn = DataSourceManager.getConnection();
        
    	stmt = conn.prepareStatement(LOAD);
        stmt.setInt(1, alias.id);
        rs = stmt.executeQuery();
    	
        if (rs.next()) {
        
        	// Filling object attributes
        	alias.client_id = rs.getInt("a_client_id");
        	alias.name = rs.getString("a_name");
        	alias.num_used = rs.getInt("a_num_used");
        	alias.time_add = new Date(rs.getLong("a_time_add"));
        	alias.time_edit = new Date(rs.getLong("a_time_edit"));
        
        }
        
        // Closing current ResultSet and Prepared Statement
        if (rs != null) rs.close();
        if (stmt != null) stmt.close();
            
    }
    
    
    /**
     * Create a new entry in the database for the current object.
     * 
     * @author Mathias Van Malderen, Daniele Pantaleone
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public static void insert(Alias alias) throws ClassNotFoundException, SQLException {
        
    	if (conn == null || conn.isClosed()) 
    		conn = DataSourceManager.getConnection();
    	
    	stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
    	stmt.setInt(1, alias.client_id);
    	stmt.setString(2, alias.name);
    	stmt.setInt(3, alias.num_used);
    	stmt.setLong(4, alias.time_add.getTime());
        stmt.executeUpdate();
    	
        // Getting the auto-incremented client id
    	rs = stmt.getGeneratedKeys();
    	alias.id = rs.getInt(1);
        
    	// Closing current ResultSet and Prepared Statement
        if (rs != null) rs.close();
        if (stmt != null) stmt.close();
            
    }
    
    
    /**
     * Update domain object in the database.
     * 
     * @author Mathias Van Malderen, Daniele Pantaleone
     * @throws ClassNotFoundException
     * @throws SQLException 
     **/
    public static void update(Alias alias) throws ClassNotFoundException, SQLException {
        
    	if (conn == null || conn.isClosed()) 
    		conn = DataSourceManager.getConnection();
    
    	stmt = conn.prepareStatement(UPDATE);
    	stmt.setInt(1, alias.client_id);
    	stmt.setString(2, alias.name);
    	stmt.setInt(3, alias.num_used);
    	stmt.setLong(4, alias.time_edit.getTime());
    	stmt.setInt(5, alias.id);
    	stmt.executeUpdate();
    	
    	// Closing current Prepared Statement
        if (stmt != null) stmt.close();
        
    }
    
    
    /**
     * Delete domain object from the database.
     * 
     * @author Mathias Van Malderen, Daniele Pantaleone
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public static void delete(Alias alias) throws ClassNotFoundException, SQLException {
        
    	if (conn == null || conn.isClosed()) 
    		conn = DataSourceManager.getConnection();
        
        stmt = conn.prepareStatement(DELETE);
        stmt.setInt(1, alias.id);
        stmt.executeUpdate();
        
        // Closing current Prepared Statement
        if (stmt != null) stmt.close();
        
    }
    
}