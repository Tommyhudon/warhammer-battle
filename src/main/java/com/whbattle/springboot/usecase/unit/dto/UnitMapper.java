package com.whbattle.springboot.usecase.unit.dto;

import com.whbattle.springboot.domain.entity.dice.DiceRoller;
import com.whbattle.springboot.domain.entity.dice.Die;
import com.whbattle.springboot.domain.entity.TemporaryModifier.numberModifier.NumberModifier;
import com.whbattle.springboot.domain.entity.TemporaryModifier.effect.Effect;
import com.whbattle.springboot.domain.entity.unit.Unit;
import com.whbattle.springboot.usecase.effect.dto.dto.EffectDto;
import com.whbattle.springboot.usecase.effect.dto.dto.EffectMapper;
import com.whbattle.springboot.usecase.numberModifier.dto.NumberModifierDto;
import com.whbattle.springboot.usecase.numberModifier.dto.NumberModifierMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UnitMapper {

    @Autowired
    private final EffectMapper effectMapper;

    @Autowired
    private final NumberModifierMapper numberModifierMapper;

    public UnitMapper(EffectMapper effectMapper, NumberModifierMapper numberModifierMapper) {
        this.effectMapper = effectMapper;
        this.numberModifierMapper = numberModifierMapper;
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
                mapEffectList(unitDto.effects),
                mapNumberBonusList(unitDto.numberModifier));
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

    public List<NumberModifier> mapNumberBonusList(NumberModifierDto[] numberBonusesDtos) { return numberModifierMapper.mapNumberBonusList(numberBonusesDtos); }
}
