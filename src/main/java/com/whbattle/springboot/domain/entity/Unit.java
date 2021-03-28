package com.whbattle.springboot.domain.entity;

public class Unit {

    private final String name;
    private int number;
    private final int save;
    private final int wound;

    //objet pour arme ?
    private final int attacks;
    private final int toHit;
    private final int toWound;
    private final int rend;
    private final int damage;

    private int totalWounds;

    private final Die die = new Die();

    public Unit(String name, int number, int save, int wound, int attacks, int toHit, int toWound, int rend, int damage, int totalWounds) {
        this.name = name;
        this.number = number;
        this.save = save;
        this.wound = wound;
        this.attacks = attacks;
        this.toHit = toHit;
        this.toWound = toWound;
        this.rend = rend;
        this.damage = damage;
        this.totalWounds = totalWounds;
    }

    public boolean isDead() {
        return number <= 0;
    }

    public int rollAttacks() {
       int successfulHits = this.rollHits();
       return this.rollWounds(successfulHits);
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

    public void resolveDamage(int numberOfAttack, int rend, int attackDamage) {
        int failedSaves = this.rollSaves(numberOfAttack, rend);
        this.resolvesWounds(failedSaves, attackDamage);
    }

    private int rollSaves(int numberOfRoll, int rend) {
        int failedSaves = 0;

        for(int j = 0; j < numberOfRoll; j ++){
            int roll = die.roll();

            if (roll < this.save + rend) {
                failedSaves++;
            }
        }

        return failedSaves;
    }

    private void resolvesWounds(int failedSaves, int attackDamage) {
        int numberOfDamage = failedSaves * attackDamage;
        this.totalWounds = this.totalWounds - numberOfDamage;

        this.number = this.number - numberOfDamage/this.wound;
    }

    @Override
    public Unit clone() {
        return new Unit(
                this.name,
                this.number,
                this.save,
                this.wound,
                this.attacks,
                this.toHit,
                this.toWound,
                this.rend,
                this.damage,
                this.totalWounds);
    }

    public String getName() {
        return name;
    }

    public int getRend() {
        return rend;
    }

    public int getDamage() { return damage; }

    public int getNumber() {
        return number;
    }
}
