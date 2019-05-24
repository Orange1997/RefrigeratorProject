package com.example.dc.refrigeratorproject.netWork;

import android.content.Context;

import com.example.dc.refrigeratorproject.config.Config;
import com.example.dc.refrigeratorproject.resposeBean.AddFridgeRes;
import com.example.dc.refrigeratorproject.resposeBean.AddShareFridgeRes;
import com.example.dc.refrigeratorproject.resposeBean.FoodDetailRes;
import com.example.dc.refrigeratorproject.resposeBean.GetFoodListRes;
import com.example.dc.refrigeratorproject.resposeBean.LoginRes;
import com.example.dc.refrigeratorproject.resposeBean.NoticeRes;
import com.example.dc.refrigeratorproject.resposeBean.RefrigeratorListRes;
import com.example.dc.refrigeratorproject.resposeBean.RegisterRes;
import com.example.dc.refrigeratorproject.resposeBean.UpdateUserRes;
import com.example.dc.refrigeratorproject.resposeBean.User;

import java.util.List;

import rx.Observable;


/**
 * Created by DC on 2019/5/13.
 */

public class DataManager {
    private Context context;
    private RetrofitService mRetrofitService;

    public DataManager(Context context) {
        this.context = context;
        this.mRetrofitService = RetrofitHelper.getInstance (context).getServer ();
    }

    public Observable<LoginRes> login(String account, String password) {
        return mRetrofitService.login (account, password, 2);
    }

    public Observable<LoginRes> loginByQq(String openId) {
        return mRetrofitService.loginByQq (openId);
    }

    public Observable<LoginRes> bindUserByQQ(String openId, String accout) {
        return mRetrofitService.bindUserByQQ (openId, accout);
    }

    public Observable<RegisterRes> register(String account, String password) {
        return mRetrofitService.register (account, password, 2);
    }

    public Observable<AddFridgeRes> addFridge(String fridgeName, String address, int userId, String code) {
        return mRetrofitService.addFridge (fridgeName, address, userId, code, 2);
    }

    public Observable<UpdateUserRes> updateFridge(String createByFridgeIds, int userId, int fridgeId) {
        return mRetrofitService.updateFridge (createByFridgeIds, userId, fridgeId, 2);
    }

    public Observable<List<RefrigeratorListRes>> getFridgeList(String ids) {
        return mRetrofitService.getFridgeList (ids);
    }

    public Observable<AddShareFridgeRes> addSharedFridge(String inviteCode, int userId) {
        return mRetrofitService.addSharedFridge (inviteCode, userId, 2);
    }

    public Observable<List<User>> getUserShareFridge(int fridgeId) {
        return mRetrofitService.getUserShareFridge (fridgeId);
    }

    public Observable<List<GetFoodListRes>> getCurrentFood(int fridgeId) {
        return mRetrofitService.getCurrentFood (fridgeId, 2);
    }

    public Observable<String> addFoodPost(String name, float amount, String unit, long outTime, long remindTime, String remind, int type) {
        int userId = Config.getUserId (context);
        int fridgeId = Config.getCurrentFridgeId (context);
        return mRetrofitService.addFoodPost (name, amount, unit, outTime, remindTime, remind, userId, fridgeId, type);
    }

    public Observable<String> updateFoodPost(String name, float amount, String unit, long outTime, long remindTime, String remind, int foodId, int type) {
        return mRetrofitService.updateFoodPost (name, amount, unit, outTime, remindTime, remind, foodId, type);
    }

    public Observable<FoodDetailRes> getFoodDetail(int foodId) {
        return mRetrofitService.getFoodDetail (foodId);
    }

    public Observable<String> deleteFood(int foodId) {
        return mRetrofitService.deleteFood (foodId);
    }

    public Observable<List<NoticeRes>> getNotice() {
        return mRetrofitService.getNotice ();
    }

    public Observable<List<NoticeRes>> getCollectedNotice() {
        int userId = Config.getUserId (context);
        return mRetrofitService.getCollectedNotice (userId);
    }

    public Observable<String> addNoticeCollection(int noticeId, String noticeTitle, String noticeImgUr, String noticeUrl, String createTime, String author, int type, int userId) {
        return mRetrofitService.addNoticeCollection (noticeId, noticeTitle, noticeImgUr, noticeUrl, createTime, author, type, userId);
    }
}