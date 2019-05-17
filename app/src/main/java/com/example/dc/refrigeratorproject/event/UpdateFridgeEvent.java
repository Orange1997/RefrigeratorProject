package com.example.dc.refrigeratorproject.event;

/**
 * Created by DC on 2019/5/16.
 */

public class UpdateFridgeEvent {
    private String message;
    public  UpdateFridgeEvent(){

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
