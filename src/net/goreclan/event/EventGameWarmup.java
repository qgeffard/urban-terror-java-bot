/**
 * Event object for Game Warmup.
 * 
 * @author      Daniele Pantaleone
 * @version     1.1
 * @copyright   Daniele Pantaleone, 02 July, 2012
 * @package     net.goreclan.event
 **/

package net.goreclan.event;

public class EventGameWarmup extends Event {

    /**
     * Object constructor.
     * 
     * @author Daniele Pantaleone 
     * @return EventGameWarmup
     **/
    public EventGameWarmup() {
        super(EventType.EVT_GAME_WARMUP);
    }
    
}