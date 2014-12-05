package com.home.ncapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NOODBInitial {
	public static final String KEY_ROWID = "_idx";
	public static final String KEY_USER = "user_name";
	public static final String KEY_PASSWORD = "user_password";
	public static final String KEY_KEYPID = "user_keysid";
	public static final String KEY_SITE = "user_site";	
	public static final String KEY_ROM = "user_rom";
	
	public static final String LOG_ROWID = "_idx";
	public static final String LOG_DATE = "log_date";
	public static final String LOG_PROSSES = "log_prosses";
	public static final String LOG_NOTES = "log_notes";
	
	public static final String CUS_ROWID = "_idx";
	public static final String CUS_NAME = "cus_name";
	public static final String CUS_SADDR = "cus_address";
	public static final String CUS_CITY = "cus_city";
	public static final String CUS_REGION = "cus_region";
	public static final String CUS_PROPINCE = "cus_propince";
	public static final String CUS_LOCATE = "cus_locate";
	public static final String CUS_KLASIFY = "cus_klasify";
	public static final String CUS_DIVISI = "cus_divisi";
	public static final String CUS_BUMN = "cus_bumn";
	public static final String CUS_LCREDIT = "cus_lcredit";
	public static final String CUS_OWNER = "cus_owner";
	public static final String CUS_PIC = "cus_pic";
	public static final String CUS_EMAIL = "cus_email";
	public static final String CUS_CTELP = "cus_ctelp";
	public static final String CUS_CRDATE = "cus_crdate";
	public static final String CUS_SITE = "cus_site";
	public static final String CUS_PSALES = "cus_psales";
			
	private static final String DATABASE_NAME = "noodb";
	private static final String DATABASE_TABLE = "noousertable";
	private static final String DATABASE_LOGTABLE = "noologstable";
	private static final String DATABASE_CUSTOMER = "noocusttable";
	private static final int DATABASE_VERSION = 1;
	
	private DbHelper ourHelper;
	private Context ourContext; 
	private SQLiteDatabase ourDatabase;
	
	private static class DbHelper extends SQLiteOpenHelper {

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		} 

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE "+ DATABASE_TABLE + " ("+
			KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
			KEY_USER + " TEXT NOT NULL, " +
			KEY_PASSWORD + " TEXT NOT NULL, " +
			KEY_KEYPID + " TEXT NOT NULL, " +
			KEY_SITE + " TEXT NOT NULL, " +
			KEY_ROM + " TEXT NOT NULL); "
            );		
			
			db.execSQL("CREATE TABLE "+ DATABASE_LOGTABLE + " ("+
			LOG_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
			LOG_DATE + " TEXT NOT NULL, " +
			LOG_PROSSES + " TEXT NOT NULL, " +
			LOG_NOTES + " TEXT NOT NULL ); "
            );	
			
			db.execSQL("CREATE TABLE "+ DATABASE_CUSTOMER + " ("+
			CUS_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
			CUS_NAME + " TEXT NOT NULL, " +
			CUS_SADDR + " TEXT NOT NULL, " +
			CUS_CITY + " TEXT NOT NULL, " +
			CUS_REGION + " TEXT NOT NULL, " +
			CUS_PROPINCE + " TEXT NOT NULL, " +
			CUS_LOCATE + " TEXT NOT NULL, " +
			CUS_KLASIFY + " TEXT NOT NULL, " +
			CUS_DIVISI + " TEXT NOT NULL, " +
			CUS_BUMN + " TEXT NOT NULL, " +
			CUS_OWNER + " TEXT NOT NULL, " +
			CUS_PIC + " TEXT NOT NULL, " +
			CUS_EMAIL + " TEXT NOT NULL, " +
			CUS_CTELP + " TEXT NOT NULL, " +
			CUS_CRDATE + " TEXT NOT NULL, " +
			CUS_SITE + " TEXT NOT NULL, " +
			CUS_PSALES + " TEXT NOT NULL ); "
		    );
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
		    db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE);
		    onCreate(db);
		    
		    db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_LOGTABLE);
		    onCreate(db);
		}	
	}	
	
	public NOODBInitial(final Context c) {
		ourContext = c ;
	}

	public NOODBInitial open() throws SQLException {
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase(); 	
		return this;
    }

	public void close() { 
		ourHelper.close();
	}
	
	// end of Initial database ======================
	// table user //--------------------------------------------------------
	public long createEntry(String iuser, String ipasswd, String isid,String ipsite,String iprom) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_USER, iuser);
		cv.put(KEY_PASSWORD, ipasswd);
		cv.put(KEY_KEYPID, isid);
		cv.put(KEY_SITE, ipsite);
		cv.put(KEY_ROM, iprom);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}
	
	public int getJUser() {
		String[] coloums = new String[] {KEY_ROWID, KEY_USER, KEY_PASSWORD, KEY_KEYPID, KEY_SITE, KEY_ROM};
		Cursor c = ourDatabase.query(DATABASE_TABLE, coloums, null,null,null,null,null);
		int result = 0;
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result++;
		}			
		return result;				
	}
	
	public long updateEntry(String l, String iuser, String ipasswd,
		String isid, String ipsite, String irom) { 
		ContentValues cvUp = new ContentValues();
		cvUp.put(KEY_USER, iuser);
		cvUp.put(KEY_PASSWORD, ipasswd);
		cvUp.put(KEY_KEYPID, isid);
		cvUp.put(KEY_SITE, ipsite);
		cvUp.put(KEY_ROM, irom);
		return ourDatabase.update(DATABASE_TABLE, cvUp, KEY_ROWID +"="+l, null);
	}
	
	public String getRtUser(long l) {
		String[] coloums = new String[]{KEY_ROWID, KEY_USER, KEY_PASSWORD, KEY_KEYPID, KEY_SITE, KEY_ROM};;
		Cursor cUser = ourDatabase.query(DATABASE_TABLE, coloums, KEY_ROWID +"="+l, null, null, null, null);
		if (cUser != null){
			cUser.moveToFirst();
			String Uname = cUser.getString(1);
			return Uname;
		}
		return null;
	}
	
	public String getRPass(long l) {
		String[] coloums = new String[]{KEY_ROWID, KEY_USER, KEY_PASSWORD, KEY_KEYPID, KEY_SITE, KEY_ROM};;
		Cursor cPass = ourDatabase.query(DATABASE_TABLE, coloums, KEY_ROWID +"="+l, null, null, null, null);
		if (cPass != null){
			cPass.moveToFirst();
			String paswdx = cPass.getString(2);
			return paswdx;
		}
		return null;
	}
	
	public String getRtSid(long l) {
		String[] coloums = new String[]{KEY_ROWID, KEY_USER, KEY_PASSWORD, KEY_KEYPID, KEY_SITE, KEY_ROM};;
		Cursor cPid = ourDatabase.query(DATABASE_TABLE, coloums, KEY_ROWID +"="+l, null, null, null, null);
		if (cPid != null){
			cPid.moveToFirst();
			String pidx = cPid.getString(3);
			return pidx;
		}
		return null;
	}
	
	public String getRtSite(long l) {
		String[] coloums = new String[]{KEY_ROWID, KEY_USER, KEY_PASSWORD, KEY_KEYPID, KEY_SITE, KEY_ROM};;
		Cursor cSite = ourDatabase.query(DATABASE_TABLE, coloums, KEY_ROWID +"="+l, null, null, null, null);
		if (cSite != null){
			cSite.moveToFirst();
			String nSitex = cSite.getString(4);
			return nSitex;
		}
		return null;
	}
	
	public String getRtRom(long l) {
		String[] coloums = new String[]{KEY_ROWID, KEY_USER, KEY_PASSWORD, KEY_KEYPID, KEY_SITE, KEY_ROM};;
		Cursor cRom = ourDatabase.query(DATABASE_TABLE, coloums, KEY_ROWID +"="+l, null, null, null, null);
		if (cRom != null){
			cRom.moveToFirst();
			String nRomx = cRom.getString(5);
			return nRomx;
		}
		return null;
	}
	
	// table logs //--------------------------------------------------------
	public long createLog(String ldate, String lproses, String lnotes) {
		ContentValues cvILog = new ContentValues();
		cvILog.put(LOG_DATE, ldate);
		cvILog.put(LOG_PROSSES, lproses);
		cvILog.put(LOG_NOTES, lnotes);
		return ourDatabase.insert(DATABASE_LOGTABLE, null, cvILog);
	}
	
	public long deletelogtab() {
		return ourDatabase.delete(DATABASE_LOGTABLE, null, null);
	}
	
	public Cursor fetchAllLog() { 
		String[] coloums = new String[]{LOG_ROWID, LOG_DATE, LOG_PROSSES, LOG_NOTES};
		return ourDatabase.query(DATABASE_LOGTABLE, coloums, null,null,null,null,null);
	}

	public int getJLog() {
		String[] coloums = new String[]{LOG_ROWID, LOG_DATE, LOG_PROSSES, LOG_NOTES};
		Cursor c = ourDatabase.query(DATABASE_LOGTABLE, coloums, null,null,null,null,null);
		int result = 0;
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result++;
		}			
		return result;				
	}
	
	public int getRecLog() {
		String[] coloums = new String[]{LOG_ROWID, LOG_DATE, LOG_PROSSES, LOG_NOTES};
		Cursor c = ourDatabase.query(DATABASE_LOGTABLE, coloums, null,null,null,null,null);
		String result = "0";
		if (c != null){
		    c.moveToFirst();
		    if (c.getString(0) != null) {
		       result = c.getString(0);			
		       return Integer.parseInt(result);
		    }else{
		       return 0;	
		    }
		}
		return 0;
	}
	
	//================================
	public String getRtLogDate(long l) {
		String[] coloums = new String[]{LOG_ROWID, LOG_DATE, LOG_PROSSES, LOG_NOTES};
		Cursor c = ourDatabase.query(DATABASE_LOGTABLE, coloums, LOG_ROWID +"="+l, null, null, null, null);
		if (c != null){
			c.moveToFirst();
			String LogDate = c.getString(1);
			return LogDate;
		}
		return null;
	}

	public String getRtLogProses(long l) {
		String[] coloums = new String[]{LOG_ROWID, LOG_DATE, LOG_PROSSES, LOG_NOTES};
		Cursor c = ourDatabase.query(DATABASE_LOGTABLE, coloums, LOG_ROWID +"="+l, null, null, null, null);
		if (c != null){
			c.moveToFirst();
			String LogProses = c.getString(2);
			return LogProses;
		}
		return null;
	}

	public String getRtNote(long l) {
		String[] coloums = new String[]{LOG_ROWID, LOG_DATE, LOG_PROSSES, LOG_NOTES};
		Cursor c = ourDatabase.query(DATABASE_LOGTABLE, coloums, LOG_ROWID +"="+l, null, null, null, null);
		if (c != null){
			c.moveToFirst();
			String LogNote = c.getString(3);
			return LogNote;
		}
		return null;
	} 
	
	
	// table customer //--------------------------------------------------------
	public long createCustomer(String icust, String iaddr, String icity,String iregion,String ipropinc) {
		ContentValues cvc = new ContentValues();
		cvc.put(CUS_NAME, icust);
<<<<<<< HEAD
		cvc.put(CUS_SADDR, iaddr); 
=======
		cvc.put(CUS_SADDR, iaddr);
>>>>>>> origin/master
		cvc.put(CUS_CITY, icity);
		cvc.put(CUS_REGION, iregion);
		cvc.put(CUS_PROPINCE, ipropinc);
		return ourDatabase.insert(DATABASE_CUSTOMER, null, cvc);
	} 
	
