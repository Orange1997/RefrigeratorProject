package com.example.dc.refrigeratorproject.resposeBean;

import java.io.Serializable;

/**
 * Created by DC on 2019/5/24.
 */

public class NoticeRes implements Serializable{

    /**
     * noticeId : 1
     * noticeTitle : 年轻人不知道的生活小妙招，别看他‘‘小’’，可省了不少钱！
     * noticeImgUr : https://pics2.baidu.com/feed/8cb1cb13495409238efa619ba8070c0db2de4923.jpeg?token=9b0a6aa66f7f2b8e27f85587d3c7caf0&s=33829C49E26313071BB5A8A303008013
     * noticeUrl : https://baijiahao.baidu.com/s?id=1634242833689263171&wfr=spider&for=pc
     * createTime : 05-23 07:24
     * author :
     生活每日优
     * noticeType : null
     */

    private int noticeId;
    private String noticeTitle;
    private String noticeImgUr;
    private String noticeUrl;
    private String createTime;
    private String author;
    private int noticeType;
    private String des;

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeImgUr() {
        return noticeImgUr;
    }

    public void setNoticeImgUr(String noticeImgUr) {
        this.noticeImgUr = noticeImgUr;
    }

    public String getNoticeUrl() {
        return noticeUrl;
    }

    public void setNoticeUrl(String noticeUrl) {
        this.noticeUrl = noticeUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(int noticeType) {
        this.noticeType = noticeType;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
