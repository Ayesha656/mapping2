package com.example.mapping2;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.mapping2.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
   // private ActivityMapsBinding binding;
    private double targetLatitude = 0.0; // Default value
    private double targetLongitude = 0.0; // Default value

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if bundle has latitude and longitude values
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            targetLatitude = extras.getDouble("latitude", 0.0);
            targetLongitude = extras.getDouble("longitude", 0.0);
        }



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker at the target location and move the camera
        LatLng targetLocation = new LatLng(targetLatitude, targetLongitude);
        mMap.addMarker(new MarkerOptions().position(targetLocation).title("Marker at Target Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(targetLocation, 15));
    }

    private void moveMapToLocation(double latitude, double longitude) {
        if (mMap != null) {
            LatLng targetLocation = new LatLng(latitude, longitude);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(targetLocation, 15));
        }
    }
}
