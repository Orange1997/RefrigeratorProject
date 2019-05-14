package com.example.dc.refrigeratorproject.netWork;

import android.content.Context;

import com.example.dc.refrigeratorproject.resposeBean.UserModel;

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
    public  Observable<List<UserModel>> login(String account, String password){
        return mRetrofitService.login(account,password);
    }
}