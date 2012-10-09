/**
 * TODO: Add JavaDoc
 * 
 * @author      Daniele Pantaleone
 * @version     1.0
 * @copyright   Daniele Pantaleone, 09 October, 2012
 * @package     net.goreclan.plugins
 **/

package net.goreclan.plugin;

import net.goreclan.bot.Command;
import net.goreclan.console.Console;
import net.goreclan.domain.Client;
import net.goreclan.domain.Group;
import net.goreclan.logger.Log;
import net.goreclan.parser.XmlConfigParser;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public abstract class Plugin {
	
	protected final Console console;
	protected final XmlConfigParser config;
	protected final Log log;

	protected Map<String, Command> commands;
	
	protected boolean enabled = true;
	
	
	/**
	 * Object constructor.
	 * 
	 * @author Daniele Pantaleone
	 * @return Plugin
	 **/
	public Plugin(Console console, Log log, String conf) {
		this.console = console;								// Copying console reference.
		this.log = log;										// Copying main log reference.
		this.config = new XmlConfigParser(conf);			// Creating a new parser for the plugin configuration file.
		this.commands = new HashMap<String, Command>();		// Initializing the commands hash map in order to register commands.
	}
	
	
	/**
	 * Load the plugin configuration file.
	 * Must be implemented in all the plugins.
	 *  
	 * @author Daniele Pantaleone
	 **/
	public abstract void onLoadConfig();
	
	
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
	 * Register a command.
	 * 
	 * @author Daniele Pantaleone
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 **/	
	public void addCommand(String cmdName, String cmdAlias, Group minGroup, Group maxGroup) throws NoSuchMethodException, SecurityException {
		
		// Converting command name/alias to lowercase.
		// We are not going to accept commands with uppercase chars
		// because this will result in a failure while registering commands
		// having the same name, but using different character cases.
		cmdName = cmdName.toLowerCase();
		cmdAlias = cmdName.toLowerCase();
		
		String cmdMethod = "Cmd" + Character.toUpperCase(cmdName.charAt(0)) + cmdName.substring(1);
		Command cmd = new Command(cmdName, cmdAlias, minGroup, maxGroup, this.getClass().getMethod(cmdMethod, Client.class, String.class));
		this.log.debug("Registering command: " + cmd.toString() + ".");
		
	}
	
	
	/**
	 * Tells whether the Plugin registered the given command.
	 * 
	 * @author Daniele Pantaleone
	 * @param  cmdName The command name
	 * @return boolean
	 **/
	public boolean hasCommand(String cmdName) {
		return this.commands.containsKey(cmdName);
	}
	
	
	/**
	 * Return the Command associated to the given string.
	 * 
	 * @author Daniele Pantaleone
	 * @param  cmdName The command name
	 * @return Command
	 */
	public Command getCommand(String cmdName) {
		return this.commands.get(cmdName);
	}
	
	
	/**
	 * Return a list of available commands.
	 * 
	 * @author Daniele Pantaleone
	 * @return Map<String, Command>
	 */
	public Map<String, Command> getCommandList() {
		return this.commands;
	}
	
	
	/**
	 * Create a new object according to the specified Plugin name.
	 * Invoke the constructor and return back the initialized object.
	 * 
	 * @author Daniele Pantaleone
	 * @param  name The name of the plugin
	 * @return Plugin
	 **/
	public static Plugin getPlugin(IrcBot bot, Log log, String pluginName, String pluginConf) {
		
		try {
			
			// Getting the correct plugin class in order to instantiate an object and call it's constructor. 
			// In this way the plugin can perform all the operations needed since we are passing to the plugin
			// constructor his XML configuration file name (actually we are passing the whole path ^^).
			Class<?> pluginClass = Class.forName("net.goreclan.plugin." + pluginName);
			Constructor<?> construct = pluginClass.getConstructor(IrcBot.class, Log.class, String.class);
			log.debug("Loading plugin '" + pluginName.substring(0, pluginName.length() - 6) + "': " + pluginClass.getName() + "...");
			
			Object plugin = construct.newInstance(bot, log, pluginConf);
			plugin.getClass().getMethod("onLoadConfig").invoke(plugin);
			log.debug("Plugin '" + pluginName.substring(0, pluginName.length() - 6) + "' loaded: " + pluginClass.getName() + ".");
			
			return (Plugin)plugin;
		
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			log.error(e.getMessage());
			return null;
		}
		
	}
	
}
