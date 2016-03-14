package com.example.cookmilk.prog02b;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.wearable.Wearable;

import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    EditText getZip;
    Button location, search;
    private GoogleApiClient mGoogleApiClient;
    static String mLatitude;
    static String mLongitude;
    static String mZip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationResult();
        searchResult();
        ///////////////////////////////////////////////
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addApi(Wearable.API)  // used for data layer API
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        ///////////////////////////////////////////////

    }

    public void searchResult() {
        location = (Button) findViewById(R.id.button);
        location.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Location mLastLocation;
                        if (ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

                        if (mLastLocation != null) {
                            mLatitude = String.valueOf(mLastLocation.getLatitude());
                            mLongitude = String.valueOf(mLastLocation.getLongitude());
//                            TextView mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
//                            TextView mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
                        }


                        Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                        Bundle bdl = new Bundle();
                        bdl.putString("CANDIDATE", "zip0000");
                        sendIntent.putExtras(bdl);
                        startService(sendIntent);
                        Intent intent = new Intent("com.example.cookmilk.prog02b.LocationActivity");
                        startActivity(intent);

                    }
                }
        );
    }
    static String getLatitude() {
        return mLatitude;
    }
    static String getLongitude() {
        return mLongitude;
    }

    public void locationResult() {
        search = (Button)findViewById(R.id.button2);
        getZip = (EditText) findViewById(R.id.editText);
        search.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                        sendIntent.putExtra("CANDIDATE", "zip"+getZip.getText().toString());
                        startService(sendIntent);
                        mZip = getZip.getText().toString();
                        System.out.println("--------------------------"+mZip+"----------------------------------");
                        Intent intent = new Intent("com.example.cookmilk.prog02b.SearchActivity");
                        startActivity(intent);
                    }

                }
        );
    }

    static String getZipCode() {
        return mZip;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle bundle) {}

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(ConnectionResult connResult) {}



}
