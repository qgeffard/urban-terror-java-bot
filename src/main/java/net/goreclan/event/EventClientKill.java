/**
 * Event object for Client Kill.
 * 
 * @author      Daniele Pantaleone
 * @version     1.1
 * @copyright   Daniele Pantaleone, 02 July, 2012
 * @package     net.goreclan.event
 **/

package net.goreclan.event;

import net.goreclan.domain.Client;
import net.goreclan.iourt42.Mod;

public class EventClientKill extends Event {

    private final Client client;
    private final Client target;
    private final Mod mod;
    
    /**
     * Object constructor.
     * 
     * @author Daniele Pantaleone
     * @param  client The client who made the kill
     * @param  target The client who got kicked
     * @param  mod The UrT MOD for this kill
     * @return EventClientKill 
     **/
    public EventClientKill(Client client, Client target, Mod mod) {
        super(EventType.EVT_CLIENT_KILL);
        this.client = client;
        this.target = target;
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
     * @return Client
     **/
    public Client getTarget() {
        return this.target;
    }
    
    
    /**
     * @author Daniele Pantaleone
     * @return Mod
     **/
    public Mod getMod() {
        return this.mod;
    }

}