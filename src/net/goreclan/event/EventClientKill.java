/**
 * Event object for Client Kill
 * 
 * @author      Daniele Pantaleone
 * @version     1.0
 * @copyright   Daniele Pantaleone, 02 July, 2012
 * @package     net.goreclan.event
 **/

package net.goreclan.event;

import net.goreclan.domain.Client;
import net.goreclan.iourt42.Mod;

public class EventClientKill extends Event {

    private Client client = null;
    private Client target = null;
    private Mod mod = null;
    
    /**
     * Object constructor
     * 
     * @return void
     * @author Daniele Pantaleone 
     **/
    public EventClientKill(Client client, Client target, Mod mod) {
        super("EVT_CLIENT_KILL");
        this.client = client;
        this.target = target;
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
     * @return Client
     * @author Daniele Pantaleone
     */
    public Client getTarget() {
        return this.target;
    }
    
    
    /**
     * @return Mod
     * @author Daniele Pantaleone
     */
    public Mod getMod() {
        return this.mod;
    }

}