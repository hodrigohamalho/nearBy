package com.huge.nearby.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import com.huge.nearby.R;
import com.huge.nearby.tasks.RequestAcessTokenTask;
import com.huge.nearby.utils.FoursquareUtil;

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
				loginToForsquare();
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();

		// extract the OAUTH access token if it exists
		Uri uri = this.getIntent().getData();
		if(uri != null) {
			String access_token = uri.getQueryParameter("code");

			if(access_token != null){
				new RequestAcessTokenTask(this).execute(access_token);
			}
		}
	}

	private void loginToForsquare() {
		// Call the webbrowser with the Foursquare OAuth login URL
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(FoursquareUtil.FOURSQUARE_LOGIN_URL));
		startActivity(intent);
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
