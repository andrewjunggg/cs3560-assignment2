package com.andrewjunggg;

public abstract class Identity {
    private String id;

    public Identity(String idString) {
        this.id = idString;
    }

    public String getId() {
        return id;
    }

    public void setId(String idString) {
        this.id = idString;
    }
}
