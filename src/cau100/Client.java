/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau100;

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
public class Client {
    DatagramSocket client;
    DatagramPacket sendPacket, receivePacket;
    InetAddress inetAddress;
    byte[] sendData, receiveData;
    int port, buffSize;
    
    public Client() {
        try {
            port = 1107;
            buffSize = 1024;
            client = new DatagramSocket();
            inetAddress = InetAddress.getByName("localhost");
        } catch (SocketException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void send(String strMsg) {
        try {
//            strMsg = ";B16DCAT013;100";
            sendData = strMsg.getBytes();
            sendPacket = new DatagramPacket(sendData, sendData.length, inetAddress, port);
            client.send(sendPacket);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void receive() {
        try {
            receiveData = new byte[buffSize];
            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            client.receive(receivePacket);
            String strRes = new String(receivePacket.getData()).trim();
            String resquestId = "", strNumber_1 = "", strNumber_2 = "";
           
            int i = strRes.indexOf(';');
            resquestId = strRes.substring(0, i);
            
            
            int j = strRes.indexOf(',', i);
            strNumber_1 = strRes.substring(i+1, j);
            strNumber_2 = strRes.substring(j+1, strRes.length());
            int a = Integer.parseInt(strNumber_1);
            int b = Integer.parseInt(strNumber_2);
            
            String strMsg = resquestId + ";" + UCLN(a, b) + "," + BCNN(a, b) + "," + (a+b) + "," + (a*b);
            send(strMsg);
            
            client.close();
        } catch (IOException ex) {
            System.out.println("Err: IOException: " + ex);
        }
    }
    
    public int UCLN(int a, int b) {
        while (a%b != 0) {
            int c = a%b;
            a = b;
            b = c;
        }
        return b;
    }
    
    public int BCNN(int a, int b) {
        return a*b/UCLN(a, b);
    }
}
