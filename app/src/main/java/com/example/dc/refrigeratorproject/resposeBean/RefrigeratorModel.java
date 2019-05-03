package com.example.dc.refrigeratorproject.resposeBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DC on 2019/5/2.
 */

public class RefrigeratorModel implements Serializable{
    public String name;
    public String address;
    public long creatorAccount;
    public String creatorName;
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

    public long getCreatorAccount() {
        return creatorAccount;
    }

    public void setCreatorAccount(long creatorAccount) {
        this.creatorAccount = creatorAccount;
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

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
}
