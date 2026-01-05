package com.example.rotationmaster;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class EmployeeDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "employees.db";
    private static final int DATABASE_VERSION = 1;

    // Table & column names
    public static final String TABLE_EMPLOYEES  = "employees";
    public static final String COL_ID           = "_id";
    public static final String COL_NAME         = "name";
    public static final String COL_START_TIME   = "start_time";
    public static final String COL_START_DATE   = "start_date";
    public static final String COL_END_TIME     = "end_time";
    public static final String COL_END_DATE     = "end_date";

    // Create table SQL
    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_EMPLOYEES + "(" +
                    COL_ID          + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_NAME        + " TEXT NOT NULL, " +
                    COL_START_DATE  + " TEXT, " +
                    COL_START_TIME  + " TEXT, " +
                    COL_END_DATE    + " TEXT, " +
                    COL_END_TIME    + " TEXT"   +
                    ");";

    public EmployeeDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEES);
        onCreate(db);
    }
}
