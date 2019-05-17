package com.example.dc.refrigeratorproject.resposeBean;

/**
 * Created by DC on 2019/5/16.
 */

public class User {
    /**
     * account : 1117
     * password : 7
     * userId : 30
     * userName : null
     * userImgUrl : null
     * userAddress : null
     * createByFridgeIds : null
     * sharedFridgeIds : null
     */

    private String account;
    private String password;
    private int userId;
    private String userName;
    private String userImgUrl;
    private String userAddress;
    private String createByFridgeIds;
    private String sharedFridgeIds;
    private int currentFridgeId;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImgUrl() {
        return userImgUrl;
    }

    public void setUserImgUrl(String userImgUrl) {
        this.userImgUrl = userImgUrl;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getCreateByFridgeIds() {
        return createByFridgeIds;
    }

    public void setCreateByFridgeIds(String createByFridgeIds) {
        this.createByFridgeIds = createByFridgeIds;
    }

    public String getSharedFridgeIds() {
        return sharedFridgeIds;
    }

    public void setSharedFridgeIds(String sharedFridgeIds) {
        this.sharedFridgeIds = sharedFridgeIds;
    }

    public int getCurrentFridgeId() {
        return currentFridgeId;
    }

    public void setCurrentFridgeId(int currentFridgeId) {
        this.currentFridgeId = currentFridgeId;
    }
}