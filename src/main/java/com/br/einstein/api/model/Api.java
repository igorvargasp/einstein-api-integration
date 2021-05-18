package com.br.einstein.api.model;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.springframework.boot.jackson.JsonObjectSerializer;

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

}