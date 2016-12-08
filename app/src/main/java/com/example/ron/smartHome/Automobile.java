package com.example.ron.smartHome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class Automobile extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "Automobile";

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

    //These are the methods that add the overflow menu to the action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch(item.getItemId()){
            case R.id.livingRoom:
                Intent intent = new Intent (Automobile.this,LivingRoom.class);
                startActivity(intent);
                break;
            case R.id.auto:
                Intent intent2 = new Intent (Automobile.this,Automobile.class);
                startActivity(intent2);
                break;
            case R.id.houseSettings:
                Intent intent3 = new Intent (Automobile.this,HouseSettings.class);
                startActivity(intent3);
                break;
            case R.id.kitchen:
                Intent intent4 = new Intent (Automobile.this,Kitchen.class);
                startActivity(intent4);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
