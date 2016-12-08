package com.example.ron.smartHome;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class fridge extends AppCompatActivity {
private SeekBar seek_bar;
    private SeekBar seek_bar2;
    private TextView text_view;
    private TextView text_view2;


    SQLiteDatabase database;
    DataBaseHelper db;
    ArrayList<String> arrayList;
    ListView listView;
    ArrayAdapter<String> listViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);


        text_view2 = (TextView)findViewById(R.id.textView2);
        text_view2.setText("Fridge Tempearture");
        seekbar();



        db = new DataBaseHelper(this);
        database = db.getWritableDatabase();

        Button button = (Button)findViewById(R.id.edit);






        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dilogue = new Dialog(fridge.this);
                dilogue.setTitle("Select Type");
                dilogue.setContentView(R.layout.custome_dilogue);
                dilogue.show();


                final EditText ed = (EditText)dilogue.findViewById(R.id.editText);
                Button bt_submit =(Button)dilogue.findViewById(R.id.submit);
                Button bt_cancel =(Button)dilogue.findViewById(R.id.cancel);

                bt_submit.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) {
                                        String newEntry = ed.getText().toString();
                                                     if(newEntry == "Fridge"){
                                                         AddData(newEntry);
                                                         ed.setText("");
                                                     }
                                                 }
                                             }

                );

            }

        });

    }


    public void AddData(String newEntry){
boolean insertdata =db.add(newEntry);
    }



    public void seekbar(){
        seek_bar = (SeekBar)findViewById(R.id.seekBar);
        seek_bar.setMax(50);
        seek_bar2 = (SeekBar)findViewById(R.id.seekBar2);
        seek_bar2.setMax(50);
        text_view = (TextView)findViewById(R.id.textView);
        text_view.setText("Temperature in celsius" + seek_bar.getProgress() + " / "+ seek_bar.getMax());
        text_view2 = (TextView)findViewById(R.id.textView2);
        text_view2.setText("Temperature in celsius" + seek_bar2.getProgress() + " / "+ seek_bar2.getMax());
        seek_bar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener(){
int progress_value;
                    final SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("progress", Context.MODE_PRIVATE);
                    String progress = sharedPreferences.getString("progress", "0");


                    @Override


                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value = progress;

                        text_view.setText(" Fridge Temperature in celsius" +progress + " / "+ seek_bar.getMax());
                        Toast.makeText(fridge.this,"Seekbar is Progress",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        Toast.makeText(fridge.this,"Seekbar is StartTracking",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("progress", text_view.getText().toString());
                        editor.commit();
                      //  text_view.setText("Fridge Temperature in celsius" + progress_value + " / "+ seek_bar.getMax());
                     //   Toast.makeText(fridge.this,"Seekbar is StopTracking",Toast.LENGTH_LONG).show();
                    }
                }
        );

        seek_bar2.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener(){
                    int progress_value2;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value2 = progress;
                        text_view2.setText("Fridger Temperature in celsius" +progress + " / "+ seek_bar2.getMax());
                        Toast.makeText(fridge.this,"Seekbar is Progress",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        Toast.makeText(fridge.this,"Seekbar is StartTracking",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        text_view.setText("Fridger Temperature in celsius" + progress_value2 + " / "+ seek_bar2.getMax());
                        Toast.makeText(fridge.this,"Seekbar is StopTracking",Toast.LENGTH_LONG).show();
                    }
                }
        );


    }
}
