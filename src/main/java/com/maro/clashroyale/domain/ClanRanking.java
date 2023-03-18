package com.maro.clashroyale.domain;

import com.maro.clashroyale.domain.clan.Clan;
import com.maro.clashroyale.domain.clan.SimpleClan;
import com.maro.clashroyale.domain.currentriverrace.RiverRace;
import com.maro.clashroyale.domain.member.Member;

import java.util.ArrayList;
import java.util.List;

public class ClanRanking {
    private Clan clan;
    private int clanAverage;
    private int clanAverageSum;
    private List<Member> allMembers;
    private List<Member> unplayedMembers;
    private List<Member> newMembers;
    private List<Member> kickMembers;
    private List<Member> downgradeMembers;
    private List<Member> upgradeMembers;
    private List<Member> reenteredMembers;
    private List<Member> averageMembers;

    private List<SimpleClan> clans;
    private RiverRace riverRace;
    private double minimumPoints;
    private double upperPoints;

    public List<Member> getAverageMembers() {
        return averageMembers;
    }

    public void setAverageMembers(List<Member> averageMembers) {
        this.averageMembers = averageMembers;
    }

    public List<Member> getReenteredMembers() {
        return reenteredMembers;
    }

    public void setReenteredMembers(List<Member> reenteredMembers) {
        this.reenteredMembers = reenteredMembers;
    }

    public List<Member> getNewMembers() {
        return newMembers;
    }

    public void setNewMembers(List<Member> newMembers) {
        if(this.newMembers == null) {
            this.newMembers = new ArrayList<>();
        }
        this.newMembers = newMembers;
    }

    public List<Member> getUnplayedMembers() {
        if(this.upgradeMembers == null) {
            this.unplayedMembers = new ArrayList<>();
        }
        return unplayedMembers;
    }

    public void setUnplayedMembers(List<Member> unplayedMembers) {
        this.unplayedMembers = unplayedMembers;
    }

    public double getMinimumPoints() {
        return minimumPoints;
    }

    public void setMinimumPoints(double minimumPoints) {
        this.minimumPoints = minimumPoints;
    }

    public double getUpperPoints() {
        return upperPoints;
    }

    public void setUpperPoints(double upperPoints) {
        this.upperPoints = upperPoints;
    }

    public RiverRace getRiverRace() {
        return riverRace;
    }

    public void setRiverRace(RiverRace riverRace) {
        this.riverRace = riverRace;
    }

    public int getClanAverageSum() {
        return clanAverageSum;
    }

    public void setClanAverageSum(int clanAverageSum) {
        this.clanAverageSum = clanAverageSum;
    }

    public int getClanAverage() {
        return clanAverage;
    }

    public void setClanAverage(int clanAverage) {
        this.clanAverage = clanAverage;
    }

    public List<SimpleClan> getClans() {
        if(this.clans == null) {
            this.clans = new ArrayList<>();
        }
        return clans;
    }

    public void setClans(List<SimpleClan> clans) {
        this.clans = clans;
    }

    public List<Member> getKickMembers() {
        if(this.kickMembers == null) {
            this.kickMembers = new ArrayList<>();
        }
        return kickMembers;
    }

    public void setKickMembers(List<Member> kickMembers) {
        this.kickMembers = kickMembers;
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public List<Member> getAllMembers() {
        if(this.allMembers == null) {
            this.allMembers = new ArrayList<>();
        }
        return allMembers;
    }

    public void setAllMembers(List<Member> allMembers) {
        this.allMembers = allMembers;
    }

    public List<Member> getDowngradeMembers() {
        if(this.downgradeMembers == null) {
            this.downgradeMembers = new ArrayList<>();
        }
        return downgradeMembers;
    }

    public void setDowngradeMembers(List<Member> downgradeMembers) {
        this.downgradeMembers = downgradeMembers;
    }

    public List<Member> getUpgradeMembers() {
        if(this.upgradeMembers == null) {
            this.upgradeMembers = new ArrayList<>();
        }
        return upgradeMembers;
    }

    public void setUpgradeMembers(List<Member> upgradeMembers) {
        this.upgradeMembers = upgradeMembers;
    }
}
