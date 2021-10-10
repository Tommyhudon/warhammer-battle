package com.whbattle.springboot.domain.entity.dice;

import com.whbattle.springboot.domain.entity.dice.Die;

public class DiceRoller {

    private final Die die;

    public DiceRoller(Die die){
        this.die = die;
    }

    //RollDice with  reRoll
    public int rollDice(int numberOfRoll, int numberToRoll, int rollModifier, boolean reRoll) {
        int successfulRolls = 0;

        for (int i = 0; i < numberOfRoll; i ++){
            int roll = die.roll();

            if (roll + rollModifier >= numberToRoll) {
                successfulRolls ++;
            } else if (reRoll) {
                successfulRolls = successfulRolls + this.rollDice(1, numberToRoll, rollModifier, false);
            }
        }

        return successfulRolls;
    }

    //RollDice with reRoll on specific number
    public int rollDiceWithSpecificReRoll(int numberOfRoll, int numberToRoll, int rollModifier, int numberToReRoll) {
        int successfulRolls = 0;

        for (int i = 0; i < numberOfRoll; i ++){
            int roll = die.roll();

            if (roll + rollModifier >= numberToRoll) {
                successfulRolls ++;
            } else if (roll == numberToReRoll) {
                successfulRolls = successfulRolls + this.rollDice(1, numberToRoll, rollModifier, false);
            }
        }

        return successfulRolls;
    }
}
