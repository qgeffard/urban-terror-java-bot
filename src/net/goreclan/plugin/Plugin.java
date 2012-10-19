/**
 * TODO: Add JavaDoc
 * 
 * @author      Daniele Pantaleone
 * @version     1.0
 * @copyright   Daniele Pantaleone, 09 October, 2012
 * @package     net.goreclan.plugins
 **/

package net.goreclan.plugin;

import net.goreclan.console.Console;
import net.goreclan.domain.Client;
import net.goreclan.domain.Group;
import net.goreclan.exception.CommandRegisterException;
import net.goreclan.utility.Command;
import net.goreclan.utility.CommandList;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.apache.commons.logging.Log;

public abstract class Plugin {
	
	protected final Console console;
	protected final Log log;
	protected final HierarchicalINIConfiguration config;
	
	protected CommandList commands;
	
	protected boolean enabled = true;
	
	
	/**
	 * Object constructor.
	 * 
	 * @author Daniele Pantaleone
	 * @param  console A reference to the main BOT console
	 * @param  log A reference to the main BOT logger
	 * @param  conf The plugin configuration file name
	 * @return Plugin
	 * @throws ConfigurationException 
	 **/
	public Plugin(Console console, Log log, String conf) throws ConfigurationException {
		this.console = console;									// Copying console reference.
		this.log = log;											// Copying main log reference.
		this.config = new HierarchicalINIConfiguration(conf);	// Creating a new parser for the plugin configuration file.
		this.commands = new CommandList();						// Creating a new CommandList object for future command registering.
	}	
	
	
	/**
	 * Load the plugin configuration file.
	 * Use this function to load plugin specific attributes configuration.
	 *  
	 * @author Daniele Pantaleone
	 **/
	public abstract void onLoadConfig();
	
	
	/**
	 * Start the plugin.
	 * This method is called right after the onLoadConfig() one.
	 * Use this function to register runtime commands and perform
	 * operations which needs to be executed at plugin startup.
	 *  
	 * @author Daniele Pantaleone
	 **/
	public abstract void onStartup();
	
	
	/**
	 * Enable the plugin.
	 * 
	 * @author Daniele Pantaleone
	 */
	public void enable() {
		this.enabled = true;
	}
	
	
	/**
	 * Disable the plugin.
	 * 
	 * @author Daniele Pantaleone
	 */
	public void disable() {
		this.enabled = false;
	}
	
	
	/**
	 * Tells whether the plugin is enabled.
	 * 
	 * @author Daniele Pantaleone
	 * @return boolean
	 */
	public boolean isEnabled() {
		return this.enabled;
	}
	
	
	/**
	 * Return the Command associated to the given name/alias.
	 * 
	 * @author Daniele Pantaleone
	 * @param  name The command name
	 * @return Command
	 */
	public Command getCommand(String name) {
		return this.commands.get(name);
	}
	
	
	/**
	 * Return a list of available commands.
	 * 
	 * @author Daniele Pantaleone
	 * @return Map<String, Command>
	 */
	public CommandList getCommandList() {
		return this.commands;
	}
	
	
	/**
	 * Tells whether the Plugin registered the given command name/alias.
	 * 
	 * @author Daniele Pantaleone
	 * @param  name The command name
	 * @return boolean
	 **/
	public boolean hasRegisteredCommand(String name) {
		return this.commands.containsKey(name);
	}
	
	
	/**
	 * Register a command.
	 * 
	 * @author Daniele Pantaleone
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 **/	
	protected void registerCommand(String cmdName, String cmdAlias, Group minGroup, Group maxGroup) throws NoSuchMethodException, SecurityException {
		
		try {
			
			// We are going to register command using only lower case characters.
			// If we let plugin coders registering commands with mixed upper/lower case characters
			// we may have some problems while checking a command name/alias existing map.
			// Instead of bothering us with futile errors, we are going to convert the given
			// command names into lower case strings.
			cmdName = cmdName.toLowerCase();
			cmdAlias = cmdName.toLowerCase();
			
			// We need to match the given command name with a specific plugin method.
			// Given the command name "blub", a map will be created against a Method
			// named "CmdBlub" which accept as input parameters the "client" object (which refers
			// to the client who issued the command) and an optional "data" string (may be null) which
			// contains Method specific extra data, needed to perform certain operations.
			String cmdMethod = "Cmd" + Character.toUpperCase(cmdName.charAt(0)) + cmdName.substring(1);
			Command cmd = new Command(cmdName, cmdAlias, minGroup, maxGroup, this.getClass().getMethod(cmdMethod, Client.class, String.class));
			this.log.debug("Registering command: " + cmd.toString() + ".");
			
			// Adding the command to plugin command list.
			this.commands.put(cmdName, cmdAlias, cmd);
			
		} catch (CommandRegisterException e) {
			// Logging the exception.
			this.log.error(e.getMessage());
		}
		
	}
	
}
