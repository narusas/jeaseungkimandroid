package org.asky78.android.favorite.dbhelper;

import org.asky78.android.favorite.common.*;

import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.*;

public class SQLiteDBHelper extends SQLiteOpenHelper{
	private static SQLiteDBHelper instance;
	private static SQLiteDatabase db;
	
	public SQLiteDBHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	public SQLiteDBHelper(Context context) {
		super(context, DatabaseDDL.DATABASE_NAME, null, DatabaseDDL.DATABASE_VERSION);
	}
	
	public static void initialize(Context context){
		if(instance == null){
			instance = new SQLiteDBHelper(context);
			try {
				db = instance.getWritableDatabase();
			} catch (Exception e) {
				Log.e(Constants.TAG,"DB CONNECT ERROR : " + e.getMessage());
			}
		}
	}
	public static final SQLiteDBHelper getInstance(Context context){
		initialize(context);
		return instance;
	}
	public static final SQLiteDatabase getDB(Context context){
		initialize(context);
		return db;
	}
	public void close(){
		db.close();
		instance = null;
	}
	public Cursor select(String sql, String[] args){
		Cursor c = null;
		try {
			c = db.rawQuery(sql, args);
		} catch (Exception e) {
			errorLog("DB SELECT QUERY ERROR : " + e.getMessage());
		}
		return c;
	}

	private void errorLog(String msg) {
		Log.e(Constants.TAG,msg);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(DatabaseDDL.TABLE_CREATE_TEST_TABLE);
			db.execSQL(" insert into test_table values('2011-11-19', 'test' ); ");
		} catch (Exception e) {
			errorLog("db create error : " + e.getMessage());
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }

}
