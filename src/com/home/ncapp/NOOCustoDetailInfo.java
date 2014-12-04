package com.home.ncapp;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class NOOCustoDetailInfo extends ListActivity  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customer_ovadd);
		
		
		Button btnDetailBack = (Button) findViewById(R.id.btnCusAdBackx);
		btnDetailBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		
		final ListView lv = getListView();	     
	    lv.setTextFilterEnabled(true);     
//	    lv.setOnItemClickListener(new OnItemClickListener() {
//			@SuppressWarnings("unchecked")
//			public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
//				HashMap<String, String> o = (HashMap<String, String>) lv.getItemAtPosition(position);
//				Toast.makeText(NOOCustoDetailInfo.this, "'" + o.get("CUSTNAME") + "' was clicked.", Toast.LENGTH_SHORT).show();
//			}
//	    });
	}
	
//	public void cariOvCustomer () {
//		ArrayList<HashMap<String, String>> mylistcustomerInfo = new ArrayList<HashMap<String, String>>();
//	    	             
//        NOODBInitial infoAdd = new NOODBInitial(NOOCustoDetailInfo.this);
//        /* 		info.open(); 
// 		int recawal = 0;
// 		int tjum = info.getJCust();
// 		if (tjum>=1) {
// 			recawal = info.getRecCustom();
// 		}else{
// 			recawal = 0;
// 		}
// 		
//		if (tjum <= 0) {
//			HashMap<String, String> maplog = new HashMap<String, String>();
// 			maplog.put("CUSTNAME",  "No Customer Found! ");;
// 			maplog.put("CUSTADDR", "-");
// 			mylistcustomerInfo.add(maplog);
//       	}else{	
// 			int totx = ((tjum+recawal)-1);  
//			for (int k = recawal ; k <= totx ; k++) {
//			   HashMap<String, String> maplog = new HashMap<String, String>();
//			   String xlog = String.valueOf(k);
//	           long l = Long.parseLong(xlog);
//	           maplog.put("CUSTNAME",  "<"+k+"> "+info.getRCustName(l));;
//	           maplog.put("CUSTADDR", info.getRCustAddr(l));
//	           mylistcustomerInfo.add(maplog);
//		    }
// 		}
//		info.close();*/	
// 	
//	    ListAdapter adapterlog = new SimpleAdapter(NOOCustoDetailInfo.this, mylistcustomerInfo , R.layout.rowovcust,
//	                           new String[] { "CUSTNAME", "CUSTADDR" },
//	                           new int[] { R.id.cust_name, R.id.cust_address });
//	     
//	    setListAdapter(adapterlog);
//	}
	

}
