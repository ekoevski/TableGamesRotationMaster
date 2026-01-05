package com.example.rotationmaster;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class EmployeeRepository {
    private final EmployeeDbHelper dbHelper;

    public EmployeeRepository(Context context) {
        this.dbHelper = new EmployeeDbHelper(context);
    }

    public long insert(Employee emp) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EmployeeDbHelper.COL_NAME, emp.name);
        values.put(EmployeeDbHelper.COL_START_TIME, emp.startTime);
        values.put(EmployeeDbHelper.COL_START_DATE, emp.startDate);
        values.put(EmployeeDbHelper.COL_END_TIME, emp.endTime);
        values.put(EmployeeDbHelper.COL_END_DATE, emp.endDate);

        long newRowId = db.insert(EmployeeDbHelper.TABLE_EMPLOYEES, null, values);
        db.close();
        return newRowId;
    }
}
