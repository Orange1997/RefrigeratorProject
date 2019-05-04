package com.example.dc.refrigeratorproject.resposeBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DC on 2019/5/2.
 */

public class RefrigeratorModel implements Serializable{
    public String name;
    public String address;
    public UserModel creator;
    public List<RefrigeratorSharerModel> sharerModelList;
    public boolean isCurrentRefrigerator;

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
