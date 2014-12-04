package com.home.ncapp;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
    
	TextView mnTvVer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);	
		
		mnTvVer = (TextView) findViewById(R.id.splashTvJudul);
		mnTvVer.setTextColor(Color.rgb(200, 0, 0));
		
		Button btnCustOview = (Button) findViewById(R.id.btnList);
		Button btnParameter = (Button) findViewById(R.id.btnPara);
		Button btnKeluar = (Button) findViewById(R.id.btnKeluar);
		
		btnCustOview.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent openActivityLisOview = new Intent("com.home.ncapp.NOOCUSTOOVIEW");
				startActivity(openActivityLisOview);
			}
		});
		
		btnParameter.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent openActivityParameter = new Intent("com.home.ncapp.NOOPARAMATER");
				startActivity(openActivityParameter);				
			}
		});
		
		btnKeluar.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
		        finish();  		
			}
		});
		 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
