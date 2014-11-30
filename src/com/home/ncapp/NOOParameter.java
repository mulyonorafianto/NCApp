package com.home.ncapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class NOOParameter extends Activity implements AdapterView.OnItemSelectedListener {

	Spinner spinsitex = null;
	
	String[] sitex = {"BDG1 Bandung","JKT1 Jakarta","SBY1 Surabaya","YGY1 Yogjakarta","SLO1 Solo","DPS1 Denpasar"};
	 		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.parameter);
		
		spinsitex = (Spinner) findViewById(R.id.paraSpin1);
		spinsitex.setOnItemSelectedListener((OnItemSelectedListener) NOOParameter.this);
		
		ArrayAdapter<String> as_sitex = new ArrayAdapter<String>(NOOParameter.this,android.R.layout.simple_spinner_item, sitex);
		as_sitex.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinsitex.setAdapter(as_sitex);
		
		Button btnBack = (Button) findViewById(R.id.paraBtnBack);
		
		btnBack.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				finish();				
			}
		});
		
	}
	
	@SuppressLint("DefaultLocale")
	public void onItemSelected(AdapterView<?> as_sitex, View v, int position,   long id) {
		String piprnc = sitex[position].toString().toUpperCase();
		Toast.makeText(this, "Principal <"+piprnc.substring(0, 3)+"> not List on Server !",Toast.LENGTH_LONG).show();
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}

}
