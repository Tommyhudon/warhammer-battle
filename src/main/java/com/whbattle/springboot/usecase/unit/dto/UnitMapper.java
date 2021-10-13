package com.whbattle.springboot.usecase.unit.dto;

import com.whbattle.springboot.domain.entity.dice.DiceRoller;
import com.whbattle.springboot.domain.entity.dice.Die;
import com.whbattle.springboot.domain.entity.unit.Effect;
import com.whbattle.springboot.domain.entity.unit.Unit;
import com.whbattle.springboot.usecase.effect.dto.dto.EffectDto;
import com.whbattle.springboot.usecase.effect.dto.dto.EffectMapper;
import com.whbattle.springboot.usecase.weapon.dto.WeaponMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UnitMapper {

    @Autowired
    private final WeaponMapper weaponMapper;

    @Autowired
    private final EffectMapper effectMapper;

    public UnitMapper(WeaponMapper weaponMapper, EffectMapper effectMapper) {
        this.weaponMapper = weaponMapper;
        this.effectMapper = effectMapper;
    }

    public Unit mapUnit(UnitDto unitDto) {
        return new Unit(
                new DiceRoller(new Die()),
                unitDto.name,
                unitDto.number,
                unitDto.save,
                unitDto.wound,
                unitDto.totalWounds,
                unitDto.attacks,
                unitDto.toHit,
                unitDto.toWound,
                unitDto.rend,
                unitDto.damage,
                unitDto.bravery,
                unitDto.reRoll,
                mapEffectList(unitDto.effects));
    }

    public Unit[] mapUnitList(UnitDto[] unitDtos) {
        Unit[] units = new Unit[unitDtos.length];
        for (int i = 0; i < unitDtos.length; i++) {
            units[i] = this.mapUnit(unitDtos[i]);
        }
        return units;
    }

    public List<Effect> mapEffectList(EffectDto[] effectDtos) {
        return effectMapper.mapEffectList(effectDtos);
    }
}
