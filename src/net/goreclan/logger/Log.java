/**
 * This class provides an interface with the Java Logging API.
 * 
 * @author Mathias Van Malderen
 * @version 1.0
 * @copyright Mathias Van Malderen, 29 June, 2012
 * @package net.goreclan.logger
 **/

package net.goreclan.logger;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {
    
    private static Logger logger;
    private static Level logLevel;
    private static String logPath;
    private static Formatter logFormatter;
    private static Handler consoleHandler;
    private static Handler fileHandler;
    private static boolean developer;
    private static boolean append;
    
    static { Log.setLogLevel(LogLevel.DEBUG); }
    
    
    private Log() {}
    
    
    /**
     * Log a bot message.
     * 
     * @author Mathias Van Malderen
     */
    public static void bot(String message) {
        log(LogLevel.BOT, message);
    }
    
    
    /**
     * Log an info message.
     * 
     * @author Mathias Van Malderen
     */
    public static void info(String message) {
        log(LogLevel.INFO, message);
    }
    
    
    /**
     * Log a verbose message.
     * 
     * @author Mathias Van Malderen
     **/
    public static void verbose(String message) {
        log(LogLevel.VERBOSE, message);
    }
    
    
    /**
     * Log a debug message.
     * 
     * @author Mathias Van Malderen
     **/
    public static void debug(String message) {
        log(LogLevel.DEBUG, message);
    }
    
    
    /**
     * Log an error message.
     * 
     * @author Mathias Van Malderen
     **/
    public static void error(String message) {
        log(LogLevel.ERROR, message);
    }
    
    
    /**
     * This method isolates all log handler instantiation and configuration.
     * 
     * @return Handler
     * @throws SecurityException
     * @throws IOException
     * @author Mathias Van Malderen
     **/
    private static Handler createHandler(String type) throws SecurityException, IOException {
        
        Handler h;
        
        if ("file".equals(type)) h = new FileHandler(logPath, append);
        else h = new ConsoleHandler();
        
        h.setLevel(Log.logLevel);
        h.setFormatter(getLogFormatter());
        
        return h;
    }
    
    
    /**
     * Write a message in the log.
     * 
     * @author Mathias Van Malderen
     **/
    private static void log(LogLevel level, String message) {
        getLogger().log(level.toFrameworkLevel(), message);
    }
    
    
    /**
     * @return Logger
     * @author Mathias Van Malderen
     **/
    private static Logger getLogger() {
        
        if (logger == null) {
            
            try {
                
                logger = Logger.getLogger("net.goreclan.gorebot");
                logger.setUseParentHandlers(false);
                logger.setLevel(Log.logLevel);
                
                if (logPath != null) {
                    fileHandler = createHandler("file");
                    logger.addHandler(fileHandler);
                }
                
                if (developer || logPath == null) {
                    consoleHandler = createHandler("console");
                    logger.addHandler(consoleHandler);
                }
                
            } catch (IOException | SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
        
        return logger;
        
    }
    
    /**
     * @return Formatter
     * @author Mathias Van Malderen
     **/
    private static Formatter getLogFormatter() {
        
        if (Log.logFormatter == null) Log.logFormatter = new LogFormatter();
        return Log.logFormatter;
    }
    
    
    /**
     * Set the current log level.
     * 
     * @author Mathias Van Malderen
     **/
    public static void setLogLevel(LogLevel logLevel) {
        Log.logLevel = logLevel.toFrameworkLevel();
    }
    
    
    /**
     * Set the current log path.
     * 
     * @author Mathias Van Malderen
     **/
    public static void setLogPath(String logPath) {
        Log.logPath = logPath;
    }
    
    
    /**
     * Set the log developer mode.
     * If set, log messages are printed also in the standard output.
     * 
     * @author Mathias Van Malderen
     **/
    public static void setDeveloper(boolean developer) {
        Log.developer = developer;
    }
    
    
    /**
     * Set whether to create new log at each bot start.
     * 
     * @author Mathias Van Malderen
     **/
    public static void setAppend(boolean append) {
        Log.append = append;
    }
    
    
    /**
     * For testing purposes only
     * 
     * @return void
     * @author Mathias Van Malderen
     **/
    public static void main(String[] args) {
        Log.setDeveloper(true);
        Log.bot("This is a bot message.");
        Log.info("This is an info message.");
        Log.verbose("This is a verbose message.");
        Log.debug("This is a debugging message.");
        Log.error("This is an error message.");
    }
    
}