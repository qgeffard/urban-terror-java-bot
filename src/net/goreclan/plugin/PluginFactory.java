/**
 * @author      Daniele Pantaleone
 * @version     1.0
 * @copyright   Daniele Pantaleone, 15 October, 2012
 * @package     net.goreclan.plugin
 **/

package net.goreclan.plugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import net.goreclan.console.Console;

import org.apache.log4j.Logger;

public class PluginFactory {
	
	
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
	public static Plugin buildPlugin(Console console, Logger log, String name, String config) {
		
		try {
			
			// Getting the correct plugin class in order to instantiate an object and call it's constructor. 
			// In this way the plugin can perform all the operations needed since we are passing to the plugin
			// constructor his XML configuration file name (actually we are passing the whole path ^^).
			Class<?> pluginClass = Class.forName("net.goreclan.plugin." + Character.toUpperCase(name.charAt(0)) + name.substring(1));
			Constructor<?> pluginConst = pluginClass.getConstructor(Console.class, Logger.class, String.class);
			log.debug("Loading plugin: " + name + "...");
			
			// Creating the plugin object and initializing it.
			Object plugin = pluginConst.newInstance(console, log, config);
			plugin.getClass().getMethod("onLoadConfig").invoke(plugin);
			log.debug("Loaded plugin: '" + name + ".");
			
			return (Plugin)plugin;
		
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			log.error(e.getMessage());
			return null;
		}
		
	}

}
