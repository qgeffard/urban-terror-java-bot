/**
 * This enum translates between desired log level names and Java Logging API log level names.
 * 
 * @author Mathias Van Malderen
 * @version 1.0
 * @copyright Mathias Van Malderen, 29 June, 2012
 * @package net.goreclan.utility.impl
 **/

package net.goreclan.logger;

import java.util.logging.Level;

public enum LogLevel {
    
    BOT(Level.FINEST),
    INFO(Level.FINER),
    VERBOSE(Level.FINE),
    DEBUG(Level.INFO),
    ERROR(Level.SEVERE);
    
    private Level logLevel;
    
    /**
     * @author Mathias Van Malderen
     * @param logLevel
     **/
    private LogLevel(Level logLevel) {
        this.logLevel = logLevel;
    }
    
    
    /**
     * Translate this LogLevel enum constant into a Java Logging API log level.
     * 
     * @return Level
     * @author Mathias Van Malderen
     **/
    public Level toFrameworkLevel() {
        return logLevel;
    }
    
    
    /**
     * Translate a Java Logging API log level into a LogLevel enum constant.
     * 
     * @return LogLevel
     * @author Mathias Van Malderen
     * @param logLevel
     **/
    public static LogLevel toLogLevel(Level logLevel) {
        
        if (Level.FINEST.equals(logLevel))  return BOT;
        if (Level.FINER.equals(logLevel))   return INFO;
        if (Level.FINE.equals(logLevel))    return VERBOSE;
        if (Level.INFO.equals(logLevel))    return DEBUG;
        if (Level.SEVERE.equals(logLevel))  return ERROR;
        
        return null;
    }
    
    
    /**
     * Translate a Java Logging API log level into a String representation
     * corresponding to a LogLevel enum constant.
     * 
     * @return String
     * @param logLevel
     * @author Mathias Van Malderen
     **/
    public static String toString(Level logLevel) {
        return toLogLevel(logLevel).toString();
    }
    
}