/**
 * @author      Daniele Pantaleone
 * @version     1.0
 * @copyright   Daniele Pantaleone, 19 October, 2012
 * @package     net.goreclan.exception
 **/

package net.goreclan.exception;

public class ParserException extends Exception {

	private static final long serialVersionUID = 1L;
	
	
	/**
     * Object constructor.
     * 
     * @author Daniele Pantaleone
     * @return ParserException
     **/
    public ParserException() {
        super();
    }
    
    
    /**
     * Object constructor.
     * 
     * @author Daniele Pantaleone
     * @param  message The exception message
     * @param  cause   A throwable cause
     * @return ParserException
     **/
    public ParserException(String message, Throwable cause) {
        super(message, cause);
    }
    
    
    /**
     * Object constructor.
     * 
     * @author Daniele Pantaleone
     * @param  message The exception message
     * @return ParserException
     **/
    public ParserException(String message) {
        super(message);
    }
    
    
    /**
     * Object constructor.
     * 
     * @author Daniele Pantaleone
     * @param  cause A throwable cause
     * @return ParserException
     **/
    public ParserException(Throwable cause) {
        super(cause);
    }
    
}