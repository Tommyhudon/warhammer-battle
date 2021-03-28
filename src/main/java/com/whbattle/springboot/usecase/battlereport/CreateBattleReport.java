package com.whbattle.springboot.usecase.battlereport;

import com.whbattle.springboot.domain.entity.BattleReport;
import com.whbattle.springboot.domain.entity.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateBattleReport {

    @Autowired
    public CreateBattleReport(){}

    public BattleReport create(Unit[] units) {
        int nbOfWin = 0;
        int NUMBER_OF_BATTLE = 1000;

        for (int i = 0; i < NUMBER_OF_BATTLE; i++) {

            if (this.battle(this.cloneUnits(units)).getName().equals(units[0].getName())) {
                nbOfWin++;
            }
        }
        float probability = (float) nbOfWin/NUMBER_OF_BATTLE;
        return new BattleReport(probability);
    }

    private Unit battle(List<Unit> units) {
        Unit winner = null;

        while(units.size() >= 2) {
            int successfulAttacks = units.get(0).rollAttacks();
            units.get(1).rollSaves(successfulAttacks, units.get(0).getRend());

            if (units.get(0).isDead()) {
                winner = units.get(1);
                break;
            }

            successfulAttacks = units.get(1).rollAttacks();
            units.get(0).rollSaves(successfulAttacks, units.get(1).getRend());

            if (units.get(1).isDead()) {
                winner = units.get(0);
                break;
            }
        }

        return winner;
    }

    private List<Unit> cloneUnits(Unit[] units) {
        List<Unit> resultList = new ArrayList<>();

        for(Unit unit: units) {
            resultList.add(unit.clone());
        }

        return resultList;
    }
}
