package com.home.ncapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NOODBInitial {
	public static final String KEY_ROWID = "_idx";
	public static final String KEY_USER = "user_name";
	public static final String KEY_PASSWORD = "user_password";
	public static final String KEY_KEYSID = "user_keysid";
	public static final String KEY_SITE = "user_site";	
	
	public static final String LOG_ROWID = "_idx";
	public static final String LOG_DATE = "log_date";
	public static final String LOG_PROSSES = "log_prosses";
	public static final String LOG_NOTES = "log_notes";
		
	private static final String DATABASE_NAME = "noodb";
	private static final String DATABASE_TABLE = "noousertable";
	private static final String DATABASE_LOGTABLE = "noologstable";
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
			KEY_KEYSID + " TEXT NOT NULL);"
            );		
			
			db.execSQL("CREATE TABLE "+ DATABASE_LOGTABLE + " ("+
			LOG_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
			LOG_DATE + " TEXT NOT NULL, " +
			LOG_PROSSES + " TEXT NOT NULL, " +
			LOG_NOTES + " TEXT NOT NULL );"
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
	// table user //
	public long createEntry(String iuser, String ipasswd, String isid,String ipsite) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_USER, iuser);
		cv.put(KEY_PASSWORD, ipasswd);
		cv.put(KEY_KEYSID, isid);
		cv.put(KEY_SITE, ipsite);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}
	
	// table logs //
	public long createLog(String ldate, String lproses, String lnotes) {
		ContentValues cvILog = new ContentValues();
		cvILog.put(LOG_DATE, ldate);
		cvILog.put(LOG_PROSSES, lproses);
		cvILog.put(LOG_NOTES, lnotes);
		return ourDatabase.insert(DATABASE_LOGTABLE, null, cvILog);
	}
	
}
