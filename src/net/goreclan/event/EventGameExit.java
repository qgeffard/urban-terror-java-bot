/**
 * Event object for Game Exit
 * 
 * @author      Daniele Pantaleone
 * @version     1.0
 * @copyright   Daniele Pantaleone, 02 July, 2012
 * @package     net.goreclan.event
 **/

package net.goreclan.event;


public class EventGameExit extends Event {

    /**
     * Object constructor
     * 
     * @return void
     * @author Daniele Pantaleone 
     **/
    public EventGameExit() {
        super(EventType.EVT_GAME_EXIT);
    }
    
}