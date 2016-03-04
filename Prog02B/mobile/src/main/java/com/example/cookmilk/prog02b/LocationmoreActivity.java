package com.example.cookmilk.prog02b;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class LocationmoreActivity extends AppCompatActivity {
    ImageButton but1, but2, but3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locationmore);
        more1();
        more2();
        more3();
    }
    public void more1() {
        but1 = (ImageButton)findViewById(R.id.zipMoreImgbut1);
        but1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.cookmilk.prog02b.LocationrepActivity");
                        startActivity(intent);
                    }
                }
        );
    }

    public void more2() {
        but2 = (ImageButton)findViewById(R.id.zipMoreImgbut2);
        but2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.cookmilk.prog02b.LocationrepActivity");
                        startActivity(intent);
                    }
                }
        );
    }

    public void more3() {
        but3 = (ImageButton)findViewById(R.id.zipMoreImgbut3);
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
}
