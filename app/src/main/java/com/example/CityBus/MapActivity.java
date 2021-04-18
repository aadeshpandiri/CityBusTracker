package com.example.CityBus;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener {

    boolean isPermissionGranted;
    /*MapView mapView;*/
    GoogleMap mGoogleMap;
    FloatingActionButton fab;
    private FusedLocationProviderClient mlocationClient;
    private int GPS_REQUEST_CODE = 9001;
    EditText txtlc;
    ImageView searchlc;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__map);

     /*   mapView = findViewById(R.id.map_view);*/
        fab = findViewById(R.id.usrfab);
        txtlc = findViewById(R.id.lctxt);
        searchlc =findViewById(R.id.lcsearch);

        checkMyPermission();

    }

    private void checkMyPermission() {

        iniMap();

        mlocationClient = new FusedLocationProviderClient( this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCurLoc();
            }
        });

        searchlc.setOnClickListener(this::geoLocate);

        Dexter.withContext(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                Toast.makeText( MapActivity.this,"Permission Granted", Toast.LENGTH_SHORT).show();
                isPermissionGranted = true;

            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(),"");
                intent.setData(uri);
                startActivity(intent);
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }

    private void geoLocate(View view) {
        String LocationName = txtlc.getText().toString();
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addressList = geocoder.getFromLocationName(LocationName,1);
            if (addressList.size()>0){
                Address address = addressList.get(0);
                gotoLocation(address.getLatitude(),address.getLongitude());
                mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(address.getLatitude(),address.getLatitude())));
                Toast.makeText(this, address.getLocality(), Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {

        }
    }

    private void iniMap() {
        if(isPermissionGranted){
            if (isGPSenable()){
                /*mapView.getMapAsync(this);
            mapView.onCreate(savedInstanceState);*/
                SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_vieww);
                supportMapFragment.getMapAsync( this);
            }
            }

    }

    private boolean isGPSenable(){

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean providerEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (providerEnable){
            return true;
        } else {
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle("GPS Permission")
                    .setMessage("GPS is required for this app to work. Please enable GPS")
                    .setPositiveButton("Yes", ((dialogInterface, i) ->{
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(intent, GPS_REQUEST_CODE);
                    } ))

                    .setCancelable(false)
                    .show();
        }

        return false;
    }

    @SuppressLint("MissingPermission")
    private void getCurLoc() {
        mlocationClient.getLastLocation().addOnCompleteListener (task -> {
            if (task.isSuccessful()){
                Location location = task.getResult();
                gotoLocation(location.getLatitude(),location.getLongitude());
            }
        });
    }

    private void gotoLocation(double latitude, double longitude) {

        LatLng Latlng = new LatLng(latitude, longitude);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(Latlng, 18);
        mGoogleMap.moveCamera(cameraUpdate);
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        /*mGoogleMap.setMyLocationEnabled(true);*/
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode== GPS_REQUEST_CODE){
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            boolean providerEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            if (providerEnable){
                Toast.makeText( this, "GPS is Enable" , Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,"GPS is not Enable", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void onComplete(Task<Location> task) {
        if (task.isSuccessful()) {
            Location location = task.getResult();
            gotoLocation(location.getLatitude(), location.getLongitude());
        }
    }

    /*    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }*/
}
