package com.maro.clashroyale.dtos.clans.clanlist;

public class ClanDTO {
    private String name;
    private int members;
    private String tag;
    private String displayTag;
    private long fame;

    public String getDisplayTag() {
        return displayTag;
    }

    public void setDisplayTag(String displayTag) {
        this.displayTag = displayTag;
    }

    public long getFame() {
        return fame;
    }

    public void setFame(long fame) {
        this.fame = fame;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getMembers() {
        return members;
    }

    public void setMembers(int members) {
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
