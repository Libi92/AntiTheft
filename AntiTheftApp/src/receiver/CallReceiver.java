package receiver;

import java.util.Date;

import service.Globals;
import service.WebServiceClient;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.provider.CallLog.Calls;
import android.util.Log;

public class CallReceiver extends PhonecallReceiver {

	Context mContext;
	@Override
	protected void onIncomingCallStarted(Context ctx, String number, Date start) {

		mContext = ctx;
		Log.d("child", "incoming started");

		super.onIncomingCallStarted(ctx, number, start);
	}

	@Override
	protected void onOutgoingCallStarted(Context ctx, String number, Date start) {

		Log.d("child", "outgoing started");

		super.onOutgoingCallStarted(ctx, number, start);
	}

	@Override
	protected void onIncomingCallEnded(Context context, String number,
			Date start, Date end) {

		Log.d("child", "incoming ended");

		Thread.currentThread();

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Cursor c = context.getContentResolver().query(
				android.provider.CallLog.Calls.CONTENT_URI, null, null, null,
				Calls.DATE);
		if (c.moveToLast()) {

			int durationIndex = c
					.getColumnIndex(android.provider.CallLog.Calls.DURATION);

			String duration = c.getString(durationIndex);
			
//			SharedPreferences preferences = context.getSharedPreferences(Globals.PREFERNECE_NAME, context.MODE_PRIVATE);
//			String childid = preferences.getString(Globals.CHILD_ID, "1");
//
//			webServiceClint.updatecall(childid, "Incoming", number, duration);
			SharedPreferences preferences = mContext.getSharedPreferences(Globals.PREFERENCES_NAME, Context.MODE_PRIVATE);
			String loginid = preferences.getString(Globals.LOGIN_ID, "0");
			WebServiceClient.updateCALL(number, "Incoming", duration, loginid);
			Log.d("Phone State", "Incoming - " + number + " - " + duration);
		}

		super.onIncomingCallEnded(context, number, start, end);
	}

	@Override
	protected void onOutgoingCallEnded(Context context, String number,
			Date start, Date end) {

		Log.d("child", "outgoing ended");

		Thread.currentThread();

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Cursor c = context.getContentResolver().query(
				android.provider.CallLog.Calls.CONTENT_URI, null, null, null,
				Calls.DATE);

		if (c.moveToLast()) {

			int durationIndex = c
					.getColumnIndex(android.provider.CallLog.Calls.DURATION);

			String duration = c.getString(durationIndex);
			
			
			
			if (number.startsWith("+")) {

			}
			else if (number.startsWith("0")) {

				number.subSequence(1, number.length());

				number = "+91" + number;

			} else {
				number = "+91" + number;
			}
			
//			SharedPreferences preferences = context.getSharedPreferences(Globals.PREFERNECE_NAME, context.MODE_PRIVATE);
//			String childid = preferences.getString(Globals.CHILD_ID, "1");
//
//			webServiceClint.updatecall(childid, "Outgoing", number, duration);
			SharedPreferences preferences = mContext.getSharedPreferences(Globals.PREFERENCES_NAME, Context.MODE_PRIVATE);
			String loginid = preferences.getString(Globals.LOGIN_ID, "0");
			WebServiceClient.updateCALL(number, "Outgoing", duration, loginid);
			Log.d("Phone State", "Outgoing - " + number + " - " + duration);
		}

		super.onOutgoingCallEnded(context, number, start, end);
	}

	@Override
	protected void onMissedCall(Context context, String number, Date start) {

		if (number.startsWith("+")) {

		}
		else if (number.startsWith("0")) {

			number.subSequence(1, number.length());

			number = "+91" + number;

		} else {
			number = "+91" + number;
		}

		Log.d("child", "missed call");

		Thread.currentThread();

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Cursor c = context.getContentResolver().query(
				android.provider.CallLog.Calls.CONTENT_URI, null, null, null,
				Calls.DATE);

		if (c.moveToLast()) {

			int durationIndex = c
					.getColumnIndex(android.provider.CallLog.Calls.DURATION);

			String duration = c.getString(durationIndex);
			
//			SharedPreferences preferences = context.getSharedPreferences(Globals.PREFERNECE_NAME, context.MODE_PRIVATE);
//			String childid = preferences.getString(Globals.CHILD_ID, "1");
//
//			webServiceClint.updatecall(childid, "Missed", number, duration);
			SharedPreferences preferences = mContext.getSharedPreferences(Globals.PREFERENCES_NAME, Context.MODE_PRIVATE);
			String loginid = preferences.getString(Globals.LOGIN_ID, "0");
			WebServiceClient.updateCALL(number, "Missed", duration, loginid);
			Log.d("Phone State", "Missed - " + number + " - " + duration);
		}

		super.onMissedCall(context, number, start);
	}

}