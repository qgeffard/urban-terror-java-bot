/**
 * Event object for Client Radio.
 * 
 * @author      Daniele Pantaleone
 * @version     1.1
 * @copyright   Daniele Pantaleone, 8 September, 2012
 * @package     net.goreclan.event
 **/

package net.goreclan.event;

import net.goreclan.domain.Client;

public class EventClientRadio extends Event {

    private final Client client;
    private final int msg_group;
    private final int msg_id;
    private final String location;
    private final String message;
    
    /**
     * Object constructor.
     * 
     * @author Daniele Pantaleone 
     * @param  client The client who issued the radio command
     * @param  msg_group The message group
     * @param  msg_id The message id
     * @param  location The string location where the radio command has been issued
     * @param  message The message attached to the radio command
     * @return EventClientRadio
     **/
    public EventClientRadio(Client client, int msg_group, int msg_id, String location, String message) {  
        super(EventType.EVT_CLIENT_RADIO);
        this.client = client;
        this.msg_group = msg_group;
        this.msg_id = msg_id;
        this.location = location;
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
     * @return int
     **/
    public int getMsgGroup() {
        return this.msg_group;
    }
    
    
    /**
     * @author Daniele Pantaleone
     * @return int
     **/
    public Integer getMsgId() {
        return this.msg_id;
    }
   
    
    /**
     * @author Daniele Pantaleone
     * @return String
     **/
    public String getLocation() {
        return this.location;
    }
    
    
    /**
     * @author Daniele Pantaleone
     * @return String
     **/
    public String getMessage() {
        return this.message;
    }
    
}