/**
 * @author      Daniele Pantaleone
 * @version     1.0
 * @copyright   Daniele Pantaleone, 27 September, 2012
 * @package     net.goreclan.plugin
 **/

package net.goreclan.plugin;

import net.goreclan.bot.IrcBot;
import net.goreclan.command.Command;
import net.goreclan.logger.Log;
import net.goreclan.parser.XmlConfigParser;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.jibble.pircbot.User;

public abstract class Plugin {
	
	protected final IrcBot bot;
	protected final Log log;
	protected final XmlConfigParser config;
	
	protected boolean enabled = true;
	
	protected Map<String, Command> commands;
	
	
	/**
	 * Object constructor.
	 * 
	 * @author Daniele Pantaleone
	 * @return Plugin
	 **/
	public Plugin(IrcBot bot, Log log, String conf) {
		this.bot = bot;
		this.log = log;
		this.config = new XmlConfigParser(conf);
		this.commands = new HashMap<String, Command>();
	}
	
	
	/**
	 * Load the plugin configuration file.
	 * Must be implemented in all the plugins.
	 *  
	 * @author Daniele Pantaleone
	 **/
	public abstract void onLoadConfig();
	
	
	/**
	 * Enable the plugin
	 * 
	 * @author Daniele Pantaleone
	 */
	public void enable() {
		this.enabled = true;
	}
	
	
	/**
	 * Disable the plugin
	 * 
	 * @author Daniele Pantaleone
	 */
	public void disable() {
		this.enabled = false;
	}
	
	
	/**
	 * Tell if the plugin is enabled
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
	public void registerCommand(String cmdName, String cmdFunc, String cmdLevel) throws NoSuchMethodException, SecurityException {
		
		String pluginName = this.getClass().getName().substring(20, this.getClass().getName().length() - 6);
		this.log.debug("Registering command '!" + cmdName + "' for plugin " + pluginName + " [ Method : " + cmdFunc + " | Level : " + cmdLevel + " ].");		
		Command cmd = new Command(this.getClass().getMethod(cmdFunc, User.class, String.class, String.class), cmdLevel);
		this.commands.put(cmdName, cmd);
		
	}
	
	
	/**
	 * Tells if the Plugin registered the given command.
	 * 
	 * @author Daniele Pantaleone
	 * @param  cmdName The command name
	 * @return boolean
	 **/
	public boolean hasRegisteredCommand(String cmdName) {
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
