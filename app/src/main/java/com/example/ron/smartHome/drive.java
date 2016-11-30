package com.example.ron.smartHome;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Drive extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drive);

        Button button = (Button)findViewById(R.id.buttonGo);


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
                }


            }
        });
    }
}
