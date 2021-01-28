/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.Template;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Jessica
 */
public class Webservice {
    
    public List<Template> getAllTemplates() throws JSONException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://api.handwrytten.com");
        //todos templates
        String request = target.path("v1/templates/list").request().get(String.class);
        
        JSONObject object = new JSONObject(request);
        
        JSONArray templates = object.getJSONArray("templates");
        List<Template> listaTemplate = new ArrayList<>();
        for(int i=0; i < templates.length(); i++){
            Template template = new Template();
            JSONObject t = templates.getJSONObject(i);
            template.setCategory_id(t.getInt("category_id"));
            template.setId(t.getInt("id"));
            template.setMessage(t.getString("message"));
            template.setName(t.getString("name"));
            template.setSignature("");
            template.setSignature2("");
            template.setWishes("");
            listaTemplate.add(template);
            System.out.println("ID: "+template.getId()+" _ Id Categoria: "+template.getCategory_id()+
                    "_ Name: "+template.getName());
        }
        return listaTemplate;
    }

    public void postTemplate(String nome, String mensagem, String uid) {
        Client client = ClientBuilder.newClient();
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("aa", "bb");
        client.register(feature);

        Template template = new Template();
        template.setMessage(mensagem);
        template.setName(nome);
        template.setUid("Token");

        WebTarget webTarget = client.target("https://api.handwrytten.com");
        Response response = webTarget.path("v1/templates/create").request().post(Entity.entity(template, MediaType.APPLICATION_JSON_TYPE));
        System.out.println(response.getStatus()); // 201 - ok 401 - nao autorizado 500 - outros erros, por exemplo, nome repetido
        System.out.println(response.getStatusInfo());
    }

}