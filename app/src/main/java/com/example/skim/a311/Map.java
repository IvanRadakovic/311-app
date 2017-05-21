package com.example.skim.a311;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import com.google.maps.android.heatmaps.Gradient;
import com.google.maps.android.heatmaps.HeatmapTileProvider;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Map extends Fragment {
	MapView mMapView;
	private GoogleMap googleMap;
	Marker marker;
	Circle markerRadius;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_map, container, false);
		
		mMapView = (MapView) rootView.findViewById(R.id.mapView);
		mMapView.onCreate(savedInstanceState);
		final SlidingUpPanelLayout sp = (SlidingUpPanelLayout)rootView.findViewById(R.id.sliding_layout) ;

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
				
				try {
					// Customise the styling of the base map using a JSON object defined
					// in a raw resource file.
					boolean success = googleMap.setMapStyle(
							MapStyleOptions.loadRawResourceStyle(
									getActivity(), R.raw.style_json));
					
					if (!success) {
						Log.e("MapStyle", "Style parsing failed.");
					}
				} catch (Resources.NotFoundException e) {
					Log.e("MapStyle", "Can't find style. Error: ", e);
				}
				
				// For showing a move to my location button
				if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) !=
						PackageManager.PERMISSION_GRANTED) {
					
					ActivityCompat.requestPermissions(getActivity(),
							new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
							1);
					
					while(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) !=
							PackageManager.PERMISSION_GRANTED) {
						try {
							Thread.sleep(20);
						} catch(InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}
				googleMap.setMyLocationEnabled(true);
				
				CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(29.7528067,-95.4009056)).zoom(9).build();
				googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


				addHeatMap();
				
				
				googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
					@Override
					public void onMapClick(LatLng point) {
						if(marker != null)
							marker.remove();
						
						if(markerRadius != null)
							markerRadius.remove();
						
						marker = googleMap.addMarker(new MarkerOptions().position(point).icon(BitmapDescriptorFactory
								.fromResource(R.drawable.shield)));
						
						markerRadius = googleMap.addCircle(new CircleOptions().center(point).radius(750)
								.fillColor(0x7003a9f3).strokeColor(0).zIndex(5));

						sp.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);

						
					}
				});
				
			}
		});
		TextView maptime = (TextView)rootView.findViewById(R.id.maptime);
		Calendar c = Calendar.getInstance();
		maptime.setText(c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE));
		Button bt = (Button)rootView.findViewById(R.id.slidingtest);
		bt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});

		sp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus){
					sp.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
				}
			}
		});

		
		return rootView;
	}
	
	
	private void addHeatMap() {
		int total = 50;
		
		double x = 29.23;
		double y = -95.87;
		
		
		List<LatLng> list = new ArrayList<>();
		
		double size = 1.0 / total;
		for(int i = 0; i < total; i++)
			for(int j = 0; j < total; j++) {
				if(Math.random() < 0.6) {
					int weight = (int) Math.floor(Math.random() * 50);
					for(int n = 0; n < weight; n++)
						list.add(new LatLng(x + (i + Math.random() * 3 - 1) * size, y + (j + Math.random() * 3 - 1) * size));
				}
			}
		
		int[] colors = {Color.rgb(161, 239, 70), Color.rgb(255, 69, 55)};
		
		float[] startPoints = {0.2f, 1f};
		
		Gradient gradient = new Gradient(colors, startPoints);
			
		HeatmapTileProvider mProvider = new HeatmapTileProvider.Builder().data(list).radius(50).gradient(gradient).opacity(0.65).build();
		googleMap.addTileOverlay(new TileOverlayOptions().tileProvider(mProvider));
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
