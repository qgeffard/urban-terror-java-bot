/**
 * This class represent a Value Object matching a "penalties" database table entry.
 *
 * @author      Daniele Pantaleone
 * @version     1.0
 * @copyright   Daniele Pantaleone, 05 October, 2012
 * @package     net.goreclan.domain
 **/

package net.goreclan.domain;

import java.sql.SQLException;
import java.util.Date;

import net.goreclan.dao.PenaltyDAO;
import net.goreclan.exception.RecordNotFoundException;

public class Penalty {
    
    public int id;
    public int client_id;
    public int admin_id;
    public String type;
    public boolean active;
    public String reason;
    public Date time_add;
	public Date time_edit;
	public Date time_expire;
  
    
    /**
     * Load object attributes using the DAO interface.
     * 
     * @author Daniele Pantaleone
     * @throws ClassNotFoundException 
     * @throws SQLException 
     * @throws RecordNotFoundException 
     **/
    public void load() throws ClassNotFoundException, SQLException, RecordNotFoundException {
    	PenaltyDAO.load(this);
    }
    
    
    /**
     * Create a new entry in the database for the current object using the DAO interface.
     * 
     * @author Daniele Pantaleone
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public void insert() throws ClassNotFoundException, SQLException {
    	PenaltyDAO.insert(this); 
    }
    
    
    /**
     * Update domain object in the database using the DAO interface.
     * 
     * @author Daniele Pantaleone
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public void update() throws ClassNotFoundException, SQLException {
    	PenaltyDAO.update(this); 
    }
    
    
    /**
     * Delete domain object from the database using the DAO interface.
     * 
     * @author Daniele Pantaleone
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public void delete() throws ClassNotFoundException, SQLException {
    	PenaltyDAO.delete(this); 
    }
    
    
    /**
     * String object representation
     * 
     * @return String
     * @author Daniele Pantaleone
     **/
    public String toString() {
        
    	// Returning a string object representation.
    	return "[ id : " + id + " | client_id : " + client_id + " | admin_id : " + admin_id + " | type : " + type + " | active : " + active + " | reson: " + reason + " ]";
 
    }
    
}