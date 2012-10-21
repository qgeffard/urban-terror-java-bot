/**
 * This class is the main container for all the plugin commands.
 * The basic idea is to provide a customized LinkedHashMap where to
 * store a command object and 2 keys (name/alias) associated to that command:
 * 		
 * 		1st - Map<k1,V>	 -> Holds a command name and the Command object which contains the method to be invoked.
 * 		2nd - Map<k2,k1> -> Holds the alias of a command and the original command name.
 * 
 * Differently from a normal LinkedHashMap, is not possible to store multiple keys to map different objects. 
 * Each command should have his name and his alias. If we try to create a map by providing  name/alias which 
 * is already used to map another command a CommandRegisterException will be thrown.
 * 
 * @author      Daniele Pantaleone
 * @version     1.0
 * @copyright   Daniele Pantaleone, 09 October, 2012
 * @package     net.goreclan.utility
 **/

package net.goreclan.utility;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import net.goreclan.exception.CommandRegisterException;

public class CommandList {
	
	private Map<String, Command> map1 = Collections.synchronizedMap(new LinkedHashMap<String, Command>());
	private Map<String, String>  map2 = Collections.synchronizedMap(new LinkedHashMap<String, String>());
	
	
	/**
	 * Clear the command list by removing all the elements.
	 * 
	 * @author Daniele Pantaleone
	 **/
	public void clear() {
		this.map1.clear();
		this.map2.clear();
	}
	
	
	/**
	 * Tells whether the given key is mapped in the command list.
	 * 
	 * @author Daniele Pantaleone
	 * @param  key The key to be checked
	 * @return boolean
	 **/
	public boolean containsKey(String key) {
		if (!this.map1.containsKey(key) && 
			!this.map2.containsKey(key))
			return false;
		
		return true;
	}
	
	
	/**
	 * Tells whether the given keys are mapped in the command list.
	 * This method matches at first the given key2 against map2. 
	 * If there is value mapped for key2, it compares the given key1 with
	 * the mapped one. If also this checks pass, it checks if there
	 * is a value mapped for key1 in map1. 
	 * 
	 * @author Daniele Pantaleone
	 * @param key1 The 1st key to be checked
	 * @param key2 The 2nd key to be checked
	 * @return boolean
	 **/
	public boolean containsKey(String key1, String key2) {
		// We are going to match the 2nd key at first.
		// If we have a match we are going to compare the value
		// mapped in map2 with key1 and check if we have this
		// key with a set value in map1.
		
		// Checking if we got a map for key2.
		if (!this.map2.containsKey(key2))
			return false;
		
		// Matching the give key1 with the mapped one.
		if (!this.map2.get(key2).equals(key1))
			return false;
		
		// Checking if we got a map for key1.
		if (!this.map1.containsKey(key1))
			return false;
		
		return true;

	}

	
	/**
	 * Tells whether the given command is available in the command list.
	 * 
	 * @author Daniele Pantaleone
	 * @param  command The command to be checked
	 * @return boolean
	 **/
	public boolean containsValue(Command command) {
		return this.map1.containsValue(command);
	}
	
	
	/**
	 * Returns the command to which the specified key is mapped.
	 * If the key is not mapped it will return null.
	 * It will return null also if the key is mapped to a null value.
	 * 
	 * @author Daniele Pantaleone
	 * @param  key The key to match
	 * @return command
	 **/
	public Command get(String key) {
		// Trying with the command name at first.
		Command command = this.map1.get(key);
		if (command != null) return command;
		
		// We got no match using the command full name.
		// We'll try to match an alias, to get the correct
		// command full name in order to retrieve our command.
		String mKey = this.map2.get(key);
		if (mKey != null) return this.map1.get(mKey);
		
		// No match.
		return null;
		
	}
	
	
	/**
	 * Add a new element to the CommandList.
	 * 
	 * @author Daniele Pantaleone
	 * @param  key1 The command name
	 * @param  key2 The command alias
	 * @param  command The command to be mapped
	 * @throws CommandRegisterException Launched when trying to add a duplicate command
	 **/
	public void put(String key1, String key2, Command command) throws CommandRegisterException {
		
		if ((this.map1.containsKey(key1)) || (this.map1.containsKey(key2)) || (this.map2.containsKey(key1)) || (this.map2.containsKey(key2)))
			throw new CommandRegisterException("Unable to register command. Duplicate command detected: [ !" + key1 + " - !" + key2 +"].");
		
		// Seems we are registering a new command so far.
		// Adding <key1,command> to map1 and <key2,key1> to map2.
		this.map1.put(key1, command);
		this.map2.put(key2, key1);
		
	}
	
	
	/**
	 * Remove and returns the command to which the specified key is mapped.
	 * If the key is not mapped it will return null.
	 * It will return null also if the key is mapped to a null value.
	 * 
	 * @author Daniele Pantaleone
	 * @param  key The key to match
	 * @return command
	 **/
	public Command remove(String key) {
		// The logic of this method is not much complicated.
		// We need to keep both maps size the same so removing
		// the command from map1 is not sufficient. We need to 
		// scan both maps and remove elements from them in order
		// to keep bilateral alignment.
		
		if (this.map1.containsKey(key)) {
			// We got a match with the command full name.
			// We need to scan map2 and match the key1 value.
			Iterator<Map.Entry<String, String>> i = this.map2.entrySet().iterator();
			while (i.hasNext()) {
				Map.Entry<String, String> entry = i.next();
				if (entry.getValue().equals(key)) {
					this.map2.remove(entry.getKey());
					break;
				}
			}
			
			// Removing and returning the command.
			return this.map1.remove(key);
			
		}
		else if (this.map2.containsKey(key)) {
			// We got a match with the command alias.
			// That's less complicated than the previous one ^^.
			String mKey = this.map2.remove(key);
			return this.map1.remove(mKey);
		}
	
		// No match.
		return null;
		
	}
	
	
	/**
	 * Returns the size of the command list.
	 * 
	 * @author Daniele Pantaleone
	 * @return int
	 **/
	public int size() {
		// Since we keep side alignment while adding and
		// removing object to the command list, we can consider
		// safe just to return the size of a single map (both maps
		// should have the same size anyway)
		return this.map1.size();
	}
	
}
