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
            port = 1109;
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
                ByteArrayInputStream bais = new ByteArrayInputStream(receivePacket.getData());
                ObjectInputStream ois = new ObjectInputStream(bais);
                Student student = (Student) ois.readObject();
                System.out.println(student);
                student.setGpa((float) 3.8);
                student.setId(001);
                
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(student);
                sendPacket = new DatagramPacket(baos.toByteArray(), 
                        baos.toByteArray().length, receivePacket.getSocketAddress());
                server.send(sendPacket);
            } catch (IOException ex) {
                Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }  
}
