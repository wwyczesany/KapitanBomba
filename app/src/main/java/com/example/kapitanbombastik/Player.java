package com.example.kapitanbombastik;

import android.view.View;

import java.io.Serializable;

public class Player extends Character implements Serializable {

    private int expMax;
    private int exp = 0;
    private int skillPoints = 5;
    public Skills skills = new Skills();

    public Player(String name) {

        super(name);
        calcMaxExp();

    }

    public Player(String name, int str, int dex, int def, int resist, int intelligence, int knowledge, int charisma , int level , int skillPoints) {

        super(name, str, dex, def, resist, intelligence, knowledge, charisma , level);

        this.skillPoints = skillPoints;
        calcMaxExp();

    }

    public void addExp(int amount){

        exp += amount;
        while(exp >= expMax)
            levelUp();

    }

    public void levelUp(){

        exp -= expMax;
        addLevel();
        calcMaxExp();
        calcMaxValues();
        fullHeal();
        skillPoints += 5;

    }

    private void calcMaxExp(){

        expMax = getLevel() * getLevel() + getLevel() * 5;

    }

    //Skill Upgrade functions

    @Override
    public void addStr() {
        super.addStr();
        skillPoints--;
    }

    @Override
    public void addDex() {
        super.addDex();
        skillPoints--;
    }

    @Override
    public void addDef() {
        super.addDef();
        skillPoints--;
    }

    @Override
    public void addResist() {
        super.addResist();
        skillPoints--;
    }

    @Override
    public void addInt() {
        super.addInt();
        skillPoints--;
    }

    @Override
    public void addKnowledge() {
        super.addKnowledge();
        skillPoints--;
    }

    @Override
    public void addCharisma() {
        super.addCharisma();
        skillPoints--;
    }


    //Getters

    public int getExpMax() {
        return expMax;
    }

    public int getExp() {
        return exp;
    }

    public int getSkillPoints() {
        return skillPoints;
    }
}
