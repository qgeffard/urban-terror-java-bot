/**
 * Event object for Client Disconnect.
 * 
 * @author      Daniele Pantaleone
 * @version     1.1
 * @copyright   Daniele Pantaleone, 02 July, 2012
 * @package     net.goreclan.event
 **/

package net.goreclan.event;

import net.goreclan.domain.Client;

public class EventClientDisconnect extends Event {

    private final Client client;
    
    /**
     * Object constructor.
     * 
     * @author Daniele Pantaleone
     * @param  client The disconnecting client
     * @return EventClientDisconnect  
     **/
    public EventClientDisconnect(Client client) {
        super(EventType.EVT_CLIENT_DISCONNECT);
        this.client = client;
    }
    
    
    /**
     * @author Daniele Pantaleone
     * @return Client
     **/
    public Client getClient() {
        return this.client;
    }
    
}