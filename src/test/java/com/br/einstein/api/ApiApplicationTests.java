package com.br.einstein.api;



import javax.json.JsonObject;

import com.br.einstein.api.model.Api;
import com.br.einstein.api.model.MessageResponse;
import com.br.einstein.api.model.SendMessage;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApiApplicationTests {

	@Test
	void contextLoads() throws Exception {
		Api api = new Api();
		JsonObject objSession = api.getSessionDetails();
		//System.out.println("ObjSession"+objSession);
		api.sendChatRequest(objSession);
		System.out.println("Digite \"Entrar\" Para conversar com o Blitz");
		/*Scanner console = new Scanner(System.in);
		console.nextLine();*/

		new MessageResponse().ReadChatDetails(objSession);
		new SendMessage().SendChatMessage(objSession);
		api.syncChatSession(objSession);
	}

}
