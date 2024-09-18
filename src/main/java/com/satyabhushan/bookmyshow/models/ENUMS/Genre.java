package com.satyabhushan.bookmyshow.models.ENUMS;

import java.util.Random;

public enum Genre {
    ACTION,
    COMEDY,
    DRAMA,
    HORROR,
    ROMANCE,
    THRILLER;
    private static final Random RANDOM = new Random();

    public static Genre randomGenre() {
        Genre[] genres = values();
        return genres[RANDOM.nextInt(genres.length)];
    }
}
