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
import java.lang.reflect.Method;
import java.util.Iterator;

import net.goreclan.console.Console;
import net.goreclan.control.GroupC;
import net.goreclan.domain.Client;
import net.goreclan.domain.Group;
import net.goreclan.exception.CommandRegisterException;
import net.goreclan.utility.Command;
import net.goreclan.utility.CommandList;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.apache.commons.configuration.SubnodeConfiguration;
import org.apache.commons.logging.Log;

public abstract class Plugin {
	
	private static final String DEFAULT_COMMAND_MIN_LEVEL = "superadmin";
	private static final String DEFAULT_COMMAND_MAX_LEVEL = "superadmin";
	
	protected HierarchicalINIConfiguration config;
	protected Console console;
	protected Log log;
	
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
	 * @param  name The command name
	 * @param  alias The command alias
	 * @param  minLevel The minimum group level which will be able to access this command (level/keyword)
	 * @param  maxLevel The maximum group level which will be able to access this command (level/keyword)
	 **/	
	@SuppressWarnings("unused")
	private void registerCommand(String name, String alias, String minLevel, String maxLevel) {
		
		try {
			
			/**
			 * We are going to register command using only lowercase strings. If we let plugin coders register commands with mixed 
			 * upper/lower case characters we may have some problems while checking a command name/alias existing in the command list.
			 * Instead of bothering us with futile errors, we are going to lowercase the given name/alias and safely remove leading and trailing spaces.
			 **/
			
			// Checking correct name parameter.
			if (name == null || name.length() == 0) throw new CommandRegisterException("Unable to register command: invalid command name specified.");
			name = name.toLowerCase().trim();
			
			// Checking if alias parameter has been given.
			if (alias == null || alias.length() == 0) alias = name;
			else alias = alias.toLowerCase().trim();
			
			// Initializing command levels.
			Group minGroup = null, maxGroup = null;
			
			if (minLevel == null || minLevel.length() == 0) {
				minGroup = GroupC.getByKeyword(DEFAULT_COMMAND_MIN_LEVEL);
				this.log.debug("Minimum group level modifier not specified for command !" + name + ". Minimum group level changed to: " + minGroup.keyword + "[" + minGroup.level + "]");
			} 
			else {
				
				minLevel = minLevel.toLowerCase().trim();
				if (minLevel.matches("\\d*")) minGroup = GroupC.getByLevel(Integer.valueOf(minLevel));
				else if (minLevel.matches("\\w*")) minGroup = GroupC.getByKeyword(minLevel);
				
				if (minGroup == null) { 
					minGroup = GroupC.getByKeyword(DEFAULT_COMMAND_MIN_LEVEL);
					this.log.error("Unable to get correct minimum level for command !" + name + ". Invalid group level modifier specified: " + minLevel + ". Minimum group level changed to: " + minGroup.keyword + "[" + minGroup.level + "]");
				}
				
			} 
			
			if (maxLevel == null || maxLevel.length() == 0) {
				// Logging as trace since the maximum level is not strictly required.
				maxGroup = GroupC.getByKeyword(DEFAULT_COMMAND_MAX_LEVEL);
				this.log.trace("Maximum group level modifier not specified for command !" + name + ". Maximum group level changed to: " + maxGroup.keyword + "[" + maxGroup.level + "]");
			} 
			else {
				
				maxLevel = maxLevel.toLowerCase().trim();
				if (maxLevel.matches("\\d*")) maxGroup = GroupC.getByLevel(Integer.valueOf(minLevel));
				else if (maxLevel.matches("\\w*")) maxGroup = GroupC.getByKeyword(minLevel);
				
				if (maxGroup == null) { 
					maxGroup = GroupC.getByKeyword(DEFAULT_COMMAND_MAX_LEVEL);
					this.log.error("Unable to get correct maximum level for command !" + name + ". Invalid group level modifier specified: " + maxLevel + ". Maximum group level changed to: " + maxGroup.keyword + "[" + maxGroup.level + "]");
				}
				
			} 
			
			// Checking group levels correctness.
			if (minGroup.level > maxGroup.level) {
				minGroup = maxGroup;
				this.log.error("Detected group level mismatch for command !" + name + ". Minimum group level is higher than maximum group level. Minimum group level changed to: " + maxGroup.keyword + "[" + maxGroup.level + "]");
			}
			
			try {
				
				// We need to match the given command name with a specific plugin method. Given the command name "blub", 
				// a map will be created against a Method named "CmdBlub" which accept as input parameters the "client" object 
				// (which refers to the client who issued the command) and an optional "data" string (may be null) which
				// contains Method specific extra data, needed to perform certain operations.
				Method method = this.getClass().getMethod(Command.getCommandMethod(name), Client.class, String.class);
				Command cmd = new Command(name, alias, minGroup, maxGroup, method);
				this.commands.put(name, alias, cmd);
				this.log.debug("Registered command: " + cmd.toString() + ".");
			
			} catch (NoSuchMethodException e) {
				// Throwing the exception using our custom one (CommandRegisterException).
				throw new CommandRegisterException("Unable to register command: " + e.getMessage() + ".");
			}
		
		} catch (CommandRegisterException e) {
			// Logging the exception.
			this.log.error(e.toString());
		}
		
	}
	
	
	/**
	 * Register all the commands specified in the plugin configuration file.
	 * 
	 * @author Daniele Pantaleone
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 **/	
	@SuppressWarnings("unused")
	private void registerCommands() {
		
		SubnodeConfiguration section = this.config.getSection("commands");
		if (section.isEmpty()) {
			// No commands specified in the
			// plugin configuration file.
			// We can exit here.
			return; 
		}
		
		// Looping through the whole commands section.
		Iterator<String> i = section.getKeys();
		while (i.hasNext()) { 
			
			String key = i.next();
			String val = this.config.getString(key);
			String[] keyList = key.split("-", 2);
			String[] valList = val.split("-", 2);
			
			String name = null, alias = null;
			String minLevel = null, maxLevel = null;
			
			/**
			 * We need to check for a proper command configuration syntax.
			 * In order to avoid future problems, during the real command 
			 * registration, all the declared commands MUST follow this syntax:
			 * 
			 * 	- <cmdName>[-<cmdAlias>]=<cmdMinLevel>[-<cmdMaxLevel>]
			 **/
			
			// Command name/alias.
			name = keyList[0];
			if (keyList.length == 2) 
				alias = keyList[1];
			
			// Command minLevel/maxLevel.
			minLevel = valList[0];
			if (valList.length == 2) 
				maxLevel = valList[1];
			
			// Registering the command.
			this.registerCommand(name, alias, minLevel, maxLevel);
			
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
	 * @param  name The plugin name
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
	 * @param  name The plugin name
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
			plugin.config = new HierarchicalINIConfiguration(configPath);		// Creating a new parser for the plugin configuration file.
			plugin.console = console;											// Copying console reference.
			plugin.log = log;													// Copying main log reference.
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
