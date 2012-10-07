/**
 * Event object for Client Gear Change.
 * 
 * @author      Daniele Pantaleone
 * @version     1.0
 * @copyright   Daniele Pantaleone, 06 July, 2012
 * @package     net.goreclan.event
 **/

package net.goreclan.event;

import net.goreclan.domain.Client;

public class EventClientGearChange extends Event {

    private final Client client;
    private final String before;
    private final String after;
    
    
    /**
     * Object constructor.
     * 
     * @author Daniele Pantaleone
     * @param  client The client who changed the gear
     * @param  before The old gear string
     * @param  after  The new gear string
     * @return EventClientGearChange
     **/
    public EventClientGearChange(Client client, String before, String after) {
        super(EventType.EVT_CLIENT_GEAR_CHANGE);
        this.client = client;
        this.before = before;
        this.after = after;
    }
    
    
    /**
     * @author Daniele Pantaleone
     * @return Client
     **/
    public Client getClient() {
        return this.client;
    }
    
    
    /**
     * @author Daniele Pantaleone
     * @return String
     **/
    public String getBefore() {
        return this.before;
    }
    
    
    /**
     * @author Daniele Pantaleone
     * @return String
     **/
    public String getAfter() {
        return this.after;
    }
    
}