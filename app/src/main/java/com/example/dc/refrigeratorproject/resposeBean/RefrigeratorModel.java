package com.example.dc.refrigeratorproject.resposeBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DC on 2019/5/2.
 */

public class RefrigeratorModel implements Serializable{
    public String name; //冰箱名称
    public String address; //冰箱所在地区
    public UserModel creator; //创建者信息
    public List<RefrigeratorSharerModel> sharerModelList; //冰箱共享用户列表
    public boolean isCurrentRefrigerator; //是否是当前冰箱
    public boolean isCreatorBySelf; //该冰箱是否为当前账户创建的

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<RefrigeratorSharerModel> getSharerModelList() {
        return sharerModelList;
    }

    public void setSharerModelList(List<RefrigeratorSharerModel> sharerModelList) {
        this.sharerModelList = sharerModelList;
    }

    public boolean isCurrentRefrigerator() {
        return isCurrentRefrigerator;
    }

    public void setCurrentRefrigerator(boolean currentRefrigerator) {
        isCurrentRefrigerator = currentRefrigerator;
    }

    public UserModel getCreator() {
        return creator;
    }

    public void setCreator(UserModel creator) {
        this.creator = creator;
    }
}
