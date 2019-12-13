/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau102;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
            port = 1109;
            buffSize = 1024;
            inetAddress = InetAddress.getByName("localhost");
            client = new DatagramSocket();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void send(Student student) {
        
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(student);
            sendPacket = new DatagramPacket(baos.toByteArray(), baos.toByteArray().length, inetAddress, port);
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
            ByteArrayInputStream bais = new ByteArrayInputStream(receivePacket.getData());
            ObjectInputStream ois = new ObjectInputStream(bais);
            Student student = (Student) ois.readObject();
            if (student.getGpa() >= 3.7) student.setGpaLetter("A");
            if (student.getGpa() >= 3.0 && student.getGpa() < 3.7) student.setGpaLetter("B");
            if (student.getGpa() >= 2.0 && student.getGpa() < 3.0) student.setGpaLetter("C");
            if (student.getGpa() >= 1.0 && student.getGpa() < 2.0) student.setGpaLetter("D");
            if (student.getGpa() < 1.0) student.setGpaLetter("F");
            
            send(student);
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
