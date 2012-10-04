/**
 * @author      Daniele Pantaleone
 * @version     1.0
 * @copyright   Daniele Pantaleone, 05 October, 2012
 * @package     net.goreclan.exception
 **/

package net.goreclan.exception;

public class RecordNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	
	/**
     * Object constructor.
     * 
     * @author Daniele Pantaleone
     * @return RecordNotFoundException
     **/
    public RecordNotFoundException() {
        super();
    }
    
    
    /**
     * Object constructor.
     * 
     * @author Daniele Pantaleone
     * @param  message The exception message
     * @param  cause   A throwable cause
     * @return RecordNotFoundException
     **/
    public RecordNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    
    /**
     * Object constructor.
     * 
     * @author Daniele Pantaleone
     * @param  message The exception message
     * @return RecordNotFoundException
     **/
    public RecordNotFoundException(String message) {
        super(message);
    }
    
    
    /**
     * Object constructor.
     * 
     * @author Mathias Van Malderen
     * @param  cause A throwable cause
     * @return RecordNotFoundException
     **/
    public RecordNotFoundException(Throwable cause) {
        super(cause);
    }
    
}