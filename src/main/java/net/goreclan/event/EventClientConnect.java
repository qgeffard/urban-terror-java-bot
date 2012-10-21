/**
 * Event object for Client Connect.
 * 
 * @author      Daniele Pantaleone
 * @version     1.0
 * @copyright   Daniele Pantaleone, 02 July, 2012
 * @package     net.goreclan.event
 **/

package net.goreclan.event;

import net.goreclan.domain.Client;

public class EventClientConnect extends Event {

    private final Client client;
    
    /**
     * Object constructor.
     * 
     * @author Daniele Pantaleone
     * @param  client The connecting client object
     * @return EventClientConnect
     **/
    public EventClientConnect(Client client) {  
        super(EventType.EVT_CLIENT_CONNECT);
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