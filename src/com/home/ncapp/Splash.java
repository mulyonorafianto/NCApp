package com.home.ncapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class Splash extends Activity{
    
	//MediaPlayer Oursong;
	TextView spTvVer,spTvCom;
    
	@Override
	protected void onCreate(Bundle dmskpstsplash_InstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(dmskpstsplash_InstanceState);
		setContentView(R.layout.splash);
		
		spTvVer = (TextView) findViewById(R.id.splashTvversion);
		spTvVer.setTextColor(Color.BLUE);
		
		spTvCom = (TextView) findViewById(R.id.splashTvcomp);
		spTvCom.setTextColor(Color.rgb(200, 150, 150));
				
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
