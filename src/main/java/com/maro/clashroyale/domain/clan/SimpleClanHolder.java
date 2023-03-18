package com.maro.clashroyale.domain.clan;

import java.util.ArrayList;
import java.util.List;

public class SimpleClanHolder {
    private List<SimpleClan> items;
    private String searchName;
    private boolean searchActiveClans = false;

    public boolean getSearchActiveClans() {
        return searchActiveClans;
    }

    public void setSearchActiveClans(boolean searchActiveClans) {
        this.searchActiveClans = searchActiveClans;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public List<SimpleClan> getItems() {
        if(this.items == null) {
            this.items = new ArrayList<>();
        }
        return items;
    }

    public void setItems(List<SimpleClan> items) {
        this.items = items;
    }
}
