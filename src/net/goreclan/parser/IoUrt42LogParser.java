/**
 * ioUrbanTerror 4.2 Log Parser
 * 
 * @author      Daniele Pantaleone
 * @version     1.0
 * @copyright   Daniele Pantaleone, 01 July, 2012
 * @package     net.goreclan.parser
 **/

package net.goreclan.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import net.goreclan.logger.Log;


public class IoUrt42LogParser {
    
    private Map<String, Pattern> lineformats;

    
    /**
     * Object constructor
     * 
     * @return IoUrt42Parser
     * @author Daniele Pantaleone 
     **/
    public IoUrt42LogParser() {
        
        this.lineformats = new HashMap<String,Pattern>();
        this.lineformats.put("AccountValidated", Pattern.compile("^\\s*[\\d]+:[\\d]+\\s?AccountValidated:\\s([\\d]+)\\s-\\s(.+)\\s-\\s([\\d]+)\\s-\\s\\\"([\\w]+)\\\"$", Pattern.CASE_INSENSITIVE));
        this.lineformats.put("AccountBan", Pattern.compile("^\\s*[\\d]+:[\\d]+\\s?AccountBan:\\s([\\d]+)\\s-\\s(.+)\\s-\\s([\\d]+)d\\s-\\s([\\d]+)h\\s-\\s([\\d]+)m$", Pattern.CASE_INSENSITIVE));
        this.lineformats.put("AccountKick", Pattern.compile("^\\s*[\\d]+:[\\d]+\\s?AccountKick:\\s([\\d]+)\\s-\\s\\\"(.*)\\\"$", Pattern.CASE_INSENSITIVE));
        this.lineformats.put("AccountRejected", Pattern.compile("^\\s*[\\d]+:[\\d]+\\s?AccountRejected:\\s([\\d]+)\\s-\\s(.*)\\s-\\s\\\"(.*)\\\"$", Pattern.CASE_INSENSITIVE));
        this.lineformats.put("BombHolder", Pattern.compile("^\\s*[\\d]+:[\\d]+\\s?Bombholder is\\s+([\\d]+)$", Pattern.CASE_INSENSITIVE));
        this.lineformats.put("ClientBegin", Pattern.compile("^\\s*[\\d]+:[\\d]+\\s?ClientBegin:\\s([\\d]+)$", Pattern.CASE_INSENSITIVE));
        this.lineformats.put("ClientCallvote", Pattern.compile("^\\s*[\\d]+:[\\d]+\\s?Callvote:\\s?([\\d]+)\\s?-\\s?\"([\\w]+)\\s?(.*)\"$", Pattern.CASE_INSENSITIVE));
        this.lineformats.put("ClientConnect", Pattern.compile("^\\s*[\\d]+:[\\d]+\\s?ClientConnect:\\s([\\d]+)$", Pattern.CASE_INSENSITIVE));
        this.lineformats.put("ClientDisconnect", Pattern.compile("^\\s*[\\d]+:[\\d]+\\s?ClientDisconnect:\\s([\\d]+)$", Pattern.CASE_INSENSITIVE));
        this.lineformats.put("ClientHit", Pattern.compile("^\\s*[\\d]+:[\\d]+\\s?Hit:\\s([\\d]+)\\s([\\d]+)\\s([\\d]+)\\s([\\d]+):\\s(.*)$", Pattern.CASE_INSENSITIVE));
        this.lineformats.put("ClientItem", Pattern.compile("^\\s*[\\d]+:[\\d]+\\s?Item:\\s([\\d]+)\\s(.*)$", Pattern.CASE_INSENSITIVE));
        this.lineformats.put("ClientKill", Pattern.compile("^\\s*[\\d]+:[\\d]+\\s?Kill:\\s([\\d]+)\\s([\\d]+)\\s([\\d]+):\\s(.*)$", Pattern.CASE_INSENSITIVE));
        this.lineformats.put("ClientRadio", Pattern.compile("^\\s*[\\d]+:[\\d]+\\s?Radio:\\s?([\\d]+)\\s?-\\s?([\\d]+)\\s?-\\s?([\\d]+)\\s?-\\s?\"(.*)\"\\s?-\\s?\"(.*)\"$", Pattern.CASE_INSENSITIVE)); 
        this.lineformats.put("ClientSay", Pattern.compile("^\\s*[\\d]+:[\\d]+\\s?say:\\s([\\d]+)\\s(.*):\\s?(.*)$", Pattern.CASE_INSENSITIVE));
        this.lineformats.put("ClientSayPrivate", Pattern.compile("^\\s*[\\d]+:[\\d]+\\s?saytell:\\s([\\d]+)\\s([\\d]+)\\s(.*):\\s?(.*)$", Pattern.CASE_INSENSITIVE));
        this.lineformats.put("ClientSayTeam", Pattern.compile("^\\s*[\\d]+:[\\d]+\\s?sayteam:\\s([\\d]+)\\s(.*):\\s?(.*)$", Pattern.CASE_INSENSITIVE));
        this.lineformats.put("ClientUserinfo", Pattern.compile("^\\s*[\\d]+:[\\d]+\\s?ClientUserinfo:\\s([\\d]+)\\s(.*)$", Pattern.CASE_INSENSITIVE));
        this.lineformats.put("ClientUserinfoChanged", Pattern.compile("^\\s*[\\d]+:[\\d]+\\s?ClientUserinfoChanged:\\s([\\d]+)\\s(.*)$", Pattern.CASE_INSENSITIVE));
        this.lineformats.put("ClientVote", Pattern.compile("^\\s*[\\d]+:[\\d]+\\s?Vote:\\s?([\\d]+)\\s?-\\s?([\\d]+)$", Pattern.CASE_INSENSITIVE));
        this.lineformats.put("GameExit", Pattern.compile("^\\s*[\\d]+:[\\d]+\\s?Exit:\\sTimelimit hit.$", Pattern.CASE_INSENSITIVE));
        this.lineformats.put("GameRoundStart", Pattern.compile("^\\s*[\\d]+:[\\d]+\\s?InitRound:\\s(.*)$", Pattern.CASE_INSENSITIVE));
        this.lineformats.put("GameWarmup", Pattern.compile("^\\s*[\\d]+:[\\d]+\\s?Warmup:$", Pattern.CASE_INSENSITIVE));
        this.lineformats.put("SurvivorWinner", Pattern.compile("^\\s*[\\d]+:[\\d]+\\s?SurvivorWinner:\\s([\\w]+)$", Pattern.CASE_INSENSITIVE));
    
        Log.bot(String.format("ioUrbanTerror 4.2 log parser configuration completed [ LINE FORMATS : %d ].", lineformats.size()));
        		
    }
    

    /**
     * Return the corresponding Line Format for the given index
     * 
     * @return Pattern
     * @author Daniele Pantaleone 
     * @throws IndexOutOfBoundsException 
     **/
    public Pattern getLineFormat(String index) throws IndexOutOfBoundsException {
    	if (!lineformats.containsKey(index)) 
        	throw new IndexOutOfBoundsException(String.format("Invalid pattern index: %s.", index));
       
        return lineformats.get(index);
    }
    
    
    /**
     * Return an HashMap containing the informations of the info string provided
     * InfoString format: \ip\110.143.73.144:27960\challenge\1052098110\qport\51418\protocol\68...
     * 
     * @return Map<String,String>
     * @author Daniele Pantaleone 
     **/
    public Map<String,String> parseUserinfo(String infostring) {
        
        Map<String, String> userinfo = new HashMap<String, String>();
        String[] data = infostring.split("\\");
        
        for(int i=0; i<data.length; i=i+2) {
            // Adding values in to the HashMap
            // Using key(i)/value(i+1) format
            userinfo.put(data[i], data[i+1]);
        }
        
        return userinfo;
        
    }

}