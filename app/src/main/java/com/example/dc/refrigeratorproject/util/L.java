package com.example.dc.refrigeratorproject.util;

import android.util.Log;

/**
 * Created by DC on 2019/3/5.
 */

public class L {

    //TAG
    public static String TAG = "tonjies";

    //5个等级 DIWE

    public static void d(String text) {
        Log.d(TAG, text + "");
    }

    public static void i(String text) {
        Log.i(TAG, text + "");
    }

    public static void w(String text) {
        Log.w(TAG, text + "");
    }

    public static void e(String text) {
        Log.e(TAG, text + "");
    }

}