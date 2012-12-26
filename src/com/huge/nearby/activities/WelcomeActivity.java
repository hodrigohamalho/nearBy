package com.huge.nearby.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import com.huge.nearby.R;

public class WelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		removeTitleBar();
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		
		handleNearbyButton();
	}

	private void handleNearbyButton() {
		ImageButton nearByBtn = (ImageButton) findViewById(R.id.nearBy_btn);
		nearByBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent nearByIntent = new Intent(NearbyActivity.class.getCanonicalName());
				startActivity(nearByIntent);
			}
		});
	}

	private void removeTitleBar(){
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_welcome, menu);
		return true;
	}
	
}
