/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau101;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class ServerControl {

    DatagramSocket server;
    DatagramPacket receivePacket, sendPacket;
    byte[] receiveData, sendData;
    int port, buffSize;

    public ServerControl() {
        try {
            port = 1108;
            buffSize = 1024;
            server = new DatagramSocket(port);
        } catch (SocketException ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listening() {
        while (true) {
            try {
                receiveData = new byte[buffSize];
                receivePacket = new DatagramPacket(receiveData, buffSize);
                server.receive(receivePacket);
                String strRes = new String(receivePacket.getData()).trim();
                System.out.println(strRes);
                
                respond();
            } catch (IOException ex) {
                Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void respond() {
        try {
            String data = "15;1,5,8,3";
            String strMsg = "requestId;".concat(data);
            sendData = strMsg.getBytes();
            sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getSocketAddress());
            server.send(sendPacket);
        } catch (IOException ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
