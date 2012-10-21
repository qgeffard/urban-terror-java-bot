/**
 * Event template class. All the Event classes MUST extend this one.
 * 
 * @author      Daniele Pantaleone, Mathias Van Malderen
 * @version     1.1
 * @copyright   Daniele Pantaleone, Mathias Van Malderen, 02 July, 2012
 * @package     net.goreclan.event
 **/

package net.goreclan.event;

import java.util.Date;

public abstract class Event {
    
    private final EventType type;
    private final Date time;
    
    /**
     * Object constructor.
     * 
     * @author Daniele Pantaleone 
     * @param  type The event type
     **/
    public Event(EventType type) {
        this.type = type;
        this.time = new Date();
    }
    
    
    /**
     * @author Mathias Van Malderen
     * @return EventType
     **/
    public EventType getType() {
        return this.type;
    }
    
    /**
     * @author Daniele Pantaleone
     * @return Date
     **/
    public Date getTime() {
        return this.time;
    }
    
    
}