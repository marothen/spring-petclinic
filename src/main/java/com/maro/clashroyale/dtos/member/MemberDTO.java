package com.maro.clashroyale.dtos.member;

import java.util.ArrayList;
import java.util.List;

public class MemberDTO {
    private String name;
    private Integer expLevel;
    private List<BattleDTO> battles;

    public Integer getExpLevel() {
        return expLevel;
    }

    public void setExpLevel(Integer expLevel) {
        this.expLevel = expLevel;
    }

    public List<BattleDTO> getBattles() {
        if(this.battles == null) {
            this.battles = new ArrayList<>();
        }
        return battles;
    }

    public void setBattles(List<BattleDTO> battles) {
        this.battles = battles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
