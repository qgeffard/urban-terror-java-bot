/**
 * Urban Terror 4.2 RCON console interface.
 * 
 * @author      Daniele Pantaleone
 * @version     1.0
 * @copyright   Daniele Pantaleone, 04 October, 2012
 * @package     net.goreclan.console
 **/

package net.goreclan.console;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import net.goreclan.domain.Client;
import net.goreclan.exception.ParserException;
import net.goreclan.iourt42.Gametype;
import net.goreclan.iourt42.Team;

public interface Console {s
	
	 /**
     * Ban a player from the server permanently.
     * 
     * @author Daniele Pantaleone
     * @param  client The client to ban from the server
     * @throws IOException If the RCON command fails in being executed
     **/
    public void ban(Client client) throws IOException;
    
    
    /**
     * Ban an ip address from the server permanently.
     * 
     * @author Daniele Pantaleone
     * @param  ip The IP address to ban from the server
     * @throws IOException If the RCON command fails in being executed
     **/
    public void ban(String ip) throws IOException;
    
    
    /**
     * Write a bold message in the middle of the screen of all players.
     * The message is going to disappear in few seconds (almost 3).
     * 
     * @author Daniele Pantaleone
     * @param  message The message to be printed
     * @throws IOException If the RCON command fails in being executed
     **/
    public void bigtext(String message) throws IOException;
    
    
    /**
     * Dump user information for the specified client.
     * 
     * @author Daniele Pantaleone
     * @param  client The client on who perform the dumpuser command
     * @throws IOException If the RCON command fails in being executed
     * @return A map with the dumpuser result. This will return null if the player is not connected anymore
     **/
    public Map<String, String> dumpuser(Client client) throws IOException;
    
    
    /**
     * Dump user information for the specified player slot.
     * 
     * @author Daniele Pantaleone
     * @param  slot The player slot on which perform the dumpuser command
     * @throws IOException If the RCON command fails in being executed
     * @return A map with the dumpuser result. This will return null if the player is not connected anymore 
     **/
    public Map<String, String> dumpuser(int slot) throws IOException;
        
    
    /**
     * Force a player in the blue team.
     * 
     * @author Daniele Pantaleone
     * @param  client The client who is going to be forced in the blue team
     * @throws IOException If the RCON command fails in being executed
     **/
    public void forceblue(Client client) throws IOException;
    
    
    /**
     * Force a player in the blue team.
     * 
     * @author Daniele Pantaleone
     * @param  slot The slot of the player who is going to be forced in the blue team
     * @throws IOException If the RCON command fails in being executed
     **/
    public void forceblue(int slot) throws IOException;
    
    
    /**
     * Force a player in the free team (autojoin).
	 *
     * @author Daniele Pantaleone
     * @param  client The client who is going to be forced
     * @throws IOException If the RCON command fails in being executed
     **/
    public void forcefree(Client client) throws IOException;
    
    
    /**
     * Force a player in the free team (autojoin).
     * 
     * @author Daniele Pantaleone
     * @param  slot The slot of the player who is going to be forced
     * @throws IOException If the RCON command fails in being executed
     **/
    public void forcefree(int slot) throws IOException;
    
    
    /**
     * Force a player in the red team.
     * 
     * @author Daniele Pantaleone
     * @param  client The client who is going to be forced in the red team
     * @throws IOException If the RCON command fails in being executed
     **/
    public void forcered(Client client) throws IOException;
    
    
    /**
     * Force a player in the red team.
     * 
     * @author Daniele Pantaleone
     * @param  slot The slot of the player who is going to be forced in the red team
     * @throws IOException If the RCON command fails in being executed
     **/
    public void forcered(int slot) throws IOException;
    
    
    /**
     * Force a player in the spectator team.
     * 
     * @author Daniele Pantaleone
     * @param  client The client who is going to be forced in the spectators team
     * @throws IOException If the RCON command fails in being executed
     **/
    public void forcespec(Client client) throws IOException;
    
    
    /**
     * Force a player in the spectator team.
     * 
     * @author Daniele Pantaleone
     * @param  slot The slot of the player who is going to be forced in the spectators team
     * @throws IOException If the RCON command fails in being executed
     **/
    public void forcespec(int slot) throws IOException;
    
    
    /**
     * Force the specified player substitute for his team (works only in match mode).
     * 
     * @author Daniele Pantaleone
     * @param  client The client who is going to be forced substitute
     * @throws IOException If the RCON command fails in being executed
     **/
    public void forcesub(Client client) throws IOException;
    
    
    /**
     * Force the specified player substitute for his team (works only in match mode).
     * 
     * @author Daniele Pantaleone
     * @param  slot The slot of the player who is going to be forced substitute
     * @throws IOException If the RCON command fails in being executed
     **/
    public void forcesub(int slot) throws IOException;
    
    
    /**
     * Force a player in the specified team.
	 *
     * @author Daniele Pantaleone
     * @param  client The client who is going to be forced
     * @param  team The team where to force the player in
     * @throws IOException If the RCON command fails in being executed
     **/
    public void forceteam(Client client, Team team) throws IOException;
    
    
    /**
     * Force a player in the specified team.
	 *
     * @author Daniele Pantaleone
     * @param  slot The slot of the player who is going to be forced
     * @param  teamname The team where to force the player in
     * @throws IOException If the RCON command fails in being executed
     **/
    public void forceteam(int slot, Team teamname) throws IOException;
    
    
    /**
     * Force a player in the specified team.
	 *
     * @author Daniele Pantaleone
     * @param  client The client who is going to be forced
     * @param  teamname A string representing the name of the team where to force the player in
     * @throws IndexOutOfBoundsException
     * @throws IOException If the RCON command fails in being executed
     **/
    public void forceteam(Client client, String teamname) throws IndexOutOfBoundsException, IOException;
    
    
    /**
     * Force a player in the specified team.
	 *
     * @author Daniele Pantaleone
     * @param  slot The slot of the player who is going to be forced
     * @param  team A string representing the name of the team where to force the player in
     * @throws IndexOutOfBoundsException
     * @throws IOException If the RCON command fails in being executed
     **/
    public void forceteam(int slot, String team) throws IndexOutOfBoundsException, IOException;
    
    
    /**
     * Return a cvar value.
     * 
     * @author Daniele Pantaleone
     * @param  name The cvar name
     * @throws IOException If the RCON command fails in being executed
     * @return The cvar value as a String
     **/
    public String getCvar(String name) throws IOException;
    
    
    /**
     * Return the fraglimit value.
     * 
     * @author Daniele Pantaleone 
     * @throws IOException If the RCON command fails in being executed
     * @throws NumberFormatException If the conversion between String and Integer fails.
     * @return The current fraglimit value
     **/
    public int getFraglimit() throws IOException, NumberFormatException;
    
    
    /**
     * Return the current gametype.
     * 
     * @author Daniele Pantaleone
     * @throws IOException If the RCON command fails in being executed
     * @throws NumberFormatException If the conversion between String and Integer fails
     * @return The Gametype object matching the current gametype
     **/
    public Gametype getGametype() throws IOException, NumberFormatException;
    
    
    /**
     * Return the current map name.
     * 
     * @author Daniele Pantaleone
     * @throws IOException If the RCON command fails in being executed
     * @return The current map name 
     **/
    public String getMap() throws IOException;
    
    
    /**
     * Return a list of available maps.
     * 
     * @author Daniele Pantaleone
     * @throws IOException If the RCON command fails in being executed
     * @return A list of all the maps available on the server
     **/
    public List<String> getMapList() throws IOException;
    
    
    /**
     * Return a boolean value which inform of match mode activated/deactivated.
     * 
     * @author Daniele Pantaleone
     * @throws IOException If the RCON command fails in being executed
     * @throws ParserException If the conversion between String and Boolean fails
     * @return TRUE if the match mode is activated, FALSE otherwise
     **/
    public boolean getMatchmode() throws IOException, ParserException;
    
    
    /**
     * Return the next map name.
     * 
     * @author Daniele Pantaleone
     * @throws IOException If the RCON command fails in being executed
     * @return The name of the nextmap set on the server
     **/
    public String getNextMap() throws IOException;
    
    
    /**
     * Return an List containing the result of the "/rcon players" command.
     * 
     * @author Daniele Pantaleone
     * @throws IOException If the RCON command fails in being executed
     * @return A list containing players informations
     **/
    public List<List<String>> getPlayers() throws IOException;
    
    
    /**
     * Return an List containing the result of the "/rcon status" command.
     * 
     * @author Daniele Pantaleone
     * @throws IOException If the RCON command fails in being executed
     * @return A list containing status informations
     **/
    public List<List<String>> getStatus() throws IOException;
    
    
    /**
     * Return the timelimit value.
     * 
     * @author Daniele Pantaleone
     * @throws IOException If the RCON command fails in being executed
     * @throws NumberFormatException If the conversion between String and Integer fails
     * @return The current timelimit value
     **/
    public int getTimelimit() throws IOException, NumberFormatException;
    
    
    /**
     * Kick the specified client from the server.
     * 
     * @author Daniele Pantaleone
     * @param  client The client who is going to be kicked from the server
     * @throws IOException If the RCON command fails in being executed
     **/
    public void kick(Client client) throws IOException;
    
    
    /**
     * Kick the specified client from the server.
     * 
     * @author Daniele Pantaleone
     * @param  slot The slot of the player who is going to be kicked from the server
     * @throws IOException If the RCON command fails in being executed
     **/
    public void kick(int slot) throws IOException;
    
    
    /**
     * Kick the specified client from the server by specifying a reason.
     * 
     * @author Daniele Pantaleone
     * @param  client The client who is going to be kicked from the server
     * @param  reason The reason why the client is going to be kicked
     * @throws IOException If the RCON command fails in being executed
     **/
    public void kick(Client client, String reason) throws IOException;
    
    
    /**
     * Kick the specified client from the server by specifying a reason.
     * 
     * @author Daniele Pantaleone
     * @param  slot The slot of the player who is going to be kicked from the server
     * @param  reason The reason why the player with the specified slot is going to be kicked
     * @throws IOException If the RCON command fails in being executed
     **/
    public void kick(int slot, String reason) throws IOException;
    
    
    /**
     * Instantly kill a player.
     * 
     * @author Daniele Pantaleone
     * @param  client The client who is going to be killed
     * @throws IOException If the RCON command fails in being executed
     **/
    public void kill(Client client) throws IOException;
    
    
    /**
     * Instantly kill a player.
     * 
     * @author Daniele Pantaleone
     * @param  slot The slot of the player who is going to be killed
     * @throws IOException If the RCON command fails in being executed
     **/
    public void kill(int slot) throws IOException;
    
    
    /**
     * Change server current map.
     * 
     * @author Daniele Pantaleone
     * @param  mapname The name of the map to load
     * @throws IOException If the RCON command fails in being executed
     **/
    public void map(String mapname) throws IOException;
    
    
    /**
     * Mute a player.
     * 
     * @author Daniele Pantaleone 
     * @param  client The client who is going to be muted
     * @throws IOException If the RCON command fails in being executed
     **/
    public void mute(Client client) throws IOException;
    
    
    /**
     * Mute a player.
     * 
     * @author Daniele Pantaleone 
     * @param  slot The slot of the player who is going to be muted
     * @throws IOException If the RCON command fails in being executed
     **/
    public void mute(int slot) throws IOException;
    
     
    /**
     * Nuke a player.
     * 
     * @author Daniele Pantaleone
     * @param  client The client who is going to be nuked
     * @throws IOException If the RCON command fails in being executed
     **/
    public void nuke(Client client) throws IOException;
    
    
    /**
     * Nuke a player.
     * 
     * @author Daniele Pantaleone
     * @param  slot The slot of the player who is going to be nuked
     * @throws IOException If the RCON command fails in being executed
     **/
    public void nuke(int slot) throws IOException;
    
    
    /**
     * Print a message to the in-game chat.
     * 
     * @author Daniele Pantaleone
     * @param  message The message to print
     * @throws IOException If the RCON command fails in being executed
     **/
    public void say(String message) throws IOException;
    
    
    /**
     * Set a cvar value.
     * 
     * @author Daniele Pantaleone
     * @param  name The name of the cvar
     * @param  value The value to assign to the cvar
     * @throws IOException If the RCON command fails in being executed
     **/
    public void setCvar(String name, String value) throws IOException;
    
    
    /**
     * Slap a player.
     * 
     * @author Daniele Pantaleone
     * @param  client The client who is going to be slapped
     * @throws IOException If the RCON command fails in being executed
     **/
    public void slap(Client client) throws IOException;
    
    
    /**
     * Slap a player.
     * 
     * @author Daniele Pantaleone
     * @param  slot The slot of the player who is going to be slapped
     * @throws IOException If the RCON command fails in being executed
     **/
    public void slap(int slot) throws IOException;
    
    
    /**
     * Start recording a server side demo of a player.
     * 
     * @author Daniele Pantaleone
     * @param  client The client whose we want to record a demo
     * @throws IOException If the RCON command fails in being executed
     **/
    public void startserverdemo(Client client) throws IOException;

    
    /**
     * Start recording a server side demo of a player.
     * 
     * @author Daniele Pantaleone
     * @param  slot The slot of the player whose we want to record a demo
     * @throws IOException If the RCON command fails in being executed
     **/
    public void startserverdemo(int slot) throws IOException;
    
    
    /**
     * Start recording a server side demo of all the online players.
     * 
     * @author Daniele Pantaleone
     * @throws IOException If the RCON command fails in being executed
     **/
    public void startserverdemo() throws IOException;
    
    
    /**
     * Stop recording a server side demo of a player.
     * 
     * @author Daniele Pantaleone 
     * @param  client The client whose we want to stop a demo recording
     * @throws IOException If the RCON command fails in being executed
     **/
    public void stopserverdemo(Client client) throws IOException;
    
    
    /**
     * Stop recording a server side demo of a player.
     * 
     * @author Daniele Pantaleone 
     * @param  slot The slot of the player whose we want to stop a demo recording
     * @throws IOException If the RCON command fails in being executed
     **/
    public void stopserverdemo(int slot) throws IOException;
    
    
    /**
     * Stop recording a server side demo of all the online players.
     * 
     * @author Daniele Pantaleone
     * @throws IOException If the RCON command fails in being executed
     **/
    public void stopserverdemo() throws IOException;
    
    
    /**
     * Send a private message to a player.
     * 
     * @author Daniele Pantaleone
     * @param  client The client you want to send the message
     * @param  message The message to be sent
     * @throws IOException If the RCON command fails in being executed
     **/
    public void tell(Client client, String message) throws IOException;
    
    
    /**
     * Send a private message to a player.
     * 
     * @author Daniele Pantaleone
     * @param  slot The slot of the player you want to send the message
     * @param  message The message to be sent
     * @throws IOException If the RCON command fails in being executed
     **/
    public void tell(int slot, String message) throws IOException;
    
    
    /**
     * Unban a player from the server.
     * This doesn't apply for "/rcon auth-ban" command.
     * 
     * @author Daniele Pantaleone
     * @param  client The client we want to unban
     * @throws IOException If the RCON command fails in being executed
     **/
    public void unban(Client client) throws IOException;
    

    /**
     * Unban a player from the server.
     * This doesn't apply for "/rcon auth-ban" command.
     * 
     * @author Daniele Pantaleone
     * @param  ip The IP address of the player we want to unban
     * @throws IOException If the RCON command fails in being executed
     **/
    public void unban(String ip) throws IOException;
    
    
    /**
     * Write a message directly in the Urban Terror console.
     * Try to avoid the use of this command. Use instead the other optimized methods available in this class.
     * 
     * @author Daniele Pantaleone
     * @param  command The command to execute
     * @throws IOException If the RCON command fails in being executed
     * @return The server response to the RCON command
     **/
    public String write(String command) throws IOException;
    
}
