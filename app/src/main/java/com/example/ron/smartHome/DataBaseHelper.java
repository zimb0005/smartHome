package com.example.ron.smartHome;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ron on 2016-12-02.
 */
public class DataBaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "kitchen.db";
    private static final int VERSION_NUM = 4;
    private static final String KEY_ID = "ID";
    static final String KEY_MESSAGE = "MESSAGE";
    static final String TABLE_NAME = "kitchen";
    public static final String COLUMN1 = "type";
    public static final String COLUMN2 = "name";
    public static final String COLUMN3 = "description";


    public DataBaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i("DataBaseHelper","Calling onCreate");


        String query = "CREATE TABLE " + TABLE_NAME+"("+
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN2 + " TEXT, "+
                COLUMN3 + " TEXT)";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i("DataBaseHelper","Calling onUpgrade, oldVersion= "+oldVersion+ " newVersion= "+newVersion);

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME+";");
        onCreate(db);

    }


    public boolean add(String name){
SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN1,"Fridge");
        cv.put(COLUMN2,name);
        cv.put(COLUMN3,name);
        long result =db.insert(TABLE_NAME,null,cv);
        if(result == -1){
            return false;
        }else
        {
            return true;
        }
    }


    public Cursor getList(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data= db.rawQuery("Select * from "+TABLE_NAME,null);
        return data;
    }
}

