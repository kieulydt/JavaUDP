/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau102;

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
 * @author DELL
 */
public class Client2 {
    DatagramSocket client;
    DatagramPacket sendPacket, receivePacket;
    byte[] sendData, receiveData;
    public Client2(){
        try {
            client = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(Client2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void send(String str){
        sendData = str.getBytes();
        try {
            sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), 1108);
            client.send(sendPacket);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public String receive(){
        receiveData = new byte[1024];
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        try {
            client.receive(receivePacket);
        } catch (IOException ex) {
            Logger.getLogger(Client2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new String(receivePacket.getData()).trim();
    }
    public static void main(String[] args) {
        Client2 cl2 = new Client2();
        String s = ";B16DCAT102;101";
        cl2.send(s);
        String result = cl2.receive();
        String[] value = result.split(";");
        String[] value1  = value[1].split(",");
        int min, max;
        min = Integer.parseInt(value1[0]);
        max = Integer.parseInt(value1[0]);
        for(int i=0; i<value1.length; i++){
            if(Integer.parseInt(value1[i])>max)
                max = Integer.parseInt(value1[i]);
            if(Integer.parseInt(value1[i])<min)
                min = Integer.parseInt(value1[i]);
        }
        String resultData = ";" + Integer.toString(max) + "," + Integer.toString(min);
        cl2.send(resultData);
        
        
        
    }
}
