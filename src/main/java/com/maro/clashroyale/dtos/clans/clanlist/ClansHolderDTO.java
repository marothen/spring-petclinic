package com.maro.clashroyale.dtos.clans.clanlist;

import java.util.ArrayList;
import java.util.List;

public class ClansHolderDTO {
    private String searchName;
    private List<ClanDTO> clans;
    private boolean currentRiverRace = false;

    public boolean isCurrentRiverRace() {
        return currentRiverRace;
    }

    public void setCurrentRiverRace(boolean currentRiverRace) {
        this.currentRiverRace = currentRiverRace;
    }

    public List<ClanDTO> getClans() {
        if(this.clans == null) {
            this.clans = new ArrayList<>();
        }
        return clans;
    }

    public void setClans(List<ClanDTO> clans) {
        this.clans = clans;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }
}
