package com.example.cookmilk.prog02b;

import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText getZip;
    Button location, search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        getZip = (EditText) findViewById(R.id.editText);
//
//        search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String curZip = getZip.getText().toString();
//
//
//            }
//        });

        locationResult();
        searchResult();

//        location.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String curZip = getZip.getText().toString();
//
//
//            }
//        });
    }
    public void searchResult() {
        location = (Button)findViewById(R.id.button);
        location.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.cookmilk.prog02b.LocationActivity");
                        startActivity(intent);
                        /////////
//                        Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
//                        sendIntent.putExtra("CAT_NAME", "Fred");
//                        startService(sendIntent);
                    }
                }
        );
    }

    public void locationResult() {
        search = (Button)findViewById(R.id.button2);
        search.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.cookmilk.prog02b.SearchActivity");
                        startActivity(intent);
                    }

                }
        );
    }

    public void sendNotification(View view) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setContentTitle(getText(R.string.app_name))
                .setContentText("Message");
        int notificationId = 1;
        NotificationManagerCompat mgr =
                NotificationManagerCompat.from(this);
        mgr.notify(notificationId,builder.build());
    }
}
