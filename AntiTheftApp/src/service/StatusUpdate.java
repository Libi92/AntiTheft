package service;

import java.text.BreakIterator;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

public class StatusUpdate extends Service{

	SharedPreferences sp;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		sp=getSharedPreferences(Globals.PREFERENCES_NAME, Context.MODE_PRIVATE);
		Globals.setServer(sp.getString(Globals.SERVER_IP, "10.0.2.2"),"8080");
		final Timer t=new Timer();
		t.schedule(new TimerTask() {
			
			@Override
			public void run() {
				
				String result=WebServiceClient.checkStatus(sp.getString(Globals.NEW_SIM_SERIAL, ""),sp.getString(Globals.USER_NAME, ""));
//				Toast.makeText(getApplicationContext(), "Status Update : " + result, Toast.LENGTH_SHORT).show();
				
				Log.d( "Status Update : " , result);
				
				if(result.equals("2"))
				{
					
//							String mob2=sp.getString(Globals.phone2, "");
							
//							SmsManager sms=SmsManager.getDefault();
//							sms.sendTextMessage(sp.getString(Globals.phone1, ""), "", "New Sim Detected, Serial is :"+sp.getString(Globals.NEW_SIM_SERIAL, ""), null, null);
//							
						
					Intent intent=new Intent(StatusUpdate.this, CameraService.class);
					startService(intent);
					
					t.cancel();
				}
				else if(result.equals("1"))
				{
					t.cancel();
				}
			}
		}, 1000,10000);
		
		return super.onStartCommand(intent, flags, startId);
	}

}
