package com.maro.clashroyale.domain.clan;

import java.util.ArrayList;
import java.util.List;

public class ClansHolder {
    private String searchName;
    private List<Clan> items;
    private boolean currentRiverRace = false;

    public boolean isCurrentRiverRace() {
        return currentRiverRace;
    }

    public void setCurrentRiverRace(boolean currentRiverRace) {
        this.currentRiverRace = currentRiverRace;
    }

    public List<Clan> getItems() {
        if(this.items == null) {
            this.items = new ArrayList<>();
        }
        return items;
    }

    public void setItems(List<Clan> items) {
        this.items = items;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }
}
