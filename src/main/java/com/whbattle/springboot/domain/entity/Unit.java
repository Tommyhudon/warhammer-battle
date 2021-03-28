package com.whbattle.springboot.domain.entity;

public class Unit {

    public String name;
    public int number;
    public int save;

    //objet pour arme ?
    public int attacks;
    public int toHit;
    public int toWound;
    public int rend;
    public int damage;

    public Unit(String name, int number, int save, int attacks, int toHit, int toWound, int rend, int damage) {
        this.name = name;
        this.number = number;
        this.save = save;
        this.attacks = attacks;
        this.toHit = toHit;
        this.toWound = toWound;
        this.rend = rend;
        this.damage = damage;
    }

    private String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private int getNumber() {
        return number;
    }

    private void setNumber(int number) {
        this.number = number;
    }

    private int getSave() {
        return save;
    }

    private void setSave(int save) {
        this.save = save;
    }

    private int getAttacks() {
        return attacks;
    }

    private void setAttacks(int attacks) {
        this.attacks = attacks;
    }

    private int getToHit() {
        return toHit;
    }

    private void setToHit(int toHit) {
        this.toHit = toHit;
    }

    private int getToWound() {
        return toWound;
    }

    private void setToWound(int toWound) {
        this.toWound = toWound;
    }

    private int getRend() {
        return rend;
    }

    private void setRend(int rend) {
        this.rend = rend;
    }

    private int getDamage() {
        return damage;
    }

    private void setDamage(int damage) {
        this.damage = damage;
    }
}
