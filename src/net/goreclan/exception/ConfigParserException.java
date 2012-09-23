/**
 * @author      Mathias Van Malderen
 * @version     1.0
 * @copyright   Mathias Van Malderen, 09 July, 2012
 * @package     net.goreclan.exceptions.impl
 **/

package net.goreclan.exception;

@SuppressWarnings("serial")
public class ConfigParserException extends ParserException {
    
    private static final String EXCEPTION_MSG = "%s [section = '%s', option = '%s']";
    
    /**
     * Return an exception message with section and option filled in.
     * 
     * @return String
     * @author Mathias Van Malderen
     **/
    private static String getExceptionMsg(String message, String section, String option) {
        return String.format(EXCEPTION_MSG, message, section, option);
    }
    
    
    /**
     * Class constructor.
     * 
     * @author Mathias Van Malderen
     **/
    public ConfigParserException() {
        super();
    }
    
    
    /**
     * Class constructor.
     * 
     * @author Mathias Van Malderen
     **/
    public ConfigParserException(String message, String section, String option, Throwable cause) {
        super(getExceptionMsg(message, section, option), cause);
    }
    
    
    /**
     * Class constructor.
     * 
     * @author Mathias Van Malderen
     **/
    public ConfigParserException(String message, String section, String option) {
        super(getExceptionMsg(message, section, option));
    }
    
    
    /**
     * Class constructor.
     * 
     * @author Mathias Van Malderen
     **/
    public ConfigParserException(String message, Throwable cause) {
        super(message, cause);
    }
    
    
    /**
     * Class constructor.
     * 
     * @author Mathias Van Malderen
     **/
    public ConfigParserException(String message) {
        super(message);
    }
    
    
    /**
     * Class constructor.
     * 
     * @author Mathias Van Malderen
     **/
    public ConfigParserException(Throwable cause) {
        super(cause);
    }
    
}
