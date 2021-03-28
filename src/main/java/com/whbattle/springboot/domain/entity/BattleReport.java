package com.whbattle.springboot.domain.entity;

import com.whbattle.springboot.domain.entity.unit.Unit;

public class BattleReport {

    public float probabilityOfWinning;
    public Unit friendlyUnit;
    public Unit enemyUnit;

    public BattleReport(float probabilityOfWinning, Unit friendlyUnit, Unit enemyUnit) {
        this.probabilityOfWinning = probabilityOfWinning;
        this.friendlyUnit = friendlyUnit;
        this.enemyUnit = enemyUnit;
    }

    public String battleReportMessage(){
        return "The probability of " +
                this.friendlyUnit.getNumber() +  " " + this.friendlyUnit.getName() +
                " to win against " +
                this.enemyUnit.getNumber() + " " + this.enemyUnit.getName() +
                " are approximately : " + this.probabilityOfWinning * 100 + "%";
    }
}
