package com.example.dc.refrigeratorproject.netWork;

import com.example.dc.refrigeratorproject.resposeBean.AddFridgeRes;
import com.example.dc.refrigeratorproject.resposeBean.AddShareFridgeRes;
import com.example.dc.refrigeratorproject.resposeBean.CommentRes;
import com.example.dc.refrigeratorproject.resposeBean.FoodDetailRes;
import com.example.dc.refrigeratorproject.resposeBean.GetFoodListRes;
import com.example.dc.refrigeratorproject.resposeBean.LoginRes;
import com.example.dc.refrigeratorproject.resposeBean.NoticeRes;
import com.example.dc.refrigeratorproject.resposeBean.RefrigeratorListRes;
import com.example.dc.refrigeratorproject.resposeBean.RegisterRes;
import com.example.dc.refrigeratorproject.resposeBean.UpdateUserRes;
import com.example.dc.refrigeratorproject.resposeBean.User;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by DC on 2019/5/13.
 */

public interface RetrofitService {
    @GET("login")
    Observable<LoginRes> login(@Query("account") String account, @Query("password") String password, @Query("tag") Integer tag);

    @GET("loginByQq")
    Observable<LoginRes> loginByQq(@Query("openId") String openId);

    @GET("bindUserByQQ")
    Observable<LoginRes> bindUserByQQ(@Query("openId") String openId, @Query("account") String account);

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

    @POST("addFoodPost")
    Observable<String> addFoodPost(@Query("foodName") String foodName, @Query("foodCount") float foodCount,
                                   @Query("foodUnit") String foodUnit, @Query("outlineTime") long outlineTime,
                                   @Query("remindTime") long remindTime, @Query("remark") String remark,
                                   @Query("userId") int userId, @Query("fridgeId") int fridgeId, @Query("foodType") int type);

    @POST("updateFoodPost")
    Observable<String> updateFoodPost(@Query("foodName") String foodName, @Query("foodCount") float foodCount,
                                      @Query("foodUnit") String foodUnit, @Query("outlineTime") long outlineTime,
                                      @Query("remindTime") long remindTime, @Query("remark") String remark,
                                      @Query("foodId") int foodId, @Query("foodType") int type);

    @GET("getFoodDetail")
    Observable<FoodDetailRes> getFoodDetail(@Query("foodId") Integer foodId);

    @POST("deleteFood")
    Observable<String> deleteFood(@Query("foodId") int foodId);

    @GET("getNotice")
    Observable<List<NoticeRes>> getNotice();

    @POST("addNoticeCollection")
    Observable<String> addNoticeCollection(@Query("noticeId") int noticeId, @Query("noticeTitle") String noticeTitle,
                                           @Query("noticeImgUr") String noticeImgUrl, @Query("noticeUrl") String noticeUrl,
                                           @Query("createTime") String createTime, @Query("author") String author,
                                           @Query("noticeType") int type, @Query("userId") int userId);

    @GET("getCollectedNotice")
    Observable<List<NoticeRes>> getCollectedNotice(@Query("userId") int userId);

    @POST("addComment")
    Observable<String> addComment(@Query("noticeId") int noticeId, @Query("content") String content,
                                  @Query("userName") String userName, @Query("createTime") long createTime,
                                  @Query("userId") int userId);

    @GET("getComment")
    Observable<List<CommentRes>> getComment(@Query("noticeId") int noticeId);
}
