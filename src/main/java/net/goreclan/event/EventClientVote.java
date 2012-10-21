/**
 * Event object for Client Vote.
 * 
 * @author      Daniele Pantaleone
 * @version     1.1
 * @copyright   Daniele Pantaleone, 8 September, 2012
 * @package     net.goreclan.event
 **/

package net.goreclan.event;

import net.goreclan.domain.Client;

public class EventClientVote extends Event {

    private final Client client;
    private final String data;
    
    /**
     * Object constructor.
     * 
     * @author Daniele Pantaleone 
     * @param  client The client who voted
     * @param  data The vote string (1-2)
     * @return EventClientVote
     **/
    public EventClientVote(Client client, String data) {  
        super(EventType.EVT_CLIENT_VOTE);
        this.client = client;
        this.data = data;
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
    public String getData() {
        return this.data;
    }
    
}