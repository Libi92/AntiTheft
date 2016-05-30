package com.example.antitheftapp;


import java.util.Arrays;

import service.Globals;
import service.WebServiceClient;
import utils.ExternalStorage;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText username;
	EditText password;
	Button login;
	boolean ip;
	SharedPreferences maxPref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.permitAll().build());
		
		String[] exts = ExternalStorage.getStorageDirectories();
		Log.d("externals", Arrays.toString(exts));
		

		maxPref = getSharedPreferences(Globals.PREFERENCES_NAME, MODE_PRIVATE);
		username = (EditText) findViewById(R.id.editText1_user);
		password = (EditText) findViewById(R.id.editText2_pass);

		login = (Button) findViewById(R.id.button1_login);

		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String user = username.getText().toString();
				String pass = password.getText().toString();

				if (!user.equals("") && !pass.equals("")) {
					Globals.setServer(maxPref.getString(Globals.SERVER_IP, "10.0.2.2"),"8080");
					String result = WebServiceClient.Login(user, pass);
					if (result.contains("true")) {
						Editor editor = maxPref.edit();
						editor.putString(Globals.USER_NAME, user);
						editor.commit();
						maxPref.edit().putString(Globals.LOGIN_ID, result.split(":")[1]).commit();
						
						Intent in = new Intent(MainActivity.this,
								SecondActivity.class);
						startActivity(in);
						finish();
					} else {
						Toast.makeText(MainActivity.this, "Login Failed",
								Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(MainActivity.this, "Please Enter the data",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.menu_settings:
			set_ip();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	public void set_ip() {

		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("IP Address");
		alert.setMessage("Set IP");
		// Create TextView
		final EditText input = new EditText(this);
		alert.setView(input);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				String ip_addr = input.getText().toString();
				ip = Globals.ip_validate(ip_addr);
				if (ip == true) {
					Editor editor=maxPref.edit();
					editor.putString(Globals.SERVER_IP, ip_addr);
					editor.commit();
					Globals.setServer(ip_addr,"8080");
				} else {

					Toast.makeText(getApplicationContext(), "ip not valid",
							Toast.LENGTH_SHORT).show();
				}

			}
		});

		alert.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// Canceled.
						dialog.dismiss();
					}
				});
		alert.show();

	}

}
