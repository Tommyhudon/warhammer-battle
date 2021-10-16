package com.whbattle.springboot.usecase.battleReport;

import com.whbattle.springboot.domain.entity.battleReport.BattleReport;
import com.whbattle.springboot.domain.entity.unit.Unit;
import com.whbattle.springboot.domain.entity.unit.UnitMemento;
import com.whbattle.springboot.domain.entity.unit.attack.Attack;
import com.whbattle.springboot.usecase.unit.dto.UnitDto;
import com.whbattle.springboot.usecase.unit.dto.UnitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
public class CreateBattleReport {

    private final UnitMapper unitMapper;
    private List<UnitMemento> initialUnitsState = new ArrayList<>();

    @Autowired
    public CreateBattleReport(UnitMapper unitMapper) {
        this.unitMapper = unitMapper;
    }

    public BattleReport create(UnitDto[] unitDtos) {
        int nbOfWin = 0;
        int nbOfSurvivors = 0;
        int NUMBER_OF_BATTLE = 10000;
        String mainUnitName = unitDtos[0].name;
        List<Unit> units = unitMapper.mapUnitList(unitDtos);
        saveUnitsState(units);

        for (int i = 0; i < NUMBER_OF_BATTLE; i++) {
            Collections.shuffle(units, new Random());

            Unit winner = battle(units);
            if (winner.getName().equals(mainUnitName)) {
                nbOfWin++;
                nbOfSurvivors += winner.getNumber();
            }
            restoreUnitsState(units);
        }

        float probability = (float) nbOfWin / NUMBER_OF_BATTLE;
        float averageSurvivingModels = (float) nbOfSurvivors / NUMBER_OF_BATTLE;
        return new BattleReport(probability, averageSurvivingModels, units.get(0), units.get(1));
    }

    public Unit battle(List<Unit> units) {
        Unit winner = null;
        int currentTurn = 0;

        Unit unit1 = units.get(0);
        Unit unit2 = units.get(1);

        while (units.size() >= 2) {

            currentTurn++;

            unit1.updateEffects(currentTurn);
            unit2.updateEffects(currentTurn);

            Attack attack = unit1.rollAttacks();
            unit2.resolveDamage(attack);

            if (unit2.isDead()) {
                winner = unit1;
                break;
            }

            attack = unit2.rollAttacks();
            unit1.resolveDamage(attack);

            if (unit1.isDead()) {
                winner = unit2;
                break;
            }

            unit1.battleShock();
            unit2.battleShock();
        }

        return winner;
    }

    private void saveUnitsState(List<Unit> units) {
        List<UnitMemento> currentBattleInitialState = new ArrayList<>();
        for (Unit unit : units) {
            currentBattleInitialState.add(unit.saveState());
        }
        initialUnitsState = currentBattleInitialState;
    }

    private void restoreUnitsState(List<Unit> units) {
        for (int i = 0; i < units.size(); i++) {
            units.get(i).restoreState(initialUnitsState.get(i));
        }
    }
}
