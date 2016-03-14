package com.example.cookmilk.prog02b;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class LocationActivity extends AppCompatActivity {
    Button but1, but2, but3;
    ImageView img1, img2, imgRep;
    Bitmap bmp;
    static String id = null;
    static String thisName = null;
    static String endod = null;
    static String thisImg = null;
    static ArrayList<String> repIDs = new ArrayList();
    static ArrayList<String> senIDs = new ArrayList();
    static ArrayList<String> repNames = new ArrayList();
    static ArrayList<String> senNames = new ArrayList();
    static ArrayList<String> eod = new ArrayList();
    static ArrayList<String> senImgs = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        locationSen();
        locationSen2();
        locationRep();
        TextView tw1, tw2, twRep, twTemp;
        tw1 = (TextView) findViewById(R.id.person1);
        tw2 = (TextView) findViewById(R.id.person2);
        twRep = (TextView) findViewById(R.id.person3);

        img1 = (ImageView)findViewById(R.id.img1);
        img2 = (ImageView)findViewById(R.id.img2);
        imgRep = (ImageView)findViewById(R.id.img3);
//        Picasso.with(this)
//                .load("https://theunitedstates.io/images/congress/225x275/M000639.jpg")
//                .resize(50, 50)
//                .centerCrop()
//                .into(img1);


        LocationHelper temp = new LocationHelper();
        String result = null;
        ArrayList<TextView> al = new ArrayList();
        ArrayList<ImageView> alImg =new ArrayList();
        al.add(tw1);
        al.add(tw2);
        alImg.add(img1);
        alImg.add(img2);
        try {
            result = temp.execute("URL").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        JSONObject jObject = null;
        String firstName = "thisName";
        String lastName = "";
        String party = "";
        String email = "";
        String web  = "";
        String title = null;
        String imgID = null;
        String imgLink = null;
        String curDod = null;
        int j = 0;
        try {
            jObject = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONArray jArray = jObject.getJSONArray("results");
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject oneObject = jArray.getJSONObject(i);
                firstName = oneObject.getString("first_name");
                lastName = oneObject.getString("last_name");
                party = oneObject.getString("party");
                email = oneObject.getString("oc_email");
                web = oneObject.getString("website");
                title = oneObject.getString("title");
                imgID = oneObject.getString("bioguide_id");
                curDod = oneObject.getString("term_end");
                imgLink = "https://theunitedstates.io/images/congress/450x550/" + imgID + ".jpg";
                if (title.equalsIgnoreCase("Rep")) {
                    twRep.setText("Name: "+firstName + " " + lastName + "\n" +
                            "Party: " + party + "\n" +
                            "Email: " + email + "\n" +
                            "Website: " + web);
                    Picasso.with(this)
                            .load(imgLink)
                            .resize(50, 50)
                            .centerCrop()
                            .into(imgRep);
                    repIDs.add(imgID);
                    repNames.add(firstName + " " + lastName);
                } else {
                    twTemp = al.get(j);
                    twTemp.setText("Name: "+firstName + " " + lastName + "\n" +
                            "Party: " + party + "\n" +
                            "Email: " + email + "\n" +
                            "Website: " + web);
                    Picasso.with(this)
                            .load(imgLink)
                            .resize(50, 50)
                            .centerCrop()
                            .into(alImg.get(j));
                    senIDs.add(imgID);
                    senNames.add(firstName + " " + lastName);
                    eod.add(curDod);
                    senImgs.add(imgLink);
                    j++;
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void locationSen() {
        but1 = (Button)findViewById(R.id.personbut1);
        but1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.cookmilk.prog02b.LocationsenActivity");
                        startActivity(intent);
                    }
                }
        );
    }
    public void locationSen2() {
        but2 = (Button)findViewById(R.id.personbut2);
        but2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.cookmilk.prog02b.LocationsenActivity");
                        startActivity(intent);
                    }
                }
        );
    }
    public void locationRep() {
        but3 = (Button)findViewById(R.id.personbut3);
        but3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.cookmilk.prog02b.LocationrepActivity");
                        startActivity(intent);
                    }
                }
        );
    }

    static String getID() {
        id = repIDs.get(0);
        return id;
    }
    static String getNames() {
        thisName = senNames.get(0);
        return thisName;
    }
    static String getEOD() {
        endod = eod.get(0);
        return endod;
    }
    static String getImg() {
        thisImg = senImgs.get(0);
        return thisImg;
    }


}
