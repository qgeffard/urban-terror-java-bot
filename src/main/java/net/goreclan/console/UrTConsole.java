/**
 * Urban Terror 4.2 RCON console implementation.
 * 
 * @author      Daniele Pantaleone
 * @version     1.2.2
 * @copyright   Daniele Pantaleone, 04 October, 2012
 * @package     net.goreclan.console
 **/

package net.goreclan.console;

import java.io.IOException;
import java.util.LinkedList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;

import net.goreclan.domain.Client;
import net.goreclan.exception.ParserException;
import net.goreclan.iourt42.Gametype;
import net.goreclan.iourt42.Team;
import net.goreclan.parser.BooleanParser;

public class UrTConsole implements Console{
    
    private Rcon rcon;
    private Log log;
    
    
    /**
     * Object constructor.
     * 
     * @author Daniele Pantaleone 
     * @param  address The remote server address
     * @param  port The virtual port on which the server is accepting connections
     * @param  password The server Rcon password
     * @param  log A reference to the main bot logger object
     * @return An initialized UrtConsole object.
     **/
    public UrTConsole(String address, int port, String password, Log log) {
    	// Configuring the RCON utility object. Console can't deal with the game without it.
    	// Exceptions are handled directly inside the RCON class, because if it fails to
    	// start, a fatal exception will be thrown and the system exit instantly.
    	this.rcon = new Rcon(address, port, password, log);
    	this.log = log;
    }
    
    
    /**
     * Ban a player from the server permanently.
     * 
     * @author Daniele Pantaleone
     * @param  client The client to ban from the server
     * @throws IOException If the RCON command fails in being executed 
     **/
    public void ban(Client client) throws IOException {
        this.rcon.sendNoRead("addip " + client.ip);
    }
    
    
    /**
     * Ban an ip address from the server permanently.
     * 
     * @author Daniele Pantaleone
     * @param  ip The IP address to ban from the server
     * @throws IOException If the RCON command fails in being executed
     **/
    public void ban(String ip) throws IOException {
        this.rcon.sendNoRead("addip " + ip);
    }
    
    
    /**
     * Write a bold message in the middle of the screen of all players.
     * The message is going to disappear in few seconds (almost 3).
     * 
     * @author Daniele Pantaleone
     * @param  message The message to be printed
     * @throws IOException If the RCON command fails in being executed
     **/
    public void bigtext(String message) throws IOException {
        this.rcon.sendNoRead("bigtext \"" + message + "\"");
    }
    
    
    /**
     * Dump user information for the specified client.
     * 
     * @author Daniele Pantaleone
     * @param  client The client you want to retrieve informations
     * @throws IOException If the RCON command fails in being executed
     * @return A map with the dumpuser result. This will return null if the player is not connected anymore
     **/
    public Map<String, String> dumpuser(Client client) throws IOException {
        
    	String result = this.rcon.sendRead("dumpuser " + client.slot);
        
        // This is the string we expect from the /rcon dumpuser <slot> command.
        // We need to parse it and build an HashMap containing the client data.
        //
        // userinfo
        // --------
        // ip                  93.40.100.128:59685
        // gear                GZJATWA
        // rate                25000
        // name                [FS]Fenix
        // racered             2
        
    	Map<String, String> map = new LinkedHashMap<String, String>();
        Pattern pattern = Pattern.compile("^\\s*([\\w]+)\\s+(.+)$");
        String[] lines = result.split("\n");
        
        if (!lines[0].equals("userinfo")) {
        	this.log.debug("Client " + client.slot + "recently disconnected but is character is still in game...");
        	return null;
        }
        
        for (String line: lines) {
            Matcher m = pattern.matcher(line);
            if (m.matches()) map.put(m.group(1), m.group(2));
        }
        
        return map;
        
    }
    
    
    /**
     * Dump user information for the specified player slot.
     * 
     * @author Daniele Pantaleone
     * @param  slot The player slot on which perform the dumpuser command
     * @throws IOException If the RCON command fails in being executed
     * @return A map with the dumpuser result. This will return null if the player is not connected anymore
     **/
    public Map<String, String> dumpuser(int slot) throws IOException {
        
    	String result = this.rcon.sendRead("dumpuser " + slot);
        
        // This is the string we expect from the /rcon dumpuser <slot> command.
        // We need to parse it and build an HashMap containing the client data.
        //
        // userinfo
        // --------
        // ip                  93.40.100.128:59685
        // gear                GZJATWA
        // rate                25000
        // name                [FS]Fenix
        // racered             2
        
    	Map<String, String> map = new LinkedHashMap<String, String>();
        Pattern pattern = Pattern.compile("^\\s*([\\w]+)\\s+(.+)$");
        String[] lines = result.split("\n");
        
        if (!lines[0].equals("userinfo")) {
        	this.log.debug("Client " + slot + "recently disconnected but is character is still in game...");
        	return null;
        }
        
        for (String line: lines) {
            Matcher m = pattern.matcher(line);
            if (m.matches()) map.put(m.group(1), m.group(2));
        }
        
        return map;
        
    }
        
    
    /**
     * Force a player in the blue team.
     * 
     * @author Daniele Pantaleone
     * @param  client The client who is going to be forced in the blue team
     * @throws IOException If the RCON command fails in being executed
     **/
    public void forceblue(Client client) throws IOException {
    	// Do not execute if the client is already in the specified team.
    	// This will prevent to overflow the server with RCON commands.
    	if (client.team != Team.TEAM_BLUE)
    		this.rcon.sendNoRead("forceteam " + client.slot + " blue");
    }
    
    
    /**
     * Force a player in the blue team.
     * 
     * @author Daniele Pantaleone
     * @param  slot The slot of the player who is going to be forced in the blue team
     * @throws IOException If the RCON command fails in being executed
     **/
    public void forceblue(int slot) throws IOException {
    	// Since we do not have a Client object as input, we cannot match the current
    	// client team. The RCON command is going to be executed anyway.
    	// NOTE: Use the previous version of the command if possible.
    	this.rcon.sendNoRead("forceteam " + slot + " blue");
    }
    
    
    /**
     * Force a player in the free team (autojoin).
	 *
     * @author Daniele Pantaleone
     * @param  client The client who is going to be forced
     * @throws IOException If the RCON command fails in being executed
     **/
    public void forcefree(Client client) throws IOException {
    	this.rcon.sendNoRead("forceteam " + client.slot + " free");
    }
    
    
    /**
     * Force a player in the free team (autojoin).
     * 
     * @author Daniele Pantaleone
     * @param  slot The slot of the player who is going to be forced
     * @throws IOException If the RCON command fails in being executed
     **/
    public void forcefree(int slot) throws IOException {
    	this.rcon.sendNoRead("forceteam " + slot +" free");
    }
    
    
    /**
     * Force a player in the red team.
     * 
     * @author Daniele Pantaleone
     * @param  client The client who is going to be forced in the red team
     * @throws IOException If the RCON command fails in being executed
     **/
    public void forcered(Client client) throws IOException {
    	// Do not execute if the client is already in the specified team.
    	// This will prevent to overflow the server with RCON commands.
    	if (client.team != Team.TEAM_RED)
    		this.rcon.sendNoRead("forceteam " + client.slot +" red");
    }
    
    
    /**
     * Force a player in the red team.
     * 
     * @author Daniele Pantaleone
     * @param  slot The slot of the player who is going to be forced in the red team
     * @throws IOException If the RCON command fails in being executed
     **/
    public void forcered(int slot) throws IOException {
    	// Since we do not have a Client object as input, we cannot match the current
    	// client team. The RCON command is going to be executed anyway.
    	// NOTE: Use the previous version of the command if possible.
    	this.rcon.sendNoRead("forceteam " + slot + " red");
    }
    
    
    /**
     * Force a player in the spectator team.
     * 
     * @author Daniele Pantaleone
     * @param  client The client who is going to be forced in the spectators team
     * @throws IOException If the RCON command fails in being executed
     **/
    public void forcespec(Client client) throws IOException {
    	// Do not execute if the client is already in the specified team.
    	// This will prevent to overflow the server with RCON commands.
    	if (client.team != Team.TEAM_SPEC)
    		this.rcon.sendNoRead("forceteam " + client.slot + " spectator");
    }
    
    
    /**
     * Force a player in the spectator team.
     * 
     * @author Daniele Pantaleone
     * @param  slot The slot of the player who is going to be forced in the spectators team
     * @throws IOException If the RCON command fails in being executed
     **/
    public void forcespec(int slot) throws IOException {
    	// Since we do not have a Client object as input, we cannot match the current
    	// client team. The RCON command is going to be executed anyway.
    	// NOTE: Use the previous version of the command if possible.
    	this.rcon.sendNoRead("forceteam " + slot + " spectator");
    }
    
    
    /**
     * Force the specified player substitute for his team (works only in match mode).
     * 
     * @author Daniele Pantaleone
     * @param  client The client who is going to be forced substitute
     * @throws IOException If the RCON command fails in being executed
     **/
    public void forcesub(Client client) throws IOException {
        this.rcon.sendNoRead("forcesub " + client.slot);
    }
    
    
    /**
     * Force the specified player substitute for his team (works only in match mode).
     * 
     * @author Daniele Pantaleone
     * @param  slot The slot of the player who is going to be forced substitute
     * @throws IOException If the RCON command fails in being executed
     **/
    public void forcesub(int slot) throws IOException {
        this.rcon.sendNoRead("forcesub " + slot);
    }
    
    
    /**
     * Force a player in the specified team.
	 *
     * @author Daniele Pantaleone
     * @param  client The client who is going to be forced
     * @param  team The team where to force the player in
     * @throws IOException If the RCON command fails in being executed
     **/
    public void forceteam(Client client, Team team) throws IOException {
    	if (team == Team.TEAM_RED) this.forcered(client);
    	else if (team == Team.TEAM_BLUE) this.forceblue(client);
    	else if (team == Team.TEAM_SPEC) this.forcespec(client);
    	else if (team == Team.TEAM_FREE) this.forcefree(client);
    }
    
    
    /**
     * Force a player in the specified team.
	 *
     * @author Daniele Pantaleone
     * @param  slot The slot of the player who is going to be forced
     * @param  team The team where to force the player in
     * @throws IOException If the RCON command fails in being executed
     **/
    public void forceteam(int slot, Team team) throws IOException {
    	if (team == Team.TEAM_RED) this.forcered(slot);
    	else if (team == Team.TEAM_BLUE) this.forceblue(slot);
    	else if (team == Team.TEAM_SPEC) this.forcespec(slot);
    	else if (team == Team.TEAM_FREE) this.forcefree(slot);
    }
    
    
    /**
     * Force a player in the specified team.
	 *
     * @author Daniele Pantaleone
     * @param  client The client who is going to be forced
     * @param  teamname A string representing the name of the team where to force the player in
     * @throws IndexOutOfBoundsException If the method fails in retrieving a Team object by matching the given team name
     * @throws IOException If the RCON command fails in being executed
     **/
    public void forceteam(Client client, String teamname) throws IndexOutOfBoundsException, IOException {
    	Team team = Team.getByName(teamname);
    	this.forceteam(client, team);
    }
    
    
    /**
     * Force a player in the specified team.
	 *
     * @author Daniele Pantaleone
     * @param  slot The slot of the player who is going to be forced
     * @param  teamname A string representing the name of the team where to force the player in
     * @throws IndexOutOfBoundsException If the method fails in retrieving a Team object by matching the give team name
     * @throws IOException If the RCON command fails in being executed
     **/
    public void forceteam(int slot, String teamname) throws IndexOutOfBoundsException, IOException {
    	Team team = Team.getByName(teamname);
    	this.forceteam(slot, team);
    }
    
    
    /**
     * Return a cvar value.
     * 
     * @author Daniele Pantaleone
     * @param  name The cvar name
     * @throws IOException If the RCON command fails in being executed
     * @return The cvar value as a String
     **/
    public String getCvar(String name) throws IOException {
    	
        String result = this.rcon.sendRead(name);
        
        String value = null;
        Pattern pattern = Pattern.compile("^\\s*\"[\\w\\d]*\"\\s*is:\"([\\w\\d]*)\".*$");
        Matcher m = pattern.matcher(result);
        if (m.matches()) value = m.group(1);
        
        return value;
        
    }
    
    
    /**
     * Return the fraglimit value.
     * 
     * @author Daniele Pantaleone 
     * @throws IOException If the RCON command fails in being executed
     * @throws NumberFormatException If the conversion between String and Integer fails
     * @return The current fraglimit value
     **/
    public int getFraglimit() throws IOException, NumberFormatException {
        return Integer.valueOf(this.getCvar("fraglimit"));
    }
    
    
    /**
     * Return the current gametype.
     * 
     * @author Daniele Pantaleone
     * @throws IOException If the RCON command fails in being executed
     * @throws NumberFormatException If the conversion between String and Integer fails
     * @return The Gametype object matching the current gametype
     **/
    public Gametype getGametype() throws IOException, NumberFormatException {
        return Gametype.getByCode(Integer.valueOf(this.getCvar("g_gametype")));
    }
    
    
    /**
     * Return the current map name.
     * 
     * @author Daniele Pantaleone
     * @throws IOException If the RCON command fails in being executed
     * @return The current map name 
     **/
    public String getMap() throws IOException {
        
    	String result = this.rcon.sendRead("status");
        
    	// This is the string we expect from the /rcon status command.
        // We need to parse it and get only the current map name.
        //
        // map: ut4_casa
        // num score ping name            lastmsg address               qport rate
        // --- ----- ---- --------------- ------- --------------------- ----- -----
        //   1    19   33 l33tn1ck             33 62.212.106.216:27960   5294 25000
        
        String mapname = null;
        Pattern pattern = Pattern.compile("^\\s*map:\\s*([\\w\\d]+)\\s*$");
        String[] lines = result.split("\n");
        
        for (String line: lines) { 
            Matcher m = pattern.matcher(line);
            if (m.matches()) { 
                mapname = m.group(1); 
                break;
            }
        }
        
        return mapname;
    }
    
    
    /**
     * Return a list of available maps.
     * 
     * @author Daniele Pantaleone
     * @throws IOException If the RCON command fails in being executed
     * @return A list of all the maps available on the server
     **/
    public List<String> getMapList() throws IOException {
    	
    	String result = this.rcon.sendRead("fdir *.bsp");
    	List<String> maplist = new LinkedList<String>();
    	Pattern pattern = Pattern.compile("^*maps/(.*).bsp$");
    	
    	String[] lines = result.split("\n");

        for (String line: lines) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches()) maplist.add(matcher.group(1));
        }
        
        return maplist;
    }
    
    
    /**
     * Return a boolean value which inform of match mode activated/deactivated.
     * 
     * @author Daniele Pantaleone
     * @throws IOException If the RCON command fails in being executed
     * @throws ParserException If the conversion between String and Boolean fails.
     * @return TRUE if the match mode is activated, FALSE otherwise
     **/
    public boolean getMatchmode() throws IOException, ParserException {
        return BooleanParser.valueOf(this.getCvar("g_matchmode"));       
    }
    
    
    /**
     * Return the next map name.
     * 
     * @author Daniele Pantaleone
     * @throws IOException If the RCON command fails in being executed
     * @return The name of the nextmap set on the server
     **/
    public String getNextMap() throws IOException {
        return this.getCvar("g_nextmap");
    }
    
    
    /**
     * Return an List containing the result of the "/rcon players" command.
     * 
     * @author Daniele Pantaleone
     * @throws IOException If the RCON command fails in being executed
     * @return A list containing players informations
     **/
    public List<List<String>> getPlayers() throws IOException {
    	
        String result = this.rcon.sendRead("players");
        
        // Quake3 color notations can be boring sometime.
        // We are going to remove all the Quake3 color codes (^[0-9]) from the string
        // returned by the server engine. We do not want to bother ourselves ^^.
    	
    	Pattern p = Pattern.compile("\\^[0-9]{1}");
        Matcher m = p.matcher(result);
        result = m.replaceAll("");
        
        // This is the string we expect from the /rcon players command.
        // We need to parse it and build an Array with players informations.
        //
        // Map: ut4_casa
        // Players: 1
        // Score: R:0 B:0
        // 0:  [FS]Fenix  SPECTATOR  k:0  d:0  ping:50  62.75.235.91:27960
        
        List<List<String>> collection = new LinkedList<List<String>>();
        Pattern pattern = Pattern.compile("^\\s*([\\d]+):\\s+(.*?)\\s+(RED|BLUE|SPECTATOR|FREE)\\s+k:([\\d]+)\\s+d:([\\d]+)\\s+ping:([\\d]+|CNCT|ZMBI)\\s*(([\\d.]+):([\\d-]+))?$", Pattern.CASE_INSENSITIVE);
        String[] lines = result.split("\n");
        
        for (String line: lines) {
            
            Matcher matcher = pattern.matcher(line);
            
            if (m.matches()) {
                
                List<String> x = new LinkedList<String>();
                x.add(matcher.group(1));  // Slot
                x.add(matcher.group(2));  // Name
                x.add(matcher.group(3));  // Team
                x.add(matcher.group(4));  // Kills
                x.add(matcher.group(5));  // Deaths
                x.add(matcher.group(6));  // Ping
                x.add(matcher.group(8));  // IP
                x.add(matcher.group(9));  // Port
                collection.add(x);
                
            }
        }
            
        return collection;
        
    }
    
    
    /**
     * Return an List containing the result of the "/rcon status" command.
     * 
     * @author Daniele Pantaleone
     * @throws IOException If the RCON command fails in being executed
     * @return A list containing status informations
     **/
    public List<List<String>> getStatus() throws IOException {
        
    	String result = this.rcon.sendRead("status");
    	
    	// Quake3 color notations can be boring sometime.
        // We are going to remove all the Quake3 color codes (^[0-9]) from the string
        // returned by the server engine. We do not want to bother ourselves ^^.
    	
    	Pattern p = Pattern.compile("\\^[0-9]{1}");
        Matcher m = p.matcher(result);
        result = m.replaceAll("");
    	
    	// This is the string we expect from the /rcon status command.
        // We need to parse it and build an Array with players informations.
        //
        // map: ut4_casa
        // num score ping name            lastmsg address               qport rate
        // --- ----- ---- --------------- ------- --------------------- ----- -----
        //   1    19   33 [FS]Fenix            33 62.212.106.216:27960   5294 25000
        
    	List<List<String>> collection = new LinkedList<List<String>>();
        Pattern pattern = Pattern.compile("^\\s*([\\d]+)\\s+([\\d-]+)\\s+([\\d]+|CNCT|ZMBI)\\s+(.*?)\\s+([\\d]+)\\s+([\\d.]+):([\\d-]+)\\s+([\\d]+)\\s+([\\d]+)$", Pattern.CASE_INSENSITIVE);
        String[] lines = result.split("\n");

        for (String line: lines) {
            
            Matcher matcher = pattern.matcher(line);
            
            if (m.matches()) {
                
                List<String> x = new LinkedList<String>();
                x.add(matcher.group(1));  // Slot
                x.add(matcher.group(2));  // Score
                x.add(matcher.group(3));  // Ping
                x.add(matcher.group(4));  // Name
                x.add(matcher.group(5));  // LastMsg
                x.add(matcher.group(6));  // IP
                x.add(matcher.group(7));  // Port
                x.add(matcher.group(8));  // QPort
                x.add(matcher.group(9));  // Rate
                collection.add(x);
                
            }
        }
        
        return collection;
    }
    
    
    /**
     * Return the timelimit value.
     * 
     * @author Daniele Pantaleone
     * @throws IOException If the RCON command fails in being executed
     * @throws NumberFormatException If the conversion between String and Integer fails
     * @return The current timelimit value
     **/
    public int getTimelimit() throws IOException, NumberFormatException {
        return Integer.valueOf(this.getCvar("timelimit"));
    }
    
    
    /**
     * Kick the specified client from the server.
     * 
     * @author Daniele Pantaleone
     * @param  client The client who is going to be kicked from the server
     * @throws IOException If the RCON command fails in being executed
     **/
    public void kick(Client client) throws IOException {
        this.rcon.sendNoRead("kick " + client.slot);
    }
    
    
    /**
     * Kick the specified client from the server.
     * 
     * @author Daniele Pantaleone
     * @param  slot The slot of the player who is going to be kicked from the server
     * @throws IOException If the RCON command fails in being executed
     **/
    public void kick(int slot) throws IOException {
        this.rcon.sendNoRead("kick " + slot);
    }
    
    
    /**
     * Kick the specified client from the server by specifying a reason.
     * 
     * @author Daniele Pantaleone
     * @param  client The client who is going to be kicked from the server
     * @param  reason The reason why the client is going to be kicked
     * @throws IOException If the RCON command fails in being executed
     **/
    public void kick(Client client, String reason) throws IOException {
        this.rcon.sendNoRead("kick " + client.slot + reason);
    }
    
    
    /**
     * Kick the specified client from the server by specifying a reason.
     * 
     * @author Daniele Pantaleone
     * @param  slot The slot of the player who is going to be kicked from the server
     * @param  reason The reason why the player with the specified slot is going to be kicked
     * @throws IOException If the RCON command fails in being executed
     **/
    public void kick(int slot, String reason) throws IOException {
    	this.rcon.sendNoRead("kick " + slot + reason);
    }
    
    
    /**
     * Instantly kill a player.
     * 
     * @author Daniele Pantaleone
     * @param  client The client who is going to be killed
     * @throws IOException If the RCON command fails in being executed
     **/
    public void kill(Client client) throws IOException {
        this.rcon.sendNoRead("smite " + client.slot);
    }
    
    
    /**
     * Instantly kill a player.
     * 
     * @author Daniele Pantaleone
     * @param  slot The slot of the player who is going to be killed
     * @throws IOException If the RCON command fails in being executed
     **/
    public void kill(int slot) throws IOException {
    	this.rcon.sendNoRead("smite " + slot);
    }
    
    
    /**
     * Change server current map.
     * 
     * @author Daniele Pantaleone
     * @param  mapname The name of the map to load
     * @throws IOException If the RCON command fails in being executed
     **/
    public void map(String mapname) throws IOException {
        this.rcon.sendNoRead("map " + mapname);
    }
    
    
    /**
     * Mute a player.
     * 
     * @author Daniele Pantaleone 
     * @param  client The client who is going to be muted
     * @throws IOException If the RCON command fails in being executed
     **/
    public void mute(Client client) throws IOException {
        this.rcon.sendNoRead("mute " + client.slot);
    }
    
    
    /**
     * Mute a player.
     * 
     * @author Daniele Pantaleone 
     * @param  slot The slot of the player who is going to be muted
     * @throws IOException If the RCON command fails in being executed
     **/
    public void mute(int slot) throws IOException {
        this.rcon.sendNoRead("mute " + slot);
    }
    
     
    /**
     * Nuke a player.
     * 
     * @author Daniele Pantaleone
     * @param  client The client who is going to be nuked
     * @throws IOException If the RCON command fails in being executed
     **/
    public void nuke(Client client) throws IOException {
    	this.rcon.sendNoRead("nuke " + client.slot);
    }
    
    
    /**
     * Nuke a player.
     * 
     * @author Daniele Pantaleone
     * @param  slot The slot of the player who is going to be nuked
     * @throws IOException If the RCON command fails in being executed
     **/
    public void nuke(int slot) throws IOException {
    	this.rcon.sendNoRead("nuke " + slot);
    }
    
    
    /**
     * Print a message to the in-game chat.
     * 
     * @author Daniele Pantaleone
     * @param  message The message to print
     * @throws IOException If the RCON command fails in being executed
     **/
    public void say(String message) throws IOException {
        this.rcon.sendNoRead("say " + message);
    }
    
    
    /**
     * Set a cvar value.
     * 
     * @author Daniele Pantaleone
     * @param  name The name of the cvar
     * @param  value The value to assign to the cvar
     * @throws IOException If the RCON command fails in being executed
     **/
    public void setCvar(String name, String value) throws IOException {
        this.rcon.sendNoRead("set " + name + " \"" + value + "\"");
    }
    
    
    /**
     * Slap a player.
     * 
     * @author Daniele Pantaleone
     * @param  client The client who is going to be slapped
     * @throws IOException If the RCON command fails in being executed
     **/
    public void slap(Client client) throws IOException {
        this.rcon.sendNoRead("slap " + client.slot);
    }
    
    
    /**
     * Slap a player.
     * 
     * @author Daniele Pantaleone
     * @param  slot The slot of the player who is going to be slapped
     * @throws IOException If the RCON command fails in being executed
     **/
    public void slap(int slot) throws IOException {
        this.rcon.sendNoRead("slap " + slot);
    }
    
    
    /**
     * Start recording a server side demo of a player.
     * 
     * @author Daniele Pantaleone
     * @param  client The client whose we want to record a demo
     * @throws IOException If the RCON command fails in being executed
     **/
    public void startserverdemo(Client client) throws IOException {
        this.rcon.sendNoRead("startserverdemo " + client.slot);
    }

    
    /**
     * Start recording a server side demo of a player.
     * 
     * @author Daniele Pantaleone
     * @param  slot The slot of the player whose we want to record a demo
     * @throws IOException If the RCON command fails in being executed
     **/
    public void startserverdemo(int slot) throws IOException {
        this.rcon.sendNoRead("startserverdemo " + slot);
    }
    
    
    /**
     * Start recording a server side demo of all the online players.
     * 
     * @author Daniele Pantaleone
     * @throws IOException If the RCON command fails in being executed
     **/
    public void startserverdemo() throws IOException {
        this.rcon.sendNoRead("startserverdemo all");
    }
    
    
    /**
     * Stop recording a server side demo of a player.
     * 
     * @author Daniele Pantaleone 
     * @param  client The client whose we want to stop a demo recording
     * @throws IOException If the RCON command fails in being executed
     **/
    public void stopserverdemo(Client client) throws IOException {
        this.rcon.sendNoRead("stopserverdemo " + client.slot);
    }
    
    
    /**
     * Stop recording a server side demo of a player.
     * 
     * @author Daniele Pantaleone 
     * @param  slot The slot of the player whose we want to stop a demo recording
     * @throws IOException If the RCON command fails in being executed
     **/
    public void stopserverdemo(int slot) throws IOException {
        this.rcon.sendNoRead("stopserverdemo " + slot);
    }
    
    
    /**
     * Stop recording a server side demo of all the online players.
     * 
     * @author Daniele Pantaleone
     * @throws IOException If the RCON command fails in being executed
     **/
    public void stopserverdemo() throws IOException {
        this.rcon.sendNoRead("stopserverdemo all");
    }
    
    
    /**
     * Send a provate message to a player.
     * 
     * @author Daniele Pantaleone
     * @param  client The client you want to send the message
     * @param  message The message to be sent
     * @throws IOException If the RCON command fails in being executed
     **/
    public void tell(Client client, String message) throws IOException {
    	this.rcon.sendNoRead("tell " + client.slot + message);
    }
    
    
    /**
     * Send a provate message to a player.
     * 
     * @author Daniele Pantaleone
     * @param  slot The slot of the player you want to send the message
     * @param  message The message to be sent
     * @throws IOException If the RCON command fails in being executed
     **/
    public void tell(int slot, String message) throws IOException {
    	this.rcon.sendNoRead("tell " + slot + message);
    }
    
    
    /**
     * Unban a player from the server.
     * 
     * @author Daniele Pantaleone
     * @param  client The client we want to unban
     * @throws IOException If the RCON command fails in being executed
     **/
    public void unban(Client client) throws IOException {
        this.rcon.sendNoRead("removeip " + client.ip);
    }
    

    /**
     * Unban a player from the server.
     * 
     * @author Daniele Pantaleone
     * @param  ip The IP address of the player we want to unban
     * @throws IOException If the RCON command fails in being executed
     **/
    public void unban(String ip) throws IOException {
        this.rcon.sendNoRead("removeip " + ip);
    }
    
    
    /**
     * Write a message directly in the Urban Terror console.
     * Try to avoid the use of this command. Use instead the other optimized methods available in this class.
     * 
     * @author Daniele Pantaleone
     * @param  command The command to execute
     * @throws IOException If the RCON command fails in being executed
     * @return The server response to the RCON command
     **/
    public String write(String command) throws IOException  {
        return this.rcon.sendRead(command);
    }
     
}