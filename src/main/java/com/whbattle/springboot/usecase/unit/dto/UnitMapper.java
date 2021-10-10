package com.whbattle.springboot.usecase.unit.dto;

import com.whbattle.springboot.domain.entity.dice.DiceRoller;
import com.whbattle.springboot.domain.entity.dice.Die;
import com.whbattle.springboot.domain.entity.unit.Unit;
import com.whbattle.springboot.domain.entity.unit.weapon.Weapon;
import com.whbattle.springboot.usecase.weapon.dto.WeaponDto;
import com.whbattle.springboot.usecase.weapon.dto.WeaponMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnitMapper {

    @Autowired
    private final WeaponMapper weaponMapper;

    public UnitMapper(WeaponMapper weaponMapper) {
        this.weaponMapper = weaponMapper;
    }
    public Unit mapUnit(UnitDto unitDto) {
        return new Unit(
                new DiceRoller(new Die()),
                unitDto.name,
                unitDto.number,
                unitDto.save,
                unitDto.wound,
                unitDto.totalWounds,
                mapWeapon(unitDto.weapon),
                unitDto.reRoll);
    }

    public Unit[] mapUnitList(UnitDto[] unitDtos) {
        Unit[] units = new Unit[unitDtos.length];
        for(int i = 0;  i < unitDtos.length; i++) {
            units[i] = this.mapUnit(unitDtos[i]);
        }
        return units;
    }

    private Weapon mapWeapon(WeaponDto weaponDto) {
        return weaponMapper.mapWeapon(weaponDto);
    }
}
