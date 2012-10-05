/**
 * This class represent a Value Object matching a "groups" database table entry.
 *
 * @author      Daniele Pantaleone
 * @version     1.1
 * @copyright   Daniele Pantaleone, 26 June, 2012
 * @package     net.goreclan.domain
 **/

package net.goreclan.domain;

import java.sql.SQLException;

import net.goreclan.dao.GroupDAO;

public class Group {
    
    public int id;
    public String name;
    public String keyword;
    public int level;
  
    
    /**
     * Load object attributes using the DAO interface.
     * 
     * @author Daniele Pantaleone
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public void load() throws ClassNotFoundException, SQLException {
        GroupDAO.load(this);
    }
    
    
    /**
     * Create a new entry in the database for the current object using the DAO interface.
     * 
     * @author Daniele Pantaleone
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public void insert() throws ClassNotFoundException, SQLException {
        GroupDAO.insert(this); 
    }
    
    
    /**
     * Update domain object in the database using the DAO interface.
     * 
     * @author Daniele Pantaleone
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public void update() throws ClassNotFoundException, SQLException {
        GroupDAO.update(this); 
    }
    
    
    /**
     * Delete domain object from the database using the DAO interface.
     * 
     * @author Daniele Pantaleone
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public void delete() throws ClassNotFoundException, SQLException {
        GroupDAO.delete(this); 
    }
    
    
    /**
     * String object representation
     * 
     * @return String
     * @author Daniele Pantaleone
     **/
    public String toString() {
        
    	// Returning a string object representation.
    	return "[ id : " + id + " | name : " + name + " | keyword : " + keyword + " | level : " + level + " ]";
 
    }
    
}