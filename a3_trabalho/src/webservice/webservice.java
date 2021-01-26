/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Jessica
 */
public class webservice {
    
    public void getAllTemplates(){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://api.handwrytten.com");
        //todos templates
        String request = target.path("v1/templates/list").request().get(String.class);
        JSONObject my_obj = new JSONObject();
        JSONArray elenco = my_obj.getJSONArray(request);
        System.out.println(request);
    }
}
