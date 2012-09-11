/**
 * Event object for Survivor Winner
 * 
 * @author      Daniele Pantaleone
 * @version     1.1
 * @copyright   Daniele Pantaleone, 15 July, 2012
 * @package     net.goreclan.event
 **/

package net.goreclan.event;

import net.goreclan.iourt42.Team;

public class EventSurvivorWinner extends Event {

    private Team team = null;
    
    /**
     * Object constructor
     * 
     * @return void
     * @author Daniele Pantaleone 
     **/
    public EventSurvivorWinner(Team team) {
        super(EventType.EVT_SURVIVOR_WINNER);
        this.team = team;
    }
    
    
    /**
     * @return Team
     * @author Daniele Pantaleone
     */
    public Team getTeam() {
        return this.team;
    }
    
}
 