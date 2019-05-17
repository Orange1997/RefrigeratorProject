package com.example.dc.refrigeratorproject.resposeBean;

import java.io.Serializable;

/**
 * Created by DC on 2019/5/16.
 */

public class RefrigeratorListRes implements Serializable{

    /**
     * fridgeId : 84
     * fridgeName : sss
     * address : 江苏省湖州市建德市
     * invitationCode : 22222
     * userId : 37
     */

    private int fridgeId;
    private String fridgeName;
    private String address;
    private String invitationCode;
    private int userId;

    public int getFridgeId() {
        return fridgeId;
    }

    public void setFridgeId(int fridgeId) {
        this.fridgeId = fridgeId;
    }

    public String getFridgeName() {
        return fridgeName;
    }

    public void setFridgeName(String fridgeName) {
        this.fridgeName = fridgeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
