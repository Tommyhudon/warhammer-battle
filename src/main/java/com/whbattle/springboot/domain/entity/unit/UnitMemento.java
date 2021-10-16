package com.whbattle.springboot.domain.entity.unit;

import com.whbattle.springboot.domain.entity.dice.ReRoll;

import java.util.List;

public class UnitMemento {

    private String name;
    private int number;
    private int save;
    private int wound;
    private int totalWounds;
    private int attacks;
    private int toHit;
    private int toWound;
    private int rend;
    private int damage;
    private int bravery;
    private ReRoll reRoll;
    private List<Effect> effects;
    private int modelLostDuringRound;

    public UnitMemento(String name, int number, int save, int wound, int totalWounds, int attacks, int toHit, int toWound, int rend, int damage, int bravery, ReRoll reRoll, List<Effect> effects, int modelLostDuringRound) {
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
        this.modelLostDuringRound = modelLostDuringRound;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public int getSave() {
        return save;
    }

    public int getWound() {
        return wound;
    }

    public int getTotalWounds() {
        return totalWounds;
    }

    public int getAttacks() {
        return attacks;
    }

    public int getToHit() {
        return toHit;
    }

    public int getToWound() {
        return toWound;
    }

    public int getRend() {
        return rend;
    }

    public int getDamage() {
        return damage;
    }

    public int getBravery() {
        return bravery;
    }

    public ReRoll getReRoll() {
        return reRoll;
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public int getModelLostDuringRound() {
        return modelLostDuringRound;
    }
}
