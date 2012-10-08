/**
 * This class represent a Value Object matching a "callvotes" database table entry.
 *
 * @author      Daniele Pantaleone
 * @version     1.0
 * @copyright   Daniele Pantaleone, 08 October, 2012
 * @package     net.goreclan.domain
 **/

package net.goreclan.domain;

import java.sql.SQLException;
import java.util.Date;

import net.goreclan.dao.CallvoteDAO;
import net.goreclan.exception.RecordNotFoundException;

public class Callvote {
    
    public int id;
    public int client_id;
    public String type;
    public String data;
    public int yes;
    public int no;
    public Date time_add;
  
    
    /**
     * Load object attributes using the DAO interface.
     * 
     * @author Daniele Pantaleone
     * @throws ClassNotFoundException 
     * @throws SQLException 
     * @throws RecordNotFoundException 
     **/
    public void load() throws ClassNotFoundException, SQLException, RecordNotFoundException {
    	CallvoteDAO.load(this);
    }
    
    
    /**
     * Create a new entry in the database for the current object using the DAO interface.
     * 
     * @author Daniele Pantaleone
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public void insert() throws ClassNotFoundException, SQLException {
    	CallvoteDAO.insert(this); 
    }
    
    
    /**
     * Update domain object in the database using the DAO interface.
     * 
     * @author Daniele Pantaleone
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public void update() throws ClassNotFoundException, SQLException {
    	CallvoteDAO.update(this); 
    }
    
    
    /**
     * Delete domain object from the database using the DAO interface.
     * 
     * @author Daniele Pantaleone
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public void delete() throws ClassNotFoundException, SQLException {
    	CallvoteDAO.delete(this); 
    }
    
    
    /**
     * String object representation
     * 
     * @return String
     * @author Daniele Pantaleone
     **/
    public String toString() {
        
    	// Returning a string object representation.
    	return "[ id : " + this.id + " | client_id : " + this.client_id + " | type : " + this.type + " | data : " + this.data + " | yes : " + this.yes + " | no : " + this.no + " | time_add : " + this.time_add.getTime() + " ]";
 
    }
    
}