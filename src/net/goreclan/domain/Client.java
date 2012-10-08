/**
 * This class represent a Value Object matching a "clients" database table entry.
 *
 * @author      Daniele Pantaleone
 * @version     1.3
 * @copyright   Daniele Pantaleone, 05 October, 2012
 * @package     net.goreclan.domain
 **/

package net.goreclan.domain;

import java.sql.SQLException;
import java.util.Date;

import net.goreclan.dao.ClientDAO;
import net.goreclan.exception.RecordNotFoundException;
import net.goreclan.iourt42.Team;

public class Client {
    
    public int id;
    public Group group;
    public String name;
    public int connections;
    public String ip;
    public String guid;
    public String auth;
    public Date time_add;
    public Date time_edit;
    
    public int slot = -1;
    public String gear;
    public Team team;
   
    
    /**
     * Load object attributes using the DAO interface.
     * 
     * @author Daniele Pantaleone
     * @throws ClassNotFoundException 
     * @throws SQLException 
     * @throws RecordNotFoundException 
     **/
    public void load() throws ClassNotFoundException, SQLException, RecordNotFoundException {
        ClientDAO.load(this);
    }
    
    
    /**
     * Create a new entry in the database for the current object using the DAO interface.
     * 
     * @author Daniele Pantaleone
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public void insert() throws ClassNotFoundException, SQLException {
        ClientDAO.insert(this); 
    }
    
    
    /**
     * Update domain object in the database using the DAO interface.
     * 
     * @author Daniele Pantaleone
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public void update() throws ClassNotFoundException, SQLException {
        ClientDAO.update(this); 
    }
    
    
    /**
     * Delete domain object from the database using the DAO interface.
     * 
     * @author Daniele Pantaleone
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public void delete() throws ClassNotFoundException, SQLException {
        ClientDAO.delete(this); 
    }
    
    
    /**
     * String object representation
     * 
     * @return String
     * @author Daniele Pantaleone
     **/
    public String toString() {
        
    	// Returning a string object representation.
    	return "[ id : " + this.id + " | name : " + this.name + " | level : " + this.group.level + " | ip : " + this.ip + " | guid : " + this.guid + " | auth : " + this.auth + " | connections: " + this.connections + " | time_add: " + this.time_add.getTime() + " | time_edit: " + this.time_edit.getTime() + " ]";
 
    }
    
}