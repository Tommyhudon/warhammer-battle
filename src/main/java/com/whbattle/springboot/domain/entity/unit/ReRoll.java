package com.whbattle.springboot.domain.entity.unit;

import com.whbattle.springboot.domain.entity.shared.Die;

public class ReRoll {
    private final boolean reRollHits;
    private final boolean reRollWounds;
    private final boolean reRollSaves;

    private final Die die = new Die();

    public ReRoll(boolean reRollHits, boolean reRollWounds, boolean reRollSaves) {
        this.reRollHits = reRollHits;
        this.reRollWounds = reRollWounds;
        this.reRollSaves = reRollSaves;
    }

    public boolean isReRollHits() {
        return reRollHits;
    }

    public boolean isReRollWounds() {
        return reRollWounds;
    }

    public boolean isReRollSaves() {
        return reRollSaves;
    }
}
