/**
 * Event object for Client Account Validated.
 * 
 * @author      Daniele Pantaleone
 * @version     1.0
 * @copyright   Daniele Pantaleone, 08 October, 2012
 * @package     net.goreclan.event
 **/

package net.goreclan.event;

import net.goreclan.domain.Client;

public class EventClientAccountValidated extends Event {

    private final Client client;
    private final String login;
    private final int rcon_level;
    private final String notoriety;
    
    /**
     * Object constructor.
     * 
     * @author Daniele Pantaleone
     * @param  client The client whose account has been validated
     * @param  login The urbanterror.info login nickname
     * @param  rcon_level The RCON level
     * @param  notoriety The client notoriety
     * @return EventClientAccountValidated
     **/
    public EventClientAccountValidated(Client client, String login, int rcon_level, String notoriety) {  
        super(EventType.EVT_CLIENT_CONNECT);
        this.client = client;
        this.login = login;
        this.rcon_level = rcon_level;
        this.notoriety = notoriety;
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
     * @return String
     **/
    public String getLogin() {
        return this.login;
    }
    
    /**
     * @author Daniele Pantaleone
     * @return int
     **/
    public int getRconLevel() {
        return this.rcon_level;
    }
    
    /**
     * @author Daniele Pantaleone
     * @return String
     **/
    public String getNotoriety() {
        return this.notoriety;
    }
    
}