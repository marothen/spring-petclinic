package com.maro.clashroyale.domain.currentriverrace;

import com.maro.clashroyale.domain.clan.Clan;
import com.maro.clashroyale.domain.clan.SimpleClan;

import java.util.List;

public class CurrentRiverRace {
    private String state;
    private Clan clan;
    private String collectionEndTime;
    private String warEndTime;
    private List<SimpleClan> clans;

    public String getCollectionEndTime() {
        return collectionEndTime;
    }

    public void setCollectionEndTime(String collectionEndTime) {
        this.collectionEndTime = collectionEndTime;
    }

    public String getWarEndTime() {
        return warEndTime;
    }

    public void setWarEndTime(String warEndTime) {
        this.warEndTime = warEndTime;
    }

    public List<SimpleClan> getClans() {
        return clans;
    }

    public void setClans(List<SimpleClan> clans) {
        this.clans = clans;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }
}
