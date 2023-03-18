package com.maro.clashroyale.domain.battle;

public class Battle {
    private GameMode gameMode;
    private String type;
    private String boatBattleSide;
    private String boatBattleWon;
    private String battleTime;

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBoatBattleSide() {
        return boatBattleSide;
    }

    public void setBoatBattleSide(String boatBattleSide) {
        this.boatBattleSide = boatBattleSide;
    }

    public String getBoatBattleWon() {
        return boatBattleWon;
    }

    public void setBoatBattleWon(String boatBattleWon) {
        this.boatBattleWon = boatBattleWon;
    }

    public String getBattleTime() {
        return battleTime;
    }

    public void setBattleTime(String battleTime) {
        this.battleTime = battleTime;
    }
}
