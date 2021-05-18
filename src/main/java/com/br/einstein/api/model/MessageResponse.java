package com.br.einstein.api.model;

import javax.json.JsonObject;

public class MessageResponse extends Api {
    private JsonObject message;

    

    public MessageResponse(JsonObject message) {
        this.message = message;
    }

    public JsonObject getMessage() {
        return message;
    }

    public void setMessage(JsonObject message) {
        this.message = message;
    }

    
    
}
