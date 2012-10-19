/**
 * TODO: Add JavaDoc
 * 
 * @author      Daniele Pantaleone
 * @version     1.0
 * @copyright   Daniele Pantaleone, 09 October, 2012
 * @package     net.goreclan.plugins
 **/

package net.goreclan.plugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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
	
	protected Console console;
	protected Log log;
	protected HierarchicalINIConfiguration config;
	
	protected CommandList commands;
	
	protected boolean enabled = true;
	
	
	/**
	 * Load the plugin configuration file.
	 * 
	 * Use this function to load plugin specific attributes configuration.
	 * If this method can also not be overridden while extending the class:
	 * in such case nothing will happen since we will execute an empty method.
	 *  
	 * @author Daniele Pantaleone
	 **/
	public void onLoadConfig() { }
	
	
	/**
	 * Start the plugin.
	 * 
	 * This method is called right after the onLoadConfig() one.
	 * Use this function to register runtime commands and perform
	 * operations which needs to be executed at plugin startup.
	 * If this method can also not be overridden while extending the class:
	 * in such case nothing will happen since we will execute an empty method.
	 *  
	 * @author Daniele Pantaleone
	 **/
	public void onStartup() { }
	
	
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
	 * Enable/disable the plugin.
	 * 
	 * @author Daniele Pantaleone
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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
	@SuppressWarnings("unused")
	private void registerCommand(String cmdName, String cmdAlias, Group minGroup, Group maxGroup) throws NoSuchMethodException, SecurityException {
		
		try {
			
			// We are going to register command using only lower case characters.
			// If we let plugin coders registering commands with mixed upper/lower case characters
			// we may have some problems while checking a command name/alias exists in the command list.
			// Instead of bothering us with futile errors, we are going to lowercase the given name/alias.
			cmdName = cmdName.toLowerCase();
			cmdAlias = cmdAlias.toLowerCase();
			
			// We need to match the given command name with a specific plugin method. Given the command name "blub", 
			// a map will be created against a Method named "CmdBlub" which accept as input parameters the "client" object 
			// (which refers to the client who issued the command) and an optional "data" string (may be null) which
			// contains Method specific extra data, needed to perform certain operations.
			Command cmd = new Command(cmdName, cmdAlias, minGroup, maxGroup, this.getClass().getMethod(Command.getCommandMethod(cmdName), Client.class, String.class));
	
			// Adding the command to plugin command list.
			this.commands.put(cmdName, cmdAlias, cmd);
			this.log.debug("Registered command: " + cmd.toString() + ".");
			
		} catch (CommandRegisterException e) {
			this.log.error("Unable to register command: " + cmdName + ".", e);
			return;
		}
		
	}
	
	
	//////////////////////////////////////////////
	// BEGIN STATIC METHODS
	//////////////////////////////////////////////
	
	
	/**
	 * Return the Fully Qualified class name of a plugin
	 * according to the given input plugin name.
	 * 
	 * @author Daniele Pantaleone
	 * @return String
	 **/
	public static String getPluginClass(String name) {
		// Appending the name of the class to the package name with regards of character cases.
		return "net.goreclan.plugin." + Character.toUpperCase(name.charAt(0)) + name.substring(1) + "Plugin";
	}
	
	
	/**
	 * Return a formatted plugin name.
	 * 
	 * @author Daniele Pantaleone
	 * @return String
	 **/
	public static String getPluginName(String name) {
		// This will act like a ".title()" method missing in the Java API.
		return Character.toUpperCase(name.charAt(0)) + name.substring(1);
	}
	
	
	/**
	 * Create a new Plugin object by matching the specified plugin name.
	 * This will also call the onLoadConfig() method in order to have a fully
	 * initialized plugin ready to be stored in the plugin list. The onStartup()
	 * methods is going to be run after all plugins finish to be loaded.
	 * 
	 * @author Daniele Pantaleone
	 * @param  console A reference to the main BOT console
	 * @param  log A reference to the main BOT logger
	 * @param  name The name of the plugin
	 * @param  config The plugin configuration file name
	 * @return Plugin
	 **/
	public static Plugin buildPlugin(Console console, Log log, String pluginName, String configPath) {
		
		try {
			
			// Getting the correct plugin class in order to instantiate the plugin object.
			Class<?> pluginClass = Class.forName(getPluginClass(pluginName));
			Constructor<?> pluginConstructor = pluginClass.getConstructor();
			log.debug("Loading plugin: " + getPluginName(pluginName) + "...");
			
			// Getting a new instance of the specified plugin.
			Plugin plugin = (Plugin)pluginConstructor.newInstance();
			
			// Initializing main plugin attributes.
			plugin.console = console;											// Copying console reference.
			plugin.log = log;													// Copying main log reference.
			plugin.config = new HierarchicalINIConfiguration(configPath);		// Creating a new parser for the plugin configuration file.
			plugin.commands = new CommandList();								// Creating a new CommandList object for future command registering.
			
			// Calling plugin specific functions, for a proper initialization.
			plugin.onLoadConfig();
			plugin.onStartup();
			
			// Returning back the initialized plugin.
			log.debug("Loaded plugin: '" + getPluginName(pluginName) + ".");
			return (Plugin)plugin;
		
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | ConfigurationException e) {
			log.error("Unable to initialize plugin: " + getPluginName(pluginName), e);
			return null;
		}
		
	}
	
}
