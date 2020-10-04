package com.demo.fitnessclubapptest.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;

public class ScheduleDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "schedule.db";
    private static final int DB_VERSION = 1;

    public ScheduleDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(ScheduleContract.ScheduleEntry.CREATE_COMMAND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(ScheduleContract.ScheduleEntry.DROP_COMMAND);
        onCreate(sqLiteDatabase);
    }
}
