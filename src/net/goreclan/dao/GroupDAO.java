/**
 * This class represent a DAO Interface with the "groups" database table.
 *
 * @author      Daniele Pantaleone
 * @version     1.0
 * @copyright   Daniele Pantaleone, 28 June, 2012
 * @package     net.goreclan.dao
 **/

package net.goreclan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.goreclan.domain.Group;
import net.goreclan.utility.DataSourceManager;

public class GroupDAO {
    
	private static Connection conn;
	private static PreparedStatement stmt;
	private static ResultSet rs;
	
    private static final String LOAD = "SELECT `name`, `keyword`, `level` FROM `groups` WHERE `id` = ?";
    
    public static final String INSERT = "INSERT INTO `groups` (`id`, `name`, `keyword`, `level`) VALUES (?,?,?,?)";
    
    public static final String UPDATE = "UPDATE `groups` SET `name` = ?, `keyword` = ?, `level` = ? WHERE `id` = ?";
    
    public static final String DELETE = "DELETE FROM `groups` WHERE `id` = ?";
    
    
    /**
     * Load values from the database and fill object attributes
     * 
     * @author Daniele Pantaleone
     * @throws ClassNotFoundException
     * @throws SQLException 
     **/
    public static void load(Group group) throws ClassNotFoundException, SQLException {
        
    	if (conn == null || conn.isClosed()) 
    		conn = DataSourceManager.getConnection();
        
    	stmt = conn.prepareStatement(LOAD);
        stmt.setInt(1, group.id);
        rs = stmt.executeQuery();
        
        if (rs.next()) {
        	
        	// Filling object attributes
            group.name = rs.getString("name");
            group.keyword = rs.getString("keyword");
            group.level = rs.getInt("level");
            
        }

        // Closing current ResultSet and Prepared Statement
        if (rs != null) rs.close();
        if (stmt != null) stmt.close();
    }
    
    
    /**
     * Create a new entry in the database for the current object
     * 
     * @author Daniele Pantaleone
     * @throws ClassNotFoundException
     * @throws SQLException 
     **/
    public static void insert(Group group) throws ClassNotFoundException, SQLException { 
        
    	if (conn == null || conn.isClosed()) 
    		conn = DataSourceManager.getConnection();
    	
    	stmt = conn.prepareStatement(INSERT);
        stmt.setInt(1, group.id);
        stmt.setString(2, group.name);
        stmt.setString(3, group.keyword);
        stmt.setInt(4, group.level);
        stmt.executeUpdate();
        
        // Closing current Prepared Statement
        if (stmt != null) stmt.close();      
        
    }
    
    
    /**
     * Update domain object in the database
     * 
     * @author Daniele Pantaleone
     * @throws ClassNotFoundException
     * @throws SQLException 
     **/
    public static void update(Group group) throws ClassNotFoundException, SQLException { 
        
    	if (conn == null || conn.isClosed()) 
    		conn = DataSourceManager.getConnection();
        
    	stmt = conn.prepareStatement(UPDATE);
    	stmt.setString(1, group.name);
    	stmt.setString(2, group.keyword);
    	stmt.setInt(3, group.level);
    	stmt.setInt(4, group.id);
    	stmt.executeUpdate();
        
    	// Closing current Prepared Statement
        if (stmt != null) stmt.close(); 
            
    }
    
    
    /**
     * Delete domain object from the database
     * 
     * @author Daniele Pantaleone
     * @throws ClassNotFoundException
     * @throws SQLException 
     **/
    public static void delete(Group group) throws ClassNotFoundException, SQLException { 
    	
    	if (conn == null || conn.isClosed()) 
    		conn = DataSourceManager.getConnection();

        stmt = conn.prepareStatement(DELETE);
        stmt.setInt(1, group.id);
        stmt.executeUpdate();
        
        // Closing current Prepared Statement
        if (stmt != null) stmt.close(); 
        
    }
    
}