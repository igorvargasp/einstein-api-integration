package com.br.einstein.api.test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;


public class LiveChat {
private final String USER_AGENT = "Mozilla/5.0";
private final String LIVE_AGENT_URL = "https://d.la3-c2-ia4.salesforceliveagent.com";
private final String API_VERSION = "51";

 public static void main(String[] args) throws Exception {
LiveChat liveCh = new LiveChat();
// 1 - Get Session Details
JsonObject objSession = liveCh.getSessionDetails();

 // 2 - Send Chat Request to Server
liveCh.sendChatRequest(objSession);

 //Wait till agent accept the chat request
System.out.println("\n\nPlease Accept the Chat Request in Service Console.");
System.out.println("Then Press \"ENTER\" to continue...");
Scanner console = new Scanner(System.in);
console.nextLine();

 // 3 - Read Chat Details
liveCh.ReadChatDetails(objSession);


 // 4 - Send Chat Message
liveCh.SendChatMessage(objSession);

 //Wait till agent accept the chat request
System.out.println("\n\nPlease Accept the Chat Request in Service Console.");
System.out.println("Then Press \"ENTER\" to continue...");
console = new Scanner(System.in);
console.nextLine();

 liveCh.syncChatSession(objSession);

 // 5 - Read Chat Message Again
liveCh.ReadChatDetails(objSession);

 // 4 - Send Chat Message Again
liveCh.SendChatMessage(objSession);

}
private void SendChatMessage(JsonObject objSession) throws Exception{
Scanner console = new Scanner (System.in);
System.out.print("\n\nPlease enter chat message:");
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
private void ReadChatDetails(JsonObject objSession) throws Exception{
URL endpoint = new URL(LIVE_AGENT_URL + "/chat/rest/System/Messages");
HttpURLConnection con = (HttpURLConnection) endpoint.openConnection();
con.setRequestMethod("GET");

 // add request header
con.setRequestProperty("User-Agent", USER_AGENT);
con.setRequestProperty("X-LIVEAGENT-API-VERSION", "51");
con.setRequestProperty("X-LIVEAGENT-AFFINITY", objSession.getString("affinityToken"));
con.setRequestProperty("X-LIVEAGENT-SESSION-KEY", objSession.getString("key"));

 System.out.println("\n\nRead Message Response Code : " + con.getResponseCode());

 //Parse this JSON to get all the messages
JsonReader reader = Json.createReader(con.getInputStream());
JsonObject mresObj = reader.readObject();
reader.close();
System.out.println("\n\nMessage Details : " + mresObj.toString());
}
private void sendChatRequest(JsonObject objSession) throws Exception{
URL endpoint = new URL(LIVE_AGENT_URL + "/chat/rest/Chasitor/ChasitorInit");
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

 private JsonObject getSessionDetails() throws Exception {
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
private void syncChatSession(JsonObject objSession) throws Exception {
JsonObject resObj = null;
URL endpoint = new URL(LIVE_AGENT_URL + "/chat/rest/System/ResyncSession");
HttpURLConnection con = (HttpURLConnection) endpoint.openConnection();
con.setRequestMethod("GET");

 // add request header
con.setRequestProperty("User-Agent", USER_AGENT);
con.setRequestProperty("X-LIVEAGENT-API-VERSION", "51");
con.setRequestProperty("X-LIVEAGENT-AFFINITY", objSession.getString("affinityToken"));
con.setRequestProperty("X-LIVEAGENT-SESSION-KEY", objSession.getString("key"));

 System.out.println("\n\nSync Session Call Response Code : " + con.getResponseCode());
}
}