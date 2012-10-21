/**
 * Urban Terror 4.2 server engine (Quake3-UrT[-ded]) RCON connection interface.
 *
 * @author      Daniele Pantaleone
 * @version     1.2
 * @copyright   Daniele Pantaleone, 04 October, 2012
 * @package     net.goreclan.console
 **/

package net.goreclan.console;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.apache.commons.logging.Log;


public class Rcon {
    
	private final Log log;
	
    private InetAddress ip;
    private int port;
    private String password;
    
    private DatagramSocket socket;
    private int timeout = 10000;
        
    
    /**
     * Object constructor.
     * 
     * @author Daniele Pantaleone 
     * @param  address The remote server address
     * @param  port The virtual port on which the server is accepting connections
     * @param  password The server Rcon password
     * @param  log A reference to the main BOT logger object
     * @return Rcon
     **/
    public Rcon(String address, int port, String password, Log log) {
        
    	// Copying the logger reference locally
    	this.log = log;
    	
        try {
        	
            this.ip = InetAddress.getByName(address);
            this.port = port;
            this.password = password;
            this.socket = new DatagramSocket();
            this.socket.setSoTimeout(this.timeout);
             
            this.log.debug("RCON utility configured [ ip : " + this.ip.getHostAddress() + " | port : " + this.port + " | password : " + this.password + " ]");
            
        } catch (UnknownHostException | SocketException e) {
        	this.log.fatal("Unable to initialize RCON utility", e);
            System.exit(1);
        }
    }
    
  
    /**
     * Write a command in the RCON console without returning the server response.
     * 
     * @author Daniele Pantaleone 
     * @param  command The command to be sent to the server engine
     * @throws IOException 
     **/
    public void sendNoRead(String command) throws IOException {
        
        this.log.trace("RCON sending [" + this.ip.getHostAddress() + ":" + this.port + "]: " + command);
        String send = "xxxxrcon " + this.password + " " + command;
        
        byte[] sendBuffer = send.getBytes();
        
        sendBuffer[0] = (byte)0xff; // Replacing 1st byte with the correct OutOfBound byte
        sendBuffer[1] = (byte)0xff; // Replacing 2nd byte with the correct OutOfBound byte
        sendBuffer[2] = (byte)0xff; // Replacing 3rd byte with the correct OutOfBound byte
        sendBuffer[3] = (byte)0xff; // Replacing 4th byte with the correct OutOfBound byte
        
        // Sending the command to the server engine. Exiting right after the send to succeed.
        this.socket.send(new DatagramPacket(sendBuffer, sendBuffer.length, this.ip, this.port));
        
    }
    
    
    /**
     * Write a command in the RCON console and return the result.
     * Note that this function simply return the string given back 
     * by the server engine without parsing the server response.
     * 
     * @author Daniele Pantaleone 
     * @param  command The command to be sent to the server engine
     * @throws IOException 
     * @return String
     **/
    public String sendRead(String command) throws IOException {
        
    	this.log.trace("RCON sending [" + this.ip.getHostAddress() + ":" + this.port + "]: " + command);
    	String send = "xxxxrcon " + this.password + " " + command;
        
        byte[] recvBuffer = new byte[2048];
        byte[] sendBuffer = send.getBytes();
       
        sendBuffer[0] = (byte)0xff; // Replacing 1st byte with the correct OutOfBound byte
        sendBuffer[1] = (byte)0xff; // Replacing 2nd byte with the correct OutOfBound byte
        sendBuffer[2] = (byte)0xff; // Replacing 3rd byte with the correct OutOfBound byte
        sendBuffer[3] = (byte)0xff; // Replacing 4th byte with the correct OutOfBound byte
        
        DatagramPacket recvPacket = new DatagramPacket(recvBuffer, recvBuffer.length);
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, this.ip, this.port);
        
        this.socket.send(sendPacket);
        this.socket.receive(recvPacket);
        
        return new String(recvPacket.getData()).replace("ÿÿÿÿprint", "");
        
    }
    
}