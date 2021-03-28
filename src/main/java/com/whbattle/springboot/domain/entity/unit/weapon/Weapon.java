package com.whbattle.springboot.domain.entity.unit.weapon;

import com.whbattle.springboot.domain.entity.shared.Die;

public class Weapon {
    private final int attacks;
    private final int toHit;
    private final int toWound;
    private final int rend;
    private final int damage;

    private final Die die = new Die();

    public Weapon(int attacks, int toHit, int toWound, int rend, int damage) {
        this.attacks = attacks;
        this.toHit = toHit;
        this.toWound = toWound;
        this.rend = rend;
        this.damage = damage;
    }

    public int rollHits(int numberOfAttacker) {
        int successfulHits = 0;

        for (int i = 0; i < numberOfAttacker * attacks; i++) {
            int roll = die.roll();

            if (roll >= this.toHit) {
                successfulHits ++;
            }
        }

        return successfulHits;
    }

    public int rollWounds(int numberOfRoll) {
        int successfulWounds = 0;

        for(int i = 0; i < numberOfRoll; i ++){
            int roll = die.roll();

            if (roll >= this.toWound ) {
                successfulWounds ++;
            }
        }

        return successfulWounds * this.damage;
    }

    public int getRend() {
        return rend;
    }

    public int getDamage() {
        return damage;
    }
}
