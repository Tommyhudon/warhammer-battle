package com.whbattle.springboot.domain.entity.unit;

import com.whbattle.springboot.domain.entity.unit.weapon.Weapon;
import com.whbattle.springboot.domain.entity.unit.attack.Attack;

public class Unit {

    private final String name;
    private int number;
    private final int save;
    private final int wound;
    private int totalWounds;
    private final Weapon weapon;
    private final Abilities abilities;

    private final DiceRoller diceRoller;

    public Unit(DiceRoller diceRoller, String name, int number, int save, int wound, int totalWounds, Weapon weapon, Abilities abilities) {
        this.diceRoller = diceRoller;
        this.name = name;
        this.number = number;
        this.save = save;
        this.wound = wound;
        this.totalWounds = totalWounds;
        this.weapon = weapon;
        this.abilities = abilities;
    }

    public boolean isDead() {
        return number <= 0;
    }

    public Attack rollAttacks() {
        int ZERO_FOR_NOW = 0;
        int successfulHits = weapon.rollHits(number, abilities.isReRollAllFailHits(), ZERO_FOR_NOW, abilities.getReRollHitsOn());
        int successfulWounds = weapon.rollWounds(successfulHits, abilities.isReRollAllFailWounds(), ZERO_FOR_NOW, abilities.getGetReRollWoundsOn());
        return new Attack(successfulWounds, weapon.getDamage(), weapon.getRend());
    }

    public void resolveDamage(Attack attack) {
        int successfulSaves = rollSaves(attack.getSuccessfulAttacks(), attack.getRend());
        this.resolvesWounds(attack.getSuccessfulAttacks() - successfulSaves, attack.getDamagePerAttack());
    }

    private int rollSaves(int numberOfSaves, int rend) {
        if (abilities.getReRollSavesOn() != 0) {
            return diceRoller.rollDiceWithSpecificReRoll(numberOfSaves, save, rend, abilities.getReRollSavesOn());
        }
        return diceRoller.rollDice(numberOfSaves, save, rend, abilities.isReRollAllFailSaves());
    }

    private void resolvesWounds(int failedSaves, int attackDamage) {
        totalWounds = totalWounds - failedSaves * attackDamage;
        number = (int) Math.ceil( (float) totalWounds/wound);
    }

    @Override
    public Unit clone() {
        return new Unit(
                diceRoller,
                name,
                number,
                save,
                wound,
                totalWounds,
                weapon,
                abilities);
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }
}
