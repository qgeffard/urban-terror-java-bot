/**
 * Event object for Client Suicide.
 * 
 * @author      Daniele Pantaleone
 * @version     1.1
 * @copyright   Daniele Pantaleone, 26 July, 2012
 * @package     net.goreclan.event
 **/

package net.goreclan.event;

import net.goreclan.domain.Client;
import net.goreclan.iourt42.Mod;

public class EventClientSuicide extends Event {

    private final Client client;
    private final Mod mod;
    
    /**
     * Object constructor.
     * 
     * @author Daniele Pantaleone
     * @param  client The client who committed suicide
     * @param  mod The UrT MOD for this suicide 
     * @return EventClientSuicide
     **/
    public EventClientSuicide(Client client, Mod mod) {
        super(EventType.EVT_CLIENT_SUICIDE);
        this.client = client;
        this.mod = mod;
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
     * @return Mod
     **/
    public Mod getMod() {
        return this.mod;
    }

}