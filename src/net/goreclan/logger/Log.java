/**
 * Logger implementation which uses the org.apache.log4j.Logger class (Apache Foundation).
 * This is class is intended to be used for debugging the application code in order to
 * avoid bugs and to check the general application environment.
 * 
 * Available log levels:
 * 		
 * 		- OFF 		[ The OFF has the highest possible rank and is intended to turn off logging. ]
 *      - FATAL		[ The FATAL level designates very severe error events that will presumably lead the application to abort. ]
 *      - ERROR		[ The ERROR level designates error events that might still allow the application to continue running.]
 *      - WARN		[ The WARN level designates potentially harmful situations. ]
 *      - INFO      [ The INFO level designates informational messages that highlight the progress of the application at coarse-grained level. ]
 *      - DEBUG		[ The DEBUG level designates fine-grained informational events that are most useful to debug an application. ]
 *      - TRACE		[ The TRACE Level designates finer-grained informational events than the DEBUG. ]
 *      - ALL       [ The ALL has the lowest possible rank and is intended to turn on all logging. ]
 * 
 * @author      Daniele Pantaleone
 * @version     1.0
 * @copyright   Daniele Pantaleone, 05 October, 2012
 * @package     net.goreclan.logger
 **/

package net.goreclan.logger;

import java.io.IOException;
import java.io.OutputStreamWriter;

import net.goreclan.parser.XmlConfigParser;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.FileAppender;
import org.apache.log4j.PatternLayout;

public class Log {
	
	private Logger logger;
	private PatternLayout layout;
	private FileAppender file;
	private ConsoleAppender console;
	
	/**
	 * Object constructor.
	 * 
	 * @author Daniele Pantaleone
	 * @throws IOException 
	 * @return Log
	 **/
	public Log(XmlConfigParser config) throws IOException {
		
		this.logger = Logger.getLogger(Log.class);
		this.layout = new PatternLayout("%-20d{yyyy-MM-dd hh:mm:ss} %-5p [%t]: %m%n");
		
		// Creating the FileAppender. This will be created anyway. It doesn't
		// matter if the log level specified in the configuration file is "OFF".
		this.file = new FileAppender();
		this.file.setLayout(this.layout);
		this.file.setFile(config.getString("log","path"));
		this.file.setAppend(config.getBoolean("log","append"));
		this.file.setName("File");
		this.file.activateOptions();
		this.logger.addAppender(this.file);
		
		if (config.getBoolean("log","console")) {
			// The user specified to use also the JAVA System.out to print
			// log messages. We are going to create and add one more handler
			// for this purpose (logging performances will decrease though).
			this.console = new ConsoleAppender();
			this.console.setLayout(this.layout);
			this.console.setWriter(new OutputStreamWriter(System.out));
			this.console.activateOptions();
			this.logger.addAppender(this.console);
		}
		

		// Setting the desired log level (will fall back to Level.DEBUG if
		// the user specified a wrong string in the configuration file).
		this.logger.setLevel(Level.toLevel(config.getString("log","level")));
	
	}
	
	
	/**
	 * Print a TRACE message in the log handler.
	 * 
	 * @author Daniele Pantaleone
	 * @param  message The message to be printed
	 */
	public void trace(String message) {
		this.logger.trace(message);
	}
	
	
	/**
	 * Print a DEBUG message in the log handler.
	 * 
	 * @author Daniele Pantaleone
	 * @param  message The message to be printed
	 */
	public void debug(String message) {
		this.logger.debug(message);
	}
	
	
	/**
	 * Print a INFO message in the log handler.
	 * 
	 * @author Daniele Pantaleone
	 * @param  message The message to be printed
	 */
	public void info(String message) {
		this.logger.info(message);
	}
	
	
	/**
	 * Print a WARN message in the log handler.
	 * 
	 * @author Daniele Pantaleone
	 * @param  message The message to be printed
	 */
	public void warn(String message) {
		this.logger.warn(message);
	}
	
	
	/**
	 * Print a ERROR message in the log handler.
	 * 
	 * @author Daniele Pantaleone
	 * @param  message The message to be printed
	 */
	public void error(String message) {
		this.logger.error(message);
	}
	
	
	/**
	 * Print a FATAL message in the log handler.
	 * 
	 * @author Daniele Pantaleone
	 * @param  message The message to be printed
	 */
	public void fatal(String message) {
		this.logger.fatal(message);
	}
	
	
    public static void main(String args[]) throws IOException {
    	
    	Log log = new Log();
    	
    	
    }
    
    
}