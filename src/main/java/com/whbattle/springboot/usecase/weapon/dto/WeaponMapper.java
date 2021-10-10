package com.whbattle.springboot.usecase.weapon.dto;

import com.whbattle.springboot.domain.entity.dice.DiceRoller;
import com.whbattle.springboot.domain.entity.dice.Die;
import com.whbattle.springboot.domain.entity.unit.weapon.Weapon;
import org.springframework.stereotype.Component;

@Component
public class WeaponMapper {
    public Weapon mapWeapon(WeaponDto weaponDto) {
        return new Weapon(
                new DiceRoller(new Die()),
                weaponDto.attacks,
                weaponDto.toHit,
                weaponDto.toWound,
                weaponDto.rend,
                weaponDto.damage);
    }
}
