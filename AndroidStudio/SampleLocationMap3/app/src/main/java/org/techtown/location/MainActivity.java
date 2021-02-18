package org.techtown.location;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pedro.library.AutoPermissions;

public class MainActivity extends AppCompatActivity {
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
            }
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLocationService();
            }
        });

        AutoPermissions.Companion.loadAllPermissions(this, 101);
    }

    public void startLocationService() {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try {
            long minTime = 10000;
            float minDistance = 0;
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    minTime,
                    minDistance,
                    new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            showCurrentLocation(location);
                        }
                        public void onProviderDisabled(String provider) { }

                        public void onProviderEnabled(String provider) { }

                        public void onStatusChanged(String provider, int status, Bundle extras) { }
                    });
        } catch(SecurityException e) {
             e.printStackTrace();
           }
    }

    public void showCurrentLocation(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        LatLng curPoint = new LatLng(latitude, longitude);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15));

        showMyLocationMarker(curPoint);
    }

    public void showMyLocationMarker(LatLng curPoint) {
        MarkerOptions myLocationMarker =  new MarkerOptions();
        myLocationMarker.position(curPoint);
        myLocationMarker.title("내 위치");
        myLocationMarker.snippet("GPS로 확인한 위치");
        myLocationMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.mylocation));

        map.addMarker(myLocationMarker);
    }
}