package com.example.ron.smartHome;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
/**
 * this class is an activity representing travel distance of the car
 * @author ron zimbalaltti
 * @date dec 1 2016
 */
public class Drive extends AppCompatActivity {
    /**
     * database instance
     */
    SQLiteDatabase database;
    /**
     * databaseHelper instance
     */
    DriveDataBaseHelper db;
    /**
     * arraylist used to store travel log
     */
    ArrayList<String>  arrayList;
    /**
     * listView used to diplay arrayList
     */
    ListView listView;
    /**
     * Adapter for listView
     */
    ArrayAdapter<String> listViewAdapter;

    /**
     * Overridden onCreate method get information stored in a database and have cursor iterate over it and store to arraylist
     * @param savedInstanceState
     */
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

                int distanceDriven = 0;//distance driven
                try {
                    distanceDriven = Integer.parseInt(String.valueOf(distance.getText()));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),getString(R.string.NumberWarning),Toast.LENGTH_LONG).show();
                    finish();
                }
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String oldDistanceCanDrive = sharedPreferences.getString("KmValue","450");
                int newDistanceCanDrive =Integer.parseInt(oldDistanceCanDrive)-distanceDriven;
                if(newDistanceCanDrive<0){
                    Toast.makeText(getApplicationContext(),getString(R.string.FuelWarning),Toast.LENGTH_LONG).show();
                }
                else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("KmValue", Integer.toString(newDistanceCanDrive));
                    int Odometer = sharedPreferences.getInt("Odometer",0);
                    editor.putInt("Odometer", distanceDriven+Odometer);
                    editor.commit();
                    ContentValues value = new ContentValues();
                    value.put(db.KEY_MESSAGE,Integer.toString(distanceDriven));
                    database.insert(db.TABLE_NAME,null,value);
                    finish();
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.car_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        builder2.setTitle(getString(R.string.Drive));
        builder2.setMessage(getString(R.string.DriveInfo));
        builder2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        AlertDialog dialog2 = builder2.create();
        dialog2.show();
        return super.onOptionsItemSelected(item);
    }
}
