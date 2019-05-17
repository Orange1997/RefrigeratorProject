package com.example.dc.refrigeratorproject.netWork;

import android.content.Context;

import com.example.dc.refrigeratorproject.resposeBean.AddFridgeRes;
import com.example.dc.refrigeratorproject.resposeBean.AddShareFridgeRes;
import com.example.dc.refrigeratorproject.resposeBean.LoginRes;
import com.example.dc.refrigeratorproject.resposeBean.RefrigeratorListRes;
import com.example.dc.refrigeratorproject.resposeBean.RegisterRes;
import com.example.dc.refrigeratorproject.resposeBean.UpdateUserRes;

import java.util.List;

import rx.Observable;


/**
 * Created by DC on 2019/5/13.
 */

public class DataManager {
    private RetrofitService mRetrofitService;
    public DataManager(Context context){
        this.mRetrofitService = RetrofitHelper.getInstance(context).getServer();
    }
    public  Observable<LoginRes> login(String account, String password){
        return mRetrofitService.login(account,password,2);
    }

    public  Observable<RegisterRes> register(String account, String password){
        return mRetrofitService.register (account,password,2);
    }

    public  Observable<AddFridgeRes> addFridge(String fridgeName, String address, int userId, String code){
        return mRetrofitService.addFridge (fridgeName,address,userId,code,2);
    }

    public  Observable<UpdateUserRes> updateFridge(String createByFridgeIds, int userId,int fridgeId){
        return mRetrofitService.updateFridge (createByFridgeIds,userId,fridgeId,2);
    }

    public  Observable<List<RefrigeratorListRes>> getFridgeList(String ids){
        return mRetrofitService.getFridgeList (ids);
    }

    public  Observable<AddShareFridgeRes> addSharedFridge(String inviteCode, int userId){
        return mRetrofitService.addSharedFridge(inviteCode,userId,2);
    }

    public  Observable<AddShareFridgeRes> getCurrentFood(int fridgeId){
        return mRetrofitService.getCurrentFood(fridgeId);
    }
}