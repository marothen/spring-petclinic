package com.maro.clashroyale.domain.riverracelog;

import com.maro.clashroyale.domain.clan.Clan;

public class Standing {
    private Integer rank;
    private Integer trophyChange;
    private Clan clan;

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getTrophyChange() {
        return trophyChange;
    }

    public void setTrophyChange(Integer trophyChange) {
        this.trophyChange = trophyChange;
    }
}
