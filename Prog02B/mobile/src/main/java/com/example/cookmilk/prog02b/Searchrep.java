package com.example.cookmilk.prog02b;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Searchrep extends AppCompatActivity {
    ImageView img;
    TextView tw1, tw2, tw3, tw4, tw0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locationsen);
        tw0 = (TextView) findViewById(R.id.name);
        tw1 = (TextView) findViewById(R.id.twparty);
        tw2 = (TextView) findViewById(R.id.tweot);
        tw3 = (TextView) findViewById(R.id.twcom);
        tw4 = (TextView) findViewById(R.id.twbill);
        img = (ImageView)findViewById(R.id.img);
        String name = SearchActivity.getNames();
        String eod = SearchActivity.getEOD();
        String imgs = SearchActivity.getImg();
        tw0.setText(name);
        tw1.setText("D");
        tw2.setText(eod);
        Picasso.with(this)
                .load(imgs)
                .resize(80, 80)
                .centerCrop()
                .into(img);

    }
}
