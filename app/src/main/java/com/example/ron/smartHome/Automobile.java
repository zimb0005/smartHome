package com.example.ron.smartHome;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;


/**
 * This class is the entry point for the automobile part of the application
 * @author ron zimbalatti
 * @date dec 1 2016
 */
public class Automobile extends AppCompatActivity  {

    /**
     * string to display activity name
     */
    protected static final String ACTIVITY_NAME = "Automobile";

    /**
     * This method sets the layout
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(ACTIVITY_NAME,"In onCreate()");
        setContentView(R.layout.activity_automobile);
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i(ACTIVITY_NAME,"In onResume()");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i(ACTIVITY_NAME,"In onStart()");

    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i(ACTIVITY_NAME,"In onPause()");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i(ACTIVITY_NAME,"In onStop()");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(ACTIVITY_NAME,"In onDestroy()");
    }

    /**
     * This method inflates the main menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    /**
     * this method initiates actions when items are selected from menu
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch(item.getItemId()){
            case R.id.livingRoom:
                Intent intent = new Intent (Automobile.this,LivingRoom.class);
                startActivity(intent);//starts Living Room activity when Icon selected
                break;
            case R.id.auto:
                Intent intent2 = new Intent (Automobile.this,Automobile.class);
                startActivity(intent2);//starts Automobile activity when Icon selected
                break;
            case R.id.houseSettings:
                Intent intent3 = new Intent (Automobile.this,HouseSettings.class);
                startActivity(intent3);//starts house settings activity when Icon selected
                break;
            case R.id.kitchen:
                Intent intent4 = new Intent (Automobile.this,Kitchen.class);
                startActivity(intent4);//starts kitchen activity when Icon selected
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
