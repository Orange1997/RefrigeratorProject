package com.example.dc.refrigeratorproject.config;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by DC on 2019/5/2.
 */

public class Config {
    public static final String KEY_SP = "key_sp";
    public static final String KEY_USER_ACCOUNT = "key_user_account";
    public static final String KEY_REFRIGERATOR_MODEL = "key_refrigerator_model";
    public static final String KEY_REFRIGERATOR_NAME = "key_refrigerator_name";

    public static void setUserAccount(Context context, long account) {
        SharedPreferences sp = context.getSharedPreferences (KEY_SP, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit ();
        edit.putLong (KEY_USER_ACCOUNT, account);
        edit.apply ();
    }

    public static long getUserAccount(Context context) {
        SharedPreferences sp = context.getSharedPreferences (KEY_SP, MODE_PRIVATE);
        return sp.getLong (KEY_USER_ACCOUNT,0);
    }


}
