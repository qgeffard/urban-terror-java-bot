/**
 * This class represents a Value Object matching an "aliases" database table entry.
 * 
 * @author      Mathias Van Malderen
 * @version     1.0
 * @copyright   Mathias Van Malderen, 27 June, 2012
 * @package     net.goreclan.domain
 **/

package net.goreclan.domain;

import java.sql.SQLException;
import java.util.Date;

import net.goreclan.dao.AliasDAO;

public class Alias {
    
	public Integer id;
	public Integer client_id;
	public String name;
	public Integer num_used;
	public Date time_add;
	public Date time_edit;
    
  
    /**
     * Load object attributes using the DAO interface.
     * 
     * @return void
     * @author Mathias Van Malderen
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public void load() throws ClassNotFoundException, SQLException {
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
    	
        // Returning a String object representation
        return String.format("[ id : %d | client_id : %d | name : %d | num_used : %d ]", id, client_id, name, num_used);
        
    }
    
}