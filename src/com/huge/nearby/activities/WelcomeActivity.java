package com.huge.nearby.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;

import com.huge.nearby.R;

public class WelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		removeTitleBar();
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_welcome, menu);
		return true;
	}
	
	private void removeTitleBar(){
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	}

}
