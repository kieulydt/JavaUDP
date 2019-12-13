/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau101;

/**
 *
 * @author Ken
 */
public class ClientView {
    public static void main(String[] args) {
        ClientControl client = new ClientControl();
        client.send(";B16DCAT;101");
        client.receive();
    }
}
