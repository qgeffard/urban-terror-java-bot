/**
 * @author      Daniele Pantaleone
 * @version     1.0
 * @copyright   Daniele Pantaleone, 05 October, 2012
 * @package     net.goreclan.exception
 **/

package net.goreclan.exception;

public class CommandRegisterException extends Exception {

	private static final long serialVersionUID = 1L;
	
	
	/**
     * Object constructor.
     * 
     * @author Daniele Pantaleone
     * @return CommandRegisterException
     **/
    public CommandRegisterException() {
        super();
    }
    
    
    /**
     * Object constructor.
     * 
     * @author Daniele Pantaleone
     * @param  message The exception message
     * @param  cause   A throwable cause
     * @return CommandRegisterException
     **/
    public CommandRegisterException(String message, Throwable cause) {
        super(message, cause);
    }
    
    
    /**
     * Object constructor.
     * 
     * @author Daniele Pantaleone
     * @param  message The exception message
     * @return CommandRegisterException
     **/
    public CommandRegisterException(String message) {
        super(message);
    }
    
    
    /**
     * Object constructor.
     * 
     * @author Daniele Pantaleone
     * @param  cause A throwable cause
     * @return CommandRegisterException
     **/
    public CommandRegisterException(Throwable cause) {
        super(cause);
    }
    
}