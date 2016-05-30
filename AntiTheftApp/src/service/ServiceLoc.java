package service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class ServiceLoc extends Service implements LocationListener {

	Location locaton;

	int locationNo = 0;;
	int locationCount = 0;
	String tits, snips;
	LatLng position;
	String time1;
	String lat1 = "", lng1 = "";

	MarkerOptions markerOptions;

	SharedPreferences sp;
	int noti_count = 0;
	StringBuilder strAddress;

	public void getMyLocationAddress(double lat, double lng) {

		Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);

		// Place your latitude and longitude
		List<Address> addresses;
		try {
			addresses = geocoder.getFromLocation(lat, lng, 1);
		

		if (addresses != null) {

			Address fetchedAddress = addresses.get(0);
			strAddress = new StringBuilder();

			for (int i = 0; i < fetchedAddress.getMaxAddressLineIndex(); i++) {
				strAddress.append(fetchedAddress.getAddressLine(i));
			}

		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void onLocationChanged(Location location) {

		Log.d("inService", "Catch");

		sp = getSharedPreferences(Globals.PREFERENCES_NAME,
				Context.MODE_PRIVATE);

		// Getting latitude of the current location
		double latitude = location.getLatitude();

		// Getting longitude of the current location
		double longitude = location.getLongitude();

		String latitude1 = Double.toString(latitude);
		String longitude1 = Double.toString(longitude);
		lat1 = latitude1;
		lng1 = longitude1;
		LatLng latLng = new LatLng(latitude, longitude);

		try {

			getMyLocationAddress(latitude, longitude);
			Globals.setServer(sp.getString(Globals.SERVER_IP, "10.0.2.2"),"8080");
			String result=WebServiceClient.updateLocation(sp.getString(Globals.USER_NAME,""),latitude1,longitude1);
			
		} catch (Exception e) {
//			Toast.makeText(getApplicationContext(), "Last Location",
//					Toast.LENGTH_LONG).show();
			Log.v("Inside Location", e.toString());
		}

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		Toast.makeText(getApplicationContext(), "Loc Service Started", Toast.LENGTH_SHORT).show();
		
		Log.d("Inside onStart", "onstart");

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.permitAll().build());

		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

		// Creating a criteria object to retrieve provider
		Criteria criteria = new Criteria();

		// Getting the name of the best provider
		String provider = locationManager.getBestProvider(criteria, true);

		// Getting Current Location
		Location location = locationManager
				.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

		if (location != null) {

			onLocationChanged(location);
		}
		//
		 locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 100, 0, this);
		//
		//
		// onLocationChanged(this.locaton);

		return super.onStartCommand(intent, flags, startId);
	}
}
