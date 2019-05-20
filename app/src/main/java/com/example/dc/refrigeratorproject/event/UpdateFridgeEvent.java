package com.example.dc.refrigeratorproject.event;

/**
 * Created by DC on 2019/5/16.
 */

public class UpdateFridgeEvent {
    private int id;
    public  UpdateFridgeEvent(int id){
        this.id = id;
    }

    public int getMessage() {
        return id;
    }

    public void setMessage(int message) {
        this.id = message;
    }
}
