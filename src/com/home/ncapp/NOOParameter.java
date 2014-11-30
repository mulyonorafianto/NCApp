package com.home.ncapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NOOParameter extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.parameter);
		
		Button btnBack = (Button) findViewById(R.id.paraBtnBack);
		
		btnBack.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				finish();				
			}
		});
		
	}

}
