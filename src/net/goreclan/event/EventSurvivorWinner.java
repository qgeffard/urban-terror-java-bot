/**
 * Event object for Survivor Winner.
 * 
 * @author      Daniele Pantaleone
 * @version     1.2
 * @copyright   Daniele Pantaleone, 15 July, 2012
 * @package     net.goreclan.event
 **/

package net.goreclan.event;

import net.goreclan.iourt42.Team;

public class EventSurvivorWinner extends Event {

    private final Team team;
    
    /**
     * Object constructor.
     * 
     * @author Daniele Pantaleone
     * @param  team The team who won the round
     * @return EventSurvivorWinner
     **/
    public EventSurvivorWinner(Team team) {
        super(EventType.EVT_SURVIVOR_WINNER);
        this.team = team;
    }
    
    
    /**
     * @author Daniele Pantaleone
     * @return Team
     **/
    public Team getTeam() {
        return this.team;
    }
    
}
 