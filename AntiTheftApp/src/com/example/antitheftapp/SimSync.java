package com.example.antitheftapp;


import service.SimChangeService;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SimSync extends Activity {

	
	Button sync;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sim_sync);
		sync=(Button) findViewById(R.id.button1_sync);
		sync.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent in=new Intent(SimSync.this, SimChangeService.class);
				startService(in);
				Intent in1=new Intent(SimSync.this, PrefSetting.class);
				startActivity(in1);
				finish();
			}
		});
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sim_sync, menu);
		return true;
	}

}


