/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau100;

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
public class Server {

    DatagramSocket server;
    DatagramPacket receivePacket, sendPacket;
    int port, bufferSize;
    byte[] receiveData, sendData;

    public Server() {

        try {
            port = 1107;
            bufferSize = 1024;
            server = new DatagramSocket(port);
        } catch (SocketException ex) {
            System.out.println("Err: Creating server failed! : " + ex);
        }
    }

    public void listening() {
        while (true) {
            try {
                receiveData = new byte[bufferSize];
                receivePacket = new DatagramPacket(receiveData, bufferSize);
                server.receive(receivePacket);
                String strRes = new String(receivePacket.getData()).trim();
                System.out.println(strRes);
                send();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void send() {
        try {
            String strMsg = "requestId;6,9";
            sendData = strMsg.getBytes();
            sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getSocketAddress());
            server.send(sendPacket);
        } catch (IOException ex) {
            System.out.println("Err: Sending message failed! : " + ex);
        }
    }
}
