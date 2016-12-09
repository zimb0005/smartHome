package com.example.ron.smartHome;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class Garage extends AppCompatActivity {

    private Switch door;
    private Switch light;
    private Button snaker;
   // private final CoordinatorLayout coordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinatorLayout);

    protected static final String ACTIVITY_NAME = "Garage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage);

        door = (Switch)findViewById(R.id.doorOpen);
        light = (Switch) findViewById(R.id.lightOn);
        snaker = (Button)findViewById(R.id.snaker);

        door.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                   light.setChecked(true);
                    CharSequence text = "Door is Open";
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(Garage.this, text, duration).show();
                } else {
                    CharSequence text = "Door is closed";
                    int duration = Toast.LENGTH_LONG;
                    Toast.makeText(Garage.this, text, duration).show();
                }
            }
        });

        light.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                View.OnClickListener mOnCistener = null;
                if(isChecked){
                    CharSequence text = "Light is On";
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(Garage.this, text, duration).show();
                } else {
                    CharSequence text = "Light is Off";
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(Garage.this, text, duration).show();
                }
            }
        });

//        snaker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Snackbar snackbar = Snackbar
//                        .make(coordinatorLayout, "smart home Snackbar testing", Snackbar.LENGTH_LONG);
//                snackbar.show();
//            }
//        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }

}
