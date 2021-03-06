/**
 * This Enum handle the Urban Terror MOD (Method of deaths)
 * 
 * @author      Daniele Pantaleone, Mathias Van Malderen
 * @version     1.1.1
 * @copyright   Daniele Pantaleone, 06 July, 2012
 * @package     net.goreclan.iourt42
 **/

package net.goreclan.iourt42;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Mod {
    
    MOD_WATER(1,null),
    MOD_LAVA(3,null),
    MOD_TELEFRAG(5,null),
    MOD_FALLING(6,null),
    MOD_SUICIDE(7,null),
    MOD_TRIGGER_HURT(9,null),
    MOD_CHANGE_TEAM(10,null),
    UT_MOD_KNIFE(12,1),
    UT_MOD_KNIFE_THROWN(13,22),
    UT_MOD_BERETTA(14,2),
    UT_MOD_DEAGLE(15,3),
    UT_MOD_SPAS(16,4),
    UT_MOD_UMP45(17,6),
    UT_MOD_MP5K(18,5),
    UT_MOD_LR300(19,8),
    UT_MOD_G36(20,9),
    UT_MOD_PSG1(21,10),
    UT_MOD_HK69(22,null),
    UT_MOD_BLED(23,null),
    UT_MOD_KICKED(24,null),
    UT_MOD_HEGRENADE(25,21),
    UT_MOD_SR8(27,14),
    UT_MOD_AK103(29,15),
    UT_MOD_SPLODED(30,null),
    UT_MOD_SLAPPED(31,null),
    UT_MOD_SMITED(32,null),
    UT_MOD_BOMBED(33,null),
    UT_MOD_NUKED(34,null),
    UT_MOD_NEGEV(35,17),
    UT_MOD_HK69_HIT(36,null),
    UT_MOD_M4(37,19),
    UT_MOD_FLAG(38,null),
    UT_MOD_GOOMBA(39,null);
    
    
    private static final Map<Integer, Mod> modByKillMode = new HashMap<Integer, Mod>();
    private static final Map<Integer, Mod> modByHitMode  = new HashMap<Integer, Mod>();
    private Integer killmode = null;
    private Integer hitmode = null;
    
    
    static {
        for (Mod m : EnumSet.allOf(Mod.class)) {
            Mod.modByKillMode.put(m.killmode, m);
            Mod.modByHitMode.put(m.hitmode, m);
        }
    }
    
    
    /**
     * Object constructor.
     * 
     * @author Daniele Pantaleone
     * @return Mod
     **/
    private Mod(Integer killmode, Integer hitmode) {
        this.killmode = killmode;
        this.hitmode = hitmode;
    }
    
    
    /**
     * @author Daniele Pantaleone
     * @return Integer
     **/
    public Integer getKillMode() {
        return killmode;
    }
    
    
    /**
     * @author Daniele Pantaleone
     * @return Integer
     **/
    public Integer getHitMode() {
        return hitmode;
    }
    
    
    /**
     * Return a Mod object by matching the Kill Mode.
     * 
     * @author Daniele Pantaleone
     * @param  killmode The kill mode code
     * @throws IndexOutOfBoundException 
     * @return Mod
     **/
    public static Mod getByKillMode(Integer killmode) throws IndexOutOfBoundsException {
        
    	if (!modByKillMode.containsKey(killmode)) 
        	throw new IndexOutOfBoundsException("Unable to match kill mode code: " + killmode + ".");
        
    	return modByKillMode.get(killmode);
    }
    
    
    /**
     * Return a Mod object by matching the Hit Mode.
     * 
     * @author Daniele Pantaleone
     * @param  hitmode The hit mode code
     * @throws IndexOutOfBoundException 
     * @return Mod
     */
    public static Mod getByHitMode(Integer hitmode) throws IndexOutOfBoundsException {
        
    	if (!modByHitMode.containsKey(hitmode)) 
        	throw new IndexOutOfBoundsException("Unable to match hit mode code: " + hitmode + ".");
     
        return modByHitMode.get(hitmode);
    }
    
}