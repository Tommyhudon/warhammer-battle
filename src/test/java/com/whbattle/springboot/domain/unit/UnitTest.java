package com.whbattle.springboot.domain.unit;

import com.whbattle.springboot.domain.entity.dice.DiceRoller;
import com.whbattle.springboot.domain.entity.dice.ReRoll;
import com.whbattle.springboot.domain.entity.unit.Effect;
import com.whbattle.springboot.domain.entity.unit.Unit;
import com.whbattle.springboot.domain.entity.unit.attack.Attack;
import com.whbattle.springboot.domain.entity.unit.weapon.Weapon;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
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
    Unit MULTIPLE_ATTACK_UNIT;
    Unit MULTIPLE_WOUND_UNIT;

    @Before
    public void setup() {
        List<Effect> effects = Collections.singletonList(effect);
        DEFAULT_UNIT = new Unit(diceRoller, "default unit", 20, 4, 1, 20, 1, 4, 4, 0, 1, 6, reRoll, effects);
        MULTIPLE_ATTACK_UNIT = new Unit(diceRoller, "default unit", 20, 4, 1, 20, 2, 4, 4, 0, 1, 6, reRoll, effects);
        MULTIPLE_WOUND_UNIT = new Unit(diceRoller, "default unit", 5, 4, 4, 20, 2, 4, 4, 0, 1, 6, reRoll, effects);

    }

    @Test
    public void whenUnitRollHits_shouldRollNumberOfAttacksEqualToNumberOfUnit() {
        DEFAULT_UNIT.rollAttacks();

        verify(diceRoller, times(1)).rollDice(20, 4, 0, false);
    }

    @Test
    public void whenUnitRollWounds_shouldRollNumberOfWoundsEqualToNumberOfSuccessfulAttack() {
        when(diceRoller.rollDice(20, 4, 0, false)).thenReturn(13);

        DEFAULT_UNIT.rollAttacks();

        verify(diceRoller, times(1)).rollDice(13, 4, 0, false);
    }

    @Test
    public void whenUnitHasEffectThatEndsOnSecondTurn_shouldDeactivateEffectAtStartOfSecondTurn() {
        when(effect.getEndTurn()).thenReturn(2);

        DEFAULT_UNIT.updateEffects(1);
        DEFAULT_UNIT.updateEffects(2);

        verify(effect, times(1)).setActive(false);
    }

    @Test
    public void GivenUnitWithBravery6And20ModelsThatLost2Models_whenDoingBattleShockTestWithResultOf8_ShouldLost1MoreModels() {
        when(diceRoller.rollDie(2)).thenReturn(8);

        DEFAULT_UNIT.setModelLostDuringRound(2);
        DEFAULT_UNIT.setNumber(18);
        DEFAULT_UNIT.setTotalWounds(18);

        DEFAULT_UNIT.battleShock();

        Assert.assertEquals(DEFAULT_UNIT.getNumber(), 17);
    }

    @Test
    public void GivenUnitWithModelWith4Wounds_whenFailingBraveryTestByOne_ShouldLose4totalWoundAnd1Number() {
        when(diceRoller.rollDie(1)).thenReturn(7);

        MULTIPLE_WOUND_UNIT.setModelLostDuringRound(1);
        MULTIPLE_WOUND_UNIT.setTotalWounds(19);

        MULTIPLE_WOUND_UNIT.battleShock();

        Assert.assertEquals(MULTIPLE_WOUND_UNIT.getNumber(), 4);
        Assert.assertEquals(MULTIPLE_WOUND_UNIT.getTotalWounds(), 16);
    }

    @Test
    public void GivenUnitWithModelWith4WoundsAnd17woundsLeft_whenFailingBraveryTestByOne_ShouldLose1totalWoundAnd1Number() {
        when(diceRoller.rollDie(1)).thenReturn(7);

        MULTIPLE_WOUND_UNIT.setModelLostDuringRound(1);
        MULTIPLE_WOUND_UNIT.setTotalWounds(17);

        MULTIPLE_WOUND_UNIT.battleShock();

        Assert.assertEquals(MULTIPLE_WOUND_UNIT.getNumber(), 4);
        Assert.assertEquals(MULTIPLE_WOUND_UNIT.getTotalWounds(), 16);
    }

    @Test
    public void whenRollingToHitWithNoSpecificReRoll_shouldJustRollDice() {
        DEFAULT_UNIT.rollHits(1, false, 0, 0);

        verify(diceRoller, times(1)).rollDice(1, 4, 0, false);
    }

    @Test
    public void whenRollingToHitWithSpecificReRoll_shouldReRollSpecificNumber() {
        DEFAULT_UNIT.rollHits(1, false, 0, 1);

        verify(diceRoller, times(1)).rollDiceWithSpecificReRoll(1, 4, 0, 1);
    }

    @Test
    public void whenRollingToWoundWithNoSpecificReRoll_shouldJustRollDice() {
        DEFAULT_UNIT.rollHits(1, false, 0, 0);

        verify(diceRoller, times(1)).rollDice(1, 4, 0, false);
    }

    @Test
    public void whenRollingToWoundWithSpecificReRoll_shouldReRollSpecificNumber() {
        DEFAULT_UNIT.rollHits(1, false, 0, 1);

        verify(diceRoller, times(1)).rollDiceWithSpecificReRoll(1, 4, 0, 1);
    }

    @Test
    public void whenRollingToHitWithMultipleAttacks_shouldRollMoreDice() {
        MULTIPLE_ATTACK_UNIT.rollHits(1, false, 0, 0);

        verify(diceRoller, times(1)).rollDice(2, 4, 0, false);
    }
}
