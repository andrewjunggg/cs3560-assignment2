package com.andrewjunggg;

public abstract class Identity {
    private String id;
    private long creationTime;

    public Identity(String idString) {
        this.id = idString;
        this.creationTime = System.currentTimeMillis();
    }

    public Identity(String idString, long createdTimeLong) {
        this.id = idString;
        this.creationTime = createdTimeLong;
    }

    public String getId() {
        return id;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setId(String idString) {
        this.id = idString;
    }

    public void setCreationTime(long createdTimeLong) {
        this.creationTime = createdTimeLong;
    }
}
