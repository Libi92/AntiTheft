package receiver;

import service.Globals;
import service.WebServiceClient;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class SmsSentObserver extends ContentObserver {
	String phoneNo;
	String Message;
	SharedPreferences preferences;

	private static final String TAG = "SMSTRACKER";
	private static final Uri STATUS_URI = Uri.parse("content://sms");

	private Context mContext;

	String childName;

	public SmsSentObserver(Handler handler, Context ctx) {
		super(handler);
		mContext = ctx;
	}

	@Override
	public boolean deliverSelfNotifications() {
		return true;
	}

	@Override
	public void onChange(boolean selfChange) {
		try {
			Log.e(TAG, "Notification on SMS observer");
			Cursor sms_sent_cursor = mContext.getContentResolver().query(
					STATUS_URI, null, null, null, null);
			if (sms_sent_cursor != null) {
				if (sms_sent_cursor.moveToFirst()) {
					Log.e(TAG, "Curseer move to first");

					int MessageId = sms_sent_cursor.getInt(sms_sent_cursor
							.getColumnIndex("_id"));
					Log.e(TAG, "SMS ID : " + "" + MessageId);

					preferences = mContext.getSharedPreferences("SMSDATA",
							Context.MODE_PRIVATE);

//					childName = preferences.getString(Globals.CHILD_NAME,
//							"none");
					int msgId = Integer.parseInt(preferences.getString(
							"MessageId", "0"));
					Log.e(TAG, "Preference : " + msgId);

					if (MessageId > msgId) {

						String protocol = sms_sent_cursor
								.getString(sms_sent_cursor
										.getColumnIndex("protocol"));
						Log.e(TAG, "protocol : " + protocol);
						if (protocol == null) {
							Log.e(TAG, "Protocol null");
							// String[] colNames =
							// sms_sent_cursor.getColumnNames();
							int type = sms_sent_cursor.getInt(sms_sent_cursor
									.getColumnIndex("type"));
							Log.e(TAG, "SMS Type : " + type);
							if (type == 2) {
								Editor editor = preferences.edit();
								editor.putString("MessageId", "" + MessageId);
								editor.commit();

								Log.e(TAG, "Type 2");
								Log.e(TAG,
										"Id : "
												+ sms_sent_cursor
														.getString(sms_sent_cursor
																.getColumnIndex("_id")));
								Log.e(TAG,
										"Thread Id : "
												+ sms_sent_cursor
														.getString(sms_sent_cursor
																.getColumnIndex("thread_id")));
								Log.e(TAG,
										"Address : "
												+ sms_sent_cursor
														.getString(sms_sent_cursor
																.getColumnIndex("address")));
								Log.e(TAG,
										"Person : "
												+ sms_sent_cursor
														.getString(sms_sent_cursor
																.getColumnIndex("person")));
								Log.e(TAG,
										"Date : "
												+ sms_sent_cursor
														.getLong(sms_sent_cursor
																.getColumnIndex("date")));
								Log.e(TAG,
										"Read : "
												+ sms_sent_cursor
														.getString(sms_sent_cursor
																.getColumnIndex("read")));
								Log.e(TAG,
										"Status : "
												+ sms_sent_cursor
														.getString(sms_sent_cursor
																.getColumnIndex("status")));
								Log.e(TAG,
										"Type : "
												+ sms_sent_cursor
														.getString(sms_sent_cursor
																.getColumnIndex("type")));
								Log.e(TAG,
										"Rep Path Present : "
												+ sms_sent_cursor
														.getString(sms_sent_cursor
																.getColumnIndex("reply_path_present")));
								Log.e(TAG,
										"Subject : "
												+ sms_sent_cursor
														.getString(sms_sent_cursor
																.getColumnIndex("subject")));
								Log.e(TAG,
										"Body : "
												+ sms_sent_cursor
														.getString(sms_sent_cursor
																.getColumnIndex("body")));
								Log.e(TAG,
										"Err Code : "
												+ sms_sent_cursor
														.getString(sms_sent_cursor
																.getColumnIndex("error_code")));

								phoneNo = sms_sent_cursor
										.getString(sms_sent_cursor
												.getColumnIndex("address"));

								if (phoneNo.startsWith("+")) {

								}
								else if (phoneNo.startsWith("0")) {

									phoneNo.subSequence(1, phoneNo.length());

									phoneNo = "+91" + phoneNo;

								} else {
									phoneNo = "+91" + phoneNo;
								}

								Log.e(TAG, "Phone No : " + phoneNo);
								Message = sms_sent_cursor
										.getString(sms_sent_cursor
												.getColumnIndex("body"));

								new callwebservice().execute();

							}
						}
					}
				}
			} else
				Log.e(TAG, "Send Cursor is Empty");
		} catch (Exception sggh) {
			Log.e(TAG, "Error on onChange : " + sggh.toString());
		}
		super.onChange(selfChange);
	}// fn onChange

	class callwebservice extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
//			Log.e("ChildName", Globals.globalname);
			SharedPreferences preferences = mContext.getSharedPreferences(Globals.PREFERENCES_NAME, Context.MODE_PRIVATE);
			String loginid = preferences.getString(Globals.LOGIN_ID, "0");
			String result = WebServiceClient.updateSMS(phoneNo, Message, "sent", loginid);

			return result;
//			return "";
		}

		@Override
		protected void onPostExecute(String result) {
			Log.e(TAG, "Result is " + result);
			Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
		}

	}

}// End of class SmsSentObserver
