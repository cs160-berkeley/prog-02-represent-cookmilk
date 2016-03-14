package com.example.cookmilk.prog02b;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;




public class PhoneListenerService extends WearableListenerService {

    //   WearableListenerServices don't need an iBinder or an onStartCommand: they just need an onMessageReceieved.
    private static final String CAND_A = "/First";
    private static final String CAND_B = "/Second";
    private static final String CAND_C = "/Third";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("T", "in PhoneListenerService, got: " + messageEvent.getPath());

        if( messageEvent.getPath().equalsIgnoreCase(CAND_A) ) {
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Intent intent = new Intent(this, LocationActivity.class );
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //you need to add this flag since you're starting a new activity from a service
            intent.putExtra("CANDIDATE", "One");/////////////////////
            Log.d("T", "about to start watch CandidatesView with CANDIDATE: One");/////////////////////
            startActivity(intent);
        } else if (messageEvent.getPath().equalsIgnoreCase( CAND_B )) {
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Intent intent = new Intent(this, LocationActivity.class );
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //you need to add this flag since you're starting a new activity from a service
            intent.putExtra("CANDIDATE", "Two");/////////////////////
            Log.d("T", "about to start watch CandidatesView with CANDIDATE: Two");/////////////////////
            startActivity(intent);
        } else if (messageEvent.getPath().equalsIgnoreCase( CAND_C )) {
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Intent intent = new Intent(this, LocationActivity.class );
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //you need to add this flag since you're starting a new activity from a service
            intent.putExtra("CANDIDATE", "Three");/////////////////////
            Log.d("T", "about to start watch CandidatesView with CANDIDATE: Three");/////////////////////
            startActivity(intent);
        } else {
            super.onMessageReceived( messageEvent );
        }

    }
}
