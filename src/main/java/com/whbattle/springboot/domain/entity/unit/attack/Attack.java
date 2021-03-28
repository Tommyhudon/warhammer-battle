package com.whbattle.springboot.domain.entity.unit.attack;

//value object
public class Attack {
    private final int successfulAttacks;
    private final int damagePerAttack;
    private  final int rend;

    public Attack(int successfulAttacks, int damagePerAttack, int rend) {
        this.successfulAttacks = successfulAttacks;
        this.damagePerAttack = damagePerAttack;
        this.rend = rend;
    }

    public int getSuccessfulAttacks() {
        return successfulAttacks;
    }

    public int getDamagePerAttack() {
        return damagePerAttack;
    }

    public int getRend() {
        return rend;
    }
}
