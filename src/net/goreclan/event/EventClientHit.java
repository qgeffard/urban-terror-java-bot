/**
 * Event object for Client Hit
 * 
 * @author      Daniele Pantaleone
 * @version     1.2
 * @copyright   Daniele Pantaleone, 15 July, 2012
 * @package     net.goreclan.event
 **/

package net.goreclan.event;

import net.goreclan.domain.Client;
import net.goreclan.iourt42.Hitlocation;
import net.goreclan.iourt42.Mod;

public class EventClientHit extends Event {

    private final Client client;
    private final Client target;
    private final Hitlocation location;
    private final Mod mod ;
    
    /**
     * Object constructor.
     * 
     * @author Daniele Pantaleone 
     * @param  client The client who made the hit
     * @param  target The client who suffered the hit
     * @param  mod The UrT MOD for this hit
     * @param  location The hit location
     * @return EventClientHit
     **/
    public EventClientHit(Client client, Client target, Mod mod, Hitlocation location) {
        super(EventType.EVT_CLIENT_HIT);
        this.client = client;
        this.target = target;
        this.mod = mod;
        this.location = location;
        
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
     * @return Hitlocation
     **/
    public Hitlocation getLocation() {
        return this.location;
    }
    
    
    /**
     * @author Daniele Pantaleone
     * @return Mod
     **/
    public Mod getMod() {
        return this.mod;
    }
    
}