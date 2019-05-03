package com.example.dc.refrigeratorproject.resposeBean;

import java.io.Serializable;

/**
 * Created by DC on 2019/5/2.
 * 冰箱共享者数据结构
 */

public class RefrigeratorSharerModel implements Serializable{
    public long sharerAccount;
    public String sharerName;
    public String sharerHeadUrl;
    public boolean isCreator;

    public long getSharerAccount() {
        return sharerAccount;
    }

    public void setSharerAccount(long sharerAccount) {
        this.sharerAccount = sharerAccount;
    }

    public String getSharerName() {
        return sharerName;
    }

    public void setSharerName(String sharerName) {
        this.sharerName = sharerName;
    }

    public String getSharerHeadUrl() {
        return sharerHeadUrl;
    }

    public void setSharerHeadUrl(String sharerHead) {
        this.sharerHeadUrl = sharerHead;
    }

    public boolean isCreator() {
        return isCreator;
    }

    public void setCreator(boolean creator) {
        isCreator = creator;
    }
}
