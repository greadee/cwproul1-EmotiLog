package com.example.assg1;

import android.media.metrics.Event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public final class EmoticonDailySummary {

    private final Long dayStart_ms;
    private List<EmoticonButtonPress> eventsByDay;

    public EmoticonDailySummary(Long dayStart_ms) {
        this.dayStart_ms = dayStart_ms;
        this.eventsByDay = new ArrayList<>();
    }

    public List<EmoticonButtonPress> getAllEventsByDay(Long dayStart_ms) {
        if (dayStart_ms == null) throw new IllegalArgumentException("Missing day filter.");

        List<EmoticonButtonPress> allEvents = EventLog.getAllEvents();
        ArrayList<EmoticonButtonPress> eventsByDay = new ArrayList<>();


        // "Java's java.util.TimeZone", Jakob Jenkov, Last update: 2014-06-23. https://jenkov.com/tutorials/java-date-time/java-util-timezone.html
        TimeZone timezone = TimeZone.getTimeZone("America/Edmonton");
        Calendar calendar = Calendar.getInstance(timezone);

        // convert day to summarize in ms to year, day.
        calendar.setTimeInMillis(dayStart_ms);
        int targYear = calendar.get(Calendar.YEAR);
        int targDay  = calendar.get(Calendar.DAY_OF_YEAR);

        // convert press timestamp in ms to year, day.
        for (EmoticonButtonPress p : allEvents) {
            calendar.setTimeInMillis(p.getTimestamp());
            int pressYear = calendar.get(Calendar.YEAR);
            int pressDay  = calendar.get(Calendar.DAY_OF_YEAR);
            if (targYear == pressYear && targDay == pressDay) { eventsByDay.add(p); }
        }
        this.eventsByDay = eventsByDay;
        return eventsByDay;
    }

    public int getTotalCount() { return eventsByDay.size(); }

    public Map<String, Integer> getEventDistr() {
        Map<String, Integer> eventDistr = new HashMap<>();
        for (EmoticonButtonPress p : eventsByDay) {
            String emoji = p.getEmoticon().getEmoji();
            eventDistr.put(emoji, eventDistr.getOrDefault(emoji, 0)+1);
        }
        return eventDistr;
    }
}
