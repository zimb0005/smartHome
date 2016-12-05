package com.example.ron.smartHome;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class Temp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        final SharedPreferences sharedPreferences2 = getApplicationContext().getSharedPreferences("progress2", Context.MODE_PRIVATE);
        final SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("progress", Context.MODE_PRIVATE);

        SeekBar seekbar = (SeekBar)findViewById(R.id.seekBar);
        final TextView textView = (TextView)findViewById(R.id.textViewBackValue);
        String progress = sharedPreferences.getString("progress", "0");
        int i = Integer.valueOf(progress);
        seekbar.setProgress(i);
        seekbar.setMax(40);
        textView.setText(progress);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("CarList", "progress");
                textView.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.i("CarList", "start");
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("progress", textView.getText().toString());
                editor.commit();
            }
        });

        SeekBar seekBar2 = (SeekBar)findViewById(R.id.seekBar2);
        final TextView textView2 = (TextView)findViewById(R.id.textViewFrontValue);
        String progress2 = sharedPreferences2.getString("progress2", "0");
        int j = Integer.valueOf(progress2);
        seekBar2.setProgress(j);
        seekBar2.setMax(40);
        textView2.setText(progress2);
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar2, int progress, boolean fromUser) {
                Log.i("CarList", "progress2");
                textView2.setText(String.valueOf(progress));

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar2) {
                Log.i("CarList", "start");

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar2) {
                Log.i("CarList", "stop");
                SharedPreferences.Editor editor = sharedPreferences2.edit();
                editor.putString("progress2", textView2.getText().toString());
                editor.commit();
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
        builder2.setTitle("Car Temperature");
        builder2.setMessage("This function is used to control the temperature in front and back of car. Adjust slider to desired temperature");
        builder2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        AlertDialog dialog2 = builder2.create();
        dialog2.show();
        return super.onOptionsItemSelected(item);
    }
}
