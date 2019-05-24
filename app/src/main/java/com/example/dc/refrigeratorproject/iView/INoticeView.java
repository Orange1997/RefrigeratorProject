package com.example.dc.refrigeratorproject.iView;

import com.example.dc.refrigeratorproject.resposeBean.NoticeRes;

import java.util.List;

/**
 * Created by DC on 2019/5/24.
 */

public interface INoticeView {
    void getNoticeSuccess(List<NoticeRes> res);
}
