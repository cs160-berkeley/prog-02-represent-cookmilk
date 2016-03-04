package com.example.cookmilk.prog02b;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class LocationActivity extends AppCompatActivity {
    ImageButton but1, but2, but3;
//    Button but4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        locationSen();
        locationSen2();
        locationRep();
//        locationMore();
    }
    public void locationSen() {
        but1 = (ImageButton)findViewById(R.id.zipImgBut1);
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
        but2 = (ImageButton)findViewById(R.id.zipImgbut2);
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
        but3 = (ImageButton)findViewById(R.id.zipMoreImgbut1);
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
//    public void locationMore() {
//        but4 = (Button)findViewById(R.id.button3);
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
