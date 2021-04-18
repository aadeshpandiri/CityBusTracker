package com.example.CityBus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.w3c.dom.Text;

public class CardClicked extends AppCompatActivity {
    TextView t1;
    String pickups;
    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    String latitude, longitude;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_clicked);
        Intent in = getIntent();
        String data = in.getStringExtra("data");
        pickups = in.getStringExtra("pickup");
        String display[] = data.split(",");
        String text = "";
        for (String s : display) {
            text = text + s + "\n\n";
        }
        int num = text.length();
        text = text.substring(1, num - 3);
        t1 = (TextView) findViewById(R.id.datadisplay);
        t1.setText(text);

    }

    public void gotomap(View view) {

//        Intent inmap = new Intent(CardClicked.this,MapTrackActivity.class);
//        inmap.putExtra("location",pickups);
//        startActivity(inmap);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(CardClicked.this);


        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String source = pickups;
        //String destination = etdestination.getText().toString().trim();

        if (source.equals("")) {
            Toast.makeText(getApplicationContext(), "Enter details Correctly ", Toast.LENGTH_SHORT).show();
        } else {


            if (ActivityCompat.checkSelfPermission(CardClicked.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(CardClicked.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                String res = getCurrentLocation();
                String resarray[] = res.split(",");

            } else {
                ActivityCompat.requestPermissions(CardClicked.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        100);
            }
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 100 && grantResults.length > 0 && (grantResults[0] + grantResults[1]
                == PackageManager.PERMISSION_GRANTED)) {
            getCurrentLocation();
        } else {
            Toast.makeText(this, "Give Permission to get Location !", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("MissingPermission")
    private String getCurrentLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {

            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    if (location != null) {
                        latitude = String.valueOf(location.getLatitude());
                        longitude = String.valueOf(location.getLongitude());

                        System.out.println("details:" + latitude + "," + longitude);

                        DisplayTrack(latitude, longitude, pickups);

                    } else {
                        LocationRequest locationRequest = new LocationRequest()
                                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                .setInterval(10000)
                                .setFastestInterval(1000)
                                .setNumUpdates(1);

                        LocationCallback locationCallback = new LocationCallback() {
                            @Override
                            public void onLocationResult(LocationResult locationResult) {
                                Location location1 = locationResult.getLastLocation();
                                latitude = String.valueOf(location1.getLatitude());
                                longitude = String.valueOf(location1.getLongitude());
                                DisplayTrack(latitude, longitude, pickups);
                            }
                        };

                        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback
                                , Looper.myLooper());
                    }
                }
            });
        } else {
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
        return (latitude + "," + longitude);
    }

    private void DisplayTrack(String latitude, String longitude, String destination) {
        try {
            //Uri uri = Uri.parse("https://www.google.co.in/maps/dir/"+source+"/"+destination);
            System.out.println("details for track :" + latitude + "," + longitude);
            Uri uri = Uri.parse("https://www.google.com/maps/dir/" + (latitude) + ",+" + (longitude) + "/" + destination);
            Intent i = new Intent(Intent.ACTION_VIEW, uri);
            i.setPackage("com.google.android.apps.maps");
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        } catch (ActivityNotFoundException e) {
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            Intent in = new Intent(Intent.ACTION_VIEW, uri);

            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(in);
        }


    }
}