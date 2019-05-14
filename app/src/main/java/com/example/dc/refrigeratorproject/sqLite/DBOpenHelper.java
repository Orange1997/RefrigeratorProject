package com.example.dc.refrigeratorproject.sqLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.dc.refrigeratorproject.config.Config.STATUS_LOGIN_NOT_MATCH;
import static com.example.dc.refrigeratorproject.config.Config.STATUS_LOGIN_NO_ACCOUNT;
import static com.example.dc.refrigeratorproject.config.Config.STATUS_LOGIN_SUCCESS;

/**
 * Created by DC on 2019/5/13.
 */

public class DBOpenHelper extends SQLiteOpenHelper {
    private Context context;
    private static final int DATABASE_VERSION_CODE = 1;
    //头像
    private static final String USER_DATA_TABLE = "user";
    private static final String USER_DATA_CREATE =
            "CREATE TABLE\"user\"(" +
                    "\"uid\"LONG PRIMARY KEY," + //主键，用户ID
                    "\"account\"VARCHAR NOT NULL," + //账号
                    "\"password\"VARCHAR NOT NULL)";  //密码

    public DBOpenHelper(Context context) {
        super (context, "hhhh", null, DATABASE_VERSION_CODE);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL (USER_DATA_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < DATABASE_VERSION_CODE) {
            switch (oldVersion) {
                case 1:
                    db.execSQL (USER_DATA_CREATE);
                    break;
            }
        }
    }

    //添加用户
    public boolean addUser(String account, String password) {
        SQLiteDatabase db = this.getWritableDatabase ();
        Cursor cursor = db.query (USER_DATA_TABLE, new String[]{"account", "password"}, "account=?", new String[]{account}, null, null, null);
        //从数据库中匹配账号密码
        while (cursor.moveToNext ()) {
            if (account.equals (cursor.getString (cursor.getColumnIndex ("account")))) {
                cursor.close ();
                return false;
            }
        }
        ContentValues cv = new ContentValues ();
        cv.put ("account", account);
        cv.put ("password", password);
        db.insert (USER_DATA_TABLE, null, cv);
        return true;
    }

    public int loginStatus(String account, String password) {
        SQLiteDatabase db = this.getReadableDatabase ();
        Cursor cursor = db.query (USER_DATA_TABLE, new String[]{"account", "password"}, "account=?", new String[]{account}, null, null, null);
        if (cursor.getCount () == 0) {
            cursor.close ();
            return STATUS_LOGIN_NO_ACCOUNT;
        }
        //从数据库中匹配账号密码
        while (cursor.moveToNext ()) {
            if (account.equals (cursor.getString (cursor.getColumnIndex ("account")))
                    && password.equals (cursor.getString (cursor.getColumnIndex ("password")))) {
                return STATUS_LOGIN_SUCCESS;
            } else if (!account.equals (cursor.getString (cursor.getColumnIndex ("account")))) {
                return STATUS_LOGIN_NO_ACCOUNT;
            } else if (account.equals (cursor.getString (cursor.getColumnIndex ("account")))
                    && !password.equals (cursor.getString (cursor.getColumnIndex ("password")))) {
                return STATUS_LOGIN_NOT_MATCH;
            }
        }
        return STATUS_LOGIN_NOT_MATCH;
    }
}