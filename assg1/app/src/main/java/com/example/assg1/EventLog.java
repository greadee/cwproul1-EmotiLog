package com.example.assg1;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

// Append-only, session-persistence
public final class EventLog {
    private static final ArrayList<EmoticonButtonPress> presses = new ArrayList<>();

    private EventLog() {
    }

    public static void addEvent(EmoticonButtonPress press) {
        if (press == null) throw new IllegalArgumentException("Button Press Event missing.");
        presses.add(press);
    }

    public static List<EmoticonButtonPress> getAllEvents() {
        return Collections.unmodifiableList(presses);
    }

    public int size() { return presses.size(); }

    public boolean isEmpty() { return presses.isEmpty(); }



}
