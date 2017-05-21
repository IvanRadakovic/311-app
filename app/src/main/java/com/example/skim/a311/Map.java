package com.example.skim.a311;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class Map extends Fragment {
	MapView mMapView;
	private GoogleMap googleMap;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_map, container, false);
		
		mMapView = (MapView) rootView.findViewById(R.id.mapView);
		mMapView.onCreate(savedInstanceState);
		
		mMapView.onResume(); // needed to get the map to display immediately
		
		try {
			MapsInitializer.initialize(getActivity().getApplicationContext());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		mMapView.getMapAsync(new OnMapReadyCallback() {
			@Override
			public void onMapReady(GoogleMap mMap) {
				googleMap = mMap;
				
				// For showing a move to my location button
				if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) !=
						PackageManager.PERMISSION_GRANTED) {
					
					ActivityCompat.requestPermissions(getActivity(),
							new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
							1);
					
				}
				googleMap.setMyLocationEnabled(true);
				
				// For dropping a marker at a point on the Map
				LatLng houston = new LatLng(29.7528067,-95.4009056);
				//googleMap.addMarker(new MarkerOptions().position(houston).title("Marker Title").snippet("Desc"));
				
				// For zooming automatically to the location of the marker
				CameraPosition cameraPosition = new CameraPosition.Builder().target(houston).zoom(9).build();
				googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
				
			}
		});
		
		return rootView;
	}
	
	
	@Override
	public void onResume() {
		super.onResume();
		mMapView.onResume();
	}
	
	@Override
	public void onPause() {
		super.onPause();
		mMapView.onPause();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		mMapView.onDestroy();
	}
	
	@Override
	public void onLowMemory() {
		super.onLowMemory();
		mMapView.onLowMemory();
	}
	
	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated
	 * to the activity and potentially other fragments contained in that
	 * activity.
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		void onFragmentInteraction(Uri uri);
	}
}
