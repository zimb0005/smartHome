package com.example.ron.smartHome;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ron on 2016-12-02.
 */
public class DriveDataBaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "Drive.db";
    private static final int VERSION_NUM = 4;
    private static final String KEY_ID = "ID";
    static final String KEY_MESSAGE = "MESSAGE";
    static final String TABLE_NAME = "drive";


    public DriveDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i("DriveDataBaseHelper","Calling onCreate");


        String query = "CREATE TABLE " + TABLE_NAME+"("+
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                KEY_MESSAGE + " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i("DriveDataBaseHelper","Calling onUpgrade, oldVersion= "+oldVersion+ " newVersion= "+newVersion);

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME+";");
        onCreate(db);

    }
}

