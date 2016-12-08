package com.example.ron.smartHome;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
/**
 * This class is a fragement used to diplay a listview of the available functions in the Automobile section
 * @author ron zimbalatti
 * @date dec 1 2016
 */
public class CarList extends Fragment {

    //String drive = getString(R.string.Drive) ;
    /**
     * arraylist represents the functions of the automobile app
     */
    ArrayList list = new ArrayList(Arrays.asList("Temp","Gas","Radio","GPS","Drive","Odometer"));
    /**
     * button to add a function to listview
     */
    Button add;
    /**
     * button to remove a function from listview
     */
    Button remove;
    /**
     * TextView used to store input for adding or removing
     */
    TextView addText;
    /**
     * database instance
     */
    SQLiteDatabase database;
    /**
     * databaseHelper instance
     */
    OptionDataBaseHelper db;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        db = new OptionDataBaseHelper(getActivity());
        database = db.getWritableDatabase();

        String query = "SELECT * FROM option WHERE 1;";

        Cursor c = database.rawQuery(query,null);

        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex(DriveDataBaseHelper.KEY_MESSAGE))!= null) {
               list.add(c.getString(c.getColumnIndex(DriveDataBaseHelper.KEY_MESSAGE)));
            }
            c.moveToNext();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_list, container, false);
        add = (Button)view.findViewById(R.id.buttonAdd);
        add.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                addText = (TextView)getActivity().findViewById(R.id.editTextList);
                list.add(addText.getText().toString());
                ContentValues value = new ContentValues();
                value.put(db.KEY_MESSAGE,addText.getText().toString());
                database.insert(db.TABLE_NAME,null,value);

            }
        });
        remove = (Button)view.findViewById(R.id.buttonRemove);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addText = (TextView)getActivity().findViewById(R.id.editTextList);
                list.remove(addText.getText().toString());
                database.delete("option","MESSAGE = '"+addText.getText().toString()+"';",null);
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
                    case 4: drive();
                        break;
                    case 5: odometer();
                        break;
                }
                return true;
            }

            public void odometer(){
                AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                builder2.setTitle(getString(R.string.Odometer));
                builder2.setMessage(getString(R.string.OdometerInfo));
                builder2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                AlertDialog dialog2 = builder2.create();
                dialog2.show();
            }

            public void temp(){
                AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                builder2.setTitle(getString(R.string.CarTemp));
                builder2.setMessage(getString(R.string.CarTempInfo));
                builder2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                AlertDialog dialog2 = builder2.create();
                dialog2.show();
            }

            public void fuel(){
                AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                builder2.setTitle(getString(R.string.FuelGuage));
                builder2.setMessage(getString(R.string.FuelGuageInfo));
                builder2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                AlertDialog dialog2 = builder2.create();
                dialog2.show();
            }

            public void drive(){
                AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                builder2.setTitle(getString(R.string.Drive));
                builder2.setMessage(getString(R.string.DriveInfo));
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
                builder2.setMessage(getString(R.string.RadioInfo));
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
                builder2.setMessage(getString(R.string.GPSInfo));
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
                    case 4: drive();
                    break;
                    case 5: odometer();
                    break;
                }
            }

            public void temp(){
                Intent intent = new Intent (getActivity(),Temp.class);
                ((Automobile)getActivity()).startActivity(intent);
            }
            public void odometer(){
                Intent intent = new Intent (getActivity(),Odometer.class);
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
                Snackbar snackbar = Snackbar.make(getView(), getString(R.string.Navigation), Snackbar.LENGTH_LONG);
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
        builder2.setTitle(getString(R.string.HelpMenu));
        builder2.setMessage(getString(R.string.HelpMenuInfo));
        builder2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        AlertDialog dialog2 = builder2.create();
        dialog2.show();


        return true;
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
