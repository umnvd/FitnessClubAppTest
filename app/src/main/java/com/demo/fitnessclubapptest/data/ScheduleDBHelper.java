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
        new ExecCreateTask().execute(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        new ExecDropTask().execute(sqLiteDatabase);
    }

    private static class ExecCreateTask extends AsyncTask<SQLiteDatabase, Void, Void> {

        @Override
        protected Void doInBackground(SQLiteDatabase... dbs) {
            dbs[0].execSQL(ScheduleContract.ScheduleEntry.CREATE_COMMAND);
            return null;
        }
    }

    private static class ExecDropTask extends AsyncTask<SQLiteDatabase, Void, Void> {

        @Override
        protected Void doInBackground(SQLiteDatabase... dbs) {
            dbs[0].execSQL(ScheduleContract.ScheduleEntry.DROP_COMMAND);
            dbs[0].execSQL(ScheduleContract.ScheduleEntry.CREATE_COMMAND);
            return null;
        }
    }
}
