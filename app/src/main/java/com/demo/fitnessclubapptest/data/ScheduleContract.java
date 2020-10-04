package com.demo.fitnessclubapptest.data;

import android.provider.BaseColumns;

public class ScheduleContract {

    public static final class ScheduleEntry implements BaseColumns {
        public static final String TABLE_NAME = "schedule";

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_START_TIME = "start_time";
        public static final String COLUMN_END_TIME = "end_time";
        public static final String COLUMN_TEACHER = "teacher";
        public static final String COLUMN_PLACE = "place";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_WEEK_DAY = "week_day";

        public static final String TYPE_TEXT = "TEXT";
        public static final String TYPE_INTEGER = "INTEGER";

        public static final String CREATE_COMMAND = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "(" + _ID + " " + TYPE_INTEGER + " PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " " + TYPE_TEXT + ", "
                + COLUMN_START_TIME + " " + TYPE_TEXT + ", "
                + COLUMN_END_TIME + " " + TYPE_TEXT + ", "
                + COLUMN_TEACHER + " " + TYPE_TEXT + ", "
                + COLUMN_PLACE + " " + TYPE_TEXT + ", "
                + COLUMN_DESCRIPTION + " " + TYPE_TEXT + ", "
                + COLUMN_WEEK_DAY + " " + TYPE_INTEGER + ")";

        public static final String DROP_COMMAND = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
