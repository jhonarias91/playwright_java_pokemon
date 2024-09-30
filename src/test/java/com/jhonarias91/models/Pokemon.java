package com.jhonarias91.models;

import java.util.List;

public class Pokemon {
    private String name;
    private String item;
    private String ability;
    private EVS evs;
    private List<String> moves;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public EVS getEvs() {
        return evs;
    }

    public void setEvs(EVS evs) {
        this.evs = evs;
    }

    public List<String> getMoves() {
        return moves;
    }

    public void setMoves(List<String> moves) {
        this.moves = moves;
    }
}