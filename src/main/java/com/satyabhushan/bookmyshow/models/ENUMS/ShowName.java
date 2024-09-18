package com.satyabhushan.bookmyshow.models.ENUMS;

import java.util.Random;

public enum ShowName {
    BENEFIT_SHOW,
    MORNING_SHOW,
    MATINEE_SHOW,
    FIRST_SHOW,
    SECOND_SHOW;
//    private static final Random RANDOM = new Random();

    public static ShowName randomShowName(int i) {
        ShowName[] showNames = values();
        return showNames[i];
    }
}
