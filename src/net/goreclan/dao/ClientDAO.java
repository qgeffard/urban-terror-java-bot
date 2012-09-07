/**
 * This class represent a DAO Interface with the "clients" database table.
 *
 * @author      Daniele Pantaleone
 * @version     1.1
 * @copyright   Daniele Pantaleone, 26 June, 2012
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
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
                
        // Getting the DBMS connection and executing the SQL statement
        conn = DataSourceManager.getConnection();
        pstmt = conn.prepareStatement(LOAD);
        pstmt.setInt(1, client.getId());
        rs = pstmt.executeQuery();
        
        if (rs.next()) {
        
            // Creating the Group object
            Group group = new Group();
            group.setId(rs.getInt("g_id"));
            group.setName(rs.getString("g_name"));
            group.setKeyword(rs.getString("g_keyword"));
            group.setLevel(rs.getInt("g_level"));
            
            // Filling object attributes
            client.setGroup(group);
            client.setName(rs.getString("c_name"));
            client.setGuid(rs.getString("c_guid"));
            client.setIp(rs.getString("c_ip"));
            client.setConnections(rs.getInt("c_connections"));
            if (rs.getString("c_username") != null) { client.setUsername(rs.getString("c_username")); }
            if (rs.getString("c_password") != null) { client.setPassword(rs.getString("c_password")); }
            client.setTimeAdd(new Date(rs.getLong("c_time_add")));
            if (rs.getString("c_time_edit") != null) { client.setTimeEdit(new Date(rs.getLong("c_time_edit"))); }
            
        }
        
        // Closing current ResultSet and Prepared Statement
        if (rs != null) rs.close();
        if (pstmt != null) pstmt.close();
            
    }
    
    
    /**
     * Create a new entry in the database for the current object.
     * 
     * @author Daniele Pantaleone
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public static void insert(Client client) throws ClassNotFoundException, SQLException { 
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet generatedKeys = null;
        
        // Getting the DBMS connection and executing the SQL statement
        conn = DataSourceManager.getConnection();
        pstmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, client.getGroup().getId());
        pstmt.setString(2, client.getName());
        pstmt.setString(3, client.getGuid());
        pstmt.setString(4, client.getIp());
        pstmt.setLong(5, client.getTimeAdd().getTime());
        pstmt.executeUpdate();
        generatedKeys = pstmt.getGeneratedKeys();
        client.setId(generatedKeys.getInt(1));
        
        // Closing current ResultSet and Prepared Statement
        if (generatedKeys != null) generatedKeys.close();
        if (pstmt != null) pstmt.close();
        
    }
    
    
    /**
     * Update domain object in the database.
     * 
     * @author Daniele Pantaleone
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public static void update(Client client) throws ClassNotFoundException, SQLException { 
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        // Getting the DBMS connection and executing the SQL statement
        conn = DataSourceManager.getConnection();
        pstmt = conn.prepareStatement(UPDATE);
        pstmt.setInt(1, client.getGroup().getId());
        pstmt.setString(2, client.getName());
        pstmt.setString(3, client.getGuid());
        pstmt.setString(4, client.getIp());
        pstmt.setInt(5, client.getConnections());
        pstmt.setString(6, client.getUsername());
        pstmt.setString(7, client.getPassword());
        pstmt.setLong(8, client.getTimeAdd().getTime());
        pstmt.setLong(9, client.getTimeEdit().getTime());
        pstmt.setInt(10, client.getId());
        pstmt.executeUpdate();
        
        // Closing the Prepared Statement
        if (pstmt != null) pstmt.close();
        
    }
    
    
    /**
     * Delete domain object from the database.
     * 
     * @author Daniele Pantaleone
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public static void delete(Client client) throws ClassNotFoundException, SQLException { 
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        // Getting the DBMS connection and executing the SQL statement
        conn = DataSourceManager.getConnection();
        pstmt = conn.prepareStatement(DELETE);
        pstmt.setInt(1, client.getId());
        pstmt.executeUpdate();
        
        // Closing the Prepared Statement
        if (pstmt != null) pstmt.close();
        
    }
    
}