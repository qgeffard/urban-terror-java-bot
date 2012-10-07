/**
 * Event object for Client Callvote.
 * 
 * @author      Daniele Pantaleone
 * @version     1.1
 * @copyright   Daniele Pantaleone, 8 September, 2012
 * @package     net.goreclan.event
 **/

package net.goreclan.event;

import net.goreclan.domain.Client;

public class EventClientCallvote extends Event {

    private final Client client;
    private final String callvoteType;
    private final String data;
    
    /**
     * Object constructor.
     * 
     * @author Daniele Pantaleone
     * @param  client The client who issued the votation
     * @param  callvoteType The type of the callvote
     * @param  data A string containing votation extra data
     * @return EventClientCallvote 
     **/
    public EventClientCallvote(Client client, String callvoteType, String data) {  
        super(EventType.EVT_CLIENT_CALLVOTE);
        this.client = client;
        this.callvoteType = callvoteType;
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
    public String getCallvoteType() {
        return this.callvoteType;
    }
   
    
    /**
     * @author Daniele Pantaleone
     * @return String
     **/
    public String getData() {
        return this.data;
    }
    
}