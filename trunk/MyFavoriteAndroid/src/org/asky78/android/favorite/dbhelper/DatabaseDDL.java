package org.asky78.android.favorite.dbhelper;

import org.asky78.android.favorite.dbhelper.table.*;

/**
 * DB의 DDL문을 정의하는 클래스 
 * @author kimjaeseung
 */
public class DatabaseDDL {

	public static final String DATABASE_NAME = "kensin.db";
	public static final int DATABASE_VERSION = 1;
	
	public static final String TABLE_CREATE_TEST_TABLE = " create table if not exists "
			+ TEST_TABLE.TABLE_NAME + " ( "
			+ TEST_TABLE.DATE + " text, "
			+ TEST_TABLE.MSG + " text ); ";
}
