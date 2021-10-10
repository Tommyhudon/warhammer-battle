package com.whbattle.springboot.usecase.battlereport;

import com.whbattle.springboot.domain.entity.battleReport.BattleReport;
import com.whbattle.springboot.domain.entity.unit.Unit;
import com.whbattle.springboot.domain.entity.unit.attack.Attack;
import com.whbattle.springboot.usecase.unit.dto.UnitDto;
import com.whbattle.springboot.usecase.unit.dto.UnitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CreateBattleReport {

    private final UnitMapper unitMapper;

    @Autowired
    public CreateBattleReport(UnitMapper unitMapper){
        this.unitMapper = unitMapper;
    }

    public BattleReport create(UnitDto[] unitDtos) {
        int nbOfWin = 0;
        int NUMBER_OF_BATTLE = 10000;
        Unit[] units = unitMapper.mapUnitList(unitDtos);


        for (int i = 0; i < NUMBER_OF_BATTLE; i++) {
            List<Unit> shuffledList = this.cloneUnits(units);
            Collections.shuffle(shuffledList, new Random());

            Unit winner = this.battle(shuffledList);
            if (winner.getName().equals(units[0].getName())) {
                nbOfWin++;
            }
        }

        float probability = (float) nbOfWin/NUMBER_OF_BATTLE;
        return new BattleReport(probability, units[0], units[1]);
    }

    public Unit battle(List<Unit> units) {
        Unit winner = null;

        while(units.size() >= 2) {
            Attack attack = units.get(0).rollAttacks();
            units.get(1).resolveDamage(attack);

            if (units.get(1).isDead()) {
                winner = units.get(0);
                break;
            }

            attack = units.get(1).rollAttacks();
            units.get(0).resolveDamage(attack);

            if (units.get(0).isDead()) {
                winner = units.get(1);
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
