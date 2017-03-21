package com.example.healthcare.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.healthcare.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;

public class MapsActivity extends AppCompatActivity
		implements OnMapReadyCallback {

	public static final String TYPE = "type";

	private GoogleMap mGoogleMap;
	private HashMap<LatLng, String> places;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps);
		SupportMapFragment mapFragment =
				(SupportMapFragment) getSupportFragmentManager()
						.findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);

		places = new HashMap<>();

		if (getIntent().getStringExtra(TYPE).equals("Bhamashah Center")) {
			places.put(new LatLng(26.9077982, 75.8010021), "Rajshani Hospital");
			places.put(new LatLng(26.9092075, 75.7978041), "Anand Hospital");
			places.put(new LatLng(26.9039964, 75.8027812), "Trauma Center SMS Hospital");
			places.put(new LatLng(26.8962595, 75.7983817), "Jaipur Hospital");
			places.put(new LatLng(26.9048098, 75.8074162), "JK Lone Hospital");

			places.put(new LatLng(26.9079565, 75.793486), "Bhamasha Swasthya Bima Yojana");
			places.put(new LatLng(26.9041487, 75.8000306), "Bhamasha Yojana");
		} else {
			places.put(new LatLng(26.8635516, 75.7530599), "Asha Bhawan");
		}
	}

	@Override
	public void onMapReady(GoogleMap googleMap) {
		mGoogleMap = googleMap;

		LatLngBounds.Builder latLngBoundsBuilder = new LatLngBounds.Builder();
		for (LatLng latLng: places.keySet()) {
			mGoogleMap.addMarker(new MarkerOptions()
					.position(latLng)
					.title(places.get(latLng)));
			latLngBoundsBuilder.include(latLng);
		}
		LatLng currentLocation = new LatLng(26.905614, 75.806000);
		mGoogleMap.addMarker(new MarkerOptions()
				.position(currentLocation)
				.title("Current Location")
				.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.ic_action_navigation_cancel)))
				.showInfoWindow();
		latLngBoundsBuilder.include(currentLocation);
		mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(
				latLngBoundsBuilder.build(), 50));
	}
}
