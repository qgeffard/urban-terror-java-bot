/**
 * Event object for Client Hit
 * 
 * @author      Daniele Pantaleone
 * @version     1.1
 * @copyright   Daniele Pantaleone, 15 July, 2012
 * @package     net.goreclan.event
 **/

package net.goreclan.event;

import net.goreclan.domain.Client;
import net.goreclan.iourt42.Hitlocation;
import net.goreclan.iourt42.Mod;

public class EventClientHit extends Event {

    private Client client = null;
    private Client target = null;
    private Hitlocation hitlocation = null;
    private Mod mod = null;
    
    /**
     * Object constructor
     * 
     * @return void
     * @author Daniele Pantaleone 
     **/
    public EventClientHit(Client client, Client target, Mod mod, Hitlocation hitlocation) {
        super("EVT_CLIENT_HIT");
        this.client = client;
        this.target = target;
        this.mod = mod;
        this.hitlocation = hitlocation;
        
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
     * @return Hitlocation
     * @author Daniele Pantaleone
     */
    public Hitlocation getHitLocation() {
        return this.hitlocation;
    }
    
    
    /**
     * @return Mod
     * @author Daniele Pantaleone
     */
    public Mod getMod() {
        return this.mod;
    }
    
}