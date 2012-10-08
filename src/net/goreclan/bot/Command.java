/**
 * Command class handler.
 * 
 * @author      Daniele Pantaleone
 * @version     1.0
 * @copyright   Daniele Pantaleone, 08 October, 2012
 * @package     net.goreclan.bot
 **/

package net.goreclan.bot;

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
		this.setName(name);
		this.setAlias(alias);
		this.setMinGroup(minGroup);
		this.setMaxGroup(maxGroup);
		this.setMethod(method);
	}
	
	/**
	 * Set the command method.
	 * 
	 * @author Daniele Pantaleone
	 * @param  method The method to map
	 **/
	public void setMethod(Method method) {
		this.method = method;
	}
	
	
	/**
	 * Set the command level.
	 * 
	 * @author Daniele Pantaleone
	 * @param  level  The corresponding method necessary level
	 **/
	public void setLevel(String level) {
		this.level = level;
	}
	
	
	/**
	 * Return the command method.
	 * 
	 * @author Daniele Pantaleone
	 * @return Method
	 **/
	public Method getMethod() {
		return this.method;
	}
	
	
	/**
	 * Return the corresponding method necessary level.
	 * 
	 * @author Daniele Pantaleone
	 * @return String
	 **/
	public String getLevel() {
		return this.level;
	}

}
