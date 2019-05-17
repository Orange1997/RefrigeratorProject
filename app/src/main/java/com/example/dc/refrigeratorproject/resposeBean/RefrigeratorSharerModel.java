package com.example.dc.refrigeratorproject.resposeBean;

import java.io.Serializable;

/**
 * Created by DC on 2019/5/2.
 * 冰箱共享者数据结构
 */

public class RefrigeratorSharerModel implements Serializable{
    public LoginRes sharer; //共享者信息
    public boolean isCreator; //是否为本人创建

    public boolean isCreator() {
        return isCreator;
    }

    public void setCreator(boolean creator) {
        isCreator = creator;
    }

    public LoginRes getSharer() {
        return sharer;
    }

    public void setSharer(LoginRes sharer) {
        this.sharer = sharer;
    }
}
