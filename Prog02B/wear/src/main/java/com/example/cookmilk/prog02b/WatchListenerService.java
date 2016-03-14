package com.example.cookmilk.prog02b;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;



public class WatchListenerService extends WearableListenerService {
    // In PhoneToWatchService, we passed in a path, either "/FRED" or "/LEXY"
    // These paths serve to differentiate different phone-to-watch messages

    private static final String FIRST = "/First";
    private static final String SECOND = "/Second";
    private static final String THIRD = "/Third";


    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("T", "in WatchListenerService, got: " + messageEvent.getPath());
        //use the 'path' field in sendmessage to differentiate use cases
        //(here, fred vs lexy)

        if( messageEvent.getPath().equalsIgnoreCase( FIRST ) ) {
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Intent intent = new Intent(this, MainActivity.class );
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //you need to add this flag since you're starting a new activity from a service
            intent.putExtra("CANDIDATE", "One");/////////////////////
            Log.d("T", "about to start watch MainActivity with CANDIDATE: One");/////////////////////
            startActivity(intent);
        } else if (messageEvent.getPath().equalsIgnoreCase( SECOND )) {
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Intent intent = new Intent(this, MainActivity.class );
            intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
            //you need to add this flag since you're starting a new activity from a service
            intent.putExtra("CANDIDATE", "Two");/////////////////////
            Log.d("T", "about to start watch MainActivity with CANDIDATE: Two");/////////////////////
            startActivity(intent);
        } else if (messageEvent.getPath().equalsIgnoreCase( THIRD )) {
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Intent intent = new Intent(this, MainActivity.class );
            intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
            //you need to add this flag since you're starting a new activity from a service
            intent.putExtra("CANDIDATE", "C");/////////////////////
            Log.d("T", "about to start watch MainActivity with CANDIDATE: Two");/////////////////////
            startActivity(intent);
        } else if (messageEvent.getPath().startsWith("/zip")) {
            int newzip = Integer.parseInt(messageEvent.getPath().substring(4));
            Log.d("T", "about to set location:" + messageEvent.getPath().substring(4));
//            ((Helper) this.getApplication()).setZip(newzip);
            Log.d("T", "locsetto:" + Integer.toString(newzip));
            Intent intent = new Intent(this, ResultActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            Toast.makeText(this, "Searching", Toast.LENGTH_LONG).show();/////////////////////
        } else {
            super.onMessageReceived( messageEvent );
        }

    }
}
