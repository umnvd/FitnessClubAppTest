package com.demo.fitnessclubapptest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;

import com.demo.fitnessclubapptest.adapters.ScheduleAdapter;
import com.demo.fitnessclubapptest.data.Lesson;
import com.demo.fitnessclubapptest.data.ScheduleContract.ScheduleEntry;
import com.demo.fitnessclubapptest.data.ScheduleDBHelper;
import com.demo.fitnessclubapptest.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewSchedule;
    private ScheduleAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    private static ScheduleDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewSchedule = findViewById(R.id.recyclerViewSchedule);
        adapter = new ScheduleAdapter();
        recyclerViewSchedule.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerViewSchedule.setAdapter(adapter);
        recyclerViewSchedule.setHasFixedSize(true);

        helper = new ScheduleDBHelper(this);
        updateSchedule();

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateSchedule();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void updateSchedule() {
        // 1 download schedule
        List<Lesson> lessons = JsonUtils.getLessonListFromJsonArray();
        if (lessons.size() > 0) {
            // 2 replace data in db
            new DeleteAndWriteTask().execute(lessons.toArray(new Lesson[lessons.size()]));
        }
        try {
            // 3 get schedule from db
            lessons = new ReadTask().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 4 set it into adapter
        adapter.setLessons(lessons);
    }

    private static class DeleteAndWriteTask extends AsyncTask<Lesson, Void, Void> {

        @Override
        protected Void doInBackground(Lesson... lessons) {
            SQLiteDatabase database = helper.getWritableDatabase();
            database.execSQL("DELETE FROM " + ScheduleEntry.TABLE_NAME);
            for (Lesson lesson : lessons) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(ScheduleEntry.COLUMN_NAME, lesson.getName());
                contentValues.put(ScheduleEntry.COLUMN_START_TIME, lesson.getStartTime());
                contentValues.put(ScheduleEntry.COLUMN_END_TIME, lesson.getEndTime());
                contentValues.put(ScheduleEntry.COLUMN_TEACHER, lesson.getTeacher());
                contentValues.put(ScheduleEntry.COLUMN_PLACE, lesson.getPlace());
                contentValues.put(ScheduleEntry.COLUMN_DESCRIPTION, lesson.getDescription());
                contentValues.put(ScheduleEntry.COLUMN_WEEK_DAY, lesson.getWeekDay());
                database.insert(ScheduleEntry.TABLE_NAME, null, contentValues);
            }
            return null;
        }
    }

    private static class ReadTask extends AsyncTask<Void, Void, List<Lesson>> {

        @Override
        protected List<Lesson> doInBackground(Void... voids) {
            List<Lesson> result = new ArrayList<>();
            SQLiteDatabase database = helper.getReadableDatabase();
            Cursor cursor = database.query(ScheduleEntry.TABLE_NAME, null, null, null, null, null, ScheduleEntry.COLUMN_WEEK_DAY + " ASC");
            try {
                if (cursor.moveToFirst()) {
                    int nameIndex = cursor.getColumnIndex(ScheduleEntry.COLUMN_NAME);
                    int startTimeIndex = cursor.getColumnIndex(ScheduleEntry.COLUMN_START_TIME);
                    int endTimeIndex = cursor.getColumnIndex(ScheduleEntry.COLUMN_END_TIME);
                    int teacherIndex = cursor.getColumnIndex(ScheduleEntry.COLUMN_TEACHER);
                    int placeIndex = cursor.getColumnIndex(ScheduleEntry.COLUMN_PLACE);
                    int descriptionIndex = cursor.getColumnIndex(ScheduleEntry.COLUMN_DESCRIPTION);
                    int weekDayIndex = cursor.getColumnIndex(ScheduleEntry.COLUMN_WEEK_DAY);

                    do {
                        String name = cursor.getString(nameIndex);
                        String startTime = cursor.getString(startTimeIndex);
                        String endTime = cursor.getString(endTimeIndex);
                        String teacher = cursor.getString(teacherIndex);
                        String place = cursor.getString(placeIndex);
                        String description = cursor.getString(descriptionIndex);
                        int weekDay = cursor.getInt(weekDayIndex);
                        result.add(new Lesson(name, startTime, endTime, teacher, place, description, weekDay));
                    } while (cursor.moveToNext());
                }
            } finally {
                cursor.close();
            }
            return result;
        }
    }
}