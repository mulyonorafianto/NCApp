package com.home.ncapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBinitialPara {
	public static final String KEY_ROWID = "idx";
	public static final String KEY_USER = "user_name";
	public static final String KEY_PASSWORD = "user_password";
	public static final String KEY_KEYSID = "user_keysid";
	
	private static final String DATABASE_NAME = "mbsdmsdb";
	private static final String DATABASE_TABLE = "dmsusertable";
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
			KEY_USER + " TEXT NOT NULL, "+
			KEY_PASSWORD + " TEXT NOT NULL, "+
			KEY_KEYSID + " TEXT NOT NULL );"
            );			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
		    db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE);
		    onCreate(db);
		}	
	}	
	
	public DBinitialPara(final Context c) {
		ourContext = c ;
	}
	
	public DBinitialPara open() throws SQLException {
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase(); 	
		return this;
    }

	public void close() { 
		ourHelper.close();
	}

	public long createEntry(String iuser, String ipasswd, String isid) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_USER, iuser);
		cv.put(KEY_PASSWORD, ipasswd);
		cv.put(KEY_KEYSID, isid);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}
	
	public String getData() {
		String[] coloums = new String[]{KEY_ROWID,KEY_USER,KEY_PASSWORD, KEY_KEYSID};
		Cursor c = ourDatabase.query(DATABASE_TABLE, coloums, null,null,null,null,null);
		String result = "";
		
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iuser = c.getColumnIndex(KEY_USER);
		int ipass = c.getColumnIndex(KEY_PASSWORD);
		int isid = c.getColumnIndex(KEY_KEYSID);
		
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result = result +""+ c.getString(iRow)+" "+c.getString(iuser)+" "+c.getString(isid)+" "+c.getString(ipass)+"\n";
		}			
		return result;		
	}
	
}