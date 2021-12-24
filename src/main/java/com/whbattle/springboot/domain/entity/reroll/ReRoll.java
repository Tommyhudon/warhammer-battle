package com.whbattle.springboot.domain.entity.reroll;

//value object for reRoll
public class ReRoll {
    private final boolean reRollAllFailHits;
    private final int reRollHitsOn;
    private final boolean reRollAllFailWounds;
    private final int getReRollWoundsOn;
    private final boolean reRollAllFailSaves;
    private final int reRollSavesOn;

    public ReRoll(boolean reRollAllFailHits, int reRollHitsOn, boolean reRollAllFailWounds, int getReRollWoundsOn, boolean reRollAllFailSaves, int reRollSavesOn) {
        this.reRollAllFailHits = reRollAllFailHits;
        this.reRollHitsOn = reRollHitsOn;
        this.reRollAllFailWounds = reRollAllFailWounds;
        this.getReRollWoundsOn = getReRollWoundsOn;
        this.reRollAllFailSaves = reRollAllFailSaves;
        this.reRollSavesOn = reRollSavesOn;
    }

    public boolean isReRollAllFailHits() {
        return reRollAllFailHits;
    }

    public boolean isReRollAllFailWounds() {
        return reRollAllFailWounds;
    }

    public boolean isReRollAllFailSaves() {
        return reRollAllFailSaves;
    }

    public int getReRollHitsOn() {
        return reRollHitsOn;
    }

    public int getGetReRollWoundsOn() {
        return getReRollWoundsOn;
    }

    public int getReRollSavesOn() {
        return reRollSavesOn;
    }
}
