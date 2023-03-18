package com.maro.clashroyale.controllers;

public class Clan {
    private String name;
    private int members;
    private String tag;
    private long fame = -1;

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
