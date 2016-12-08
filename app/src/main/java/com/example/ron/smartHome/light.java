package com.example.ron.smartHome;

import android.content.ContentResolver;
import android.media.audiofx.BassBoost;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class light extends AppCompatActivity {
private int brightness;
    private ContentResolver cResolver;
    private Window window;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);
        Button on = (Button)findViewById(R.id.button8);
        on.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Lights Turned ON ", Toast.LENGTH_LONG).show();
            }
        });
        Button off = (Button)findViewById(R.id.button10);
        off.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Lights Turned Off", Toast.LENGTH_LONG).show();
            }
        });
      //  final TextView tv = (TextView)findViewById(R.id.textView9);
        SeekBar seekbar = (SeekBar)findViewById(R.id.seekBar);
        cResolver=getContentResolver();
        window =getWindow();
        seekbar.setMax(255);
        seekbar.setKeyProgressIncrement(1);
try{
    brightness = Settings.System.getInt(cResolver, Settings.System.SCREEN_BRIGHTNESS);

} catch (Settings.SettingNotFoundException e) {
    e.printStackTrace();
}

seekbar.setProgress(brightness);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

               if(brightness<=20){
                   brightness = 20;
               }else
               {
                   brightness =progress;
               }

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
Settings.System.putInt(cResolver,Settings.System.SCREEN_BRIGHTNESS,brightness);
                WindowManager.LayoutParams layoutParams = window.getAttributes();
                layoutParams.screenBrightness = brightness / (float)255;
                window.setAttributes(layoutParams);

            }
        });
    }
}
