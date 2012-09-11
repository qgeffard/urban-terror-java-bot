/**
 * Event object for Client Team Change
 * 
 * @author      Daniele Pantaleone
 * @version     1.1
 * @copyright   Daniele Pantaleone, 15 July, 2012
 * @package     net.goreclan.event
 **/

package net.goreclan.event;

import net.goreclan.domain.Client;
import net.goreclan.iourt42.Team;

public class EventClientTeamChange extends Event {

    private Client client = null;
    private Team before = null;
    private Team after = null;
    
    
    /**
     * Object constructor
     * 
     * @return void
     * @author Daniele Pantaleone 
     **/
    public EventClientTeamChange(Client client, Team before, Team after) {
        super(EventType.EVT_CLIENT_TEAM_CHANGE);
        this.client = client;
        this.before = before;
        this.after = after;
    }
    
    
    /**
     * @return Client
     * @author Daniele Pantaleone
     */
    public Client getClient() {
        return this.client;
    }
    
    
    /**
     * @return Team
     * @author Daniele Pantaleone
     */
    public Team getBefore() {
        return this.before;
    }
    
    
    /**
     * @return Team
     * @author Daniele Pantaleone
     */
    public Team getAfter() {
        return this.after;
    }
}