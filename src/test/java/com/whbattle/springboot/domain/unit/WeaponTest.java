package com.whbattle.springboot.domain.unit;

import com.whbattle.springboot.domain.entity.dice.DiceRoller;
import com.whbattle.springboot.domain.entity.unit.weapon.Weapon;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class WeaponTest {

    Weapon DEFAULT_WEAPON;
    Weapon MULTIPLE_ATTACK_WEAPON;

    @Mock
    DiceRoller diceRoller;

    @Before
    public void setup() {
        DEFAULT_WEAPON = new Weapon(diceRoller, 1, 4, 4, 0, 1);
        MULTIPLE_ATTACK_WEAPON = new Weapon(diceRoller,2, 4, 4, 0 , 1);
    }

    @Test
    public void whenRollingToHitWithNoSpecificReRoll_shouldJustRollDice() {
        DEFAULT_WEAPON.rollHits(1, false, 0, 0);

        verify(diceRoller, times(1)).rollDice(1, 4, 0, false);
    }

    @Test
    public void whenRollingToHitWithSpecificReRoll_shouldReRollSpecificNumber() {
        DEFAULT_WEAPON.rollHits(1, false, 0, 1);

        verify(diceRoller, times(1)).rollDiceWithSpecificReRoll(1, 4, 0, 1);
    }

    @Test
    public void whenRollingToWoundWithNoSpecificReRoll_shouldJustRollDice() {
        DEFAULT_WEAPON.rollHits(1, false, 0, 0);

        verify(diceRoller, times(1)).rollDice(1, 4, 0, false);
    }

    @Test
    public void whenRollingToWoundWithSpecificReRoll_shouldReRollSpecificNumber() {
        DEFAULT_WEAPON.rollHits(1, false, 0, 1);

        verify(diceRoller, times(1)).rollDiceWithSpecificReRoll(1, 4, 0, 1);
    }

    @Test
    public void whenRollingToHitWithMultipleAttacks_shouldRollMoreDice() {
        MULTIPLE_ATTACK_WEAPON.rollHits(1, false, 0, 0);

        verify(diceRoller, times(1)).rollDice(2, 4, 0, false);
    }
}
