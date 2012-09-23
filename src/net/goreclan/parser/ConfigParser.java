/**
 * This interface defines an interface for configuration parsers.
 * Every configuration is logically divided into configuration sections.
 * Each configuration section consists of multiple option settings.
 * 
 * @author		Mathias Van Malderen
 * @version 	1.0
 * @copyright 	Mathias Van Malderen 08 July, 2012
 * @package 	net.goreclan.utility.impl
 **/

package net.goreclan.parser;

import java.util.Map;

import net.goreclan.exception.ParserException;

public interface ConfigParser {
	
	
	/**
	 * Read the option value of a configuration section as a Boolean.
	 * 
	 * @return boolean
	 * @author Mathias Van Malderen
	 * @throws ParserException
	 **/
	boolean getBoolean(String section, String option) throws ParserException;
	
	
	/**
	 * Read the option value of a configuration section as a Double.
	 * 
	 * @return double
	 * @author Mathias Van Malderen
	 * @throws ParserException
	 **/
	double getDouble(String section, String option) throws ParserException;
	
	
	/**
	 * Read the option value of a configuration section as a Float.
	 * 
	 * @return Float
	 * @author Mathias Van Malderen
	 * @throws ParserException
	 **/
	float getFloat(String section, String option) throws ParserException;
	

	/**
	 * Read the option value of a configuration section as an Integer.
	 * 
	 * @return int
	 * @author Mathias Van Malderen
	 * @throws ParserException
	 **/
	int getInteger(String section, String option) throws ParserException;

	
	/**
	 * Read the option value of a configuration section as a Long.
	 * 
	 * @return long
	 * @author Mathias Van Malderen
	 * @throws ParserException
	 **/
	long getLong(String section, String option) throws ParserException;
	
	
	/**
	 * Read the option value of a configuration section as a Short.
	 * 
	 * @return short
	 * @author Mathias Van Malderen
	 * @throws ParserException
	 **/
	short getShort(String section, String option) throws ParserException;
	
	
	/**
	 * Read the option value of a configuration section as a String.
	 * 
	 * @return String
	 * @author Mathias Van Malderen
	 * @throws ParserException
	 **/
	String getString(String section, String option) throws ParserException;
	
	
	/**
	 * Read all setting names and setting values from a section.
	 * 
	 * @return Map<String, String>
	 * @author Mathias Van Malderen
	 * @throws ParserException
	 **/
	Map<String, String> getSettings(String section) throws ParserException;
	
}
