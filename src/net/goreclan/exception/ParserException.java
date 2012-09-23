/**
 * @author      Mathias Van Malderen
 * @version     1.0
 * @copyright   Mathias Van Malderen, 04 July, 2012
 * @package     net.goreclan.exceptions.impl
 **/

package net.goreclan.exception;

@SuppressWarnings("serial")
public class ParserException extends RuntimeException {

    /**
     * Class constructor
     * 
     * @author Mathias Van Malderen
     **/
    public ParserException() {
        super();
    }
    
    
    /**
     * Class constructor
     * 
     * @author Mathias Van Malderen
     **/
    public ParserException(String message, Throwable cause) {
        super(message, cause);
    }
    
    
    /**
     * Class constructor
     * 
     * @author Mathias Van Malderen
     **/
    public ParserException(String message) {
        super(message);
    }
    
    
    /**
     * Class constructor
     * 
     * @author Mathias Van Malderen
     **/
    public ParserException(Throwable cause) {
        super(cause);
    }

}