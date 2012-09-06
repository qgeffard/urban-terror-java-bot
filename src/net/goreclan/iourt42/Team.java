/**
 * This Enum handle the Urban Terror teams
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

public enum Team {
    
    TEAM_RED(1,"TEAM_RED"),
    TEAM_BLUE(2,"TEAM_BLUE"),
    TEAM_SPEC(3,"TEAM_SPEC"),
    TEAM_FREE(-1,"TEAM_FREE");
    
    private static final Map<Integer, Team> teamByCode = new HashMap<Integer, Team>();
    private static final Map<String, Team> teamByName = new HashMap<String, Team>();
    private Integer code = null;
    private String  name = null;
    
    
    static {
        for (Team t : EnumSet.allOf(Team.class)) {
            Team.teamByCode.put(t.code, t);
            Team.teamByName.put(t.name, t);
        }
    }
    
    
    /**
     * Object constructor
     * 
     * @return Team
     * @author Daniele Pantaleone 
     **/
    private Team(Integer code, String name) {
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
     * Return a Team object by matching the Team code
     * 
     * @return Team
     * @author Daniele Pantaleone
     * @throws IndexOutOfBoundException 
     */
    public static Team getByCode(Integer code) throws IndexOutOfBoundsException {
       
    	if (!teamByCode.containsKey(code)) 
        	throw new IndexOutOfBoundsException(String.format("Invalid team code: %d.", code));
        
        return teamByCode.get(code);
    }
    
    
    /**
     * Return a Team object by matching the Team name
     * 
     * @return Team
     * @author Daniele Pantaleone
     * @throws IndexOutOfBoundException 
     */
    public static Team getByName(String name) throws IndexOutOfBoundsException {
    	
    	switch(name.toUpperCase()) {
    		case 	"RED":  	  name = "TEAM_RED";  break;
    		case    "R":		  name = "TEAM_RED";  break;
    		case 	"BLUE": 	  name = "TEAM_BLUE"; break;
    		case    "B":		  name = "TEAM_BLUE"; break;
    		case 	"SPECTATOR":  name = "TEAM_SPEC"; break;
    		case 	"SPEC":		  name = "TEAM_SPEC"; break;
    		case    "S":		  name = "TEAM_SPEC"; break;
    		case	"FREE":		  name = "TEAM_FREE"; break;
    	}	
    	
        if (!teamByName.containsKey(name.toUpperCase())) 
        	throw new IndexOutOfBoundsException(String.format("Invalid team name: %s.", name.toUpperCase()));
        
        return teamByName.get(name.toUpperCase());
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