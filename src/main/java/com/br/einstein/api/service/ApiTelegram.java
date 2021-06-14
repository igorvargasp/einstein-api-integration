package com.br.einstein.api.service;


import java.util.ArrayList;
import java.util.List;

import javax.json.JsonObject;
import javax.json.JsonValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import com.br.einstein.api.model.Telegram;
import com.br.einstein.api.repository.TelegramRepository;
import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.response.BaseResponse;


@Component
public class ApiTelegram<R> extends TelegramLongPollingBot{

	 
	
	public final String BOT_TOKEN = "1897173711:AAGM61uf9_YpUuYhBm84UM1e39EYLwM4WwM";
	public final String URL = "https://api.telegram.org/bot"+BOT_TOKEN+"/getUpdates";
	private String idUser = "";
	public int cont = 0;
	public ApiEinstein  apiEinstein = new ApiEinstein();
	public JsonObject objSession;
	
	@Autowired
	public TelegramRepository repository;
	
	
	public void registerBot() {
		try {
			TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
			botsApi.registerBot(new ApiTelegram());
			
		} catch (TelegramApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	@Override
	public String getBotUsername() {
		// TODO Auto-generated method stub
		return "Bliitzzbot";
	}


	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return BOT_TOKEN;
	}




	
	public void onUpdateReceived(Update update) {
		
		
		
		try {
			String msg;
			if(cont == 0) {
				
				String message = update.getMessage().getText();
				System.out.println(message);
				if(!message.isEmpty()) {
					
						
					objSession = apiEinstein.getSessionDetails();
			    	apiEinstein.sendChatRequest(objSession);        
				    ArrayList<Telegram> list = new ArrayList<Telegram>();
				    
				    list.addAll(new ApiEinstein().ReadChatDetails(objSession));	
				    list.addAll(new ApiEinstein().ReadChatDetails(objSession));	
				    list.addAll(new ApiEinstein().ReadChatDetails(objSession));	
				    list.addAll(new ApiEinstein().ReadChatDetails(objSession));	
				    list.addAll(new ApiEinstein().ReadChatDetails(objSession));	
				    ArrayList<Telegram> list2 =  list;
				    
				    for (Telegram telegram : list2) {
						 
						 String replace= telegram.getMessage().replace("\"","");
						 if(!replace.isEmpty()) {
							 sendMsg(update.getMessage().getChatId().toString(),replace);		 
						 }
						
			    }
					   			
					new ApiEinstein().SendChatMessage(objSession, message);		
					apiEinstein.syncChatSession(objSession);	
				cont=1;	
				}
				
			}else if(cont == 1) {
				apiEinstein.sendChatRequest(objSession);	
				String message = "login";					      
				 new ApiEinstein().ReadChatDetails(objSession); 
					new ApiEinstein().SendChatMessage(objSession, message);		
					apiEinstein.syncChatSession(objSession);
				 cont=2;
			
					if(cont == 2) {
															
						apiEinstein.sendChatRequest(objSession);
						String messagee = update.getMessage().getText();
							    
						List<Telegram> listt =  new ApiEinstein().ReadChatDetails(objSession);
						//System.out.println("list2->"+list.toString());
						String replace;
						for (Telegram string : listt) {
						 replace= string.getMessage().replace("\"","");
						 sendMsg(update.getMessage().getChatId().toString(),replace);	
							new ApiEinstein().SendChatMessage(objSession, messagee);		
							apiEinstein.syncChatSession(objSession);
						 
						}
						
						cont=3;
					}
			
			}else if(cont == 3) {
					
				
				
					apiEinstein.sendChatRequest(objSession);	
					String message = update.getMessage().getText();
						      
					List<Telegram> list =  new ApiEinstein().ReadChatDetails(objSession);
					//System.out.println("list2->"+list.toString());
					String replace;
					for (Telegram string : list) {
					 replace= string.getMessage().replace("\"","");
					 sendMsg(update.getMessage().getChatId().toString(),replace);	
						new ApiEinstein().SendChatMessage(objSession, message);		
						apiEinstein.syncChatSession(objSession);
					
					}	
				
			
				
			
			}
				
			
						
			
				
				
			
			 
			
				
		
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public synchronized void sendMsg(String chatId, String s) throws Exception {
		
        SendMessage sendMessage = new SendMessage();
        //sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
       
        sendMessage.setText(s);
        
        
        
		
        try {
        	
        		
        		execute(sendMessage);
        	
        	
        	//methodA(chatId);
        	
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        
       
    }




	//@Async
	public String getId() {
		return idUser;
	}



	public void setId(String id) {
		this.idUser = id;
	}



	
	

	
}
