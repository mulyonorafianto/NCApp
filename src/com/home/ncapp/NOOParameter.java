package com.home.ncapp;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class NOOParameter extends Activity implements AdapterView.OnItemSelectedListener {

	Spinner spinsitex = null;
	Spinner spinrax = null;
	
	EditText parUsername; 
	EditText parPasswd;
	EditText parPidkey, parSite, parRom ;
	
	CheckBox synPil;
	
	public String datestring; 
	
	String[] sitex = {"BDG1 Bandung","JKT1 Jakarta","SBY1 Surabaya","YGY1 Yogjakarta","SLO1 Solo","DPS1 Denpasar"};
	String[] romax = {"RA1 Region Indonesia Barat","RA2 Region Indonesia Tengah","RA3 Region Indonesia Timur"};
	
	@SuppressLint("SimpleDateFormat")
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.parameter);
		
		parUsername = (EditText) findViewById(R.id.paraETusername);
		parPasswd = (EditText) findViewById(R.id.paraETpassword); 
		parPidkey = (EditText) findViewById(R.id.paraETpidkey);
		parSite = (EditText) findViewById(R.id.paraETsite);
		parRom = (EditText) findViewById(R.id.paraETrom);
		
		synPil = (CheckBox) findViewById(R.id.paraCbSync);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
		Date date = new Date();
		datestring = dateFormat.format(date); 
		
		openparasql();
		
		parSite.setEnabled(false);
		parRom.setEnabled(false);
		
		spinsitex = (Spinner) findViewById(R.id.paraSpin1);
		spinsitex.setOnItemSelectedListener((OnItemSelectedListener) NOOParameter.this);
		
		spinrax = (Spinner) findViewById(R.id.paraSpin2);
		spinrax.setOnItemSelectedListener((OnItemSelectedListener) NOOParameter.this);
		
		ArrayAdapter<String> as1 = new ArrayAdapter<String>(NOOParameter.this,android.R.layout.simple_spinner_item, sitex);
		as1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinsitex.setAdapter(as1);
	
		ArrayAdapter<String> as2 = new ArrayAdapter<String>(NOOParameter.this,android.R.layout.simple_spinner_item, romax);
		as2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinrax.setAdapter(as2);
		
		
		Button btnSave = (Button) findViewById(R.id.paraBtnSave);
		btnSave.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				boolean itsimpan = true;
				 try {
					 String iuser = parUsername.getText().toString();
					 String ipasswd = parPasswd.getText().toString();
					 String ipid = parPidkey.getText().toString();
					 String isite = parSite.getText().toString();
					 String irom = parRom.getText().toString();
					 
					 if (iuser.isEmpty()) {
						 itsimpan = false;
					 	 Toast.makeText(NOOParameter.this, "Save Parameter FAIL!, User is Null", Toast.LENGTH_SHORT).show();
					 } else {	 
						 NOODBInitial entry = new NOODBInitial(NOOParameter.this);
						 entry.open();
						 if(entry.getJUser()<= 0) { 
				            entry.createEntry(iuser,ipasswd,ipid,isite,irom);						    
						 }else{
							entry.updateEntry("1",iuser,ipasswd,ipid,isite,irom);
						 }						 
						 entry.createLog(datestring, "Parameter Log :", "Save User "+iuser+", Password "+ipasswd+", Sid Key "+ipid+", Site "+isite+", ROM "+irom);
						 entry.close();
					 }
				 } catch (Exception e) {
					 itsimpan = false;
					 String error = e.toString();
					 Dialog d = new Dialog(NOOParameter.this);
				     d.setTitle("Error !");
				     TextView tv = new TextView(NOOParameter.this);
				     tv.setText(error);
				     d.setContentView(tv);  
				     d.show();
				     Log.e("log_tag_save_param", "Error parameter save data "+e.toString());
				 } finally {
					 if (itsimpan) {
					     Dialog d = new Dialog(NOOParameter.this);
					     d.setTitle("Save Data !");					     
					     TextView tv = new TextView(NOOParameter.this);
					     tv.setText("Succes");
					     d.setContentView(tv);
					     d.show();
					 }				 
				 }
				 finish();				
			}
		});
					
		Button btnLog = (Button) findViewById(R.id.paraBtnLog);		
		btnLog.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent openActivityParalog = new Intent("com.home.ncapp.NOOPARALOG");
				startActivity(openActivityParalog);		
			}
		});
		
		Button btnBack = (Button) findViewById(R.id.paraBtnBack);		
		btnBack.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				finish();				
			}
		});
		
	}
	 
	@SuppressLint("DefaultLocale")
	public void onItemSelected(AdapterView<?> parent, View v, int position,   long id) {	
        switch (parent.getId()) {
        case R.id.paraSpin1:
        	String sitepos = sitex[position].toString().toUpperCase();
        	if (parSite.getText().toString().equals("")) {
        	    parSite.setText(sitepos.substring(0, 4));        	    
        	} else {
        		if (synPil.isChecked()) {
        			parSite.setText(sitepos.substring(0, 4));
        			Toast.makeText(this, "Site Optional <"+sitepos.substring(0, 4)+"> is Choice ! ",Toast.LENGTH_LONG).show();
        		}
        	}
            break;
        case R.id.paraSpin2:
        	String rompos = romax[position].toString().toUpperCase();
        	if (parRom.getText().toString().equals("")) {
        		parRom.setText(rompos.substring(0, 3));
        	} else {
        		if (synPil.isChecked()) {
        	       parRom.setText(rompos.substring(0, 3));
        	       Toast.makeText(this, "Rom Optional <"+rompos.substring(0, 3)+"> is Choice ! ",Toast.LENGTH_LONG).show();
        		}
        	}
            break;
        default:
            break;
        }
	}

	public void onNothingSelected(AdapterView<?> as_sitex) {
		// TODO Auto-generated method stub		
	}
	
	private void openparasql() {
		NOODBInitial info = new NOODBInitial(NOOParameter.this);
		info.open();
		
		if (info.getJUser()>=1) {
		    String x = "1";
            long l = Long.parseLong(x); 	        
            String returnUsr = info.getRtUser(l);
            String returnPass = info.getRPass(l);
            String returnSid = info.getRtSid(l);
            String returnSite = info.getRtSite(l);
            String returnRom = info.getRtRom(l);
		
            parUsername.setText(returnUsr);
            parPasswd.setText(returnPass);
            parPidkey.setText(returnSid);
            parSite.setText(returnSite);
            parRom.setText(returnRom);
		}
		info.close();
	}

	
}
