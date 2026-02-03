package com.example.assg1;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EventLogActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<String> allEventsStr = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventlog);

        // init. back button
        Button back = findViewById(R.id.btn_summaryBack);
        back.setOnClickListener(this);

        // populated Event ListView
        ListView eventList = findViewById(R.id.list_eventLog);

        if (EventLog.getAllEvents().isEmpty()) {
            allEventsStr.add("No events logged yet.");
        } else {
            for (EmoticonButtonPress press : EventLog.getAllEvents()) {
                String timestamp = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(new Date(press.getTimestamp()));
                String event = "Feeling: " + press.getEmoticon().getEmoji() + " at " + timestamp;
                allEventsStr.add(event);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, allEventsStr);
        eventList.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        // finish sub-activity, go back to main activity.
        if (view.getId() == R.id.btn_summaryBack) {
            finish();
        }
    }
}

