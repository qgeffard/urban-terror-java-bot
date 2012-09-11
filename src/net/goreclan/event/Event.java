/**
 * Event template class. All the Event classes must extend this one.
 * 
 * @author      Daniele Pantaleone
 * @version     1.0
 * @copyright   Daniele Pantaleone, 02 July, 2012
 * @package     net.goreclan.event
 **/

package net.goreclan.event;

import java.util.Date;

public abstract class Event {
    
    private EventType name = null;
    private Date time = null;
    
    /**
     * Object constructor
     * 
     * @return void
     * @author Daniele Pantaleone 
     **/
    public Event(EventType name) {
        this.name = name;
        this.time = new Date();
    }
    
    
    /**
     * @return String
     * @author Daniele Pantaleone
     **/
    public EventType getName() {
        return this.name;
    }
    
    /**
     * @return Date
     * @author Daniele Pantaleone
     **/
    public Date getTime() {
        return this.time;
    }
    
    
}