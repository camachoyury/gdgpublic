package com.gdgcochabamba.ubicate.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHandler extends SQLiteOpenHelper {
	private static DataBaseHandler instance = null;

	public DataBaseHandler(Context context) {

		super(context, DBConstants.DATABASE_NAME, null,
				DBConstants.DATABASE_VERSION);
	}

	public static synchronized DataBaseHandler getInstance(Context context) {
		if (instance == null) {
			instance = new DataBaseHandler(context);
		}
		return instance;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + DBConstants.USER + "("
				+ DBConstants.USER_ID + " INTEGER PRIMARY KEY,"
				+ DBConstants.USER_EMAIL + " TEXT ,"
				+ DBConstants.USER_PASSWORD + " TEXT )");
	
		
		db.execSQL("CREATE TABLE " + DBConstants.PLACE + "("
				+ DBConstants.PLACE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ DBConstants.PLACE_NAME + " TEXT ,"
				+ DBConstants.PLACE_REFERENCE + " TEXT ,"
				+ DBConstants.PLACE_VECINITY + " TEXT ,"
				+ DBConstants.PLACE_LONGITUDE + " TEXT ,"
				+ DBConstants.PLACE_LATITUDE + " TEXT )");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		
		db.execSQL("DROP TABLE IF EXISTS " + DBConstants.PLACE );
		onCreate(db);
	}

}
