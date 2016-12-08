package com.example.ron.smartHome;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ron on 2016-12-02.
 */
public class OptionDataBaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "Option.db";
    private static final int VERSION_NUM = 4;
    private static final String KEY_ID = "ID";
    static final String KEY_MESSAGE = "MESSAGE";
    static final String TABLE_NAME = "option";


    public OptionDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME+"("+
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                KEY_MESSAGE + " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME+";");
        onCreate(db);

    }
}

