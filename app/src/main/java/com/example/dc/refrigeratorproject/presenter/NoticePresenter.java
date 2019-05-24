package com.example.dc.refrigeratorproject.presenter;

import android.content.Context;

import com.example.dc.refrigeratorproject.iView.INoticeView;
import com.example.dc.refrigeratorproject.resposeBean.NoticeRes;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by DC on 2019/5/24.
 */

public class NoticePresenter extends BasePresenter {
    private INoticeView iNoticeView;

    public NoticePresenter(Context mContext, INoticeView iView){
        super(mContext);
        iNoticeView = iView;
    }

    public void getNotice(){
        mCompositeSubscription.add(manager.getNotice ()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<NoticeRes>> () {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();

                    }

                    @Override
                    public void onNext(List<NoticeRes> s) {
                        if (s!=null&&s.size ()>0){
                            iNoticeView.getNoticeSuccess (s);
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
