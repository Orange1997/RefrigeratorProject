package com.example.dc.refrigeratorproject.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by DC on 2019/5/3.
 */

public class DialogUtil {

    public static void showNormalDialog(Context context, final String message, final OnPositiveClickListener mOnPositiveClickListener) {
        showNormalDialog (context, "提示", message, mOnPositiveClickListener);
    }

    public static void showNormalDialog(Context context, String title, final String message, final OnPositiveClickListener mOnPositiveClickListener) {
         /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder (context);
        normalDialog.setTitle (title);
        normalDialog.setMessage (message);
        normalDialog.setPositiveButton ("确定",
                new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mOnPositiveClickListener != null) {
                            mOnPositiveClickListener.onPositiveClick ();
                        }
                    }
                });
        normalDialog.setNegativeButton ("取消",
                new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mOnPositiveClickListener != null) {
                            mOnPositiveClickListener.onNegativeClick ();
                        }
                    }
                });
        // 显示
        normalDialog.show ();
    }

    public interface OnPositiveClickListener {
        void onPositiveClick();

        void onNegativeClick();
    }

}
