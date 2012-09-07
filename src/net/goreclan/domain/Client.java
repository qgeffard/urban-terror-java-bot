/**
 * This class represent a Value Object matching a "clients" database table entry.
 *
 * @author      Daniele Pantaleone
 * @version     1.2
 * @copyright   Daniele Pantaleone, 26 June, 2012
 * @package     net.goreclan.domain.impl
 **/

package net.goreclan.domain;

import java.sql.SQLException;
import java.util.Date;

import net.goreclan.dao.ClientDAO;
import net.goreclan.iourt42.Team;

public class Client {
    
    public Integer id;
    public Group group;
    public String name;
    public String guid;
    public String ip;
    public Integer connections;
    public String username;
    public String password;
    public Date time_add;
    public Date time_edit;
    
    public Integer slot;
    public String gear;
    public Team team;
   
    
    /**
     * Load object attributes using the DAO object interface
     * 
     * @author Daniele Pantaleone
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public void load() throws ClassNotFoundException, SQLException {
        ClientDAO.load(this);
    }
    
    
    /**
     * Create a new entry in the database for the current object using the DAO object interface
     * 
     * @author Daniele Pantaleone
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public void insert() throws ClassNotFoundException, SQLException {
        ClientDAO.insert(this); 
    }
    
    
    /**
     * Update domain object in the database using the DAO object interface
     * 
     * @author Daniele Pantaleone
     * @throws ClassNotFoundException 
     * @throws SQLException 
     **/
    public void update() throws ClassNotFoundException, SQLException {
        ClientDAO.update(this); 
    }
    
    
    /**
     * Delete domain object from the database using the DAO object interface
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
        
    	// Returning a String object representation
        return String.format("[ id : %d | name : %s | level : %d | guid : %s | ip : %s | connections : %d ]", id, name, group.level, guid, ip, connections);
        
    }
    
}