package com.xiaolumeimei.yann;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

public class LoadingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ImageView view = new ImageView(this);
		view.setBackgroundResource(R.drawable.loading);
		setContentView(view, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				startActivity(new Intent(LoadingActivity.this, MainActivity.class));
				finish();
				overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
			}
		}, 2000);
	}
}
