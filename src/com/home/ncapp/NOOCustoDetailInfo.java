package com.home.ncapp;

<<<<<<< HEAD
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class NOOCustoDetailInfo extends ListActivity  {
	public String datestring;
	EditText sqlCustName, sqlCustAddrs;
	
	@SuppressLint("SimpleDateFormat") @Override
=======
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class NOOCustoDetailInfo extends ListActivity  {

	@Override
>>>>>>> origin/master
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customer_ovadd);
		
<<<<<<< HEAD
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
		Date date = new Date();
		datestring = dateFormat.format(date);
		
		sqlCustName = (EditText) findViewById(R.id.edcusname);
		sqlCustAddrs = (EditText) findViewById(R.id.edcustadr); 
=======
>>>>>>> origin/master
		
		Button btnDetailBack = (Button) findViewById(R.id.btnCusAdBackx);
		btnDetailBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
<<<<<<< HEAD

		Button btnDetailSave = (Button) findViewById(R.id.btnCusAdSavex);
		btnDetailSave.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean itsimpan = true;
				try {
					 String icustname = sqlCustName.getText().toString();
					 String icustaddr = sqlCustAddrs.getText().toString();
					 String icustcity = " ";
					 String icustreg = " ";
					 String icustprop = " ";
					 if (icustname.isEmpty()) {
						 itsimpan = false;
					 	 Toast.makeText(NOOCustoDetailInfo.this, "Save Parameter FAIL!, Customer Name is Null", Toast.LENGTH_SHORT).show();
					 } else {	 
						 NOODBInitial entryCust = new NOODBInitial(NOOCustoDetailInfo.this);
						 entryCust.open();
						 if(entryCust.getJUser()<= 0) { 
							 entryCust.createCustomer(icustname, icustaddr, icustcity, icustreg, icustprop);						    
						 }else{
							 entryCust.updateCustomer("1",icustname,icustaddr,icustcity,icustreg,icustprop);
						 }						 
						 entryCust.createLog(datestring, "Parameter", "Save Customer Name "+icustname+", Address "+icustaddr+", City "+icustcity+", Regional "+icustreg);
						 entryCust.close();
					 }
				 } catch (Exception e) {
					 itsimpan = false;
					 String error = e.toString();
					 Dialog d = new Dialog(NOOCustoDetailInfo.this);
				     d.setTitle("Error !");
				     TextView tv = new TextView(NOOCustoDetailInfo.this);
				     tv.setText(error);
				     d.setContentView(tv); 
				     d.show();
				 } finally {
					 if (itsimpan) {
					     Dialog d = new Dialog(NOOCustoDetailInfo.this);
					     d.setTitle("Save Data !");
					     TextView tv = new TextView(NOOCustoDetailInfo.this);
					     tv.setText("Succes");
					     d.setContentView(tv);
					     d.show();
					 }				 
				 }
				 finish();
				
			}
		});

		
		cariOvCustomer();
		
		final ListView lv = getListView();	     
	    lv.setTextFilterEnabled(true);     
	    lv.setOnItemClickListener(new OnItemClickListener() {
			@SuppressWarnings("unchecked")
			public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
				HashMap<String, String> o = (HashMap<String, String>) lv.getItemAtPosition(position);
				Toast.makeText(NOOCustoDetailInfo.this, "'" + o.get("EDCUSTNAME") + "' was clicked.", Toast.LENGTH_SHORT).show();
			}
	    });
	    	    
	}
    	
	public void cariOvCustomer () {
		ArrayList<HashMap<String, String>> mylistcustomerInfo = new ArrayList<HashMap<String, String>>();
	    	             
        NOODBInitial infoAdd = new NOODBInitial(NOOCustoDetailInfo.this);
        infoAdd.open(); 
 		int recawal = 0;
 		int tjum = infoAdd.getJCust();
 		if (tjum>=1) {
 			recawal = infoAdd.getRecCustom();
 		}else{
 			recawal = 0;
 		}
 		
		if (tjum <= 0) {
			HashMap<String, String> maplog = new HashMap<String, String>();
 			maplog.put("EDCUSTNAME",  "No Customer Found! ");;
 			maplog.put("EDCUSTADDR", "-");
 			mylistcustomerInfo.add(maplog);
       	}else{	
 			int totx = ((tjum+recawal)-1);  
			for (int k = recawal ; k <= totx ; k++) {
			   HashMap<String, String> maplog = new HashMap<String, String>();
		   String xlog = String.valueOf(k);
           long l = Long.parseLong(xlog);
	           maplog.put("EDCUSTNAME",  "<"+k+"> "+infoAdd.getRCustName(l));;
	           maplog.put("EDCUSTADDR", infoAdd.getRCustAddr(l));
           mylistcustomerInfo.add(maplog);
	    }
 		}
		infoAdd.close();	
 	
	    ListAdapter adapterlog = new SimpleAdapter(NOOCustoDetailInfo.this, mylistcustomerInfo , R.layout.rowovcustadd,
	                           new String[] { "EDCUSTNAME", "EDCUSTADDR" },
	                           new int[] { R.id.cust_name, R.id.cust_address });
	     
	    setListAdapter(adapterlog);
    }
=======
		
		
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
>>>>>>> origin/master
	

}
