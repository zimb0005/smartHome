package com.example.ron.smartHome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class OutsideWeather extends AppCompatActivity {

    private static String[] temp;
    private static String iconName;
    private Bitmap picture = null;
    private ProgressBar progressBar;
    protected static final String ACTIVITY_NAME = "WeatherForecast";
    private ImageView icon;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outside_weather);

        icon = (ImageView) findViewById(R.id.weatherView);
        tv3 = (TextView) findViewById(R.id.textView3);
        tv4 = (TextView) findViewById(R.id.textView4);
        tv5 = (TextView) findViewById(R.id.textView5);

        ForecastQuery FQ = new ForecastQuery();
        FQ.execute("http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=d99666875e0e51521f0040a3d97d0f6a&mode=xml&units=metric");

    }

    private class ForecastQuery extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... args) {
            temp = new String[3];

            try {
                URL url = new URL(args[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();
                InputStream in = conn.getInputStream();

                XmlPullParser parser = Xml.newPullParser();
                try {
                    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    parser.setInput(in, null);
                    parser.nextTag();
                    while (parser.next() != XmlPullParser.END_TAG) {
                        if (parser.getEventType() != XmlPullParser.START_TAG) {
                            continue;
                        }
                        String name = parser.getName();
                        if (name.equals("temperature")) {
                            temp[0] = parser.getAttributeValue(null, "value");
                            publishProgress(25);
                            temp[1] = parser.getAttributeValue(null, "min");
                            publishProgress(50);
                            temp[2] = parser.getAttributeValue(null, "max");
                            publishProgress(75);
                        }

                        if (name.equals("weather")) {
                            iconName = parser.getAttributeValue(null, "icon");
                            System.out.println("icon name: " + iconName);
                            if (!fileExistance(iconName + ".png")) {
                                final String imgString = "http://openweathermap.org/img/w/" + iconName + ".png";
                                getBitmapFromURL(imgString);

                            }else{
                                FileInputStream fis = null;
                                try {
                                    fis = new FileInputStream(iconName + ".png");
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                                picture = BitmapFactory.decodeStream(fis);

                                Log.i(ACTIVITY_NAME, "Looking for " + iconName + ".png");
                            }
                        } else {
                            if (parser.getEventType() != XmlPullParser.START_TAG) {
                                throw new IllegalStateException();
                            }
                            int depth = 1;
                            while (depth != 0) {
                                switch (parser.next()) {
                                    case XmlPullParser.END_TAG:
                                        depth--;
                                        break;
                                    case XmlPullParser.START_TAG:
                                        depth++;
                                        break;
                                }
                            }
                        }
                    }
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {

            }
            return temp[0];
        }

        @Override
        protected void onProgressUpdate(Integer... value) {

            progressBar = (ProgressBar) findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(value[0]);

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressBar = (ProgressBar) findViewById(R.id.progressBar);
            progressBar.setVisibility(View.INVISIBLE);
            tv3.setText("temperature: " + temp[0]);
            tv4.setText("Min: " + temp[1]);
            tv5.setText("Max: " + temp[2]);
            icon.setImageBitmap(picture);
        }

        public boolean fileExistance(String fname) {
            File file = getBaseContext().getFileStreamPath(fname);
            return file.exists();
        }

        public Bitmap getBitmapFromURL(String src) {
            try {
                URL url = new URL(src);
                HttpURLConnection connection = (HttpURLConnection) url
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                picture = BitmapFactory.decodeStream(connection.getInputStream());

                return picture;

            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                return null;
            }
        }
    }
}
