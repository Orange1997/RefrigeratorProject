package com.example.dc.refrigeratorproject.resposeBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DC on 2019/5/16.
 */

public class RegisterRes implements Serializable {

    /**
     * status : OK
     * data : [{"account":"1117","password":"7","userId":30,"userName":null,"userImgUrl":null,"userAddress":null,"createByFridgeIds":null,"sharedFridgeIds":null}]
     */

    private List<User> data;
    /**
     * status : 0
     * data : []
     */

    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }


}
