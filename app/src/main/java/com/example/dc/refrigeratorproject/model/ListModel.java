package com.example.dc.refrigeratorproject.model;

import java.io.Serializable;

/**
 * Created by DC on 2019/5/11.
 */

public class ListModel implements Serializable{
    private Long time;
    private String title;

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
