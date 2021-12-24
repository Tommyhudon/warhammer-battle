package com.whbattle.springboot.domain.entity.TemporaryModifier.effect;

import com.whbattle.springboot.domain.entity.TemporaryModifier.TemporaryModifier;

public class Effect implements TemporaryModifier {
    private final int toHitModifier;
    private final int toWoundModifier;
    private final int braveryModifier;
    private final int saveModifier;
    private final int startTurn;
    private final int endTurn;
    private boolean active = false;

    public Effect(int toHitModifier, int toWoundModifier, int braveryModifier, int saveModifier, int startTurn, int endTurn) {
        this.toHitModifier = toHitModifier;
        this.toWoundModifier = toWoundModifier;
        this.braveryModifier = braveryModifier;
        this.saveModifier = saveModifier;
        this.startTurn = startTurn;
        this.endTurn = endTurn;
    }

    @Override
    public int getBraveryModifier() { return braveryModifier; }

    public int getToHitModifier() {
        return toHitModifier;
    }

    public int getToWoundModifier() {
        return toWoundModifier;
    }

    @Override
    public int getSaveModifier() { return saveModifier; }

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
