package com.example.antitheftapp;

import service.StatusUpdate;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TestActivity extends Activity {

	Button b;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);

	b=(Button) findViewById(R.id.button1test);
	b.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
		
			Intent inte=new Intent(TestActivity.this, StatusUpdate.class);
			startService(inte);
			
		}
	});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_test, menu);
		return true;
	}

}
