package com.demo.fitnessclubapptest.utils;

import com.demo.fitnessclubapptest.data.Lesson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_PLACE = "place";
    private static final String KEY_TEACHER = "teacher";
    private static final String KEY_START_TIME = "startTime";
    private static final String KEY_END_TIME = "endTime";
    private static final String KEY_WEEK_DAY = "weekDay";

    public static List<Lesson> getLessonListFromJsonArray() {
        JSONArray jsonArray = NetworkUtils.getJsonArrayFromNetwork();
        List<Lesson> result = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonLesson = jsonArray.getJSONObject(i);
                String name = jsonLesson.getString(KEY_NAME);
                String description = jsonLesson.getString(KEY_DESCRIPTION);
                String place = jsonLesson.getString(KEY_PLACE);
                String teacher = jsonLesson.getString(KEY_TEACHER);
                String startTime = jsonLesson.getString(KEY_START_TIME);
                String endTime = jsonLesson.getString(KEY_END_TIME);
                int weekDay = jsonLesson.getInt(KEY_WEEK_DAY);
                result.add(new Lesson(name, startTime, endTime, teacher, place, description, weekDay));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
