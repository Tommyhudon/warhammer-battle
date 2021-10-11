package com.whbattle.springboot.domain.entity.unit;

import com.whbattle.springboot.domain.entity.dice.DiceRoller;
import com.whbattle.springboot.domain.entity.dice.ReRoll;
import com.whbattle.springboot.domain.entity.unit.attack.Attack;
import com.whbattle.springboot.domain.entity.unit.weapon.Weapon;

import java.util.List;

public class Unit {

    private final String name;
    private int number;
    private final int save;
    private final int wound;
    private int totalWounds;
    private final Weapon weapon;
    private final ReRoll reRoll;
    private final List<Effect> effects;

    private final DiceRoller diceRoller;

    public Unit(DiceRoller diceRoller, String name, int number, int save, int wound, int totalWounds, Weapon weapon, ReRoll reRoll, List<Effect> effects) {
        this.diceRoller = diceRoller;
        this.name = name;
        this.number = number;
        this.save = save;
        this.wound = wound;
        this.totalWounds = totalWounds;
        this.weapon = weapon;
        this.reRoll = reRoll;
        this.effects = effects;
    }

    public boolean isDead() {
        return number <= 0;
    }

    public Attack rollAttacks() {
        int toHitModifier = 0;
        int toWoundModifier = 0;

        for (Effect effect : this.effects) {
            if (effect.isActive()) {
                toHitModifier += effect.getToHitModifier();
                toWoundModifier += effect.getToWoundModifier();
            }
        }

        int successfulHits = weapon.rollHits(number, reRoll.isReRollAllFailHits(), toHitModifier, reRoll.getReRollHitsOn());
        int successfulWounds = weapon.rollWounds(successfulHits, reRoll.isReRollAllFailWounds(), toWoundModifier, reRoll.getGetReRollWoundsOn());
        return new Attack(successfulWounds, weapon.getDamage(), weapon.getRend());
    }

    public void resolveDamage(Attack attack) {
        int successfulSaves = rollSaves(attack.getSuccessfulAttacks(), attack.getRend());
        this.resolvesWounds(attack.getSuccessfulAttacks() - successfulSaves, attack.getDamagePerAttack());
    }

    private int rollSaves(int numberOfSaves, int rend) {
        if (reRoll.getReRollSavesOn() != 0) {
            return diceRoller.rollDiceWithSpecificReRoll(numberOfSaves, save, rend, reRoll.getReRollSavesOn());
        }
        return diceRoller.rollDice(numberOfSaves, save, rend, reRoll.isReRollAllFailSaves());
    }

    private void resolvesWounds(int failedSaves, int attackDamage) {
        totalWounds = totalWounds - failedSaves * attackDamage;
        number = (int) Math.ceil((float) totalWounds / wound);
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
                reRoll,
                effects);
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public void updateEffects(int turn) {
        for (Effect effect : this.effects) {
            if (effect.getStartTurn() == turn) {
                effect.setActive(true);
            }
            if (effect.getEndTurn() == turn) {
                effect.setActive(false);
            }
        }
    }
}
