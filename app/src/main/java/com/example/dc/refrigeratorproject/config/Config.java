package com.example.dc.refrigeratorproject.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by DC on 2019/5/2.
 */

public class Config {
    public static final String KEY_SP = "key_sp";
    public static final String KEY_USER_ACCOUNT = "key_user_account";
    public static final String KEY_USER_PSD = "key_user_psd";
    public static final String KEY_REFRIGERATOR_MODEL = "key_refrigerator_model";
    public static final String KEY_REFRIGERATOR_NAME = "key_refrigerator_name";
    public static final String KEY_REFRIGERATOR_ADDRESS = "key_refrigerator_address";
    public static final String KEY_REFRIGERATOR_ADDRESS_PROVINCE = "key_refrigerator_address_province";
    public static final String KEY_REFRIGERATOR_ADDRESS_CITY = "key_refrigerator_address_city";
    public static final String KEY_REFRIGERATOR_ADDRESS_AREA = "key_refrigerator_address_area";
    public static final String KEY_REFRIGERATOR_IS_TO_CREATE = "key_refrigerator_is_to_create";
    public static final String KEY_EDIT_SIGN_CONTENT = "key_edit_sign_content";
    public static final String KEY_FOUND_TYPE = "key_found_type";
    public static final String KEY_FOUND_URL = "key_found_url";
    public static final String KYE_COMMENT_FROM_WHERE = "key_comment_from_where";
    public static final String VALUE_COMMENT_TO_SEND = "value_comment_to_send";
    public static final String VALUE_COMMENT_TO_REPLY = "value_comment_to_reply";

    public static final int STATUS_LOGIN_NO_ACCOUNT = 10;
    public static final int STATUS_LOGIN_NOT_MATCH = 11;
    public static final int STATUS_LOGIN_SUCCESS = 12;

    public static boolean isLogin(Context context) {
        return getUserAccount (context) != 0 && !TextUtils.isEmpty (getUserPsd (context));
    }

    public static void logout(Context context) {
        setUserAccount (context, 0);
        setUserPsd (context, "");
    }


    public static void setUserAccount(Context context, long account) {
        SharedPreferences sp = context.getSharedPreferences (KEY_SP, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit ();
        edit.putLong (KEY_USER_ACCOUNT, account);
        edit.apply ();
    }

    public static long getUserAccount(Context context) {
        SharedPreferences sp = context.getSharedPreferences (KEY_SP, MODE_PRIVATE);
        return sp.getLong (KEY_USER_ACCOUNT, 0);
    }

    public static void setUserPsd(Context context, String psd) {
        SharedPreferences sp = context.getSharedPreferences (KEY_SP, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit ();
        edit.putString (KEY_USER_PSD, psd);
        edit.apply ();
    }

    public static String getUserPsd(Context context) {
        SharedPreferences sp = context.getSharedPreferences (KEY_SP, MODE_PRIVATE);
        return sp.getString (KEY_USER_PSD, "");
    }

    public static void putString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences (KEY_SP, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit ();
        edit.putString (key, value);
        edit.apply ();
    }

    public static String getString(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences (KEY_SP, MODE_PRIVATE);
        return sp.getString (key, "");
    }


}
