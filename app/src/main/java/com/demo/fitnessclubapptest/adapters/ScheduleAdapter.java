package com.demo.fitnessclubapptest.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.fitnessclubapptest.R;
import com.demo.fitnessclubapptest.data.Lesson;

import java.util.ArrayList;
import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {

    private List<Lesson> lessons = new ArrayList<>();

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule, parent, false);
        return new ScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        Lesson lesson = lessons.get(position);
        String name = lesson.getName();
        String time = lesson.getStartTime() + " - " + lesson.getEndTime();
        String teacher = lesson.getTeacher();
        String place = lesson.getPlace();
        String description = lesson.getDescription();
        int weekDay = lesson.getWeekDay();

        holder.textViewName.setText(name);
        holder.textViewTime.setText(time);
        holder.textViewTeacher.setText(teacher);
        holder.textViewPlace.setText(place);
        holder.textViewDescription.setText(description);
        holder.textViewDay.setText(String.valueOf(weekDay));
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }

    public class ScheduleViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;
        private TextView textViewTime;
        private TextView textViewTeacher;
        private TextView textViewPlace;
        private TextView textViewDescription;
        private TextView textViewDay;

        public ScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewTime = itemView.findViewById(R.id.textViewTime);
            textViewTeacher = itemView.findViewById(R.id.textViewTeacher);
            textViewPlace = itemView.findViewById(R.id.textViewPlace);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewDay = itemView.findViewById(R.id.textViewDay);
        }
    }
}
