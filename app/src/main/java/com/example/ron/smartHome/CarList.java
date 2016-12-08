package com.example.ron.smartHome;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.icu.text.NumberFormat;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;

public class CarList extends Fragment implements View.OnClickListener {

    ArrayList list = new ArrayList(Arrays.asList("Temp","Fuel","Radio","GPS","Lights","Odometer","Drive"));
    Button add;
    Button remove;
    TextView addText;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,final Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_car_list, container, false);
        add = (Button)view.findViewById(R.id.buttonAdd);
        add.setOnClickListener(this);
        remove = (Button)view.findViewById(R.id.buttonRemove);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addText = (TextView)getActivity().findViewById(R.id.editTextList);
                list.remove(addText.getText().toString());
            }
        });

        // Inflate the layout for this fragment
        ListView listView = (ListView)view.findViewById(R.id.listView);
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
               getActivity(),android.R.layout.simple_list_item_1,list
        );
        listView.setAdapter(listViewAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){

                switch(position) {
                    case 0: temp();
                        break;
                    case 1: fuel();
                        break;
                    case 2: radio();
                        break;
                    case 3: gps();
                        break;
                    case 6: drive();
                        break;
                }
                return true;
            }

            public void temp(){
                AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                builder2.setTitle("Car Temperature");
                builder2.setMessage("This function is used to control the temperature in front and back of car. Adjust slider to desired temperature");
                builder2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                AlertDialog dialog2 = builder2.create();
                dialog2.show();
            }

            public void fuel(){
                AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                builder2.setTitle("Fuel Guage");
                builder2.setMessage("This function displays fuel level and distance you are able to travel with remaining fuel");
                builder2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                AlertDialog dialog2 = builder2.create();
                dialog2.show();
            }

            public void drive(){
                AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                builder2.setTitle("Drive");
                builder2.setMessage("This function replicates driving the car the distance entered. It keeps a log of all entries and will display a warning if distance entered is farther then you can drive with remaining fuel");
                builder2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                AlertDialog dialog2 = builder2.create();
                dialog2.show();
            }

            public void radio(){
                AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                builder2.setTitle("Radio");
                builder2.setMessage("This function allows you to set the tuner and adjust volume. It also has presets which are programmable with a long click");
                builder2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                AlertDialog dialog2 = builder2.create();
                dialog2.show();
            }

            public void gps(){
                AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                builder2.setTitle("GPS");
                builder2.setMessage("This function launches Google Navigation");
                builder2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                AlertDialog dialog2 = builder2.create();
                dialog2.show();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                switch(position) {
                    case 0: temp();
                    break;
                    case 1: fuel();
                    break;
                    case 2: radio();
                    break;
                    case 3: gps();
                    break;
                    case 6: drive();
                    break;
                }
            }

            public void temp(){
                Intent intent = new Intent (getActivity(),Temp.class);
                ((Automobile)getActivity()).startActivity(intent);
            }

            public void fuel(){
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
                new Navigation().execute();
                Snackbar snackbar = Snackbar.make(getView(), "Navigation Loading", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.car_menu, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
        builder2.setTitle("Help Menu");
        builder2.setMessage("Author: Ron Zimabalatti\n\nVersion: 1.0\n\nInstructions:\n\nEach Item on the list is a function you can use to access controls\n\nA long click on the item will display instructions\n\na short click will access the controls ");
        builder2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        AlertDialog dialog2 = builder2.create();
        dialog2.show();


        return true;
    }

    @Override
    public void onClick(View v) {

                addText = (TextView)getActivity().findViewById(R.id.editTextList);
                list.add(addText.getText().toString());

    }

    public class Navigation extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] params) {
            SystemClock.sleep(1000);
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("google.navigation:q="));
            ((Automobile)getActivity()).startActivity(intent);
            return null;
        }

    }

}
