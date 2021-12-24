package com.whbattle.springboot.domain.entity.TemporaryModifier.numberModifier;

import com.whbattle.springboot.domain.entity.TemporaryModifier.TemporaryModifier;

public class NumberModifier implements TemporaryModifier {
    private final int minimumNumberForModifier;
    private final int braveryModifier;
    private final int toHitModifier;
    private final int toWoundModifier;
    private final int saveModifier;

    public NumberModifier(int minimumNumberForModifier, int braveryBonus, int toHitModifier, int toWoundModifier, int saveModifier) {
        this.minimumNumberForModifier = minimumNumberForModifier;
        this.braveryModifier = braveryBonus;
        this.toHitModifier = toHitModifier;
        this.toWoundModifier = toWoundModifier;
        this.saveModifier = saveModifier;
    }

    public int getMinimumNumberForModifier() {
        return minimumNumberForModifier;
    }

    public int getBraveryModifier() {
        return braveryModifier;
    }

    public int getToHitModifier() {
        return toHitModifier;
    }

    public int getToWoundModifier() {
        return toWoundModifier;
    }

    public int getSaveModifier() {
        return saveModifier;
    }
}
