/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau101;

import cau100.Client;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.util.logging.PlatformLogger;

/**
 *
 * @author DELL
 */
public class Client1 {
    DatagramSocket client;
    DatagramPacket sendPacket, receivePacket;
    byte[] sendData, receiveData;
    public Client1(){
        try{
            client = new DatagramSocket();
            
        }catch(SocketException ex){
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    public void send(String str){
        try {
            sendData = str.getBytes();
            sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), 1108);
            client.send(sendPacket);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String receive(){
        try {
            receiveData = new byte[1024];
            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            client.receive(receivePacket);
            return new String(receivePacket.getData()).trim();
        } catch (IOException ex) {
            Logger.getLogger(Client1.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public static void main(String[] args) {
        Client1 cl1 = new Client1();
        String s = ";B16DCAT102,101";
        String ele = "";
        String spec_ele = "";
        cl1.send(s);
        String result = cl1.receive();
        String[] value = result.split(";");
        for(int i=0; i<value[1].length(); i++){
            if('a'<=value[1].charAt(i) && 'z'>=value[1].charAt(i) || 'A'<=value[1].charAt(i) && 'Z'>=value[1].charAt(i) || '0'<=value[1].charAt(i) && '9'>=value[1].charAt(i))
                ele = ele + Character.toString(value[1].charAt(i));
            else
                spec_ele = spec_ele + Character.toString(value[1].charAt(i));
        } 
        String resultData;
        resultData = ";" + ele + ";" + spec_ele;
        cl1.send(resultData);
    }
        
    
}
