package com.whbattle.springboot.domain.entity.dice;

import java.util.Random;

public class Die {
    private final Random rand = new Random();

    public Die() {}

    public int roll() {
        return rand.nextInt(5) + 1;
    }
}
