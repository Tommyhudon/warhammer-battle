package com.whbattle.springboot.domain.entity;

public class Unit {

    private String name;
    private int number;
    private int save;

    //objet pour arme ?
    private int attacks;
    private int toHit;
    private int toWound;
    private int rend;
    private int damage;

    private final Die die = new Die();

    public Unit(String name, int number, int save, int attacks, int toHit, int toWound, int rend, int damage) {
        this.name = name;
        this.number = number;
        this.save = save;
        this.attacks = attacks;
        this.toHit = toHit;
        this.toWound = toWound;
        this.rend = rend;
        this.damage = damage;
    }

    public boolean isDead() {
        return number <= 0;
    }

    public int rollAttacks() {
       int successfulHits = this.rollHits();
       int successfulWounds = this.rollWounds(successfulHits);
       return successfulWounds;
    }

    private int rollHits() {
        int successfulHits = 0;

        for (int i = 0; i < number * attacks; i++) {
            int roll = die.roll();

            if (roll >= this.toHit) {
                successfulHits ++;
            }
        }

        return successfulHits;
    }

    private int rollWounds(int numberOfRoll) {
        int successfulWounds = 0;

        for(int i = 0; i < numberOfRoll; i ++){
            int roll = die.roll();

            if (roll >= this.toWound ) {
                successfulWounds ++;
            }
        }

        return successfulWounds * this.damage;
    }

    public void rollSaves(int numberOfRoll, int rend) {

        for(int j = 0; j < numberOfRoll; j ++){
            int roll = die.roll();

            if (roll < this.save + rend) {
                this.number--;
            }
        }
    }

    @Override
    public Unit clone() {
        return new Unit(this.name,
        this.number,
        this.save,
        this.attacks,
        this.toHit,
        this.toWound,
        this.rend,
        this.damage);
    }

    public String getName() {
        return name;
    }

    public int getRend() {
        return rend;
    }
}
