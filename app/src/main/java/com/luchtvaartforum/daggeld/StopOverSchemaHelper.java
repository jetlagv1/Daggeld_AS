package com.luchtvaartforum.daggeld;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.util.Log;
import java.util.Calendar;
import android.text.format.DateFormat;
import android.text.format.Time;
import javax.xml.transform.*;
import android.database.*;

public class StopOverSchemaHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "daggeld";
	//Increase this number for updating tables and database
	private static final int DATABASE_VERSION = 1;
	
	StopOverSchemaHelper (Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	

	@Override
	public void onCreate(SQLiteDatabase db) {
		//Create stopover table
		String createString = "CREATE TABLE " + StopOverTable.TABLE_NAME
		+ " (" + StopOverTable.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
		+ StopOverTable.AIRPORT + " TEXT,"
		+ StopOverTable.BETAAL_PERIODE + " Integer,"
		+ StopOverTable.ON_BLOCKS + " LONG,"
		+ StopOverTable.OFF_BLOCKS + " LONG,"
		+ StopOverTable.DAILY_ALLOWANCE + " DOUBLE);";
		db.execSQL(createString);
		
		//Create daggeld table
		createString = "CREATE TABLE " + DaggeldTable.TABLE_NAME
		+ " (" + DaggeldTable.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
		+ DaggeldTable.AIRPORT + " TEXT,"
		+ DaggeldTable.BETAAL_PERIODE + " Integer,"
		+ DaggeldTable.SUNDRIES + " DOUBLE,"
		+ DaggeldTable.LUNCH_ALLOWANCE + " DOUBLE"
		+ DaggeldTable.DINER_ALLOWANCE + " DOUBLE);";
		db.execSQL(createString);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersionNumber, int newVersionNumber) {
		Log.w("LOG_TAG", "Upgrading database from version "
		+ oldVersionNumber + " to " + newVersionNumber 
		+ ",which will destroy all old data");
		
		// KILL PREVIOUS TABLES IF UPGRADED
		db.execSQL("DROP TABLE IF EXISTS " + StopOverTable.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + DaggeldTable.TABLE_NAME);
		// CREATE NEW INSTANCE OF SCHEMA
		onCreate(db);
		
	}
	
	// WRAPPER METHOD FOR ADDING A STOPOVER
	public long addStopover(String airport, Integer betaal_periode, Long on_blocks, Long off_blocks, Double daily_allowance) { 
		// CREATE A CONTENTVALUE OBJECT 
		ContentValues cv = new ContentValues();
		cv.put(StopOverTable.AIRPORT, airport);
		cv.put(StopOverTable.BETAAL_PERIODE, betaal_periode);
		cv.put(StopOverTable.ON_BLOCKS, on_blocks);
		cv.put(StopOverTable.OFF_BLOCKS, off_blocks);
		cv.put(StopOverTable.DAILY_ALLOWANCE, daily_allowance);
	// RETRIEVE WRITEABLE DATABASE AND INSERT 
		SQLiteDatabase sd = getWritableDatabase();
		long result = sd.insert(StopOverTable.TABLE_NAME, StopOverTable.AIRPORT, cv);
		return result;
	}
	
	// WRAPPER METHOD OM DAGGELD TOE TE VOEGEN
	public long addDaggeld(String airport, Integer betaal_periode, Double sundries, Double lunch_allowance, Double diner_allowance) {
		ContentValues cv = new ContentValues();
		cv.put(DaggeldTable.AIRPORT, airport);
		cv.put(DaggeldTable.BETAAL_PERIODE, betaal_periode);
		cv.put(DaggeldTable.SUNDRIES, sundries);
		cv.put(DaggeldTable.LUNCH_ALLOWANCE, lunch_allowance);
		cv.put(DaggeldTable.DINER_ALLOWANCE, diner_allowance);
	// RETRIEVE WRITEABLE DATABASE AND INSERT	
		SQLiteDatabase sd = getWritableDatabase();
		long result = sd.insert(DaggeldTable.TABLE_NAME, DaggeldTable.AIRPORT, cv);
		return result;
	}
	
	// GET ALL STOPOVERS IN A BETAALPERIODE
	public Cursor getStopoversInBetaalperiode(int betaalperiode) { 
		SQLiteDatabase sd = getWritableDatabase();
		// WE ONLY NEED TO RETURN STOPOVER IDS 
		String[] cols = new String[] { StopOverTable.ID };
		String[] selectionArgs = new String[] { String.valueOf(betaalperiode) };
		// QUERY CLASS MAP FOR STUDENTS IN COURSE 
		Cursor c = sd.query(StopOverTable.TABLE_NAME, cols, StopOverTable.BETAAL_PERIODE + "= ?", selectionArgs, null, null, null);
		return c;
	}
	
	// GET ALL STOPOVERS AT AN AIRPORT
	public Cursor getStopoversAtAirport(String airport) { 
		SQLiteDatabase sd = getWritableDatabase();
		// WE ONLY NEED TO RETURN STOPOVER IDS 
		String[] cols = new String[] { StopOverTable.ID };
		String[] selectionArgs = new String[] { airport };
		// QUERY CLASS MAP FOR STUDENTS IN COURSE 
		Cursor c = sd.query(StopOverTable.TABLE_NAME, cols, StopOverTable.AIRPORT + "= ?", selectionArgs, null, null, null);
		return c;
	}
}
