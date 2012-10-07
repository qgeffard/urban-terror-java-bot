/**
 * This Enum handle the Urban Terror Gametypes.
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

public enum Gametype {
    
    GT_FFA	(0,	"FFA"),
    GT_LMS	(1,	"LMS"),
    GT_TDM	(3,	"TDM"),
    GT_TS	(4,	"TS"),
    GT_FTL	(5,	"FTL"),
    GT_CAH	(6,	"CAH"),
    GT_CTF	(7,	"CTF"),
    GT_BOMB	(8, "BOMB");
    
    private static final Map<Integer, Gametype> gametypeByCode = new HashMap<Integer, Gametype>();
    private static final Map<String, Gametype> gametypeByName = new HashMap<String, Gametype>();
    private Integer code;
    private String name;
    
    
    static {
        for (Gametype g : EnumSet.allOf(Gametype.class)) {
            Gametype.gametypeByCode.put(g.code, g);
            Gametype.gametypeByName.put(g.name, g);
        }
    }
    
    
    /**
     * Object constructor.
     * 
     * @author Daniele Pantaleone 
     * @param  code The Gametype code
     * @param  name The Gametype short visual identifier
     * @return Gametype
     **/
    private Gametype(int code, String name) {
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
     * @return String
     **/
    public String getName() {
        return name;
    }
    
    
    /**
     * Return a Gametype object by matching the Gametype code.
     * 
     * @author Daniele Pantaleone
     * @param  code The Gametype code
     * @throws IndexOutOfBoundException 
     * @return Gametype
     **/
    public static Gametype getByCode(Integer code) throws IndexOutOfBoundsException {
        
    	if (!gametypeByCode.containsKey(code)) 
        	throw new IndexOutOfBoundsException("Unable to match gametype code: " + code + ".");
        
    	return gametypeByCode.get(code);
    }
    
    
    /**
     * Return a Gametype object by matching the Gametype name.
     * 
     * @author Daniele Pantaleone
     * @param  name The Gametype short visual identifier
     * @throws IndexOutOfBoundException 
     * @return Gametype
     **/
    public static Gametype getByName(String name) throws IndexOutOfBoundsException {
        
    	name = name.toUpperCase();
    	if (!gametypeByName.containsKey(name)) 
        	throw new IndexOutOfBoundsException("Unable to match gametype name: " + name + ".");
        
    	return gametypeByName.get(name.toUpperCase());
    }
    
    
    /**
     * String object representation.
     * 
     * @author Daniele Pantaleone
     * @return String
     **/
    public String toString() {
        
    	// Returning a String object representation
        return "[ code : " + this.code + " | name : " + this.name + " ]";
        
    }
    
}