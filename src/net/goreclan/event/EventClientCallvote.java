/**
 * Event object for Client Callvote
 * 
 * @author      Daniele Pantaleone
 * @version     1.0
 * @copyright   Daniele Pantaleone, 8 September, 2012
 * @package     net.goreclan.event
 **/

package net.goreclan.event;

import net.goreclan.domain.Client;

public class EventClientCallvote extends Event {

    private Client client = null;
    private String type = null;
    private String data = null;
    
    /**
     * Object constructor
     * 
     * @return void
     * @author Daniele Pantaleone 
     **/
    public EventClientCallvote(Client client, String type, String data) {  
        super("EVT_CLIENT_CALLVOTE");
        this.client = client;
        this.type = type;
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
    public String getType() {
        return this.type;
    }
   
    
    /**
     * @return String
     * @author Daniele Pantaleone
     */
    public String getData() {
        return this.data;
    }
    
}