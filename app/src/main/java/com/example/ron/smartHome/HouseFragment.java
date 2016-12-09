package com.example.ron.smartHome;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class HouseFragment extends Fragment implements View.OnClickListener {

    private ArrayList<String> list = new ArrayList(Arrays.asList("Garage", "House temp", "Outside"));

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_house, container, false);

        ListView lv = (ListView) v.findViewById(R.id.listView);
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1, list);
        lv.setAdapter(listViewAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        garage();
                        break;
                    case 1:
                        tempHouse();
                        break;
                    case 2:
                        outside();
                        break;
                }
            }


            public void garage() {

                Intent intent = new Intent(getActivity(), Garage.class);
                ((HouseSettings) getActivity()).startActivity(intent);
            }

            public void tempHouse() {
                Intent intent = new Intent(getActivity(), TempHouse.class);
                ((HouseSettings) getActivity()).startActivity(intent);
            }

            public void outside() {
                Intent intent = new Intent(getActivity(), OutsideWeather.class);
                ((HouseSettings) getActivity()).startActivity(intent);
            }

        });
        return v;
    }


    @Override
    public void onClick(View view) {

    }
}
