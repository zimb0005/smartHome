package com.example.ron.smartHome;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * This class represents the fuel guage in the car
 * @author ron zimbalatti
 * @date dec 1 2016
 */
public class Fuel extends AppCompatActivity {
    /**
     * this method is used to diplay the amount of fuel and how far you can travel it stores the info in a shared preference
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel);

        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("KmValue",sharedPreferences.getString("KmValue","225"));
        editor.commit();
        final ProgressBar progress = (ProgressBar)findViewById(R.id.progressBar);
        final String km = sharedPreferences.getString("KmValue", "225");
        final Button fillButton = (Button)findViewById(R.id.fillButton);
        final TextView Km = (TextView)findViewById(R.id.textViewKmValue);
        Km.setText(km);
        int kmInt = Integer.parseInt(km);
        progress.setProgress(kmInt/9);
        fillButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("KmValue", "450");
                editor.commit();
                Km.setText("450");
                progress.setProgress(50);
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
        builder2.setTitle(getString(R.string.FuelGuage));
        builder2.setMessage(getString(R.string.FuelGuageInfo));
        builder2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        AlertDialog dialog2 = builder2.create();
        dialog2.show();
        return super.onOptionsItemSelected(item);
    }
}
