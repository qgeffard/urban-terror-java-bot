/**
 * This class represent a RCON connection interface.
 *
 * @author      Daniele Pantaleone
 * @version     1.1
 * @copyright   Daniele Pantaleone, 29 June, 2012
 * @package     net.goreclan.console
 **/

package net.goreclan.console;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import net.goreclan.logger.Log;

public class Rcon {
    
    private InetAddress ip;
    private int port;
    private String password;
    private DatagramSocket socket;
    private int timeout = 10000;
        
    
    /**
     * Object constructor
     * 
     * @return Rcon
     * @author Daniele Pantaleone 
     **/
    public Rcon(String address, int port, String password) {
        
        try {
        	
            this.ip = InetAddress.getByName(address);
            this.port = port;
            this.password = password;
            this.socket = new DatagramSocket();
            this.socket.setSoTimeout(this.timeout);
            
            Log.debug(String.format("RCON configuration completed [ IP : %s | PORT : %d | PASSWORD : %s | TIMEOUT : %d ].", 
            						 this.ip.getHostAddress(), 
            						 this.port, 
            						 this.password, 
            						 this.timeout));
            
        } catch (UnknownHostException | SocketException e) {
            Log.error(e.toString());
            System.exit(1);
        }
    }
    
  
    /**
     * Write a command in the RCON console without returning the server response.
     * 
     * @return String
     * @author Daniele Pantaleone 
     * @throws IOException 

     **/
    public void sendNoRead(String command) throws IOException {
        
        // Logging the RCON command and building a proper RCON string
        Log.verbose(String.format("RCON sending [%s:%d]: %s", this.ip.getHostAddress(), this.port, command));
        String send = String.format("xxxxrcon %s %s", this.password, command);
        
        byte[] sendBuffer = send.getBytes();
        
        sendBuffer[0] = (byte)0xff; // Replacing 1st byte with the correct OutOfBound byte
        sendBuffer[1] = (byte)0xff; // Replacing 2nd byte with the correct OutOfBound byte
        sendBuffer[2] = (byte)0xff; // Replacing 3rd byte with the correct OutOfBound byte
        sendBuffer[3] = (byte)0xff; // Replacing 4th byte with the correct OutOfBound byte
        
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, this.ip, this.port);
        this.socket.send(sendPacket);
        
    }
    
    
    /**
     * Write a command in the RCON console and return the result.
     * Note that this function simply return the string given back by the server engine without parsing the server response.
     * 
     * @return String
     * @author Daniele Pantaleone 
     * @throws IOException 

     **/
    public String sendRead(String command) throws IOException {
        
        // Logging the RCON command and building a proper RCON string
        Log.verbose(String.format("RCON sending [%s:%d]: %s", this.ip.getHostAddress(), this.port, command));
        String send = String.format("xxxxrcon %s %s", this.password, command);
        
        byte[] recvBuffer = new byte[1024];
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