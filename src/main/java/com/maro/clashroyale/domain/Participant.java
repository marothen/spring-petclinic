package com.maro.clashroyale.domain;

public class Participant {
    private String tag;
    private String name;
    private int fame = 0;
    private int repairPoints = 0;
    private int boatAttacks = 0;
    private int decksUsed = 0;
    private int decksUsedToday = 0;

    public int getDecksUsed() {
        return decksUsed;
    }

    public void setDecksUsed(int decksUsed) {
        this.decksUsed = decksUsed;
    }

    public int getDecksUsedToday() {
        return decksUsedToday;
    }

    public void setDecksUsedToday(int decksUsedToday) {
        this.decksUsedToday = decksUsedToday;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getPoints() {
        return this.getFame() + getRepairPoints();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFame() {
        return fame;
    }

    public void setFame(Integer fame) {
        this.fame = fame;
    }

    public Integer getRepairPoints() {
        return repairPoints;
    }

    public void setRepairPoints(Integer repairPoints) {
        this.repairPoints = repairPoints;
    }

    public Integer getBoatAttacks() {
        return boatAttacks;
    }

    public void setBoatAttacks(Integer boatAttacks) {
        this.boatAttacks = boatAttacks;
    }
}
