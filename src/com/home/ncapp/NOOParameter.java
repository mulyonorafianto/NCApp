package com.home.ncapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class NOOParameter extends Activity implements AdapterView.OnItemSelectedListener {

	Spinner spinsitex = null;
	Spinner spinrax = null;
	
	EditText parUsername; 
	EditText parPasswd;
	EditText parPidkey, parSite, parRom ;
	
	String[] sitex = {"BDG1 Bandung","JKT1 Jakarta","SBY1 Surabaya","YGY1 Yogjakarta","SLO1 Solo","DPS1 Denpasar"};
	String[] romax = {"RA1 Region Indonesia Barat","RA2 Region Indonesia Tengah","RA3 Region Indonesia Timur"};
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.parameter);
		
		parUsername = (EditText) findViewById(R.id.paraETusername);
		parPasswd = (EditText) findViewById(R.id.paraETpassword);
		parPidkey = (EditText) findViewById(R.id.paraETpidkey);
		parSite = (EditText) findViewById(R.id.paraETsite);
		parRom = (EditText) findViewById(R.id.paraETrom);
		
		spinsitex = (Spinner) findViewById(R.id.paraSpin1);
		spinsitex.setOnItemSelectedListener((OnItemSelectedListener) NOOParameter.this);
		
		spinrax = (Spinner) findViewById(R.id.paraSpin2);
		spinrax.setOnItemSelectedListener((OnItemSelectedListener) NOOParameter.this);
		
		ArrayAdapter<String> as_sitex = new ArrayAdapter<String>(NOOParameter.this,android.R.layout.simple_spinner_item, sitex);
		as_sitex.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinsitex.setAdapter(as_sitex);
		
		ArrayAdapter<String> as_ramx = new ArrayAdapter<String>(NOOParameter.this,android.R.layout.simple_spinner_item, romax);
		as_ramx.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinrax.setAdapter(as_ramx);
		
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
		String sitepos = sitex[position].toString().toUpperCase();
		Toast.makeText(this, "Site Optional <"+sitepos.substring(0, 4)+"> is Choice ! ",Toast.LENGTH_LONG).show();
	}

	public void onNothingSelected(AdapterView<?> as_sitex) {
		// TODO Auto-generated method stub		
	}
	

	@SuppressLint("DefaultLocale")
	public void onItemSelected1(AdapterView<?> as_ramx, View v, int position,   long id) {
		String rampos = romax[position].toString().toUpperCase(); 
		Toast.makeText(this, "Area Oprasional <"+rampos.substring(0, 4)+"> is Choice ! ",Toast.LENGTH_LONG).show();
	}
	
	public void onNothingSelected1(AdapterView<?> as_ramx) {
		// TODO Auto-generated method stub		
	}

}
