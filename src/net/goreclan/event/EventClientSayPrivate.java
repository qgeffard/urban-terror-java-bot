/**
 * Event object for Client SayTell
 * 
 * @author      Daniele Pantaleone
 * @version     1.0
 * @copyright   Daniele Pantaleone, 15 July, 2012
 * @package     net.goreclan.event
 **/

package net.goreclan.event;

import net.goreclan.domain.Client;

public class EventClientSayPrivate extends Event {

    private Client client = null;
    private Client target = null;
    private String sentence = null;
    
    /**
     * Object constructor
     * 
     * @return void
     * @author Daniele Pantaleone 
     **/
    public EventClientSayPrivate(Client client, Client target, String sentence) {
        super(EventType.EVT_CLIENT_SAY_PRIVATE);
        this.client = client;
        this.target = target;
        this.sentence = sentence;
    }
    
    
    /**
     * @return Client
     * @author Daniele Pantaleone
     */
    public Client getClient() {
        return this.client;
    }
    
    
    /**
     * @return Client
     * @author Daniele Pantaleone
     */
    public Client getTarget() {
        return this.target;
    }
    
    
    /**
     * @return String
     * @author Daniele Pantaleone
     */
    public String getSentence() {
        return this.sentence;
    }
    
}