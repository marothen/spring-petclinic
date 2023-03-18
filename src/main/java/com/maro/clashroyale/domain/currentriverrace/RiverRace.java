package com.maro.clashroyale.domain.currentriverrace;

public class RiverRace {
    private String state;
    private String warEndTime;
    private String collectionEndTime;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getWarEndTime() {
        return warEndTime;
    }

    public void setWarEndTime(String warEndTime) {
        this.warEndTime = warEndTime;
    }

    public String getCollectionEndTime() {
        return collectionEndTime;
    }

    public void setCollectionEndTime(String collectionEndTime) {
        this.collectionEndTime = collectionEndTime;
    }
}
