/**
 * This Enum handle the Urban Terror teams.
 * 
 * @author      Daniele Pantaleone
 * @version     1.2.1
 * @copyright   Daniele Pantaleone, 04 October, 2012
 * @package     net.goreclan.iourt42
 **/

package net.goreclan.iourt42;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Team {
    
    TEAM_RED  (1,  "RED"),
    TEAM_BLUE (2,  "BLUE"),
    TEAM_SPEC (3,  "SPECTATOR"),
    TEAM_FREE (-1, "FREE");
    
    private static final Map<Integer, Team> teamByCode = new HashMap<Integer, Team>();
    private static final Map<String, Team> teamByName = new HashMap<String, Team>();
    private int code;
    private String name;
    
    
    static {
        for (Team t : EnumSet.allOf(Team.class)) {
            Team.teamByCode.put(t.code, t);
            Team.teamByName.put(t.name, t);
        }
    }
    
    
    /**
     * Object constructor.
     * 
     * @author Daniele Pantaleone
     * @param  code The UrbanTerror 4.2 team code
     * @param  name A string representing the team name
     * @return Team
     **/
    private Team(int code, String name) {
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
     * Return a Team object by matching the Team code.
     * 
     * @author Daniele Pantaleone
     * @param  code The Urban Terror 4.2 team code
     * @throws IndexOutOfBoundException 
     * @return Team
     */
    public static Team getByCode(Integer code) throws IndexOutOfBoundsException {
       
    	if (!teamByCode.containsKey(code)) 
        	throw new IndexOutOfBoundsException("Unable to match team code: " + code + ".");
        
        return teamByCode.get(code);
    }
    
    
    /**
     * Return a Team object by matching the Team name.
     * 
     * @author Daniele Pantaleone
     * @param  name A string representing the team name
     * @throws IndexOutOfBoundException 
     * @return Team
     */
    public static Team getByName(String name) throws IndexOutOfBoundsException {
    	
    	name = name.toUpperCase();
    	
    	switch(name) {
    		case	"R":	  name = "TEAM_RED";  break;
    		case	"B":	  name = "TEAM_BLUE"; break;
    		case	"S":	  name = "TEAM_SPEC"; break;
    		case	"SPEC":	  name = "TEAM_SPEC"; break;
    	}	
    	
        if (!teamByName.containsKey(name)) 
        	throw new IndexOutOfBoundsException("Unable to match team name: " + name + ".");
        
        return teamByName.get(name);
    }
    
    
    /**
     * String object representation.
     * 
     * @return String
     * @author Daniele Pantaleone
     **/
    public String toString() {
        
    	// Returning a String object representation
    	return "[ code : " + this.code + " | name : " + this.name + " ]";
    }
    
}