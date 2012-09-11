/**
 * Event object for Client SayTeam
 * 
 * @author      Daniele Pantaleone
 * @version     1.0
 * @copyright   Daniele Pantaleone, 02 July, 2012
 * @package     net.goreclan.event
 **/

package net.goreclan.event;

import net.goreclan.domain.Client;

public class EventClientSayTeam extends Event {

    private Client client = null;
    private String sentence = null;
    
    /**
     * Object constructor
     * 
     * @return void
     * @author Daniele Pantaleone 
     **/
    public EventClientSayTeam(Client client, Client target, String sentence) {
        super(EventType.EVT_CLIENT_SAY_TEAM);
        this.client = client;
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
     * @return String
     * @author Daniele Pantaleone
     */
    public String getSentence() {
        return this.sentence;
    }
    
}