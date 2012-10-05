/**
 * This class represent a DAO Interface with the "penalties" database table.
 *
 * @author      Daniele Pantaleone
 * @version     1.0
 * @copyright   Daniele Pantaleone, 05 October, 2012
 * @package     net.goreclan.dao
 **/

package net.goreclan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Date;

import net.goreclan.domain.Penalty;
import net.goreclan.exception.RecordNotFoundException;
import net.goreclan.utility.DataSourceManager;

public class PenaltyDAO {
    
	private static Connection connection;
	private static PreparedStatement statement;
	private static ResultSet resultset;
	
	private static final String LOAD = "SELECT `client_id`, `admin_id`, `type`, `active`, `reason`, `time_add`, `time_edit`, `time_expire` FROM `penalties` WHERE `id` = ?";
    
    private static final String INSERT = "INSERT INTO `penalties` (`client_id`, `admin_id`, `type`, `active`, `reason`, `time_add`, `time_edit`, `time_expire`) VALUES (?,?,?,?,?,?,?,?)";
    
    private static final String UPDATE = "UPDATE `penalties` SET `client_id` = ?, `admin_id` = ?, `type` = ?, `active` = ?, `reason` = ?, `time_add` = ?, `time_edit` = ?, `time_expire`= ? WHERE `id` = ?";
    
    private static final String DELETE = "DELETE FROM `penalties` WHERE `id` = ?";
    
    
    /**
     * Load values from the database and fill object attributes.
     * 
     * @author Daniele Pantaleone
     * @param  penalty The penalty object where to store data
     * @throws ClassNotFoundException 
     * @throws SQLException 
     * @throws RecordNotFoundException 
     **/
    public static void load(Penalty penalty) throws ClassNotFoundException, SQLException, RecordNotFoundException {
        
    	if (connection == null || connection.isClosed()) 
    		connection = DataSourceManager.getConnection();
        
    	statement = connection.prepareStatement(LOAD);
    	statement.setInt(1, penalty.id);
        resultset = statement.executeQuery();
        
        if (!resultset.next())
        	throw new RecordNotFoundException("Unable to find a match for penalty: " + penalty.id + ".");
        
        // Storing the penalty data
        penalty.client_id = resultset.getInt("client_id");
        penalty.admin_id = resultset.getInt("admin_id");
        penalty.type = resultset.getString("type");
        penalty.active = resultset.getBoolean("active");
        penalty.reason = resultset.getString("reason");
        penalty.time_add = new Date(resultset.getLong("time_add"));
        penalty.time_edit = new Date(resultset.getLong("time_edit"));
        if (resultset.wasNull()) penalty.time_edit = null;
        penalty.time_expire = new Date(resultset.getLong("time_expire"));
        if (resultset.wasNull()) penalty.time_expire = null;
            
        resultset.close();
        statement.close();
            
    }
    
    
    /**
     * Create a new entry in the database for the current object.
     * 
     * @author Daniele Pantaleone
     * @param  penalty The penalty whose informations needs to be stored
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public static void insert(Penalty penalty) throws ClassNotFoundException, SQLException { 
        
    	if (connection == null || connection.isClosed()) 
    		connection = DataSourceManager.getConnection();
    	
    	statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
    	statement.setInt(1, penalty.client_id);
    	statement.setInt(2, penalty.admin_id);
    	statement.setString(3, penalty.type);
    	statement.setBoolean(4, penalty.active);
    	if (penalty.reason != null) statement.setString(5, penalty.reason);
    	else statement.setNull(5, Types.VARCHAR);
    	statement.setLong(6, penalty.time_add.getTime());
    	if (penalty.time_edit != null) statement.setLong(7, penalty.time_edit.getTime());
    	else statement.setNull(7, Types.BIGINT);
    	if (penalty.time_expire != null) statement.setLong(8, penalty.time_expire.getTime());
    	else statement.setNull(8, Types.BIGINT);
    	statement.executeUpdate();

    	// Storing the new generated penalty id
    	resultset = statement.getGeneratedKeys();
    	penalty.id = resultset.getInt(1);
        
        resultset.close();
        statement.close();
        
    }
    
    
    /**
     * Update domain object in the database.
     * 
     * @author Daniele Pantaleone
     * @param  penalty The penalty whose informations needs to be updated
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public static void update(Penalty penalty) throws ClassNotFoundException, SQLException { 
        
    	if (connection == null || connection.isClosed()) 
    		connection = DataSourceManager.getConnection();
        
    	statement = connection.prepareStatement(UPDATE);
    	statement.setInt(1, penalty.client_id);
    	statement.setInt(2, penalty.admin_id);
    	statement.setString(3, penalty.type);
    	statement.setBoolean(4, penalty.active);
    	if (penalty.reason != null) statement.setString(5, penalty.reason);
    	else statement.setNull(5, Types.VARCHAR);
    	statement.setLong(6, penalty.time_add.getTime());
    	if (penalty.time_edit != null) statement.setLong(7, penalty.time_edit.getTime());
    	else statement.setNull(7, Types.BIGINT);
    	if (penalty.time_expire != null) statement.setLong(8, penalty.time_expire.getTime());
    	else statement.setNull(8, Types.BIGINT);
    	statement.executeUpdate();
        statement.setInt(9, penalty.id);
        statement.executeUpdate();
        statement.close();
        
    }
    
    
    /**
     * Delete domain object from the database.
     * 
     * @author Daniele Pantaleone
     * @param  penalty The penalty whose informations needs to be deleted
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public static void delete(Penalty penalty) throws ClassNotFoundException, SQLException { 
        
    	if (connection == null || connection.isClosed()) 
    		connection = DataSourceManager.getConnection();
        
    	statement = connection.prepareStatement(DELETE);
    	statement.setInt(1, penalty.id);
    	statement.executeUpdate();
        statement.close();
        
    }
    
}