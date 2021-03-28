package com.whbattle.springboot.domain.entity.unit;

import com.whbattle.springboot.domain.entity.shared.Die;
import com.whbattle.springboot.domain.entity.unit.weapon.Weapon;
import com.whbattle.springboot.domain.entity.unit.attack.Attack;

public class Unit {

    private final String name;
    private int number;
    private final int save;
    private final int wound;
    private int totalWounds;
    private final Weapon weapon;
    private final ReRoll reRoll;

    private final Die die = new Die();

    public Unit(String name, int number, int save, int wound, int totalWounds, Weapon weapon, ReRoll reRoll) {
        this.name = name;
        this.number = number;
        this.save = save;
        this.wound = wound;
        this.totalWounds = totalWounds;
        this.weapon = weapon;
        this.reRoll = reRoll;
    }

    public boolean isDead() {
        return number <= 0;
    }

    public Attack rollAttacks() {
        int successfulHits = this.weapon.rollHits(this.number,this.reRoll.isReRollHits());
        int successfulWounds = this.weapon.rollWounds(successfulHits, this.reRoll.isReRollWounds());
        return new Attack(successfulWounds, this.weapon.getDamage(), this.weapon.getRend());
    }

    public void resolveDamage(Attack attack) {
        int failedSaves = this.rollSaves(attack.getSuccessfulAttacks(), attack.getRend());
        this.resolvesWounds(failedSaves, attack.getDamagePerAttack());
    }

    private int rollSaves(int numberOfRoll, int rend) {
        int failedSaves = 0;

        for(int j = 0; j < numberOfRoll; j ++){
            int roll = die.roll();

            if (roll < this.save + rend) {
                failedSaves++;
            } else if (this.reRoll.isReRollSaves()){
                roll = die.roll();

                if (roll < this.save + rend) {
                    failedSaves++;
                }
            }
        }

        return failedSaves;
    }

    private void resolvesWounds(int failedSaves, int attackDamage) {
        this.totalWounds = this.totalWounds - failedSaves * attackDamage;
        this.number = (int) Math.ceil( (float) totalWounds/wound);
    }

    @Override
    public Unit clone() {
        return new Unit(
                this.name,
                this.number,
                this.save,
                this.wound,
                this.totalWounds,
                this.weapon,
                this.reRoll);
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }
}
