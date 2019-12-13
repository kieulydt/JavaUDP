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
public class Client3 {
    static DatagramSocket client;
    DatagramPacket sendPacket, receivePacket;
    byte[] sendData, receiveData;
    public Client3(){
        try {
            client = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(Client2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void send(String str){
        sendData = str.getBytes();
        try {
            sendPacket  = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), 1108);
            client.send(sendPacket);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String receive(){
        receiveData = new byte[1024];
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        try {
            client.receive(receivePacket);
        } catch (IOException ex) {
            Logger.getLogger(Client3.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new String(receivePacket.getData()).trim();
    }
    public static boolean ss(int a, String[] b){
        for(int i=0; i<b.length; i++){
            if(a==Integer.parseInt(b[i]))
                return false;
        }
        return true;
    }
    public static void close(){
       if(client!=null){
           try{
               client.close();
           } catch(Exception e){
               System.out.println(e);
           }
           
       }
    }
    public static void main(String[] args) {
        Client3 cl3 = new Client3();
        String s = ";B16DCAT102;101";
        cl3.send(s);
        String result = cl3.receive();
        String[] value = result.split(";");
        String kq = "";
        int n = Integer.parseInt(value[1]);
        String[] value1 = value[2].split(",");
        for(int i=0; i<=n; i++){
            if(ss(i,value1)){
                kq = kq + Integer.toString(i) + ",";
            }
        }
        kq = kq.substring(0, kq.length()-1);
        cl3.send(kq);
        close();
    }
}
