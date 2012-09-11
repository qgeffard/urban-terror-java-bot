/**
 * Event object for Client Gear Change
 * 
 * @author      Daniele Pantaleone
 * @version     1.0
 * @copyright   Daniele Pantaleone, 06 July, 2012
 * @package     net.goreclan.event
 **/

package net.goreclan.event;

import net.goreclan.domain.Client;

public class EventClientGearChange extends Event {

    private Client client = null;
    private String before = null;
    private String after = null;
    
    
    /**
     * Object constructor
     * 
     * @return void
     * @author Daniele Pantaleone 
     **/
    public EventClientGearChange(Client client, String before, String after) {
        super(EventType.EVT_CLIENT_GEAR_CHANGE);
        this.client = client;
        this.before = before;
        this.after = after;
    }
    
    
    /**
     * @return Client
     * @author Daniele Pantaleone
     */
    public Client getClient() {
        return this.client;
    }
    
    
    /**
     * @return String
     * @author Daniele Pantaleone
     */
    public String getBefore() {
        return this.before;
    }
    
    
    /**
     * @return String
     * @author Daniele Pantaleone
     */
    public String getAfter() {
        return this.after;
    }
    
}