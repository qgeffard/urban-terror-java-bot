/**
 * Event object for Client Suicide
 * 
 * @author      Daniele Pantaleone
 * @version     1.0
 * @copyright   Daniele Pantaleone, 26 July, 2012
 * @package     net.goreclan.event
 **/

package net.goreclan.event;

import net.goreclan.domain.Client;
import net.goreclan.iourt42.Mod;

public class EventClientSuicide extends Event {

    private Client client = null;
    private Mod mod = null;
    
    /**
     * Object constructor
     * 
     * @return void
     * @author Daniele Pantaleone 
     **/
    public EventClientSuicide(Client client, Mod mod) {
        super(EventType.EVT_CLIENT_SUICIDE);
        this.client = client;
        this.mod = mod;
    }
    
    
    /**
     * @return Client
     * @author Daniele Pantaleone
     */
    public Client getClient() {
        return this.client;
    }
    
    
    /**
     * @return Mod
     * @author Daniele Pantaleone
     */
    public Mod getMod() {
        return this.mod;
    }

}