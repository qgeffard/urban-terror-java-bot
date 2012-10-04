/**
 * This class is a utility class that provides behavior to parse Strings into Booleans.
 * 
 * @author      Mathias Van Malderen
 * @version     1.0
 * @copyright   Mathias Van Malderen 09 July, 2012
 * @package     net.goreclan.parser
 **/

package net.goreclan.parser;

import java.util.HashMap;
import java.util.Map;

import net.goreclan.exception.ParserException;

public class BooleanParser {
    
    private static final Map<String, Boolean> stringToBoolean;
    
    static {
        stringToBoolean = new HashMap<String, Boolean>();
        stringToBoolean.put("true", Boolean.TRUE);
        stringToBoolean.put("false", Boolean.FALSE);
        stringToBoolean.put("yes", Boolean.TRUE);
        stringToBoolean.put("no", Boolean.FALSE);
        stringToBoolean.put("1", Boolean.TRUE);
        stringToBoolean.put("0", Boolean.FALSE);
    }
    
    /**
     * @return Boolean
     * @author Mathias Van Malderen
     * @throw ParserException
     **/
    public static Boolean valueOf(String s) {
        Boolean value = stringToBoolean.get(s.toLowerCase());
        if (value == null) throw new ParserException(String.format("Unable to parse String as Boolean: %s", s));
        return value;
    }
    
}