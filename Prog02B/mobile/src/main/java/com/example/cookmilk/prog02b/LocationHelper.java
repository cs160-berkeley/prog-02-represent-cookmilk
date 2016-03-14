package com.example.cookmilk.prog02b;

/**
 * Created by Cookmilk on 3/12/16.
 */
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.wearable.Wearable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class LocationHelper extends AsyncTask<String, Void, String>{

//    private GoogleApiClient mGoogleApiClient;//////////////*****************//////////////////////

    @Override
    protected String doInBackground(String... params) {

        ////////////////////////////
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .addApi(LocationServices.API)
//                .addApi(Wearable.API)  // used for data layer API
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this)
//                .build();
        ///////////////////////////
        String latitude;
        String longitude;
        String link;
        String temp1 = "&longitude=";
        String temp2 = "&";
        latitude = MainActivity.getLatitude();
        longitude = MainActivity.getLongitude();


        HttpsURLConnection urlConnection = null;
        BufferedReader reader = null;
        String forecastJsonStr = "";
        String baseUrl = "https://congress.api.sunlightfoundation.com/legislators/locate?latitude=";
        String apiKey = "apikey=" + "a3a61bae5e8a4be9aae5272066d30ed7";
        URL url = null;
        link = baseUrl + latitude + temp1 + longitude + temp2 + apiKey;
        try {
//            url = new URL("https://congress.api.sunlightfoundation.com/legislators/locate?latitude=40&longitude=-74&apikey=a3a61bae5e8a4be9aae5272066d30ed7");
            url = new URL(link);

            urlConnection = (HttpsURLConnection) url.openConnection();///////////
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"), 8);
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                return null;
            }
            forecastJsonStr = buffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return forecastJsonStr;

    }

}
