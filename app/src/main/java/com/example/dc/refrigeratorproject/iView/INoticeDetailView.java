package com.example.dc.refrigeratorproject.iView;

import com.example.dc.refrigeratorproject.resposeBean.CommentRes;
import com.example.dc.refrigeratorproject.resposeBean.NoticeRes;

import java.util.List;

/**
 * Created by DC on 2019/5/25.
 */

public interface INoticeDetailView extends IView {
    void onCollectSuccess(String s);
    void getCollects(List<NoticeRes> list);
    void getCommentSuccess(List<CommentRes> s);
}
