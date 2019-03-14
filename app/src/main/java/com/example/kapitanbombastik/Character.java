package com.example.kapitanbombastik;

import java.io.Serializable;

public abstract class Character implements Serializable {

    private String name;
    private int level = 1;

    private int str = 0;
    private int dex = 0;
    private int def = 0;
    private int resist = 0;
    private int inteligence = 0;
    private int knowledge = 0;
    private int charisma = 0;

    private int hpMax;
    private int armorMax;
    private int kutasMax;
    private int hp;
    private int armor;
    private int kutas;


    public Character(String name) {

        this.name = name;
        calcMaxValues();
        fullHeal();

    }

    public Character(String name, int str, int dex, int def, int resist, int intelligence, int knowledge, int charisma , int level) {
        this.name = name;
        this.str = str;
        this.dex = dex;
        this.def = def;
        this.resist = resist;
        this.inteligence = intelligence;
        this.knowledge = knowledge;
        this.charisma = charisma;
        this.level = level;

        calcMaxValues();
        fullHeal();

    }

    public void fullHeal(){

        hp = hpMax;
        armor = armorMax;
        kutas = kutasMax;

    }

    public void calcMaxValues(){

        hpMax = 10 + def;
        armorMax = level + str + (def * 2) ;
        kutasMax = level + (charisma * 2);


    }

    //Incremental functions

    public void addLevel(){
        level++;
    }

    public void addStr(){
        str++;
    }

    public void addDex(){
        dex++;
    }

    public void addDef(){
        def++;
    }

    public void addResist(){
        resist++;
    }

    public void addInt(){
        inteligence++;
    }

    public void addKnowledge(){
        knowledge++;
    }

    public void addCharisma(){
        charisma++;
    }

    //Getters

    public String getName() {
        return name;
    }

    public int getStr() {
        return str;
    }

    public int getDex() {
        return dex;
    }

    public int getLevel() {
        return level;
    }

    public int getDef() {
        return def;
    }

    public int getResist() {
        return resist;
    }

    public int getInteligence() {
        return inteligence;
    }

    public int getKnowledge() {
        return knowledge;
    }

    public int getCharisma() {
        return charisma;
    }

    public int getHp() {
        return hp;
    }

    public int getArmor() {
        return armor;
    }

    public int getKutas() {
        return kutas;
    }

    public int getHpMax() {
        return hpMax;
    }

    public int getArmorMax() {
        return armorMax;
    }

    public int getKutasMax() {
        return kutasMax;
    }
}
