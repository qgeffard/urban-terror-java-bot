/**
 * XML configuration parser
 * 
 * @author      Mathias Van Malderen, Daniele Pantaleone
 * @version     1.1
 * @copyright   Mathias Van Malderen 08 July, 2012
 * @package     net.goreclan.parser
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

import net.goreclan.exception.XmlConfigParserException;

public class XmlConfigParser {
    
    private static final String SECTION_XPATH = "/configuration/settings[@name='%s']/set";
    private static final String SETTING_XPATH = SECTION_XPATH + "[@name='%s']";
    
    private DocumentBuilder builder;
    private Document document;
    private XPath xpath;
    
    
    /**
     * Class constructor.
     * 
     * @author Mathias Van Malderen
     * @throws XmlConfigParserException
     **/
    public XmlConfigParser(String path) throws XmlConfigParserException {
        this(new File(path));        
    }
    
    
    /**
     * Class constructor.
     * 
     * @author Mathias Van Malderen
     * @throws XmlConfigParserException
     **/
    public XmlConfigParser(File configPath) throws XmlConfigParserException {
        
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            xpath = XPathFactory.newInstance().newXPath();
            document = builder.parse(configPath);
        } catch (IOException | SAXException | ParserConfigurationException e) {
            throw new XmlConfigParserException("Unable to initialize XML configuration parser.", e);
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
     * @return Boolean
     * @author Mathias Van Malderen
     * @throws XmlConfigParserException
     **/
    public boolean getBoolean(String section, String option) throws XmlConfigParserException {
        return BooleanParser.valueOf(this.getString(section, option));
    }
    

    /**
     * Read the option value of a configuration section as a Double.
     * 
     * @return Double
     * @author Mathias Van Malderen
     * @throws XmlConfigParserException
     **/
    public double getDouble(String section, String option) throws XmlConfigParserException {
        try { return Double.valueOf(this.getString(section, option)); } 
        catch (NumberFormatException e) { throw new XmlConfigParserException("Unable parse this value as a Double", section, option, e); }
    }
    
    
    /**
     * Read the option value of a configuration section as a Float.
     * 
     * @return Float
     * @author Mathias Van Malderen
     * @throws XmlConfigParserException
     **/
    public float getFloat(String section, String option) throws XmlConfigParserException {
        try { return Float.valueOf(this.getString(section, option)); } 
        catch (NumberFormatException e) { throw new XmlConfigParserException("Unable to parse this value as a Float", section, option, e); }
    }
    
    
    /**
     * Read the option value of a configuration section as an Integer.
     * 
     * @return Integer
     * @author Mathias Van Malderen
     * @throws XmlConfigParserException
     **/
    public int getInteger(String section, String option) throws XmlConfigParserException {
        try { return Integer.valueOf(this.getString(section, option)); } 
        catch (NumberFormatException e) { throw new XmlConfigParserException("Unable to parse this values as an Integer", section, option, e); }
    }
    
    
    /**
     * Read the option value of a configuration section as a Long.
     * 
     * @return Long
     * @author Mathias Van Malderen
     * @throws XmlConfigParserException
     **/

    public long getLong(String section, String option) throws XmlConfigParserException {
        try { return Long.valueOf(this.getString(section, option)); } 
        catch (NumberFormatException e) { throw new XmlConfigParserException("Unable to parse this value as a Long", section, option, e); }
    }
    
    
    /**
     * Read the option value of a configuration section as a Short.
     * 
     * @return short
     * @author Mathias Van Malderen
     * @throws XmlConfigParserException
     **/
    public short getShort(String section, String option) throws XmlConfigParserException {
        try { return Short.valueOf(this.getString(section, option)); } 
        catch (NumberFormatException e) { throw new XmlConfigParserException("Unable to parse this value as a Short", section, option, e); }
    }
    
    
    /**
     * Read the option value of a configuration section as a String.
     * 
     * @return String
     * @author Mathias Van Malderen
     * @throws XmlConfigParserException
     **/
    public String getString(String section, String option) throws XmlConfigParserException {
        try {
            String res = xpath.evaluate(getSettingXPath(section, option), document);
            if (res.isEmpty()) throw new XmlConfigParserException("Unable to match given section/option", section, option);
            return res;
        } catch (XPathExpressionException e) {
            throw new XmlConfigParserException("Unable to read configuration value", section, option, e);
        }
    }
    
    
    /**
     * Read all setting names and setting values from a section.
     * 
     * @return Map<String, String>
     * @author Mathias Van Malderen
     * @throws XmlConfigParserException
     **/
    public Map<String, String> getSection(String section) throws XmlConfigParserException {
        try {
            NodeList nodes = (NodeList) xpath.evaluate(getSectionXPath(section), document, XPathConstants.NODESET);
            Map<String, String> res = new HashMap<String, String>();
            for (int i = 0; i < nodes.getLength(); i++) {
                String name  = nodes.item(i).getAttributes().getNamedItem("name").getNodeValue();
                String value = nodes.item(i).getChildNodes().item(0).getNodeValue();
                res.put(name, value);
            }
            return res;
        } catch (XPathExpressionException e) {
            throw new XmlConfigParserException("Unable to parse configuration section", section, null, e);
        }
    }
    
}
