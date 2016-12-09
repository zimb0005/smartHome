package com.example.ron.smartHome;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class TempDatabaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "Temps.db";
    private static final int VERSION_NUM = 1;
    public static final String KEY_ID = "_id";
    public static final String TIME ="time";
    public static final String TEMP ="data";
    public static final String TABLE_NAME = "name";
    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + "( " + KEY_ID
            + " integer primary key autoincrement, " + TIME
            + " text not null);"
            + TEMP + "text not null";

    public TempDatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(DATABASE_CREATE);

        Log.i("TempDatabaseHelper", "Calling onCreate");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
        Log.i("TempDatabaseHelper", "Calling onUpgrade, oldVersion=" + oldVersion + " newVersion=" + newVersion);
    }


}
