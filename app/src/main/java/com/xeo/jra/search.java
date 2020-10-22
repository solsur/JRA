package com.xeo.jra;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

public class search extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final String[] PERMISSIONS_REQUIRED = new String[] {"android.permission.ACCESS_FINE_LOCATION"};
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    //Please replace localhost with IP 10.0.2.2 when your web service deployed in localhost
    final String phpURL = "http://192.168.1.13/myplace/CPlace/getListNearPlaceWithRadiusToJson";
    public MyHandler mHander;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final double RADIUS_KM = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
        mHander = new MyHandler();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(PERMISSIONS_REQUIRED, PERMISSIONS_REQUEST_CODE);
            return;
        }
        //Enable MyLocation button on Map
        mMap.setMyLocationEnabled(true);
        //Enable Zoom control button on Map
        mMap.getUiSettings().setZoomControlsEnabled(true);
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    //Zoom level
                    float zoom = 13.0f;
                    //Move camera to current location and apply zoom level
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), zoom));

                    //Draw a circle with center point is current position
                    drawCircle(new LatLng(location.getLatitude(), location.getLongitude()));
                    //Create thread to get data from php and then display result in map
                    ThreadGetData thread = new ThreadGetData();
                    thread.setRadius(RADIUS_KM);
                    thread.setLon(location.getLongitude());
                    thread.setLat(location.getLatitude());
                    thread.start();
                }

            }
        });
    }

    private void drawCircle(LatLng latLng) {
        CircleOptions circleOptions = new CircleOptions();
        //Set center of circle
        circleOptions.center(latLng);
        //Set radius of circle
        circleOptions.radius(search.RADIUS_KM * 1000);
        //Set border color of circle
        circleOptions.strokeColor(Color.BLUE);
        //Set border width of circle
        circleOptions.strokeWidth(2);
        //Adding circle to map
        Circle mapCircle = mMap.addCircle(circleOptions);
        //We can remove above circle with code bellow
        //mapCircle.remove();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            if (PackageManager.PERMISSION_GRANTED == grantResults[0]) {
                onMapReady(mMap);
            } else {
                Toast.makeText(getApplicationContext(), "Permission request denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @SuppressLint("HandlerLeak")
    private class MyHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 1) {
                ArrayList<PlaceMarker> listMarker = PlaceMarker.fromJsonArray(String.valueOf(msg.obj));
                for (PlaceMarker marker : listMarker) {
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(new LatLng(marker.getLat(), marker.getLon()));
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    markerOptions.title(marker.getName() + " _ " + marker.getDistance() + "(km)");
                    mMap.addMarker(markerOptions);
                }
            }
        }
    }

    private class ThreadGetData extends Thread {
        private double radius;
        private double lat;
        private double lon;

        public void setRadius(double radius) {
            this.radius = radius;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }
    }
}