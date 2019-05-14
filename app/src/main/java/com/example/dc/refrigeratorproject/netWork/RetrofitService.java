package com.example.dc.refrigeratorproject.netWork;

import com.example.dc.refrigeratorproject.resposeBean.UserModel;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by DC on 2019/5/13.
 */

public interface RetrofitService {
    @GET("a")
    Observable<List<UserModel>> login(@Query("account") String account, @Query("password") String password);
}
