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
public class ServerView {
    public static void main(String[] args) {
        Server server = new Server();
        server.listening();
        server.send();
    }
}
