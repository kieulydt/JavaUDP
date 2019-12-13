/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau101;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class ClientControl {

    DatagramSocket client;
    DatagramPacket sendPacket, receivePacket;
    InetAddress inetAddress;
    byte[] sendData, receiveData;
    int port, buffSize;

    public ClientControl() {
        try {
            port = 1108;
            buffSize = 1024;
            inetAddress = InetAddress.getByName("localhost");
            client = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void send(String strMsg) {
        try {
            sendData = strMsg.getBytes();
            sendPacket = new DatagramPacket(sendData, sendData.length, inetAddress, port);
            client.send(sendPacket);
        } catch (IOException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void receive() {
        try {
            receiveData = new byte[buffSize];
            receivePacket = new DatagramPacket(receiveData, buffSize);
            client.receive(receivePacket);
            String strRes = new String(receivePacket.getData()).trim();            
//            System.out.println(strRes);
            String requestId = "", temp_1 = "", temp_2 = "";
            int k = strRes.indexOf(';');
            requestId = strRes.substring(0, k);
            for (int i = 0; i < strRes.length(); i++) {
                if ((strRes.charAt(i) >= 48 && strRes.charAt(i) <= 57)
                        || (strRes.charAt(i) >= 65 && strRes.charAt(i) <= 90)
                        || (strRes.charAt(i) >= 97 && strRes.charAt(i) <= 122)) {
                    temp_1 += strRes.charAt(i);
                } else {
                    temp_2 += strRes.charAt(i);
                }
            }
            
            String strMsg = requestId + ";" + temp_1 + "," + temp_2;
            send(strMsg);
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
