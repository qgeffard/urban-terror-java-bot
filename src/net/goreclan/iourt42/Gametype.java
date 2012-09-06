/**
 * This Enum handle the Urban Terror gametypes
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

public enum Gametype {
    
    GT_FFA(0,"GT_FFA"),
    GT_LMS(1,"GT_LMS"),
    GT_TDM(3,"GT_TDM"),
    GT_TS(4,"GT_TS"),
    GT_FTL(5,"GT_FTL"),
    GT_CAH(6,"GT_CAH"),
    GT_CTF(7,"GT_CTF"),
    GT_BOMB(8,"GT_BOMB");
    
    private static final Map<Integer, Gametype> gametypeByCode = new HashMap<Integer, Gametype>();
    private static final Map<String, Gametype> gametypeByName = new HashMap<String, Gametype>();
    private Integer code = null;
    private String  name = null;
    
    
    static {
        for (Gametype g : EnumSet.allOf(Gametype.class)) {
            Gametype.gametypeByCode.put(g.code, g);
            Gametype.gametypeByName.put(g.name, g);
        }
    }
    
    
    /**
     * Object constructor
     * 
     * @return Gametype
     * @author Daniele Pantaleone 
     **/
    private Gametype(Integer code, String name) {
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
     * Return a Gametype object by matching the Gametype code
     * 
     * @return Gametype
     * @author Daniele Pantaleone
     * @throws IndexOutOfBoundException 
     */
    public static Gametype getByCode(Integer code) throws IndexOutOfBoundsException {
        
    	if (!gametypeByCode.containsKey(code)) 
        	throw new IndexOutOfBoundsException(String.format("Invalid gametype code: %d.", code));
        
    	return gametypeByCode.get(code);
    }
    
    
    /**
     * Return a Gametype object by matching the Gametype name
     * 
     * @return Gametype
     * @author Daniele Pantaleone
     * @throws IndexOutOfBoundException 
     */
    public static Gametype getByName(String name) throws IndexOutOfBoundsException {
        
    	switch(name.toUpperCase()) {
    		case "FFA":	  name = "GT_FFA";  break;
    		case "LMS":	  name = "GT_LMS";  break;
    		case "TDM":	  name = "GT_TDM";  break;
    		case "TS":	  name = "GT_TS";   break;
    		case "FTL":	  name = "GT_FTL";  break;
    		case "CAH":   name = "GT_CAH";  break;
    		case "CTF":	  name = "GT_CTF";  break;
    		case "BOMB":  name = "GT_BOMB"; break;
    	}
    	
    	if (!gametypeByName.containsKey(name.toUpperCase())) 
        	throw new IndexOutOfBoundsException(String.format("Invalid gametype name: %s.", name.toUpperCase()));
        
    	return gametypeByName.get(name.toUpperCase());
    }
    
    
    /**
     * String object representation
     * 
     * @return String
     * @author Daniele Pantaleone
     **/
    public String toString() {
        
    	// Returning a String object representation
        return String.format("[ code : %d | name : %s ]", code, name);
        
    }
    
}