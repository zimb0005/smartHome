package com.example.ron.smartHome;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Drive extends AppCompatActivity {

    SQLiteDatabase database;
    DriveDataBaseHelper db;
    ArrayList<String>  arrayList;
    ListView listView;
    ArrayAdapter<String> listViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drive);

        db = new DriveDataBaseHelper(this);
        database = db.getWritableDatabase();

        Button button = (Button)findViewById(R.id.buttonGo);

        arrayList =  new ArrayList<>();;

        String query = "SELECT * FROM drive WHERE 1;";

        Cursor c = database.rawQuery(query,null);

        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex(DriveDataBaseHelper.KEY_MESSAGE))!= null) {
                arrayList.add(c.getString(c.getColumnIndex(DriveDataBaseHelper.KEY_MESSAGE)));
            }
            c.moveToNext();
        }
        listView = (ListView)findViewById(R.id.listView2);

        listViewAdapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_1,arrayList
        );

        listView.setAdapter(listViewAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText distance = (EditText)findViewById(R.id.editTextDrive);
                int i = Integer.parseInt(String.valueOf(distance.getText()));//distance driven
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String km = sharedPreferences.getString("KmValue","450");
                int j =Integer.parseInt(km)-i;
                km = Integer.toString(j);
                if(j<0){
                    Toast.makeText(getApplicationContext(),"You do not have enough gas to drive this far!",Toast.LENGTH_LONG).show();
                }
                else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("KmValue", km);
                    editor.commit();
                    ContentValues value = new ContentValues();
                    value.put(db.KEY_MESSAGE,Integer.toString(i));
                    database.insert(db.TABLE_NAME,null,value);
                    finish();
                }


            }
        });
    }
}
