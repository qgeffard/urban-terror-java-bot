/**
 * This class represents a Value Object matching an "aliases" database table entry.
 * 
 * @author      Mathias Van Malderen, Daniele Pantaleone
 * @version     1.1
 * @copyright   Mathias Van Malderen, 05 October, 2012
 * @package     net.goreclan.domain
 **/

package net.goreclan.domain;

import java.sql.SQLException;
import java.util.Date;

import net.goreclan.dao.AliasDAO;
import net.goreclan.exception.RecordNotFoundException;

public class Alias {
    
	public int id;
	public int client_id;
	public String name;
	public int num_used;
	public Date time_add;
	public Date time_edit;
    
  
    /**
     * Load object attributes using the DAO interface.
     * 
     * @return void
     * @author Mathias Van Malderen
     * @throws ClassNotFoundException 
     * @throws SQLException 
     * @throws RecordNotFoundException 
     **/
    public void load() throws ClassNotFoundException, SQLException, RecordNotFoundException {
        AliasDAO.load(this);
    }
    
    
    /**
     * Create a new entry in the database for the current object using the DAO interface.
     * 
     * @return void
     * @author Mathias Van Malderen
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public void insert() throws ClassNotFoundException, SQLException {
        AliasDAO.insert(this);
    }
    
    
    /**
     * Update domain object in the database using the DAO interface.
     * 
     * @return void
     * @author Mathias Van Malderen
     * @throws ClassNotFoundException 
     * @throws SQLException  
     **/
    public void update() throws ClassNotFoundException, SQLException {
        AliasDAO.update(this);
    }
    
    
    /**
     * Delete domain object from the database using the DAO interface.
     * 
     * @return void
     * @author Mathias Van Malderen
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public void delete() throws ClassNotFoundException, SQLException {
        AliasDAO.delete(this);
    }
    
    
    /**
     * String object representation
     * 
     * @return String
     * @author Daniele Pantaleone
     **/
    public String toString() {
    	
    	// Returning a string object representation.
    	return "[ id : " + id + " | name : " + name + " | num_used : " + num_used + " ]";
        
    }
    
}