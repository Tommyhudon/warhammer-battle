package com.whbattle.springboot.domain.entity;

import java.util.Random;

public class Die {
    private final Random rand = new Random();

    public Die() {}

    public int roll() {
        int test = rand.nextInt(5) + 1;
        return test;
    }
}
