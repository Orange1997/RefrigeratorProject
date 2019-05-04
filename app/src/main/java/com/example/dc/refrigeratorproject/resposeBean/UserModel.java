package com.example.dc.refrigeratorproject.resposeBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DC on 2019/5/3.
 */

public class UserModel implements Serializable{
    private long account;
    private String name;
    private String head;
    private List<RefrigeratorModel> refrigeratorModelList;

    public long getAccount() {
        return account;
    }

    public void setAccount(long account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public List<RefrigeratorModel> getRefrigeratorModelList() {
        return refrigeratorModelList;
    }

    public void setRefrigeratorModelList(List<RefrigeratorModel> refrigeratorModelList) {
        this.refrigeratorModelList = refrigeratorModelList;
    }
}
