/**
 * Event object for Client Name Change.
 * 
 * @author      Daniele Pantaleone
 * @version     1.1
 * @copyright   Daniele Pantaleone, 06 July, 2012
 * @package     net.goreclan.event
 **/

package net.goreclan.event;

import net.goreclan.domain.Client;

public class EventClientNameChange extends Event {

    private final Client client;
    private final String before;
    private final String after;
    
    
    /**
     * Object constructor.
     * 
     * @author Daniele Pantaleone 
     * @param  client The client who changed nickname
     * @param  before The old nickname
     * @param  after The new nickname
     * @return EventClientNameChange
     **/
    public EventClientNameChange(Client client, String before, String after) {
        super(EventType.EVT_CLIENT_NAME_CHANGE);
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