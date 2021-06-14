package com.br.einstein.api.model;




import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import com.fasterxml.jackson.annotation.JsonProperty;



@Document(collection = "test_db")
public class Telegram {
	
	@Id
	@JsonProperty
	private String id;
	@JsonProperty
	private String message;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

	

	
	
}
