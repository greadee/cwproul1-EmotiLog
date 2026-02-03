package com.example.assg1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final ArrayList<Button> emojiBtns = new ArrayList<>();
    private final ArrayList<Emoticon> emoticonArr = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // pop. emoticonsArr with Emoticons
        emoticonArr.add(new Emoticon("happy", "😊"));
        emoticonArr.add(new Emoticon("investigative", "🧐"));
        emoticonArr.add(new Emoticon("miserable", "😖"));
        emoticonArr.add(new Emoticon("angry", "🤬"));
        emoticonArr.add(new Emoticon("rowdy", "🤠"));
        emoticonArr.add(new Emoticon("mindblown", "🤯"));
        emoticonArr.add(new Emoticon("dead", "☠️"));
        emoticonArr.add(new Emoticon("mischievous", "😈"));
        emoticonArr.add(new Emoticon("fiendish", "👹"));

        // pop. emojiBtns array with Buttons
        emojiBtns.add(findViewById(R.id.btn_happy));
        emojiBtns.add(findViewById(R.id.btn_investigative));
        emojiBtns.add(findViewById(R.id.btn_miserable));
        emojiBtns.add(findViewById(R.id.btn_angry));
        emojiBtns.add(findViewById(R.id.btn_rowdy));
        emojiBtns.add(findViewById(R.id.btn_mindblown));
        emojiBtns.add(findViewById(R.id.btn_dead));
        emojiBtns.add(findViewById(R.id.btn_mischievous));
        emojiBtns.add(findViewById(R.id.btn_fiendish));

        // attach a click listener to each of the buttons
        for (Button b : emojiBtns) {
            b.setOnClickListener(this);
        }

        // init. EventLog and EventSummary buttons with click listeners
        Button eventLogBtn = findViewById(R.id.btn_viewLog);
        eventLogBtn.setOnClickListener(this);
        Button eventSummaryBtn = findViewById(R.id.btn_viewSummary);
        eventSummaryBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id  == R.id.btn_viewLog) {
            startActivity(new Intent(MainActivity.this, EventLogActivity.class));
        } else if (id  == R.id.btn_viewSummary) {
            startActivity(new Intent(MainActivity.this, EventSummaryActivity.class));
        } else if (id == R.id.btn_happy) {
            logPress("happy");
        } else if (id == R.id.btn_investigative) {
            logPress("investigative");
        } else if (id == R.id.btn_miserable) {
            logPress("miserable");
        } else if (id == R.id.btn_angry) {
            logPress("angry");
        } else if (id == R.id.btn_rowdy) {
            logPress("rowdy");
        } else if (id == R.id.btn_mindblown) {
            logPress("mindblown");
        } else if (id == R.id.btn_dead) {
            logPress("dead");
        } else if (id == R.id.btn_mischievous) {
            logPress("mischievous");
        } else if (id == R.id.btn_fiendish) {
            logPress("fiendish");
        }
    }

    private void logPress(String emoticonName) {
        Emoticon emoticon = getEmoticonByName(emoticonName);
        EmoticonButtonPress press = new EmoticonButtonPress(emoticon);

        // log press in the EventLog object.
        EventLog.addEvent(press);
    }

    // logPress helper
    private Emoticon getEmoticonByName(String emoticonName) {
        if (emoticonName == null) return null;
        for (Emoticon e : emoticonArr) {
            if (e.getName().equals(emoticonName)) {
                return e;
            }
        }
        return null;
    }
}