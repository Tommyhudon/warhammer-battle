package com.whbattle.springboot.domain.entity.unit;

import com.whbattle.springboot.domain.entity.dice.DiceRoller;
import com.whbattle.springboot.domain.entity.dice.ReRoll;
import com.whbattle.springboot.domain.entity.unit.attack.Attack;

import java.util.List;

public class Unit {

    private final String name;
    private int number;
    private final int save;
    private final int wound;
    private int totalWounds;
    private final int attacks;
    private final int toHit;
    private final int toWound;
    private final int rend;
    private final int damage;
    private final int bravery;
    private final ReRoll reRoll;
    private final List<Effect> effects;
    private final DiceRoller diceRoller;
    private int modelLostDuringRound;

    public Unit(DiceRoller diceRoller, String name, int number, int save, int wound, int totalWounds, int attacks, int toHit, int toWound, int rend, int damage, int bravery, ReRoll reRoll, List<Effect> effects) {
        this.diceRoller = diceRoller;
        this.name = name;
        this.number = number;
        this.save = save;
        this.wound = wound;
        this.totalWounds = totalWounds;
        this.attacks = attacks;
        this.toHit = toHit;
        this.toWound = toWound;
        this.rend = rend;
        this.damage = damage;
        this.bravery = bravery;
        this.reRoll = reRoll;
        this.effects = effects;
        modelLostDuringRound = 0;
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

        int successfulHits = this.rollHits(number, reRoll.isReRollAllFailHits(), toHitModifier, reRoll.getReRollHitsOn());
        int successfulWounds = this.rollWounds(successfulHits, reRoll.isReRollAllFailWounds(), toWoundModifier, reRoll.getGetReRollWoundsOn());
        return new Attack(successfulWounds,this.damage, this.rend);
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

    //TODO make private
    public int rollHits(int numberOfAttacker, boolean reRoll, int rollModifier, int numberToReRoll) {
        if (numberToReRoll != 0) {
            return diceRoller.rollDiceWithSpecificReRoll(numberOfAttacker * attacks, toHit, rollModifier, numberToReRoll);
        }
        return diceRoller.rollDice(numberOfAttacker * attacks, toHit, rollModifier, reRoll);
    }

    // TODO make private
    public int rollWounds(int numberOfWounds, boolean reRoll, int rollModifier, int numberToReRoll) {
        if (numberToReRoll != 0) {
            return diceRoller.rollDiceWithSpecificReRoll(numberOfWounds, toWound, rollModifier, numberToReRoll);
        }
        return diceRoller.rollDice(numberOfWounds, toWound, rollModifier, reRoll);
    }

    private void resolvesWounds(int failedSaves, int attackDamage) {
        totalWounds = totalWounds - failedSaves * attackDamage;
        setModelLostDuringRound(number - calculateLostModel());
        number = calculateLostModel();
    }

    private int calculateLostModel() {
        return  (int) Math.ceil((float) totalWounds / wound);
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

    public void battleShock() {
        // 1 D 6 + le nb de model mort - bravery - bonus de nb
        int braveryRoll = diceRoller.rollDie(modelLostDuringRound) - bravery - (int) Math.floor((float) number/10);

        if (braveryRoll > 0) {
            if (totalWounds % wound != 0) {
                totalWounds -= totalWounds % wound;
                number --;
                braveryRoll --;
            }
            number -= braveryRoll;
        }
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
                attacks,
                toHit,
                toWound,
                rend,
                damage,
                bravery,
                reRoll,
                effects);
    }

    public void setModelLostDuringRound(int numberOfModel) {
        modelLostDuringRound = numberOfModel;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public int getTotalWounds() { return totalWounds; }

    public void setTotalWounds(int totalWounds) {
        this.totalWounds = totalWounds;
    }
}
