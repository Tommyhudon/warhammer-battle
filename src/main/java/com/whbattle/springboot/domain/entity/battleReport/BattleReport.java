package com.whbattle.springboot.domain.entity.battleReport;

import com.whbattle.springboot.domain.entity.unit.Unit;

public class BattleReport {

    public float probabilityOfWinning;
    public float averageRemainingModels;
    public Unit friendlyUnit;
    public Unit enemyUnit;

    public BattleReport(float probabilityOfWinning, float averageRemainingModels, Unit friendlyUnit, Unit enemyUnit) {
        this.probabilityOfWinning = probabilityOfWinning;
        this.averageRemainingModels = averageRemainingModels;
        this.friendlyUnit = friendlyUnit;
        this.enemyUnit = enemyUnit;
    }

    public String battleReportMessage() {
        return String.format("The probability of %s winning against %s is approximately %s.\n" +
                        "On average, there are %s surviving models.",
                this.friendlyUnit.getNumber() + " " + this.friendlyUnit.getName(),
                this.enemyUnit.getNumber() + " " + this.enemyUnit.getName(),
                this.probabilityOfWinning * 100 + "%",
                Math.round(this.averageRemainingModels)
        );
    }
}
