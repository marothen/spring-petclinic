package com.maro.clashroyale.domain.clan;

public class SimpleClan {
    private String tag;
    private String name;
    private String fame;
	private int fameAsNumber;
    private int clanAverage;
    private int clanAverageSum;
    private int difference;
    private double differencePercent;
    private int members;
    private String description;
    private String finishTime;
    private int clanScore;

	private String strength;

	private String memberStrength;

	private int decksUsedToday = 0;
	private int possibleDecksToday = 0;

	private int boatAttacks = 0;

    public int getClanScore() {
        return clanScore;
    }

    public void setClanScore(int clanScore) {
        this.clanScore = clanScore;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public double getDifferencePercent() {
        return differencePercent;
    }

    public void setDifferencePercent(double differencePercent) {
        this.differencePercent = differencePercent;
    }

    public int getDifference() {
        return difference;
    }

    public void setDifference(int difference) {
        this.difference = difference;
    }

    public int getMembers() {
        return members;
    }

    public void setMembers(int members) {
        this.members = members;
    }

    public int getClanAverage() {
        return clanAverage;
    }

    public void setClanAverage(int clanAverage) {
        this.clanAverage = clanAverage;
    }

    public int getClanAverageSum() {
        return clanAverageSum;
    }

    public void setClanAverageSum(int clanAverageSum) {
        this.clanAverageSum = clanAverageSum;
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

    public String getFame() {
        return fame;
    }

    public void setFame(String fame) {
        this.fame = fame;
    }

	public int getFameAsNumber() {
		return fameAsNumber;
	}

	public void setFameAsNumber(int fameAsNumber) {
		this.fameAsNumber = fameAsNumber;
	}

	public String getStrength() {
		return strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public String getMemberStrength() {
		return memberStrength;
	}

	public void setMemberStrength(String memberStrength) {
		this.memberStrength = memberStrength;
	}

	public int getDecksUsedToday() {
		return decksUsedToday;
	}

	public void setDecksUsedToday(int decksUsedToday) {
		this.decksUsedToday = decksUsedToday;
	}

	public int getPossibleDecksToday() {
		return possibleDecksToday;
	}

	public void setPossibleDecksToday(int possibleDecksToday) {
		this.possibleDecksToday = possibleDecksToday;
	}

	public int getBoatAttacks() {
		return boatAttacks;
	}

	public void setBoatAttacks(int boatAttacks) {
		this.boatAttacks = boatAttacks;
	}
}
