package com.example.assg1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EventSummaryActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        // init. back button
        Button back = findViewById(R.id.btn_summaryBack);
        back.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        // finish sub-activity, go back to main activity.
        if (view.getId() == R.id.btn_summaryBack) { finish(); }
    }
}
