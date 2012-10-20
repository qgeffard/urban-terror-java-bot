/**
 * Command class handler.
 * 
 * @author      Daniele Pantaleone
 * @version     1.0
 * @copyright   Daniele Pantaleone, 08 October, 2012
 * @package     net.goreclan.utility
 **/

package net.goreclan.utility;

import java.lang.reflect.Method;

import net.goreclan.domain.Group;

public class Command {
	
	private final String name;
	private final String alias;
	private final Group minGroup;
	private final Group maxGroup;
	private final Method method;
	
	/**
	 * Object constructor.
	 * 
	 * @author Daniele Pantaleone
	 * @param  name The command name
	 * @param  alias The command alias
	 * @param  minGroup The minimum group level who has access to this command
	 * @param  maxGroup The maximum group level who has access to this command
	 * @param  method The method to be mapped with this command
	 * @return Command
	 **/
	public Command(String name, String alias, Group minGroup, Group maxGroup, Method method) {
		this.name = name;
		this.alias = alias;
		this.minGroup = minGroup;
		this.maxGroup = maxGroup;
		this.method = method;
	}
	
	
	/**
	 * Return the command name.
	 * 
	 * @author Daniele Pantaleone
	 * @return String
	 **/
	public String getName() {
		return this.name;
	}
	
	
	/**
	 * Return the command alias.
	 * 
	 * @author Daniele Pantaleone
	 * @return String
	 **/
	public String getAlias() {
		return this.alias;
	}
	
	
	/**
	 * Return the minimum group authorized to use the command.
	 * 
	 * @author Daniele Pantaleone
	 * @return Group
	 **/
	public Group getMinGroup() {
		return this.minGroup;
	}
	

	/**
	 * Return the maximum group authorized to use the command.
	 * 
	 * @author Daniele Pantaleone
	 * @return Group
	 **/
	public Group getMaxGroup() {
		return this.maxGroup;
	}
	
	
	/**
	 * Return the method where on which the command has been mapped over.
	 * 
	 * @author Daniele Pantaleone
	 * @return Method
	 **/
	public Method getMethod() {
		return this.method;
	}
	
	
	/**
	 * String object representation.
	 * 
	 * @author Daniele Pantaleone
	 * @return String
	 **/
	public String toString() {
		
		// Returning a string object representation.
		return "[ name: " + this.name + " - alias: " + this.alias + " - minLevel: " + this.minGroup.level + " - maxLevel: " + this.maxGroup.level + " - method: " + this.method.getName() + " ]";   
	}
	
	
	//////////////////////////////////////////////
	// BEGIN STATIC METHODS
	//////////////////////////////////////////////
	
	
	/**
	 * Return a formatted command method name.
	 * 
	 * @author Daniele Pantaleone
	 * @param  name The command name
	 * @return String
	 **/
	public static String getCommandMethod(String name) {
		// This will act like a ".title()" method missing in the Java API.
		return "Cmd" + Character.toUpperCase(name.charAt(0)) + name.substring(1);
	}

}
