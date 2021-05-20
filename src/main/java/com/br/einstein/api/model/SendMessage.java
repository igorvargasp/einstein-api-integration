package com.br.einstein.api.model;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.json.Json;
import javax.json.JsonObject;

public class SendMessage{
    
    private JsonObject message;

    
    
    

    public JsonObject getMessage() {
        return message;
    }



    public void setMessage(JsonObject message) {
        this.message = message;
    }



    public void SendChatMessage(JsonObject message) throws Exception{
    	var console = new Scanner(System.in);
        
       
			System.out.print("\n\nDigite a sua respota:");
			 // Get what the user types.
			
			 URL endpoint = new URL(new Api().getLive_Agent_URL() + "/chat/rest/Chasitor/ChatMessage");
			HttpURLConnection con = (HttpURLConnection) endpoint.openConnection();
			
			 // add request header
			con.setRequestProperty("User-Agent", new Api().getUser_Agent());
			con.setRequestProperty("X-LIVEAGENT-API-VERSION", new Api().getApi_Version());
			con.setRequestProperty("X-LIVEAGENT-AFFINITY", message.getString("affinityToken"));
			con.setRequestProperty("X-LIVEAGENT-SESSION-KEY", message.getString("key"));
			
			 con.setRequestMethod("POST");
			
			 // Send post request
			String chatMsg = console.nextLine();
			String jsonPost = Json.createObjectBuilder().add("text", chatMsg).add("isPost", true).build().toString();
		System.out.println("jsonPost:"+jsonPost);//Uncomment this line to check the JSON request body
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(jsonPost);
			wr.flush();
			wr.close();
			
			 //System.out.println("Send Message Response Code : " + con.getResponseCode());
			/*if(chatMsg.equals("sair") || chatMsg.equals("Sair") ) {
				return "sair";
			}
			return "continuar";*/
		
        }

}
