package com.example.cookmilk.prog02b;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SearchActivity extends AppCompatActivity {
    Button but1, but2, butRep, butRep2, butRep3, butRep4, butRep5;
    ImageView img1, img2, imgRep, imgRep2, imgRep3, imgRep4, imgRep5;
//    Button but4;
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
        setContentView(R.layout.activity_search);
        searchSen();
        searchSen2();
        searchRep();
        searchRep2();
        searchRep3();
        searchRep4();
        searchRep5();
        TextView tw1, tw2, twRep, twRep2, twRep3, twRep4, twRep5, twTemp;
        tw1 = (TextView) findViewById(R.id.personse1);
        tw2 = (TextView) findViewById(R.id.personse2);
        twRep = (TextView) findViewById(R.id.person3);
        twRep2 = (TextView) findViewById(R.id.person4);
        twRep3 = (TextView) findViewById(R.id.person5);
        twRep4 = (TextView) findViewById(R.id.person6);
        twRep5 = (TextView) findViewById(R.id.person7);

        img1 = (ImageView)findViewById(R.id.imgse1);
        img2 = (ImageView)findViewById(R.id.imgse2);
        imgRep = (ImageView)findViewById(R.id.img3);
        imgRep2 = (ImageView)findViewById(R.id.img4);
        imgRep3 = (ImageView)findViewById(R.id.img5);
        imgRep4 = (ImageView)findViewById(R.id.img6);
        imgRep5 = (ImageView)findViewById(R.id.img6);
//        searchMore();
        SearchHelper temp = new SearchHelper();
        String result = null;
        ArrayList<TextView> al = new ArrayList();
        ArrayList<ImageView> alImg =new ArrayList();
        al.add(tw1);
        al.add(tw2);
        alImg.add(img1);
        alImg.add(img2);

        ArrayList<TextView> alRep = new ArrayList();
        ArrayList<ImageView> alImgRep =new ArrayList();
        alRep.add(twRep);
        alRep.add(twRep2);
        alRep.add(twRep3);
        alRep.add(twRep4);
        alRep.add(twRep5);
        alImgRep.add(imgRep);
        alImgRep.add(imgRep2);
        alImgRep.add(imgRep3);
        alImgRep.add(imgRep4);
        alImgRep.add(imgRep5);
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
        int k = 0;

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
                imgLink = "https://theunitedstates.io/images/congress/450x550/" + imgID + ".jpg";
                if (title.equalsIgnoreCase("Rep")) {
                    alRep.get(k).setText("Name: " + firstName + " " + lastName + "\n" +
                            "Party: " + party + "\n" +
                            "Email: " + email + "\n" +
                            "Website: " + web);
                    Picasso.with(this)
                            .load(imgLink)
                            .resize(50, 50)
                            .centerCrop()
                            .into(alImgRep.get(k));
//                    System.out.println("======================="+k+"=============================");

                    senIDs.add(imgID);
                    senNames.add(firstName + " " + lastName);
                    eod.add(curDod);
                    senImgs.add(imgLink);
                    k++;
                } else {
                    twTemp = al.get(j);
                    twTemp.setText("Name: " + firstName + " " + lastName + "\n" +
                            "Party: " + party + "\n" +
                            "Email: " + email + "\n" +
                            "Website: " + web);
                    Picasso.with(this)
                            .load(imgLink)
                            .resize(50, 50)
                            .centerCrop()
                            .into(alImg.get(j));
                    j++;
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

    public void searchSen() {
        but1 = (Button)findViewById(R.id.personbutse1);
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
    public void searchSen2() {
        but2 = (Button)findViewById(R.id.personbutse2);
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
    public void searchRep() {
        butRep = (Button)findViewById(R.id.personbut3);
        butRep.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.cookmilk.prog02b.Searchrep");
                        startActivity(intent);
                    }
                }
        );
    }
    public void searchRep2() {
        butRep2 = (Button)findViewById(R.id.personbut4);
        butRep2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.cookmilk.prog02b.LocationrepActivity");
                        startActivity(intent);
                    }
                }
        );
    }
    public void searchRep3() {
        butRep3 = (Button)findViewById(R.id.personbut5);
        butRep3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.cookmilk.prog02b.LocationrepActivity");
                        startActivity(intent);
                    }
                }
        );
    }
    public void searchRep4() {
        butRep4 = (Button)findViewById(R.id.personbut6);
        butRep4.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.cookmilk.prog02b.LocationrepActivity");
                        startActivity(intent);
                    }
                }
        );
    }
    public void searchRep5() {
        butRep5 = (Button)findViewById(R.id.personbut6);
        butRep5.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.cookmilk.prog02b.LocationrepActivity");
                        startActivity(intent);
                    }
                }
        );
    }
//    public void searchMore() {
//        but4 = (Button)findViewById(R.id.morerep);
//        but4.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent("com.example.cookmilk.prog02b.LocationmoreActivity");
//                        startActivity(intent);
//                    }
//                }
//        );
//    }
}
