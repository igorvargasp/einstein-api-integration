package com.br.einstein.api.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.br.einstein.api.model.Telegram;
import com.br.einstein.api.repository.TelegramRepository;
import com.br.einstein.api.service.ApiTelegram;


public class DataSeeder implements CommandLineRunner {

	@Autowired
	public TelegramRepository repository;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		
		
	}

}
