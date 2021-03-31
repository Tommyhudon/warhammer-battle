package com.whbattle.springboot.domain.unit;

import com.whbattle.springboot.domain.entity.unit.Abilities;
import com.whbattle.springboot.domain.entity.unit.DiceRoller;
import com.whbattle.springboot.domain.entity.unit.Unit;
import com.whbattle.springboot.domain.entity.unit.attack.Attack;
import com.whbattle.springboot.domain.entity.unit.weapon.Weapon;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UnitTest {



    @Mock
    Weapon weapon;

    @Mock
    Abilities abilities;

    @Mock
    Attack attack;

    @Mock
    DiceRoller diceRoller;

    Unit DEFAULT_UNIT;

    @Before
    public void setup() {
       DEFAULT_UNIT = new Unit(diceRoller,"default unit", 20, 4, 4, 20, weapon, abilities);
    }

    @Test
    public void whenUnitRollHits_shouldRollNumberOfAttacksEqualToNumberOfUnit() {
        DEFAULT_UNIT.rollAttacks();

        verify(weapon, times(1)).rollHits(20, false, 0, 0);
    }

    @Test
    public void whenUnitRollWounds_shouldRollNumberOfWoundsEqualToNumberOfSuccessfulAttack() {
        when(weapon.rollHits(20, false, 0, 0)).thenReturn(13);

        DEFAULT_UNIT.rollAttacks();

        verify(weapon, times(1)).rollWounds(13, false, 0, 0);
    }


}
