package com.example.ron.smartHome;

import android.content.SharedPreferences;
import android.icu.text.DecimalFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Radio extends AppCompatActivity {

    int volumeInt;
    String volumeString;
    TextView volumeText;
    Double tunerDouble;
    String tunerString;
    TextView tunerText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);
        final SharedPreferences volumePreference = getApplicationContext().getSharedPreferences("Volume",MODE_PRIVATE);
        final SharedPreferences tunerPreference = getApplicationContext().getSharedPreferences("Tuner",MODE_PRIVATE);
        final SharedPreferences preset1Preference = getApplicationContext().getSharedPreferences("Preset1",MODE_PRIVATE);
        final SharedPreferences preset2Preference = getApplicationContext().getSharedPreferences("Preset2",MODE_PRIVATE);
        final SharedPreferences preset3Preference = getApplicationContext().getSharedPreferences("Preset3",MODE_PRIVATE);
        final SharedPreferences preset4Preference = getApplicationContext().getSharedPreferences("Preset4",MODE_PRIVATE);
        final SharedPreferences preset5Preference = getApplicationContext().getSharedPreferences("Preset5",MODE_PRIVATE);
        final SharedPreferences preset6Preference = getApplicationContext().getSharedPreferences("Preset6",MODE_PRIVATE);
        volumeString = volumePreference.getString("Volume","0");
        volumeInt = Integer.parseInt(volumeString);
        volumeText = (TextView)findViewById(R.id.textViewRadioVolume);
        volumeText.setText(volumeString);
        tunerString = tunerPreference.getString("Tuner","100.1");
        tunerDouble = Double.parseDouble(tunerString);
        tunerText = (TextView)findViewById(R.id.textViewTuner);
        tunerText.setText(tunerString);
        final Button volumeUp = (Button)findViewById(R.id.buttonVolUp);
        final Button volumeDown = (Button)findViewById(R.id.buttonVolDown);
        final Button tunerUp = (Button)findViewById(R.id.buttonTuneMore);
        final Button tunerDown = (Button)findViewById(R.id.buttonTunerLess);
        final Button preset1 = (Button)findViewById(R.id.buttonPreset1);
        final Button preset2 = (Button)findViewById(R.id.buttonPreset2);
        final Button preset3 = (Button)findViewById(R.id.buttonPreset3);
        final Button preset4 = (Button)findViewById(R.id.buttonPreset4);
        final Button preset5 = (Button)findViewById(R.id.buttonPreset5);
        final Button preset6 = (Button)findViewById(R.id.buttonPreset6);
        preset1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                SharedPreferences.Editor editor = preset1Preference.edit();
                editor.putString("Preset1", tunerString);
                editor.commit();
                return false;
            }
        });
        preset1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tunerString = preset1Preference.getString("Preset1",tunerString);
                tunerText.setText(tunerString);
                tunerDouble = Double.parseDouble(tunerString);
            }
        });
        preset2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                SharedPreferences.Editor editor = preset1Preference.edit();
                editor.putString("Preset2", tunerString);
                editor.commit();
                return false;
            }
        });
        preset2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tunerString = preset1Preference.getString("Preset2",tunerString);
                tunerText.setText(tunerString);
                tunerDouble = Double.parseDouble(tunerString);
            }
        });
        preset3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                SharedPreferences.Editor editor = preset1Preference.edit();
                editor.putString("Preset3", tunerString);
                editor.commit();
                return false;
            }
        });
        preset3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tunerString = preset1Preference.getString("Preset3",tunerString);
                tunerText.setText(tunerString);
                tunerDouble = Double.parseDouble(tunerString);
            }
        });
        preset4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                SharedPreferences.Editor editor = preset1Preference.edit();
                editor.putString("Preset4", tunerString);
                editor.commit();
                return false;
            }
        });
        preset4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tunerString = preset1Preference.getString("Preset4",tunerString);
                tunerText.setText(tunerString);
                tunerDouble = Double.parseDouble(tunerString);
            }
        });
        preset5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                SharedPreferences.Editor editor = preset1Preference.edit();
                editor.putString("Preset5", tunerString);
                editor.commit();
                return false;
            }
        });
        preset5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tunerString = preset1Preference.getString("Preset5",tunerString);
                tunerText.setText(tunerString);
                tunerDouble = Double.parseDouble(tunerString);
            }
        });
        preset6.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                SharedPreferences.Editor editor = preset1Preference.edit();
                editor.putString("Preset6", tunerString);
                editor.commit();
                return false;
            }
        });
        preset6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tunerString = preset1Preference.getString("Preset6",tunerString);
                tunerText.setText(tunerString);
                tunerDouble = Double.parseDouble(tunerString);
            }
        });
        tunerUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tunerDouble<101.89) {
                    tunerDouble+= .1;
                    tunerString = String.format("%.1f",tunerDouble);
                    tunerText.setText(tunerString);
                    SharedPreferences.Editor editor = tunerPreference.edit();
                    editor.putString("Tuner", tunerString);
                    editor.commit();
                }
                else{
                    tunerText.setText("80.1");
                    tunerDouble = 80.1;
                    tunerString = String.format("%.1f",tunerDouble);
                    SharedPreferences.Editor editor = tunerPreference.edit();
                    editor.putString("Tuner", tunerString);
                    editor.commit();
                }
            }
        });
        tunerDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tunerDouble>80.1) {
                    tunerDouble-= .1;
                    tunerString = String.format("%.1f",tunerDouble);
                    tunerText.setText(tunerString);
                    SharedPreferences.Editor editor = tunerPreference.edit();
                    editor.putString("Tuner", tunerString);
                    editor.commit();
                }
                else{
                    tunerText.setText("101.9");
                    tunerDouble = 101.9;
                    tunerString = String.format("%.1f",tunerDouble);
                    SharedPreferences.Editor editor = tunerPreference.edit();
                    editor.putString("Tuner", tunerString);
                    editor.commit();
                }
            }
        });
        volumeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(volumeInt<9) {
                    volumeInt++;
                    volumeString = String.valueOf(volumeInt);
                    volumeText.setText(volumeString);
                    SharedPreferences.Editor editor = volumePreference.edit();
                    editor.putString("Volume", volumeString);
                    editor.commit();
                }
            }
        });
        volumeDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(volumeInt>0) {
                    volumeInt--;
                    volumeString = String.valueOf(volumeInt);
                    volumeText.setText(volumeString);
                    SharedPreferences.Editor editor = volumePreference.edit();
                    editor.putString("Volume", volumeString);
                    editor.commit();
                }
            }
        });
    }
}
