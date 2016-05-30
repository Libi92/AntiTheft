package com.example.antitheftapp;

import java.util.Calendar;

import receiver.CallReceiver;
import service.ActivateObserver;
import service.Globals;
import service.ServiceLoc;
import service.StatusUpdate;
import service.WebServiceClient;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PrefSetting extends Activity {

	EditText num1, num2, email, useremail, pass;
	SharedPreferences sp;
	Button save;
	private PendingIntent pendingIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pref_setting);

		sp = getSharedPreferences(Globals.PREFERENCES_NAME,
				Context.MODE_PRIVATE);
		Globals.setServer(sp.getString(Globals.SERVER_IP, "10.0.2.2"),"8080");
		String result = WebServiceClient.getNumbers(sp.getString(
				Globals.USER_NAME, ""));

		String n1 = result.split(":")[0];
		String n2 = result.split(":")[1];
		String ema = result.split(":")[2];
		num1 = (EditText) findViewById(R.id.editText2_num1);
		num2 = (EditText) findViewById(R.id.editText1_num2);
		email = (EditText) findViewById(R.id.editText1_ema);
		useremail = (EditText) findViewById(R.id.editText2_userema);
		pass = (EditText) findViewById(R.id.editText3_pass);

		save = (Button) findViewById(R.id.button1);

		num1.setText(n1);
		num2.setText(n2);
		email.setText(ema);
		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String result = WebServiceClient.sendNumber(num1.getText()
						.toString(), num2.getText().toString(), email.getText()
						.toString(), sp.getString(Globals.USER_NAME, ""));

				Editor editor = sp.edit();
				editor.putString(Globals.phone1, num1.getText().toString());
				editor.putString(Globals.phone2, num2.getText().toString());
				editor.putString(Globals.emailid, email.getText().toString());
				editor.putString(Globals.userEmail, useremail.getText()
						.toString());
				editor.putString(Globals.userPass, pass.getText().toString());
				editor.putString(Globals.cmdPrefix, ((EditText)findViewById(R.id.editTextCmdPrefix)).getText().toString());
				
				editor.commit();
				Toast.makeText(PrefSetting.this, "preference Updated", Toast.LENGTH_SHORT).show();
//				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pref_setting, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == R.id.action_start) {
			
			Toast.makeText(PrefSetting.this, "Tracking Started", Toast.LENGTH_SHORT).show();
			Intent myIntent = new Intent(PrefSetting.this, ServiceLoc.class);

			pendingIntent = PendingIntent.getService(PrefSetting.this, 0,
					myIntent, 0);

			final AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

			final Calendar calendar = Calendar.getInstance();

			calendar.setTimeInMillis(System.currentTimeMillis());

			Thread t = new Thread(new Runnable() {

				@Override
				public void run() {
					alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
							calendar.getTimeInMillis(), 10000, pendingIntent);
				}
			});
			t.start();
			
//			startService(new Intent(PrefSetting.this,
//					ActivateObserver.class));
			
								
			
		} else {
			
			Toast.makeText(PrefSetting.this, "Tracking Stoped", Toast.LENGTH_SHORT).show();
			AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

			alarmManager.cancel(pendingIntent);
		}

		return super.onOptionsItemSelected(item);
	}

}
