package com.whbattle.springboot.domain.entity.unit.weapon;
import com.whbattle.springboot.domain.entity.unit.DiceRoller;

public class Weapon {
    private final int attacks;
    private final int toHit;
    private final int toWound;
    private final int rend;
    private final int damage;

    private final DiceRoller diceRoller = new DiceRoller();

    public Weapon(int attacks, int toHit, int toWound, int rend, int damage) {
        this.attacks = attacks;
        this.toHit = toHit;
        this.toWound = toWound;
        this.rend = rend;
        this.damage = damage;
    }

    public int rollHits(int numberOfAttacker, boolean reRoll, int rollModifier, int numberToReRoll) {
        if (numberToReRoll != 0) {
            return diceRoller.rollDice(numberOfAttacker * attacks, toHit, rollModifier, numberToReRoll);
        }
        return diceRoller.rollDice(numberOfAttacker * attacks, toHit, rollModifier, reRoll);
    }

    public int rollWounds(int numberOfWounds, boolean reRoll, int rollModifier, int numberToReRoll) {
        if (numberToReRoll != 0) {
            return diceRoller.rollDice(numberOfWounds, toWound, rollModifier, numberToReRoll);
        }
        return diceRoller.rollDice(numberOfWounds, toWound, rollModifier, reRoll);
    }

    public int getRend() {
        return rend;
    }

    public int getDamage() {
        return damage;
    }
}
