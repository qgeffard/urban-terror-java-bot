/**
 * This enum allows for type-safe comparisons when Event types are involved.
 * 
 * @author      Mathias Van Malderen
 * @version     1.0
 * @copyright   Mathias Van Malderen, 11 September, 2012
 * @package     net.goreclan.event
 **/

package net.goreclan.event;

public enum EventType {
    
	EVT_CLIENT_ACCOUNT_VALIDATED,
	EVT_CLIENT_ACCOUNT_BAN,
	EVT_CLIENT_ACCOUNT_KICK,
	EVT_CLIENT_ACCOUNT_REJECTED,
    EVT_CLIENT_CALLVOTE,
    EVT_CLIENT_CONNECT,
    EVT_CLIENT_DISCONNECT,
    EVT_CLIENT_GEAR_CHANGE,
    EVT_CLIENT_HIT,
    EVT_CLIENT_KILL,
    EVT_CLIENT_NAME_CHANGE,
    EVT_CLIENT_RADIO,
    EVT_CLIENT_SAY,
    EVT_CLIENT_SAY_PRIVATE,
    EVT_CLIENT_SAY_TEAM,
    EVT_CLIENT_SUICIDE,
    EVT_CLIENT_TEAM_CHANGE,
    EVT_CLIENT_VOTE,
    EVT_GAME_EXIT,
    EVT_GAME_ROUND_START,
    EVT_GAME_WARMUP,
    EVT_SURVIVOR_WINNER,
    
}
