package com.jhonarias91.models;

import java.util.List;
import java.util.Map;

public class PokemonTeam {
    private String format;
    private String gen;
    private List<Pokemon> team;
    private List<String> moveInputNames;
    private Map<String, String> statInputNames;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public List<Pokemon> getTeam() {
        return team;
    }

    public void setTeam(List<Pokemon> team) {
        this.team = team;
    }

    public List<String> getMoveInputNames() {
        return moveInputNames;
    }

    public void setMoveInputNames(List<String> moveInputNames) {
        this.moveInputNames = moveInputNames;
    }

    public Map<String, String> getStatInputNames() {
        return statInputNames;
    }

    public void setStatInputNames(Map<String, String> statInputNames) {
        this.statInputNames = statInputNames;
    }
}

