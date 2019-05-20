package com.example.dc.refrigeratorproject.netWork;

import com.example.dc.refrigeratorproject.resposeBean.AddFridgeRes;
import com.example.dc.refrigeratorproject.resposeBean.AddShareFridgeRes;
import com.example.dc.refrigeratorproject.resposeBean.GetFoodListRes;
import com.example.dc.refrigeratorproject.resposeBean.LoginRes;
import com.example.dc.refrigeratorproject.resposeBean.RefrigeratorListRes;
import com.example.dc.refrigeratorproject.resposeBean.RegisterRes;
import com.example.dc.refrigeratorproject.resposeBean.UpdateUserRes;
import com.example.dc.refrigeratorproject.resposeBean.User;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by DC on 2019/5/13.
 */

public interface RetrofitService {
    @GET("login")
    Observable<LoginRes> login(@Query("account") String account, @Query("password") String password, @Query("tag") Integer tag);

    @GET("register")
    Observable<RegisterRes> register(@Query("account") String account, @Query("password") String password, @Query("tag") Integer tag);

    @GET("addFridge")
    Observable<AddFridgeRes> addFridge(@Query("fridgeName") String fridgeName, @Query("address") String address,
                                       @Query("userId") Integer userId, @Query("invitationCode") String invitationCode,
                                       @Query("tag") Integer tag);

    @GET("updateUser")
    Observable<UpdateUserRes> updateFridge(@Query("createByFridgeIds") String createByFridgeIds, @Query("userId") Integer userId,
                                           @Query("currentFridgeId") Integer fridgeId, @Query("tag") Integer tag);

    @GET("getFridge")
    Observable<List<RefrigeratorListRes>> getFridgeList(@Query("fridgeIds") String fridgeIds);

    @GET("addSharedFridge")
    Observable<AddShareFridgeRes> addSharedFridge(@Query("invitationCode") String invitationCode,
                                                  @Query("userId") Integer userId, @Query("tag") Integer tag);

    @GET("getUserShareFridge")
    Observable<List<User>> getUserShareFridge(@Query("fridgeId") Integer fridgeId);

    @GET("getCurrentFood")
    Observable<List<GetFoodListRes>> getCurrentFood(@Query("fridgeId") Integer fridgeId, @Query("tag") Integer tag);

    @GET("addFoodPost")
    Observable<String> addFoodPost(@Query("imgData") String imgData, @Query("foodName") String foodName,
                                   @Query("foodCount") float foodCount, @Query("foodUnit") String foodUnit,
                                   @Query("outlineTime") String outlineTime, @Query("remindTime") String remindTime,
                                   @Query("remark") String remark, @Query("userId") int userId, @Query("fridgeId") int fridgeId);
}
