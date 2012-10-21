/**
 * Event object for Client Team Change.
 * 
 * @author      Daniele Pantaleone
 * @version     1.2
 * @copyright   Daniele Pantaleone, 15 July, 2012
 * @package     net.goreclan.event
 **/

package net.goreclan.event;

import net.goreclan.domain.Client;
import net.goreclan.iourt42.Team;

public class EventClientTeamChange extends Event {

    private final Client client;
    private final Team before;
    private final Team after;
    
    
    /**
     * Object constructor.
     * 
     * @author Daniele Pantaleone 
     * @param  client The client who changed team
     * @param  before The old team
     * @param  after The new team
     * @return EventClientTeamChange
     **/
    public EventClientTeamChange(Client client, Team before, Team after) {
        super(EventType.EVT_CLIENT_TEAM_CHANGE);
        this.client = client;
        this.before = before;
        this.after = after;
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
     * @return Team
     **/
    public Team getBefore() {
        return this.before;
    }
    
    
    /**
     * @author Daniele Pantaleone
     * @return Team
     **/
    public Team getAfter() {
        return this.after;
    }
}