<<<<<<< HEAD
	public long updateCustomer(String l, String icust, String iaddr,
			String icity, String iregion, String ipropinc) {
			ContentValues cvUp = new ContentValues();
			cvUp.put(CUS_NAME, icust);
			cvUp.put(CUS_SADDR, iaddr);
			cvUp.put(CUS_CITY, icity);
			cvUp.put(CUS_REGION, iregion);
			cvUp.put(CUS_PROPINCE, ipropinc);
			return ourDatabase.update(DATABASE_CUSTOMER, cvUp, KEY_ROWID +"="+l, null);
		}
	
=======
>>>>>>> origin/master
	public int getJCust() {
		String[] coloums = new String[]{CUS_ROWID,CUS_NAME,CUS_SADDR,CUS_CITY,CUS_REGION,CUS_PROPINCE,CUS_LOCATE,CUS_KLASIFY,CUS_DIVISI,CUS_BUMN,CUS_OWNER,CUS_PIC,CUS_EMAIL,CUS_CTELP,CUS_CRDATE,CUS_SITE,CUS_PSALES};
		Cursor c = ourDatabase.query(DATABASE_CUSTOMER, coloums, null,null,null,null,null);
		int result = 0;
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result++;
		}			
		return result;				
	}

	public int getRecCustom() {
		String[] coloums = new String[]{CUS_ROWID,CUS_NAME,CUS_SADDR,CUS_CITY,CUS_REGION,CUS_PROPINCE,CUS_LOCATE,CUS_KLASIFY,CUS_DIVISI,CUS_BUMN,CUS_OWNER,CUS_PIC,CUS_EMAIL,CUS_CTELP,CUS_CRDATE,CUS_SITE,CUS_PSALES};
		Cursor c = ourDatabase.query(DATABASE_CUSTOMER, coloums, null,null,null,null,null);
		String result = "0";
		if (c != null){
		    c.moveToFirst();
		    if (c.getString(0) != null) {
		       result = c.getString(0);			
		       return Integer.parseInt(result);
		    }else{
		       return 0;	
		    }
		}
		return 0;
	}
	
	public String getRCustName(long l) {
		String[] coloums = new String[]{CUS_ROWID,CUS_NAME,CUS_SADDR,CUS_CITY,CUS_REGION,CUS_PROPINCE,CUS_LOCATE,CUS_KLASIFY,CUS_DIVISI,CUS_BUMN,CUS_OWNER,CUS_PIC,CUS_EMAIL,CUS_CTELP,CUS_CRDATE,CUS_SITE,CUS_PSALES};;
		Cursor cCust = ourDatabase.query(DATABASE_CUSTOMER, coloums, KEY_ROWID +"="+l, null, null, null, null);
		if (cCust != null){
			cCust.moveToFirst();
			String Uname = cCust.getString(1);
			return Uname;
		}
		return null;
	}

	public String getRCustAddr(long l) {
		String[] coloums = new String[]{CUS_ROWID,CUS_NAME,CUS_SADDR,CUS_CITY,CUS_REGION,CUS_PROPINCE,CUS_LOCATE,CUS_KLASIFY,CUS_DIVISI,CUS_BUMN,CUS_OWNER,CUS_PIC,CUS_EMAIL,CUS_CTELP,CUS_CRDATE,CUS_SITE,CUS_PSALES};;
		Cursor cCust = ourDatabase.query(DATABASE_CUSTOMER, coloums, KEY_ROWID +"="+l, null, null, null, null);
		if (cCust != null){
			cCust.moveToFirst();
			String Uname = cCust.getString(2);
			return Uname;
		}
		return null;
	}
	
	
}
