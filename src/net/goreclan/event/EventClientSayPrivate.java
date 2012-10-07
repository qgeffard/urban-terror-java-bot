/**
 * Event object for Client SayTell.
 * 
 * @author      Daniele Pantaleone
 * @version     1.1
 * @copyright   Daniele Pantaleone, 15 July, 2012
 * @package     net.goreclan.event
 **/

package net.goreclan.event;

import net.goreclan.domain.Client;

public class EventClientSayPrivate extends Event {

    private final Client client;
    private final Client target;
    private final String message;
    
    /**
     * Object constructor.
     * 
     * @author Daniele Pantaleone 
     * @param  client The client who said something
     * @param  target The client on which the /tell command has been performed
     * @param  message The sentence said
     * @return EventClientSayPrivate
     **/
    public EventClientSayPrivate(Client client, Client target, String message) {
        super(EventType.EVT_CLIENT_SAY_PRIVATE);
        this.client = client;
        this.target = target;
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
     * @return Client
     **/
    public Client getTarget() {
        return this.target;
    }
    
    
    /**
     * @author Daniele Pantaleone
     * @return String
     */
    public String getMessage() {
        return this.message;
    }
    
}