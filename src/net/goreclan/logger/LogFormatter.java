/**
 * This class takes care of formatting Java Logging API log records into a desired format.
 * 
 * @author Mathias Van Malderen
 * @version 1.0
 * @copyright Mathias Van Malderen, 29 June, 2012
 * @package net.goreclan.logger
 **/

package net.goreclan.logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class LogFormatter extends Formatter {
    
    private static final String TIME_FORMAT_STR = "yyyy-MM-dd hh:mm:ss";
    private static final DateFormat dateFormatter = new SimpleDateFormat(TIME_FORMAT_STR);
    
    /**
     * @return String
     * @author Mathias Van Malderen
     **/
    @Override
    public String format(LogRecord logRecord) {
        
        String logTime = formatTime(logRecord.getMillis());
        String logLevel = formatLevel(logRecord.getLevel());
        String logMessage = formatMessage(logRecord.getMessage());
        
        return String.format("%-20s%-10s%s\n", logTime, logLevel, logMessage);
    }
    
    
    /**
     * @return String
     * @author Mathias Van Malderen
     **/
    public String formatTime(long millis) {
        return dateFormatter.format(new Date(millis));
    }
    
    
    /**
     * @return String
     * @author Mathias Van Malderen
     **/
    public String formatLevel(Level logLevel) {
        return LogLevel.toString(logLevel);
    }
    
    
    /**
     * @return String
     * @author Mathias Van Malderen
     **/
    public String formatMessage(String message) {
        return message.replace("\n", ", ");
    }
    
}