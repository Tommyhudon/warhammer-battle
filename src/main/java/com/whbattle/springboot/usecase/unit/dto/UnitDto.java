package com.whbattle.springboot.usecase.unit.dto;

import com.whbattle.springboot.domain.entity.reroll.ReRoll;
import com.whbattle.springboot.usecase.effect.dto.dto.EffectDto;
import com.whbattle.springboot.usecase.numberModifier.dto.NumberModifierDto;

public class UnitDto {
    public String name;
    public int number;
    public int save;
    public int wound;
    public int totalWounds;
    public int attacks;
    public int toHit;
    public int toWound;
    public int rend;
    public int damage;
    public int bravery;
    public ReRoll reRoll;
    public EffectDto[] effects;
    public NumberModifierDto[] numberModifier;
}
