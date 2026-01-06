package com.example.rotationmaster;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


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
            "CREATE TABLE " + TABLE_EMPLOYEES + " (" +
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
        //TODO: Fails here, which you also added in the
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEES);
        onCreate(db);
    }

    // Employee getter
    public ArrayList<Employee> getAllEmployees() {
        ArrayList<Employee> empList = new ArrayList<>(); // Declare empty list
        SQLiteDatabase db = this.getReadableDatabase();  // Must be using the global helper database... check
        // The cursor is an object that populates your SQL query
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EMPLOYEES, null);

        // Iterate through cursor and save into empList
        Employee emp;
        if (cursor.moveToFirst()) {
            do {
                emp = new Employee();
                emp.name = cursor.getString(1);
                emp.startTime = cursor.getString(2);
                emp.startDate = cursor.getString(3);
                emp.endTime = cursor.getString(4);
                emp.endDate = cursor.getString(5);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return empList;
    }


}
