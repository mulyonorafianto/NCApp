package com.home.ncapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity{
    
	//MediaPlayer Oursong;
    
	@Override
	protected void onCreate(Bundle dmskpstsplash_InstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(dmskpstsplash_InstanceState);
		setContentView(R.layout.splash);
		//MediaPlayer Oursong = MediaPlayer.create(splash.this, R.raw.mbswatersplash);
		//Oursong.start();
		Thread timer = new Thread() {
		  public void run() {  
			try {	
				sleep(10000); 
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				Intent openDMSMainActivity = new Intent("com.home.ncapp.MAINACTIVITY");
				startActivity(openDMSMainActivity);
			}
		  }
		};
		timer.start();
	}

	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//Oursong.release();
		finish();
	}	
}
