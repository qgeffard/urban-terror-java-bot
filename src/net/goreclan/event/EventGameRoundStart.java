/**
 * Event object for InitRound
 * 
 * @author      Daniele Pantaleone
 * @version     1.0
 * @copyright   Daniele Pantaleone, 02 July, 2012
 * @package     net.goreclan.event
 **/

package net.goreclan.event;

public class EventGameRoundStart extends Event {
    
    /**
     * Object constructor
     * 
     * @return void
     * @author Daniele Pantaleone 
     **/
    public EventGameRoundStart(String infostring) {
        super(EventType.EVT_GAME_ROUND_START);
    }

}
 