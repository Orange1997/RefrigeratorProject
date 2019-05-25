package com.example.dc.refrigeratorproject.resposeBean;

import java.io.Serializable;

/**
 * Created by DC on 2019/5/25.
 */

public class CommentRes implements Serializable{

    /**
     * noticeId : 5
     * userName : hhhh
     * content : hhhh
     * createTime : 1558713600000
     * userId : 2
     */

    private int noticeId;
    private String userName;
    private String content;
    private long createTime;
    private int userId;

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
