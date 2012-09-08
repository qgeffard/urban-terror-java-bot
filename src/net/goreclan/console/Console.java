/**
 * ioUrbanTerror 4.2 RCON Console implementation
 * 
 * @author      Daniele Pantaleone
 * @version     1.1
 * @copyright   Daniele Pantaleone, 01 July, 2012
 * @package     net.goreclan.console
 **/

package net.goreclan.console;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.goreclan.domain.Client;
import net.goreclan.exception.ParserException;
import net.goreclan.iourt42.Gametype;
import net.goreclan.iourt42.Team;
import net.goreclan.parser.BooleanParser;

public class Console {
    
    private Rcon rcon = null;
    
    /**
     * Object constructor
     * 
     * @return Console
     * @author Daniele Pantaleone 
     **/
    public Console(String address, Integer port, String password) {
    	// Configuring the RCON utility object
        this.rcon = new Rcon(address, port, password);
    }
    
    
    /**
     * Ban a player from the server permanently
     * 
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public void ban(Client client) throws IOException {
        this.rcon.sendNoRead(String.format("addip %s", client.ip));
    }
    
    
    /**
     * Ban an ip address from the server permanently
     * 
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public void ban(String ip) throws IOException {
        this.rcon.sendNoRead(String.format("addip %s", ip));
    }
    
    
    /**
     * Write a bold message in the middle of the screen of all players.
     * 
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public void bigtext(String message) throws IOException {
        this.rcon.sendNoRead(String.format("bigtext \"%s\"", message));
    }
    
    
    /**
     * Dump user information for the specified client
     * 
     * @return Map<String, String>
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public Map<String, String> dumpuser(Client client) throws IOException {
        
    	String result = this.rcon.sendRead(String.format("dumpuser %d", client.slot));
        
        // This is the string we expect from the /rcon dumpuser <slot> command.
        // We need to parse it and build an HashMap containing the client data.
        //
        // userinfo
        // --------
        // ip                  93.40.100.128:59685
        // gear                GZJATWA
        // rate                25000
        // name                Mr.Click
        // racered             2
        
    	Map<String, String> dumpuser = new HashMap<String, String>();
        Pattern pattern = Pattern.compile("^\\s*([\\w]+)\\s+(.+)$");
        String[] lines = result.split("\n");
        
        for (String line: lines) {
            Matcher m = pattern.matcher(line);
            if (m.matches()) dumpuser.put(m.group(1), m.group(2));
        }
        
        return dumpuser;
        
    }
    
    
    /**
     * Dump user information for the specified player slot
     * 
     * @return Map<String, String>
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public Map<String, String> dumpuser(Integer slot) throws IOException {
        
    	String result = this.rcon.sendRead(String.format("dumpuser %d", slot));
        
        // This is the string we expect from the /rcon dumpuser <slot> command.
        // We need to parse it and build an HashMap containing the client data.
        //
        // userinfo
        // --------
        // ip                  93.40.100.128:59685
        // gear                GZJATWA
        // rate                25000
        // name                Mr.Click
        // racered             2
        
    	Map<String, String> dumpuser = new HashMap<String, String>();
        Pattern pattern = Pattern.compile("^\\s*([\\w]+)\\s+(.+)$");
        String[] lines = result.split("\n");
        
        for (String line: lines) {
            Matcher m = pattern.matcher(line);
            if (m.matches()) dumpuser.put(m.group(1), m.group(2));
        }
        
        return dumpuser;
        
    }
        
    
    /**
     * Force a player in the blue team.
     * 
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public void forceblue(Client client) throws IOException {
    	// Do not execute if the client is already in the specified team
    	if (client.team != Team.TEAM_BLUE)
    		this.rcon.sendNoRead(String.format("forceteam %d blue", client.slot));
    }
    
    
    /**
     * Force a player in the blue team.
     * 
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public void forceblue(Integer slot) throws IOException {
    	// Since we do not have a Client object as input, we cannot match the current
    	// client team. The RCON command is going to be executed anyway.
    	// NOTE: Use the previous version of the command if possible.
    	this.rcon.sendNoRead(String.format("forceteam %d blue", slot));
    }
    
    
    /**
     * Force a player in the free team (autojoin).
	 *
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public void forcefree(Client client) throws IOException {
    	this.rcon.sendNoRead(String.format("forceteam %d free", client.slot));
    }
    
    
    /**
     * Force a player in the free team (autojoin).
     * @return void
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public void forcefree(Integer slot) throws IOException {
    	this.rcon.sendNoRead(String.format("forceteam %d free", slot));
    }
    
    
    /**
     * Force a player in the red team.
     * 
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public void forcered(Client client) throws IOException {
    	// Do not execute if the client is already in the specified team
    	if (client.team != Team.TEAM_RED)
    		this.rcon.sendNoRead(String.format("forceteam %d red", client.slot));
    }
    
    
    /**
     * Force a player in the red team.
     * 
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public void forcered(Integer slot) throws IOException {
    	// Since we do not have a Client object as input, we cannot match the current
    	// client team. The RCON command is going to be executed anyway.
    	// NOTE: Use the previous version of the command if possible.
    	this.rcon.sendNoRead(String.format("forceteam %d red", slot));
    }
    
    
    /**
     * Force a player in the spectator team.
     * 
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public void forcespec(Client client) throws IOException {
    	// Do not execute if the client is already in the specified team
    	if (client.team != Team.TEAM_SPEC)
    		this.rcon.sendNoRead(String.format("forceteam %d spectator", client.slot));
    }
    
    
    /**
     * Force a player in the spectator team.
     * 
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public void forcespec(Integer slot) throws IOException {
    	// Since we do not have a Client object as input, we cannot match the current
    	// client team. The RCON command is going to be executed anyway.
    	// NOTE: Use the previous version of the command if possible.
    	this.rcon.sendNoRead(String.format("forceteam %d spectator", slot));
    }
    
    
    /**
     * Force the specified player substitute for his team (works only in match mode).
     * 
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public void forcesub(Client client) throws IOException {
        this.rcon.sendNoRead(String.format("forcesub %d", client.slot));
    }
    
    
    /**
     * Force the specified player substitute for his team (works only in match mode).
     * 
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public void forcesub(Integer slot) throws IOException {
        this.rcon.sendNoRead(String.format("forcesub %d", slot));
    }
    
    
    /**
     * Force a player in the specified team.
	 *
     * @author Daniele Pantaleone
     * @throws IOException 
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
     * @throws IOException 
     **/
    public void forceteam(Integer slot, Team team) throws IOException {
    	if (team == Team.TEAM_RED) this.forcered(slot);
    	else if (team == Team.TEAM_BLUE) this.forceblue(slot);
    	else if (team == Team.TEAM_SPEC) this.forcespec(slot);
    	else if (team == Team.TEAM_FREE) this.forcefree(slot);
    }
    
    
    /**
     * Force a player in the specified team.
	 *
     * @author Daniele Pantaleone
     * @throws IndexOutOfBoundsException
     * @throws IOException 
     **/
    public void forceeam(Client client, String teamname) throws IndexOutOfBoundsException, IOException {
    	Team team = Team.getByName(teamname);
    	this.forceteam(client, team);
    }
    
    
    /**
     * Force a player in the specified team.
	 *
     * @author Daniele Pantaleone
     * @throws IndexOutOfBoundsException
     * @throws IOException 
     **/
    public void forceteam(Integer slot, String teamname) throws IndexOutOfBoundsException, IOException {
    	Team team = Team.getByName(teamname);
    	this.forceteam(slot, team);
    }
    
    
    /**
     * Return a cvar value.
     * 
     * @author Daniele Pantaleone
     * @throws IOException 
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
     * @return String
     * @author Daniele Pantaleone 
     * @throws IOException 
     **/
    public Integer getFraglimit() throws IOException {
        return new Integer(this.getCvar("fraglimit"));
    }
    
    
    /**
     * Return the current gametype.
     * 
     * @return Gametype
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public Gametype getGametype() throws IOException {
        return Gametype.getByCode(new Integer(this.getCvar("g_gametype")));
    }
    
    
    /**
     * Return the current map name.
     * 
     * @return String
     * @author Daniele Pantaleone
     * @throws IOException 
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
     * Return a boolean value which inform of match mode activated/deactivated.
     * 
     * @return Boolean
     * @author Daniele Pantaleone
     * @throws IOException 
     * @throws ParserException
     **/
    public Boolean getMatchmode() throws IOException, ParserException {
        return BooleanParser.valueOf(this.getCvar("g_matchmode"));       
    }
    
    
    /**
     * Return the next map name.
     * 
     * @return String
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public String getNextMap() throws IOException {
        return this.getCvar("g_nextmap");
    }
    
    
    /**
     * Return an List containing the result of the "/rcon players" command.
     * 
     * @return List<List<String>>
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public List<List<String>> getPlayers() throws IOException {
    	
        String result = this.rcon.sendRead("players");
        
        // Do not bother us with Quake3 color codes.
    	// We are going to remove them anyway.
    	
    	Pattern p = Pattern.compile("\\^[0-9]{1}");
        Matcher m = p.matcher(result);
        result = m.replaceAll("");
        
        // This is the string we expect from the /rcon players command.
        // We need to parse it and build an Array with players informations.
        //
        // Map: ut4_casa
        // Players: 1
        // Score: R:0 B:0
        // 0:  [Gore]Fenix  SPECTATOR  k:0  d:0  ping:50  62.75.235.91:27960
        
        List<List<String>> players = new ArrayList<List<String>>();
        Pattern pattern = Pattern.compile("^\\s*([\\d]+):\\s+(.*?)\\s+(RED|BLUE|SPECTATOR|FREE)\\s+k:([\\d]+)\\s+d:([\\d]+)\\s+ping:([\\d]+|CNCT|ZMBI)\\s*(([\\d.]+):([\\d-]+))?$", Pattern.CASE_INSENSITIVE);
        String[] lines = result.split("\n");
        
        for (String line: lines) {
            
            Matcher matcher = pattern.matcher(line);
            
            if (m.matches()) {
                
                List<String> x = new ArrayList<String>();
                x.add(matcher.group(1));  // Slot
                x.add(matcher.group(2));  // Name
                x.add(matcher.group(3));  // Team
                x.add(matcher.group(4));  // Kills
                x.add(matcher.group(5));  // Deaths
                x.add(matcher.group(6));  // Ping
                x.add(matcher.group(8));  // IP
                x.add(matcher.group(9));  // Port
                players.add(x);
                
            }
        }
            
        return players;
        
    }
    
    
    /**
     * Return an List containing the result of the "/rcon status" command.
     * 
     * @return List<List<String>>
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public List<List<String>> getStatus() throws IOException {
        
    	String result = this.rcon.sendRead("status");
    	
    	// Do not bother us with Quake3 color codes.
    	// We are going to remove them anyway.
    	
    	Pattern p = Pattern.compile("\\^[0-9]{1}");
        Matcher m = p.matcher(result);
        result = m.replaceAll("");
    	
    	// This is the string we expect from the /rcon status command.
        // We need to parse it and build an Array with players informations.
        //
        // map: ut4_casa
        // num score ping name            lastmsg address               qport rate
        // --- ----- ---- --------------- ------- --------------------- ----- -----
        //   1    19   33 l33tn1ck             33 62.212.106.216:27960   5294 25000
        
    	List<List<String>> status = new ArrayList<List<String>>();
        Pattern pattern = Pattern.compile("^\\s*([\\d]+)\\s+([\\d-]+)\\s+([\\d]+|CNCT|ZMBI)\\s+(.*?)\\s+([\\d]+)\\s+([\\d.]+):([\\d-]+)\\s+([\\d]+)\\s+([\\d]+)$", Pattern.CASE_INSENSITIVE);
        String[] lines = result.split("\n");

        for (String line: lines) {
            
            Matcher matcher = pattern.matcher(line);
            
            if (m.matches()) {
                
                List<String> x = new ArrayList<String>();
                x.add(matcher.group(1));  // Slot
                x.add(matcher.group(2));  // Score
                x.add(matcher.group(3));  // Ping
                x.add(matcher.group(4));  // Name
                x.add(matcher.group(5));  // LastMsg
                x.add(matcher.group(6));  // IP
                x.add(matcher.group(7));  // Port
                x.add(matcher.group(8));  // QPort
                x.add(matcher.group(9));  // Rate
                status.add(x);
                
            }
        }
        
        return status;
    }
    
    
    /**
     * Return the timelimit value.
     * 
     * @return String
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public Integer getTimelimit() throws IOException {
        return new Integer(this.getCvar("timelimit"));
    }
    
    
    /**
     * Kick the specified client from the server.
     * 
     * @return void
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public void kick(Client client) throws IOException {
        this.rcon.sendNoRead(String.format("kick %d", client.slot));
    }
    
    
    /**
     * Kick the specified client from the server.
     * 
     * @return void
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public void kick(Integer slot) throws IOException {
        this.rcon.sendNoRead(String.format("kick %d", slot));
    }
    
    
    /**
     * Kick the specified client from the server by specifying a reason.
     * 
     * @return void
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public void kick(Client client, String reason) throws IOException {
        this.rcon.sendNoRead(String.format("kick %d \"%s\"", client.slot, reason));
    }
    
    
    /**
     * Kick the specified client from the server by specifying a reason.
     * 
     * @return void
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public void kick(Integer slot, String reason) throws IOException {
        this.rcon.sendNoRead(String.format("kick %d \"%s\"", slot, reason));
    }
    
    
    /**
     * Instantly kill a player..
     * 
     * @return void
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public void kill(Client client) throws IOException {
        this.rcon.sendNoRead(String.format("smite %d", client.slot));
    }
    
    
    /**
     * Instantly kill a player..
     * 
     * @return void
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public void kill(Integer slot) throws IOException {
        this.rcon.sendNoRead(String.format("smite %d", slot));
    }
    
    
    /**
     * Change server current map.
     * 
     * @return void
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public void map(String mapname) throws IOException {
        this.rcon.sendNoRead(String.format("map %s", mapname));
    }
    
    
    /**
     * Mute a player.
     * 
     * @return void
     * @author Daniele Pantaleone 
     * @throws IOException 
     **/
    public void mute(Client client) throws IOException {
        this.rcon.sendNoRead(String.format("mute %d", client.slot));
    }
    
    
    /**
     * Mute a player.
     * 
     * @return void
     * @author Daniele Pantaleone 
     * @throws IOException 
     **/
    public void mute(Integer slot) throws IOException {
        this.rcon.sendNoRead(String.format("mute %d", slot));
    }
    
     
    /**
     * Nuke a player.
     * 
     * @return void
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public void nuke(Client client) throws IOException {
    	this.rcon.sendNoRead(String.format("nuke %d", client.slot));
    }
    
    
    /**
     * Nuke a player.
     * 
     * @return void
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public void nuke(Integer slot) throws IOException {
    	this.rcon.sendNoRead(String.format("nuke %d", slot));
    }
    
    
    /**
     * Print a message in the in-game chat.
     * 
     * @return void
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public void say(String message) throws IOException {
        this.rcon.sendNoRead(String.format("say %s", message));
    }
    
    
    /**
     * Set a cvar value.
     * 
     * @return void
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public void setCvar(String name, String value) throws IOException {
        this.rcon.sendNoRead(String.format("set %s \"%s\"", name, value));
    }
    
    
    /**
     * Slap a player.
     * 
     * @return void
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public void slap(Client client) throws IndexOutOfBoundsException, IOException {
        this.rcon.sendNoRead(String.format("slap %d", client.slot));
    }
    
    
    /**
     * Slap a player.
     * 
     * @return void
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public void slap(Integer slot) throws IOException {
        this.rcon.sendNoRead(String.format("slap %d", slot));
    }
    
    
    /**
     * Start recording a server side demo of a player.
     * 
     * @return void
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public void startserverdemo(Client client) throws IOException {
        this.rcon.sendNoRead(String.format("startserverdemo %d", client.slot));
    }

    
    /**
     * Start recording a server side demo of a player.
     * 
     * @return void
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public void startserverdemo(Integer slot) throws IOException {
        this.rcon.sendNoRead(String.format("startserverdemo %d", slot));
    }
    
    
    /**
     * Start recording a server side demo of all the online players.
     * 
     * @return void
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public void startserverdemo() throws IOException {
        this.rcon.sendNoRead("startserverdemo all");
    }
    
    
    /**
     * Stop recording a server side demo of a player.
     * 
     * @return void
     * @author Daniele Pantaleone 
     * @throws IOException 
     **/
    public void stopserverdemo(Client client) throws IOException {
        this.rcon.sendNoRead(String.format("stopserverdemo %d", client.slot));
    }
    
    
    /**
     * Stop recording a server side demo of a player.
     * 
     * @return void
     * @author Daniele Pantaleone 
     * @throws IOException 
     **/
    public void stopserverdemo(Integer slot) throws IOException {
        this.rcon.sendNoRead(String.format("stopserverdemo %d", slot));
    }
    
    
    /**
     * Stop recording a server side demo of all the online players.
     * 
     * @return void
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public void stopserverdemo() throws IOException {
        this.rcon.sendNoRead("stopserverdemo all");
    }
    
    
    /**
     * Unban a player from the server.
     * 
     * @return void
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public void unban(Client client) throws IOException {
        this.rcon.sendNoRead(String.format("removeip %s", client.ip));
    }
    

    /**
     * Unban a player from the server.
     * 
     * @return void
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public void unban(String ip) throws IOException {
        this.rcon.sendNoRead(String.format("removeip %s", ip));
    }
    
    
    /**
     * Write a message directly in the Urban Terror console
     * 
     * @return void
     * @author Daniele Pantaleone
     * @throws IOException 
     **/
    public String write(String command) throws IOException  {
        return this.rcon.sendRead(command);
    }
     
}