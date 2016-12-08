package com.example.ron.smartHome;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;

public class KitchenList extends Fragment implements View.OnClickListener {


    ArrayList list = new ArrayList(Arrays.asList("Microwave","Fridge","Lights"));
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


        View view = inflater.inflate(R.layout.kichen_list_fragment, container, false);
        add = (Button)view.findViewById(R.id.buttonAdd);
        add.setOnClickListener(this);

        remove = (Button)view.findViewById(R.id.buttonRemove);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addText = (TextView)getActivity().findViewById(R.id.editTextList);
                list.remove(addText.getText().toString());
                addText.setText("");
            }
        });

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
                    case 0:Microwave();
                        break;
                    case 1:Fridge();
                        break;
                    case 2:Light();
                }
            }

            public void Microwave(){


               Intent intent = new Intent (getActivity(),Microwave.class);
               startActivity(intent);
            }

            public void Fridge(){


                Intent intent = new Intent (getActivity(),fridge.class);
                startActivity(intent);
            }
            public void Light(){


                Intent intent = new Intent (getActivity(),light.class);
                startActivity(intent);
            }
        });
        return view;
    }




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
