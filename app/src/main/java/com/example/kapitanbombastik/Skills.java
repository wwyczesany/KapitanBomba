package com.example.kapitanbombastik;

import java.io.Serializable;
import java.util.ArrayList;

public class Skills implements Serializable {

    protected class Skill implements Serializable{

        private String name;
        private String description;
        private int level = 0;

        public Skill(String name , String description) {
            this.name = name;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public int getLevel() {
            return level;
        }
    }

    //-----------------Nested class above-------------------

    public ArrayList<Skill> skillArrayList = new ArrayList<>();

    public Skills() {

        skillArrayList.add(new Skill("Strzał w dupsko" , "Odbyt rozjebany"));
        skillArrayList.add(new Skill("Napierdalać!!!" , "Tfu! Tępy huj"));

    }
}
