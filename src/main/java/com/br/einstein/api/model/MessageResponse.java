package com.br.einstein.api.model;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

import org.json.*;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONValue;
public class MessageResponse{
    private JsonObject message;
    
    


    public JsonObject getMessage() {
        return message;
    }

    public void setMessage(JsonObject message) {
        this.message = message;
    }

    
    public void ReadChatDetails(JsonObject message) throws Exception{
    	URL endpoint = new URL(new Api().getLive_Agent_URL() + "/chat/rest/System/Messages");
    	HttpURLConnection con = (HttpURLConnection) endpoint.openConnection();
    	con.setRequestMethod("GET");

    	 // add request header
    	con.setRequestProperty("User-Agent", new Api().getUser_Agent());
    	con.setRequestProperty("X-LIVEAGENT-API-VERSION", new Api().getApi_Version());
    	con.setRequestProperty("X-LIVEAGENT-AFFINITY", message.getString("affinityToken"));
    	con.setRequestProperty("X-LIVEAGENT-SESSION-KEY", message.getString("key"));

    	 con.getResponseCode();
    	
    	 
    	 //Parse this JSON to get all the messages
    	JsonReader reader = Json.createReader(con.getInputStream());
    	JsonObject mresObj = reader.readObject();
    	JsonArray elements = mresObj.getJsonArray("messages");
    	//	cria um array do tamanho dos objetos messages
    	String result = "";
    	String resultText1 = "";
    	
    		  	
    	reader.close();
    	
    	for (int i = 0; i < elements.size(); i++) {

	  		//faço uma iteração
            JsonObject jsonObject1 = elements.getJsonObject(i);
            // recebo os objetos message
            JsonObject text1 = jsonObject1.getJsonObject("message");
            // dentro do objeto message pego a string text
            Object testando = text1.getJsonString("text");
            // pego a string do objeto testando e passo para a string result
            result = String.valueOf(testando);
            if(!result.equals("null")) {
            	resultText1  = result;
            	System.out.println(resultText1.replaceAll("^\"+|\"+$", ""));
            	//System.out.println("mensagem->"+resultText1.replaceAll("^\"+|\"+$", ""));
            	//System.out.println("mensagem->"+items.toString());
            }
            
        }
    	
    		
    	
    	
    	for (int i = 0; i < elements.size(); i++) {

	  		//faço uma iteração
            JsonObject jsonObject1 = elements.getJsonObject(i);
            // recebo os objetos message
            JsonObject text1 = jsonObject1.getJsonObject("message");
            JsonArray testando = text1.getJsonArray("items");
                
                result = String.valueOf(testando); 
                String[] resultSplit = new String[result.length()];
                resultSplit = result.split(",");
                
                
                JSONArray arr = new JSONArray(resultSplit);
                
                for(int x=0; x < arr.length(); x++) {
                	 String obj = arr.getString(x);
                	 if(!obj.equals("null")) {       
                		
     
                 	String nova = obj.replace("{", "").replace("}", "").replace(":", "").replace("[","").replace("]", "").replace("\"","").replace("text", "");
                 	String sub = nova.replace("null", "");
                 	
                 			if(!sub.equals("")) {
                 				System.out.println("->"+sub);	
                 			}
                 			
                 			
                 					
                 		
                 	
                 	
                 
                 }
                	
                	   
                }
                  	
            	
                    // pego a string do objeto testando e passo para a string result          
                    
                   
                    
                }
              
              
            }
            
           
            
         
        }
    	 
  	
    	
    

