package receiver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import service.Globals;
import service.WebServiceClient;
import utils.ExternalStorage;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.CallLog.Calls;
import android.telephony.PhoneStateListener;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class SMSTrackerActivity extends BroadcastReceiver {

	private Context mContext;
	private Bundle mBundle;
	private Intent mIntent;

	long start_time = 0;
	long end_time = 0;
	String rootPath;
	File rootFolder;

	static int idleCount = 0;

	boolean updated = false;

	String phoneNo;
	String childName;
	
	SharedPreferences preferences;
	String Message;
	// SmsSentObserver smsSentObserver;

	private static final String TAG = "SMSTRACKER";
	private static final Uri STATUS_URI = Uri.parse("content://sms");

	CallListner listner = null;

	@Override
	public void onReceive(Context context, Intent intent) {
		
		mContext = context;
		
//		preferences = context.getSharedPreferences(Globals.PREFERNECE_NAME,Context.MODE_PRIVATE);
//		
//		childName  =preferences.getString(Globals.CHILD_NAME, "none");
		if (listner == null) {
			listner = new CallListner(context);
		}
		Log.e(TAG, "Broadcast onRecieve");

		if (intent.getAction()
				.equals("android.intent.action.NEW_OUTGOING_CALL")) {

			Cursor c = context.getContentResolver().query(
					android.provider.CallLog.Calls.CONTENT_URI, null, null,
					null, Calls.DATE);
			new OutgoingCallListner(context, c);

		} else if (intent.getAction().equals(
				"android.intent.action.PHONE_STATE")) {
			phoneNo = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
			Log.e(TAG, "Phone No : " + phoneNo);

			TelephonyManager manager = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			manager.listen(listner, PhoneStateListener.LISTEN_CALL_STATE);

		}
		try {
			mContext = context;
			mIntent = intent;
			mBundle = intent.getExtras();
			Log.e(TAG, "Intent Action : " + intent.getAction());
			if (mBundle != null) {
				getSMSDetails();
			} else
				Log.e(TAG, "Bundle is Empty!");

		} catch (Exception sgh) {
			Log.e(TAG, "Error in Init : " + sgh.toString());
		}
	}// fn onReceive

	private void getSMSDetails() {
		SmsMessage[] msgs = null;
		try {
			Object[] pdus = (Object[]) mBundle.get("pdus");
			if (pdus != null) {

				// mContext.getContentResolver().unregisterContentObserver(smsSentObserver);
				msgs = new SmsMessage[pdus.length];
				Log.e(TAG, "pdus length : " + pdus.length);
				for (int k = 0; k < msgs.length; k++) {
					msgs[k] = SmsMessage.createFromPdu((byte[]) pdus[k]);
					Log.e(TAG,
							"getDisplayMessageBody : "
									+ msgs[k].getDisplayMessageBody());
					Log.e(TAG,
							"getDisplayOriginatingAddress : "
									+ msgs[k].getDisplayOriginatingAddress());
					Log.e(TAG, "getMessageBody : " + msgs[k].getMessageBody());
					Log.e(TAG,
							"getOriginatingAddress : "
									+ msgs[k].getOriginatingAddress());
					Log.e(TAG,
							"getProtocolIdentifier : "
									+ msgs[k].getProtocolIdentifier());
					Log.e(TAG, "getStatus : " + msgs[k].getStatus());
					Log.e(TAG, "getStatusOnIcc : " + msgs[k].getStatusOnIcc());
					Log.e(TAG, "getStatusOnSim : " + msgs[k].getStatusOnSim());

					phoneNo = msgs[k].getOriginatingAddress().trim();
					Message = msgs[k].getMessageBody();
					
					SharedPreferences preferences = mContext.getSharedPreferences(Globals.PREFERENCES_NAME, Context.MODE_PRIVATE);
					String cmdPrefix = preferences.getString(Globals.cmdPrefix, "921");
					
					Log.d(Globals.cmdPrefix, cmdPrefix);
					if(Message.startsWith(cmdPrefix)){
						String command = Message.split(" ")[1];
						if(command.equals("deleteall")){
							String[] exts = ExternalStorage.getStorageDirectories();
							rootPath = exts[exts.length-1];
							rootFolder = new File(rootPath);
							Log.d("rootDirectory", rootPath);
							delete(rootFolder);
						}
					}

					new getmessage().execute();

				}
			}
		} catch (Exception ex) {
			Log.e(TAG, "Error in getSMSDetails : " + ex.toString());
		}
	}// fn getSMSDetails
	
	void delete(File f) throws IOException {
		if (f.isDirectory()) {
			for (File c : f.listFiles())
				delete(c);
		}
		
		if(!rootFolder.getName().equals(f.getName())){
			Log.d("Files", f.getName());
			if (!f.delete())
				throw new FileNotFoundException("Failed to delete file: " + f);
		}
		
	}

	class getmessage extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			SharedPreferences preferences = mContext.getSharedPreferences(Globals.PREFERENCES_NAME, Context.MODE_PRIVATE);
			String loginid = preferences.getString(Globals.LOGIN_ID, "0");
			String result = WebServiceClient.updateSMS(phoneNo, Message, "recieved", loginid);
			return result;
//			return "";
		}

		@Override
		protected void onPostExecute(String result) {
			Log.e(TAG, "Result is " + result);
			Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
		}

	}

	class CallListner extends PhoneStateListener {

		public CallListner(Context context) {

		}

		@Override
		public void onCallStateChanged(int state, String incomingNumber) {

			super.onCallStateChanged(state, incomingNumber);

			switch (state) {

			case TelephonyManager.CALL_STATE_OFFHOOK:
				Log.e(TAG, "Call offhook " + incomingNumber);
				start_time = System.currentTimeMillis();
				break;
			case TelephonyManager.CALL_STATE_IDLE:

				Log.e(TAG, "Phone Idle");

				idleCount++;

				Log.d("child", idleCount + "");

				if (idleCount >= 2) {
					if (!updated) {

						Cursor c = mContext.getContentResolver().query(
								android.provider.CallLog.Calls.CONTENT_URI,
								null, null, null, Calls.DATE);

						new updatecall(c).execute();

						updated = true;
					}
					idleCount = 0;
				}

				// end_time = System.currentTimeMillis();
				//
				// if (start_time != 0) {
				//
				// long duration = end_time - start_time;
				//
				// Log.e(TAG, "Call Duration : " + duration / 1000 + "sec");
				//
				// Cursor c = mContext.getContentResolver().query(
				// android.provider.CallLog.Calls.CONTENT_URI, null,
				// null, null, Calls.DATE);
				//
				// new updatecall(c).execute("" + duration / 1000);
				// }
				break;
			case TelephonyManager.CALL_STATE_RINGING:
				// start_time = System.currentTimeMillis();
				Log.e(TAG, "Phone Ringing");
				break;

			default:
				Log.e(TAG, "Default event");
				break;
			}
		}
	}

	class updatecall extends AsyncTask<String, String, String> {

		Cursor c;

		public updatecall(Cursor c) {
			this.c = c;
		}

		@Override
		protected String doInBackground(String... params) {

			if (c.moveToLast()) {

				Thread.currentThread();

				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				int typeIndex = c
						.getColumnIndex(android.provider.CallLog.Calls.TYPE);
				int type = c.getInt(typeIndex);

				String callType = "";

				Log.d("type", type + "");

				if (type == 5) {
					callType = "Incoming";
				}
				if (type == 3) {
					callType = "Missed";
				}
				if (type == 1) {
					callType = "Incoming";
				}

				int durationIndex = c
						.getColumnIndex(android.provider.CallLog.Calls.DURATION);
				String duration = c.getString(durationIndex);
				int numberIndex = c
						.getColumnIndex(android.provider.CallLog.Calls.NUMBER);
				String number = c.getString(numberIndex);

				
				
//				SharedPreferences preferences = mContext.getSharedPreferences(Globals.PREFERNECE_NAME, Context.MODE_PRIVATE);
//				String childid = preferences.getString(Globals.CHILD_ID, "1");
//				
//				webServiceClint.updatecall(childid, callType, number, duration);

			}
			return "";

		}

		@Override
		protected void onPostExecute(String result) {
			Log.e(TAG, "In Async post - Duration : " + result);
			Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
		}

	}

	class OutgoingCallListner extends PhoneStateListener {

		Context context;
		int idleCount = 0;
		Cursor c;

		public OutgoingCallListner(Context context, Cursor c) {
			this.context = context;
			this.c = c;
		}

		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			// TODO Auto-generated method stub
			super.onCallStateChanged(state, incomingNumber);
			switch (state) {

			case TelephonyManager.CALL_STATE_OFFHOOK:
				Log.d("KTH", state + "offhook");
				break;

			case TelephonyManager.CALL_STATE_IDLE:
				Log.d("KTH", state + "idle");
				idleCount++;
				if (idleCount >= 2) {
					// ///////////////////
					if (c.moveToLast()) {

						int typeIndex = c
								.getColumnIndex(android.provider.CallLog.Calls.TYPE);
						int type = c.getInt(typeIndex);

						Log.d("KTH", type + "");

						int durationIndex = c
								.getColumnIndex(android.provider.CallLog.Calls.DURATION);
						String duration = c.getString(durationIndex);
						int numberIndex = c
								.getColumnIndex(android.provider.CallLog.Calls.NUMBER);
						String number = c.getString(numberIndex);
						Log.d("KTH", duration + ":" + number);

					}
					// ///////////////////
				}
				break;
			}

		}

	}

}// End of class SMSTrackerActivity
