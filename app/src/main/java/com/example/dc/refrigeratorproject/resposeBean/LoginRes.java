package com.example.dc.refrigeratorproject.resposeBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DC on 2019/5/3.
 */

public class LoginRes implements Serializable{


    private int status;
    private List<User> data;

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
