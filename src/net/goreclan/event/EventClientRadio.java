/**
 * Event object for Client Radio
 * 
 * @author      Daniele Pantaleone
 * @version     1.0
 * @copyright   Daniele Pantaleone, 8 September, 2012
 * @package     net.goreclan.event
 **/

package net.goreclan.event;

import net.goreclan.domain.Client;

public class EventClientRadio extends Event {

    private Client client = null;
    private Integer msg_group = null;
    private Integer msg_id = null;
    private String location = null;
    private String message = null;
    
    /**
     * Object constructor
     * 
     * @return void
     * @author Daniele Pantaleone 
     **/
    public EventClientRadio(Client client, Integer msg_group, Integer msg_id, String location, String message) {  
        super(EventType.EVT_CLIENT_RADIO);
        this.client = client;
        this.msg_group = msg_group;
        this.msg_id = msg_id;
        this.location = location;
        this.message = message;
    }
    
    
    /**
     * @return Client
     * @author Daniele Pantaleone
     */
    public Client getClient() {
        return this.client;
    }
    
    
    /**
     * @return Integer
     * @author Daniele Pantaleone
     */
    public Integer getMsgGroup() {
        return this.msg_group;
    }
    
    
    /**
     * @return Integer
     * @author Daniele Pantaleone
     */
    public Integer getMsgId() {
        return this.msg_id;
    }
   
    
    /**
     * @return String
     * @author Daniele Pantaleone
     */
    public String getLocation() {
        return this.location;
    }
    
    
    /**
     * @return String
     * @author Daniele Pantaleone
     */
    public String getMessage() {
        return this.message;
    }
    
}