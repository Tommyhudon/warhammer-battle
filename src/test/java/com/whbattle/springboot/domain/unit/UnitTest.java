package com.whbattle.springboot.domain.unit;

import com.whbattle.springboot.domain.entity.dice.DiceRoller;
import com.whbattle.springboot.domain.entity.dice.ReRoll;
import com.whbattle.springboot.domain.entity.unit.Effect;
import com.whbattle.springboot.domain.entity.unit.Unit;
import com.whbattle.springboot.domain.entity.unit.attack.Attack;
import com.whbattle.springboot.domain.entity.unit.weapon.Weapon;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UnitTest {
    @Mock
    Weapon weapon;

    @Mock
    ReRoll reRoll;

    @Mock
    Attack attack;

    @Mock
    Effect effect;

    @Mock
    DiceRoller diceRoller;

    Unit DEFAULT_UNIT;

    @Before
    public void setup() {
        List<Effect> effects = Arrays.asList(effect);
        DEFAULT_UNIT = new Unit(diceRoller, "default unit", 20, 4, 4, 20, weapon, reRoll, effects);
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

    @Test
    public void whenUnitHasEffectThatEndsOnSecondTurn_shouldDeactivateEffectAtStartOfSecondTurn() {
        when(effect.getEndTurn()).thenReturn(2);

        DEFAULT_UNIT.updateEffects(1);
        DEFAULT_UNIT.updateEffects(2);

        verify(effect, times(1)).setActive(false);
    }
}
