package com.br.einstein.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.einstein.api.model.Telegram;
import com.br.einstein.api.repository.TelegramRepository;

@RestController
@RequestMapping("https://api.telegram.org/bot1897173711:AAGM61uf9_YpUuYhBm84UM1e39EYLwM4WwM/getUpdates")
public class Controller {

	
	 @Autowired
	 private TelegramRepository repository;
	 
	 
	

}
