/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3.consumidor.webservice;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author Jessica
 */
public class A3ConsumidorWebservice {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://api.handwrytten.com");
        //todos templates
        String request = target.path("v1/templates/list").request().get(String.class);
        System.out.println(request);
    }
    
}
