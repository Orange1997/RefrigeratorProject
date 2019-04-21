package com.example.dc.refrigeratorproject.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.example.dc.refrigeratorproject.R;


public class CountDownButtonHelper {


    private Context context;
    // 倒计时timer
    private CountDownTimer countDownTimer;
    // 计时结束的回调接口
    private OnFinishListener listener;

    private TextView textView;
    //保存按钮之前的颜色
    private ColorStateList textColorOrigin;

    /**
     * @param textView        textView
     * @param defaultString 默认显示的字符串
     * @param context1
     * @param max           需要进行倒计时的最大值,单位是秒
     * @param interval      倒计时的间隔，单位是秒
     */
    public CountDownButtonHelper(final TextView textView,
                                 final String defaultString, Context context1, int max, int interval) {
        this.context = context1;
        this.textView = textView;
        // 由于CountDownTimer并不是准确计时，在onTick方法调用的时候，time会有1-10ms左右的误差，这会导致最后一秒不会调用onTick()
        // 因此，设置间隔的时候，默认减去了10ms，从而减去误差。
        // 经过以上的微调，最后一秒的显示时间会由于10ms延迟的积累，导致显示时间比1s长max*10ms的时间，其他时间的显示正常,总时间正常
        countDownTimer = new CountDownTimer(max * 1000, interval * 1000 - 10) {

            @Override
            public void onTick(long time) {
                // 第一次调用会有1-10ms的误差，因此需要+15ms，防止第一个数不显示，第二个数显示2s
                if ((time + 15) / 1000 >= 10) {
                    textView.setText(((time + 15) / 1000) + context.getString(R.string.info_second));
                } else {
                    textView.setText("0" + ((time + 15) / 1000) + context.getString(R.string.info_second));
                }

            }

            @Override
            public void onFinish() {
                textView.setEnabled(true);
                textView.setText(defaultString);//控制结束时的文字
                textView.setTextSize(12);//大小
//                textView.setBackgroundResource(R.drawable.shape_rounded_small_red);
                if (listener != null) {
                    listener.finish();
                }
            }
        };
    }

    /**
     * 开始倒计时
     */
    public void start() {
        //计时内容属性设置
        textView.setTextColor (Color.WHITE);
        textView.setEnabled(false);
        textView.setTextSize(12);
//        textView.setBackgroundResource(R.drawable.shape_rounded_small_gray);
        countDownTimer.start();
    }
    public  void finish()
    {
        countDownTimer.onFinish();
    }

    /**
     * 设置倒计时结束的监听器
     * listener
     */
    public void setOnFinishListener(OnFinishListener listener) {
        this.listener = listener;
    }

    /**
     * 计时结束的回调接口
     */
    public interface OnFinishListener {
        public void finish();
    }

}

