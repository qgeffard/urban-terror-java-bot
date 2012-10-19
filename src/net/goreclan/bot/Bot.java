/**
 * Main BOT class.
 * 
 * @author      Daniele Pantaleone
 * @version     1.0
 * @copyright   Daniele Pantaleone, 19 October, 2012
 * @package     net.goreclan.bot
 **/

package net.goreclan.bot;

import java.io.OutputStreamWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import net.goreclan.console.Console;
import net.goreclan.parser.Parser;
import net.goreclan.plugin.Plugin;
import net.goreclan.utility.DataSourceManager;

public class Bot {
	
	public static final String BOTNAME = "[GORE]BOT";
	public static final String VERSION = "1.0";
	public static final String AUTHOR = "Daniele Pantaleone, Mathias Van Malderen";
	
	public static Log log;														// Main BOT logger utility.
	public static HierarchicalINIConfiguration config;							// Main configuration .ini file.
	public static Console console;												// Urban Terror 4.2 console utility.
	public static Parser parser;												// Urban Terror 4.2 log parser.
	
	public static Map<String, String> game;										// Hold current game cvars settings.
	public static Map<String, Plugin> plugins;									// Hold loaded BOT plugins.
	
	
	/**
	 * Run the BOT.
	 * 
	 * @author Daniele Pantaleone
	 * @param args Extra arguments to be passed at runtime
	 **/
	public static void main(String[] args) {
	
		try {
			
			// Turning Log level off before initialization.
			Logger.getRootLogger().setLevel(Level.OFF);
			
			// Creating the logger using log4j library.
			Logger logger = Logger.getLogger("main");
			
			// Creating the main configuration object.
			config = new HierarchicalINIConfiguration("conf/main.ini");
			
			// Creating the logger FileAppender.
			FileAppender fileAppender = new FileAppender();
			fileAppender.setLayout(new PatternLayout("%-20d{yyyy-MM-dd hh:mm:ss} %-6p [%t]: %m%n"));
			fileAppender.setFile("log/bot.log");
			fileAppender.setAppend(config.getBoolean("log.append"));
			fileAppender.setName("FILE");
			fileAppender.activateOptions();
			
			// Adding the FileAppender to the main logger.
			logger.addAppender(fileAppender);
			
			if (config.getBoolean("log.console")) {
				// Creating the logger ConsoleAppender.
				ConsoleAppender consoleAppender = new ConsoleAppender();
				consoleAppender.setLayout(new PatternLayout("%-20d{yyyy-MM-dd hh:mm:ss} %-6p [%t]: %m%n"));
				consoleAppender.setWriter(new OutputStreamWriter(System.out));
				consoleAppender.setName("CONSOLE");
				consoleAppender.activateOptions();
				
				// Adding the ConsoleAppender to the main logger.
				logger.addAppender(consoleAppender);
			}
			
			// Setting the log level for both the log appenders.
			logger.setLevel(Level.toLevel(config.getString("log.level")));
			
			// Creating the main Log object.
			log = new Log4JLogger(logger);
			
			// We got a fully initialized logger utility now.
			// We can start printing some info messages on it.
			log.info("Starting " + BOTNAME + " v" + VERSION + " [ " + AUTHOR + " ].");
			
			// Initializing DataSourcemanager class.
			DataSourceManager.setDCS(config.getString("database.dcs"));
			DataSourceManager.setUsername(config.getString("database.username"));
			DataSourceManager.setPassword(config.getString("database.password"));
			
			log.info("DataSourceManager initialized: " + config.getString("database.dcs") + ".");
			
			// Creating a Map where to store game cvars.
			game = new LinkedHashMap<String, String>();
			
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		
	}

}
