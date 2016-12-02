package com.example.ron.smartHome;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.NumberFormat;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;


public class CarList extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_car_list, container, false);

        String[] list = {"Temp","Fuel","Radio","GPS","Lights","Odometer","Drive"};
        // Inflate the layout for this fragment

        ListView listView = (ListView)view.findViewById(R.id.listView);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
               getActivity(),android.R.layout.simple_list_item_1,list
        );

        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                switch(position) {

                    case 0: tempSettings();
                    break;
                    case 1: fuelGuage();
                    break;
                    case 2: radio();
                    break;
                    case 3: gps();
                    break;
                    case 6: drive();
                    break;
                }
            }

            public void tempSettings(){

                final SharedPreferences sharedPreferences2 = getActivity().getSharedPreferences("progress2", Context.MODE_PRIVATE);
                final SharedPreferences sharedPreferences = getActivity().getSharedPreferences("progress", Context.MODE_PRIVATE);

                View v = getLayoutInflater(savedInstanceState).inflate(R.layout.auto_temp, container);
                SeekBar seekbar = (SeekBar) v.findViewById(R.id.seekBar);
                final TextView textView = (TextView) v.findViewById(R.id.textViewBackValue);
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
                SeekBar seekBar2 = (SeekBar) v.findViewById(R.id.seekBar2);
                final TextView textView2 = (TextView) v.findViewById(R.id.textViewFrontValue);
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
                AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                builder2.setTitle("Car Temperature");
                builder2.setView(v);
                builder2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                AlertDialog dialog2 = builder2.create();
                dialog2.show();
            }

            public void fuelGuage(){

                Intent intent = new Intent (getActivity(),Fuel.class);
                ((Automobile)getActivity()).startActivity(intent);

            }

            public void drive(){
                Intent intent = new Intent (getActivity(),Drive.class);
                ((Automobile)getActivity()).startActivity(intent);
            }

            public void radio(){
                Intent intent = new Intent (getActivity(),Radio.class);
                ((Automobile)getActivity()).startActivity(intent);
            }
            public void gps(){
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("google.navigation:q="));
                ((Automobile)getActivity()).startActivity(intent);
            }


        });

        return view;
    }



}
