package com.gdgcochabamba.ubicate;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import com.gdgcochabamba.ubicate.controller.CheckinController;
import com.gdgcochabamba.ubicate.controller.PlaceController;
import com.gdgcochabamba.ubicate.exceptions.PlaceRequestException;
import com.gdgcochabamba.ubicate.model.Checkin;
import com.gdgcochabamba.ubicate.model.Place;
import com.gdgcochabamba.ubicate.rest.helpers.PlaceHelper;
import com.gdgcochabamba.ubicate.util.Utility;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.MapFragment;

import android.R.integer;
import android.R.string;
import android.test.AndroidTestRunner;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.LatLngBounds.Builder;

@SuppressLint("NewApi")
public class MapActivity extends Activity implements OnMapClickListener,
		OnMyLocationChangeListener,OnMarkerDragListener, OnMapLongClickListener, OnMarkerClickListener, OnClickListener {

	private static GoogleMap map;
	protected CheckBox checkbox;
	List<Place> places;
	private boolean update = true;
	Location location;
	private boolean create = false; 
	String markerId ="";
	public Marker newMarker;
	private HashMap<String, String> markerPlace = new HashMap<String, String>(); 
	EditText comment;
	Dialog dialog;
	

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

		map.setMyLocationEnabled(true);
		map.getUiSettings().setZoomControlsEnabled(true);
		map.getUiSettings().setCompassEnabled(true);
		map.getUiSettings().setMyLocationButtonEnabled(true);
		location = map.getMyLocation();
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-17.385126,
				-66.155055), 15));

		if (location != null) {
			Log.d("LOCATION", "Lng: " + location.getLongitude() + " lat: "
					+ location.getLatitude());
		} else {
			Log.d("LOCATION", "Location is null");
		}

		map.setOnMapClickListener(this);
		map.setOnMyLocationChangeListener(this);
		map.setOnMarkerDragListener(this);
		map.setOnMarkerClickListener(this);

	}

	@Override
	public void onMapClick(LatLng point) {


	}

	
	public void addMarker() {
		
		Marker maker = map.addMarker(new MarkerOptions()
		.position(
				new LatLng(location.getLatitude(), location.getLongitude()))
		.title("New Place")
		.snippet("touch to add this place")
		.icon(BitmapDescriptorFactory
				.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
		.anchor(0.5f, 0.5f).draggable(true));
		markerId =  maker.getId();
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()),18));
		newMarker = maker;
		
	}
	public void animateCamera(Location location) {
		Log.d("Change", "change Location");
		if (map.getMyLocation() != null)
			map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
					location.getLatitude(), location.getLongitude()), 15));
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {

		super.onConfigurationChanged(newConfig);

		if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
			Log.d("ORIENTATION_PORTRAIT", "ORIENTATION_PORTRAIT");
		} else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			Log.d("ORIENTATION_LANDSCAPE", "ORIENTATION_LANDSCAPE");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map_menu, menu);
		return true;
	}

	public void refreshPlaces() {
		Log.d("refreshing", "Refresh places ");
		
		if (location != null) {
			Log.d("Location", "lng: "+location.getLongitude()+" lat: "+location.getLatitude());
			map.clear();
			animateCamera(location);
			new LoadPlaces()
					.execute(new String[] { location.getLatitude() + "",
							location.getLongitude() + "" });
		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_update:
			refreshPlaces();
			return true;
		case R.id.action_add_place:
			addMarker();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void checkLocationState() {

		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		boolean gps_enabled = false;
		try {
			gps_enabled = locationManager
					.isProviderEnabled(LocationManager.GPS_PROVIDER);
		} catch (Exception ex) {
			Log.e("MainActivity", ex.getMessage());
		}

		if (!gps_enabled) {
			Dialog dialog = createDialog();
			dialog.show();
		}

	}

	public Dialog createDialog() {
		LayoutInflater inflater = this.getLayoutInflater();
		View neverShow = inflater.inflate(R.layout.never_show, null);
		checkbox = (CheckBox) neverShow.findViewById(R.id.checkbox);

		// Use the Builder class for convenient dialog construction
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setView(neverShow)
				.setTitle(R.string.location_settings_title)
				.setMessage(R.string.location_instructions)
				.setPositiveButton(R.string.location_settings,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								if (checkbox.isChecked()) {
									doNotShowAgain();
								}
								Intent myIntent = new Intent(
										Settings.ACTION_LOCATION_SOURCE_SETTINGS);
								startActivity(myIntent);
							}
						})
				.setNegativeButton(R.string.location_skip,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// User cancelled the dialog
								if (checkbox.isChecked()) {
									doNotShowAgain();
								}
							}
						});
		// Create the AlertDialog object and return it
		return builder.create();
	}

	private void doNotShowAgain() {
		// persist shared preference to prevent dialog from showing again.
		Log.d("MainActivity", "TODO: Persist shared preferences.");
	}

	public class LoadPlaces extends AsyncTask<String, String, String> {
		ProgressDialog pDialog;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(MapActivity.this);
			pDialog.setMessage(Html
					.fromHtml("<b>Search</b><br/>Loading Places..."));
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {

			PlaceController controller = new PlaceController();
			try {

				// get nearest places
				places = controller.findPlacesByProximity(params[0], params[1]);

			} catch (PlaceRequestException p) {

				Log.e("ERROR from Google PLaces API", p.getMessage());
				Toast msg = Toast.makeText(getApplicationContext(),
						p.getMessage(), Toast.LENGTH_LONG);
				msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2,
						msg.getYOffset() / 2);
				msg.show();

			} catch (Exception e) {
				Log.e("ERROR from Google PLaces API", e.getMessage());
				Toast msg = Toast.makeText(getApplicationContext(),
						e.getMessage(), Toast.LENGTH_LONG);
				msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2,
						msg.getYOffset() / 2);
				msg.show();
			}

			return null;
		}

		protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting all products
			pDialog.dismiss();
			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {

					Builder build = new LatLngBounds.Builder();

					for (Place place : places) {
						
						if (place.id != null) {
							
							Log.e("PLACE", place.getReference());
							build.include(new LatLng(place.getGeometry()
									.getLocation().getLat(), place
									.getGeometry().getLocation().getLng()));
							
							Marker m = map.addMarker (new MarkerOptions()
									.position(
											new LatLng(place.getGeometry()
													.getLocation().getLat(),
													place.getGeometry()
															.getLocation()
															.getLng()))
									.title(place.name)
									.snippet(place.getVicinity())
									.icon(BitmapDescriptorFactory
											.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
									.anchor(0.5f, 0.5f));
							markerPlace.put(m.getId(), place.getReference());
						}
					}
					
					LatLngBounds bounds = build.build();

					map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds,
							50));

				}
			});

		}

	}

	@Override
	public void onMyLocationChange(Location location) {
		if (update) {
			update = false;
			this.location = location;
			animateCamera(location);

			new LoadPlaces()
					.execute(new String[] { location.getLatitude() + "",
							location.getLongitude() + "" });

		}
		Log.d("onMyLocationChange", "lng: "+location.getLongitude()+" lat: "+location.getLatitude());
		this.location = location;

	}

	@Override
	 public void onMapLongClick(LatLng point) {
	  Log.d("New marker added@", point.toString());
	  map.addMarker(new MarkerOptions()
	       .position(point)
	       .draggable(true));
	  
	  
	 }

	 @Override
	 public void onMarkerDrag(Marker marker) {
		  Log.d("Marker " + marker.getId() ," Drag@" + marker.getPosition());
	 }

	 @Override
	 public void onMarkerDragEnd(Marker marker) {
		 Log.d("Marker " + marker.getId() , " DragEnd");
	 }

	 @Override
	 public void onMarkerDragStart(Marker marker) {
		 Log.d("Marker " + marker.getId() , " DragStart");
	  
	 }

	 EditText placeName;
	 
	 AutoCompleteTextView placeType;
	@Override
	public boolean onMarkerClick(Marker marker) {
		if( marker.getId().equalsIgnoreCase(markerId)){
					// custom dialog
			
				 dialog = new Dialog(this);
					dialog.setContentView(R.layout.dialog_add_place);
					dialog.setTitle("Add New Place");
		 
					// set the custom dialog components - text, image and button
					placeName = (EditText) dialog.findViewById(R.id.et_place_name);
					 placeType = (AutoCompleteTextView) dialog.findViewById(R.id.et_place_type);
					String[] stringTypes = getResources().getStringArray(R.array.place_types);
					ArrayAdapter<String> types = new ArrayAdapter<String>(getApplicationContext(), R.layout.autocomplete_item, Utility.getPlacesTypes(stringTypes));
					placeType.setAdapter(types);
//					
					ImageView image = (ImageView) dialog.findViewById(R.id.image);
					image.setImageResource(R.drawable.ic_launcher);
		 
					Button btnOk = (Button) dialog.findViewById(R.id.btn_ok);
					
					Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
					// if button is clicked, close the custom dialog
					btnOk.setOnClickListener(this);
					btnCancel.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							dialog.dismiss();
							
						}
					});
		 
					dialog.show();
		}else {
			
			 dialog = new Dialog(this);
			dialog.setContentView(R.layout.dialog_checkin);
			dialog.setTitle("Checkin at "+marker.getTitle() );
 
			newMarker = marker; 
			// set the custom dialog components - text, image and button
			comment = (EditText) dialog.findViewById(R.id.et_comment);
			ImageView image = (ImageView) dialog.findViewById(R.id.image);
			image.setImageResource(R.drawable.ic_launcher);
			
			Button btnOk = (Button) dialog.findViewById(R.id.btn_ok);
			
			Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
			// if button is clicked, close the custom dialog
			btnOk.setOnClickListener(this);
			btnCancel.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					
				}
			});
 
			dialog.show();
		}
		return false;
	}

	@Override
	public void onClick(View v) {
		if (newMarker != null && !markerPlace.containsKey((newMarker.getId()))) {
			PlaceController controller = new PlaceController();
			try {
//				Place place = new Place()
				String reference = controller.addNewPlaceInPlaces(PlaceHelper.newPlaceToJson(String.valueOf(newMarker.getPosition().longitude), String.valueOf(newMarker.getPosition().latitude),
placeName.getText().toString(), placeType.getText().toString()));
				newMarker.setTitle(placeName.getText().toString());
				newMarker.setSnippet(reference);
			newMarker.hideInfoWindow();
			newMarker.showInfoWindow();
				dialog.dismiss();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}else {
			if (comment.getText().length() >0) {
				Log.e("newMarker.getId()", markerPlace.get(newMarker.getId()));
				CheckinController controller = new CheckinController();
				try {
					String userId = MainActivity.instance.loadStringPreferences("USER_ID");
					Checkin checkin = new Checkin(markerPlace.get(newMarker.getId()),userId , comment.getText().toString());
					
					controller.postCheckin(checkin);
					dialog.dismiss();
					
					
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			
		}
		
	}

}
