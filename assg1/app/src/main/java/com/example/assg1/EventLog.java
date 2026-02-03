package com.example.assg1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Append-only, session-persistence
public final class EventLog {
    private static final ArrayList<EmoticonButtonPress> presses = new ArrayList<>();

    private EventLog() {
    };

    public static void addEvent(EmoticonButtonPress press) {
        if (press == null) throw new IllegalArgumentException("Button Press Event missing.");
        presses.add(press);
    }

    public static List<EmoticonButtonPress> getAllEvents() {
        return Collections.unmodifiableList(presses);
    }
    /*
    public static List<EmoticonButtonPress> getAllEventsByDay(//What type are we passing through?) {
        if (//type == null) throw new IllegalArgumentException("Missing day filter.");
        ArrayList<EmoticonButtonPress> eventsByDay = new ArrayList<>();
        // return all EmoticonButtonPresses by day for EventSummary

        }
    }
    */

    public static int size() { return presses.size(); }

    public static boolean isEmpty() { return presses.isEmpty(); }



}
