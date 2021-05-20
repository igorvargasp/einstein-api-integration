package com.br.einstein.api.model;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;



public class Api {
    
private final String USER_AGENT = "Mozilla/5.0";
private final String LIVE_AGENT_URL = "https://d.la3-c2-ia4.salesforceliveagent.com";
private final String API_VERSION = "51";


public String getUser_Agent() {
    return USER_AGENT;
}

public String getLive_Agent_URL() {
    return LIVE_AGENT_URL;
}
   
public String getApi_Version() {
    return API_VERSION;
}





public void syncChatSession(JsonObject objSession) throws Exception {
    
    URL endpoint = new URL("https://d.la3-c2-ia4.salesforceliveagent.com"+ "/chat/rest/System/ResyncSession");
    HttpURLConnection con = (HttpURLConnection) endpoint.openConnection();
    con.setRequestMethod("GET");
    
     // add request header
    con.setRequestProperty("User-Agent", "Mozilla/5.0");
    con.setRequestProperty("X-LIVEAGENT-API-VERSION", "51");
    con.setRequestProperty("X-LIVEAGENT-AFFINITY", objSession.getString("affinityToken"));
    con.setRequestProperty("X-LIVEAGENT-SESSION-KEY", objSession.getString("key"));
    
     //System.out.println("\n\nSync Session Call Response Code : " + con.getResponseCode());
    }

    public void sendChatRequest(JsonObject objSession) throws Exception{
        //System.out.println("ObjSession"+objSession);
        
        URL endpoint = new URL("https://d.la3-c2-ia4.salesforceliveagent.com"+ "/chat/rest/Chasitor/ChasitorInit");
        HttpURLConnection con = (HttpURLConnection) endpoint.openConnection();
        // add request header
        con.setRequestProperty("X-LIVEAGENT-API-VERSION", "51");
        con.setRequestProperty("X-LIVEAGENT-AFFINITY", objSession.getString("affinityToken"));
        con.setRequestProperty("X-LIVEAGENT-SESSION-KEY", objSession.getString("key"));
        con.setRequestProperty("X-LIVEAGENT-SEQUENCE", "1");
        con.setRequestMethod("POST");
        // Send post request
        JsonArray array = Json.createArrayBuilder().build();
        
        String jsonPost = Json.createObjectBuilder().add("organizationId", "00D5Y0000024iD6")
        .add("deploymentId", "572i00000006rlJ").add("buttonId", "5735Y000000oyjs")
        .add("sessionId", objSession.getString("id")).add("userAgent", USER_AGENT).add("language", "en-US")
        .add("screenResolution", "1900x1080")
        .add("prechatDetails", array)
        .add("prechatEntities", array).add("receiveQueueUpdates", true).add("isPost", true).build().toString();
        
        /*Uncomment the below line to check the JSON request body*/
        // System.out.println("jsonPost:"+jsonPost);
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(jsonPost);
        wr.flush();
        wr.close();
       
        
         System.out.println("\n\nChat Request Call Response Code : " + con.getResponseCode());
        System.out.println("\n Responde message: " + con.getResponseMessage());
        }


            public JsonObject getSessionDetails() throws Exception {
                System.out.println("Detalhes da sessao");
            JsonObject resObj = null;
            URL endpoint = new URL(LIVE_AGENT_URL + "/chat/rest/System/SessionId");
            HttpURLConnection con = (HttpURLConnection) endpoint.openConnection();
            con.setRequestMethod("GET");
            
             // add request header
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("X-LIVEAGENT-API-VERSION", API_VERSION);
            con.setRequestProperty("X-LIVEAGENT-AFFINITY", "null");
            
             System.out.println("\n\nSession Call Response Code : " + con.getResponseCode());
            
             // Get the Session details
            JsonReader reader = Json.createReader(con.getInputStream());
            resObj = reader.readObject();
            reader.close();
            System.out.println("\n\nkey: " + resObj.getString("key"));
            System.out.println("id: " + resObj.getString("id"));
            System.out.println("clientPollTimeout: " + resObj.getInt("clientPollTimeout"));
            System.out.println("affinityToken: " + resObj.getString("affinityToken"));
            return resObj;
            }

}