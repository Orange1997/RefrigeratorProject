package com.example.dc.refrigeratorproject.presenter;

import android.content.Context;

import com.example.dc.refrigeratorproject.iView.ILoginView;
import com.example.dc.refrigeratorproject.iView.IView;
import com.example.dc.refrigeratorproject.resposeBean.LoginRes;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by DC on 2019/5/13.
 */

public class LoginPresenter extends BasePresenter{
    private ILoginView iloginView;

    public LoginPresenter(Context mContext,IView iView){
       super(mContext);
       iloginView = (ILoginView)iView;
    }

    public void login(String account,String password){
        mCompositeSubscription.add(manager.login (account,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginRes> () {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        iloginView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(LoginRes loginRes) {
                        if (loginRes != null){
                            if (loginRes.getStatus ()==0){
                                iloginView.onError("账号密码不匹配！");
                            }else if (loginRes.getStatus ()==1&&loginRes.getData ()!=null){
                                iloginView.onSuccess(loginRes.getData ().get (0));
                            }
                        }
                    }
                })
        );
    }

    public void loginByQq(final String openId){
        mCompositeSubscription.add(manager.loginByQq (openId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginRes> () {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        iloginView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(LoginRes s) {
                        if (s!=null){
                            if (s.getStatus ()==1){
                                iloginView.onLoginByQqSuccess (s.getData ().get (0));
                            }else if (s.getStatus ()==0){
                                iloginView.onLoginByQqFailure ("你还未绑定手机号，现在去绑定",openId);
                            }
                        }
                    }
                })
        );
    }

    @Override
    public void onStop() {
        super.onStop ();
    }
}
