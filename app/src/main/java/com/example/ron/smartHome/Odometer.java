package com.example.ron.smartHome;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
/**
 * This class represents the odometer of the car
 * @author ron zimbalatti
 * @date dec 1 2016
 */
public class Odometer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odometer);
        TextView odometer = (TextView)findViewById(R.id.textViewOdometer);
        final SharedPreferences odometerPreference = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int odometerInt = odometerPreference.getInt("Odometer",0);
        odometer.setText(Integer.toString(odometerInt));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.car_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        builder2.setTitle(getString(R.string.Odometer));
        builder2.setMessage(getString(R.string.OdometerInfo));
        builder2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        AlertDialog dialog2 = builder2.create();
        dialog2.show();
        return super.onOptionsItemSelected(item);
    }
}
