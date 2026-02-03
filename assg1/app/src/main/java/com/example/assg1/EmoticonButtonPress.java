package com.example.assg1;

public final class EmoticonButtonPress {

    private final Emoticon emoticon;
    private final long timestamp_ms;

    public EmoticonButtonPress(Emoticon emoticon) {
        if (emoticon == null) throw new IllegalArgumentException("emoticon required");
        this.emoticon = emoticon;
        this.timestamp_ms = System.currentTimeMillis();
    }
    public Emoticon getEmoticon() { return emoticon; }
    public long getTimestamp() { return timestamp_ms; }
}
