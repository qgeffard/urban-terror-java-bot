/**
 * @author      Mathias Van Malderen, Daniele Pantaleone
 * @version     1.1
 * @copyright   Mathias Van Malderen, 04 July, 2012
 * @package     net.goreclan.exception
 **/

package net.goreclan.exception;

public class XmlConfigParserException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	
	/**
     * Return an exception message with section and option filled in.
     * 
     * @return String
     * @param  message The exception message
     * @param  section The section of the XML config file
     * @param  option  The option of the XML config file
     * @return String
     * @author Mathias Van Malderen
     **/
    private static String getExceptionMsg(String message, String section, String option) {
    	return message + " [SECTION = '" + section + "', OPTION = '" + option + "'].";
    }
    
    
	/**
     * Object constructor.
     * 
     * @author Mathias Van Malderen
     **/
    public XmlConfigParserException() {
        super();
    }
    
    
    /**
     * Object constructor.
     * 
     * @author Mathias Van Malderen
     * @param  message The exception message
     * @param  cause   a Throwable cause
     * @return XmlConfigParserException
     **/
    public XmlConfigParserException(String message, Throwable cause) {
        super(message, cause);
    }
    
    
    /**
     * Object constructor.
     * 
     * @author Mathias Van Malderen
     * @param  message The exception message
     * @return XmlConfigParserException
     **/
    public XmlConfigParserException(String message) {
        super(message);
    }
    
    
    /**
     * Object constructor.
     * 
     * @author Mathias Van Malderen
     * @param  cause a Throwable cause
     * @return XmlConfigParserException
     **/
    public XmlConfigParserException(Throwable cause) {
        super(cause);
    }
    
    /**
     * Object constructor.
     * 
     * @author Mathias Van Malderen
     * @param  message The exception message
     * @param  section The section of the XML config file
     * @param  optoion The option of the XML config file
     * @param  cause   a Throwable cause
     * @return XmlConfigParserException
     **/
    public XmlConfigParserException(String message, String section, String option, Throwable cause) {
        super(getExceptionMsg(message, section, option), cause);
    }
    
    
    /**
     * Object constructor.
     * 
     * @return XmlConfigParserException
     * @author Mathias Van Malderen
     **/
    public XmlConfigParserException(String message, String section, String option) {
        super(getExceptionMsg(message, section, option));
    }

}