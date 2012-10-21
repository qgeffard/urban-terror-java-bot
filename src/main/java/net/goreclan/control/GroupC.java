/**
 * Group domain object controller.
 *
 * @author      Daniele Pantaleone
 * @version     1.1
 * @copyright   Daniele Pantaleone, 20 October, 2012
 * @package     net.goreclan.control
 **/

package net.goreclan.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;

import net.goreclan.domain.Group;
import net.goreclan.utility.DataSourceManager;

public class GroupC {
    
	public static Log log;
	
	private static Connection connection;
	private static PreparedStatement statement;			
	private static ResultSet resultset;
	
	private static List<Group> groups;
	
	private static final String LOAD_ALL = "SELECT * FROM `groups` ORDER BY `id` ASC";
	
	
	/**
	 * Return the Group object matching the specified Group id.
	 * 
	 * @author Daniele Pantaleone
	 * @param  id The Group id
	 * @return Group
	 **/
	public static Group getById(int id) {
		
		if (groups == null || groups.size() == 0) 
			buildList();
		
		Group group = null;
		Iterator<Group> i = groups.iterator();
		
		while (i.hasNext()) { 
			group = i.next();
			if (group.id == id) 
				return group;
		}
		
		return null;
		
	}
	
	
	/**
	 * Return the Group object matching the specified Group keyword.
	 * 
	 * @author Daniele Pantaleone
	 * @param  keyword The Group keyword
	 * @return Group
	 **/
	public static Group getByKeyword(String keyword) {
		
		if (groups == null || groups.size() == 0) 
			buildList();
		
		Group group = null;
		Iterator<Group> i = groups.iterator();
		
		while (i.hasNext()) { 
			group = i.next();
			if (group.keyword.equals(keyword)) 
				return group;
		}
		
		return null;
		
	}
	
	
	/**
	 * Return the Group object matching the specified Group level.
	 * 
	 * @author Daniele Pantaleone
	 * @param  level The Group level
	 * @return Group
	 **/
	public static Group getByLevel(int level) {
		
		if (groups == null || groups.size() == 0) 
			buildList();
		
		Group group = null;
		Iterator<Group> i = groups.iterator();
		
		while (i.hasNext()) { 
			group = i.next();
			if (group.level == level) 
				return group;
		}
		
		return null;
		
	}
	
	
	/**
	 * Return the Group object matching the specified Group name.
	 * 
	 * @author Daniele Pantaleone
	 * @param  name The Group name
	 * @return Group
	 **/
	public static Group getByName(String name) {
		
		if (groups == null || groups.size() == 0) 
			buildList();
		
		Group group = null;
		Iterator<Group> i = groups.iterator();
		
		while (i.hasNext()) { 
			group = i.next();
			if (group.name.equals(name)) 
				return group;
		}
		
		return null;
		
	}
	
	
	//////////////////////////////////////////////
	// BEGIN AUXILIARY METHODS
	//////////////////////////////////////////////
	
	
	/**
	 * Build the list of available Groups.
	 * 
	 * @author Daniele Pantaleone
	 **/
	private static void buildList() {
		
		try {
			
			if (connection == null || connection.isClosed()) 
				connection = DataSourceManager.getConnection();
			
			statement = connection.prepareStatement(LOAD_ALL);
	    	resultset = statement.executeQuery();
	    	
	    	groups = getCollectionFromResultSet(resultset);
	    	resultset.close();
	        statement.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			log.fatal(e.toString());
			System.exit(1);
		}
		
	}
	
	
	/**
	 * Return a collection of Group objects accessible through the List<Group> interface.
	 * 
	 * @author Daniele Pantaleone
	 * @param  resultset The ResultSet object from which to fetch data
	 * @return List<Group>
	 * @throws SQLException 
	 **/
	private static List<Group> getCollectionFromResultSet(ResultSet resultset) throws SQLException {
		
		List<Group> collection = new LinkedList<Group>();
		
		while (resultset.next()) { 
			// Appending all the objects to the collection.
			Group group = getObjectFromCursor(resultset);
			collection.add(group);
			log.trace("Loaded client group: " + group.toString());
		}		
		
		return Collections.unmodifiableList(collection);

	}
	
	
	/**
	 * Return a Group object matching the input ResultSet tuple.
	 * 
	 * @author Daniele Pantaleone
	 * @param  resultset The ResultSet object from which to instantiate the collection.
	 * @throws SQLException 
	 * @return Group
	 **/
	private static Group getObjectFromCursor(ResultSet resultset) throws SQLException {
		Group group = new Group();
		group.id = resultset.getInt("id");
		group.name = resultset.getString("name");
        group.keyword = resultset.getString("keyword");
        group.level = resultset.getInt("level");
		return group;
	}
	
}