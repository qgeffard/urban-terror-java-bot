/**
 * This Enum handle the Urban Terror MOD (Method of deaths)
 * 
 * @author      Daniele Pantaleone, Mathias Van Malderen
 * @version     1.1
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
     * Object constructor
     * 
     * @return Mod
     * @author Daniele Pantaleone 
     **/
    private Mod(Integer killmode, Integer hitmode) {
        this.killmode = killmode;
        this.hitmode = hitmode;
    }
    
    
    /**
     * @return Integer
     * @author Daniele Pantaleone
     */
    public Integer getKillMode() {
        return killmode;
    }
    
    
    /**
     * @return Integer
     * @author Daniele Pantaleone
     */
    public Integer getHitMode() {
        return hitmode;
    }
    
    
    /**
     * Return a Mod object by matching the Kill Mode
     * 
     * @return Mod
     * @author Daniele Pantaleone
     * @throws IndexOutOfBoundException 
     */
    public static Mod getByKillMode(Integer killmode) throws IndexOutOfBoundsException {
        
    	if (!modByKillMode.containsKey(killmode)) 
        	throw new IndexOutOfBoundsException(String.format("Invalid kill mode index: %d.", killmode));
        
    	return modByKillMode.get(killmode);
    }
    
    
    /**
     * Return a Mod object by matching the Hit Mode
     * 
     * @return Mod
     * @author Daniele Pantaleone
     * @throws IndexOutOfBoundException 
     */
    public static Mod getByHitMode(Integer hitmode) throws IndexOutOfBoundsException {
        
    	if (!modByHitMode.containsKey(hitmode)) 
        	throw new IndexOutOfBoundsException(String.format("Invalid hit mode index: %d.", hitmode));
     
        return modByHitMode.get(hitmode);
    }
    
}