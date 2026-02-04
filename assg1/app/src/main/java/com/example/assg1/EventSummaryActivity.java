package com.example.assg1;

import android.media.metrics.Event;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class EventSummaryActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        // init. back button
        Button back = findViewById(R.id.btn_summaryBack);
        back.setOnClickListener(this);

        Button pickDay = findViewById(R.id.btn_summaryPickDay);
        pickDay.setOnClickListener(this);

        Button confirmInput = findViewById(R.id.btn_summaryConfirmDay);
        confirmInput.setOnClickListener(this);

        TextView summaryCountView = findViewById(R.id.summary_count);
        TextView summaryFreqView = findViewById(R.id.summary_frequency);
        Long rightNow = System.currentTimeMillis();

        setSummaryViews(summaryCountView, summaryFreqView, rightNow);
    }

    @Override
    public void onClick(View view) {
        // init. Views for use in method
        TextView summaryCountView = findViewById(R.id.summary_count);
        TextView summaryFreqView = findViewById(R.id.summary_frequency);
        TextView promptString = findViewById(R.id.date_prompt);
        EditText promptInput = findViewById(R.id.date_input);
        Button confirmInput = findViewById(R.id.btn_summaryConfirmDay);
        Button pickDay = findViewById(R.id.btn_summaryPickDay);

        // finish sub-activity, go back to main activity.
        if (view.getId() == R.id.btn_summaryBack) { finish(); }
        // Convert input to date in ms, hide and display corresponding views
        else if (view.getId() == R.id.btn_summaryConfirmDay) {
            String date = promptInput.getText().toString().trim();
            Long time_ms = dateStringConvert_ms(date);

            if (time_ms == null) {
                promptInput.setError("Use YYYY-MM-DD");
                return;
            }

            setSummaryViews(summaryCountView, summaryFreqView, time_ms);

            // Hide prompt display
            promptString.setVisibility(View.GONE);
            promptInput.setVisibility(View.GONE);
            confirmInput.setVisibility(View.GONE);
            // Bring back Summary display
            summaryCountView.setVisibility(View.VISIBLE);
            summaryFreqView.setVisibility(View.VISIBLE);
            pickDay.setVisibility(View.VISIBLE);
        }
        // Prompt for input, hide and display corresponding views
        else if (view.getId() == R.id.btn_summaryPickDay) {
            // Hide default display
            summaryCountView.setVisibility(View.GONE);
            summaryFreqView.setVisibility(View.GONE);
            pickDay.setVisibility(View.GONE);

            // Display entry text and input field
            promptString.setVisibility(View.VISIBLE);
            promptInput.setVisibility(View.VISIBLE);
            confirmInput.setVisibility(View.VISIBLE);

            promptInput.requestFocus();
        }
    }
    private void setSummaryViews(TextView textCount, TextView textFreq, Long time_ms) {
        // Get summary values
        EmoticonDailySummary summary = new EmoticonDailySummary(time_ms);
        summary.getAllEventsByDay(time_ms);
        Map<String, Integer> eventDistr = summary.getEventDistr();
        int summaryCount = summary.getTotalCount();

        // Set summary values inside of the TextView
        textCount.setText("Total events: " + summaryCount);
        textFreq.setText(formatDistrString(eventDistr, summaryCount));
    }
    private String formatDistrString(Map<String, Integer> distr, int summaryCount) {
        if (distr == null || distr.isEmpty()) {
            return "No events recorded on this day.";
        }
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(distr.entrySet());

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> e : entries) {
            int count = e.getValue();
            sb.append("You pressed the ");
            sb.append(e.getKey());
            sb.append(" emoji ");
            sb.append(count);
            sb.append(" times. You felt this way ");
            double frequency = (double) count / summaryCount;
            frequency = frequency * 100;
            int percentage = (int) frequency;
            sb.append(percentage);
            sb.append("% of the time.");
            sb.append("\n");
        }
        return sb.toString().trim();
    }

    // "How to convert a date to milliseconds", Luiggi Mendoza, Last Edited: 05/17/2018. https://stackoverflow.com/questions/26637168/how-to-convert-a-date-to-milliseconds, https://stackoverflow.com/users/1065197/luiggi-mendoza
    private Long dateStringConvert_ms(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            sdf.setTimeZone(TimeZone.getTimeZone("America/Edmonton"));

            Date date = sdf.parse(dateStr);
            if (date == null) return null;
            return date.getTime();

        } catch (ParseException e) { return null; }
    }
}

