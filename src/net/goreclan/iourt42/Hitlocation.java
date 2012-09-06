/**
 * This Enum handle the Urban Terror Hit Locations
 * 
 * @author      Daniele Pantaleone
 * @version     1.1
 * @copyright   Daniele Pantaleone, 15 July, 2012
 * @package     net.goreclan.iourt42
 **/

package net.goreclan.iourt42;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Hitlocation {
    
    HEAD(1,"HEAD"),
    HELMET(4,"HELMET"),
    TORSO(5,"TORSO"),
    VEST(6,"VEST"),
    LEFT_ARM(7,"LEFT_ARM"),
    RIGHT_ARM(8,"RIGHT_ARM"),
    LEGS(9,"LEGS");
    //TODO: Find the hit code for body hits
    
    private static final Map<Integer, Hitlocation> hitlocationByCode = new HashMap<Integer, Hitlocation>();
    private static final Map<String, Hitlocation> hitlocationByName = new HashMap<String, Hitlocation>();
    private Integer code = null;
    private String  name = null;
    
    
    static {
        for (Hitlocation h : EnumSet.allOf(Hitlocation.class)) {
            Hitlocation.hitlocationByCode.put(h.code, h);
            Hitlocation.hitlocationByName.put(h.name, h);
        }
    }
    
    
    /**
     * Object constructor
     * 
     * @return Hitlocation
     * @author Daniele Pantaleone 
     **/
    private Hitlocation(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
    
    
    /**
     * @return Integer
     * @author Daniele Pantaleone
     */
    public Integer getCode() {
        return code;
    }
    
    
    /**
     * @return Integer
     * @author Daniele Pantaleone
     */
    public String getName() {
        return name;
    }
    
    
    /**
     * Return a Hitlocation object by matching the Hitlocation code
     * 
     * @return Hitlocation
     * @author Daniele Pantaleone
     * @throws IndexOutOfBoundException 
     */
    public static Hitlocation getByCode(Integer code) throws IndexOutOfBoundsException {
        
    	if (!hitlocationByCode.containsKey(code)) 
        	throw new IndexOutOfBoundsException(String.format("Invalid hitlocation code: %d.", code));
        
    	return hitlocationByCode.get(code);
    }
    
    
    /**
     * Return a Hitlocation object by matching the Hitlocation name
     * 
     * @return Hitlocation
     * @author Daniele Pantaleone
     * @throws IndexOutOfBoundException 
     */
    public static Hitlocation getByName(String name) throws IndexOutOfBoundsException {
        
    	if (!hitlocationByName.containsKey(name.toUpperCase())) 
    		throw new IndexOutOfBoundsException(String.format("Invalid hitlocation name: %s.", name.toUpperCase()));
        
    	return hitlocationByName.get(name.toUpperCase());
    }
    
    
    /**
     * String object representation
     * 
     * @return String
     * @author Daniele Pantaleone
     **/
    public String toString() {
        
    	// Returning a String object representation
        return String.format("[ code : %d | name : %s ]", this.getCode(), this.getName());
        
    }
    
}