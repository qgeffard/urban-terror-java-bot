/**
 * This Enum handle the Urban Terror Hit Locations
 * 
 * @author      Daniele Pantaleone
 * @version     1.2
 * @copyright   Daniele Pantaleone, 15 July, 2012
 * @package     net.goreclan.iourt42
 **/

package net.goreclan.iourt42;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Hitlocation {
    
    HEAD		(1,"HEAD"),
    HELMET		(4,"HELMET"),
    TORSO		(5,"TORSO"),
    VEST		(6,"VEST"),
    LEFT_ARM	(7,"LEFT_ARM"),
    RIGHT_ARM	(8,"RIGHT_ARM"),
    LEGS		(9,"LEGS");
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
     * Object constructor.
     * 
     * @author Daniele Pantaleone 
     * @return Hitlocation
     **/
    private Hitlocation(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
    
    
    /**
     * @author Daniele Pantaleone
     * @return Integer
     **/
    public Integer getCode() {
        return code;
    }
    
    
    /**
     * @author Daniele Pantaleone
     * @return Integer
     **/
    public String getName() {
        return name;
    }
    
    
    /**
     * Return a Hitlocation object by matching the hit location code.
     * 
     * @author Daniele Pantaleone
     * @param  code The hit location code
     * @throws IndexOutOfBoundException 
     * @return Hitlocation
     **/
    public static Hitlocation getByCode(Integer code) throws IndexOutOfBoundsException {
        
    	if (!hitlocationByCode.containsKey(code)) 
        	throw new IndexOutOfBoundsException("Unable to match hit location code: " + code + ".");
        
    	return hitlocationByCode.get(code);
    }
    
    
    /**
     * Return a Hitlocation object by matching the hit location name.
     * 
     * @author Daniele Pantaleone
     * @param  name The hit location visual identifier
     * @throws IndexOutOfBoundException 
     * @return Hitlocation
     **/
    public static Hitlocation getByName(String name) throws IndexOutOfBoundsException {
        
    	name = name.toUpperCase();
    	if (!hitlocationByName.containsKey(name)) 
    		throw new IndexOutOfBoundsException("Unable to match hitlocation name: " + name + ".");
        
    	return hitlocationByName.get(name);
    }
    
    
    /**
     * String object representation.
     * 
     * @author Daniele Pantaleone
     * @return String
     **/
    public String toString() {
        
    	// Returning a String object representation
        return "[ code : " + this.code + "  | name : " + this.name + " ]";
        
    }
    
}