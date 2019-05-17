package com.example.dc.refrigeratorproject.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.dc.refrigeratorproject.resposeBean.User;
import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by DC on 2019/5/2.
 */

public class Config {
    public static final String KEY_SP = "key_sp";
    public static final String KEY_USER = "key_user";
    public static final String KEY_USER_ACCOUNT = "key_user_account";
    public static final String KEY_USER_CURRENT_FRIDGE_ID= "key_user_current_fridge_id";
    public static final String KEY_USER_SHARED_FRIDGE_IDS= "key_user_shared_fridge_ids";
    public static final String KEY_USER_ID = "key_user_account";
    public static final String KEY_USER_CREATE_FRIDGE_IDS = "key_user_create_ids";
    public static final String KEY_USER_PSD = "key_user_psd";
    public static final String KEY_REFRIGERATOR_MODEL = "key_refrigerator_model";
    public static final String KEY_EDIT_NAME = "key_edit_name";
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

    public static final String KEY_LOCATION_LATITUDE = "key_location_latitude"; //经度
    public static final String KEY_LOCATION_LONGITUDE = "key_location_longitude"; //纬度

    public static final int STATUS_LOGIN_NO_ACCOUNT = 10;
    public static final int STATUS_LOGIN_NOT_MATCH = 11;
    public static final int STATUS_LOGIN_SUCCESS = 12;


    public static boolean isLogin(Context context) {
        return !TextUtils.isEmpty (getUserPsd (context));
    }

    public static void logout(Context context) {
        setUserAccount (context, "");
        setUserPsd (context, "");
    }

    public static void setUser(Context context, User user) {
        SharedPreferences sp = context.getSharedPreferences (KEY_SP, MODE_PRIVATE);
        String userJson = new Gson ().toJson (user, User.class);
        SharedPreferences.Editor edit = sp.edit ();
        edit.putString (KEY_USER, userJson);
        edit.apply ();
    }

    public static User getUser(Context context) {
        SharedPreferences sp = context.getSharedPreferences (KEY_SP, MODE_PRIVATE);
        String user = sp.getString (KEY_USER, "");
        if (TextUtils.isEmpty (user)) {
            return null;
        } else {
            return new Gson ().fromJson (user, User.class);
        }
    }

    public static void setCreateFridgeIds(Context context, int id) {
        SharedPreferences sp = context.getSharedPreferences (KEY_SP, MODE_PRIVATE);
        User user = getUser (context);
        String ids = null;
        if (user != null) {
            ids = user.getCreateByFridgeIds ();
        }
        StringBuilder stringBuilder = new StringBuilder ();
        if (!TextUtils.isEmpty (ids)) {
            if (id != 0) {
                stringBuilder.append (ids).append (",").append (String.valueOf (id));
            }else {
                stringBuilder.append (ids);
            }
        } else {
            if (id != 0) {
                stringBuilder.append (String.valueOf (id));
            }else {
                stringBuilder.append (String.valueOf (""));
            }
        }
        SharedPreferences.Editor edit = sp.edit ();
        edit.putString (KEY_USER_CREATE_FRIDGE_IDS, stringBuilder.toString ());
        edit.apply ();
    }

    public static String getCreateFridgeIds(Context context) {
        SharedPreferences sp = context.getSharedPreferences (KEY_SP, MODE_PRIVATE);
        String ids = sp.getString (KEY_USER_CREATE_FRIDGE_IDS, "");
        if (TextUtils.isEmpty (ids)) {
            return "";
        } else {
            return ids;
        }
    }

    public static void setSharedFridgeIds(Context context, String ids) {
        SharedPreferences sp = context.getSharedPreferences (KEY_SP, MODE_PRIVATE);
        User user = getUser (context);
        if (user!=null){
            user.setSharedFridgeIds (ids);
            setUser (context,user);
        }
        SharedPreferences.Editor edit = sp.edit ();
        if (ids!=null){
            edit.putString (KEY_USER_SHARED_FRIDGE_IDS, ids);
        }else {
            edit.putString (KEY_USER_SHARED_FRIDGE_IDS, "");
        }
        edit.apply ();
    }

    public static String getSharedFridgeIds(Context context) {
        SharedPreferences sp = context.getSharedPreferences (KEY_SP, MODE_PRIVATE);
        String ids = sp.getString (KEY_USER_SHARED_FRIDGE_IDS, "");
        if (TextUtils.isEmpty (ids)) {
            return "";
        } else {
            return ids;
        }
    }

    public static void setUserAccount(Context context, String account) {
        SharedPreferences sp = context.getSharedPreferences (KEY_SP, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit ();
        edit.putString (KEY_USER_ACCOUNT, account);
        edit.apply ();
    }

    public static String getUserAccount(Context context) {
        SharedPreferences sp = context.getSharedPreferences (KEY_SP, MODE_PRIVATE);
        String ac = sp.getString (KEY_USER_ACCOUNT, "");
        return ac;
    }

    public static void setUserPsd(Context context, String psd) {
        SharedPreferences sp = context.getSharedPreferences (KEY_SP, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit ();
        edit.putString (KEY_USER_PSD, psd);
        edit.apply ();
    }

    public static int getUserId(Context context) {
        SharedPreferences sp = context.getSharedPreferences (KEY_SP, MODE_PRIVATE);
        return sp.getInt (KEY_USER_ID, 0);
    }

    public static void setUserID(Context context, int uid) {
        SharedPreferences sp = context.getSharedPreferences (KEY_SP, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit ();
        edit.putInt (KEY_USER_ID, uid);
        edit.apply ();
    }

    public static String getUserPsd(Context context) {
        SharedPreferences sp = context.getSharedPreferences (KEY_SP, MODE_PRIVATE);
        return sp.getString (KEY_USER_PSD, "");
    }


    public static void setCurrentFridgeId(Context context, int id) {
        SharedPreferences sp = context.getSharedPreferences (KEY_SP, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit ();
        edit.putInt (KEY_USER_CURRENT_FRIDGE_ID, id);
        edit.apply ();
    }

    public static int getCurrentFridgeId(Context context) {
        SharedPreferences sp = context.getSharedPreferences (KEY_SP, MODE_PRIVATE);
        return sp.getInt (KEY_USER_CURRENT_FRIDGE_ID, 0);
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

    public static void putDouble(Context context, String key, double value) {
        SharedPreferences sp = context.getSharedPreferences (KEY_SP, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit ();
        edit.putString (key, String.valueOf (value));
        edit.apply ();
    }

    public static double getDouble(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences (KEY_SP, MODE_PRIVATE);
        if (TextUtils.isEmpty (sp.getString (key,""))){
            return 0;
        }else {
            return Double.parseDouble (sp.getString (key, ""));
        }
    }

    public static String getAllFridges(Context context){
        String ids = "";
        if (!TextUtils.isEmpty (getCreateFridgeIds (context))&&!TextUtils.isEmpty (getSharedFridgeIds (context))){
            ids =getCreateFridgeIds (context)+","+getSharedFridgeIds (context);
        }else if (TextUtils.isEmpty (getCreateFridgeIds (context))){
            ids = getSharedFridgeIds (context);
        }else if (TextUtils.isEmpty (getSharedFridgeIds (context))){
            ids = getCreateFridgeIds (context);
        }
        return ids;
    }



}
