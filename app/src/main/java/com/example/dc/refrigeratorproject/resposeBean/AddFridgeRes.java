package com.example.dc.refrigeratorproject.resposeBean;

import java.io.Serializable;

/**
 * Created by DC on 2019/5/16.
 */

public class AddFridgeRes implements Serializable{

    /**
     * fridgeId : 69
     */

    private int fridgeId;

    public int getFridgeId() {
        return fridgeId;
    }

    public void setFridgeId(int fridgeId) {
        this.fridgeId = fridgeId;
    }
}
