package com.example.assg1;

public class Emoticon {
    private final String id;
    private final String emoji;

    public Emoticon(String id, String emoji) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("String id not supplied to Emoticon constructor.");
        }
        if (emoji == null || emoji.trim().isEmpty()) {
            throw new IllegalArgumentException("String emoji not supplied to Emoticon constructor.");
        }
        this.id = id.trim();
        this.emoji = emoji.trim();
    }
    public String getId() {return id;}
    public String getEmoji(){return id;}

}
