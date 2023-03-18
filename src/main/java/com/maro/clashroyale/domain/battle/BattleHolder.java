package com.maro.clashroyale.domain.battle;

import java.util.ArrayList;
import java.util.List;

public class BattleHolder {
    private List<Battle> items;

    public List<Battle> getItems() {
        if(this.items == null) {
            items = new ArrayList<>();
        }
        return items;
    }

    public void setItems(List<Battle> items) {
        this.items = items;
    }
}
