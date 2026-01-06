package com.example.rotationmaster;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.SimpleCursorAdapter;

public class EmployeeRepository {
    public final EmployeeDbHelper dbHelper;

    public EmployeeRepository(Context context) {
        this.dbHelper = new EmployeeDbHelper(context);
        // TODO: This should be removed, you added it because you are debugging
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        this.dbHelper.onCreate(db);
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

    public SimpleCursorAdapter pullMyDealersFromDb(Context activityContext) {
        String query = "SELECT ID as _id, name, start_time FROM " + EmployeeDbHelper.TABLE_EMPLOYEES;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        String[] fromColumns = {"name", "start_time"};
        int[] toViews = {android.R.id.text1, android.R.id.text2};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                activityContext,
                android.R.layout.simple_list_item_2,
                cursor,
                fromColumns,
                toViews,
                0
        );
        return  adapter;
    }

}
