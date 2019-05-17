package com.example.dc.refrigeratorproject.sqLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

}