package com.br.einstein.api.model;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class MessageResponse{
    private JsonObject message;

    


    public JsonObject getMessage() {
        return message;
    }

    public void setMessage(JsonObject message) {
        this.message = message;
    }

    
    public void ReadChatDetails(JsonObject message) throws Exception{
        System.out.println("Chat Details");
        URL endpoint = new URL(new Api().getLive_Agent_URL() + "/chat/rest/System/Messages");
        HttpURLConnection con = (HttpURLConnection) endpoint.openConnection();
        con.setRequestMethod("GET");
        
         // add request header
        con.setRequestProperty("User-Agent", new Api().getUser_Agent());
        con.setRequestProperty("X-LIVEAGENT-API-VERSION", new Api().getApi_Version());
        con.setRequestProperty("X-LIVEAGENT-AFFINITY", message.getString("affinityToken"));
        con.setRequestProperty("X-LIVEAGENT-SESSION-KEY", message.getString("key"));
        
         System.out.println("\n\nRead Message Response Code : " + con.getResponseCode());
        
         //Parse this JSON to get all the messages
        JsonReader reader = Json.createReader(con.getInputStream());
        JsonObject mresObj = reader.readObject();
        reader.close();
        System.out.println("\n\nMessage Details : " + mresObj.toString());
        }
    
}
