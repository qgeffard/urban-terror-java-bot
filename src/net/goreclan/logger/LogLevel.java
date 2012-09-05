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
    
    VERBOSE(Level.FINEST),
    DEBUG(Level.FINE),
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
        if (Level.FINEST.equals(logLevel)) return VERBOSE;
        else if (Level.FINE.equals(logLevel)) return DEBUG;
        else if (Level.SEVERE.equals(logLevel)) return ERROR;
        else return null;
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