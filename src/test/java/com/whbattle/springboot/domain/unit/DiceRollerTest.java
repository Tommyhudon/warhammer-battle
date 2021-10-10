package com.whbattle.springboot.domain.unit;

import com.whbattle.springboot.domain.entity.dice.Die;
import com.whbattle.springboot.domain.entity.dice.DiceRoller;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DiceRollerTest {

    @Mock
    Die die;

    DiceRoller diceRoller;

    @Before
    public void setup(){
        diceRoller = new DiceRoller(die);
    }

    @Test
    public void whenRollingHigherOrEqualThanNumberToRoll_shouldReturnOneSuccessfulRoll() {
        when(die.roll()).thenReturn(4);

        int RESULT_SHOULD_BE_ONE = diceRoller.rollDice(1, 4, 0, false);

        Assert.assertEquals(RESULT_SHOULD_BE_ONE, 1);
    }

    @Test
    public void whenRollingLowerThanNumberToRoll_shouldReturnZeroSuccessfulRoll() {
        when(die.roll()).thenReturn(3);

        int RESULT_SHOULD_BE_ZERO = diceRoller.rollDice(1, 4, 0, false);

        Assert.assertEquals(RESULT_SHOULD_BE_ZERO, 0);
    }

    @Test
    public void whenRollingHigherOrEqualThanNumberToRollWithReRoll_shouldRollOnce() {
        when(die.roll()).thenReturn(4);

        diceRoller.rollDice(1, 4, 0, true);

        verify(die, times(1)).roll();
    }

    @Test
    public void whenRollingLowerThanNumberToRollWithReRoll_shouldRollTwice() {
        when(die.roll()).thenReturn(3);

        diceRoller.rollDice(1, 4, 0, true);

        verify(die, times(2)).roll();
    }

    @Test
    public void whenRollingLowerThanNumberToRollWithModifier_shouldReturnOneSuccessfulRoll() {
        when(die.roll()).thenReturn(3);

        int RESULT_SHOULD_BE_ONE = diceRoller.rollDice(1, 4, 1, false);

        Assert.assertEquals(RESULT_SHOULD_BE_ONE, 1);
    }

    @Test
    public void whenRollingLowerThanNumberToRollWithModifierAndReRoll_shouldRollOnce() {
        when(die.roll()).thenReturn(3);

        diceRoller.rollDice(1, 4, 1, true);

        verify(die, times(1)).roll();
    }

    @Test
    public void whenRollingEqualOrHigherThanNumberToRollWithTheReRoll_shouldReturnOne() {
        when(die.roll()).thenReturn(2, 3);

        int RESULT_SHOULD_BE_ONE = diceRoller.rollDice(1, 4, 1, true);

        verify(die, times(2)).roll();
        Assert.assertEquals(RESULT_SHOULD_BE_ONE, 1);
    }


    @Test
    public void whenRollingWithSpecificNumberToReRoll_shouldReRollOnSpecificNumber() {
        when(die.roll()).thenReturn(1 );

        diceRoller.rollDiceWithSpecificReRoll(1, 4, 0, 1);

        verify(die, times(2)).roll();
    }

    @Test
    public void whenRollingWithSpecificNumberToReRoll_shouldReturnOneIfReRollSuccessful() {
        when(die.roll()).thenReturn(1, 4);

        int RESULT_SHOULD_BE_ONE = diceRoller.rollDiceWithSpecificReRoll(1, 4, 0, 1);

        Assert.assertEquals(RESULT_SHOULD_BE_ONE, 1);
    }

    @Test
    public void whenRollingWithSpecificNumberToReRollAndModifier_shouldReturnOneIfReRollSuccessfulWithModifier() {
        when(die.roll()).thenReturn(1, 3);

        int RESULT_SHOULD_BE_ONE = diceRoller.rollDiceWithSpecificReRoll(1, 4, 1, 1);

        Assert.assertEquals(RESULT_SHOULD_BE_ONE, 1);
    }
}
