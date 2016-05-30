package com.example.antitheftapp;

import service.Globals;
import service.SimChangeService;
import android.os.Bundle;
import android.provider.Settings.Global;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.widget.Button;

public class SecondActivity extends Activity {

	SharedPreferences sp;
	Button sync;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		sp=getSharedPreferences(Globals.PREFERENCES_NAME,Context.MODE_PRIVATE);
		
		if(sp.contains(Globals.SIM_SERIAL))
		{
			Intent in=new Intent(SecondActivity.this, PrefSetting.class);
			startActivity(in);
			finish();
		}
		else
		{
			Intent in=new Intent(SecondActivity.this, SimSync.class);
			startActivity(in);
			finish();
		}
	}
	

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_second, menu);
		return true;
	}

	
	
	
}
