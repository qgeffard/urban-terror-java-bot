/**
 * An XML Configuration parser for files.
 * 
 * @author        Mathias Van Malderen
 * @version     1.0
 * @copyright     Mathias Van Malderen 08 July, 2012
 * @package     net.goreclan.utility.impl
 **/

package net.goreclan.parser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import net.goreclan.exception.ConfigParserException;
import net.goreclan.exception.ParserException;

public class XmlConfigParser implements ConfigParser {
    
    private static final String SECTION_XPATH = "/configuration/settings[@name='%s']/set";
    private static final String SETTING_XPATH = SECTION_XPATH + "[@name='%s']";
    
    private DocumentBuilder builder;
    private Document document;
    private XPath xpath;
    
    
    /**
     * Class constructor.
     * 
     * @author Mathias Van Malderen
     * @throws ParserException
     **/
    public XmlConfigParser(String configPath) throws ParserException {
        this(new File(configPath));        
    }
    
    
    /**
     * Class constructor.
     * 
     * @author Mathias Van Malderen
     * @throws ParserException
     **/
    public XmlConfigParser(File configPath) throws ParserException {
        
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            xpath = XPathFactory.newInstance().newXPath();
            document = builder.parse(configPath);
        } catch (IOException | SAXException | ParserConfigurationException e) {
            throw new ParserException("An error occured while initializing the XML parser.", e);
        }
        
    }
    
    
    /**
     * Return the option value lookup XPath expression with section and option filled in.
     * 
     * @return String
     * @author Mathias Van Malderen
     **/
    private String getSettingXPath(String section, String option) {
        return String.format(SETTING_XPATH, section, option);
    }
    
    
    /**
     * Return the section lookup XPath expression with section filled in.
     * 
     * @return String
     * @author Mathias Van Malderen
     **/
    private String getSectionXPath(String section) {
        return String.format(SECTION_XPATH, section);
    }
    
    
    /**
     * Read the option value of a configuration section as a Boolean.
     * 
     * @return boolean
     * @author Mathias Van Malderen
     * @throws ParserException
     **/
    @Override
    public boolean getBoolean(String section, String option) throws ParserException {
        try {
            return BooleanParser.valueOf(this.getString(section, option));
        } catch (ParserException e) {
            throw new ConfigParserException("The value cannot be parsed as a boolean", section, option, e);
        }
    }
    
    
    /**
     * Read the option value of a configuration section as a Double.
     * 
     * @return double
     * @author Mathias Van Malderen
     * @throws ParserException
     **/
    @Override
    public double getDouble(String section, String option) throws ParserException {
        try {
            return Double.valueOf(this.getString(section, option));
        } catch (NumberFormatException e) {
            throw new ConfigParserException("The value cannot be parsed as a double.", section, option, e);
        }
    }
    
    
    /**
     * Read the option value of a configuration section as a Float.
     * 
     * @return float
     * @author Mathias Van Malderen
     * @throws ParserException
     **/
    @Override
    public float getFloat(String section, String option) throws ParserException {
        try {
            return Float.valueOf(this.getString(section, option));
        } catch (NumberFormatException e) {
            throw new ConfigParserException("The value cannot be parsed as a float.", section, option, e);
        }
    }
    
    
    /**
     * Read the option value of a configuration section as an Integer.
     * 
     * @return int
     * @author Mathias Van Malderen
     * @throws ParserException
     **/
    @Override
    public int getInteger(String section, String option) throws ParserException {
        try {
            return Integer.valueOf(this.getString(section, option));
        } catch (NumberFormatException e) {
            throw new ConfigParserException("The value cannot be parsed as an integer.", section, option, e);
        }
    }
    
    

    /**
     * Read the option value of a configuration section as a Long.
     * 
     * @return long
     * @author Mathias Van Malderen
     * @throws ParserException
     **/
    @Override
    public long getLong(String section, String option) throws ParserException {
        try {
            return Long.valueOf(this.getString(section, option));
        } catch (NumberFormatException e) {
            throw new ConfigParserException("The value cannot be parsed as a Long.", section, option, e);
        }
    }
    
    
    /**
     * Read the option value of a configuration section as a Short.
     * 
     * @return short
     * @author Mathias Van Malderen
     * @throws ParserException
     **/
    @Override
    public short getShort(String section, String option) throws ParserException {
        try {
            return Short.valueOf(this.getString(section, option));
        } catch (NumberFormatException e) {
            throw new ConfigParserException("The value cannot be parsed as a short.", section, option, e);
        }
    }
    
    
    /**
     * Read the option value of a configuration section as a String.
     * 
     * @return String
     * @author Mathias Van Malderen
     * @throws ParserException
     **/
    @Override
    public String getString(String section, String option) throws ParserException {
        try {
            String res = xpath.evaluate(getSettingXPath(section, option), document);
            if (res.isEmpty()) throw new ConfigParserException("Configuration section or option not found.", section, option);
            return res;
        } catch (XPathExpressionException e) {
            throw new ConfigParserException("An error occured while reading the option value.", section, option, e);
        }
    }
    
    
    /**
     * Read all setting names and setting values from a section.
     * 
     * @return Map<String, String>
     * @author Mathias Van Malderen
     * @throws ParserException
     **/
    @Override
    public Map<String, String> getSettings(String section) throws ParserException {
        try {
            NodeList nodes = (NodeList) xpath.evaluate(getSectionXPath(section), document, XPathConstants.NODESET);
            Map<String, String> res = new HashMap<String, String>();
            for (int i = 0; i < nodes.getLength(); i++) {
                String settingName  = nodes.item(i).getAttributes().getNamedItem("name").getNodeValue();
                String settingValue = nodes.item(i).getChildNodes().item(0).getNodeValue();
                res.put(settingName, settingValue);
            }
            return res;
        } catch (XPathExpressionException e) {
            throw new ConfigParserException("An error occured while reading a whole section.", section, null, e);
        }
    }
    
}
