package com.maro.clashroyale.domain.clan;

import com.maro.clashroyale.domain.Participant;

import java.util.List;

public class Clan {
    private String tag;
    private String name;
    private Long badgeId;
    private Long fame;
    private Integer repairPoints;
    private String finishTime;
    private Integer clanScore;

	private Integer periodPoints;
    private List<Participant> participants;

	private int decksUsedToday = 0;
	private int possibleDecksToday = 0;

	private int boatAttacks = 0;

    public Integer getClanScore() {
        return clanScore;
    }

    public void setClanScore(Integer clanScore) {
        this.clanScore = clanScore;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
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

    public Long getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(Long badgeId) {
        this.badgeId = badgeId;
    }

    public Long getFame() {
        return fame;
    }

    public void setFame(Long fame) {
        this.fame = fame;
    }

    public Integer getRepairPoints() {
        return repairPoints;
    }

    public void setRepairPoints(Integer repairPoints) {
        this.repairPoints = repairPoints;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

	public Integer getPeriodPoints() {
		return periodPoints;
	}

	public void setPeriodPoints(Integer periodPoints) {
		this.periodPoints = periodPoints;
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
