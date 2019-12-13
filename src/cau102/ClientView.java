/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau102;

/**
 *
 * @author Dell
 */
public class ClientView {
    public static void main(String[] args) {
        ClientControl client = new ClientControl();
        Student student = new Student("B16DCAT");
        client.send(student);
        client.receive();
    }
}
