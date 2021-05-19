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
		JsonObject objSession = api.getSessionDetails();
		//System.out.println("ObjSession"+objSession);
		api.sendChatRequest(objSession);
		System.out.println("Digite \"Entrar\" Para conversar com o Blitz e \"Sair\" para finalizar sua sessão");
		Scanner console = new Scanner(System.in);
		console.nextLine();	
		
		do {
		new MessageResponse().ReadChatDetails(objSession);
		new SendMessage().SendChatMessage(objSession);
		api.syncChatSession(objSession);	
		}while(!console.equals("sair"));
		
		
		
	}

}
