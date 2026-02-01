package com.example.assg1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public final class EmoticonDailySummary {

    private final long dayStart_ms;
    private final List<EmoticonButtonPress> presses;

    public EmoticonDailySummary(long dayStart_ms) {
        this.dayStart_ms = dayStart_ms;
        this.presses = new ArrayList<>();
    }

    public long getDayStart() { return dayStart_ms; }

    public List<EmoticonButtonPress> getPresses() {
        return Collections.unmodifiableList(presses);
    }

    public void addPress(EmoticonButtonPress press) {
        if (press != null) presses.add(press);
    }

    public int getTotalCount() { return presses.size(); }

    // Need implement: dayStart_ms logic to filter EmotionButtonPress timestamp_ms.
    // Need implement: Map<> for EmoticonButtonPress emoticon frequency.

}
