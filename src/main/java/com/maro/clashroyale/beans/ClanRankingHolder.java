package com.maro.clashroyale.beans;

import com.maro.clashroyale.domain.ClanRanking;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.SessionScope;

@Component
@ApplicationScope
public class ClanRankingHolder {

    private ClanRanking ranking;

    public ClanRanking getRanking() {
        return ranking;
    }

    public void setRanking(ClanRanking ranking) {
        this.ranking = ranking;
    }
}
