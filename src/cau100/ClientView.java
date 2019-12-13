/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau100;

/**
 *
 * @author Dell
 */
public class ClientView {
    public static void main(String[] args) {
        Client client = new Client();
        client.send(";B16DCAT;100");
        client.receive();
    }
}
