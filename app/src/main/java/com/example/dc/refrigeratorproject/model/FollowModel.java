package com.example.dc.refrigeratorproject.model;

import java.io.Serializable;

/**
 * Created by DC on 2019/5/14.
 */

public class FollowModel implements Serializable{
    private String name;
    private String intro;
    private boolean isFollow;
    private String img;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public boolean isFollow() {
        return isFollow;
    }

    public void setFollow(boolean follow) {
        isFollow = follow;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
