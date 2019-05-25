package com.example.dc.refrigeratorproject.iView;

import com.example.dc.refrigeratorproject.resposeBean.CommentRes;

import java.util.List;

/**
 * Created by DC on 2019/5/25.
 */

public interface ICommentView extends IView{
    void addCommentSuccess(String s);
    void getComment(List<CommentRes> s);
}
