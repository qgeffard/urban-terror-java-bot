/**
 * Event object for Client Say.
 * 
 * @author      Daniele Pantaleone
 * @version     1.1
 * @copyright   Daniele Pantaleone, 02 July, 2012
 * @package     net.goreclan.event
 **/

package net.goreclan.event;

import net.goreclan.domain.Client;

public class EventClientSay extends Event {

    private final Client client;
    private final String message;
    
    /**
     * Object constructor.
     * 
     * @author Daniele Pantaleone 
     * @param  client The client who said something
     * @param  message The sentence said
     * @return EventClientSay
     **/
    public EventClientSay(Client client, Client target, String message) {
        super(EventType.EVT_CLIENT_SAY);
        this.client = client;
        this.message = message;
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
    public String getMessage() {
        return this.message;
    }
    
}