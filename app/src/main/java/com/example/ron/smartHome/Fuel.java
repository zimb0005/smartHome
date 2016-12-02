package com.example.ron.smartHome;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Fuel extends AppCompatActivity {

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
}
