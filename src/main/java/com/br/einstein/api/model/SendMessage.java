package com.br.einstein.api.model;

import java.net.URL;
import java.util.Scanner;

import javax.json.JsonObject;

public class SendMessage extends Api {
    
    private String message;


    

    public SendMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private void SendChatMessage(JsonObject objSession) throws Exception{
        Scanner console = new Scanner (System.in);
        System.out.print("\n\nDigite a sua respota:");
        String chatMsg = console.nextLine(); // Get what the user types.
        
         URL endpoint = new URL(LIVE_AGENT_URL + "/chat/rest/Chasitor/ChatMessage");
        HttpURLConnection con = (HttpURLConnection) endpoint.openConnection();
        
         // add request header
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("X-LIVEAGENT-API-VERSION", "51");
        con.setRequestProperty("X-LIVEAGENT-AFFINITY", objSession.getString("affinityToken"));
        con.setRequestProperty("X-LIVEAGENT-SESSION-KEY", objSession.getString("key"));
        
         con.setRequestMethod("POST");
        
         // Send post request
        String jsonPost = Json.createObjectBuilder().add("text", chatMsg).add("isPost", true).build().toString();
        //System.out.println("jsonPost:"+jsonPost);//Uncomment this line to check the JSON request body
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(jsonPost);
        wr.flush();
        wr.close();
        
         System.out.println("Send Message Response Code : " + con.getResponseCode());
        }

}
