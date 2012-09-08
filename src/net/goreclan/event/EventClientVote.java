/**
 * Event object for Client Vote
 * 
 * @author      Daniele Pantaleone
 * @version     1.0
 * @copyright   Daniele Pantaleone, 8 September, 2012
 * @package     net.goreclan.event
 **/

package net.goreclan.event;

import net.goreclan.domain.Client;

public class EventClientVote extends Event {

    private Client client = null;
    private String data = null;
    
    /**
     * Object constructor
     * 
     * @return void
     * @author Daniele Pantaleone 
     **/
    public EventClientVote(Client client, String data) {  
        super("EVT_CLIENT_VOTE");
        this.client = client;
        this.data = data;
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
    public String getData() {
        return this.data;
    }
    
}