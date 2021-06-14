package com.br.einstein.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import com.br.einstein.api.model.Telegram;
import com.br.einstein.api.repository.TelegramRepository;
import com.br.einstein.api.service.ApiTelegram;


@SpringBootApplication
@EnableAsync
public class ApiApplication {

	@Autowired
	public static TelegramRepository repository;
	
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(ApiApplication.class, args);
		/*ApiEinstein api = new ApiEinstein();
		Message messages = new Message();
		Update update = new Update();
    	//Chat chat = new Chat();
    	messages.getReplyToMessage();
		String sair = "";
		JsonObject objSession = api.getSessionDetails();
		api.sendChatRequest(objSession);
		
		new ApiTelegram().registerBot();
		
		do {
		List<String> list =  new ApiEinstein().ReadChatDetails(objSession);

    	//chat.setId((long) 1372667777);
    	messages.setText(list.toString());  	
    	//messages.setChat(chat);
    	update.setMessage(messages);
    	new ApiTelegram().onUpdateReceived(update);
		new ApiEinstein().SendChatMessage(objSession);		
		api.syncChatSession(objSession);
		}while(!sair.equals("sair"));*/
		new ApiTelegram().registerBot();
		//new ApiTelegram().run(args);
		
		
	
		
		
		
	}
	
	

}
