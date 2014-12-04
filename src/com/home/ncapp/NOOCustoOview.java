package com.home.ncapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class NOOCustoOview extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customer_oview);
		
		cariOvCustomer();
		
		final ListView lv = getListView();	     
	    lv.setTextFilterEnabled(true);     
	    lv.setOnItemClickListener(new OnItemClickListener() {
			@SuppressWarnings("unchecked")
			public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
				HashMap<String, String> o = (HashMap<String, String>) lv.getItemAtPosition(position);
				Toast.makeText(NOOCustoOview.this, "'" + o.get("CUSTNAME") + "' was clicked.", Toast.LENGTH_SHORT).show();
			}
	    });
	    
	    Button btnOvCustAdd = (Button) findViewById(R.id.btncustOvAdd);
	    btnOvCustAdd.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent openActivityCustAdd = new Intent("com.home.ncapp.NOOCUSTODETAILINFO");
				startActivity(openActivityCustAdd);
			}
		});
	    
	    Button btnOvCustBack = (Button) findViewById(R.id.btncustOvBack);
	    btnOvCustBack.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {				
				finish();
			}
		});
	    
		
	}

	public void cariOvCustomer () {
		ArrayList<HashMap<String, String>> mylistcustomer = new ArrayList<HashMap<String, String>>();
	    	             
        NOODBInitial info = new NOODBInitial(NOOCustoOview.this);
 		info.open(); 
 		int recawal = 0;
 		int tjum = info.getJCust();
 		if (tjum>=1) {
 			recawal = info.getRecCustom();
 		}else{
 			recawal = 0;
 		}
 		
		if (tjum <= 0) {
			HashMap<String, String> maplog = new HashMap<String, String>();
 			maplog.put("CUSTNAME",  "No Customer Found! ");;
 			maplog.put("CUSTADDR", "-");
 			mylistcustomer.add(maplog);
       	}else{	
 			int totx = ((tjum+recawal)-1);  
			for (int k = recawal ; k <= totx ; k++) {
			   HashMap<String, String> maplog = new HashMap<String, String>();
			   String xlog = String.valueOf(k);
	           long l = Long.parseLong(xlog);
	           maplog.put("CUSTNAME",  "<"+k+"> "+info.getRCustName(l));;
	           maplog.put("CUSTADDR", info.getRCustAddr(l));
	           mylistcustomer.add(maplog);
		    }
 		}
		info.close();	
 	
	    ListAdapter adapterCustOview = new SimpleAdapter(NOOCustoOview.this, mylistcustomer , R.layout.rowovcust,
	                           new String[] { "CUSTNAME", "CUSTADDR" },
	                           new int[] { R.id.cust_name, R.id.cust_address });
	     
	    setListAdapter(adapterCustOview);
	}
	

}
