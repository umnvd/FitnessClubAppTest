package com.demo.fitnessclubapptest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.demo.fitnessclubapptest.adapters.ScheduleAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewSchedule;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewSchedule = findViewById(R.id.recyclerViewSchedule);
        recyclerViewSchedule.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        ScheduleAdapter adapter = new ScheduleAdapter();
        recyclerViewSchedule.setAdapter(adapter);
        recyclerViewSchedule.setHasFixedSize(true);

    }
}