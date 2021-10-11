package com.whbattle.springboot.domain.entity.unit;

public class Effect {
    private final int toHitModifier;
    private final int toWoundModifier;
    private final int startTurn;
    private final int endTurn;
    private boolean active = false;

    public Effect(int toHitModifier, int toWoundModifier, int startTurn, int endTurn) {
        this.toHitModifier = toHitModifier;
        this.toWoundModifier = toWoundModifier;
        this.startTurn = startTurn;
        this.endTurn = endTurn;
    }

    public int getToHitModifier() {
        return toHitModifier;
    }

    public int getToWoundModifier() {
        return toWoundModifier;
    }

    public int getStartTurn() {
        return startTurn;
    }

    public int getEndTurn() {
        return endTurn;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
