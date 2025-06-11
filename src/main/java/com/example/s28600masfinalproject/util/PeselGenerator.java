package com.example.s28600masfinalproject.util;

import java.util.Random;

public class PeselGenerator {
    public static String generateRandomPesel(){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 11; i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }
}
