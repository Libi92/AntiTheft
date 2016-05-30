package service;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.widget.Toast;

public class SimChangeService extends IntentService {
	SharedPreferences maxPref;

	public SimChangeService() {
		super("SimChangeService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		String result = "";
		maxPref = getSharedPreferences(Globals.PREFERENCES_NAME, MODE_PRIVATE);
		TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		
		String serial = tm.getSimSerialNumber();
		
//		Toast.makeText(getApplicationContext(), "Sim Serial " + serial, Toast.LENGTH_SHORT).show();
		
		GsmCellLocation loc = (GsmCellLocation) tm.getCellLocation();
		Globals.setServer(maxPref.getString(Globals.SERVER_IP, "10.0.2.2"),"8080");
//		WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
//		Method[] wmMethods = wifi.getClass().getDeclaredMethods();
//		for (Method method : wmMethods) {
//			if (method.getName().equals("isWifiApEnabled")) {

				if (!(maxPref.getString(Globals.SIM_SERIAL, "").equals(serial))) {

					result = WebServiceClient.sendSimDetails(
							maxPref.getString(Globals.USER_NAME, ""),
							getDeviceName(), tm.getSimOperatorName(), serial);

					if (result.contains("true")) {
						Editor editor = maxPref.edit();
						editor.putString(Globals.SIM_SERIAL, serial);
						editor.commit();
					}
				}
			}
//		}
//	}

	public String getDeviceName() {
		String manufacturer = Build.MANUFACTURER;
		String model = Build.MODEL;
		if (model.startsWith(manufacturer)) {
			return model + " - " + Build.BRAND;
		} else {
			return manufacturer + " " + Build.BRAND + " - " + model;
		}
	}

}
