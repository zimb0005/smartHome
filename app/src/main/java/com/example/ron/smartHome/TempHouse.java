package com.example.ron.smartHome;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class TempHouse extends AppCompatActivity {

    private static final String ACTIVITY_NAME = "TempHouse";
    private static ListView list;
    private static EditText timeValue;
    private static EditText tempValue;
    private ImageView add;
    private ImageView delete;
    private static TextView message;
    private TempAdapter tempAdapter;

    SQLiteDatabase db;
    final TempDatabaseHelper cdh = new TempDatabaseHelper(this);
    private ArrayList<String> tempSchedule = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_house);
        timeValue = (EditText) findViewById(R.id.time1);
        tempValue = (EditText) findViewById(R.id.temp1);
        add = (ImageView) findViewById(R.id.add);
        delete = (ImageView) findViewById(R.id.delete);
        list = (ListView) findViewById(R.id.schedule);

        db = cdh.getWritableDatabase();
        final ContentValues newValue = new ContentValues();

        Cursor c = db.query(false, cdh.TABLE_NAME, new String[]{cdh.TIME}, null, null, null, null, null, null);

        c.moveToFirst();
        while (!c.isAfterLast()) {
            tempSchedule.add(c.getString(c.getColumnIndex(cdh.TIME)));
            Log.i(ACTIVITY_NAME, "SQL MESSAGE: " + c.getString(c.getColumnIndex(TempDatabaseHelper.TIME)));
            tempSchedule.add(c.getString(c.getColumnIndex(cdh.TIME)));
            c.moveToNext();
        }

        for (String data : c.getColumnNames()) {
            tempSchedule.add(data);
        }
        String[] allColumns = {cdh.KEY_ID, cdh.TIME, cdh.TEMP};

        tempAdapter = new TempAdapter(this);
        list.setAdapter(tempAdapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!timeValue.getText().equals("")) {
                    newValue.put(cdh.TIME, timeValue.getText().toString());
                    newValue.put(cdh.TEMP, tempValue.getText().toString());
                    db.insert(cdh.TABLE_NAME, "Null", newValue);

                    tempSchedule.add(timeValue.getText().toString() + " TEMP "
                            + tempValue.getText().toString());
                    tempAdapter.notifyDataSetChanged(); //this restarts the process of getCount()/ getView()
                    timeValue.setText("");
                    tempValue.setText("");
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!timeValue.getText().equals("")) {

                    db.delete(cdh.TABLE_NAME, "Null", null);

                    tempSchedule.remove(timeValue.getText().toString() + " TEMP "
                            + tempValue.getText().toString());
                    tempAdapter.notifyDataSetChanged(); //this restarts the process of getCount()/ getView()
                    timeValue.setText("");
                    tempValue.setText("");
                }
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    public void open() throws SQLException {
        db = cdh.getWritableDatabase();
    }

    private class TempAdapter extends ArrayAdapter<String> {

        public TempAdapter(Context ctx) {
            super(ctx, 0);
        }

        @Override
        public int getCount() {
            return tempSchedule.size();
        }

        @Override
        public String getItem(int position) {
            return tempSchedule.get(position);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View result = null;
            LayoutInflater inflater = TempHouse.this.getLayoutInflater();

            result = inflater.inflate(R.layout.temp_row_value, null);

            message = (TextView) result.findViewById(R.id.tempValue1);
            message.setText(getItem(position)); // get the string at position
            return result;
        }

    }
}
