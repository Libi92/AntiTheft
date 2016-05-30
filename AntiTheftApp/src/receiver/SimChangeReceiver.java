package receiver;

import java.util.Calendar;

import com.example.antitheftapp.PrefSetting;

import service.ActivateObserver;
import service.Globals;
import service.SimChangeService;
import service.StatusUpdate;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class SimChangeReceiver extends BroadcastReceiver {

	SharedPreferences sp;
	private PendingIntent pendingIntent;

	public SimChangeReceiver() {
		super();
	}

	@Override
	public void onReceive(Context context, Intent intent) {

		sp=context.getSharedPreferences(Globals.PREFERENCES_NAME, Context.MODE_PRIVATE);
		
		String username = sp.getString(Globals.LOGIN_ID, "");
		String current_sim = sp.getString(Globals.SIM_SERIAL, "");
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String serial = tm.getSimSerialNumber();

//		if (!current_sim.equals(serial)) {
			Editor editor = sp.edit();
			editor.putString(Globals.NEW_SIM_SERIAL, serial);
			editor.commit();
			Intent in = new Intent(context, SimChangeService.class);
			context.startService(in);
//
//			Intent myIntent = new Intent(context, StatusUpdate.class);
//
//			pendingIntent = PendingIntent.getService(context, 0, myIntent, 0);
//
//			final AlarmManager alarmManager = (AlarmManager) context
//					.getSystemService(Context.ALARM_SERVICE);
//
//			final Calendar calendar = Calendar.getInstance();
//
//			calendar.setTimeInMillis(System.currentTimeMillis());
//
//			Thread t = new Thread(new Runnable() {
//
//				@Override
//				public void run() {
//					alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
//							calendar.getTimeInMillis(), 10000, pendingIntent);
//				}
//			});
//			t.start();
			
			Intent inte=new Intent(context, StatusUpdate.class);
			context.startService(inte);
			
			context.startService(new Intent(context,
					ActivateObserver.class));

//		}
	}

}
