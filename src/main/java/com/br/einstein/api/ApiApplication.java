package com.br.einstein.api;

import java.util.Scanner;

import javax.json.JsonObject;

import com.br.einstein.api.model.Api;
import com.br.einstein.api.model.MessageResponse;
import com.br.einstein.api.model.SendMessage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ApiApplication.class, args);
		Api api = new Api();
		
	
		String sair = "";
		JsonObject objSession = api.getSessionDetails();
		api.sendChatRequest(objSession);
		do {
			
		
		new MessageResponse().ReadChatDetails(objSession);
		 //String saida = 
		new SendMessage().SendChatMessage(objSession);
		api.syncChatSession(objSession);
		
		/*if(saida.equals("sair")) {
		break;	
		}*/
		}while(!sair.equals("sair"));
		
		
		
	}

}
