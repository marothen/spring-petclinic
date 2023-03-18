package com.maro.clashroyale.domain.member;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private String tag;
    private String name;
    private String role;
    private String lastSeen;
    private Integer expLevel;
    private Integer trophies;
    private Integer clanRank;
    private Integer previousClanRank;
    private Integer donations;
    private Integer donationsReceived;
    private Integer clanChestPoints;
    private Arena arena;
    private Double average;
	private Double realAverage = Double.valueOf(0);
    private List<Integer> points = new ArrayList<>();
    private List<Integer> countablePoints;
    private int currentPoints = 0;
    private int ranking;
    private double difference = 0;
    private double percent = 0;
    private long dayNotSeen;
    private boolean isParticipant = false;
    private boolean reentered = false;
    private Integer decksUsed = 0;
    private Integer decksUsedToday = 0;
    private Integer boatAttacks = 0;

    public Integer getBoatAttacks() {
        return boatAttacks;
    }

    public void setBoatAttacks(Integer boatAttacks) {
        this.boatAttacks = boatAttacks;
    }

    public Integer getDecksUsed() {
        return decksUsed;
    }

    public void setDecksUsed(Integer decksUsed) {
        this.decksUsed = decksUsed;
    }

    public Integer getDecksUsedToday() {
        return decksUsedToday;
    }

    public void setDecksUsedToday(Integer decksUsedToday) {
        this.decksUsedToday = decksUsedToday;
    }

    public void setCurrentPoints(int currentPoints) {
        this.currentPoints = currentPoints;
    }

    public boolean isReentered() {
        return reentered;
    }

    public void setReentered(boolean reentered) {
        this.reentered = reentered;
    }

    public List<Integer> getCountablePoints() {
        return countablePoints;
    }

    public void setCountablePoints(List<Integer> countablePoints) {
        this.countablePoints = countablePoints;
    }

    public boolean isParticipant() {
        return isParticipant;
    }

    public void setParticipant(boolean participant) {
        isParticipant = participant;
    }

    public long getDayNotSeen() {
        return dayNotSeen;
    }

    public void setDayNotSeen(long dayNotSeen) {
        this.dayNotSeen = dayNotSeen;
    }

    public double getDifference() {
        return difference;
    }

    public void setDifference(double difference) {
        this.difference = difference;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public Integer getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(Integer currentPoints) {
        this.currentPoints = currentPoints;
    }

    public List<Integer> getPoints() {
        return points;
    }

    public void setPoints(List<Integer> points) {
        this.points = points;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

	public Double getRealAverage() {
		return realAverage;
	}

	public void setRealAverage(Double realAverage) {
		this.realAverage = realAverage;
	}

	public String getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
    }

    public Integer getExpLevel() {
        return expLevel;
    }

    public void setExpLevel(Integer expLevel) {
        this.expLevel = expLevel;
    }

    public Integer getTrophies() {
        return trophies;
    }

    public void setTrophies(Integer trophies) {
        this.trophies = trophies;
    }

    public Integer getClanRank() {
        return clanRank;
    }

    public void setClanRank(Integer clanRank) {
        this.clanRank = clanRank;
    }

    public Integer getPreviousClanRank() {
        return previousClanRank;
    }

    public void setPreviousClanRank(Integer previousClanRank) {
        this.previousClanRank = previousClanRank;
    }

    public Integer getDonations() {
        return donations;
    }

    public void setDonations(Integer donations) {
        this.donations = donations;
    }

    public Integer getDonationsReceived() {
        return donationsReceived;
    }

    public void setDonationsReceived(Integer donationsReceived) {
        this.donationsReceived = donationsReceived;
    }

    public Integer getClanChestPoints() {
        return clanChestPoints;
    }

    public void setClanChestPoints(Integer clanChestPoints) {
        this.clanChestPoints = clanChestPoints;
    }

    public Arena getArena() {
        return arena;
    }

    public void setArena(Arena arena) {
        this.arena = arena;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
