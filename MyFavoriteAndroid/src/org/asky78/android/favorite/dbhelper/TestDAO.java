package org.asky78.android.favorite.dbhelper;

import org.asky78.android.favorite.common.*;

import android.content.*;
import android.database.*;
import android.util.*;

public class TestDAO {
	private SQLiteDBHelper db;

	public TestDAO(Context context) {
		db = SQLiteDBHelper.getInstance(context);
	}

	public void close() {
		db.close();
	}
	
	public Cursor selectList(String searchText){
		StringBuilder sb = new StringBuilder();
		sb.append(" select date, msg ");
		sb.append(" from test_table ");
		sb.append(" where msg like '%" + searchText + "%' ");
		
		Cursor c = null;
		
		try {
			c = db.select(sb.toString(), null);
		} catch (Exception e) {
			Log.e(Constants.TAG, "select error : " + e.getMessage());
		}
		return c;
	}
}
