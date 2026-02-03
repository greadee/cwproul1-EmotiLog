package com.example.assg1;

public class Emoticon {
    private final String name;
    private final String emoji;

    public Emoticon(String name, String emoji) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("String id not supplied to Emoticon constructor.");
        }
        if (emoji == null || emoji.trim().isEmpty()) {
            throw new IllegalArgumentException("String emoji not supplied to Emoticon constructor.");
        }
        this.name = name.trim().toLowerCase();
        this.emoji = emoji.trim();
    }
    public String getName() {return name;}
    public String getEmoji(){return emoji;}

}
