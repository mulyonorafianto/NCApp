package com.home.ncapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

public class NOOParalog extends ListActivity {

	Button plogbtnclear,plogbtnback; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.paralog);
		
		plogbtnclear = (Button) findViewById(R.id.logbtnClear);
		plogbtnback = (Button) findViewById(R.id.logbtnback);
		
		carilog();
		
		plogbtnclear.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				NOODBInitial infoclear = new NOODBInitial(NOOParalog.this);
		 		infoclear.open(); 
		 		infoclear.deletelogtab();
		 		infoclear.close();	
		 		finish();
			}
		});
		
		plogbtnback.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
		 		finish();		 		
			}
		});

		
	}
	
	
	public void carilog () {
		ArrayList<HashMap<String, String>> mylistlog = new ArrayList<HashMap<String, String>>();
	    	             
        NOODBInitial info = new NOODBInitial(NOOParalog.this);
 		info.open(); 
 		int recawal = 0;
 		int tjum = info.getJLog();
 		if (tjum>=1) {
 			recawal = info.getRecLog();
 		}else{
 			recawal = 0;
 		}
 		
		if (tjum <= 0) {
			HashMap<String, String> maplog = new HashMap<String, String>();
 			maplog.put("LOGDATE",  "No Log Found! ");;
 			maplog.put("LOGPROSES", "-");
 			maplog.put("LOGNOTES",  "-");
 			mylistlog.add(maplog);
       	}else{	
 			int totx = ((tjum+recawal)-1);  
			for (int k = recawal ; k <= totx ; k++) {
			   HashMap<String, String> maplog = new HashMap<String, String>();
			   String xlog = String.valueOf(k);
	           long l = Long.parseLong(xlog);
	           maplog.put("LOGDATE",  "<"+k+"> "+info.getRtLogDate(l));;
	           maplog.put("LOGPROSES", info.getRtLogProses(l));
	           maplog.put("LOGNOTES",  info.getRtNote(l));
	           mylistlog.add(maplog);
		    }
 		}
		info.close();	
 	
	    ListAdapter adapterlog = new SimpleAdapter(NOOParalog.this, mylistlog , R.layout.rowlog,
	                           new String[] { "LOGDATE", "LOGPROSES", "LOGNOTES" },
	                           new int[] { R.id.log_date, R.id.log_proses, R.id.log_note });
	     
	    setListAdapter(adapterlog);
	}

}
