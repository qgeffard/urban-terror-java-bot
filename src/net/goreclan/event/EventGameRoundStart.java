/**
 * Event object for InitRound.
 * 
 * @author      Daniele Pantaleone
 * @version     1.1
 * @copyright   Daniele Pantaleone, 02 July, 2012
 * @package     net.goreclan.event
 **/

package net.goreclan.event;

public class EventGameRoundStart extends Event {
    
	public final String infostring;
	
    /**
     * Object constructor.
     * 
     * @author Daniele Pantaleone 
     * @param  infostring The InitRound infostring
     * @return EventGameRoundStart
     **/
    public EventGameRoundStart(String infostring) {
        super(EventType.EVT_GAME_ROUND_START);
        this.infostring = infostring;
    }
    
    /**
     * @author Daniele Pantaleone
     * @return String
     **/
    public String getInfostring() {
    	return this.infostring;
    }

}
 