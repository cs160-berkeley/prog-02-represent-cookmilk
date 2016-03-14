package com.example.cookmilk.prog02b;

/**
 * Created by Cookmilk on 3/12/16.
 */
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class SearchHelper extends AsyncTask<String , Void, String>{
    @Override
    protected String doInBackground(String... params) {
        HttpsURLConnection urlConnection = null;
        BufferedReader reader = null;
        String forecastJsonStr = "";
        String baseUrl = "Https://congress.api.sunlightfoundation.com/legislators/locate?zip=";
        String zip = MainActivity.getZipCode();
        String apiKey = "&apikey=" + "a3a61bae5e8a4be9aae5272066d30ed7";
        String link = null;
        link = baseUrl + zip + apiKey;
//        System.out.println("--------------------------"+link+"----------------------------------");
        URL url = null;
        try {
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
