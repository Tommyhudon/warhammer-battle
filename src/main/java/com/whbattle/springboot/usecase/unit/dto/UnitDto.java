package com.whbattle.springboot.usecase.unit.dto;

import com.whbattle.springboot.domain.entity.dice.ReRoll;
import com.whbattle.springboot.usecase.weapon.dto.WeaponDto;

public class UnitDto {
        public String name;
        public int number;
        public int save;
        public int wound;
        public int totalWounds;
        public WeaponDto weapon;
        public ReRoll reRoll;
}
