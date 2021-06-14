package com.br.einstein.api.service;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.json.JSONArray;

import com.br.einstein.api.model.Telegram;





public class ApiEinstein {

	private final String USER_AGENT = "Mozilla/5.0";
	private final String LIVE_AGENT_URL = "https://d.la3-c2-ia4.salesforceliveagent.com";
	private final String API_VERSION = "52";
	public JsonObject objSession;

	public String getUser_Agent() {
	    return USER_AGENT;
	}

	public String getLive_Agent_URL() {
	    return LIVE_AGENT_URL;
	}
	   
	public String getApi_Version() {
	    return API_VERSION;
	}


	


	// FAZ A SINCRONIZAÇÃO DO CHAT
	public synchronized  void syncChatSession(JsonObject objSession) throws Exception {
	    
	    URL endpoint = new URL("https://d.la3-c2-ia4.salesforceliveagent.com"+ "/chat/rest/System/ResyncSession");
	    HttpURLConnection con = (HttpURLConnection) endpoint.openConnection();
	    con.setRequestMethod("GET");
	    
	     // add request header
	    con.setRequestProperty("User-Agent", "Mozilla/5.0");
	    con.setRequestProperty("X-LIVEAGENT-API-VERSION", "52");
	    con.setRequestProperty("X-LIVEAGENT-AFFINITY", objSession.getString("affinityToken"));
	    con.setRequestProperty("X-LIVEAGENT-SESSION-KEY", objSession.getString("key"));
	    
	     //con.getResponseCode();
	    con.getResponseCode();
	    }
		// ENVIA UM REQUEST PARA O CHAT
	    public synchronized  void sendChatRequest(JsonObject objSession) throws Exception{
	        //System.out.println("ObjSession"+objSession);
	        
	        URL endpoint = new URL("https://d.la3-c2-ia4.salesforceliveagent.com"+ "/chat/rest/Chasitor/ChasitorInit");
	        HttpURLConnection con = (HttpURLConnection) endpoint.openConnection();
	        // add request header
	        con.setRequestProperty("X-LIVEAGENT-API-VERSION", "52");
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
	       
	        
	         con.getResponseCode();
	        con.getResponseMessage();
	        System.out.println("Digite Entrar para começar o chat");
	        }

	    		//RECEBE OS DADOS DA SESSÃO
	            public  synchronized JsonObject getSessionDetails() throws Exception {
	               
	            JsonObject resObj;
	           
	            URL endpoint = new URL(LIVE_AGENT_URL + "/chat/rest/System/SessionId");
	            HttpURLConnection con = (HttpURLConnection) endpoint.openConnection();
	            con.setRequestMethod("GET");
	            
	             // add request header
	            con.setRequestProperty("User-Agent", USER_AGENT);
	            con.setRequestProperty("X-LIVEAGENT-API-VERSION", API_VERSION);
	            con.setRequestProperty("X-LIVEAGENT-AFFINITY", "null");
	            
	             con.getResponseCode();
	            
	             // Get the Session details
	            JsonReader reader = Json.createReader(con.getInputStream());
	            resObj = reader.readObject();
	            reader.close();
	            /*System.out.println("\n\nkey: " + resObj.getString("key"));
	            System.out.println("id: " + resObj.getString("id"));
	            System.out.println("clientPollTimeout: " + resObj.getInt("clientPollTimeout"));
	            System.out.println("affinityToken: " + resObj.getString("affinityToken"));*/
	            return resObj;
	            }

	            
	            
	            //RECEBE OS DADOS DO CHAT
	            public synchronized  ArrayList<Telegram> ReadChatDetails(JsonObject message) throws Exception{
	            	ArrayList<Telegram> list = new ArrayList<Telegram>();
	            	ArrayList<Telegram> itemsList = new ArrayList<Telegram>();
	            	Set<Telegram> set = new LinkedHashSet<>();
	            	URL endpoint = new URL(LIVE_AGENT_URL  + "/chat/rest/System/Messages");
	            	HttpURLConnection con = (HttpURLConnection) endpoint.openConnection();
	            	con.setRequestMethod("GET");

	            	 // add request header
	            	con.setRequestProperty("User-Agent", USER_AGENT);
	            	con.setRequestProperty("X-LIVEAGENT-API-VERSION", API_VERSION);
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
	            	Telegram obj = new Telegram();
	            	obj.setMessage(resultText1);
	            	list.add(obj);
	            	String sub = "";
	            	
	            	
	            	
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
	                        	 String obj1 = arr.getString(x);
	                        	 if(!obj1.equals("null")) {       
	                        		
	             
	                         	String nova = obj1.replace("{", "").replace("}", "").replace(":", "").replace("[","").replace("]", "").replace("\"","").replace("text", "");
	                         	sub = nova.replace("null", "");
	                         	Telegram obj2 = new Telegram();
	                         			if(!sub.equals("")) {
	                         				System.out.println("->"+sub);
	                         				
	                    	            	obj2.setMessage(sub);
	                    	            	 System.out.println("adiciona->"+sub);
	                    	            	 list.add(obj2);	
	                         			}     
	                         			
	                         
	                         }
	                        	
	                        	   
	                     }
	                          	
	                    	
	                                   
	                            
	                           
	                            
	                }
	            	
	            
	            	
	            	
	            	return list;
	                      
	            }
	            
	            
	            
	            
	            
	            
	            
	                    
	            //ENVIA AS MENSAGENS PARA O CHAT       
	            public synchronized void SendChatMessage(JsonObject message,  String update) throws Exception{
	            	
	                
	               
	        			
	        			 // Get what the user types.
	        			
	        			 URL endpoint = new URL(LIVE_AGENT_URL + "/chat/rest/Chasitor/ChatMessage");
	        			HttpURLConnection con = (HttpURLConnection) endpoint.openConnection();
	        			
	        			 // add request header
	        			con.setRequestProperty("User-Agent", USER_AGENT);
	        			con.setRequestProperty("X-LIVEAGENT-API-VERSION", API_VERSION);
	        			con.setRequestProperty("X-LIVEAGENT-AFFINITY", message.getString("affinityToken"));
	        			con.setRequestProperty("X-LIVEAGENT-SESSION-KEY", message.getString("key"));
	        			
	        			 con.setRequestMethod("POST");
	        			
	        			 // Send post request
	        			//String chatMsg = response;
	        			String jsonPost = Json.createObjectBuilder().add("text", update).add("isPost", true).build().toString();
	        			//System.out.println("jsonPost:"+jsonPost);//Uncomment this line to check the JSON request body
	        			con.setDoOutput(true);
	        			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	        			wr.writeBytes(jsonPost);
	        			wr.flush();
	        			wr.close();
	        			
	        			 con.getResponseCode();
	        			
	        		
	                }
	            
	
	
}
