package com.example.dc.refrigeratorproject.resposeBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DC on 2019/5/3.
 */

public class UserModel implements Serializable{
    private long account; //用户账户
    private String name;  //用户昵称
    private String head;  //用户头像
    private List<RefrigeratorModel> refrigeratorModelList;  //用户拥有的冰箱列表

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
