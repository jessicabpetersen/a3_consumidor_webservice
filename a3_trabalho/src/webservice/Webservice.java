/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import java.lang.reflect.Array;
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
        String request = target.path("v1/templates/list").request().get(String.class);

        JSONObject object = new JSONObject(request);

        JSONArray templates = object.getJSONArray("templates");
        List<Template> listaTemplate = new ArrayList<>();
        for (int i = 0; i < templates.length(); i++) {
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
        }
        return listaTemplate;
    }

    public String postTemplate(String nome, String mensagem) {
        String sRetorno;
        Client client = ClientBuilder.newClient();
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("aa", "bb");
        client.register(feature);

        Template template = new Template();
        template.setMessage(mensagem);
        template.setName(nome);
        template.setUid("394b9376e801b3b69f3346e77171f97b");

        WebTarget webTarget = client.target("https://api.handwrytten.com");
        Response response = webTarget.path("v1/templates/create").request().post(Entity.entity(template, MediaType.APPLICATION_JSON_TYPE));

        switch (response.getStatus()) {
            case 404:
                sRetorno = "O caminho especificado não existe (404 - not found).";
                break;
            case 401:
                sRetorno = "Usuário sem autorização. (UID not found)";
                break;
            case 500:
                sRetorno = "Houve um erro ao processar a requisição.";
                break;
            case 200:
                sRetorno = "Novo template cadastrado.";
                break;
                default:
                sRetorno = "Ocorreu um erro inesperado ao processar os dados.";
        }
        return sRetorno;
    }

}
